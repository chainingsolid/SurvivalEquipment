package chainingSolid.survivalEquipment.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.items.armor.livingArmor.LivingArmorChestPlate;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import chainingSolid.survivalEquipment.util.livingEquipment.effects.Flight;

public class LivingArmorTendonPuller extends Item implements LoadableItem {
	
	public LivingArmorTendonPuller() {
		
	}
	
	@Override
	public String getIdentifier() {
		return "living_armor_tendon_puller";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
		
		
		return false;
	}
	
	
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count){
		
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int par4){
		
	}
	
}
