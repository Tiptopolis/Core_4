package com.uchump.prime.CORE.Device.Struct;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.uchump.prime.CORE.Device.iGraph;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iNode;

import java.util.Map.Entry;


public interface iTree<N extends iNode,V> extends iGraph<N,V>{

	public Set<Entry<N, V>> entrySet();
	
}
