package com.uchump.prime.TESTS.Raymarching;

import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;

public class SDF implements iSDF {

	public String type = "";
	public aVector Center;
	public iSDF Type;

	public SDF(aVector center, iSDF type) {
		this.Center = center;
		this.Type = type;
	}


	@Override
	public <T extends iSDF> Class<T> getType() {
		return  (Class<T>) this.Type.getClass();
	}


	@Override
	public <T extends Number> float dst(aVector pReference, aVector center, T size) {
		return this.Type.dst(pReference, center, size);
	}


	@Override
	public String TypeName() {
		// TODO Auto-generated method stub
		return this.Type.TypeName();
	}

}
