package com.uchump.prime._PRIME._coPRIME.S_S.Nodex.B;

import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aNode;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iNode;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.bNode;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.eNode;

public class lNode extends bNode<eNode,eNode> {
//link node, LAN
//binary link of 2 eNodes
//Simple geometry object: ax+by+c=0
	
	public aVector<Number> len; //a,b,c,...len
	
	public lNode()
	{
		this.len = new uVector(3);
		
	}
	
}
