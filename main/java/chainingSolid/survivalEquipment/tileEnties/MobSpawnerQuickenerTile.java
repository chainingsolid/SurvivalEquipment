package chainingSolid.survivalEquipment.tileEnties;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;

public class MobSpawnerQuickenerTile extends TileEntity {
	
	public MobSpawnerQuickenerTile() {
		
	}
	
	@Override
	public void updateEntity(){
		if(worldObj.isRemote){
			return;
			
			
		}
		for(int y = -1; y > -4;y--){
			this.speedUpSpawnerAt(xCoord, yCoord+y, zCoord);
		}
		
		
	}
	
	public void speedUpSpawnerAt(int x, int y, int z){
		TileEntity te = worldObj.getTileEntity(x, y, z);
		if(te != null){
			if(te instanceof TileEntityMobSpawner){
				TileEntityMobSpawner spawner = (TileEntityMobSpawner)te;
				spawner.func_145881_a().updateSpawner();
			}
		}
	}
	
}
