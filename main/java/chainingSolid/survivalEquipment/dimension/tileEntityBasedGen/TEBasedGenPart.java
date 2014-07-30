package chainingSolid.survivalEquipment.dimension.tileEntityBasedGen;

import java.util.Random;

import net.minecraft.world.World;

public abstract class TEBasedGenPart{
	
	protected Random rand;
	protected TEBasedGenPartLoactionAndSize partLoc;
	protected TEBasedGenPart previousPart;
	protected World world;
	
	public TEBasedGenPart(TEBasedGenPartLoactionAndSize partLocation,TEBasedGenPart previousPart){
		this.partLoc = partLocation;
		world = partLoc.world;
		rand = new Random();
		rand.setSeed(partLoc.world.getSeed()+partLoc.centerX-partLoc.centerZ);
		this.previousPart = previousPart;
	}
	
	public TEBasedGenPart(TEBasedGenPartLoactionAndSize partLocation){
		this.partLoc = partLocation;
		world = partLoc.world;
		rand = new Random();
		rand.setSeed(partLoc.world.getSeed()+partLoc.centerX-partLoc.centerZ);
	}
	
	public abstract void genPart();
	
}
