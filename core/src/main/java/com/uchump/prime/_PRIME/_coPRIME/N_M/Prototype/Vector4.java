package com.uchump.prime._PRIME._coPRIME.N_M.Prototype;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Quaternion;
import com.uchump.prime._PRIME.METATRON.M.File.Rect;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;
public class Vector4 extends uVector {
	
	public Vector4() {
		this(0, 0, 0, 0);
	}

	public Vector4(float x, float y, float z, float w) {
		super(new Float[] { x, y, z, w });
	}

	public Vector4(Quaternion q) {
		this(q.x, q.y, q.z, q.w);

	}

	public Vector4(Color q) {
		this(q.r, q.g, q.b, q.a);
		this.label(new char[] { 'r', 'g', 'b', 'a' }, true);
	}

	public com.badlogic.gdx.math.Quaternion Gdx() {
		return new Quaternion(this.getElement(0).floatValue(), this.getElement(1).floatValue(),
				this.getElement(2).floatValue(), this.getElement(3).floatValue());
	}

	public com.badlogic.gdx.graphics.Color Color() {
		return Color(false);
	}

	public com.badlogic.gdx.graphics.Color Color(boolean d) {
		if (d)
			return new Color(((1 / 256) * this.getElement(0).floatValue()) % 255,
					((1 / 256) * this.getElement(1).floatValue()) % 255,
					((1 / 256) * this.getElement(2).floatValue()) % 255,
					((1 / 256) * this.getElement(3).floatValue()) % 255);
		else
			return new Color(this.getElement(0).floatValue(), this.getElement(1).floatValue(),
					this.getElement(2).floatValue(), this.getElement(3).floatValue());
	}

	public Rect Rect() {
		return new Rect(this.getElement(0).floatValue(), this.getElement(1).floatValue(),
				this.getElement(2).floatValue(), this.getElement(3).floatValue());
	}

	public aVector set(float x, float y, float z, float w) {
		this.set(new Float[] { x, y, z, w });
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.label(new char[] { 'x', 'y', 'z', 'w' }, true);
		return this;
	}

	public aVector set(Vector4 v) {
		this.set(new Float[] { v.x, v.y, v.z, v.w });
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.w = v.w;
		return this;
	}

	public aVector set(Quaternion v) {
		this.set(new Float[] { v.x, v.y, v.z, v.w });
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
		this.w = v.w;
		return this;
	}
	

	public aVector add(Quaternion q) {
		Vector4 tmp = new Vector4(q);
		return this.add(tmp);
	}

	public aVector mul(Quaternion q) {
		Vector4 tmp = new Vector4(q);
		return this.mul(tmp);
	}
	
	public Vector4 mtxMul(Vector2 v)
	{
		Vector4 x = new Vector4(v.x,v.y,v.x,v.y);
		return (Vector4) this.mul(x);
	}
	
	public aVector sub(Quaternion q) {
		Vector4 tmp = new Vector4(q);
		return this.sub(tmp);
	}

	public aVector div(Quaternion q) {
		Vector4 tmp = new Vector4(q);
		return this.div(tmp);
	}

	public aVector set(Color v) {
		this.set(new Float[] { v.r, v.g, v.b, v.a });
		this.x = v.r;
		this.y = v.g;
		this.z = v.b;
		this.w = v.a;
		this.label(new char[] { 'r', 'g', 'b', 'a' }, true);
		return this;
	}
	
	@Override
	public Vector4 cpy()
	{
		return new Vector4(this.x,this.y,this.z, this.w);
	}
	

}
