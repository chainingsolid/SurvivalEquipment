package chainingSolid.survivalEquipment.items.armor.livingArmor;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;

public class LivingArmorHelmet extends LivingArmor {
	
	public LivingArmorHelmet() {
		super(0);
		
	}
	
	
	
	
	public void onArmorTick(World world, EntityPlayer player, ItemStack thisStack){
		super.onArmorTick(world, player, thisStack);
		if(world.isRemote){
			return;
		}
		
		List l = world.getChunkProvider().getPossibleCreatures(EnumCreatureType.monster, (int)player.posX, (int)player.posY, (int)player.posZ);
		if(l != null){
			for(int i = 0; l.size() > i;i++){
				BiomeGenBase.SpawnListEntry spawnEntry = (SpawnListEntry) l.get(i);
			}
		}
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	@Override
	public String getIdentifier() {
		return "living_armor_helmet";
	}
	
	
}
