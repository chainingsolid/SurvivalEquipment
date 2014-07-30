package chainingSolid.survivalEquipment.util.livingEquipment;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public interface LivingEquipmentEffect {
	
	public void onTick(EntityLivingBase entity, ItemStack stack);
	
	public String getEffectName();
}
