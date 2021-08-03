package com.uchump.prime._PRIME._coPRIME.S_S.Nodex;

import java.io.Serializable;

import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.Element;

import com.badlogic.ashley.core.Component;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aNode;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

public class eNode<T extends Object> extends aNode<eNode, Object,  T> implements _N, Element, Component {

	// elemental existential of object of type T;
	public _N name;
	public eNode root;
	public T element;
	
	public int depth;
	public float rootDst;
	
	protected char[] Labels = new char[1];

	public eNode() {
		super();
		this.root=this;
		
		
	}

	public eNode(String name) {
		super();
		this.I=this;
		this.K = (T) new Name("NULL");
		this.V = name;
	}

	public eNode(T Object) {

		super(Object.toString(),Object);
		//this.Name = Object.toString();
		this.I=this;
		this.K = Object;
		this.V = Object;
	}

	@Override
	public T getKey()
	{
		return this.element;
	}
	
	@Override
	public Document getDocument() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getParentElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public AttributeSet getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStartOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getEndOffset() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getElementIndex(int offset) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getElementCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Element getElement(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getDepth()
	{
		return this.depth;
	}
	
	@Override
	public float getRootDist()
	{
		return this.rootDst;
	}
	
	@Override
	public String toLog() {
		String log = "<" + this.getName()+
				">|<"+this.Name().getClass().getSimpleName()+
				">+<"+this.Symbol().getClass().getSimpleName()+
				">+<"+this.Index().getClass().getSimpleName()+":"+this.Index().getValue()+">";
		log+="\n["+this.getDepth()+"]{"+this.Labels.length+"}\n";
		if(this.I!=null)
			log += "I([" + this.I + "]<" + this.I.getClass().getSimpleName() + ">\n|\n";
		if (this.K != null)
			log += "K([" + this.K + "]<" + this.K.getClass().getSimpleName() + ">\n&\n";
		if (this.V != null)
			log += "V[" + this.V + "]<" + this.V.getClass().getSimpleName() + ">)\n" + this.getRootDist()+"\n";
		
		log+="\n";
		log+="#"+Labels.length;
		log+="\n";

		return log;
	}

	

}
