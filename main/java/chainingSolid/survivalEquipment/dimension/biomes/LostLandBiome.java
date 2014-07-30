package chainingSolid.survivalEquipment.dimension.biomes;

import java.util.ArrayList;
import java.util.Random;

import chainingSolid.survivalEquipment.dimension.GenBox;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class LostLandBiome extends BiomeGenBase {
	
	public LostLandBiome(int id) {
		super(id);
		this.biomeName = "lost land biome";
	}
	
	public ArrayList<GenBox> genBoxes(int chunckX,int chunckZ){
		int x = chunckX * 16;
		int z = chunckZ * 16;
		ArrayList<GenBox> boxes = new ArrayList<GenBox>();
		
		GenBox bedrockBox = new GenBox(Blocks.bedrock);
		
		bedrockBox.minX = x;
		bedrockBox.minZ = z;
		bedrockBox.maxX = x + 16;
		bedrockBox.maxZ = z + 16;
		
		bedrockBox.minY = 0;
		bedrockBox.maxY = 1;
		
		boxes.add(bedrockBox);
		
		GenBox grassTop = new GenBox(Blocks.grass, chunckX*16, 2, chunckX*16, chunckX*16+16, 64, chunckZ*16+16);
		boxes.add(grassTop);
		
		return boxes;
	}
	
	WorldGenDungeons dungeonGen = new WorldGenDungeons();
	
	public void decorate(World world, Random rand,int x,int z){
		super.decorate(world, rand, x, z);
		int tries = 0;
		while(tries < 10){
			tries++;
			this.doDungeonGen(world, rand, x, z);
		}
	}
	
	
	
	public boolean doWordGen(WorldGenerator gen, World world,Random rand,int x,int z){
		int y = world.getHeightValue(x, z);
		return gen.generate(world, rand, x+rand.nextInt(16)+8, y, z+rand.nextInt(16)+8);
	}
	
	public void plantFlower(World world,Random rand,int x,int z){
		int y = world.getHeightValue(x, z);
		this.plantFlower(world, rand, x+rand.nextInt(16)+8, y, z+rand.nextInt(16)+8);
	}
	
	public boolean doDungeonGen(World world,Random rand,int x,int z){
		int y = world.getHeightValue(x, z);
		return dungeonGen.generate(world, rand, x+rand.nextInt(16)+8, rand.nextInt(80), z+rand.nextInt(16)+8);
	}
	
	
	public boolean doWorldGenTree(WorldGenAbstractTree gen, World world,Random rand,int x,int z){
		int y = world.getHeightValue(x, z);
		int x2 = x+rand.nextInt(16)+8;
		int z2 = z+rand.nextInt(16)+8;
		if(gen.generate(world, rand, x2, y, z2)){
			gen.func_150524_b(world, rand, x2, y, z2);
		}
		return true;
	}
	
}
