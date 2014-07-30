package chainingSolid.survivalEquipment.blocks;

import java.util.ArrayList;

import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EnderMenAtractor extends Block implements LoadableBlock {
	
	public EnderMenAtractor(Material material) {
		super(material);
		this.setHardness(2);
		this.setLightLevel(1);
		this.textureName = "";
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float p_149727_7_,
			float p_149727_8_, float p_149727_9_) {
		if(world.isRemote) {
			return false;
		}else{
			
			EntityEnderman enderman = new EntityEnderman(world);
			enderman.addPotionEffect(new PotionEffect(10, 10000, 1));
			
			enderman.setPosition(x + .5F, y + 1, z + .5F);
			
			if (world.isAirBlock(x, y - 1, z)) {
				world.setBlock(x, y - 1, z, Blocks.stone);
			}
			world.setBlock(x, y, z, Blocks.air);
			
			world.spawnEntityInWorld(enderman);
			
			player.addPotionEffect(new PotionEffect(18,10,0));
			return true;
		}
	}

	@Override
	public String getIdentifier() {
		return "endermen_atractor";
	}

	@Override
	public Block getInstance() {
		return this;
	}

}
