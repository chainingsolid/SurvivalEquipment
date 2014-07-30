package chainingSolid.survivalEquipment.loading;

import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {
	
	public ClientProxy() {
			
	}
	
	@Override
	public void Init(FMLInitializationEvent event){
		super.Init(event);
		RenderingRegister.Init(event);
		
	}
	
	
	@Override
	public boolean isClient(){
		return true;
	}
}
