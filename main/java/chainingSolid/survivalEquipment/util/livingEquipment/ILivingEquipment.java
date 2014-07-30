package chainingSolid.survivalEquipment.util.livingEquipment;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public interface ILivingEquipment {
	
	public boolean canUpgrade(ItemStack stack);
	
	public int getLevel(ItemStack stack);
	
	public float getMaxLevel(ItemStack stack);
	
	public float getUpgradeChance(ItemStack stack);
	
	public void levelUp(ItemStack stack);
}
