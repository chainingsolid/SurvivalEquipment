package chainingSolid.survivalEquipment.loading;

import chainingSolid.survivalEquipment.tileEnties.EnchantedBagChestTile;
import chainingSolid.survivalEquipment.tileEnties.EnchantedBookShelfTile;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerTile;
import chainingSolid.survivalEquipment.tileEnties.ItemStackMoverPipesTile;
import chainingSolid.survivalEquipment.tileEnties.LostLandPortalTile;
import chainingSolid.survivalEquipment.tileEnties.MobSpawnerQuickenerTile;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntities {
	
	public static void Init(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(ItemStackMoverPipesTile.class, "ItemStackMoverPipesTile");
		GameRegistry.registerTileEntity(EnchantedEnderDiggerTile.class, "EnchantedEnderDiggerTile");
		GameRegistry.registerTileEntity(EnchantedBagChestTile.class, "ChestOfAdvancednessTile");
		GameRegistry.registerTileEntity(EnchantedBookShelfTile.class, "EnchantedBookShelfTile");
		GameRegistry.registerTileEntity(MobSpawnerQuickenerTile.class, "MobSpawnerQuickenerTile");
		GameRegistry.registerTileEntity(LostLandPortalTile.class, "LostLandPortalTile");
	}
	
}
