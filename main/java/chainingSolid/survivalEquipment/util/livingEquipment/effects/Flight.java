package chainingSolid.survivalEquipment.util.livingEquipment.effects;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import chainingSolid.survivalEquipment.network.EventData.LivingArmorFlightClientInput;
import chainingSolid.survivalEquipment.network.packet.LivingArmorFlightClientInputUpdatePacket;
import chainingSolid.survivalEquipment.util.livingEquipment.LivingEquipmentEffect;

public class Flight implements LivingEquipmentEffect {
	
	//TODO unify velocity vars make them both pull form one total velocity var and get client side stuff woriking and maybe potion buonuses? left and right a and d keys edit total max/min velocity
	
	public static Flight instance = new Flight();
	
	public static HashMap<Integer,FlightData> lastFlightData = new HashMap<Integer, FlightData>();
	
	public Flight() {
		
	}
	
	@Override
	public String getEffectName() {
		return "LivingArmorFlight";
	}
	
	@Override
	public void onTick(EntityLivingBase entity, ItemStack stack){}
	
	public static void clientInputUpdate(LivingArmorFlightClientInput packet){
		
		if(!lastFlightData.containsKey(packet.playerId)){
			FlightData newFlightData = new FlightData();
			newFlightData.isJumpKeyDown = packet.isJumpKeyDown;
			newFlightData.isCrouchKeyDown = packet.isCrouchKeyDown;
			newFlightData.isForwardKeyDown = packet.isForwardKeyDown;
			newFlightData.isBackwardKeyDown = packet.isBackwardKeyDown;
			newFlightData.isRightKeyDown = packet.isRightKeyDown;
			newFlightData.isLeftKeyDown = packet.isLeftKeyDown;
			
			lastFlightData.put(packet.playerId, newFlightData);
		}else{
			FlightData data = lastFlightData.get(packet.playerId);
			data.isJumpKeyDown = packet.isJumpKeyDown;
			data.isCrouchKeyDown = packet.isCrouchKeyDown;
			data.isForwardKeyDown = packet.isForwardKeyDown;
			data.isBackwardKeyDown = packet.isBackwardKeyDown;
			data.isRightKeyDown = packet.isRightKeyDown;
			data.isLeftKeyDown = packet.isLeftKeyDown;
			
		}
		
	}
	
	public static void flightTick(EntityPlayer player){
		FlightData data = lastFlightData.get(player.getEntityId());
		if(data == null){
			//System.out.println("Dodging null pointers so you can fly");
			return;
		}
		
		double horivontalVelocity, verticalVelocity;
		
		
		
		//player.setVelocity(0, 0, 0);
		//player.velocityChanged = false;
		
		//this.posX + (float)(this.horivontalVelocity * Math.cos(Math.toRadians(currentRotationPitch + 90))),
//		this.posY + (float)this.verticalVelocity,
//		this.posZ + (float)(this.horivontalVelocity * Math.sin(Math.toRadians(currentRotationPitch + 90)))
		
	}
	
	
	
	
	
}
