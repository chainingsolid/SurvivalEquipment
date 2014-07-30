package chainingSolid.survivalEquipment.entity;

import chainingSolid.survivalEquipment.util.entity.QuadraticFlightPath;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class HeavyArrow extends EntityArrow {
	
	public HeavyArrow(World world) {
		super(world);
		
		
	}
	
	public HeavyArrow(World world, EntityLivingBase livingingEntity, float power){
		super(world, livingingEntity, power);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	private double distanceFlown;
	
	private QuadraticFlightPath qPath;
	
	private double velocity,angle,angleRad,rotation,rotationRad;
	
	private int age = 10;
	
	private double distancePerTick;
	
	private double startY;
	
	public HeavyArrow(World world) {
		super(world);
		this.boundingBox.setBounds(0, 0, 0, 1, 1, 1);
		this.onGround = false;
	}
	
	public HeavyArrow(World world,double velocity,EntityPlayer player) {
		this(world);
		this.velocity = velocity;
		this.angle = player.rotationPitch*-1;
		this.angleRad = Math.toRadians(angle);
		System.out.println("angle"+angle+"  angle Rad:"+angleRad);
		this.rotation = player.rotationYaw+90;
		this.rotationRad = Math.toRadians(rotation);
		this.distancePerTick = velocity/150;
		System.out.println("distance per tick"+distancePerTick);
		this.setPosition(player.posX, player.posY+1, player.posZ);
		this.startY = player.posY+1;
	}
	
	@Override
	protected void entityInit(){
		
	}
	
	@Override
	public void onEntityUpdate(){
		super.onEntityUpdate();
		if(qPath == null){
			qPath = new QuadraticFlightPath(velocity, angleRad);
		}
		
		double h = qPath.getHieghtAtDistance(distanceFlown);
		
		System.out.println(""+h);
		this.setPosition(
				(Math.cos(rotationRad)*distancePerTick) + this.posX,
				(h)+ this.startY,
				(Math.sin(rotationRad)*distancePerTick) + this.posZ
				);
		
		distanceFlown += distancePerTick;
		
		//System.out.println("Dist"+distancePerTick+"\tangle:"+qPath.angle);
		age++;
		if(age > 20*30){
			System.out.println("good bye world");
			this.setDead();
		}else{
			
		}
		
		worldObj.setBlock((int)posX, (int)posY, (int)posZ,Blocks.glass);
		
		
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound var1) {
		
		
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound var1) {
		
		
	}
	*/
	
	
	
}
