package chainingSolid.survivalEquipment.items;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import chainingSolid.survivalEquipment.util.Util;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class SlotButtonItem extends Item implements LoadableItem{
	
	public static final int 
			ITEM_STACK_ROUTER_NORTH = Util.I_SIDED_INVENTORY_SIDE_NORTH_ID,
			ITEM_STACK_ROUTER_SOUTH = Util.I_SIDED_INVENTORY_SIDE_SOUTH_ID,
			ITEM_STACK_ROUTER_EAST = Util.I_SIDED_INVENTORY_SIDE_EAST_ID,
			ITEM_STACK_ROUTER_WEST = Util.I_SIDED_INVENTORY_SIDE_WEST_ID,
			ITEM_STACK_ROUTER_TOP = Util.I_SIDED_INVENTORY_SIDE_TOP_ID,
			ITEM_STACK_ROUTER_BOTTOM = Util.I_SIDED_INVENTORY_SIDE_BOTTOM_ID,
			ITEM_STACK_ROUTER_RULE_WHITE_LIST = 6,
			ITEM_STACK_ROUTER_RULE_BLACK_LIST = 7,
			ITEM_STACK_ROUTER_RULE_TAB_SORT = 8,
			ITEM_STACK_ROUTER_RULE_MOD_SORT = 9,
			ITEM_STACK_ROUTER_RULE_DISPLAY_NAME_SORT = 10,
			ITEM_STACK_ROUTER_DISPLAY_NAME_EXACT_MATCH = 11,
			ITEM_STACK_ROUTER_DISPLAY_NAME_CONTIANS = 12,
			ITEM_STACK_ROUTER_DISPLAY_NAME_STARTS_WITH = 13,
			ITEM_STACK_ROUTER_DISPLAY_NAME_ENDS_WITH = 14
			;
	
	private static HashMap<Integer,SlotButtonInfo> slotButtonInfos = new HashMap<Integer,SlotButtonInfo>();
	
	public SlotButtonItem(){
		this.setMaxDamage(1000);
		this.addButtonInfos();
	}
	
	private static void addButtonInfos(){
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_NORTH,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_north"),
						ITEM_STACK_ROUTER_NORTH,
						"North")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_SOUTH,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_south"),
						ITEM_STACK_ROUTER_SOUTH,
						"South")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_EAST,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_east"),
						ITEM_STACK_ROUTER_EAST,
						"East")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_WEST,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_west"),
						ITEM_STACK_ROUTER_WEST,
						"West")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_TOP,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_top"),
						ITEM_STACK_ROUTER_TOP,
						"Top")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_BOTTOM,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_bottom"),
						ITEM_STACK_ROUTER_BOTTOM,
						"Bottom")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_RULE_WHITE_LIST,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_rule_white_list"),
						ITEM_STACK_ROUTER_RULE_WHITE_LIST,
						"Rule:WhiteList")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_RULE_BLACK_LIST,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_rule_black_list"),
						ITEM_STACK_ROUTER_RULE_BLACK_LIST,
						"Rule:BlackList")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_RULE_TAB_SORT,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_rule_tab_sort"),
						ITEM_STACK_ROUTER_RULE_TAB_SORT,
						"Rule:TabSort")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_RULE_MOD_SORT,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_rule_mod_sort"),
						ITEM_STACK_ROUTER_RULE_MOD_SORT,
						"Rule:ModSort")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_RULE_DISPLAY_NAME_SORT,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_rule_display_name_sort"),
						ITEM_STACK_ROUTER_RULE_DISPLAY_NAME_SORT,
						"Rule:DisplayNameSort")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_DISPLAY_NAME_EXACT_MATCH,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_rule_display_name_exact_match"),
						ITEM_STACK_ROUTER_DISPLAY_NAME_EXACT_MATCH,
						"DisplayNameSortMode:ExactMatch")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_DISPLAY_NAME_CONTIANS,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_rule_display_name_contianes"),
						ITEM_STACK_ROUTER_DISPLAY_NAME_CONTIANS,
						"DisplayNameSortMode:Contianes")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_DISPLAY_NAME_STARTS_WITH,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_rule_display_name_starts_with"),
						ITEM_STACK_ROUTER_DISPLAY_NAME_STARTS_WITH,
						"DisplayNameSortMode:StartsWith")
				);
		slotButtonInfos.put(
				ITEM_STACK_ROUTER_DISPLAY_NAME_ENDS_WITH,
				new SlotButtonInfo(
						new ResourceLocation(SurvivalEquipment.MODRESOURCEPATH,"/textures/items/item_stack_router_rule_display_name_ends_with"),
						ITEM_STACK_ROUTER_DISPLAY_NAME_ENDS_WITH,
						"DisplayNameSortMode:EndsWith")
				);
		
	}
	
	@Override
	public int getDisplayDamage(ItemStack stack){
		return 0;
	}
	
	@Override
	public boolean isDamaged(ItemStack stack){
		return false;
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack){
		return slotButtonInfos.get(stack.getItemDamage()).NAME;
	}
	
	
	@Override
	public IIcon getIconFromDamage(int damadge){
		return slotButtonInfos.get(damadge).icon;
	}
	
	@Override
	public void registerIcons(IIconRegister register){
		for(SlotButtonInfo info:slotButtonInfos.values()){
			info.icon = register.registerIcon(info.ICON_LOCATION.getResourceDomain()+info.ICON_LOCATION.getResourcePath());
		}
	}
	
	@Override
	public String getIdentifier() {
		return "slot-button_info_item";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	public static class SlotButtonInfo{
		
		public IIcon icon;
		public final ResourceLocation ICON_LOCATION;
		public final int DAMADGE;
		public final String NAME;
		
		public SlotButtonInfo(ResourceLocation iconLocation,int damadge,String name){
			this.ICON_LOCATION = iconLocation;
			this.DAMADGE = damadge;
			this.NAME = name;
		}
		
	}
	
}
