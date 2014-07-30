package chainingSolid.survivalEquipment.items.tools.livingTool;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import chainingSolid.survivalEquipment.loading.loadable.LoadableItem;
import chainingSolid.survivalEquipment.util.Util;
import chainingSolid.survivalEquipment.util.livingEquipment.ILivingEquipment;

public class LivingHoe extends ItemHoe implements LoadableItem, ILivingEquipment {
	
	public final String DAMADGE = "damadge",LEVEL = "level",CAN_REPIAR = "can_repair";
	public final int STARTING_DAMADGE = 10;
	
	public LivingHoe(ToolMaterial m) {
		super(m);
		this.setNoRepair();
	}
	
	@Override
	public String getIdentifier() {
		return "living_hoe";
	}
	
	@Override
	public Item getInstance() {
		return this;
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player){
		Util.nullNBTTagCheck(stack);
		stack.getTagCompound().setInteger(DAMADGE, 0);
		stack.getTagCompound().setInteger(LEVEL, 1);
		stack.getTagCompound().setBoolean(CAN_REPIAR, true);
	}
	
	@Override
	public int getDamage(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		return stack.getTagCompound().getInteger(DAMADGE);
	}
	
	@Override
	public int getMaxDamage(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		int level = stack.getTagCompound().getInteger(LEVEL);
		return level * 10 + this.STARTING_DAMADGE;
	}
	
	@Override
	public void setDamage(ItemStack stack, int damage){
		Util.nullNBTTagCheck(stack);
		stack.getTagCompound().setInteger(this.DAMADGE, damage);
		if(damage < 0 || damage > this.getMaxDamage(stack)){
			stack.stackSize = 0;
		}
	}
	
	@Override
	public boolean isDamaged(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		if(stack.getTagCompound().getInteger(DAMADGE) > 0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public int getDisplayDamage(ItemStack stack){
		Util.nullNBTTagCheck(stack);
		return stack.getTagCompound().getInteger(DAMADGE);
	}
	
	@Override
	public boolean canUpgrade(ItemStack stack) {
		Util.nullNBTTagCheck(stack);
		if(stack.getMaxDamage()*.5 > this.getDamage(stack) && stack.getTagCompound().getBoolean(CAN_REPIAR)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public int getLevel(ItemStack stack) {
		Util.nullNBTTagCheck(stack);
		return stack.getTagCompound().getInteger(LEVEL);
	}
	
	@Override
	public float getMaxLevel(ItemStack stack) {
		return 4;
	}
	
	@Override
	public float getUpgradeChance(ItemStack stack) {
		return .01F;
	}
	
	@Override
	public void levelUp(ItemStack stack) {
		Util.nullNBTTagCheck(stack);
		stack.getTagCompound().setInteger(LEVEL, stack.getTagCompound().getInteger(LEVEL)+1);
		stack.getTagCompound().setBoolean(CAN_REPIAR, false);
	}
	
	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta){
		Util.nullNBTTagCheck(stack);
		if(ForgeHooks.isToolEffective(stack, block, meta)){
			return stack.getTagCompound().getInteger(LEVEL)+1;
		}else{
			return 1;
		}
	}
}
