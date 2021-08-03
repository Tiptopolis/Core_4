package com.uchump.prime.LIB.MAP.A_I;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uchump.prime.CORE.UI.Camera.A_I.aCamera;
import com.uchump.prime.CORE.UI.Camera.A_I.aCameraController;
import com.uchump.prime.CORE.UI.Rendering.Drawable.iDrawable;
import com.uchump.prime.CORE.UI.SceneGraph.uStage;
import com.uchump.prime.LIB.MAP.LayerType;
import com.uchump.prime.LIB.MAP.RegionType;
import com.uchump.prime.LIB.MAP.uMapData;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public class aMap extends uStage implements iDrawable{

	protected ArrayList<aCamera> viewing;

	//emulating how we can project Stage to World like that
	Camera mCam;
	Viewport mView;
	
	
	public HashMap<LayerType,aMapRegion> Layers;
	public HashMap<RegionType,aMapRegion> Regions;
	
	public aMap(uMapData data)
	{
		
	}




	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public uTransform getTransform() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void transformUpdated() {
		// TODO Auto-generated method stub
		
	}




	@Override
	public String toLog() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
