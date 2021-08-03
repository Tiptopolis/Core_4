package com.uchump.prime.CORE.UI.Camera;

import com.uchump.prime.CORE.UI.Camera.A_I.aCameraController;
import com.uchump.prime._PRIME.METATRON.Agents.iAgent;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public class uCameraController extends aCameraController{

	public uCameraController(iMonad root) {
		super(root);
		
	}
	
	public uCameraController(iMonad root,String name) {
		super(root, name);
	}

	public uCameraController setTransform(uTransform t)
	{
		this.transform = t;
		return this;
	}


	
	
}
