package chainingSolid.survivalEquipment.items.tools;

import java.util.List;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.achievement.SurvivalEquipmentAchievements;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import chainingSolid.survivalEquipment.util.Util;
import chainingSolid.survivalEquipment.util.harvestingExplosion.HarvestingExplosion;

public class StripMinerPick extends ItemPickaxe implements LoadableItem{

	public StripMinerPick() {
		super(ToolMaterial.IRON);
		this.setMaxStackSize(1);
		this.setMaxDamage(2500);
		
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player){
		player.addStat(SurvivalEquipmentAchievements.stripMiningMachine, 1);
	}
	
	@Override
	public boolean onBlockStartBreak(ItemStack stack, int X, int Y, int Z, EntityPlayer player){
		int effcencyLevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack);
		if(effcencyLevel > 0){
			player.addStat(SurvivalEquipmentAchievements.theFastMiner[effcencyLevel-1], 1);
			System.out.println("effciency " +effcencyLevel);
		}
		
		int unbreakinglevel = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, stack);
		if(unbreakinglevel > 0){
			player.addStat(SurvivalEquipmentAchievements.theLongMiner[unbreakinglevel-1], 1);
			System.out.println("unbreaking " +unbreakinglevel);
		}
		
		stack.setRepairCost(0);
		
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_){
		list.add("can be repaied without increaing cost");
	}
	
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta){
		if(this.isBlockMineAble(block)){
			return 20F;
		}else{
			return 1F;
		}
	}
	
	@Override
	public boolean canHarvestBlock(Block block,ItemStack stack){
		return this.isBlockMineAble(block);
	}
	
	private boolean isBlockMineAble(Block block){
		if(Blocks.stone == block || block == Blocks.cobblestone){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world,Block block,int x ,int y ,int z,EntityLivingBase entity){
		if(!world.isRemote){
			stack.damageItem(1, entity);
			if(entity instanceof EntityPlayer){
				
			}
			
		}
		
		return false;
	}
	
	@Override
	public IIcon getIconFromDamage(int par1){
		return itemIcon;
	}
	
	@Override
	public void registerIcons(IIconRegister r){
		itemIcon=r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+"stripminerpick");
	}
	
	
	private void gravelCheck(World world,int x ,int y ,int z, int idk,EntityPlayer player){
		boolean playerInventoryHasTorouch = false;
		
		
	}
	
	
	
	@Override
	public String getIdentifier() {
		return "strip_miner_pick";
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
