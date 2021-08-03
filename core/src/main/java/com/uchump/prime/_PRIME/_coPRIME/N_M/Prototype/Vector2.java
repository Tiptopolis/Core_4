package com.uchump.prime._PRIME._coPRIME.N_M.Prototype;

public class Vector2 extends uVector {

	public Vector2() {
		this(0, 0);
	}
	
	public Vector2(Number n)
	{
		super(new Float[] { n.floatValue(), n.floatValue() });
	}

	public Vector2(float x, float y) {
		super(new Float[] { x, y });
	}

	public Vector2(com.badlogic.gdx.math.Vector2 v) {
		super(new Float[] { v.x, v.y });
	}

	public com.badlogic.gdx.math.Vector2 Gdx() {
		return new com.badlogic.gdx.math.Vector2(this.getElement(0).floatValue(), this.getElement(1).floatValue());
	}

	public void set(float x, float y) {
		this.set(new Float[] { x, y });
		this.x = x;
		this.y = y;
	}

	public void set(Vector2 v) {
		this.set(new Float[] { v.x, v.y });
		this.x = v.x;
		this.y = v.y;
	}

	@Override
	public Vector2 cpy() {
		return new Vector2(this.x, this.y);
	}

}
