package chainingSolid.survivalEquipment.util.harvestingExplosion;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class HarvestingExplosion {
	
	
	private World world;
	private int xCoord , yCoord , zCoord ,
				blastPower,
				radius
				;
	
	public HarvestingExplosion(World world,int radius, int blastPower,int x, int y,int z){
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
		this.world = world;
		this.blastPower = blastPower;
		this.radius = radius;
		
	}
	
	
	public void explode(){
		for(int ring = 1;ring < radius;ring++){
			System.out.println("Ring: "+ring);
			for(double x = 0;x < 2*(Math.PI);x = x + 1){
				
				for(double y = 0;y < 2*(Math.PI);y = y + 1){
					
					for(double z = 0;z < 2*(Math.PI);z = z + 1){
						
						int blockX = (int)Math.sin(x)*ring+this.xCoord;
						int blockY = (int)Math.sin(y)*ring+this.yCoord;
						int blockZ = (int)Math.sin(z)*ring+this.zCoord;
						
						Block block = world.getBlock(blockX, blockY, blockZ);
						
						if(block.getBlockHardness(world, blockX, blockY, blockZ) <= blastPower){
							world.setBlockToAir(blockX, blockY, blockZ);
							System.out.println("Destroyed XD:"+blockX+" YD: "+blockY+" ZD: "+blockZ);
						}
					}
				}
			}
		}
	}
	
	
}
