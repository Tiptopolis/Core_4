package com.uchump.prime._PRIME.METATRON.Agents.A_I;

import com.uchump.prime._PRIME.METATRON.M.File.A_I.iStruct;
import com.uchump.prime._PRIME._coPRIME.N_M._N;

public interface iProperty<T> extends iStruct {

	public String getName();
	public T getValue();
	
	public void setIndex(T index);
	public void setHeader(iProperty header);
	public boolean hasHeader();
	public boolean hasHeader(String name);
	public iProperty getHeader();
	
	@Override
	public iProperty copyTo(Object index);
	
	public default T getValue(String name) {
		if (this.getName().equals(name)) {
			return this.getValue();
		} else
			return null;
	}
	
	@Override
	public default _N getType()
	{
		return new Name(this.getValue().getClass().toString());
	}
	
}
