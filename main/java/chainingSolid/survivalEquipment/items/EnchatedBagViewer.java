package chainingSolid.survivalEquipment.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class EnchatedBagViewer extends EnchantedBag implements LoadableItem{

	public EnchatedBagViewer() {
		super();
		this.setMaxStackSize(1);
		this.iconString = "enchanted_bag_viewer";
	}
	
	@Override
	public void registerIcons(IIconRegister r){
		this.itemIcon = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+iconString);
	}
	
	@Override
	public String getIdentifier() {
		return "enchanted_bag_viewer";
	}
	
	
	@Override
	public Item getInstance() {
		return this;
	}
	
}
