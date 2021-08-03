package com.uchump.prime.LIB.MAP;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime.LIB.Dex;
import com.uchump.prime._PRIME.METATRON.M.File.A_I.iType;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public class aWorld implements iSpace {
//Vector3 direction = new Vector3(1, 0, 0).rot(modelInstance.tranform);
	// one MapLayer for each type, can be toggled on/off

	// Defines the "Inner" Dexes of a MetaSpace

	public ArrayList<iSpace> SubSpaces = new ArrayList<iSpace>();
	public HashMap<_N<iType>, Dex> Types; 
	
	@Override
	public Vector3 getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector3 getUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public uTransform metric() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

}
