package com.uchump.prime._PRIME.METATRON.M.File;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime._PRIME._coPRIME.N_M.Geom;
import com.uchump.prime._PRIME._coPRIME.N_M.VectorUtils;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public class BoundShape implements Shape2D {

	// 2D
	// temporary, just-in-time 2d geometry, capturable

	public Vector3 position = new Vector3();
	public Vector3 size = new Vector3();
	public Quaternion rotation = new Quaternion(); // euler angles

	public Color fillColor = Color.WHITE;
	public Color lineColor = Color.GRAY;
	public int vertexNum = 0;
	public float vertexSize = 2;
	public float lineWidth = 1;
	public ArrayList<Vector3> shape = new ArrayList<Vector3>();

	private boolean dirty = false;
	public boolean debug = true;
	public boolean fill = false;
	public boolean lines = true;
	public boolean linesOutter = true;
	public boolean linesInner = true;

	public uTransform transform;

	public BoundShape() {

	}

	public float perimeter() {
		float sum = 0f;
		for (int i = 0; i < this.vertexNum; i++) {
			sum = sum + this.shape.get(i).dst(this.shape.get(i + 1));
		}
		return sum;
	}

	// return signed area of polygon
	public double area() {
		double sum = 0.0;
		for (int i = 0; i < this.vertexNum; i++) {
			sum = sum + (this.shape.get(i).x * this.shape.get(i + 1).y)
					- (this.shape.get(i).y * this.shape.get(i + 1).x) - (this.shape.get(i).z * this.shape.get(i + 1).z);
		}
		return 0.5 * sum;
	}

	@Override
	public boolean contains(Vector2 v) {

		return contains(v.x, v.y);
	}

	/*
	 * @Override public boolean contains(Vector2 point) { int winding = 0; for (int
	 * i = 0; i < this.vertexNum-1; i++) { int ccw =
	 * Vect2.ccw(VectorUtils.downcast(this.shape.get(i)),
	 * VectorUtils.downcast(this.shape.get(i + 1)), point); if (this.shape.get(i +
	 * 1).y > point.y && point.y >= this.shape.get(i).y) // upward crossing if (ccw
	 * == +1) winding++; if (this.shape.get(i + 1).y <= point.y && point.y <
	 * this.shape.get(i).y) // downward crossing if (ccw == -1) winding--; } return
	 * winding != 0;
	 * 
	 * }
	 */

	/** Returns whether an x, y pair is contained within the polygon. */
	@Override
	public boolean contains(float x, float y) {
				
		return (this.toPolygon().contains(x, y));

	}

	public void setColor(Color color) {
		this.lineColor = color;
		this.fillColor = color;
	}

	public void setColor(Color line, Color fill) {
		this.lineColor = line;
		this.fillColor = fill;
	}

	public void drawShape() {
		int o = 0;
		int n = 0;
		int s = this.shape.size() - 1;
		Vector3 next;
		Vector3 last;

		for (int i = 0; i < s + 1; i++) {
			Vector3 v = this.shape.get(i);
			Sketcher.setColor(this.lineColor);

			int f = s + 1;
			n = i + 1;
			if (n >= f)
				n = (n % f);

			next = this.shape.get(n);

			if (this.lines) {
				if (this.linesOutter) {
					Sketcher.Drawer.line(new Vector2(next.x, next.y), new Vector2(v.x, v.y), this.lineColor,
							this.lineWidth);
				}
				if (this.linesInner) {
					Sketcher.Drawer.line(new Vector2(v.x, v.y), new Vector2(this.position.x, this.position.y),
							this.lineColor, this.lineWidth);
				}
			}

			Sketcher.setColor(this.fillColor);
			Sketcher.Drawer.filledCircle(v.x, v.y, this.vertexSize + (this.vertexSize % v.z));

		}

		if (!this.shape.isEmpty()) {
			last = this.shape.get(0);
			Sketcher.setColor(new Color(1, 1, 1, 0.5f));
			Sketcher.Drawer.filledCircle(last.x, last.y, this.vertexSize);
		}
	}

	public static BoundShape bindVertex(Vector3 at, float radius, Color c) {
		BoundShape newShape = new BoundShape();
		newShape.position = at;
		newShape.size = new Vector3().add(radius);
		newShape.vertexNum = 1;
		newShape.vertexSize = radius;
		newShape.lines = false;
		newShape.setColor(c);
		newShape.shape.add(at);
		return newShape;
	}

	public static BoundShape bindVertex(uTransform t, float radius, Color c) {
		BoundShape newShape = new BoundShape();
		newShape.position = t.GetLocalPosition();
		newShape.rotation = t.GetLocalRotation();
		newShape.size = t.getMagnitude();
		newShape.vertexNum = 1;
		newShape.vertexSize = radius;
		newShape.lines = false;
		newShape.setColor(c);
		newShape.shape.add(t.GetLocalPosition());
		return newShape;
	}

	public static BoundShape bindShape(Vector3 at, Vector3 radius, Color c, int n) {
		BoundShape newShape = new BoundShape();

		newShape.position = at;
		newShape.size = radius;
		newShape.rotation = new Quaternion();
		newShape.fillColor = c.cpy();
		newShape.lineColor = c.cpy();
		newShape.vertexNum = n;

		newShape.shape = Geom.regPolygon(at, radius, n);

		return newShape;
	}

	// rebind to rotate
	public static BoundShape bindShape(Vector3 at, Vector3 radius, Vector3 rotation, Color c, int n) {
		BoundShape newShape = new BoundShape();

		newShape.position = at;
		newShape.size = radius;
		newShape.rotation = VectorUtils.upcast(rotation);
		newShape.fillColor = c.cpy();
		newShape.lineColor = c.cpy();
		newShape.vertexNum = n;

		newShape.shape = Geom.generatePolygon(at, radius, rotation, n);

		return newShape;

	}

	public static BoundShape bindShape(Vector3 at, Vector3 radius, Vector3 rotation, Color c, int n, float vertSize) {
		BoundShape newShape = new BoundShape();
		newShape.position = at;
		newShape.size = radius;
		newShape.rotation = VectorUtils.upcast(rotation);
		newShape.fillColor = c.cpy();
		newShape.lineColor = c.cpy();

		newShape.vertexNum = n;
		newShape.vertexSize = vertSize;

		newShape.shape = Geom.generatePolygon(at, radius, rotation, n);

		return newShape;

	}

	public static BoundShape bindShape(uTransform t, Color c, int n, float vertSize) {
		BoundShape newShape = new BoundShape();
		newShape.position = t.GetLocalPosition();
		newShape.size = t.GetScale();
		newShape.rotation = t.GetLocalRotation();
		newShape.fillColor = c.cpy();
		newShape.lineColor = c.cpy();

		newShape.vertexNum = n;
		newShape.vertexSize = vertSize;

		newShape.shape = Geom.generatePolygon(t, n);
		newShape.transform = t;
		// Log(t.of + " " + newShape.shape.size() + "/" + n);
		return newShape;

	}



	public static BoundShape bindShape(BoundShape s, Color c, int n, float vertSize) {
		BoundShape newShape = new BoundShape();
		newShape.position = s.position.cpy();
		newShape.size = s.size.cpy();
		newShape.rotation = s.rotation.cpy();
		newShape.fillColor = c.cpy();
		newShape.lineColor = c.cpy();

		newShape.vertexNum = n;
		newShape.vertexSize = vertSize;

		// newShape.shape = Geometry.generatePolygon(s.getTransform(), n);

		newShape.shape = Geom.generatePolygon(newShape.position.cpy(), newShape.size.cpy(),
				VectorUtils.downcast(newShape.rotation.cpy().nor()), n);

		// Log(t.of + " " + newShape.shape.size() + "/" + n);
		return newShape;

	}

	public void rebind(Vector3 at)
	{
		this.position = at;
		if(this.transform == null)
			this.captureTransform();
		this.rebind();
	}
	
	public void rebind() {
		if (this.transform != null) {
			uTransform t = this.getTransform();						
			this.position = t.GetLocalPosition();
			Vector3 s = this.getTransform().GetScale();			
			this.size = s;
			this.rotation = t.GetLocalRotation();
			this.shape.clear();
			this.shape = Geom.generatePolygon(t, this.vertexNum);
		} else {
			this.captureTransform();
			this.rebind();
		}
	}

	/////////////////////
	//////////////////////
	/////////////////////
	// Directed Line Segment, Ray
	public static BoundShape bindRadius(Vector3 at, Vector3 length, Vector3 rotation, Color color) {
		return bindShape(at, length, rotation, color, 1);
	}

	// proper a-? line
	public static BoundShape bindRadian(Vector3 origin, Vector3 length, Vector3 dir, Color color) {
		BoundShape newShape = new BoundShape();
		newShape.position = origin;
		newShape.size = length;
		newShape.rotation = VectorUtils.upcast(dir);
		newShape.setColor(color);
		newShape.vertexNum = 2;
		newShape.shape = Geom.generateLine(origin, length, dir);
		newShape.lineWidth = length.z;
		newShape.vertexSize = 1 + length.z;

		return newShape;
	}

	public static BoundShape bindLine(Vector3 origin, Vector3 to) {
		BoundShape newShape = new BoundShape();

		Vector3 dir = VectorUtils.dir(origin.cpy(), to.cpy());
		Vector3 dst = to.cpy().sub(origin.cpy());
		newShape.position = origin;
		newShape.size = dst;
		newShape.rotation = VectorUtils.upcast(dir);
		newShape.vertexNum = 2;
		newShape.shape = Geom.lineTo(origin, to);
		return newShape;
	}

	////
	//

	public void captureTransform() {
		this.transform = new uTransform();		
		this.transform.SetLocalPosition(this.position);
		this.transform.SetLocalRotation(this.rotation);
		this.transform.SetLocalScale(this.size);

	}

	public uTransform getTransform() {
		if (this.transform == null)
			this.captureTransform();
		return this.transform;
	}

	public BoundShape setTransform(uTransform transform) {
		this.transform = transform;
		this.rebind();
		return this;
	}

	public Polygon toPolygon() {

		ArrayList<Vector2> tmpVerts = new ArrayList<Vector2>();
		for (Vector3 v : this.shape) {
			tmpVerts.add(VectorUtils.downcast(v.cpy()));
		}

		Polygon result = new Polygon(VectorUtils.disassembleVects(tmpVerts.toArray()));

		if (this.transform != null) {
			Vector3 scl = this.transform.GetScale();
			result.setScale(scl.x, scl.y);
			Quaternion rot = this.transform.GetLocalRotation();
			result.setPosition(rot.x, rot.y);
		}

		return result;
	}

	@Override
	public String toString() {
		String out = "";
		out += "\n";
		out += this.vertexNum + "";
		out += "\n";
		out += "" + this.size;
		out += "\n";
		out += "" + this.position;

		return out;
	}

}
