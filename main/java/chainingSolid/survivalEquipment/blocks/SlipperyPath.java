package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.achievement.SurvivalEquipmentAchievements;
import chainingSolid.survivalEquipment.loading.ItemList;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class SlipperyPath extends Block implements LoadableBlock{

	public SlipperyPath(Material par2Material) {
		super( par2Material);
		this.setHardness(2F);
		this.slipperiness=1F;
		this.textureName= SurvivalEquipment.MODRESOURCEPATH+"slipperypath";
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if(world.isRemote){
			return;
		}
		if(entity instanceof EntityPlayer){
			try {
				EntityPlayer player = (EntityPlayer) entity;
				
				player.addPotionEffect(new PotionEffect(Potion.jump.id, 10, 1));
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 10, 2));
				player.addStat(SurvivalEquipmentAchievements.slipperyFast, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

	@Override
	public String getIdentifier() {
		return "slippery_path";
	}
	
	@Override
	public String getUnlocalizedName(){
		return this.getIdentifier();
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
	
	
}
