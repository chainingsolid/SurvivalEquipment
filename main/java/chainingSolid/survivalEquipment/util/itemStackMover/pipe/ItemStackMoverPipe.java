package chainingSolid.survivalEquipment.util.itemStackMover.pipe;

import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import chainingSolid.survivalEquipment.util.itemStackMover.ItemStackMoverPipeLocation;
import chainingSolid.survivalEquipment.util.itemStackMover.direction.EnumPipeDirection;


public abstract class ItemStackMoverPipe{
	
	public ItemStackMoverPipeLocation location;
	
	public ItemStackMoverPipe(){
		
	}
	
	public EnumPipeDirection XDirection, YDirection,ZDirection;
	
	public abstract List<ItemStackMoverPipe> connect(List<ItemStackMoverPipe> adjacentPipes);
	
	public abstract boolean canTake(ItemStack stack , EnumPipeDirection fromDirection);
	
	public abstract void take(ItemStack stack , EnumPipeDirection fromDirection);
	
	public abstract void update();
	
	public abstract IInventory getInventory();
}