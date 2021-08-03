package com.uchump.prime.TESTS.Raymarching.UselesssNonsense;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;

import com.uchump.prime._PRIME._coPRIME.N_M.NumberUtils;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aNumber;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector2;

public class SDF_Maths_F {
//aVector v;
	//attempted rip from SebastianLague lololol
	public static float maxDst = 100;
	public static ArrayList<Circle> circles = new ArrayList<Circle>();
	public static ArrayList<Box> boxes = new ArrayList<Box>();

	public static float length(Vector2 v) {
		return (float) Math.sqrt((v.x * v.x) + (v.y * v.y));
	}

	public static float sigDstToCircle(Vector2 p, Vector2 center, float radius) {
		// P is a point outside the radius of the circle
		// dst TO the circle FROM p lol

		return length((Vector2) p.cpy().sub(center.cpy())) - radius;
	}

	public static float sigDstToBox(Vector2 p, Vector2 center, Vector2 size) {
		Vector2 offset = (Vector2) new Vector2(NumberUtils.abs(p.cpy().sub(center.cpy()))).sub(size.cpy());
		float usigDst = length((Vector2) offset.cpy().max(offset.cpy(), 0));
		float dstInBox = (float) NumberUtils.resolveF(offset.max(offset.min(offset, 0), 0));
		return usigDst - dstInBox;
	}

	public static float sigDstToScene(Vector2 p) {
		float dstToScene = maxDst;
		// loop over all circles & boxes in the "scene", ie You, p
		// returns smallest found value as dstToScene

		for (int i = 0; i < circles.size() - 1; i++) {
			Circle c = circles.get(i);
			float dstToCircle = sigDstToCircle(p, c.center, c.radius); // this IS the ray lol
			Log("C" + dstToCircle);
			dstToScene = (float) NumberUtils.resolveF(NumberUtils.min(dstToCircle, dstToScene));
		}

		for (int i = 0; i < boxes.size() - 1; i++) {
			Box b = boxes.get(i);
			float dstToBox = sigDstToBox(p, b.center, b.size);
			dstToScene = (float) NumberUtils.resolveF(NumberUtils.min(dstToBox, dstToScene));
			Log("dB" + dstToBox);
		}
		 Log("dS" + dstToScene);
		return dstToScene;
	}

	public static class Shape {
		Vector2 center;

		public Shape(Vector2 at, Number size) {
			this.center = at;
		}

	}

	public static class Circle extends Shape {
		float radius = 1;

		public Circle(Vector2 at, Number size) {
			super(at, size);
			this.radius = size.floatValue();

		}

	}

	public static class Box extends Shape {
		Vector2 size = new Vector2();

		public Box(Vector2 at, Vector2 size) {
			super(at, size);
			this.size = size;

		}

	}

}
