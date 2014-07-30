package chainingSolid.survivalEquipment.tileEnties;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import chainingSolid.survivalEquipment.SurvialEquipmentTab;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.items.EnchantedBag;
import chainingSolid.survivalEquipment.network.EventData.EnchantedBagChestData;
import chainingSolid.survivalEquipment.util.GenericInventory;
import chainingSolid.survivalEquipment.util.Util;
import chainingSolid.survivalEquipment.util.enchantedBagViewerGui.EnchantedBagInventory;

public class EnchantedBagChestTile extends TileEntity implements IInventory {
	
	private boolean hasSetup = false;
	
	private int bagCount = 0;
	
	private int playersViewingCount = 0;
	
	public EnchantedBagInventory[] bagInvs;
	
	public EnchantedBagInventory[] nonNullBagInvMapps;
	
	public int EnchantedBagInvSize = EnchantedBagInventory.BAG_INV_HIEGHT * EnchantedBagInventory.BAG_INV_WIDTH;
	
	public GenericInventory bagsInChest = new GenericInventory(9){
		@Override
		public boolean isItemValidForSlot(int slot, ItemStack stack){
			if(stack != null){
				if(stack.getItem() instanceof EnchantedBag){
					return true;
				}
			}
			return false;
		}
	};
	
	public EnchantedBagChestTile(){
		bagInvs = new EnchantedBagInventory[9];
	}
	
	@Override
	public void updateEntity(){
		if(!hasSetup){
			setup();
			hasSetup = true;
		}
	}
	
	private void setup(){
		for(int index = 0; index < bagsInChest.getSizeInventory();index++){
			ItemStack stack = bagsInChest.getStackInSlot(index);
			bagInvs[index] = new EnchantedBagInventory(stack);
		}
		this.refreshBagCount();
	}
	
	public boolean addBagToChest(ItemStack bag){
		for(int index = 0; index < bagsInChest.getSizeInventory();index++){
			ItemStack stack = bagsInChest.getStackInSlot(index);
			if(stack == null){
				bagsInChest.setInventorySlotContents(index, bag);
				this.setup();
				return true;
			}
		}
		return false;
	}
	
	public boolean removeBag(int slot, EntityPlayer player){
		ItemStack bag = bagsInChest.getStackInSlot(slot);
		if(bag != null){
			System.out.println("removing bag "+slot);
			bagInvs[slot].saveBagToStack(bag);
			bagInvs[slot].setThisBagNoLongerInUse(bag);
			Util.putItemStackInWorld(worldObj, player.posX,player.posY+.5,player.posZ, bag);
			this.bagsInChest.setInventorySlotContents(slot, null);
			this.hasSetup = false;
			return true;
		}
		return false;
	}
	
	public void refreshBagCount(){
		bagCount = 0;
		for(int i = 0;i < bagsInChest.getSizeInventory();i++){
			if(bagsInChest.getStackInSlot(i) != null){
				bagCount++;
			}
		}
		nonNullBagInvMapps = new EnchantedBagInventory[bagCount];
		int index = 0;
		for(int i = 0;i < bagsInChest.getSizeInventory();i++){
			if(bagsInChest.getStackInSlot(i) != null){
				nonNullBagInvMapps[index] = bagInvs[i];
				index++;
			}
		}
	}
	
	public void onDestroyed(){
		for(int i = 0; i < bagsInChest.getSizeInventory(); i++){
			ItemStack stack = bagsInChest.getStackInSlot(i);
			if(stack != null){
				Util.putItemStackInWorld(worldObj, xCoord, yCoord, zCoord, stack);
			}
			
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setTag("inv", bagsInChest.writeInventoryToTag());
		for(int i = 0; i < bagInvs.length;i++){
			if(bagInvs[i] != null){
				tag.setTag(""+i, bagInvs[i].writeInventoryToTag());
			}
		}
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		bagsInChest.setInventoryFromTag(tag.getCompoundTag("inv"));
		for(int i = 0; i < bagInvs.length;i++){
			if(tag.getTag(""+i) != null){
				bagInvs[i] = new EnchantedBagInventory(this.bagsInChest.getStackInSlot(i));
				bagInvs[i].setInventoryFromTag(tag.getCompoundTag(""+i));
			}
		}
		
	}
	
	public void sendChestToPlayer(EntityPlayer p){
		EnchantedBagChestData dataForClient = new EnchantedBagChestData();
		dataForClient.dimId = worldObj.provider.dimensionId;
		dataForClient.xCoord = this.xCoord;
		dataForClient.yCoord = this.yCoord;
		dataForClient.zCoord = this.zCoord;
		dataForClient.chestTag = new NBTTagCompound();
		this.writeToNBT(dataForClient.chestTag);
		NetworkRegistry.TargetPoint target = new NetworkRegistry.TargetPoint(dataForClient.dimId, xCoord, yCoord, zCoord, 10D);
		
		FMLProxyPacket pck = new FMLProxyPacket(dataForClient.writeDataToArray(),SurvivalEquipment.CHANNEL_NAME);
		SurvivalEquipment.channel.sendToAllAround(pck, target);
		
		
	}
	
	@Override
	public int getSizeInventory() {
		if(!this.hasSetup){
			return 0;
		}else{
			return nonNullBagInvMapps.length * EnchantedBagInvSize;
		}
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) {
		int invOffset = slot / EnchantedBagInvSize;
		int slotInInv = slot - invOffset * EnchantedBagInvSize;
		return nonNullBagInvMapps[invOffset].getStackInSlot(slotInInv);
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		int invOffset = slot / EnchantedBagInvSize;
		int slotInInv = slot - invOffset * EnchantedBagInvSize;
		return nonNullBagInvMapps[invOffset].decrStackSize(slotInInv, amount);
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		int invOffset = slot / EnchantedBagInvSize;
		int slotInInv = slot - invOffset * EnchantedBagInvSize;
		return nonNullBagInvMapps[invOffset].getStackInSlotOnClosing(slotInInv);
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		int invOffset = slot / EnchantedBagInvSize;
		int slotInInv = slot - invOffset * EnchantedBagInvSize;
		nonNullBagInvMapps[invOffset].setInventorySlotContents(slotInInv, stack);
		
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
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}
	
	@Override
	public void openInventory() {
		this.playersViewingCount++;
		System.out.println("open");
		setTextureOpenOrClosedBasedOnPlayersViewing();
	}
	
	@Override
	public void closeInventory() {
		this.playersViewingCount--;
		System.out.println("closed");
		setTextureOpenOrClosedBasedOnPlayersViewing();
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		int invOffset = slot / EnchantedBagInvSize;
		int slotInInv = slot - invOffset * EnchantedBagInvSize;
		return nonNullBagInvMapps[invOffset].isItemValidForSlot(slotInInv, stack);
	}
	
	public void setTextureOpenOrClosedBasedOnPlayersViewing(){
		if(worldObj.isRemote){
			return;
		}
		if(this.playersViewingCount > 0){
			System.out.println("you shuold now see an open bag");
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
		}else{
			System.out.println("you shuold now see a closed bag");
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
		}
	}
	
	
	
	
	
}
