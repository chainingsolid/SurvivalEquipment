package chainingSolid.survivalEquipment.dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import chainingSolid.survivalEquipment.dimension.biomes.BiomeList;
import chainingSolid.survivalEquipment.dimension.biomes.LostLandBiome;
import chainingSolid.survivalEquipment.util.Util;

public class CustomGenerator {
	
	public static Chunk genChunck(World world, int x, int z){
		LostLandBiome biome = getBiomeForChunck(x,z);
		
		byte biomeId = (byte) biome.biomeID;
		byte[] biomeIds = new byte[16*16];
		
		for(int i = 0; i < biomeIds.length;i++){
			biomeIds[i] = biomeId;
		}
		
		int blocksInChunck = 16*16*256;
		
		Block[] blocks = new Block[blocksInChunck];
		System.out.println(blocksInChunck);
		
		for(int i = 0; i< blocks.length;i++){
			blocks[i] = Blocks.air;
		}
		
		
		List<GenBox> boxes = biome.genBoxes(x, z);
		
		if(boxes != null){
			for(GenBox box : boxes){
				box.putBlocksInArray(blocks, x, z);
				System.out.println("put box "+box +" in chunk");
			}
		}else{
			System.out.println("you gave me null boxes :( ");
		}
		
		Chunk c = new Chunk(world,blocks,biomeIds, x, z);
		
		c.generateSkylightMap();
		c.setChunkModified();
		c.lastSaveTime = 0;
		
		//System.out.println("gened "+ x + " : "+z + " with biome " + biome.biomeID);
		return c;
	}
	
	public static LostLandBiome getBiomeForChunck(int x, int z){
		int nearestIslandCenterX = (x & 0x11111110) + x;
		int nearestIslandCenterZ = (z & 0x11111110) + z;
		
		LostLandBiome biome = BiomeList.INSTANCE.lostOcean;
		boolean genLand = Util.getDistance(x, 0, z, nearestIslandCenterX, 0, nearestIslandCenterZ) < 5;
		System.out.println(genLand + " " + Util.getDistance(x, 0, z, nearestIslandCenterX, 0, nearestIslandCenterZ));
		if(genLand){
			Random rand = new Random();
			rand.setSeed(nearestIslandCenterZ*nearestIslandCenterX);
			int biomeInList = rand.nextInt(BiomeList.INSTANCE.biomeArray.length);
			
			biome = BiomeList.INSTANCE.biomeArray[biomeInList];
		}
		
		return biome;
	}
	
	
	
	
	
	public static int getIndexInBlockArrayOfcords(int x,int y,int z){
		return x * 256 * 16 | z * 256 | y;
	}
	
	
	
	
	
	
	
	
	
}
