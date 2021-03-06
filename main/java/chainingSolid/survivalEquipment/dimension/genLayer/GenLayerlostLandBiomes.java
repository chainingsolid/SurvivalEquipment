package chainingSolid.survivalEquipment.dimension.genLayer;

import chainingSolid.survivalEquipment.dimension.biomes.BiomeList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerlostLandBiomes extends GenLayer {
	
	private BiomeGenBase[] allowedBiomes;
	
	public GenLayerlostLandBiomes(long seed) {
		super(seed);
		this.allowedBiomes = BiomeList.INSTANCE.biomeArray.clone();
		
	}
	
	@Override
	public int[] getInts(int x, int z, int width, int depth) {
		int[] dest = IntCache.getIntCache(width*depth);
		
		for (int dz=0; dz<depth; dz++){
			for (int dx=0; dx<width; dx++){
				this.initChunkSeed(dx+x, dz+z);
				dest[(dx+dz*width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
			}
		}
		return dest;
	}
	
}
