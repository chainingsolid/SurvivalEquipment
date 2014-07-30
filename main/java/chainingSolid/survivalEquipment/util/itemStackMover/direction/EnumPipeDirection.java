package chainingSolid.survivalEquipment.util.itemStackMover.direction;

import chainingSolid.survivalEquipment.util.EnumAxis;

public enum EnumPipeDirection {
	
	NORTH(0, 0, 0, "North"),
	SOUTH(0,0,1,"South"),
	EAST(1,0,0,"East"),
	WEST(0,0,0,"West"),
	TOP(0,1,0,"Top"),
	BOTTOM(0,0,0,"Bottom")
	;
	
	public final int X_OFFSET, Y_OFFSET, Z_OFFSET;
	
	public final String DIRECTION_NAME;
	
	private EnumPipeDirection(int xOffSet, int yOffSet,int zOffSet, String name){
		X_OFFSET = xOffSet;
		Y_OFFSET = yOffSet;
		Z_OFFSET = zOffSet;
		DIRECTION_NAME = name;
	}
	
	
}
