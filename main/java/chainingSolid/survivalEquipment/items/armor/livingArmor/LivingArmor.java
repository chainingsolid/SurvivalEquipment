package chainingSolid.survivalEquipment.items.armor.livingArmor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;
import net.minecraftforge.common.util.EnumHelper;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import chainingSolid.survivalEquipment.util.Util;
import chainingSolid.survivalEquipment.util.livingEquipment.ILivingEquipment;
import chainingSolid.survivalEquipment.util.livingEquipment.LivingEquipmentEffect;
import chainingSolid.survivalEquipment.util.livingEquipment.LivingEquipmentProgresser;
import chainingSolid.survivalEquipment.util.livingEquipment.effects.DamadgeAdaptionEffect;

//TODO make the armor behave like armor

public abstract class LivingArmor extends ItemArmor implements ISpecialArmor, ILivingEquipment, LoadableItem{
	
	private static final int ENCHANTABLITY = 20;
	
	public static final String LEVEL = "level",DAMAGE = "damage";
	public static final int STARTING_DAMAGE = 0,MAX_LEVEL = 10,STARTING_DURABILITY = 100;
	
	
	public LivingArmor(int armorIndexType) {
		super(ArmorMaterial.CHAIN, 1, armorIndexType);
		this.setMaxDamage(1);
		this.setNoRepair();
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player){
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger(LEVEL, 1);
		stack.getTagCompound().setInteger(DAMAGE, STARTING_DAMAGE);
	}
	
	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot){
		Util.nullNBTTagCheck(stack);
		
		stack.getTagCompound().setInteger(DAMAGE, stack.getItemDamage() + damage);
		
	}
	
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot){
		Util.nullNBTTagCheck(armor);
		
		if(armor.getTagCompound().getInteger(LEVEL) < 1){
			armor.getTagCompound().setInteger(LEVEL, 1);
		}
		
		int damadgeReduction = 0;
		int armorIndex = armor.getTagCompound().getInteger(LEVEL);
		ArmorProperties props = new ArmorProperties(0,0,0);
		props.AbsorbMax = armor.getMaxDamage() +1 - armor.getItemDamage();
		props.AbsorbRatio = armor.getTagCompound().getInteger(LEVEL) / 50D;
		props.Priority = 0;
		
		return props;
	}
	
	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot){
		Util.nullNBTTagCheck(armor);
		
		return armor.getTagCompound().getInteger(LEVEL);
	}
	
	@Override
	public int getMaxDamage(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		int level = stack.getTagCompound().getInteger(LEVEL);
		return level * 50 + this.STARTING_DURABILITY;
	}
	
	@Override
	public int getDamage(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		int damage = stack.getTagCompound().getInteger(DAMAGE);
		stack.setItemDamage(damage);
		return damage;
	}
	
	@Override
	public void setDamage(ItemStack stack, int damage){
		stack.getTagCompound().setInteger(DAMAGE, damage);
		if(damage < 0 || damage > this.getMaxDamage(stack)){
			stack.stackSize = 0;
		}
	}
	
	@Override
	public int getDisplayDamage(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		return stack.getTagCompound().getInteger(DAMAGE);
	}
	
	@Override
	public boolean isDamaged(ItemStack stack){
		if(this.getDamage(stack) > 0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean canUpgrade(ItemStack stack){
		if(this.getMaxDamage(stack)*.5 > this.getDamage(stack)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public int getLevel(ItemStack stack) {
		Util.nullNBTTagCheck(stack);
		return stack.getTagCompound().getInteger(LEVEL);
	}
	
	@Override
	public float getMaxLevel(ItemStack stack) {
		return 10;
	}
	
	@Override
	public float getUpgradeChance(ItemStack stack) {
		return .01F;
	}
	
	@Override
	public void levelUp(ItemStack stack) {
		Util.nullNBTTagCheck(stack);
		stack.getTagCompound().setInteger(LEVEL, stack.getTagCompound().getInteger(LEVEL)+1);
	}
	
}
