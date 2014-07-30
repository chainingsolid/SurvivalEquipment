package chainingSolid.survivalEquipment.loading;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public CommonProxy() {
		
	}
	
	public void preInit(FMLPreInitializationEvent event){
		SurvivalEquipment.LOADER.preInit(event);
	}
	
	public void Init(FMLInitializationEvent event){
		SurvivalEquipment.LOADER.Init(event);
		TileEntities.Init(event);
		EntityRegister.Init(event);
		
		
		
		
	}
	
	public void postInit(FMLPostInitializationEvent event){
		SurvivalEquipment.LOADER.PostInit(event);
	}
	
	public boolean isClient(){
		return false;
	}
	
}
