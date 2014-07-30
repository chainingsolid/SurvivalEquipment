package chainingSolid.survivalEquipment.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSnowball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import chainingSolid.survivalEquipment.util.Util;

public class MobFarmFloor extends Block implements LoadableBlock{
	
	
	
	public MobFarmFloor(Material par2Material) {
		super(par2Material);
		this.setHardness(1);
		this.setLightOpacity(256);
		this.setLightLevel(0);
		this.setTickRandomly(true);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z){
		List l = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(-.5+x, y-1, -.5+z, 1.5+x, y+2, 1.5+z));
		EntityPlayer closestPlayer = world.getClosestPlayer(x, y, z, 3);
		if(l.contains(closestPlayer)){
			return super.getCollisionBoundingBoxFromPool(world, x, y, z);
		}else{
			return AxisAlignedBB.getBoundingBox(0, 0, 0, 0, 0, 0);
		}
	}
	
	private IIcon sideTexture , top , bottom;
	
	@Override
	public IIcon getIcon(int side, int meta){
		if(side == Util.I_SIDED_INVENTORY_SIDE_NORTH_ID || side == Util.I_SIDED_INVENTORY_SIDE_SOUTH_ID || side == Util.I_SIDED_INVENTORY_SIDE_EAST_ID || side == Util.I_SIDED_INVENTORY_SIDE_WEST_ID){
			return sideTexture;
		}
		if(side == Util.I_SIDED_INVENTORY_SIDE_TOP_ID){
			return top;
		}
		if(side == Util.I_SIDED_INVENTORY_SIDE_BOTTOM_ID){
			return bottom;
		}
		
		return null;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister r){
		sideTexture = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+"mob_farm_floor_side");
		top = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+"mob_farm_floor_top");
		bottom = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+"clear");
		
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side){
		return true;
	}
	
	@Override
	public boolean isNormalCube(){
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(){
		return false;
	}
	
	@Override
	public String getIdentifier() {
		return "mob_farm_floor";
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
