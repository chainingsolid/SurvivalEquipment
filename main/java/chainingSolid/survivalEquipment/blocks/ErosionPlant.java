package chainingSolid.survivalEquipment.blocks;

import java.util.HashMap;
import java.util.Random;

import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class ErosionPlant extends Block implements LoadableBlock{
	
	
	public static HashMap<Block,Block> erodableBlockList = new HashMap<Block, Block>();
	
	
	public ErosionPlant(Material p_i45394_1_) {
		super(p_i45394_1_);
		this.setTickRandomly(true);
		
		erodableBlockList.put(Blocks.stone, Blocks.cobblestone);
		erodableBlockList.put(Blocks.cobblestone, Blocks.gravel);
		erodableBlockList.put(Blocks.gravel, Blocks.dirt);
		
	}
	
	@Override
	public String getIdentifier() {
		return "erosion_plant";
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
	public void updateTick(World world, int x, int y, int z, Random rand){
		int meta = world.getBlockMetadata(x, y, z);
		System.out.println(meta);
		if(meta < 16){
			world.setBlockMetadataWithNotify(x, y, z, meta+1, 3);
		}
			int nearByBlockX = x + getRandomOffset(rand);
			int nearByBlockZ = z + getRandomOffset(rand);
			int nearByBlockY = y + rand.nextInt(2)-1;
			
			Block block = world.getBlock(nearByBlockX, nearByBlockY,nearByBlockZ);
			if(erodableBlockList.containsKey(block)){
				world.setBlock(nearByBlockX, nearByBlockY, nearByBlockZ, erodableBlockList.get(block));
			}
			
			Block blockBelow = world.getBlock(x, y-1, z);
			if(erodableBlockList.containsValue(blockBelow) && !erodableBlockList.containsKey(blockBelow)){
				world.setBlock(x, y-1, z, this);
			}
		
	}
	
	private int getRandomOffset(Random rand){
		return rand.nextInt(3)-1;
	}
	
	public int tickRate(World world){
		return 1;
	}
}
