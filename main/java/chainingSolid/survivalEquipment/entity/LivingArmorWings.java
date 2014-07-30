package chainingSolid.survivalEquipment.entity;

import chainingSolid.survivalEquipment.util.livingEquipment.effects.Flight;
import chainingSolid.survivalEquipment.util.livingEquipment.effects.FlightData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class LivingArmorWings extends Entity {
	
	public EntityPlayer player;
	
	private double horivontalVelocity = 0,
			verticalVelocity = 0,
			rightWing = 0,
			leftWing = 0;
	
	
	private double currentRotationPitch = 0;
	
	
	
	public LivingArmorWings(World world) {
		super(world);
		this.yOffset = 0F;
	}
	
	@Override
	protected void entityInit() {
		
		
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
		//super.readFromNBT(var1);
		
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
		//super.writeToNBT(var1);
		
	}
	
	@Override
	public void onUpdate(){
		super.onUpdate();
		if(worldObj.isRemote){
			this.handleClientStuff();
		}else{
			this.handleServerStuff();
		}
	}
	
	private void handleServerStuff(){
		
		
		FlightData data = null;
		if(player != null){
			data = Flight.lastFlightData.get(player.getEntityId());
		}
		if(data == null){
			return;
		}
		
		
		
		
//		this.setPosition(
//				this.posX + (float)(this.horivontalVelocity * Math.cos(Math.toRadians(currentRotationPitch + 90))),
//				this.posY + (float)this.verticalVelocity,
//				this.posZ + (float)(this.horivontalVelocity * Math.sin(Math.toRadians(currentRotationPitch + 90)))
//				);
		
		
		
		
	}
	
	private void trimVelocities(){
		if(this.horivontalVelocity > 1D){
			this.horivontalVelocity = 1D;
		}else if(this.horivontalVelocity < -.5D){
			this.horivontalVelocity = -.5D;
		}
		if(this.verticalVelocity > 1D){
			this.verticalVelocity = 1D;
		}else if(this.verticalVelocity < -1D){
			this.verticalVelocity = -1D;
		}
		if(this.rightWing > 1D){
			this.rightWing = 1D;
		}else if(this.rightWing < -1D){
			this.rightWing = -1D;
		}
		if(this.leftWing > 1D){
			this.leftWing = 1D;
		}else if(this.leftWing < -1D){
			this.leftWing = -1D;
		}
		
	}
	
	
	private void handleClientStuff(){
		
	}
	
	@Override
	public boolean interactFirst(EntityPlayer player){
		if(!worldObj.isRemote){
			this.setDead();
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean shouldRiderSit(){
		return false;
	}
	
}
