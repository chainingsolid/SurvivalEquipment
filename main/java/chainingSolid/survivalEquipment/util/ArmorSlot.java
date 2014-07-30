package chainingSolid.survivalEquipment.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ArmorSlot extends SlotThatTakesInToAccountIsItemVaildForSlot {
	
	public final int SLOT;
	public EntityPlayer thePlayer;
	
	private int[] slotConversion = new int[]{3,2,1,0};
	
	public ArmorSlot(IInventory inv, int indexInInv, int displayX, int displayY,int slot,EntityPlayer p) {
		super(inv, indexInInv, displayX, displayY);
		SLOT = slot;
		thePlayer = p;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack){
		if(stack != null){
			return stack.getItem().isValidArmor(stack, slotConversion[SLOT], thePlayer);
		}else{
			return false;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getBackgroundIconIndex(){
		return ItemArmor.func_94602_b(slotConversion[SLOT]);
	}
}
