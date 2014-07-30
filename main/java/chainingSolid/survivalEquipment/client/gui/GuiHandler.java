package chainingSolid.survivalEquipment.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.tileEnties.EnchantedBagChestTile;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerTile;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {
	
	
	public final static int ENCHANTED_BAG_ID = 0,
			ENCHANTED_ENDER_DIGGER_ID = 1,
			ITEM_STACK_PIPES = 2,
			ENCHANTED_BAG_CHEST = 4;
	
	public GuiHandler(){
		NetworkRegistry.INSTANCE.registerGuiHandler(SurvivalEquipment.instance, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		try{
			TileEntity te = world.getTileEntity(x, y, z);
			switch(ID){
			case ENCHANTED_BAG_ID:
				if(player != null){
					return new EnchantedBagViewerContainer(player);
				}
				break;
			case ENCHANTED_ENDER_DIGGER_ID:
				EnchantedEnderDiggerTile tile = (EnchantedEnderDiggerTile) world.getTileEntity(x, y, z);
				
				if(tile != null &&  player != null){
					return new ContainerEnchantedEnderDigger(tile,player);
				}
				break;
			case ENCHANTED_BAG_CHEST:
				if(player != null && te instanceof EnchantedBagChestTile){
					return new EnchantedBagChestContianer(player,(EnchantedBagChestTile)te);
				}
				break;
			}
		}catch(Exception exeption){
			exeption.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		try{
			TileEntity te = world.getTileEntity(x, y, z);
			switch(ID){
			case ENCHANTED_BAG_ID:
				if(player != null){
					return new GuiEnchantedBagViewer(new EnchantedBagViewerContainer(player),player);
				}
				break;
			case ENCHANTED_ENDER_DIGGER_ID:
				EnchantedEnderDiggerTile tile = (EnchantedEnderDiggerTile) world.getTileEntity(x, y, z);
				
				if(tile != null && player != null){
					return new EnchantedEnderDiggerGui(new ContainerEnchantedEnderDigger(tile,player));
				}
				break;
			case ENCHANTED_BAG_CHEST:
				if(player != null && te instanceof EnchantedBagChestTile){
					return new ChestOfAdvancednessGui(new EnchantedBagChestContianer(player,(EnchantedBagChestTile)te));
				}
				break;
			}
		}catch(Exception exeption){
			exeption.printStackTrace();
		}
		return null;
	}
	
}
