package chainingSolid.survivalEquipment.items.tools.livingTool;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;

public class LivingBow extends ItemBow implements LoadableItem {
	
	public LivingBow() {
		
	}
	
	@Override
	public String getIdentifier() {
		return "living_bow";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
}
