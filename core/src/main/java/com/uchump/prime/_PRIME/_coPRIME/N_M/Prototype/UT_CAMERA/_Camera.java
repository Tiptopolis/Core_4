package com.uchump.prime._PRIME._coPRIME.N_M.Prototype.UT_CAMERA;

import com.badlogic.gdx.graphics.Camera;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Matrix4;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;
import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.bNode;

import com.uchump.prime._PRIME._coPRIME.S_S.Nodex.E.cNode;

public class _Camera extends bNode<cNode,_Camera> {

	public Camera forRef;

	/** the projection matrix **/
	public final Matrix4 _projection = new Matrix4();
	/** the view matrix **/
	public final Matrix4 _view = new Matrix4();
	/** the combined projection and view matrix **/
	public final Matrix4 _combined = new Matrix4();
	/** the inverse combined projection and view matrix **/
	public final Matrix4 _invProjectionView = new Matrix4();

	public _Camera() {
		super();
		
	}

	@Override
	public uTransform getTransform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transformUpdated() {
		// TODO Auto-generated method stub

	}

}
