package com.uchump.prime.LIB;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.METATRON.M.File.OroborosList;
import com.uchump.prime._PRIME._coPRIME.META.iCypher;
import com.uchump.prime._PRIME._coPRIME.N_M.Maths;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aMatrix;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector4;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.eNode;

public class eMatrix<T extends Object> extends eNode<eMatrix<Object>> {

	public int Dimensions;
	public ArrayList<uVector> AddressIndex;
	public HashMap<uVector, Object> AddressMatrix;
	public OroborosList<T> ValueIndex;
	
	public eMatrix(Vector3 size)
	{
		
		this.Dimensions = 3;
		this.ValueIndex = new OroborosList<T>(1,1,1);
		int D =0;
		for(int x=0; x < size.x; x++)
			for(int y=0; y < size.y; y++)
				for(int z=0; z < size.z; z++)
				{
					AddressIndex.add(new uVector(new Integer[] {x,y,z,D,(x+y+z+D)/Maths.factorial(this.Dimensions)}));
					if(D<this.Dimensions)
					{
						
						D++;
					}
					
					
				}
			
				
			
	}

	public eMatrix(aMatrix M) {
		this.Dimensions = M.i().intValue();
		
			

		this.AddressIndex = new ArrayList<uVector>();
		for(int i = 0; i < this.AddressIndex.size(); i++){
			
		
		}
			
	}

	public eMatrix(Vector4 size) {
		this.Dimensions = 4;
		
		this.AddressIndex.ensureCapacity((int) (size.x*size.y*size.z));		

	}

	public eMatrix(Matrix3 input) {
		this.Dimensions = 3;
	}

	public eMatrix(Matrix4 input) {
		this.Dimensions = 4;
	}

	public String toLog()
	{
		String log = "";
		
		
		return log;
	}
	
}
