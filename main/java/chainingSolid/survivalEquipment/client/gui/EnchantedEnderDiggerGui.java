package chainingSolid.survivalEquipment.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class EnchantedEnderDiggerGui extends SurvivalUtilsGui {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation("survivalutils", "textures/gui/enchantedbagviewer.png");
	
	ContainerEnchantedEnderDigger contianer;
	
	protected final int SLOT_SIZE = 20;
	
	private GuiTextField instructions;
	
	public EnchantedEnderDiggerGui(ContainerEnchantedEnderDigger container){
		super(container);
		this.contianer = container;
		
		this.xSize = container.xSize;
		this.ySize = container.ySize;
		
		
	}
	
	@Override
	public void initGui(){
		super.initGui();
		this.allowUserInput = true;
		
		instructions = new GuiTextField(fontRendererObj, this.guiLeft + 25, this.guiTop, 200, 15);
		instructions.setCanLoseFocus(true);
		
	}
	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3){
		super.drawGuiContainerBackgroundLayer(var1, var2, var3);
		GL11.glColor4f(1, 1, 1, 1);
		
		instructions.drawTextBox();
		
		/*String[] slotNames = {"x min","x max","y min","y max","z min","z max","writen book with an instruction on each page"};
		int index = 0;
		for(String s : slotNames){
			this.fontRendererObj.drawStringWithShadow(s, 135, 10 + SLOT_SIZE*index++, 0xffffff);
		}
		this.fontRendererObj.drawStringWithShadow("use iron ingots to increase range", 180, 10, 0xffffff);
		this.fontRendererObj.drawStringWithShadow("example instructions:", 180, 50, 0xffffff);
		this.fontRendererObj.drawStringWithShadow("diamond pickaxe,stone pickaxe : stone, iron ore", 180, 60, 0xffffff);
		this.fontRendererObj.drawStringWithShadow("item.strip-miner-pick.name : stone", 180, 70, 0xffffff);
		this.fontRendererObj.drawStringWithShadow("everything else goes in adject inventories", 180, 100, 0xffffff);*/
	}
	
	
	
	
	
}
