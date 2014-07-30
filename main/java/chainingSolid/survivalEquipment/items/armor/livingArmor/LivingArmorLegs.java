package chainingSolid.survivalEquipment.items.armor.livingArmor;

import net.minecraft.item.Item;
import chainingSolid.survivalEquipment.util.livingEquipment.LivingEquipmentEffect;

public class LivingArmorLegs extends LivingArmor {
	
	public LivingArmorLegs() {
		super(2);
		
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	@Override
	public String getIdentifier() {
		return "living_armor_legs";
	}
	
}
