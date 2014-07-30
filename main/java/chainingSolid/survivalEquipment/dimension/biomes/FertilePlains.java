package chainingSolid.survivalEquipment.dimension.biomes;

import java.util.ArrayList;
import java.util.Random;

import chainingSolid.survivalEquipment.dimension.GenBox;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenTallGrass;

public class FertilePlains extends LostLandBiome {
	
	public FertilePlains(int biomeId) {
		super(biomeId);
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.stone;
		this.biomeName="Fertile Plains";
		this.heightVariation = 0.05F;
		this.rootHeight = 0.1F;
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));
		this.theBiomeDecorator.grassPerChunk = 16^2;
		this.setColor(10);
		
	}
	
	private WorldGenDoublePlant tallFlowers = new WorldGenDoublePlant();
	
	@Override
	public void decorate(World world, Random rand,int x,int z){
		super.decorate(world, rand, x, z);
		this.doWordGen(tallFlowers, world, rand, x, z);
	}
	
	
	@Override
	public float getSpawningChance(){
		return .5F;
	}
	
	@Override
	public ArrayList<GenBox> genBoxes(int chunckX, int chunckZ) {
		return super.genBoxes(chunckX, chunckZ);
	}
}
