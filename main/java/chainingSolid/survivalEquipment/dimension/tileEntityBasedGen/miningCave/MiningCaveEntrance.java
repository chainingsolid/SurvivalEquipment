package chainingSolid.survivalEquipment.dimension.tileEntityBasedGen.miningCave;

import net.minecraft.block.Block;
import chainingSolid.survivalEquipment.dimension.tileEntityBasedGen.TEBasedGenPart;
import chainingSolid.survivalEquipment.dimension.tileEntityBasedGen.TEBasedGenPartLoactionAndSize;

public class MiningCaveEntrance extends TEBasedGenPart {
	
	public MiningCaveEntrance(TEBasedGenPartLoactionAndSize partLocation, TEBasedGenPart previousPart) {
		super(partLocation, previousPart);
		
	}
	
	public MiningCaveEntrance(TEBasedGenPartLoactionAndSize partLocAndSize) {
		super(partLocAndSize);
		
	}
	
	@Override
	public void genPart() {
		
		
		for(double radians = 0;radians <= 2*(Math.PI);radians =+ .01){
			double x = Math.sin(radians);
			double z = Math.cos(radians);
			for(int hieght = partLoc.centerY;hieght >= partLoc.centerY-partLoc.down;hieght--){
			
			}
		}
		
		
		
		
		
	}
	
	private boolean placeBlockIfBlockIsAir(Block blockToPlace, int x,int y,int z){
		if(world.isAirBlock(x, y, z)){
			world.setBlock(x, y, z, blockToPlace);
			return true;
		}else{
			return false;
		}
	}
	
}
