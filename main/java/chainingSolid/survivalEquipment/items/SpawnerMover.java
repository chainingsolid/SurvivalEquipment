package chainingSolid.survivalEquipment.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import chainingSolid.survivalEquipment.util.Util;

public class SpawnerMover extends Item implements LoadableItem {
	
	public static final String SPAWNER_TAG = "SPAWNER_TAG",
			HAS_SPAWNER = "HAS_SPAWNER";
	
	public SpawnerMover() {
		this.setMaxStackSize(1);
	}
	
	@Override
	public String getIdentifier() {
		return "spawner_mover";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ){
		if(world.isRemote){
			return false;
		}
		Util.nullNBTTagCheck(stack);
		if(stack.getTagCompound().getBoolean(HAS_SPAWNER)){
			
		}
		Block block = world.getBlock(x, y, z);
		if(block instanceof BlockMobSpawner){
			BlockMobSpawner spawner = (BlockMobSpawner) block;
			TileEntityMobSpawner tileSpawner = (TileEntityMobSpawner) world.getTileEntity(x, y, z);
			this.pickUpSpawner(spawner, tileSpawner, stack);
			return true;
		}else{
			return false;
		}
	}
	
	
	private void pickUpSpawner(BlockMobSpawner spawner, TileEntityMobSpawner tileSpawner, ItemStack stack){
		NBTTagCompound tag = new NBTTagCompound();
		tileSpawner.writeToNBT(tag);
		stack.getTagCompound().setTag(SPAWNER_TAG, tag);
		stack.getTagCompound().setBoolean(HAS_SPAWNER, true);
		
	}
	
	private void placeSpawner(ItemStack stack, World world){
		
	}
	
}
