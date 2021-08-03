package com.uchump.prime._PRIME._coPRIME.N_M.Prototype;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.UnityTransform.Transform;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.UnityTransform.gdxTransform;

public class uTransform extends gdxTransform implements iMonad {

	public uTransform() {
		super();
		// All.add(this);
	}

	public uTransform(Vector3 position, Quaternion rotation, Vector3 scale) {
		super(position, rotation, scale);

	}

	public uTransform(iMonad of) {
		super();
		this.of = of;
		All.add(this);
	}

	public uTransform(iMonad of, Transform parent) {
		this(of);
		this.SetParent(parent);

	}

	public uTransform clone(iMonad to) {
		return this;
	}

	@Override
	public uTransform getTransform() {

		return this;
	}

	@Override
	public void transformUpdated() {
		// TODO Auto-generated method stub

	}

}
