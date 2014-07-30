package chainingSolid.survivalEquipment.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import chainingSolid.survivalEquipment.tileEnties.EnchantedEnderDiggerTile;
import chainingSolid.survivalEquipment.util.Util;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

public class EnchantedEnderDigger extends BlockContainer implements LoadableBlock{
	
	
	
	public EnchantedEnderDigger(Material par2Material) {
		super(par2Material);
		this.blockResistance=500;
		this.setHardness(4);
		this.setLightLevel(0F);
		this.textureName=SurvivalEquipment.MODRESOURCEPATH+"EED";
	}
	
	@Override
	public TileEntity createNewTileEntity(World world,int meta) {
		return new EnchantedEnderDiggerTile();
	}
	
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float idk0, float idk1, float idk2){
		if(world.isRemote){
			return false;
		}else{
			FMLNetworkHandler.openGui(player, SurvivalEquipment.instance, SurvivalEquipment.guiHandler.ENCHANTED_ENDER_DIGGER_ID, world, x, y, z);
			
		}
		
		return true;
	}
	
	public void randomDisplayTick(World world, int x, int y, int z, Random rand){
		TileEntity te = world.getTileEntity(x, y, z);
		if(te instanceof EnchantedEnderDiggerTile){
			EnchantedEnderDiggerTile diggerTile = (EnchantedEnderDiggerTile)te;
			for(int u = 0; u < 20;u++){
				world.spawnParticle("smoke", diggerTile.currentXOff+x +.5, diggerTile.currentYOff+y+.5, diggerTile.currentZOff+z+.5,0, 0, 0);
				world.spawnParticle("smoke", diggerTile.currentXOff+x+.5, diggerTile.currentYOff+y+.5, diggerTile.currentZOff+z+.5,0, 0, 0);
			}
			int[] xs = new int[]{
					diggerTile.xCoord+diggerTile.XOffMin,
					diggerTile.xCoord+diggerTile.XOffMax
					};
			int[] ys = new int[]{
					diggerTile.yCoord+diggerTile.YOffMin,
					diggerTile.yCoord+diggerTile.YOffMax
					};
			int[] zs = new int[]{
					diggerTile.zCoord+diggerTile.ZOffMin,
					diggerTile.zCoord+diggerTile.ZOffMax
					};
			
			for(int loopx : xs){
				for(int loopy : ys){
					for(int loopz : zs){
						for(int loopx2 : xs){
							for(int loopy2 : ys){
								for(int loopz2 : zs){
									this.drawParticleLine(world, loopx, loopy, loopz, loopx2, loopy2, loopz2);
								}
							}
						}
					}
				}
			}
		}
		
	}
	
	private void drawParticleLine(World world,int startx,int starty,int startz,int endx,int endy,int endz){
		int xmin = Math.min(startx, endx);
		int xmax = Math.max(startx, endx);
		int ymin = Math.min(starty, endy);
		int ymax = Math.max(starty, endy);
		int zmin = Math.min(startz, endz);
		int zmax = Math.max(startz, endz);
		
		int difx = xmax-xmin;
		int dify = ymax-ymin;
		int difz = zmax-zmin;
		
		
		
		try{
			int maxdif = Math.max(Math.abs(difx), Math.max(Math.abs(dify), Math.abs(difz)));
			if(maxdif == 0){
				return;
			}
			double increase = Math.pow(maxdif, -1);
			for(double dist=0;dist <= 1;dist += increase){
				world.spawnParticle("reddust", xmin+(dist*difx)+.5, ymin+(dist*dify)+.5, zmin+(dist*difz)+.5, 0, 0, 0);
			}
		}catch(ArithmeticException e){
			
		}
		
	}
	
	@Override
	public String getIdentifier() {
		return "enchanted_ender_digger";
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
