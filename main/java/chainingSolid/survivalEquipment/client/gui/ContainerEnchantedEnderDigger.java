package chainingSolid.survivalEquipment.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerTile;
import chainingSolid.survivalEquipment.util.GenericInventory;
import chainingSolid.survivalEquipment.util.SlotThatTakesInToAccountIsItemVaildForSlot;
import chainingSolid.survivalEquipment.util.enchantedEnderDigger.EnchantedEnderDiggerWhiteListRow;


public class ContainerEnchantedEnderDigger extends SurvivalEquipmentContainer {
	
	protected int xSize = 256;
	protected int ySize = 240;
	
	protected final int SLOT_SIZE = 20;
	
	EnchantedEnderDiggerTile diggerTile;
	
	EntityPlayer player;
	
	public ContainerEnchantedEnderDigger(EnchantedEnderDiggerTile diggerTile, EntityPlayer player){
		super(player);
		this.diggerTile = diggerTile;
		this.player = player;
		//this.addRangeSlots();
		this.setPlayerInvPosition(0, SLOT_SIZE*7);
		this.buildContianer();
	}
	
	private void addRangeSlots() {
		for(int slot = 0; slot < diggerTile.getSizeInventory();slot++){
			this.addSlotToContainer(new Slot(diggerTile,slot,0,slot*SLOT_SIZE));
		}
	}
	
	private void addPlayerSlots(EntityPlayer player){
		InventoryPlayer playerInv = player.inventory;
		int index = 9;
		for(int y = 0; y < 3;y++){
			for(int x = 0; x < 9;x++){
				this.addSlotToContainer(new Slot(playerInv,index++,x*SLOT_SIZE,y*SLOT_SIZE + 20*7));
			}
		}
		index = 0;
		for(int y = 0; y < 1;y++){
			for(int x = 0; x < 9;x++){
				this.addSlotToContainer(new Slot(playerInv,index++,x*SLOT_SIZE,y*SLOT_SIZE + 20*10));
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2){
		return null;
	}
}
