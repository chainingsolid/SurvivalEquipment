package chainingSolid.survivalEquipment.dimension.biomes;

import java.util.ArrayList;
import java.util.Random;

import chainingSolid.survivalEquipment.dimension.GenBox;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenSpikes;

public class MountianOcean extends LostLandBiome {
	
	public MountianOcean(int par1) {
		super(par1);
		this.biomeName = "MountainsOcean";
		this.heightVariation = 1F;
		this.rootHeight = -.8F;
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.stone;
		this.setEnableSnow();
		this.field_150604_aj = 900;
	}
	
	
	
	@Override
	public void decorate(World world, Random rand,int x,int z){
		super.decorate(world, rand, x, z);
		
	}
	
	
	
	@Override
	public ArrayList<GenBox> genBoxes(int chunckX, int chunckZ) {
		ArrayList<GenBox> boxes = super.genBoxes(chunckX, chunckZ);
		
		GenBox stone = new GenBox(Blocks.stone, chunckX*16, 2, chunckZ*16, chunckX*16+16, 70, chunckZ*16+16);
		
		boxes.add(stone);
		
		return boxes;
	}
	
	
	
}
