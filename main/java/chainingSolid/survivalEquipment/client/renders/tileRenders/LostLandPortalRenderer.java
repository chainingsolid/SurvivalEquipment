package chainingSolid.survivalEquipment.client.renders.tileRenders;

import org.lwjgl.opengl.GL11;

import chainingSolid.survivalEquipment.blocks.Portal;
import chainingSolid.survivalEquipment.tileEnties.LostLandPortalTile;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class LostLandPortalRenderer extends TileEntitySpecialRenderer {
	
	private static ResourceLocation texture = new ResourceLocation("survivalutils", "textures/models/lost_land_portal.png");
	private static LostLandPortalModel model = new LostLandPortalModel();
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		
		GL11.glPushMatrix();
		GL11.glTranslatef( (float)x, (float)y, (float)z);
		GL11.glScalef(1F, 1F, 1F);
		GL11.glTranslatef(.5F, .5F, .5F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		
		model.renderPortal(tile);
		
		GL11.glPopMatrix();
	}
	
	
	
	
	
	
	
	
	
	
}
