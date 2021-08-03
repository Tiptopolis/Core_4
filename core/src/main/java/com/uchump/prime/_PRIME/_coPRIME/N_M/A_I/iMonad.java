package com.uchump.prime._PRIME._coPRIME.N_M.A_I;

import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public interface iMonad {
	
	public default boolean exists()
	{
		return true;
	}
	
	public default boolean exists(boolean f)
	{
		this.exists();
		return f;
	}
	
	public default String getName()
	{
		return this.getClass().getSimpleName();
	}
	
	public uTransform getTransform();
	void transformUpdated(); //informed by transform that it has updated
	
	public String toLog();
}
