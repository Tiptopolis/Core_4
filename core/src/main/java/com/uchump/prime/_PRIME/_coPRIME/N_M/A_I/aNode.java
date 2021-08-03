package com.uchump.prime._PRIME._coPRIME.N_M.A_I;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.badlogic.ashley.core.Component;
import com.uchump.prime.CORE.Device.iGraph;
import com.uchump.prime.CORE.Device.Struct.LinkedList.Node;
import com.uchump.prime.CORE.Device.Struct.aTree;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime._PRIME.METATRON.Agents.iControlAgent;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uNumber;

public abstract class aNode<N extends aNode, V, O extends Object> extends Node<Object> implements iNode<N, V, O>, Component, iMonad, Serializable {

	public boolean exists = false;
	protected _N Index;
	public iSpace Of;

	public N I;
	public O K;
	public V V;


	public HashMap<aNode, String> Links = new HashMap<aNode, String>();
	

	
	public aNode() {
		this.exists = true;
		this.I = (N) this;
	
		this.exists = true;
	}

	public aNode(iGraph of, Object value) {
		this.exists = true;
		this.I = (N) this;		
		of.put(this, value);
		this.exists();

	}
	
	

	public aNode(V value, O of) {
		this.exists = true;
		this.I = (N) this;
		this.K = of;
		this.V = value;
			
		
	}

	@Override
	public boolean exists() {
		
		return this.exists;
	}
	
	@Override
	public boolean exists(boolean f)
	{
		this.exists=f;
		this.exists();
		return f;
	}

	@Override
	public iSpace getGraph() {

		return this.Of;
	}

	@Override
	public boolean getExpanded() {
		if (this.exists)
			return true;
		else
			return false;
	}

	@Override
	public void setExpanded(boolean is) {
		this.exists(is);
	}
	
	@Override
	public aNode connectTo(N other)
	{
		if(other.exists())
		{
			this.Links.put(other, other.getName());
		}
		return other;		
	}

	@Override
	public uTransform getTransform() {
		if (this.exists) {
			uTransform t = new uTransform(this);
			return t;
		}
		return new uTransform();
	}

	@Override
	public void transformUpdated() {

	}

	@Override
	public boolean equals(Object other) {
		if(other == this)
			return true;
		
		if(other==this.I || other == this.K || other == this.V)
		return true;
		
		if (!(other instanceof iNode))
			return false;
		else {
			iNode N = (iNode) other;
					
			
			return N.Index().equals(this.Index);
		}

	}
	


	@Override
	public String toString() {
		return "<"+this.getClass().getSimpleName()+">";
	}

	@Override
	public String toLog() {
		String log = "<" + this.getName() + ">";
		log+="\n"+"_"+"["+this.getDepth()+"]\n";

		if(this.I!=null)
			log += "I([" + this.I + "]<" + this.I.getClass().getSimpleName() + ">\n|\n";
		if (this.K != null)
			log += "K([" + this.K + "]<" + this.K.getClass().getSimpleName() + ">\n&\n";
		if (this.V != null)
			log += "V[" + this.V + "]<" + this.V.getClass().getSimpleName() + ">)\n" + this.getRootDist()+"\n";

		return log;
	}

	@Override
	public Object as(_N n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getBits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object setValue(Object value) {
		this.V = (V) value;
		return null;
	}

	@Override
	public O getValue() {
		return this.K;
	}

	@Override
	public V getKey() {
		return this.V;
	}

	@Override
	public aNode Name() {
		return this;
	}

	@Override
	public _N Index() {
		
		return this;
	}

	@Override
	public String Symbol() {
		return this.toString();
	}
	



	@Override
	public _N new_N(_N index, Object name, String symbol) {
		// TODO Auto-generated method stub
		return null;
	}

}
