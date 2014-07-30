package chainingSolid.survivalEquipment;

import chainingSolid.survivalEquipment.loading.ItemList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SurvialEquipmentTab extends CreativeTabs {
	
	public SurvialEquipmentTab(String label) {
		super(label);
		//this.setBackgroundImageName("item_search.png");
	}
	
	@Override
	public EnumEnchantmentType[] func_111225_m(){
		return new EnumEnchantmentType[1];
	}
	
	@Override
	public boolean hasSearchBar(){
		return false;
	}
	
	@Override
	public String getTranslatedTabLabel(){
		return this.getTabLabel();
	}
	
	@Override
	public ItemStack getIconItemStack(){
		return new ItemStack(ItemList.stripMinerPick);
	}
	
	@Override
	public Item getTabIconItem() {
		return ItemList.stripMinerPick;
	}
}
