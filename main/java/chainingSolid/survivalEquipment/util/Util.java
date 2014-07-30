package chainingSolid.survivalEquipment.util;

import java.util.ArrayList;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;


/***
 * general catch all util class
*/
public class Util {
	
	
	public static int[][] sideRelativeCordList = new int[][]{
			{1,0,0},//east
			{0,1,0},//top
			{0,0,1},//south
			{-1,0,0},//west
			{0,-1,0},//bottom
			{0,0,-1}//north
	};
	
	
	public final static int I_SIDED_INVENTORY_SIDE_BOTTOM_ID = 0,
					I_SIDED_INVENTORY_SIDE_TOP_ID = 1,
					I_SIDED_INVENTORY_SIDE_NORTH_ID = 2,
					I_SIDED_INVENTORY_SIDE_SOUTH_ID = 3,
					I_SIDED_INVENTORY_SIDE_WEST_ID = 4,
					I_SIDED_INVENTORY_SIDE_EAST_ID = 5
					;
	
	
	public static ItemStack[] findStacksInInventory(IInventory inv, Item items){
		ArrayList<ItemStack> stacksWithItem = new ArrayList<ItemStack>();
		for(int index = 0; index < inv.getSizeInventory();index++){
			ItemStack stcak = inv.getStackInSlot(index);
			
			
		}
		return stacksWithItem.toArray(new ItemStack[1]);
	}
	
	public static double getDistance(int x1, int y1,int z1,
								int x2,int y2,int z2){
		return Math.sqrt((Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2)+Math.pow(z2-z1, 2)));
	}
	
	public static void nullNBTTagCheck(ItemStack stack){
		if(stack.getTagCompound() == null){
			stack.setTagCompound(new NBTTagCompound());
		}
	}
	
	public static boolean isValueInRange(int value, int rangeUpper, int rangeLower){
		return (value < rangeUpper && value > rangeLower);
	}
	
	public static boolean isIntInArray(int value,int[] array){
		for(int arrayValue : array){
			if(value == arrayValue){
				return true;
			}
		}
		return false;
	}
	
	public static ItemStack isTheirItemInPlayerInv(EntityPlayer player, Item toCompare){
		InventoryPlayer playerInv = player.inventory;
		for(int index = 0;index < playerInv.getSizeInventory();index++){
			ItemStack stackInInv = playerInv.getStackInSlot(index);
			if(stackInInv.getItem() == toCompare){
				return stackInInv;
			}
		}
		return null;
		
	}
	
	public static void enterDebug(){
		System.out.println("debug");
	}
	
	public static void putItemStackInWorld(World world, double x,double y,double z,ItemStack stack){
		ItemStack newStack = new ItemStack(stack.getItem(), 1, stack.getItem().getDamage(stack));//this how vanilla does it so it actually works
		newStack.stackTagCompound = stack.stackTagCompound;//this is a hack so the bag keeps its inventory
		EntityItem e = new EntityItem(world, x,y, z, newStack);
		if(!world.isRemote){
			world.spawnEntityInWorld(e);
		}
	}
	
	
	public static int chunkCordToWorldCord(int chunk){
		int value = chunk * 16;
		if(value < 0){
			value++;
		}
		return value;
	}
	
	public static boolean isCordInWorldBounds(int x ,int y,int z){
		if(y < 256 && y >=0){
			return true;
		}else{
			return false;
		}
	}
}
