package chainingSolid.survivalEquipment.items;

import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import net.minecraft.item.Item;

public class ZisteauEnemyMarker extends Item implements LoadableItem{
	
	public ZisteauEnemyMarker() {
		
	}
	
	@Override
	public String getIdentifier() {
		return "zisteau_enemy_marker";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
}
