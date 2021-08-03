package com.uchump.prime.LIB.MAP;

import java.util.ArrayList;

import com.uchump.prime._PRIME.METATRON.M.File.A_I.iType;


public enum LayerType{
	LOCAL, // a specific area, a patch as it were	
	PATCH, // group of disconnected independent local-layers
	_SPACE, //a 'global layer' child object, like a panel's Global Layer would be a NameSpace layer 
	GLOBAL; // whole accessible map area

	public static ArrayList<LayerType> All;

	private LayerType() {
		reg(this);
	}

	private static void reg(LayerType r) {
		if (All == null)
			All = new ArrayList<LayerType>();
		
		All.add(r);

	}
}
