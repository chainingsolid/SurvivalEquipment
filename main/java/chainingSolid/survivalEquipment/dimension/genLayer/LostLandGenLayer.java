package chainingSolid.survivalEquipment.dimension.genLayer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiver;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public class LostLandGenLayer extends GenLayer {
	
	public LostLandGenLayer(long seed) {
		super(seed);
		
	}
	
	public static GenLayer[] makeWorldGenlayers(long seed,WorldType worldType){
		
		GenLayer biomes = new GenLayerlostLandBiomes(seed);
		
		biomes = new GenLayerZoom(1000L, biomes);
		biomes = new GenLayerZoom(1001L, biomes);
		biomes = new GenLayerZoom(1002L, biomes);
		biomes = new GenLayerZoom(1003L, biomes);
		biomes = new GenLayerZoom(1004L, biomes);
		
		
		GenLayer genLayerZoom = new GenLayerVoronoiZoom(seed, biomes);
		
		biomes.initWorldGenSeed(seed);
		genLayerZoom.initWorldGenSeed(seed);
		
		return new GenLayer[]{biomes,genLayerZoom};
	}
	
	
	
	@Override
	public int[] getInts(int var1, int var2, int var3, int var4) {
		
		return null;
	}
	
}
