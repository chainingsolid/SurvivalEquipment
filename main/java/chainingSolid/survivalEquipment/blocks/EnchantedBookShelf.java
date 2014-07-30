package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import chainingSolid.survivalEquipment.tileEnties.EnchantedBookShelfTile;

public class EnchantedBookShelf extends BlockContainer implements LoadableBlock {
	
	public EnchantedBookShelf(Material m) {
		super(m);
		
	}
	
	@Override
	public String getIdentifier() {
		return "enchanted_bookself";
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new EnchantedBookShelfTile();
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
	@Override
	public float getEnchantPowerBonus(World world, int x, int y, int z){
		if(!world.isBlockIndirectlyGettingPowered(x, y, z)){
			return 1;
		}else{
			return 0;
		}
	}
	
}
