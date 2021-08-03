package com.uchump.prime.CORE.Device;

import java.util.Map;

import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime._PRIME.METATRON.M.File.A_I.iStruct;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;

public interface iGraph<N,O> extends Map<N,O>, iSpace, iMonad, iStruct{

	@Override
	default String getName() {
		return this.getClass().getSimpleName();
	}

	
	
}
