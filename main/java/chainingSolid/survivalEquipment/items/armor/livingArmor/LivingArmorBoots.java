package chainingSolid.survivalEquipment.items.armor.livingArmor;

import net.minecraft.item.Item;

public class LivingArmorBoots extends LivingArmor {
	
	public LivingArmorBoots() {
		super(3);
		
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	@Override
	public String getIdentifier() {
		return "living_armor_boots";
	}
	
}
