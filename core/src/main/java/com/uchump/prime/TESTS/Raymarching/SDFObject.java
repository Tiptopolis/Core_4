package com.uchump.prime.TESTS.Raymarching;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

import com.uchump.prime.CORE.UI.Camera.OrthoController;
import com.uchump.prime._PRIME.METATRON.Metatron;
import com.uchump.prime._PRIME.METATRON.M.Boot.oLF_Renderer_3M;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME.METATRON.M.File.OroborosList;
import com.uchump.prime._PRIME.METATRON.M.File.TimeKey;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector3;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector4;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

public class SDFObject extends BoundShape implements iMonad , iSDF{
	protected Metatron M;
	protected OrthoController C;
	public iSDF Collider;

	public boolean lensUp = true;
	

	int mem;
	public int clr;
	
	Vector3 pos;
	Vector4 rot;
	Vector3 siz;

	public SDFObject(Vector3 at, iSDF type) {		
		pos = new Vector3(this.position);
		rot = new Vector4(this.rotation);
		siz = new Vector3(this.size.cpy().add(MathUtils.PI));
		this.Collider = type;
		this.vertexNum = 1;
		this.vertexSize = 6;
		this.rebind(at.gdxV3());
	}

	public void update() {

		float fTheta = 0;
		float iTheta = 0;

		float deltaTime = M.DeltaTime.I.floatValue();

		float i = M.iTime.I.floatValue();
		float m = M.Metranome.I.floatValue();

		fTheta = M.RealSecond.I.floatValue();
		iTheta = 1;

		// Log(i + " : " + fTheta + " @ " + deltaTime);

	}
	
	public float dst(aVector from)
	{
		Log(""+this.Collider.getType());
		return this.Collider.dst(from, new Vector3(this.position), new uVector().gdx(this.size));
		
	}

	////////////////////
	///////////////////
	

	private void render(Vector3 md) {

		float deltaTime = M.DeltaTime.I.floatValue();
		this.rebind(md.gdxV3());
	}

	public void clear() {

	}

	//////////

	@Override
	public void transformUpdated() {
		this.rebind();
	}

	@Override
	public String toLog() {

		return this.toLog();
	}

	public static float cInner(Number N) {

		return N.floatValue() % 180;
	}

	public static float cOutter(Number N) {

		return N.floatValue() % 360;
	}

	@Override
	public <T extends Number> float dst(aVector pReference, aVector center, T size) {		
		return this.Collider.dst(pReference, center, size);
	}

	@Override
	public <T extends iSDF> Class<T> getType() {
		return (Class<T>) this.Collider.getClass();
	}

	@Override
	public String TypeName() {
		return this.Collider.TypeName();
	}

}
