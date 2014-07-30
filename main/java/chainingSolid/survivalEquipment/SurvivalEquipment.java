package chainingSolid.survivalEquipment;

import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import chainingSolid.survivalEquipment.achievement.SurvivalEquipmentAchievements;
import chainingSolid.survivalEquipment.client.gui.GuiHandler;
import chainingSolid.survivalEquipment.dimension.WorldProviderLostLand;
import chainingSolid.survivalEquipment.dimension.biomes.BiomeList;
import chainingSolid.survivalEquipment.loading.CommonProxy;
import chainingSolid.survivalEquipment.loading.SULoader;
import chainingSolid.survivalEquipment.network.EventChannnelHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = "SurvivalEquipment")
public class SurvivalEquipment {
	
	@Instance
	public static SurvivalEquipment instance = new SurvivalEquipment();
	@SidedProxy(modId="SurvivalEquipment",clientSide="chainingSolid.survivalEquipment.loading.ClientProxy",serverSide="chainingSolid.survivalEquipment.loading.CommonProxy")
	public static CommonProxy proxy;
	
	public static int dimid = 10;
	
	public static SurvialEquipmentTab tab = new SurvialEquipmentTab("SurvivalEquipment");
	
	public static GuiHandler guiHandler;
	
	public static final SULoader LOADER = new SULoader();
	
	public static final String MODRESOURCEPATH="survivalequipment:";
	public static final String CHANNEL_NAME = "SurvivalEquipment";
	
	public static FMLEventChannel channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(CHANNEL_NAME);
	
	@EventHandler
	public void preInint(FMLPreInitializationEvent event){
		proxy.preInit(event);
		guiHandler = new GuiHandler();
		
		DimensionManager.registerProviderType(dimid, WorldProviderLostLand.class, false);
		DimensionManager.registerDimension(dimid, dimid);
		
		
		BiomeManager.addVillageBiome(BiomeList.INSTANCE.fertilePlains, true);
		
	}
	
	@EventHandler
	public void inint(FMLInitializationEvent event){
		proxy.Init(event);
		FMLCommonHandler.instance().bus().register(new chainingSolid.survivalEquipment.event.EventHandler());
		MinecraftForge.EVENT_BUS.register(new chainingSolid.survivalEquipment.event.EventHandler());
		SurvivalEquipmentAchievements.addAchievements();
		
		
		channel.register(new EventChannnelHandler());
	}
	
	@EventHandler
	public void postInint(FMLPostInitializationEvent event){
		proxy.postInit(event);
	}
	
	
}
