package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;

public class HorseRoad extends Block implements LoadableBlock {
	
	public HorseRoad(Material material) {
		super(material);
		this.setBlockName(this.getIdentifier());
		this.textureName = SurvivalEquipment.MODRESOURCEPATH+"horse_path";
	}
	
	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity){
		if(world.isRemote){
			return;
		}else{
			if(entity instanceof EntityHorse){
				EntityHorse horse = (EntityHorse) entity;
				
				horse.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 5, 4));
				
				horse.addPotionEffect(new PotionEffect(1, 5, 4));
			}
		}
	}
	
	
	@Override
	public String getIdentifier() {
		return "horse_road";
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
	
	
	
}
