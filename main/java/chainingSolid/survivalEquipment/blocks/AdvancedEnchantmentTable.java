package chainingSolid.survivalEquipment.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import chainingSolid.survivalEquipment.loading.loadable.LoadableBlock;

public class AdvancedEnchantmentTable extends BlockEnchantmentTable implements LoadableBlock {
	
	@Override
	public String getIdentifier() {
		return "advanced_enchantment_table";
	}
	
	@Override
	public Block getInstance() {
		return this;
	}
	
	
}
