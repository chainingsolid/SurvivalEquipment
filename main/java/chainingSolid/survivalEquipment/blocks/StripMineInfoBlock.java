package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.Config;
import chainingSolid.survivalEquipment.loading.loadable.Loadable;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class StripMineInfoBlock extends Block implements LoadableBlock{
	
	private final int bottom = 0,top = 1,north = 2, east = 5, west = 4,south = 3;
	
	public StripMineInfoBlock(Material par2Material) {
		super(par2Material);
		this.setHardness(1F);
		this.textureName= SurvivalEquipment.MODRESOURCEPATH+"stripmineinfoblock";
	}
	
	boolean toBig;
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk0, float idk1, float idk2, float idk3){
		if(!world.isRemote){
			boolean shouldSend = true;
			toBig = false;
			int dist = -2;
			ChatComponentStyle message = new ChatComponentText("");
			
			switch(idk0){
				case north:
					dist = findDistance(player,world,0,-1,x,y,z);
					message.appendText("Distance north is "+dist);
					break;
				case south:
					dist = findDistance(player,world,0,1,x,y,z);
					message.appendText("Distance south is "+dist);
					break;
				case west:
					dist = findDistance(player,world,-1,0,x,y,z);
					message.appendText("Distance west is "+dist);
					break;
				case east:
					dist = findDistance(player,world,1,0,x,y,z);
					message.appendText("Distance east is "+dist);
					break;
				default:
					shouldSend = false;
					break;
			}
			if(toBig){
				message = new ChatComponentText("Distance is over limit: "+ Config.stripMineInfoBlockRange+" this is editable in the config");
			}
			if(shouldSend){
				player.addChatMessage(message);
			}
			return true;
		}
		return false;
	}
	
	boolean found;
	int distance;
	Block blockFound;
	private int findDistance(EntityPlayer playerToTell,World world,int directionX,int directionZ,int x, int y,int z){
		distance = 0;
		
		found = false;
		
		if(directionZ == 0){
			for(int xx = x + directionX;!found;xx = xx + directionX){
				blockFound = world.getBlock(xx, y, z);
				check(world , xx , y ,z);
			}
		}else{
			for(int zz = z + directionZ;!found;zz = zz + directionZ){
				blockFound = world.getBlock(x, y, zz);
				check(world , x , y , zz);
			}
		}
		
		return distance;
	}
	
	private void check(World world, int x , int y, int z) {
		if(!blockFound.isOpaqueCube()){
			distance++;
			if(distance > Config.stripMineInfoBlockRange){
				found = true;
				toBig = true;
			}
		}else{
			found = true;
		}
		
	}
	
	@Override
	public String getIdentifier() {
		return "strip_mine_info_block";
	}
	
	@Override
	public String getUnlocalizedName(){
		return this.getIdentifier();
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
}
