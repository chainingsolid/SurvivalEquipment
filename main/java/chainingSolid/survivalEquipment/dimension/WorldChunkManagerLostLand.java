package chainingSolid.survivalEquipment.dimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import chainingSolid.survivalEquipment.dimension.biomes.BiomeList;
import chainingSolid.survivalEquipment.dimension.biomes.FertilePlains;
import chainingSolid.survivalEquipment.dimension.genLayer.LostLandGenLayer;

import java.lang.reflect.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldChunkManagerLostLand extends WorldChunkManager{
	
	private GenLayer _genBiomes;
	private GenLayer _biomeIndexLayer;
	private BiomeCache cache;
	
	public WorldChunkManagerLostLand(World world){
		super(world);
		
		GenLayer[] genLayers = LostLandGenLayer.makeWorldGenlayers(world.getSeed(), world.getWorldInfo().getTerrainType());
		this._genBiomes = genLayers[0];
		this._biomeIndexLayer = genLayers[1];
		cache = new BiomeCache(this);
	}
	
	@Override
	public List getBiomesToSpawnIn(){
		return BiomeList.INSTANCE.biomeList;
	}
	
	@Override
	public BiomeGenBase getBiomeGenAt(int x, int z){
		return this.cache.getBiomeGenAt(x, z);
	}
	
	@Override
	public float[] getRainfall(float[] tempatures, int x, int z, int xWidth, int zWidth){
		IntCache.resetIntCache();
		
		int chunkSize = xWidth * zWidth;
		
		if (tempatures == null || tempatures.length < chunkSize){
			tempatures = new float[chunkSize];
		}
		
		int[] temps = _biomeIndexLayer.getInts(x, z, xWidth, zWidth);
		
		for (int loopCounter = 0; loopCounter < chunkSize; ++loopCounter){
			float temp = BiomeGenBase.getBiomeGenArray()[temps[loopCounter]].getIntRainfall() / 65536.0F;
			
			if (temp > 1.0F){
				temp = 1.0F;
			}
			
			tempatures[loopCounter] = temp;
		}
		
		return tempatures;
	}
	
	@Override
	public float getTemperatureAtHeight(float temp, int hieght){
		return temp;
	}
	
	@Override
	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] biomeGenBaseArray, int x, int z, int widthX, int widthZ){
		IntCache.resetIntCache();
		
		int ChuckSize = widthX*widthZ;
		
		biomeGenBaseArray = this.makeSureArrayIsBigEnought(biomeGenBaseArray, widthX, widthZ);
		
		int[] biomeIdsForGen = _genBiomes.getInts(x, z, widthX, widthZ);
		
		for(int columNumInChunk = 0; columNumInChunk < ChuckSize; ++columNumInChunk){
			biomeGenBaseArray[columNumInChunk] = BiomeGenBase.getBiomeGenArray()
					[biomeIdsForGen[columNumInChunk]];
		}
		
		return biomeGenBaseArray;
	}
	
	@Override
	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] biomeGenBAseArray, int x, int z, int widthX, int widthZ){
		return this.getBiomeGenAt(biomeGenBAseArray, x, z, widthX, widthZ, true);
	}
	
	@Override
	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] biomeGenBaseArray, int x, int z, int widthX, int widthZ, boolean mapFeatures){
		
		IntCache.resetIntCache();
		
		int chuckSize = widthX * widthZ;
		
		this.makeSureArrayIsBigEnought(biomeGenBaseArray, widthX, widthZ);
		
		if(this.useCachedBiomes(mapFeatures,x,z,widthX,widthZ)){
			BiomeGenBase[] cachedData = cache.getCachedBiomes(x, z);
			System.arraycopy(cachedData, 0, biomeGenBaseArray, 0, Math.min(biomeGenBaseArray.length, cachedData.length));
			return biomeGenBaseArray;
		}else{
			int[] biomeData = _biomeIndexLayer.getInts(x, z, widthX, widthZ);
			
			for(int columNumInChunk = 0; columNumInChunk < chuckSize; ++columNumInChunk){
				biomeGenBaseArray[columNumInChunk] = BiomeGenBase.getBiomeGenArray()[biomeData[columNumInChunk]];
			}
			
			return biomeGenBaseArray;
		}
		
		
		
	}
	
	private boolean useCachedBiomes(boolean mapFeatures, int x, int z, int widthX, int widthZ) {
		if(mapFeatures && widthX == 16 && widthZ == 16 && (x & 15) == 0 && (z & 15) == 0){
			return true;
		}else{
			return false;
		}
	}
	

	@Override
	public boolean areBiomesViable(int x, int z, int width, List list){
		IntCache.resetIntCache();
		
		int xCoord = x - width >> 2;
		int zCoord = z - width >> 2;
		int xForAdjust = x + width >> 2;
		int zForAdjust = z + width >> 2;
		
		int xWidth = xCoord - xForAdjust + 1;
		int zWidth = zCoord - zForAdjust + 1;
		
		int[] biomeIds = _genBiomes.getInts(xCoord, zCoord, xWidth, zWidth);
		
		for(int loopCounter = 0;loopCounter < xWidth*zWidth;++loopCounter){
			BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[biomeIds[loopCounter]];
			if(!list.contains(biome)){
				return false;
			}
		}
		
		return true;
	}
	
	public ChunkPosition findBiomePosition(int x, int z, int idk, List list, Random rand){
		IntCache.resetIntCache();
		int xCoord = x - idk >> 2;
		int zCoord = z - idk >> 2;
		int xAdjustment = x + idk >> 2;
		int zAdjustment = z + idk >> 2;
		int xWidth = xAdjustment - xCoord + 1;
		int zWidth = zAdjustment - zCoord + 1;
		int[] biomeIds = _genBiomes.getInts(xCoord, zCoord, xWidth, zWidth);
		ChunkPosition chunkPostion = null;
		int randSwitch = 0;
		
		for (int loopCounter = 0; loopCounter < xWidth * zWidth; ++loopCounter)
		{
			int xGuess = xCoord + loopCounter % xWidth << 2;
			int zGuess = zCoord + loopCounter / xWidth << 2;
			BiomeGenBase biomeId = BiomeGenBase.getBiomeGenArray()[biomeIds[loopCounter]];
			
			if (list.contains(biomeId) && (chunkPostion == null || rand.nextInt(randSwitch + 1) == 0))
			{
				chunkPostion = new ChunkPosition(xGuess, 0, zGuess);
				++randSwitch;
			}
		}
		
		return chunkPostion;
	}
	
	@Override
	public void cleanupCache(){
		cache.cleanupCache();
	}
	
	private BiomeGenBase[] makeSureArrayIsBigEnought(BiomeGenBase[] biomeGenBaseArray,int widthX, int widthZ){
		int ChunkSize = widthX*widthZ;
		if(biomeGenBaseArray == null || biomeGenBaseArray.length < ChunkSize){
			return new BiomeGenBase[ChunkSize];
		}
		return biomeGenBaseArray;
	}
	
}
