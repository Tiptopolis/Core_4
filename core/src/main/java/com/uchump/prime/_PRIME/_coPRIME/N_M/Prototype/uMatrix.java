package com.uchump.prime._PRIME._coPRIME.N_M.Prototype;

import java.util.ArrayList;

import com.uchump.prime._PRIME._coPRIME.N_M.NumberUtils;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aMatrix;

public class uMatrix extends aMatrix<uMatrix> {

	public uMatrix() {
		super();
	}

	public uMatrix(Number n)
	{
		super(n);
	}
	
	public uMatrix(Number n,Number m)
	{
		super(n,m);
	}
	
	public uMatrix(com.badlogic.gdx.math.Matrix4 M4)
	{
		super(NumberUtils.resolveFA(M4.getValues()));
		
		
	}
	
	
}
