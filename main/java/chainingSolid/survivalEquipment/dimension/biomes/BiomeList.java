package chainingSolid.survivalEquipment.dimension.biomes;

import java.util.ArrayList;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeList {
	
	public static BiomeList INSTANCE = new BiomeList();
	
	public FertilePlains fertilePlains = new FertilePlains(150);
	public MountianOcean mountainOcean = new MountianOcean(151);
	public JungleMoutians jungleMoutians = new JungleMoutians(152);
	public VariableForest variableForest = new VariableForest(153);
	public FrozenMountians frozenMoutians = new FrozenMountians(154);
	public LostOcean lostOcean = new LostOcean(155);
	
	public LostLandBiome[] biomeArray = new LostLandBiome[]{
			fertilePlains,
			mountainOcean,
			jungleMoutians,
			variableForest,
			frozenMoutians,
			lostOcean
	};
	
	public ArrayList<LostLandBiome> biomeList = new ArrayList<LostLandBiome>();
	
	public BiomeList(){
		biomeList.add(fertilePlains);
		biomeList.add(jungleMoutians);
		biomeList.add(mountainOcean);
		biomeList.add(variableForest);
		biomeList.add(frozenMoutians);
		biomeList.add(lostOcean);
	}
}
