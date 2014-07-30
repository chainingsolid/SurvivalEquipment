package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import chainingSolid.survivalEquipment.SurvialEquipmentTab;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;

public class XRayBlock extends Block implements LoadableBlock {
	
	public XRayBlock(Material m){
		super(m);
		this.textureName = SurvivalEquipment.MODRESOURCEPATH+"clear";
		this.setBlockName("xray");
	}
	
	@Override
	public String getIdentifier() {
		return "xray_block";
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
}
