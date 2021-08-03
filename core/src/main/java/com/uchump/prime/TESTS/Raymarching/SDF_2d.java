package com.uchump.prime.TESTS.Raymarching;

import com.uchump.prime._PRIME._coPRIME.N_M.NumberUtils;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector2;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector4;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

public class SDF_2d {
//shapes
	// :https://iquilezles.org/www/articles/distfunctions2d/distfunctions2d.htm
	// :https://iquilezles.org/www/articles/distfunctions/distfunctions.htm
	public static float sdCircle(Vector2 p, Number r) {

		return p.cpy().sub(r).len();
		// return p.len() - r.floatValue();
	}

	// b box size,
	float sdBox(Vector2 p, Vector2 b) {
		Vector2 d = new Vector2(p.cpy().abs().sub(b.cpy()));
		Vector2 D = new Vector2(d.cpy().max(d, 0));
		return D.len() + Math.min(Math.max(d.x, d.y), 0);

		// vec2 d = abs(p)-b;
		// return length(max(d,0.0)) + min(max(d.x,d.y),0.0);
	}

	// b box size, r radius
	float sdRoundedBox(Vector2 p, Vector2 b, Vector4 r) {
		if (p.x <= 0) {
			r.x = r.z;
			r.y = r.w;
		}
		if (p.y <= 0) {
			r.x = r.y;
		}
		Vector2 q = new Vector2(p.cpy().abs().sub(b.cpy()).add(r.y));
		Vector2 Q = new Vector2(q.cpy().min(0));
		return (min(max(q.x, q.y), 0).floatValue()) + Q.len() - r.x;

		// r.xy = (p.x>0.0)?r.xy : r.zw;
		// r.x = (p.y>0.0)?r.x : r.y;
		// Vector2 q = abs(p)-b+r.x;
		// return min(max(q.x,q.y),0.0) + length(max(q,0.0)) - r.x;
	}

	// a,b, th is oriented line & its theta?
	float sdOrientedBox(Vector2 p, Vector2 a, Vector2 b, float th) {
		float l = a.cpy().sub(b.cpy()).len();
		Vector2 d = new Vector2((b.cpy().sub(a)).div(l));
		Vector2 q = new Vector2(p.cpy().sub(a.cpy().add(b.cpy())).mul(0.5f));
		Vector4 q2 = new Vector4(d.x, -d.y, d.y, d.x).mtxMul(q.cpy());
		Vector2 q3 = new Vector2(q2.abs().sub(new Vector2(l, th).mul(0.5f)));
		return (q3.max(0).len()) + (Math.min(Math.max(q3.x, q3.y), 0));
	}

	/////////////////////

	public Number min(Number a, Number b) {
		return NumberUtils.min(a, b);
	}

	public Number max(Number a, Number b) {
		return NumberUtils.max(a, b);
	}

	public Number length(Number v) {
		if (v instanceof aVector)
			return ((aVector) v).len();

		return v;
	}
}
