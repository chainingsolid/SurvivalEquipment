package chainingSolid.survivalEquipment.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import chainingSolid.survivalEquipment.tileEnties.ItemStackMoverPipesTile;

public class ItemStackMoverPipesRenderer extends TileEntitySpecialRenderer {
	
	
	ResourceLocation texture;
	ItemStackMoverPipesModel model;
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTickTime) {
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(-1F, -1F, 1F);
		
		
		this.bindTexture(texture);
		
		model.render((ItemStackMoverPipesTile)tile, partialTickTime,0.046F);
		
		GL11.glPopMatrix();
	}
	
	
	
}
