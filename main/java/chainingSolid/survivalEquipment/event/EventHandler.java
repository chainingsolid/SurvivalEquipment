package chainingSolid.survivalEquipment.event;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

import org.lwjgl.input.Keyboard;

import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.items.tools.LongBow;
import chainingSolid.survivalEquipment.keyBind.LivingArmorWingsKeyBind;
import chainingSolid.survivalEquipment.network.EventData.LivingArmorFlightClientInput;
import chainingSolid.survivalEquipment.util.livingEquipment.ILivingEquipment;
import chainingSolid.survivalEquipment.util.livingEquipment.LivingEquipmentProgresser;
import chainingSolid.survivalEquipment.util.livingEquipment.effects.Flight;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class EventHandler {
	
	public EventHandler(){
		
	}
	
	@SubscribeEvent
	public void PlayerTickEvent(PlayerTickEvent event){
		
		if(event.side.equals(event.side.SERVER)){
			LivingEquipmentProgresser.updateLivingEquipmentInInv(event.player.inventory);
		}
		
		if(event.side.isServer() && event.phase.END.equals(event.phase)){
			Flight.flightTick(event.player);
			return;
		}else if(event.side.isClient() && event.phase.START.equals(event.phase)){
			LivingArmorFlightClientInput data = new LivingArmorFlightClientInput();
			data.isJumpKeyDown = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode());
			data.isCrouchKeyDown = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak.getKeyCode());
			data.isForwardKeyDown = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode());
			data.isBackwardKeyDown = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindBack.getKeyCode());
			data.isRightKeyDown = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindRight.getKeyCode());
			data.isLeftKeyDown = Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindLeft.getKeyCode());
			//data.isFlightToggleDown = LivingArmorWingsKeyBind.LAWingsKeyBind.getIsKeyPressed();
			data.isFlightToggleDown = false;
			data.playerId = event.player.getEntityId();
			
			FMLProxyPacket pck = new FMLProxyPacket(data.writeDataToArray(),SurvivalEquipment.CHANNEL_NAME);
			SurvivalEquipment.channel.sendToServer(pck);
		}
	}
	
	@SubscribeEvent
	public void ItemTooltipEvent(net.minecraftforge.event.entity.player.ItemTooltipEvent event){
		try{
			Item item = event.itemStack.getItem();
			event.toolTip.add("SurvivalEquipment tool tip info");
			if(item instanceof ILivingEquipment){
				ILivingEquipment e = (ILivingEquipment)item;
				event.toolTip.add("Level: "+e.getLevel(event.itemStack));
			}
			event.toolTip.add("damadge: "+event.itemStack.getItemDamage());
			event.toolTip.add("max Damadge: "+event.itemStack.getMaxDamage());
			for(CreativeTabs tab: item.getCreativeTabs()){
				event.toolTip.add("Tab name: "+tab.getTabLabel());
			}
		}catch(Exception e){
			
		}
	}
	
	@SubscribeEvent
	public void arrowLooseEvent(ArrowLooseEvent event){
		if(event.bow.getItem() instanceof LongBow){
			event.charge *= 5;
		}
	}
	
	
}
