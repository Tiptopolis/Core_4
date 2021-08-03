package com.uchump.prime._PRIME._coPRIME.N_M.Prototype;

import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aMatrix;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;

public class Matrix4 extends uMatrix{

	
	public Matrix4()
	{
		super(4);
	}
	
	public Matrix4(aMatrix m)
	{
		super(m);
	}
	
	public Matrix4(com.badlogic.gdx.math.Matrix4 m)
	{
		super(m);
	}
	
	public Matrix4(aVector v)
	{
		super(v);
	}
	
	
	
	public Matrix4(Vector3 p, Vector4 r, Vector3 s)
	{
		
	}
	

	
	
	public com.badlogic.gdx.math.Matrix4 Gdx()
	{
		Number[] Ns = (Number[]) this.valueArray();
		
		return new com.badlogic.gdx.math.Matrix4();
	}
}
