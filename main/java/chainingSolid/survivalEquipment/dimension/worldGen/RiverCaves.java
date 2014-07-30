package chainingSolid.survivalEquipment.dimension.worldGen;

import java.util.Random;

import chainingSolid.survivalEquipment.util.Util;
import net.minecraft.init.Blocks;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RiverCaves extends WorldGenerator {
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		
		
		this.genSphere(world, x, z);
		
		world.setBlock(x, 150, z, Blocks.beacon);
		
		return true;
	}
	
	
	
	private void genSphere(World world, int centerX, int centerZ){
		
		int radius = world.rand.nextInt(5) + 5;
		int extendedRadius = (int) (radius * 10);
		int centerY = 64;
		
		for(int x = centerX - extendedRadius; x < centerX + extendedRadius; x++){
			for(int y = centerY - extendedRadius; y < centerY + extendedRadius; y++){
				for(int z = centerZ - extendedRadius; z < centerZ + extendedRadius; z++){
					if(Util.getDistance(centerX, centerZ, centerY, x, y, z) < radius){
						if(y < 64){
							world.setBlock(x, y, z, Blocks.water);
						}else{
							world.setBlock(x, y, z, Blocks.air);
						}
					}
				}
			}
		}
		
		
		
	}
	
	
}
