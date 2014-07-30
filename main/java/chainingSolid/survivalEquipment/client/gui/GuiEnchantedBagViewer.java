package chainingSolid.survivalEquipment.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import chainingSolid.survivalEquipment.util.enchantedBagViewerGui.EnchantedBagInventory;
import chainingSolid.survivalEquipment.util.enchantedBagViewerGui.EnchantedBagInventorySlot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiEnchantedBagViewer extends SurvivalUtilsGui {
	
	
	
	public GuiEnchantedBagViewer(EnchantedBagViewerContainer container,EntityPlayer player) {
		super(container);
		
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		super.drawGuiContainerBackgroundLayer(f, x, y);
		GL11.glColor4f(1, 1, 1, 1);
	}
	
	
	
}
