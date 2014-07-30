package chainingSolid.survivalEquipment.dimension;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.dimension.biomes.BiomeList;
import chainingSolid.survivalEquipment.dimension.biomes.FertilePlains;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class WorldProviderLostLand extends WorldProvider {
	
	
	
	public WorldProviderLostLand(){
		super();
		this.dimensionId = SurvivalEquipment.dimid;
		System.out.println("made lost world provider");
	}
	
	@Override
	protected void registerWorldChunkManager(){
		System.out.println("made world chuynck manager");
		this.worldChunkMgr = new WorldChunkManagerLostLand(worldObj);
	}
	
	@Override
	public IChunkProvider createChunkGenerator(){
		System.out.println("Made chunck generator (aka chunck provider)");
		return new ChunkProviderLostLand(worldObj);
	}
	
	@Override
	public boolean isSurfaceWorld(){
		return false;
	}
	
	@Override
	public String getDimensionName() {
		return "Lost Land";
	}
	
	@Override
	public double getMovementFactor(){
		return 1.0F;
	}
	
	@Override
	public float getCloudHeight(){
		return 256F;
	}
	
	@Override
	public boolean canCoordinateBeSpawn(int x, int z){
		return false;
	}
	
	@Override
	public boolean canRespawnHere(){
		return false;
	}
	
}
