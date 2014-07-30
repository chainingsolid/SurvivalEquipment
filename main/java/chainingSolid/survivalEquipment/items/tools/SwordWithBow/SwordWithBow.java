package chainingSolid.survivalEquipment.items.tools.SwordWithBow;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class SwordWithBow extends ItemBow implements LoadableItem{
	
	public final EnumSwordWithBowBladeTypes BLADE_TYPE;
	private IIcon[] iconArray2;
	
	public SwordWithBow(EnumSwordWithBowBladeTypes bladeType,String texture) {
		super();
		iconArray2 = new IIcon[3];
		BLADE_TYPE = bladeType;
		this.iconString = "sword_with_bow_"+texture;
		this.bFull3D = true;
	}
	
	@Override
	public Multimap getItemAttributeModifiers(){
		Multimap map = HashMultimap.create();
		map.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
				new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.BLADE_TYPE.getMaterial().getDamageVsEntity()+4, 0));
		return map;
	}
	
	@Override
	public int getItemEnchantability(){
		return BLADE_TYPE.getMaterial().getEnchantability();
	}
	
	@Override
	public int getMaxDamage(ItemStack stack){
		return this.BLADE_TYPE.getMaterial().getMaxUses();
	}
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase e1, EntityLivingBase e2){
		stack.damageItem(1, e1);
		return false;
	}
	
	@Override
	public void registerIcons(IIconRegister r){
		this.itemIcon = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+this.iconString);
		this.iconArray2[0] = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+this.iconString);
		this.iconArray2[1] = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+this.iconString+"_");
		this.iconArray2[2] = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+this.iconString+"_");
		
	}
	
	@Override
	public IIcon getItemIconForUseDuration(int i){
		return this.iconArray2[i];
	}
	
	
	
	@Override
	public String getIdentifier() {
		return "Sword-With-Bow"+BLADE_TYPE.getTextureName();
	}
	
	@Override
	public String getUnlocalizedName(){
		return this.getIdentifier();
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
}
