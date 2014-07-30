package chainingSolid.survivalEquipment.dimension.biomes;

import java.util.ArrayList;
import java.util.Random;

import chainingSolid.survivalEquipment.dimension.GenBox;
import chainingSolid.survivalEquipment.dimension.worldGen.RiverCaves;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class JungleMoutians extends LostLandBiome {
	
	public JungleMoutians(int par1) {
		super(par1);
		this.biomeName="JungleMoutians";
		this.fillerBlock = Blocks.stone;
		this.topBlock = Blocks.grass;
		this.heightVariation = .2F;
		this.rootHeight = 1.5F;
		this.theBiomeDecorator.treesPerChunk = 21;
		this.theBiomeDecorator.reedsPerChunk = 5;
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityOcelot.class, 10, 4, 4));
	}
	
	private WorldGenShrub shrubs;
	private WorldGenVines vines = new WorldGenVines();
	private RiverCaves rc = new RiverCaves();
	
	@Override
	public void decorate(World world, Random rand,int x,int z){
		super.decorate(world, rand, x, z);
		for(int time = 0;time < 50; time++){
			this.doWordGen(vines, world, rand, x, z);
		}
		shrubs = new WorldGenShrub(3, 0);
		for(int time = 0;time < 100; time++){
			this.doWorldGenTree(shrubs, world, rand, x, z);
		}
		
		if(world.rand.nextInt(10)==1){
			//this.doWordGen(rc, world, rand, x, z);
		}
	}
	
	@Override
	public WorldGenAbstractTree func_150567_a(Random rand){
		return new WorldGenMegaJungle(false, 10, 20, 3, 3);
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random){
		return new WorldGenMelon();
	}
	
	@Override
	public ArrayList<GenBox> genBoxes(int chunckX, int chunckZ) {
		return super.genBoxes(chunckX, chunckZ);
	}
	
}
