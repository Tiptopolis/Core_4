package com.uchump.prime.CORE.Device.ECS;

import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime.CORE.UI.Events.aEventManager;
import com.uchump.prime._PRIME.METATRON.Signals.Signal;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public class aSystem extends aEventManager implements iSpace{
	
	//Size is TypeSpace
	//Each system has its own componentType registry & associated mappers
	//Every Component has a ComponentMapper associated with it
	
	public aSystem()
	{
		
	}

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
	
}
