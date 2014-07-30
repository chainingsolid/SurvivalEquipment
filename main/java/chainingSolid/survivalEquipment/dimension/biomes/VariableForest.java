package chainingSolid.survivalEquipment.dimension.biomes;

import java.util.ArrayList;
import java.util.Random;

import chainingSolid.survivalEquipment.dimension.GenBox;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class VariableForest extends LostLandBiome {
	
	WorldGenForest birch = new WorldGenForest(false,false);
	WorldGenTrees tree = new WorldGenTrees(false);
	WorldGenCanopyTree darkOakTree = new WorldGenCanopyTree(false);
	WorldGenTaiga2 spruce = new WorldGenTaiga2(false);
	WorldGenSavannaTree acaicia = new WorldGenSavannaTree(false);
	
	public VariableForest(int id) {
		super(id);
		this.rootHeight = 0.1F;
		this.heightVariation = 0.2F;
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.stone;
		this.theBiomeDecorator.treesPerChunk = 10;
		this.theBiomeDecorator.flowersPerChunk = 100;
		this.biomeName = "Variable_Forest";
		this.addDefaultFlowers();
		this.theBiomeDecorator.flowersPerChunk = 15;
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 12, 10, 20));
		this.flowers.clear();
		
		for(int x = 0; x < BlockFlower.field_149859_a.length; x++){
			this.addFlower(Blocks.red_flower, x, 10);
		}
		
		//TODO add all the flowers
		
	}
	
	
	
	public WorldGenAbstractTree func_150567_a(Random rand){
		switch(rand.nextInt(6)){
		case(0):
			return tree;
		case(1):
			return darkOakTree;
		case(2):
			return birch;
		case(3):
			return spruce;
		case(4):
			return new WorldGenTrees(false, 4 + rand.nextInt(7), 3, 3, true);
		case(5):
			return this.acaicia;
		}
		
		
		return null;
	}
	//1 ,4 ,5
	private WorldGenDoublePlant tallFlowers = new WorldGenDoublePlant();
	
	@Override
	public void decorate(World world, Random rand,int x,int z){
		super.decorate(world, rand, x, z);
		for(int i = 0;i < 5;i++){
			int flowerType = world.rand.nextInt(3);
			if(flowerType == 0){
				tallFlowers.func_150548_a(1);
			}else if(flowerType == 1){
				tallFlowers.func_150548_a(4);
			}else if(flowerType == 2){
				tallFlowers.func_150548_a(5);
			}
			this.doWordGen(tallFlowers, world, rand, x, z);
		}
		
		for(int i = 0;i < 10;i++){
			this.plantFlower(world, rand, x, z);
		}
		
	}
	
	public String func_150572_a(Random rand, int x, int y, int z){
		int type = rand.nextInt(9);
		return BlockFlower.field_149859_a[type];
	}
	
	
	
	@Override
	public ArrayList<GenBox> genBoxes(int chunckX, int chunckZ) {
		return super.genBoxes(chunckX, chunckZ);
	}
	
}
