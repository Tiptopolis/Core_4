package com.uchump.prime._PRIME._coPRIME.N_M.A_I;

import java.util.Map;

public interface iSymbol<V> extends Map.Entry<V, Object>{
	//iValue, basic DataType interface, ties a Type to a Value Object
	
	
	
	
	public default  byte[] getBytes() {		
		int k =this.getKey().hashCode();
		int v = this.getValue().hashCode();		
		byte[]I = new byte[]{(byte) k,(byte) v};
		return I;
	}
	public default Object[] getBits() {
	return new Object[] {this.getBytes(),this.getKey(), this.getValue()};	
	}
	
}
