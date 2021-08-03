package com.uchump.prime._PRIME._coPRIME.S_S.Nodex;

import static com.uchump.prime._PRIME.uAppUtils.*;

import com.badlogic.gdx.utils.Disposable;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aNode;

//PointerNode
public class bNode<A,B> extends eNode<Object> implements Disposable{

	 
	//now b node, BinaryPairOfObjectsOfType<A,B>
	public A A;
	public B B;
	public boolean C = false;

	protected bNode()
	{
		super();
		this.I=this;
		this.K = this.A;
		this.V = this.B;
		
	}
	
	public bNode(A a) //for assigning B later
	{
		super();
		this.I=this;
		this.K = this.A;
		this.V = this.B;
		this.A = a;	
	}
	
	public bNode(A a, B b)
	{
		super();
		this.I=this;
		this.K = this.A;
		this.V = this.B;
		this.A = a;
		this.B = b;
	
	}
	
	public A setA(A a)
	{
		this.A = a;
		this.K = this.A;
		return this.A;
	}
	
	public B setB(B b)
	{
		this.B = b;
		this.V = this.B;
		return this.B;
	}
	@Override
	public void dispose() {
		this.disconnect();
		this.Links.clear();
		if(this.A instanceof Disposable) {
		((Disposable)this.A).dispose();
		Log(" A " + A.getClass().getSimpleName());
		}
		
		if(this.B instanceof Disposable) {
			((Disposable)this.B).dispose();
			Log(" B " + B.getClass().getSimpleName());
		}
		this.A = null;
		this.B = null;
		
	}
	

	
	
}
