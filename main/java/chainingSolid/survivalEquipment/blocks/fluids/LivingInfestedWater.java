package chainingSolid.survivalEquipment.blocks.fluids;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;

public class LivingInfestedWater extends BlockFluidClassic implements LoadableBlock{
	
	public static LivingInfestedFluid fliud = new LivingInfestedFluid("Living infested water");
	
	public LivingInfestedWater(Material m) {
		super(fliud, m);
		FluidRegistry.registerFluid(fliud);
		this.setTickRandomly(true);
	}
	
	@Override
	public String getIdentifier() {
		return "living_infested_water";
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
	@Override
	public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
		return null;
	}
	
	@Override
	public boolean canDrain(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public int getQuantaValue(IBlockAccess world, int x, int y, int z) {
		return 1;
	}
	
	@Override
	public boolean canCollideCheck(int meta, boolean fullHit){
		return false;
	}
	
	@Override
	public int getMaxRenderHeightMeta() {
		return 0;
	}
	
	private void setBlockToLivingInfestedWater(World world, int x,int y,int z, int meta){
		world.setBlock(x, y, z, this.getInstance());
		world.setBlockMetadataWithNotify(x, y, z, meta, 1);
	}
	
}
