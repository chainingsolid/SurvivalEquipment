package chainingSolid.survivalEquipment.dimension.biomes;

import java.util.ArrayList;
import java.util.Random;

import chainingSolid.survivalEquipment.dimension.GenBox;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenIceSpike;

public class FrozenMountians extends LostLandBiome {
	
	public FrozenMountians(int biomeId) {
		super(biomeId);
		this.heightVariation = 1F;
		this.rootHeight = 10F;
		this.setBiomeName("FrozenMountians");
		this.setEnableSnow();
		this.topBlock = Blocks.snow;
		this.fillerBlock = Blocks.stone;
		this.temperature = 0F;
		this.spawnableCreatureList.clear();
		
	}
	
	WorldGenIceSpike iceSpike = new WorldGenIceSpike(); 
	
	@Override
	public void decorate(World world, Random rand,int x,int z){
		super.decorate(world, rand, x, z);
		
		this.doWordGen(iceSpike, world, rand, x, z);
		
	}
	
	@Override
	public ArrayList<GenBox> genBoxes(int chunckX, int chunckZ) {
		return super.genBoxes(chunckX, chunckZ);
	}
	
	
}
