package chainingSolid.survivalEquipment.tileEnties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.network.EventData.EnchantedEnderDiggerSyncData;
import chainingSolid.survivalEquipment.util.GenericInventory;

public class EnchantedEnderDiggerTile extends TileEntity implements IInventory{
	
	
	//TODO make mining only skip a block when it was told to. and fix crash on crops
	
	GenericInventory inventory = new GenericInventory(7){
		@Override
		public boolean isItemValidForSlot(int i, ItemStack stack) {
			if(i != 6){
				if(stack.getItem().equals(Items.iron_ingot)){
					return true;
				}else{
					return false;
				}
			}else{
				if(stack.getItem() instanceof ItemWritableBook){
					return true;
				}else{
					return false;
				}
			}
		}
	};
	
	public final int
			X_MIN_SLOT = 0,
			X_MAX_SLOT = 1,
			Y_MIN_SLOT = 2,
			Y_MAX_SLOT = 3,
			Z_MIN_SLOT = 4,
			Z_MAX_SLOT = 5;
	
	HashMap<String,List<String>> blockNameToToolList = new HashMap<String,List<String>>();
	
	ArrayList<IInventory> localInvs = new ArrayList<IInventory>();
	
	public int XOffMin,XOffMax,YOffMin,YOffMax,ZOffMin,ZOffMax;
	
	public int currentXOff, currentYOff , currentZOff;
	
	public int coolDown;
	
	public EnchantedEnderDiggerTile() {
		
	}
	
	@Override
	public void updateEntity(){
		if(worldObj.isRemote){
			worldObj.getBlock(xCoord, yCoord, zCoord).randomDisplayTick(worldObj, xCoord, yCoord, zCoord, new Random());
			return;
		}
		coolDown = 0;
		if(coolDown > 0){
			coolDown--;
		}else{
			this.updateLocalInvs();
			this.updateRange();
			
			this.updateMineMappingsFromBook();
			
			this.runCycle();
			this.syncWithClient();
		}
		
	}
	
	private void syncWithClient(){
		
		try{
			EnchantedEnderDiggerSyncData data = new EnchantedEnderDiggerSyncData();
			data.xcoord = xCoord;
			data.ycoord = yCoord;
			data.zcoord = zCoord;
			data.currentXOff = currentXOff;
			data.currentYOff = currentYOff;
			data.currentZOff = currentZOff;
			data.XOffMin = XOffMin;
			data.XOffMax = XOffMax;
			data.YOffMin = YOffMin;
			data.YOffMax = YOffMax;
			data.ZOffMin = ZOffMin;
			data.ZOffMax = ZOffMax;
			data.dimId = this.worldObj.provider.dimensionId;
			FMLProxyPacket pck = new FMLProxyPacket(data.writeDataToArray(),SurvivalEquipment.CHANNEL_NAME);
			SurvivalEquipment.channel.sendToAll(pck);
		}catch(Exception e){
			
		}
	}
	
	private void updateMineMappingsFromBook(){
		ItemStack stack = this.getStackInSlot(6);
		if(stack != null){
			if(stack.getItem() instanceof ItemWritableBook){
				if(stack.hasTagCompound()){
					if(stack.getTagCompound().hasKey("pages")){
						NBTTagList pageTag = stack.getTagCompound().getTagList("pages", 8);
						String[] info = new String[pageTag.tagCount()];
						for(int i = 0;i < pageTag.tagCount();i++){
							info[i] = pageTag.getStringTagAt(i);
						}
						this.updateMineMapingsFromString(info);
						return;
					}
				}
			}
		}
		this.updateMineMapingsFromString(new String[]{""});
	}
	
	private void updateMineMapingsFromString(String stringArray[]){
		blockNameToToolList.clear();
		for(String UncleanString : stringArray){
			try{
				String cleanString = this.simplifyString(UncleanString);
				
				String[] tools_blocks = cleanString.split(":");
				String tools = tools_blocks[0];
				String[] toolListArray = tools.split(",");
				String blocks = tools_blocks[1];
				String[] blockListArray = blocks.split(",");
				
				ArrayList<String> toolList = new ArrayList<String>();
				for(String tool : toolListArray){
					toolList.add(tool);
				}
				
				for(String key : blockListArray){
					blockNameToToolList.put(key, toolList);
				}
				
			}catch(Exception e){
				
			}
		}
		
	}
	
	private void runCycle(){
		if(isCurrentPosInRange()){
			this.incrementPosAndAttemptMine();
		}else{
			this.resetPos();
		}
	}
	
	private void incrementPosAndAttemptMine(){
		this.currentYOff++;
		if(this.currentYOff > this.YOffMax){
			this.currentYOff = this.YOffMin;
			this.currentXOff++;
			if(this.currentXOff > this.XOffMax){
				this.currentXOff = this.XOffMin;
				this.currentZOff++;
				if(this.currentZOff > this.ZOffMax){
					this.resetPos();
				}
			}
		}
		this.runCycleOnBlock(currentXOff + this.xCoord, currentYOff + this.yCoord, currentZOff + this.zCoord);
	}
	
	private void resetPos(){
		this.currentXOff = this.XOffMin;
		this.currentYOff = this.YOffMin;
		this.currentZOff = this.ZOffMin;
	}
	
	private boolean isCurrentPosInRange(){
		if((this.XOffMin <= this.currentXOff)&&(this.currentXOff <= this.XOffMax)){
			if((this.YOffMin <= this.currentYOff)&&(this.currentYOff <= this.YOffMax)){
				if((this.ZOffMin <= this.currentZOff)&&(this.currentZOff <= this.ZOffMax)){
					return true;
				}
			}
		}
		return false;
	}
	
	private void runCycleOnBlock(int x,int y,int z){
		if(y < 0 || y > 256){
			return;
		}
		Block block = worldObj.getBlock(x, y, z);
		if(block instanceof BlockAir){
			return;
		}
		
		int metadata = worldObj.getBlockMetadata(x,y,z);
		ItemStack stack = null;
		try{
			stack = this.getStackForBlock(simplifyString(new ItemStack(Item.getItemFromBlock(block),0,metadata).getDisplayName()));
		}catch(NullPointerException e){
			System.out.println(block.getLocalizedName());
		}
		if(stack == null){
			return;
		}else{
			if(stack.getItem().canHarvestBlock(block, stack) || block.getMaterial().isToolNotRequired()){
				ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
				block.onBlockPreDestroy(worldObj, x, y, z, metadata);
				stack.attemptDamageItem(1, new Random());
				boolean canSilkHarvest = false;
				try{
					canSilkHarvest = block.canSilkHarvest(worldObj, null, x, y, z, metadata) && 0 < EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, stack);
				}catch(Exception e){
					
				}
				if(canSilkHarvest){
					drops.add(new ItemStack(block,1,metadata));
				}else{
					drops = block.getDrops(worldObj, x, y, z, metadata, EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, stack));
				}
				this.coolDown = (int)(stack.getItem().getDigSpeed(stack, block, metadata) - block.getBlockHardness(worldObj, x, y, z));
				worldObj.setBlock(x, y, z, Blocks.air);
				this.outputCollectedDrops(drops);
			}
		}
	}
	
	public ItemStack getStackForBlock(String nameOfBlock){
		List<String> apropriteTools = blockNameToToolList.get(nameOfBlock);
		if(apropriteTools == null || nameOfBlock.equalsIgnoreCase("tile.air.name")){
			return null;
		}else{
			ItemStack stack;
			if(!localInvs.isEmpty()){
				for(IInventory i : localInvs){
					for(int slot = 0; slot < i.getSizeInventory(); slot++){
						stack = i.getStackInSlot(slot);
						if(stack != null){
							if(apropriteTools.contains(simplifyString(stack.getDisplayName()))){
								if((stack.getMaxDamage() - stack.getItemDamage() > 5) || !stack.isItemDamaged()){
									return stack;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	private void outputCollectedDrops(ArrayList<ItemStack> drops){
		if(!drops.isEmpty()){
			
			ArrayList<ItemStack> dropsLeft = new ArrayList<ItemStack>();
			
			A: for(ItemStack stack : drops){
				for(IInventory i : this.localInvs){
					for(int slot = 0; slot < i.getSizeInventory();slot++){
						ItemStack stackInInv = i.getStackInSlot(slot);
						if(stackInInv == null){
							i.setInventorySlotContents(slot, stack);
							continue A;
						}else{
							if(stackInInv.getItem()==stack.getItem() && stackInInv.isStackable()){
								if(stackInInv.getMaxStackSize() > stackInInv.stackSize){
									int diff = stackInInv.getMaxStackSize() - stackInInv.stackSize;
									if(diff > stack.stackSize){
										stackInInv.stackSize += stack.stackSize;
										continue A;
									}else{
										stackInInv.stackSize = stackInInv.getMaxStackSize();
										stack.stackSize -= diff;
									}
								}
							}
						}
						
					}
				}
				dropsLeft.add(stack);
			}
			
			if(!dropsLeft.isEmpty()){
				for(ItemStack stack : dropsLeft){
					EntityItem e = new EntityItem(worldObj, xCoord, yCoord+2, zCoord, stack);
					worldObj.spawnEntityInWorld(e);
				}
			}
		}
	}
	
	private void updateLocalInvs(){
		localInvs.clear();
		for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS){
			TileEntity e = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord+direction.offsetY, zCoord+direction.offsetZ);
			if(e instanceof IInventory){
				localInvs.add((IInventory)e);
			}
		}
	}
	
	private void updateRange(){
		this.XOffMin = -this.getSizeOfStackInSlot(X_MIN_SLOT);
		this.XOffMax = this.getSizeOfStackInSlot(X_MAX_SLOT);
		this.YOffMin = -this.getSizeOfStackInSlot(Y_MIN_SLOT);
		this.YOffMax = this.getSizeOfStackInSlot(Y_MAX_SLOT);
		this.ZOffMin = -this.getSizeOfStackInSlot(Z_MIN_SLOT);
		this.ZOffMax = this.getSizeOfStackInSlot(Z_MAX_SLOT);
		if(this.YOffMin+this.yCoord < 0){
			this.YOffMin = 0 - this.yCoord;
		}
		if(this.YOffMax > 256){
			this.YOffMax = 256;
		}
	}
	
	private int getSizeOfStackInSlot(int slot){
		ItemStack stack = inventory.getStackInSlot(slot);
		if(stack == null){
			return 0;
		}else{
			return stack.stackSize;
		}
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound NBTTagCompound){
		super.writeToNBT(NBTTagCompound);
		NBTTagCompound.setTag("inv", this.inventory.writeInventoryToTag());
		NBTTagCompound.setInteger("currentX", this.currentXOff);
		NBTTagCompound.setInteger("currentY", this.currentYOff);
		NBTTagCompound.setInteger("currentZ", this.currentZOff);
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound NBTTagCompound){
		super.readFromNBT(NBTTagCompound);
		this.inventory.setInventoryFromTag(NBTTagCompound.getCompoundTag("inv"));
		this.currentXOff = NBTTagCompound.getInteger("currentX");
		this.currentYOff = NBTTagCompound.getInteger("currentY");
		this.currentZOff = NBTTagCompound.getInteger("currentZ");
	}
	
	public String simplifyString(String s){
		return s.replace(" ", "").toLowerCase().replace("\n", "");
	}
	
	
	@Override
	public int getSizeInventory() {
		return inventory.getSizeInventory();
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory.getStackInSlot(slot);
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return inventory.decrStackSize(slot, amount);
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return inventory.getStackInSlotOnClosing(slot);
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory.setInventorySlotContents(slot, stack);
	}
	
	@Override
	public String getInventoryName() {
		return "";
	}
	
	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if(player.getDistance(xCoord, yCoord, zCoord) < 5){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void openInventory() {
		
	}
	
	@Override
	public void closeInventory() {
		
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return inventory.isItemValidForSlot(slot, stack);
	}
	
	
	
}
