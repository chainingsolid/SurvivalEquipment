package chainingSolid.survivalEquipment.items.tools;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.achievement.SurvivalEquipmentAchievements;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class HeavyHammer extends ItemSword implements LoadableItem{
	
	public HeavyHammer() {
		super(Item.ToolMaterial.WOOD);
		this.setFull3D();
		this.setMaxStackSize(1);
		this.setMaxDamage(Item.ToolMaterial.EMERALD.getMaxUses());
		this.setNoRepair();
		
		this.getItemAttributeModifiers();
		this.setMaxDamage(Item.ToolMaterial.EMERALD.getMaxUses());
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player){
		stack.addEnchantment(Enchantment.knockback, Enchantment.knockback.getMaxLevel() + 5);
		stack.addEnchantment(Enchantment.unbreaking, Enchantment.unbreaking.getMaxLevel());
		player.addStat(SurvivalEquipmentAchievements.heavyHitting, 1);
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack){
		if(entityLiving.worldObj.isRemote){
			return false;
		}else{
			if(!EnchantmentHelper.getEnchantments(stack).containsKey(Enchantment.knockback.effectId)){
				stack.addEnchantment(Enchantment.knockback, Enchantment.knockback.getMaxLevel() + 5);
			}
			if(!EnchantmentHelper.getEnchantments(stack).containsKey(Enchantment.unbreaking.effectId)){
				stack.addEnchantment(Enchantment.unbreaking, Enchantment.unbreaking.getMaxLevel());
			}
			return true;
		}
	}
	
	@Override
	public void registerIcons(IIconRegister r){
		itemIcon=r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+"heavy_hammer");
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5){
		if(world.isRemote){
			return;
		}else{
			if(entity instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer)entity;
				ItemStack heldItem = player.getHeldItem();
				if(heldItem != null){
					if(heldItem.getItem() instanceof HeavyHammer){
						if(player.getActivePotionEffect(Potion.moveSlowdown) == null && player.getCurrentEquippedItem() != null){
							player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 10, 1));
						}
						if(player.getActivePotionEffect(Potion.digSlowdown) == null && player.getCurrentEquippedItem() != null){
							player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 10, 1));
						}
						if(player.getActivePotionEffect(Potion.resistance) == null && player.isBlocking()){
							player.addPotionEffect(new PotionEffect(Potion.resistance.id,5,1));
						}
					}
				}
			}
		}
	}
	
	@Override
	public Multimap getItemAttributeModifiers(){
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 2D, 0));
		return multimap;
	}
	
	
	@Override
	public String getIdentifier() {
		return "heavy_hammer";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	
	
}
