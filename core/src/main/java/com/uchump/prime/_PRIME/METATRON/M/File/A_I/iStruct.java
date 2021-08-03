package com.uchump.prime._PRIME.METATRON.M.File.A_I;

import java.util.Collection;

import javax.swing.text.AttributeSet;

import com.uchump.prime._PRIME.METATRON.Agents.A_I.iProperty;



public interface iStruct extends iType, AttributeSet{
	
	
	public Collection<iProperty> getProperties();
	
	public  iStruct copyTo(Object newOwner);
}
