package com.uchump.prime._PRIME.METATRON.M.File;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;
import java.io.Serializable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME.METATRON.M.File.BoundShape;
import com.uchump.prime._PRIME._coPRIME.N_M.VectorUtils;

public class Rect  implements Serializable, Shape2D {

	public float minX;
	public float minY;

	public float maxX;
	public float maxY;

	public float width;
	public float height;
	protected BoundShape shape;

	public Rect() {
		
		this(0, 0, 0, 0);
	}

	public Rect(float x, float y, float width, float height) {
		// bottom-left
		
		this.minX = x;
		this.minY = y;
		this.width = width;
		this.height = height;
		this.maxX = x + width;
		this.maxY = y + height;
	}

	public Rect(Vector2 position, Vector2 size) {
		// bottom-left
		this(position.x, position.y, size.x, size.y);
	}

	public Rect(Rectangle r) {
		this(r.x, r.y, r.width, r.height);
	}

	public Rect set(float x, float y, float width, float height) {
		this.minX = x;
		this.minY = y;
		this.width = width;
		this.height = height;
		this.maxX = x + width;
		this.maxY = y + height;
		return this;
	}

	public Rect set(Vector2 position, Vector2 size) {

		return this.set(position.x, position.y, size.x, size.y);
	}

	public void setAt(Vector2 at, Vector2 size) {
		this.width = size.x;
		this.height = size.y;
		this.set(at.x - (this.width / 2), at.y - (this.height / 2), this.width, this.height);
	}
	
	public Vector2 getSize()
	{
		return new Vector2(this.width, this.height);
	}

	public Vector2 getCenter() {
		float x = this.minX + (this.width / 2);
		float y = this.minY + (this.height / 2);
		return new Vector2(x, y);
	}

	public void centerAt(Vector2 at) {
		this.set(at.x - (this.width / 2), at.y - (this.height / 2), this.width, this.height);
	}

	public void centerAt(Vector3 at) {
		this.set(at.x - (this.width / 2), at.y - (this.height / 2), this.width, this.height);
	}

	public Rect centeredAt(Vector3 at) {
		this.centerAt(at);
		return this;
	}

	@Override
	public boolean contains(Vector2 point) {

		return this.contains(point.x, point.y);
	}

	@Override
	public boolean contains(float x, float y) {
		return this.minX <= x && this.minX + this.width >= x && this.minY <= y && this.minY + this.height >= y;
	}

	public Rect scl(float w, float h) {
		Vector2 ctr = this.getCenter();
		this.set(this.minX, this.minY, this.width / w, this.height * h);
		this.centerAt(ctr);
		return this;
	}

	public Rect scl(Vector2 scalar) {
		return this.scl(scalar.x, scalar.y);
	}

	public Matrix4 getMatrix() {
		return new Matrix4().setToOrtho2D(this.minX, this.minY, this.width, this.height);
	}

	public Rectangle getRectangle() {
		return new Rectangle(this.minX, this.minY, this.width, this.height);
	}

	public BoundShape bind(Color c) {

		BoundShape r = new BoundShape();
		Vector3 cnt = new Vector3((this.width / 2), (this.height / 2), 0);
		Vector3 pos = new Vector3(this.minX, this.minY, 0);
		// Vector3 R = new Vector3(1,1,1).crs(new Vector3(0,1,0)).nor();
		// R.rot(matrix)
		r = BoundShape.bindShape(pos, cnt, new Vector3(1, 1, 0), c, 4);
		this.shape = r;
		r.vertexSize = 2;

		return this.shape;
	}

	@Override
	public String toString() {

		return "<Rect>: @" + new Vector2(this.minX, this.minY) + " #" + VectorUtils.IntVector2(this.width, this.height);
	}

	public String toLog() {
		String log = "";
		log += "["+this.width +","+this.height+"]";
		log += "\n*-* ("+this.minX + ","+this.minY+")";
		log += "\n*X*   >=>";
		log += "\n*-* ("+this.maxX + ","+this.maxY+")";
		log += "\n";
		return log;
	}
}
