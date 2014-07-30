package chainingSolid.survivalEquipment.util.livingEquipment;

import java.util.Random;

import chainingSolid.survivalEquipment.loading.ItemList;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LivingEquipmentProgresser {
	
	public static Random rand = new Random();
	
	public static void runCycleOnEquipment(ILivingEquipment equipment , ItemStack stack){
		if(equipment.canUpgrade(stack)){
			if(equipment.getLevel(stack) < equipment.getMaxLevel(stack)){
				if(equipment.getUpgradeChance(stack) < rand.nextFloat()){
					equipment.levelUp(stack);
				}
			}
		}
	}
	
	public static void updateLivingEquipmentInInv(IInventory inv){
		for(int index = 0; index < inv.getSizeInventory();index++){
			ItemStack stack = inv.getStackInSlot(index);
			if(stack != null){
				if(stack.getItem() instanceof ILivingEquipment){
					ILivingEquipment equipment = (ILivingEquipment)stack.getItem();
					runCycleOnEquipment(equipment,stack);
					runRepairCycle(inv, equipment, stack);
				}
			}
		}
	}
	
	public static void runRepairCycle(IInventory inv , ILivingEquipment equipment , ItemStack equipmentStack){
		
		if(equipmentStack.getMaxDamage() > equipmentStack.getItemDamage() && equipmentStack.getItemDamage() > 0){
			for(int index = 0 ; index < inv.getSizeInventory();index++){
				ItemStack stack = inv.getStackInSlot(index);
				if(stack != null){
					if(stack.getItem() == ItemList.livingFlesh){
						equipmentStack.setItemDamage(equipmentStack.getItemDamage() - 1);
						stack.stackSize--;
						if(stack.stackSize == 0){
							inv.setInventorySlotContents(index, null);
						}
					}
				}
			}
		}
	}
	
}
