package chainingSolid.survivalEquipment.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class SnowGun extends Item implements LoadableItem{
	
	public SnowGun() {
		super();
		this.canRepair=false;
		this.setMaxStackSize(1);
		
	}
	
	
	@Override
	public Multimap getItemAttributeModifiers(){
		return HashMultimap.create();
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10){
		return false;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(world.isRemote){
			return stack;
		}
		
		EntitySnowball snowBall = new EntitySnowball(world,player);
		
		snowBall.motionX *= 2;
		snowBall.motionY *= 2;
		snowBall.motionZ *= 2;
		
		world.spawnEntityInWorld(snowBall);
		return stack;
	}
	
	
	
	@Override
	public void registerIcons(IIconRegister r){
		itemIcon=r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+"snow_gun");
	}
	
	@Override
	public String getIdentifier() {
		return "snow_gun";
	}
	
	
	@Override
	public Item getInstance() {
		return this;
	}
	
}
