package com.uchump.prime.LIB._.Sebastian.Geometry;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public abstract class Maths2D {
//using GDX lolol
	public static float PseudoDistanceFromPointToLine(Vector2 a, Vector2 b, Vector2 c) {
		return Math.abs((c.x - a.x) * (-b.y + a.y) + (c.y - a.y) * (b.x - a.x));
	}

	public static int SideOfLine(Vector2 a, Vector2 b, Vector2 c) {
		return (int) Math.signum((c.x - a.x) * (-b.y + a.y) + (c.y - a.y) * (b.x - a.x));
	}

	public static int SideOfLine(float ax, float ay, float bx, float by, float cx, float cy) {
		return (int) Math.signum((cx - ax) * (-by + ay) + (cy - ay) * (bx - ax));
	}

	public static boolean PointInTriangle(Vector2 a, Vector2 b, Vector2 c, Vector2 p) {
		// this.defines a triangle lolol
		float area = 0.5f * (-b.y * c.x + a.y * (-b.x + c.x) + a.x * (b.y - c.y) + b.x * c.y);
		float s = 1 / (2 * area) * (a.y * c.x - a.x * c.y + (c.y - a.y) * p.x + (a.x - c.x) * p.y);
		float t = 1 / (2 * area) * (a.x * b.y - a.y * b.x + (a.y - b.y) * p.x + (b.x - a.x) * p.y);
		return s >= 0 && t >= 0 && (s + t) <= 1;

	}

	public static boolean LineSegmentsIntersect(Vector2 a, Vector2 b, Vector2 c, Vector2 d) {
		float denominator = ((b.x - a.x) * (d.y - c.y)) - ((b.y - a.y) * (d.x - c.x));
		if (MathUtils.isEqual(denominator, 0)) {
			return false;
		}

		float numerator1 = ((a.y - c.y) * (d.x - c.x)) - ((a.x - c.x) * (d.y - c.y));
		float numerator2 = ((a.y - c.y) * (b.x - a.x)) - ((a.x - c.x) * (b.y - a.y));

		if (MathUtils.isEqual(numerator1, 0) || MathUtils.isEqual(numerator2, 0)) {
			return false;
		}

		float r = numerator1 / denominator;
		float s = numerator2 / denominator;

		return (r > 0 && r < 1) && (s > 0 && s < 1);
	}

}
