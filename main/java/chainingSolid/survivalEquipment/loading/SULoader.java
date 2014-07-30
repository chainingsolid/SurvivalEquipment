package chainingSolid.survivalEquipment.loading;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.blocks.fluids.LivingInfestedWater;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class SULoader {
	
	private ArrayList<LoadableItem> itemsToLoad = new ArrayList<LoadableItem>();
	
	private ArrayList<LoadableBlock> blocksToLoad = new ArrayList<LoadableBlock>();
	
	public void preInit(FMLPreInitializationEvent event){
		this.buildFluids();
		this.addItems();
		this.addBlocks();
		
		
		this.initalizeItemArray();
		this.initalizeBlockArray();
		
		this.nameItems();
		this.nameBlocks();
		
		this.registerItems();
		this.registerBlocks();
		
		this.registerRecipes();
		
		this.addTileEntityRenderers(event);
	}
	
	private void buildFluids() {
		//BlockList.livingInfestedWater  = new LivingInfestedWater(Material.water);
		
	}
	
	public void Init(FMLInitializationEvent event){
		this.addLivingEquipmentEffects();
		
		this.registerRecipes();
	}
	
	public void PostInit(FMLPostInitializationEvent event){
		
	}
	
	private void addLivingEquipmentEffects(){
		
	}
	
	private void addTileEntityRenderers(FMLPreInitializationEvent event) {
		
		
		
	}
	
	private void addItems(){
		itemsToLoad.add(ItemList.enchantedBag);
		//itemsToLoad.add(ItemList.enchantedBagViewer);
		itemsToLoad.add(ItemList.snowGun);
		itemsToLoad.add(ItemList.stripMinerPick);
		itemsToLoad.add(ItemList.livingArmorHelmet);
		itemsToLoad.add(ItemList.livingArmorChestPlate);
		itemsToLoad.add(ItemList.livingArmorLegs);
		itemsToLoad.add(ItemList.livingArmorBoots);
		itemsToLoad.add(ItemList.livingArmorTendonPuller);
		itemsToLoad.add(ItemList.livingFlesh);
		itemsToLoad.add(ItemList.hammer);
		itemsToLoad.add(ItemList.slotButtonItem);
		itemsToLoad.add(ItemList.livingSword);
		itemsToLoad.add(ItemList.livingPickaxe);
		itemsToLoad.add(ItemList.livingSpade);
		itemsToLoad.add(ItemList.livingAxe);
		itemsToLoad.add(ItemList.livingHoe);
		itemsToLoad.add(ItemList.livingBow);
		itemsToLoad.add(ItemList.longBow);
		itemsToLoad.add(ItemList.spawnSight);
	}
	
	private void addBlocks(){
		blocksToLoad.add(BlockList.enchatntedEnderDigger);
		blocksToLoad.add(BlockList.mobFarmFloor);
		blocksToLoad.add(BlockList.itemStackMoverPipes);
		blocksToLoad.add(BlockList.slipperyPath);
		blocksToLoad.add(BlockList.slipperyPathSlab);
		blocksToLoad.add(BlockList.stripMineInfoBlock);
		blocksToLoad.add(BlockList.portal);
		blocksToLoad.add(BlockList.horseRoad);
		blocksToLoad.add(BlockList.endermenAtractor);
		blocksToLoad.add(BlockList.xRay);
		blocksToLoad.add(BlockList.chandelier);
		blocksToLoad.add(BlockList.erosionPlant);
		blocksToLoad.add(BlockList.enchantedBagChest);
		blocksToLoad.add(BlockList.enchantedBookShelf);
		blocksToLoad.add(BlockList.advancedEnchantmentTable);
		blocksToLoad.add(BlockList.mobSpawnerQuickener);
		//blocksToLoad.add(BlockList.livingInfestedWater);
	}
	
	LoadableItem items[];
	
	LoadableBlock blocks[];
	
	private void initalizeItemArray(){
		items = new LoadableItem[1];
		items = itemsToLoad.toArray(items);
	}
	private void initalizeBlockArray(){
		blocks = new LoadableBlock[1];
		blocks = blocksToLoad.toArray(blocks);
	}
	
	private void nameItems(){
		for(LoadableItem item:items){
			item.getInstance().setUnlocalizedName(item.getIdentifier());
		}
	}
	
	private void nameBlocks(){
		for(LoadableBlock block:blocks){
			block.getInstance().setBlockName(block.getIdentifier());
		}
	}
	
	private void registerItems(){
		for(LoadableItem item:items){
			GameRegistry.registerItem(item.getInstance(), item.getIdentifier());
			item.getInstance().setCreativeTab(SurvivalEquipment.tab);
		}
	}
	
	private void registerBlocks(){
		for(LoadableBlock block:blocks){
			GameRegistry.registerBlock(block.getInstance(), block.getIdentifier());
			block.getInstance().setCreativeTab(SurvivalEquipment.tab);
		}
	}
	
	private void registerRecipes(){
		GameRegistry.addRecipe(new ItemStack(ItemList.stripMinerPick,1),"sis"," i "," i ",'s',Blocks.stone,'i',Items.iron_ingot);
		GameRegistry.addRecipe(new ItemStack(BlockList.stripMineInfoBlock,1),"sis","gtg","sis",'s',Blocks.stone_slab,'i',Items.iron_ingot,'g',Blocks.glass,'t',Blocks.redstone_torch);
		GameRegistry.addRecipe(new ItemStack(Blocks.netherrack,8),"ccc","cbc","ccc",'c',Blocks.cobblestone,'b',Items.blaze_powder);
		GameRegistry.addRecipe(new ItemStack(Blocks.soul_sand,8),new Object[]{"sss","sws","sss",'s',Blocks.sand,'w',new ItemStack(Items.skull, 1, 1)});

		GameRegistry.addRecipe(new ItemStack(BlockList.horseRoad,6),"f f","ggg"," s ",'f',Blocks.fence,'g',Blocks.gravel,'s',Blocks.stonebrick);

		GameRegistry.addRecipe(new ItemStack(BlockList.horseRoad,3),"f f","ggg"," s ",'f',Blocks.fence,'g',Blocks.gravel,'s',Blocks.stonebrick);

		
		GameRegistry.addRecipe(new ItemStack(ItemList.enchantedBag),"lsl","lsl","bgb",'l',Items.leather,'s',Items.string,'b',Items.enchanted_book,'g',Items.gold_ingot);
		GameRegistry.addRecipe(new ItemStack(BlockList.enchantedBagChest),"lbl","lcl","lbl",'l',Items.leather,'c',Blocks.chest,'b',Items.enchanted_book);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.livingFlesh),"pbv","lrw","cnm",'p',Items.porkchop,'b',Items.bone,'v', 
				Blocks.vine,'l',Items.leather,'r',Items.blaze_rod,'w',Items.wheat,
				'c',Items.chicken,'n',Items.nether_wart,'m',Items.melon);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.hammer),"ogg","oig"," i ",'o',Blocks.obsidian,'g',Blocks.gold_block,'i',Blocks.iron_block);
		
		GameRegistry.addRecipe(new ItemStack(Items.quartz,4),"b",'b',Blocks.quartz_block);
		
		GameRegistry.addRecipe(new ItemStack(BlockList.mobFarmFloor,16),"pdp","sis","srs",'p',Blocks.wooden_pressure_plate,'d',Blocks.trapdoor,'s',Items.stick,'i',Blocks.iron_bars,'r',Items.redstone);
		
		GameRegistry.addSmelting(new ItemStack(Items.wheat), new ItemStack(Items.string), 0);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.snowGun)," hp","dfs","rrs",'r',Blocks.redstone_block,'d',Blocks.dispenser,'f',Items.diamond_shovel,'p',Blocks.pumpkin,'h',Blocks.heavy_weighted_pressure_plate,'s',Blocks.snow);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.livingArmorBoots),"l l","l l",'l',ItemList.livingFlesh);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.livingArmorChestPlate),"l l","lll","lll",'l',ItemList.livingFlesh);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.livingArmorHelmet),"lll","l l","   ",'l',ItemList.livingFlesh);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.livingArmorLegs),"lll","l l","l l",'l',ItemList.livingFlesh);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.livingAxe)," ll"," sl"," s ",'l',ItemList.livingFlesh,'s',Items.stick);
		GameRegistry.addRecipe(new ItemStack(ItemList.livingAxe),"ll ","ls "," s ",'l',ItemList.livingFlesh,'s',Items.stick);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.livingHoe),"ll "," s "," s ",'l',ItemList.livingFlesh,'s',Items.stick);
		GameRegistry.addRecipe(new ItemStack(ItemList.livingHoe)," ll"," s "," s ",'l',ItemList.livingFlesh,'s',Items.stick);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.livingPickaxe),"lll"," s "," s ",'l',ItemList.livingFlesh,'s',Items.stick);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.livingSpade)," l "," s "," s ",'l',ItemList.livingFlesh,'s',Items.stick);
		
		GameRegistry.addRecipe(new ItemStack(ItemList.livingSword)," l "," l "," s ",'l',ItemList.livingFlesh,'s',Items.stick);
	}
	
	
	
	
	
	
	
	
	
}
