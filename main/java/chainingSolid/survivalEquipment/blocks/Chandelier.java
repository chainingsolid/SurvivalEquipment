package chainingSolid.survivalEquipment.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;

public class Chandelier extends Block implements LoadableBlock {

	public Chandelier(Material material) {
		super(material);
		this.setLightOpacity(0);
		this.setLightLevel(1F);
		this.setBlockBounds(.2F, .0F, .2F, .8F, 1F, .8F);
	}
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB AABB, List list, Entity entity){
		super.addCollisionBoxesToList(world, x, y, z, AABB, list, entity);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z){
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z){
		return super.getSelectedBoundingBoxFromPool(world, y, y, z);
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player){
		System.out.println("player Vec"+player.getLookVec().toString());
		super.onBlockClicked(world, x, y, z, player);
	}
	
	@Override
	public String getIdentifier() {
		return "chandelier";
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
}
