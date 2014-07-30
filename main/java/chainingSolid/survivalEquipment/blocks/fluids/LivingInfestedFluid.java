package chainingSolid.survivalEquipment.blocks.fluids;

import net.minecraftforge.fluids.Fluid;
import chainingSolid.survivalEquipment.loading.BlockList;

public class LivingInfestedFluid extends Fluid{
	
	public LivingInfestedFluid(String fluidName) {
		super(fluidName);
		this.block = BlockList.livingInfestedWater;
		this.isGaseous = false;
	}
}