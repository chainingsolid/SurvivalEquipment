package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.items.EnchantedBag;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import chainingSolid.survivalEquipment.tileEnties.EnchantedBagChestTile;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class EnchantedBagChest extends BlockContainer implements LoadableBlock {
	
	private IIcon sideTextureOpen,sideTextureClosed;
	
	public EnchantedBagChest(Material m) {
		super(m);
		this.setHardness(2F);
		this.setResistance(1F);
	}
	
	@Override
	public String getIdentifier() {
		return "enchanted_bag_chest";
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9){
		if(world.isRemote){
			return false;
		}else{
			ItemStack stack = player.getHeldItem();
			if(stack != null){
				if(stack.getItem() instanceof EnchantedBag){
					EnchantedBagChestTile tile = ((EnchantedBagChestTile)world.getTileEntity(x, y, z));
					if(tile.addBagToChest(stack)){
						stack.stackSize--;
						return true;
					}
				}
			}
			if(!player.isSneaking()){
				FMLNetworkHandler.openGui(player, SurvivalEquipment.instance, SurvivalEquipment.guiHandler.ENCHANTED_BAG_CHEST, world, x, y, z);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister r){
		this.sideTextureOpen = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+"enchanted_bag_open");
		this.sideTextureClosed = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+"enchanted_bag_closed");
	}
	
	@Override
	public IIcon getIcon(int side, int meta){
		if(meta > 1){
			return sideTextureOpen;
		}else{
			return sideTextureClosed;
		}
	}
	
	
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta){
		if(world.isRemote){
			return;
		}
		EnchantedBagChestTile tile = (EnchantedBagChestTile) world.getTileEntity(x, y, z);
		tile.onDestroyed();
		super.breakBlock(world, x, y, z, block, meta);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new EnchantedBagChestTile();
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
}
