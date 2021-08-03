package com.uchump.prime._PRIME._coPRIME.N_M;

import static com.uchump.prime._PRIME.uAppUtils.*;

import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aNumber;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uNumber;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

import net.dermetfan.utils.StringUtils;

public class NumberUtils {
//VectorUtils for uVector
	// N / D = Q
	// N * (1/D) = Q
	// n rem d = n − d ∗ TRUNC(n/d) (sign of dividend d),
	// n mod d = n − d ∗ bn/dc (sign of divisor n).

	public static Number add(Number a, Number b) {
		// takes on class of Number a

		if (a instanceof Float) {
			return a.floatValue() + b.floatValue();
		}

		if (a instanceof Integer) {
			return a.intValue() + b.intValue();
		}

		if (a instanceof Short) {
			return a.shortValue() + b.shortValue();
		}

		if (a instanceof Long) {
			return a.longValue() + b.longValue();
		}

		if (a instanceof Double) {
			return a.doubleValue() + b.doubleValue();
		}

		if (a instanceof Byte) {
			return a.byteValue() + b.byteValue();
		}
		if (a instanceof aNumber) {
			return ((aNumber) a).add(b);
		}
		return a;
	}

	public static Number sub(Number a, Number b) {
		if (a instanceof Float) {
			return a.floatValue() - b.floatValue();
		}

		if (a instanceof Integer) {
			return a.intValue() - b.intValue();
		}

		if (a instanceof Short) {
			return a.shortValue() - b.shortValue();
		}

		if (a instanceof Long) {
			return a.longValue() - b.longValue();
		}

		if (a instanceof Double) {
			return a.doubleValue() - b.doubleValue();
		}

		if (a instanceof Byte) {
			return a.byteValue() - b.byteValue();
		}

		if (a instanceof aNumber) {
			return ((aNumber) a).sub(b);
		}

		return a;
	}

	public static Number mul(Number a, Number b) {
		if (a instanceof Float) {
			return a.floatValue() * b.floatValue();
		}

		if (a instanceof Integer) {
			return a.intValue() * b.intValue();
		}

		if (a instanceof Short) {
			return a.shortValue() * b.shortValue();
		}

		if (a instanceof Long) {
			return a.longValue() * b.longValue();
		}

		if (a instanceof Double) {
			return a.doubleValue() * b.doubleValue();
		}

		if (a instanceof Byte) {
			return a.byteValue() * b.byteValue();
		}

		if (a instanceof aNumber) {
			return ((aNumber) a).mul(b);
		}

		return a;
	}

	public static Number div(Number a, Number b) {
		Number A = a;
		if (A instanceof Float) {
			return a.floatValue() / b.floatValue();
		}

		if (A instanceof Integer) {
			return a.intValue() / b.intValue();
		}

		if (A instanceof Short) {
			return a.shortValue() / b.shortValue();
		}

		if (A instanceof Long) {
			return a.longValue() / b.longValue();
		}

		if (A instanceof Double) {
			return a.doubleValue() / b.doubleValue();
		}

		if (A instanceof Byte) {
			return a.byteValue() / b.byteValue();
		}

		if (A instanceof aNumber) {
			return ((aNumber) a).div(b);
		}

		return a;
	}

	public static Number pow(Number a, Number b) {

		// a^x
		Number A = a;

		if (A instanceof Float) {
			return Math.pow(a.floatValue(), b.floatValue());
		}

		if (A instanceof Integer) {
			return Math.pow(a.intValue(), b.intValue());
		}

		if (A instanceof Short) {
			return Math.pow(a.shortValue(), b.shortValue());

		}

		if (A instanceof Long) {
			return Math.pow(a.longValue(), b.longValue());
		}

		if (A instanceof Double) {
			return Math.pow(a.doubleValue(), b.doubleValue());
		}

		if (A instanceof Byte) {
			return Math.pow(a.byteValue(), b.byteValue());
		}

		if (A instanceof aNumber) {

			return (((aNumber) A).pow(b));
		}

		return a;
	}

	public static Number root(Number a, Number r) {
		Number A = a;
		if (A instanceof Float) {
			return pow(A, div(1d, r));
		}

		if (A instanceof Integer) {
			// cant subdivide integers
			return pow(A.floatValue(), div(1d, r.floatValue()));
		}

		if (A instanceof Short) {
			return pow(A, div(1d, resolve(r)));
		}

		if (A instanceof Long) {
			return pow(A, div(1d, resolve(r)));
		}

		if (A instanceof Double) {
			return pow(A, div(1d, resolve(r)));
		}

		if (A instanceof Byte) {
			return pow(A, div(1d, resolve(r)));
		}

		if (A instanceof aNumber) {

			// aNumber N = aNumUtils.resolveA(aNumUtils.div(1d, aNumUtils.resolve(r)));
			return ((aNumber) A).root(r);
		}

		return a;
	}

	public static Number mod(Number a, Number b) {
		// sign of divisor
		if (a instanceof Float) {
			return Math.signum(NumberUtils.resolveF(b)) * (a.floatValue() % b.floatValue());
		}

		if (a instanceof Integer) {
			return Math.signum(NumberUtils.resolveD(b)) * (a.intValue() % b.intValue());
		}

		if (a instanceof Short) {
			return Math.signum(NumberUtils.resolveD(b)) * (a.shortValue() % b.shortValue());
		}

		if (a instanceof Long) {
			return Math.signum(NumberUtils.resolveD(b)) * (a.longValue() % b.longValue());
		}

		if (a instanceof Double) {
			return Math.signum(NumberUtils.resolveD(b)) * (a.doubleValue() % b.doubleValue());
		}

		if (a instanceof Byte) {
			return Math.signum(NumberUtils.resolveD(b)) * (a.byteValue() % b.byteValue());
		}

		if (a instanceof aNumber) {
			return Math.signum(NumberUtils.resolveD(b)) * NumberUtils.resolveD(((aNumber) a).mod(b));
		}

		return a;
	}

	public static Number rem(Number a, Number b) {
		// sign of dividend
		if (a instanceof Float) {
			return Math.signum(NumberUtils.resolveD(a)) * (a.floatValue() % b.floatValue());
		}

		if (a instanceof Integer) {
			return Math.signum(NumberUtils.resolveD(a)) * (a.intValue() % b.intValue());
		}

		if (a instanceof Short) {
			return Math.signum(NumberUtils.resolveD(a)) * (a.shortValue() % b.shortValue());
		}

		if (a instanceof Long) {
			return Math.signum(NumberUtils.resolveD(a)) * (a.longValue() % b.longValue());
		}

		if (a instanceof Double) {
			return Math.signum(NumberUtils.resolveD(a)) * (a.doubleValue() % b.doubleValue());
		}

		if (a instanceof Byte) {
			return Math.signum(NumberUtils.resolveD(a)) * (a.byteValue() % b.byteValue());
		}

		if (a instanceof aNumber) {
			return Math.signum(NumberUtils.resolveD(a)) * NumberUtils.resolveD(((aNumber) a).mod(b));
		}

		return a;
	}

	public static Number abs(Number a) {
		// takes on class of Number a

		if (a instanceof Float) {
			return Math.abs(a.floatValue());
		}

		if (a instanceof Integer) {
			return Math.abs(a.intValue());
		}

		if (a instanceof Short) {
			return Math.abs(a.shortValue());
		}

		if (a instanceof Long) {
			return Math.abs(a.longValue());
		}

		if (a instanceof Double) {
			return Math.abs(a.doubleValue());
		}

		if (a instanceof Byte) {
			return Math.abs(a.byteValue());
		}
		if (a instanceof aNumber) {
			return ((aNumber) a).abs();
		}
		return a;
	}

	public static Number max(Number a, Number b) {
		if (a instanceof Float) {
			return Math.max(a.floatValue(), b.floatValue());
		}

		if (a instanceof Integer) {
			return Math.max(a.intValue(), b.intValue());
		}

		if (a instanceof Short) {
			return Math.max(a.shortValue(), b.shortValue());
		}

		if (a instanceof Long) {
			return Math.max(a.longValue(), b.longValue());
		}

		if (a instanceof Double) {
			return Math.max(a.doubleValue(), b.doubleValue());
		}

		if (a instanceof Byte) {
			return Math.max(a.byteValue(), b.byteValue());
		}

		if (a instanceof aNumber) {
			return ((aNumber) a).max(a, b);
		}

		return a;
	}

	public static Number min(Number a, Number b) {
		if (a instanceof Float) {
			return Math.min(a.floatValue(), b.floatValue());
		}

		if (a instanceof Integer) {
			return Math.min(a.intValue(), b.intValue());
		}

		if (a instanceof Short) {
			return Math.min(a.shortValue(), b.shortValue());
		}

		if (a instanceof Long) {
			return Math.min(a.longValue(), b.longValue());
		}

		if (a instanceof Double) {
			return Math.min(a.doubleValue(), b.doubleValue());
		}

		if (a instanceof Byte) {
			return Math.min(a.byteValue(), b.byteValue());
		}

		if (a instanceof aNumber) {
			return ((aNumber) a).min(a, b);
		}

		return a;
	}

	//
	////
	//

	public static Number resolve(Object n) {
		if ((Number.class.isAssignableFrom(n.getClass()))) {
			return resolve((Number) n);
		} else
			return 0;
	}

	public static Number resolve(Number n) {
		if (n == null)
			n = 0f;

		if ((Float.class.isAssignableFrom(n.getClass()))) {

			return resolveF(n);
		}

		if ((Integer.class.isAssignableFrom(n.getClass()))) {

			return resolveI(n);
		}
		if ((Double.class.isAssignableFrom(n.getClass()))) {

			return resolveD(n);
		}

		if ((Short.class.isAssignableFrom(n.getClass()))) {

			return resolveS(n);
		}

		if ((Long.class.isAssignableFrom(n.getClass()))) {

			return resolveL(n);
		}

		if ((Byte.class.isAssignableFrom(n.getClass()))) {

			return resolveL(n);
		}

		if ((aVector.class.isAssignableFrom(n.getClass()))) {

			return resolve(((aVector) n).i().floatValue());
		}

		return n;
	}

	public static Byte resolveB(Number b) {
		return b.byteValue();
	}

	public static Byte b(Number b) {
		return resolveB(b);
	}

	public static int resolveI(Number i) {
		return i.intValue();
	}

	public static int i(Number i) {
		return resolveI(i);
	}

	public static float resolveF(Number f) {
		return f.floatValue();
	}

	public static float f(Number f) {
		return resolveF(f);
	}

	public static double resolveD(Number d) {
		return d.doubleValue();
	}

	public static double d(Number d) {
		return resolveF(d);
	}

	public static short resolveS(Number s) {
		return s.shortValue();
	}

	public static short s(Number s) {
		return resolveS(s);
	}

	public static long resolveL(Number l) {
		return l.longValue();
	}

	public static long l(Number l) {
		return resolveL(l);
	}

	public static Number str(String N) {

		return resolve(Float.parseFloat(N));
	}

	public static Number str(String N, Class<? extends Number> as) {

		if (as.isAssignableFrom(Integer.class)) {
			return resolve((as.cast(Integer.parseInt(N))));
		}

		return resolve((as.cast(Float.parseFloat(N))));
	}

	public static aNumber resolveTo(Number n) {
		return new uNumber(n);
	}

	public static aVector V(Number V) {
		uVector v = new uVector(V);
		v.overrideType(V);
		return v;
	}

	////////
	public static Number[] resolveArray(Object[] n) {
		Number[] result = new Number[n.length];
		for (int i = 0; i < n.length; i++) {

			result[i] = resolve(n[i]);
		}

		return result;
	}

	public static Number[] resolveFA(float[] fs) {
		Number[] result = new Number[fs.length];
		for (int i = 0; i < fs.length; i++) {

			result[i] = resolve(fs[i]);
		}

		return result;
	}

	/*
	 * public static aVector resolveV(Number V) { return resolveV(V); }
	 */

	public static Number[] scanFrom(Object[] other) {
		Number[] Result = new Number[other.length];

		for (int i = 0; i < other.length; i++) {

			Number N = resolve(other[i]);
			Result[i] = N;

		}

		return Result;
	}

	public static Number[] toDigits(Number N) {
		Number[] Result = new Number[N.intValue()];
		for (int i = 0; i <= Result.length; i++) {
			Result[i] = i;
		}

		return Result;
	}

	/////////////////////////////////
	public static int floatToIntBits(float value) {
		return Float.floatToIntBits(value);
	}

	public static int floatToRawIntBits(float value) {
		return Float.floatToRawIntBits(value);
	}

	/**
	 * Converts the color from a float ABGR encoding to an int ABGR encoding. The
	 * alpha is expanded from 0-254 in the float encoding (see
	 * {@link #intToFloatColor(int)}) to 0-255, which means converting from int to
	 * float and back to int can be lossy.
	 */
	public static int floatToIntColor(float value) {
		int intBits = Float.floatToRawIntBits(value);
		intBits |= (int) ((intBits >>> 24) * (255f / 254f)) << 24;
		return intBits;
	}

	/**
	 * Encodes the ABGR int color as a float. The alpha is compressed to use only
	 * even numbers between 0-254 to avoid using bits in the NaN range (see
	 * {@link Float#intBitsToFloat(int)} javadocs). Rendering which uses colors
	 * encoded as floats should expand the 0-254 back to 0-255, else colors cannot
	 * be fully opaque.
	 */
	public static float intToFloatColor(int value) {
		return Float.intBitsToFloat(value & 0xfeffffff);
	}

	public static float intBitsToFloat(int value) {
		return Float.intBitsToFloat(value);
	}

	public static long doubleToLongBits(double value) {
		return Double.doubleToLongBits(value);
	}

	public static double longBitsToDouble(long value) {
		return Double.longBitsToDouble(value);
	}
}
