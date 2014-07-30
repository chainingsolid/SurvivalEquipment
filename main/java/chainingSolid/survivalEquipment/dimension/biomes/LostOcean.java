package chainingSolid.survivalEquipment.dimension.biomes;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import chainingSolid.survivalEquipment.dimension.GenBox;

public class LostOcean extends LostLandBiome {
	
	public LostOcean(int id) {
		super(id);
		this.biomeName = "Lost ocean";
	}
	
	@Override
	public ArrayList<GenBox> genBoxes(int chunckX, int chunckZ) {
		ArrayList<GenBox> boxes = super.genBoxes(chunckX, chunckZ);
		
		GenBox water = new GenBox(Blocks.water, chunckX*16+1, 2, chunckZ*16+1, chunckX*16+16-1, 64, chunckZ*16+16-1);
		boxes.add(water);
		
		return boxes;
	}
	
}
