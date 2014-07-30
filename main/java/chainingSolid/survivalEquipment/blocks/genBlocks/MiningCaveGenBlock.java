package chainingSolid.survivalEquipment.blocks.genBlocks;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;

public class MiningCaveGenBlock extends BlockContainer implements LoadableBlock {
	
	protected MiningCaveGenBlock(Material material) {
		super(material);
		this.setBlockUnbreakable();
	}
	
	@Override
	public String getIdentifier() {
		return "Mining-Cave-Gen-Block";
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
}
