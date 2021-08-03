package com.uchump.prime._PRIME._coPRIME.S_S.Nodex;

import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aNode;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uNumber;

public class oNode extends aNode<eNode<Object>,_N,_N>{

	
	
	public oNode()
	{
		this.I=new eNode(this);
		this.K = new Name("OBJECT");
		this.V = new uNumber(0);
	}
}
