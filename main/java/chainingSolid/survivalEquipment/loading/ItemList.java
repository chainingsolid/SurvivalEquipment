package chainingSolid.survivalEquipment.loading;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import chainingSolid.survivalEquipment.items.EnchantedBag;
import chainingSolid.survivalEquipment.items.EnchatedBagViewer;
import chainingSolid.survivalEquipment.items.LivingArmorTendonPuller;
import chainingSolid.survivalEquipment.items.LivingFlesh;
import chainingSolid.survivalEquipment.items.SlotButtonItem;
import chainingSolid.survivalEquipment.items.SnowGun;
import chainingSolid.survivalEquipment.items.SpawnSight;
import chainingSolid.survivalEquipment.items.armor.livingArmor.LivingArmorBoots;
import chainingSolid.survivalEquipment.items.armor.livingArmor.LivingArmorChestPlate;
import chainingSolid.survivalEquipment.items.armor.livingArmor.LivingArmorHelmet;
import chainingSolid.survivalEquipment.items.armor.livingArmor.LivingArmorLegs;
import chainingSolid.survivalEquipment.items.tools.HeavyHammer;
import chainingSolid.survivalEquipment.items.tools.LongBow;
import chainingSolid.survivalEquipment.items.tools.StripMinerPick;
import chainingSolid.survivalEquipment.items.tools.livingTool.LivingAxe;
import chainingSolid.survivalEquipment.items.tools.livingTool.LivingBow;
import chainingSolid.survivalEquipment.items.tools.livingTool.LivingHoe;
import chainingSolid.survivalEquipment.items.tools.livingTool.LivingPickaxe;
import chainingSolid.survivalEquipment.items.tools.livingTool.LivingSpade;
import chainingSolid.survivalEquipment.items.tools.livingTool.LivingSword;

public class ItemList {
	
	
	
	public static StripMinerPick stripMinerPick = new StripMinerPick();
	public static EnchantedBag enchantedBag = new EnchantedBag();
	public static EnchatedBagViewer enchantedBagViewer = new EnchatedBagViewer();
	public static SnowGun snowGun = new SnowGun();
	
	private static ArmorMaterial livingArmorMaterial = ArmorMaterial.CLOTH;
	
	public static LivingArmorHelmet livingArmorHelmet  = new LivingArmorHelmet();
	public static LivingArmorChestPlate livingArmorChestPlate = new LivingArmorChestPlate();
	public static LivingArmorLegs livingArmorLegs = new LivingArmorLegs();
	public static LivingArmorBoots livingArmorBoots = new LivingArmorBoots();
	
	public static LivingArmorTendonPuller livingArmorTendonPuller = new LivingArmorTendonPuller();
	
	public static LivingFlesh livingFlesh = new LivingFlesh();
	
	public static HeavyHammer hammer = new HeavyHammer();
	
	public static SlotButtonItem slotButtonItem = new SlotButtonItem();
	
	public static LivingSword livingSword = new LivingSword(ToolMaterial.WOOD);
	
	public static LivingPickaxe livingPickaxe = new LivingPickaxe(ToolMaterial.WOOD);
	
	public static LivingSpade livingSpade = new LivingSpade(ToolMaterial.WOOD);
	
	public static LivingAxe livingAxe = new LivingAxe(ToolMaterial.WOOD);
	
	public static LivingHoe livingHoe = new LivingHoe(ToolMaterial.WOOD);
	
	public static LivingBow livingBow = new LivingBow();
	
	public static LongBow longBow = new LongBow();
	
	public static SpawnSight spawnSight = new SpawnSight();
	
	
}
