package chainingSolid.survivalEquipment.items.armor.livingArmor;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.entity.LivingArmorWings;
import chainingSolid.survivalEquipment.util.livingEquipment.LivingEquipmentEffect;

public class LivingArmorChestPlate extends LivingArmor {
	
	public LivingArmorChestPlate() {
		super(1);
		
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
		super.onArmorTick(world, player, itemStack);
		if(world.isRemote){
			return;
		}
		
		if(player.riddenByEntity instanceof LivingArmorWings){
			
		}else{
			//LivingArmorWings wings = new LivingArmorWings(world);
			//wings.posX = player.posX;
			//wings.posY = player.posY;
			//wings.posZ = player.posZ;
			//world.spawnEntityInWorld(wings);
			
			//wings.mountEntity(player);
			//System.out.println("made wings for ya");
		}
		
		
		
		
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	@Override
	public String getIdentifier() {
		return "living_armor_chest_plate";
	}
	
}
