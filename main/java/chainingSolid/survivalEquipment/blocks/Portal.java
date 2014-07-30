package chainingSolid.survivalEquipment.blocks;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.dimension.TeleporterLostLand;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import chainingSolid.survivalEquipment.tileEnties.LostLandPortalTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Portal extends BlockContainer implements LoadableBlock{
	
	public Portal(Material material) {
		super(material);
		this.setLightOpacity(0);
		this.setLightLevel(1F);
		
	}
	
	
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk0, float idk1, float idk2, float idk3){
		if(world.isRemote){
			return false;
		}else{
			if(player instanceof EntityPlayerMP){
				
				EntityPlayerMP playerMP = (EntityPlayerMP) player;
				
				if(playerMP.dimension == 0){
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, SurvivalEquipment.dimid,
							new TeleporterLostLand(playerMP.mcServer.worldServerForDimension(SurvivalEquipment.dimid)));
					return true;
				}
				if(playerMP.dimension == SurvivalEquipment.dimid){
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 0,
							new TeleporterLostLand(playerMP.mcServer.worldServerForDimension(SurvivalEquipment.dimid)));
					return true;
				}
				
				
			}
			
			return true;
		}
		
	}



	@Override
	public String getIdentifier() {
		return "portal_lost_land";
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new LostLandPortalTile();
	}
	
	public static int renderId = RenderingRegistry.getNextAvailableRenderId();
	
	@Override
	public int getRenderType() {
		return renderId;
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
}
