package com.uchump.prime._PRIME.METATRON.Agents;

import com.uchump.prime._PRIME.METATRON.Signals.Listener;
import com.uchump.prime._PRIME.METATRON.Signals.Signal;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;

public interface iAgent extends iMonad{

	public iAgent getSource();
	
	public default Signal<?> signal()
	{
		return null;
	}
	
	public default Signal<?> signal(Signal s)
	{
		return s;
	}
	
}
