package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class LostLandTreeWoodCore extends BlockContainer {
	
	protected LostLandTreeWoodCore(Material material) {
		super(material);
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}
	
}
