package com.uchump.prime._PRIME._coPRIME.S_S.Nodex.E;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.math.Quaternion;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.eNode;

//extends Vector3?
public class qNode extends eNode<Quaternion> {

	// temporary quaternion node;

	//protected char[] Labels = new char[4];
	protected boolean temporary = true;

	//refreshes upon observation lol;
	
	public qNode() {
		super();
		this.K = new Quaternion();		
		this.Index=new uVector(new Float[4]);
		this.V = this.Index;
		Labels = new char[] {'x','y','z','w'};
		this.label(this.Labels);
	}

	
	
	public qNode(char[] lbls) {
		this.I = this;
		this.K = new Quaternion();
		this.Index=new uVector(new Float[4]);
		this.V = this.Index;

		this.label(lbls);
	}
	
	
	

	private void label(char[] lbls) {
		for (int i = 0; i < Labels.length - 1; i++) {
			this.Labels[i] = lbls[i];
		}
		((uVector)this.V).label(lbls,true);
	}
	
	

	@Override
	public Quaternion as(_N n) {
		// if _N meets certain criteria, return a new Quaternion

		return this.K;
	}
	
	@Override
	public _N Index() {
		
		return this.Index;
	}

	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getBits() {
		// elements
		return ((uVector)this.V).elements.toArray();
	}
	
	@Override
	public String toString()
	{
		this.exists(false);		
		return super.toString();
	}
	
	@Override
	public String getName()
	{
		
		this.exists(false);
		Log("*qPOOF!");
		return super.getName();
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
		log+="#"+((uVector)this.V).labels.size();
		log+="\n";

		return log;
	}

}
