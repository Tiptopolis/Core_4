package com.uchump.prime.TESTS.Raymarching.UselesssNonsense;

import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;

public interface iSDFx {


	public float dst(aVector reference, aVector center, iSDFx type);

	public aVector center();

	public iSDFx center(aVector v);

	public <T extends iSDFx> Class<T> type();

}
