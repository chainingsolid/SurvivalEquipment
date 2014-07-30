package chainingSolid.survivalEquipment.client.renders;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class XPFleshRender extends Render {
	
	ResourceLocation location = new ResourceLocation("extrastuff", "textures/models/flesh.png");
	
	XPFleshModel model = new XPFleshModel();
	
	public XPFleshRender() {
		
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTickTime) {
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		GL11.glScalef(-1F, -1F, 1F);
		
		this.bindEntityTexture(entity);
		
		model.render(entity,yaw,partialTickTime,0.1F);
		
		GL11.glPopMatrix();
		
	}
	//TODO get the rendering going
	
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if(location == null){
			System.out.println("WHY");
		}
		return location;
	}
	
}
