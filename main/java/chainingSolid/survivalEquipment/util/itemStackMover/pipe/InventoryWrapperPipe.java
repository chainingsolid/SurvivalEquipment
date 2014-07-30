package chainingSolid.survivalEquipment.util.itemStackMover.pipe;

import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import chainingSolid.survivalEquipment.util.itemStackMover.direction.EnumPipeDirection;


public class InventoryWrapperPipe extends ItemStackMoverPipe {
	
	private final IInventory INV;
	
	public InventoryWrapperPipe(IInventory inv){
		super();
		INV = inv;
	}
	
	
	@Override
	public List<ItemStackMoverPipe> connect(List<ItemStackMoverPipe> adjacentPipes) {
		
		return null;
	}
	
	@Override
	public boolean canTake(ItemStack stack, EnumPipeDirection fromDirection) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void take(ItemStack stack, EnumPipeDirection fromDirection) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public IInventory getInventory() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
