package chainingSolid.survivalEquipment.tileEnties.genTileEntities;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.dimension.biomes.MountianOcean;
import chainingSolid.survivalEquipment.dimension.tileEntityBasedGen.TEBasedGenPartLoactionAndSize;
import chainingSolid.survivalEquipment.dimension.tileEntityBasedGen.miningCave.MiningCaveEntrance;

public class MiningCaveTileEntity extends TileEntity {
	
	private boolean hasGenerated = false;
	private Random rand;
	
	public MiningCaveTileEntity(World world){
		this.worldObj = world;
		rand = new Random();
	}
	
	@Override
	public void updateEntity(){
		if(hasGenerated || !this.isInOceanMountains()){
			this.destroySelf();
		}else{
			this.createMiningCave();
			this.hasGenerated = true;
		}
	}
	
	private boolean isInOceanMountains(){
		if(worldObj.getBiomeGenForCoords(xCoord, zCoord) instanceof MountianOcean){
			return true;
		}else{
			return false;
		}
	}
	
	
	private void destroySelf(){
		worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
	}
	
	private void createMiningCave(){
		int topBlock = worldObj.getHeightValue(xCoord, zCoord);
		TEBasedGenPartLoactionAndSize partLocAndSize = new TEBasedGenPartLoactionAndSize();
		rand.setSeed(topBlock+worldObj.getSeed());
		int radius = 10 + rand.nextInt(5);
		partLocAndSize.world = worldObj;
		partLocAndSize.centerX = this.xCoord;
		partLocAndSize.centerZ = this.zCoord;
		partLocAndSize.centerY = topBlock - rand.nextInt(5);
		partLocAndSize.up = 10 + rand.nextInt(10);
		partLocAndSize.down = 5;
		partLocAndSize.x_Back = radius;
		partLocAndSize.x_Forward = radius;
		partLocAndSize.z_Back = radius;
		partLocAndSize.z_Forward = radius;
		MiningCaveEntrance caveEntrence = new MiningCaveEntrance(partLocAndSize);
		caveEntrence.genPart();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
