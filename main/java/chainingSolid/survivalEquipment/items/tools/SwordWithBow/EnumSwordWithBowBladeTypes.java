package chainingSolid.survivalEquipment.items.tools.SwordWithBow;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public enum EnumSwordWithBowBladeTypes {
	
	Wood(ToolMaterial.WOOD,"Wood",Block.getBlockFromName("planks")),
			
	Stone(ToolMaterial.STONE,"Stone",Block.getBlockFromName("stone")),
			
	Iron(ToolMaterial.IRON,"Iron",Item.itemRegistry.getObject("iron")),
			
	Gold(ToolMaterial.GOLD,"Gold",Item.itemRegistry.getObject("gold")),
			
	Diamond(ToolMaterial.EMERALD,"Diamond",Item.itemRegistry.getObject("Diamond"))
	;
	
	private ToolMaterial material;
	private String textureName;
	private Object craftingMaterial;
	
	private EnumSwordWithBowBladeTypes(ToolMaterial material,String textureName,Object craftingMaterial){
		this.material = material;
		this.textureName = textureName;
		this.craftingMaterial = craftingMaterial;
	}
	
	public ToolMaterial getMaterial(){
		return this.material;
	}
	
	public String getTextureName(){
		return this.textureName;
	}
	
	public Object getCraftingMaterial(){
		return craftingMaterial;
	}
}
