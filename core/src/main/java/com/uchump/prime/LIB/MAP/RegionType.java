package com.uchump.prime.LIB.MAP;

import java.util.ArrayList;

import com.uchump.prime._PRIME.METATRON.M.File.A_I.iType;


public enum RegionType{
	// the way this map-layer/map-object interprets vector coordinates

	// in _ABS, the MapObjects are the Cells, lol
	INT_ABS, //O-O
	//0-Size UV region
	//screen pixels
	
	FLOAT_ABS, //O<->O
	//free-point movement & placement
	//WC3-type unit movement, mapped to an Int-Cell Layer underneath

	INT_CELL, //[]-[]
	// represents a grid of static geometric cells; Grid-based placement & pathing
	// cell's location is 'bottom left'
	//standard RTS placement & pathing

	FLOAT_CELL; //
	// represents a lattice of dynamic geometric cells, cell location is center
	//spline road/wall networks, medievalLords Corn-fields
	
	public static ArrayList<RegionType> All;

	private RegionType() {
		reg(this);
	}

	private static void reg(RegionType r) {
		if (All == null)
			All = new ArrayList<RegionType>();
		
		All.add(r);

	}



}
