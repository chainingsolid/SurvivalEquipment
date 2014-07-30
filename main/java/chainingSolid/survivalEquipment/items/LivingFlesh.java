package chainingSolid.survivalEquipment.items;

import net.minecraft.item.Item;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;

public class LivingFlesh extends Item implements LoadableItem {
	
	public LivingFlesh() {
		this.iconString =SurvivalEquipment.MODRESOURCEPATH+"living-flesh";
	}
	
	@Override
	public String getIdentifier() {
		return "living_flesh";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
}
