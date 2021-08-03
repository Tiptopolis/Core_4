package com.uchump.prime._PRIME._coPRIME.N_M.Prototype;

import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;

public class Vector3 extends uVector {

	public Vector3() {
		this(0, 0, 0);
	}

	public Vector3(float x, float y, float z) {
		super(new Float[] { x, y, z });
	}

	public Vector3(com.badlogic.gdx.math.Vector3 v) {
		super(new Float[] { v.x, v.y, v.z });
	}

	public com.badlogic.gdx.math.Vector3 Gdx() {
		return new com.badlogic.gdx.math.Vector3(this.getElement(0).floatValue(), this.getElement(1).floatValue(),
				this.getElement(2).floatValue());
	}

	public void set(float x, float y, float z) {
		this.set(new Float[] { x, y, z });
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void set(Vector3 v) {
		this.set(new Float[] { v.x, v.y, v.z });
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}

	@Override
	public Vector3 cpy() {
		return new Vector3(this.x, this.y, this.z);
	}

}
