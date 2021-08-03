package com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Geometry;


import java.util.ArrayList;

import com.badlogic.gdx.utils.Array;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aMatrix;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Matrix4;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector3;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector4;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public class Transform extends aMatrix{

	//a Labled Vector
	//in-stream of numbers, get first 3, check that they are vectors,
	//if (qNode.define(inputVects[2]) != null);
	//public gdxTransform t;
	private static final String TAG = "Transform";
	public static final ArrayList<Transform> All = new ArrayList<Transform>();
	
	protected Vector3 			_localPosition;
	protected Vector4 			_localRotation;
	protected Vector3 			_localScale;
	
	protected Transform 			_parent;
	protected Array<Transform> 	_children;
	protected Matrix4 			_localToWorldMatrix;
	
	protected int depth = -1;
	protected Vector3 _localDirection;
	protected Vector3 _localUp;
	protected Vector3 _localRight;
	
	public uTransform forReference;
	
	public Transform()
	{
		this._localPosition = new Vector3();
		this._localRotation = new Vector4();
		this._localScale = new Vector3();
		
	}
	
	public Transform(com.badlogic.gdx.math.Vector3 position,com.badlogic.gdx.math.Quaternion rotation,com.badlogic.gdx.math.Vector3 scale)
	{
		this._localPosition = new Vector3(position);
		this._localRotation = new Vector4(0,0,-1,0);
		this._localScale = new Vector3(1,1,1);
		this._localUp = new Vector3(0,1,0);
		this.UpdateMatricies();
		
	}
	

	
	public Transform(Vector3 position,Vector4 rotation,Vector3 scale)
	{
		this._localPosition = position.cpy();
		this._localRotation = rotation.cpy();
		this._localScale = scale.cpy();
		
		
		this.UpdateMatricies();
	}
	
	public Transform(uTransform trns)
	{
		this._localPosition = new Vector3(trns.GetPosition());
		this._localRotation = new Vector4(trns.GetRotation());
		this._localScale = new Vector3(trns.GetScale());
		this.UpdateMatricies();
	}
	
	protected void UpdateMatricies()
	{
		_localToWorldMatrix =  new Matrix4(_localPosition, _localRotation, _localScale); 
		
		if(_parent != null)
		{
			_localToWorldMatrix = (Matrix4) new Matrix4(_parent._localToWorldMatrix).mul(_localToWorldMatrix);
		}
		
		for(Transform child: _children)
		{
			child.UpdateMatricies();
		}
	}
	
}
