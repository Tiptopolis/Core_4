package com.uchump.prime.LIB.MAP;

import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.LIB.MAP.A_I.aMap;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aMatrix;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

public class uMapGenerator {

	
	
	public uMapGenerator()
	{
		
	}
	
	public static aMap newMap(Number size)
	{
		
		
		
		uMapData data = new uMapData();
		aMap newMap = new aMap(data);
		
		return newMap;
	}
	
	public static aMap fromMatrix(aMatrix M)
	{
		
		
		
		return null;
		
	}
	
}
