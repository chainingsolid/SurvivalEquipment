package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.tileEnties.MelterTile;

public class Melter extends BlockContainer {
	
	public Melter(Material material) {
		super(material);
		
		
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metaData) {
		return new MelterTile();
	}
	
}
