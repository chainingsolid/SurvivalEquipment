package chainingSolid.survivalEquipment.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import chainingSolid.survivalEquipment.util.Util;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class EnchantedBag extends Item implements LoadableItem{
	
	public static final String IS_OPEN = "is_open";
	
	public EnchantedBag(){
		super();
		this.setMaxStackSize(1);
		
	}
	
	private IIcon open,closed;
	
	@Override
	public void registerIcons(IIconRegister r){
		open = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+"enchanted_bag_open");
		closed = r.registerIcon(SurvivalEquipment.MODRESOURCEPATH+"enchanted_bag_closed");
		
	}
	
	@Override
	public IIcon getIconIndex(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		if(stack.getTagCompound().getBoolean(IS_OPEN)){
			return open;
		}else{
			return closed;
		}
	}
	
	@Override
	public IIcon getIcon(ItemStack stack, int pass){
		Util.nullNBTTagCheck(stack);
		if(stack.getTagCompound().getBoolean(IS_OPEN)){
			return open;
		}else{
			return closed;
		}
	}
	
	@Override
	public String getIdentifier() {
		return "enchanted_bag";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){
		if(world.isRemote){
			return stack;
		}else{
			if(!player.isSneaking()){
				FMLNetworkHandler.openGui(player, SurvivalEquipment.instance, SurvivalEquipment.guiHandler.ENCHANTED_BAG_ID, world, 0, 0, 0);
			}
			return stack;
		}
	}
	
	@Override
	public boolean hasEffect(ItemStack stack, int pass){
		return true;
	}
	
	@Override
	public void onCreated(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer){
		stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setBoolean(IS_OPEN, false);
	}
	
}
