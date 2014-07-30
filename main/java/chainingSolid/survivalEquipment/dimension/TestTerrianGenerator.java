package chainingSolid.survivalEquipment.dimension;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class TestTerrianGenerator extends ChunkProviderLostLand {
	
	public TestTerrianGenerator(World world) {
		super(world);
		
	}
	
	@Override
	public Chunk provideChunk(int x, int z){
		Chunk chunk = new Chunk(world, new Block[16*16*256], x, z);
		
		
		
		
		
		
		return chunk;
	}
	
	
	
	
}
