package chainingSolid.survivalEquipment.items.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.entity.HeavyArrow;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;

public class LongBow extends ItemBow implements LoadableItem {
	
	public LongBow() {
		super();
		this.setFull3D();
		this.setMaxStackSize(1);
		this.setMaxDamage(this.getMaxDamage());
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack){
		return super.getMaxItemUseDuration(stack) * 2;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack){
		return EnumAction.bow;
	}
	
	@Override
	public String getIdentifier() {
		return "long_bow";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
}
