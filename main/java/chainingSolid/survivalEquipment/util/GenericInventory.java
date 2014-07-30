package chainingSolid.survivalEquipment.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class GenericInventory implements IInventory {
	
	
	ItemStack[] inv;
	
	public GenericInventory(int size) {
		inv = new ItemStack[size];
	}
	
	@Override
	public int getSizeInventory() {
		return inv.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		if(i >= this.getSizeInventory()){
			Util.enterDebug();
		}
		return inv[i];
	}
	
	@Override
	public ItemStack decrStackSize(int i, int amount) {
		ItemStack stack = this.getStackInSlot(i);
		if(stack != null){
			if(stack.stackSize <= amount){
				this.setInventorySlotContents(i, null);
			}else{
				stack = stack.splitStack(amount);
			}
			
		}
		return stack;
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = this.getStackInSlot(i);
		this.setInventorySlotContents(i, null);
		return item;
	}
	
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		inv[i] = stack;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return true;
	}
	
	@Override
	public void closeInventory() {}
	
	@Override
	public String getInventoryName() {
		return null;
	}
	
	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}
	
	@Override
	public void markDirty() {
		
	}
	
	@Override
	public void openInventory() {
	}
	
	public final String TAG_KEY="TAG_KEY";
	
	public NBTTagCompound writeInventoryToTag(){
		NBTTagCompound tag = new NBTTagCompound();
		for(int index = 0; index < this.getSizeInventory();index++){
			if(this.getStackInSlot(index) != null){
				tag.setTag(TAG_KEY+index, this.getStackInSlot(index).writeToNBT(new NBTTagCompound()));
			}
		}
		return tag;
	}
	
	public void setInventoryFromTag(NBTTagCompound nbtTagCompound){
		for(int index = 0; index < this.getSizeInventory();index++){
			this.setInventorySlotContents(index, ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag(TAG_KEY+index)));
		}
	}
	
	
	
	
}
