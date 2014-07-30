package chainingSolid.survivalEquipment.items;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;

public class SpawnSight extends Item implements LoadableItem {
	
	public SpawnSight() {
		
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float floatX, float floatY, float floatZ){
		if(world.isRemote){
			return false;
		}
		for(EnumCreatureType type : EnumCreatureType.values()){
			List l = world.getChunkProvider().getPossibleCreatures(type, x, y, z);
			ChatComponentText chat;
			if(l != null){
				if(!l.isEmpty()){
					chat = new ChatComponentText(l.toString());
					MinecraftServer.getServer().getConfigurationManager().sendChatMsg(chat);
				}
			}
			
		}
		return true;
	}
	
	@Override
	public String getIdentifier() {
		return "spawn_sight";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
}
