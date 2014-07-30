package chainingSolid.survivalEquipment.client.renders.tileRenders;

import java.util.ArrayList;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.tileentity.TileEntity;

public class LostLandPortalModel extends ModelBase {
	
	private final float MODEL_SIZE = 0.065F;
	
	ModelRenderer base = new ModelRenderer(this, 0, 0);
	ModelRenderer centerBox = new ModelRenderer(this, 0, 0);
	ModelRenderer pillars = new ModelRenderer(this,0,0);
	
	public LostLandPortalModel(){
		base.addBox(-9F, -.5F, -9F,
						18, 1, 18);
		base.setRotationPoint(0, -7F, 0);
		
		centerBox.addBox(-3F, -6F, -3F,
						6, 12, 6);
		centerBox.setRotationPoint(0, 0F, 0);
		
		float[] pillarOffSets = {10,-10};
		
		for(int pillarId = 0; pillarId < 2;pillarId++){
			pillars.addBox(-1.5F, -15F, -.5F+pillarOffSets[pillarId],
							3, 30, 1);
			pillars.setRotationPoint(0, 10F, 0);
		}
		
	}
	
	protected void renderPortal(TileEntity tile){
		pillars.rotateAngleY = (float)tile.getWorldObj().getWorldTime() / 75F;
		
		base.render(MODEL_SIZE);
		centerBox.render(MODEL_SIZE);
		pillars.render(MODEL_SIZE);
	}
	
}
