package chainingSolid.survivalEquipment.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterLostLand extends Teleporter {
	
	private WorldServer sever;
	
	public TeleporterLostLand(WorldServer worldServer) {
		super(worldServer);
		this.sever = worldServer;
	}
	
	@Override
	public void placeInPortal(Entity entitiy,double x, double y,double z,float idk){
		entitiy.moveEntity(0, 100, 0);
	}
	
}
