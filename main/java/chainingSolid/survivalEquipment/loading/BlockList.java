package chainingSolid.survivalEquipment.loading;

import net.minecraft.block.material.Material;
import chainingSolid.survivalEquipment.blocks.AdvancedEnchantmentTable;
import chainingSolid.survivalEquipment.blocks.Chandelier;
import chainingSolid.survivalEquipment.blocks.EnchantedBagChest;
import chainingSolid.survivalEquipment.blocks.EnchantedBookShelf;
import chainingSolid.survivalEquipment.blocks.EnchantedEnderDigger;
import chainingSolid.survivalEquipment.blocks.EnderMenAtractor;
import chainingSolid.survivalEquipment.blocks.ErosionPlant;
import chainingSolid.survivalEquipment.blocks.HorseRoad;
import chainingSolid.survivalEquipment.blocks.ItemStackMoverPipes;
import chainingSolid.survivalEquipment.blocks.MobFarmFloor;
import chainingSolid.survivalEquipment.blocks.MobSpawnerQuickener;
import chainingSolid.survivalEquipment.blocks.Portal;
import chainingSolid.survivalEquipment.blocks.SlipperyPath;
import chainingSolid.survivalEquipment.blocks.SlipperyPathSlab;
import chainingSolid.survivalEquipment.blocks.StripMineInfoBlock;
import chainingSolid.survivalEquipment.blocks.XRayBlock;
import chainingSolid.survivalEquipment.blocks.fluids.LivingInfestedWater;

public class BlockList {
	
	public static EnchantedEnderDigger enchatntedEnderDigger = new EnchantedEnderDigger(Material.iron);
	public static MobFarmFloor mobFarmFloor = new MobFarmFloor(Material.rock);
	public static ItemStackMoverPipes itemStackMoverPipes = new ItemStackMoverPipes(Material.iron);
	public static SlipperyPath slipperyPath = new SlipperyPath(Material.rock);
	public static SlipperyPathSlab slipperyPathSlab = new SlipperyPathSlab(false, Material.rock);
	public static StripMineInfoBlock stripMineInfoBlock = new StripMineInfoBlock(Material.iron);
	public static Portal portal = new Portal(Material.iron);
	public static HorseRoad horseRoad = new HorseRoad(Material.ground);
	public static EnderMenAtractor endermenAtractor = new EnderMenAtractor(Material.rock);
	public static XRayBlock xRay = new XRayBlock(Material.ground);
	public static Chandelier chandelier = new Chandelier(Material.wood);
	public static ErosionPlant erosionPlant = new ErosionPlant(Material.plants);
	public static EnchantedBagChest enchantedBagChest = new EnchantedBagChest(Material.wood);
	public static EnchantedBookShelf enchantedBookShelf = new EnchantedBookShelf(Material.wood);
	public static AdvancedEnchantmentTable advancedEnchantmentTable = new AdvancedEnchantmentTable();
	public static LivingInfestedWater livingInfestedWater;
	public static MobSpawnerQuickener mobSpawnerQuickener = new MobSpawnerQuickener(Material.rock);
	//TODO um was told to not touch fliud id map but it's null so no fluids
	
}
