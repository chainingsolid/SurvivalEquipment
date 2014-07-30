package chainingSolid.survivalEquipment.tileEnties;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class MelterTile extends TileEntity {
	
	public int currentHeat = 0;
	
	
	
	public MelterTile() {
		
	}
	
	
	
	
	public static int getBurnTime(ItemStack stack){
		return  TileEntityFurnace.getItemBurnTime(stack);
	}
	
	
	
	
	
}
