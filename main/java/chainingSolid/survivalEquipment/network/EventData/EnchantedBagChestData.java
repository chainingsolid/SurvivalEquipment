package chainingSolid.survivalEquipment.network.EventData;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.network.EventChannnelHandler;
import chainingSolid.survivalEquipment.tileEnties.EnchantedBagChestTile;

public class EnchantedBagChestData extends EventData {
	
	public NBTTagCompound chestTag;
	public int dimId , xCoord, yCoord, zCoord;
	
	@Override
	public void writeFromByteBuffer(ByteBuf buffer) {
		PacketBuffer buf = new PacketBuffer(buffer);
		try {
			this.chestTag = buf.readNBTTagCompoundFromBuffer();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		this.dimId = buf.readInt();
		this.xCoord = buf.readInt();
		this.yCoord = buf.readInt();
		this.zCoord = buf.readInt();
		
	}
	
	@Override
	public ByteBuf writeDataToArray() {
		PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());
		buffer.writeByte(EventChannnelHandler.EVENT_DATA_ENCHANTED_BAG_CHEST);
		try {
			buffer.writeNBTTagCompoundToBuffer(chestTag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		buffer.writeInt(dimId);
		buffer.writeInt(xCoord);
		buffer.writeInt(yCoord);
		buffer.writeInt(zCoord);
		return buffer;
	}
	
	@Override
	public void act() {
		System.out.println("heres your chest");
		World world = Minecraft.getMinecraft().theWorld;
		if(world != null){
			if(world.provider.dimensionId == dimId){
				TileEntity te = world.getTileEntity(xCoord, yCoord, zCoord);
				if(te instanceof EnchantedBagChestTile){
					EnchantedBagChestTile chestTile = (EnchantedBagChestTile)te;
					chestTile.readFromNBT(chestTag);
				}
			}
		}
	}
	
}
