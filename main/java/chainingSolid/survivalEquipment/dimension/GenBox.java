package chainingSolid.survivalEquipment.dimension;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class GenBox {
	
	public GenBox(Block b){
		this.fillerBlock = b;
	}
	
	public GenBox(Block b,int xMin, int yMin, int zMin,int xMax,int yMax,int zMax){
		this.fillerBlock = b;
		this.minX = xMin;
		this.minY = yMin;
		this.minZ = zMin;
		this.maxX = xMax;
		this.maxY = yMax;
		this.maxZ = zMax;
	}
	
	public int minX, minY, minZ, maxX, maxY, maxZ;
	
	public Block fillerBlock;
	public int fillerBlockMetaData = 0;
	
	public void putBlocksInArray(Block[] blocks,int chunkX,int chunkZ){
		this.clipCordsToChunk(chunkX, chunkZ);
		
		for(int x = minX; x <= maxX;x++){
			for(int y = minY; x <= maxY;y++){
				for(int z = minZ; z <= maxZ;z++){
					blocks[CustomGenerator.getIndexInBlockArrayOfcords(x % 16, y, z % 16)] = this.fillerBlock;
				}
			}
		}
		
		
		
		
		/*for(int x = minX;x <= maxX;x++){
			for(int z = minZ;z <= maxZ;z++){
				for(int y = minY;y <= maxY;y++){
					ExtendedBlockStorage[] storage = chunk.getBlockStorageArray();
					ExtendedBlockStorage str = storage[y >> 4];
					if(str == null){
						chunk.func_150807_a(Math.abs(x % 16),y, Math.abs(z % 16), Blocks.stone, fillerBlockMetaData);
						storage = chunk.getBlockStorageArray();
						str = storage[y >> 4];
					}
					str.func_150818_a(Math.abs(x%16), y % 16, Math.abs(z%16), fillerBlock);
					str.setExtBlockMetadata(Math.abs(x%16), y % 16, Math.abs(z%16), fillerBlockMetaData);
				}
			}
		}*/
	}
	
	private void clipCordsToChunk(int x,int z){
		int minClipX = x*16;
		int minClipZ = z*16;
		int maxClipX = minClipX + 16;
		int maxClipZ = minClipZ + 16;
		
		minX = Math.min(minX, minClipX);
		minZ = Math.min(minZ, minClipZ);
		
		maxX = Math.min(maxX, maxClipX);
		maxZ = Math.min(maxZ, maxClipZ);
		
		minY = Math.max(minY, 0);
		maxY = Math.min(maxY, 256);
		
		System.out.println(minX+" "+minZ);
		
		
	}
	
}
