package chainingSolid.survivalEquipment.loading;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.entity.HeavyArrow;
import chainingSolid.survivalEquipment.entity.LivingArmorWings;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityRegister {
	
	public EntityRegister() {
		
	}
	
	
	public static void Init(FMLInitializationEvent event){
		EntityRegistry.registerModEntity(LivingArmorWings.class, "LivingArmorWings", 0, SurvivalEquipment.instance, 200, 1, true);
		EntityRegistry.registerModEntity(HeavyArrow.class, "HeavyArrow", 0, SurvivalEquipment.instance, 200, 1, true);
	}
	
	
	
}
