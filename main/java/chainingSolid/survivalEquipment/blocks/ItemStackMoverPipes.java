package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import chainingSolid.survivalEquipment.tileEnties.ItemStackMoverPipesTile;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ItemStackMoverPipes extends BlockContainer implements LoadableBlock{
	
	public ItemStackMoverPipes(Material par2Material) {
		super(par2Material);
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new ItemStackMoverPipesTile();
	}
	
	@Override
	public String getIdentifier() {
		return "item_stack_mover_pipes";
	}
	
	@Override
	public String getUnlocalizedName(){
		return this.getIdentifier();
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
	@Override //TODO forward call to TE  look at modjam code
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float posInBlockX, float posInBlockY, float posInBlockZ){
		if(world.isRemote){
			return false;
		}
		TileEntity te = world.getTileEntity(x, y, z);
		
		
		return false;
	}
	
	
}
