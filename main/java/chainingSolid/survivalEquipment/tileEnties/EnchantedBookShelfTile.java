package chainingSolid.survivalEquipment.tileEnties;

import chainingSolid.survivalEquipment.util.GenericInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class EnchantedBookShelfTile extends TileEntity implements IInventory {
	
	private GenericInventory inv = new GenericInventory(9){
		
		@Override
		public boolean isItemValidForSlot(int slot,ItemStack stack){
			if(stack != null){
				if(stack.getItem() == Items.enchanted_book){
					return true;
				}
			}
			return false;
		}
		
	};
	
	@Override
	public void writeToNBT(NBTTagCompound tag){
		super.writeToNBT(tag);
		tag.setTag("inv", inv.writeInventoryToTag());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag){
		super.readFromNBT(tag);
		inv.setInventoryFromTag(tag.getCompoundTag("inv"));
	}
	
	@Override
	public int getSizeInventory() {
		return inv.getSizeInventory();
	}
	
	@Override
	public ItemStack getStackInSlot(int slot){
		return inv.getStackInSlot(slot);
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		return inv.decrStackSize(slot, amount);
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return inv.getStackInSlotOnClosing(slot);
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv.setInventorySlotContents(slot, stack);
	}
	
	@Override
	public String getInventoryName() {
		return "Chest of Advancedness";
	}
	
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return inv.getInventoryStackLimit();
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if(this.getDistanceFrom(player.posX, player.posY, player.posZ) < 4){
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
		return inv.isItemValidForSlot(slot, stack);
	}
	
}
