package chainingSolid.survivalEquipment.network;

import net.minecraft.client.Minecraft;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import chainingSolid.survivalEquipment.network.packet.LivingArmorFlightClientInputUpdatePacket;
import chainingSolid.survivalEquipment.network.packet.SUPacket;
import chainingSolid.survivalEquipment.network.packet.TestPacket;
import chainingSolid.survivalEquipment.util.livingEquipment.effects.Flight;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;

public class PacketHandler extends FMLIndexedMessageToMessageCodec<SUPacket>{
	
	
	
	public PacketHandler() {
		this.addDiscriminator(SUPacket.TEST_PACKET, TestPacket.class);
		this.addDiscriminator(SUPacket.FLIGHT_UPDTAE_PACKET, LivingArmorFlightClientInputUpdatePacket.class);
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, SUPacket msg, ByteBuf target) throws Exception {
		msg.encode(target);
	}
	
	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, SUPacket msg) {
		msg.deEncode(source);
		
		if(msg instanceof LivingArmorFlightClientInputUpdatePacket){
			LivingArmorFlightClientInputUpdatePacket packet = (LivingArmorFlightClientInputUpdatePacket) msg;
			
		}
		
	}
	
}
