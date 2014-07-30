package chainingSolid.survivalEquipment.util.livingEquipment.effects;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.EnumHelper;
import chainingSolid.survivalEquipment.util.livingEquipment.LivingEquipmentEffect;

public class DamadgeAdaptionEffect implements LivingEquipmentEffect {
	
	public final String MAX_DAMADGE_ABSORTION = "MAX_DAMADGE_ABSORTION",
			DAMADGE_TAKEN = "DAMADGE_TAKEN";
	
	public DamadgeAdaptionEffect() {
		
	}
	
	@Override
	public void onTick(EntityLivingBase entity, ItemStack stack) {
		int damadgeTaken = stack.getTagCompound().getInteger(DAMADGE_TAKEN);
		double damadgeAbsorption = stack.getTagCompound().getDouble(MAX_DAMADGE_ABSORTION);
		
		
		
		
	}
	
	@Override
	public String getEffectName() {
		
		return null;
	}
	
	
	
	
	
	
	
	
	
}
