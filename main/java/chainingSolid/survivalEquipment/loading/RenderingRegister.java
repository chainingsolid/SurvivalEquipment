package chainingSolid.survivalEquipment.loading;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import chainingSolid.survivalEquipment.blocks.Portal;
import chainingSolid.survivalEquipment.client.renders.tileRenders.LostLandPortalRenderer;
import chainingSolid.survivalEquipment.tileEnties.LostLandPortalTile;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class RenderingRegister {
	
	public RenderingRegister() {
		
	}
	
	public static void Init(FMLInitializationEvent event){
		
		TileEntityRendererDispatcher.instance.mapSpecialRenderers.put(LostLandPortalTile.class, new LostLandPortalRenderer());
		
	}
	
	
}
