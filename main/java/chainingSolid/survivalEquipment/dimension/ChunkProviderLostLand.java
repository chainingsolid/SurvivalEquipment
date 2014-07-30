package chainingSolid.survivalEquipment.dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenVillage;


public class ChunkProviderLostLand extends ChunkProviderGenerate{
	
	protected World world;
	private Random rand;
	
	private MapGenVillage villageGen;
	private MapGenRavine ravines;
	private MapGenMineshaft mineshaftGen;
	private MapGenCaves caves;
	private MapGenScatteredFeature scatteredFeatureGenerator;
	
	
	
	private NoiseGeneratorOctaves noise;
	
	public ChunkProviderLostLand(World world){
		super(world, world.getSeed(), true);
		this.world = world;
		rand = new Random(world.getSeed());
		this.noise = new NoiseGeneratorOctaves(rand, 0);
	}
	
	
	
	@SuppressWarnings("unused")
	@Override
	public Chunk provideChunk(int x, int z) {
			
		if(false){
			rand = new Random();
			rand.setSeed(x * world.getSeed() - z * world.getSeed());
			Block[] blocksInChunk = new Block[16*16*256];
			BiomeGenBase[] biomesForGen = new BiomeGenBase[10];
			biomesForGen = world.getWorldChunkManager().loadBlockGeneratorData(biomesForGen, x*16, z*16, 16, 16);
			byte[] byteArray = new byte[16*16*256];
			this.func_147424_a(x, z, blocksInChunk);
			
			for(BiomeGenBase b:biomesForGen){
				for(int xInChunk = 0;xInChunk < 16; xInChunk++){
					for(int zInChunk = 0;zInChunk < 16; zInChunk++){
						b.genBiomeTerrain(world, rand, blocksInChunk, byteArray, x*16 + xInChunk, z*16 + zInChunk, 0.1D);
					}
				}
			}
			
			villageGen = new MapGenVillage();
			villageGen.func_151539_a(this, world, x, z, blocksInChunk);
			
			ravines = new MapGenRavine();
			ravines.func_151539_a(this, world, x, z, blocksInChunk);
			ravines.func_151539_a(this, world, x, z, blocksInChunk);
			
			mineshaftGen = new MapGenMineshaft();
			mineshaftGen.func_151539_a(this, world, x, z, blocksInChunk);
			
			scatteredFeatureGenerator = new MapGenScatteredFeature();
			scatteredFeatureGenerator.func_151539_a(this, world, x, z, blocksInChunk);
			
			ArrayList<BiomeGenBase> biomesToGenList = new ArrayList<BiomeGenBase>();
			
			Chunk chunk = new Chunk(world, blocksInChunk, byteArray,x, z);
			
			chunk.generateSkylightMap();
			return chunk;
		}
		
		return CustomGenerator.genChunck(world,x, z);
		
	}
	
	private boolean doesArrayHaveBiome(BiomeGenBase[] biomeArray,BiomeGenBase biomeTofind){
		for(BiomeGenBase b:biomeArray){
			if(b.isEqualTo(biomeTofind)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Chunk loadChunk(int x, int z) {
		return this.provideChunk(x, z);
	}
	
	@Override
	public void populate(IChunkProvider provider, int x, int z) {
		super.populate(provider, x, z);
	}
	
	
	
	@Override
	public String makeString() {
		return "Lost_Land";
	}
	
	@Override
	public List getPossibleCreatures(EnumCreatureType creatureTypes, int x, int y, int z) {
		BiomeGenBase biome = this.world.getBiomeGenForCoords(x, z);
		return biome.getSpawnableList(creatureTypes);
	}
	
	@Override
	public ChunkPosition func_147416_a(World var1, String var2, int var3, int var4, int var5) {
		// TODO write this later
		return null;
	}
	
	
	
}
