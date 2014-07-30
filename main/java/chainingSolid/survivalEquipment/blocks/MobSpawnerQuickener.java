package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.SurvivalEquipment;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;
import chainingSolid.survivalEquipment.tileEnties.MobSpawnerQuickenerTile;

public class MobSpawnerQuickener extends BlockContainer implements
		LoadableBlock {
	
	public MobSpawnerQuickener(Material m) {
		super(m);
		this.textureName = SurvivalEquipment.MODRESOURCEPATH+"clear";
	}
	
	@Override
	public String getIdentifier() {
		return "mob_spawner_quickener";
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new MobSpawnerQuickenerTile();
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
}
