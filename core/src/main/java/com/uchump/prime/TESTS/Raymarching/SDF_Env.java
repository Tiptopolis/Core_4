package com.uchump.prime.TESTS.Raymarching;

import java.util.ArrayList;
import java.util.HashMap;

import com.uchump.prime.TESTS.Raymarching.UselesssNonsense.SDF_BS.SDF;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector3;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public class SDF_Env {

	public static ArrayList<SDFObject> _All = new ArrayList<SDFObject>();
	public static HashMap<Integer, iSDF> Index_All = new HashMap<Integer, iSDF>();

	//link to space, set primary caster
	public static uTransform Caster;
	
	public static float maxDst = 300;
	
	public static void setCaster(uTransform c)
	{
		Caster = c;
	}
	
	
	public static void addNewSDF(SDFObject s, iSDF type)
	{
		_All.add(s);
		Index_All.put(_All.indexOf(s), type);
		
		
	}
	
}
