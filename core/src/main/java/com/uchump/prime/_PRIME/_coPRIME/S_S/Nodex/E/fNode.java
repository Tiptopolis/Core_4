package com.uchump.prime._PRIME._coPRIME.S_S.Nodex.E;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.text.AttributeSet;

import com.uchump.prime._PRIME.METATRON.Agents.iAgent;
import com.uchump.prime._PRIME.METATRON.Agents.iControlGroup;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector3;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.bNode;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.eNode;

public class fNode extends eNode<bNode<fNode,Object>> implements AttributeSet, Serializable, iControlGroup{
	//function, file, form
	
	public ArrayList<fNode> Connections;
	
	
	public fNode() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public iAgent getSource() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getAttributeCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public boolean isDefined(Object attrName) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isEqual(AttributeSet attr) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public AttributeSet copyAttributes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object getAttribute(Object key) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Enumeration<?> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean containsAttribute(Object name, Object value) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean containsAttributes(AttributeSet attributes) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public AttributeSet getResolveParent() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
