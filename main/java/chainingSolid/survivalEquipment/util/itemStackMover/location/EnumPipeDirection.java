package chainingSolid.survivalEquipment.util.itemStackMover.location;

import chainingSolid.survivalEquipment.util.EnumAxis;

public enum EnumPipeDirection {
	
	NORTH(0, 0, -1, EnumAxis.Z, "North"),
	SOUTH(0,0,1,EnumAxis.Z,"South"),
	EAST(1,0,0,EnumAxis.X,"East"),
	WEST(-1,0,0,EnumAxis.X,"West"),
	TOP(0,1,0,EnumAxis.Y,"Top"),
	BOTTOM(0,-1,0,EnumAxis.Y,"Bottom")
	;
	
	public final int X_OFFSET, Y_OFFSET, Z_OFFSET;
	
	public final EnumAxis axis;
	
	public final String DIRECTION_NAME;
	
	private EnumPipeDirection(int xOffSet, int yOffSet,int zOffSet, EnumAxis axis, String name){
		X_OFFSET = xOffSet;
		Y_OFFSET = yOffSet;
		Z_OFFSET = zOffSet;
		this.axis = axis;
		DIRECTION_NAME = name;
	}
	
	
}
