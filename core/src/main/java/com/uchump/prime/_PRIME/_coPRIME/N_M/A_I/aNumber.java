package com.uchump.prime._PRIME._coPRIME.N_M.A_I;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.math.MathUtils;
import com.uchump.prime._PRIME._coPRIME.N_M.NumberUtils;
import com.uchump.prime._PRIME._coPRIME.N_M._N;

public abstract class aNumber extends Number implements _N<Number> {

	// a polynomial

	// defacto algebraic 'ring': can add, mul;
	// defacto algebraic 'field':can add, sub, mul, div;
	// 'field' is just a square 'ring'?
	// reminder that Field and Ring are the ^2||2Pi versions of the same thing lol

	public String label;
	public Number value; // primary value, r length?
	public _N<Number> node;

	protected int i = 0;// index, magnitude, len //scale, Radix length?
	protected float t = 1; // theta //precision? character length?

	protected static float epsilon = 0.00000001f; // epsilon, equality threshold;

	protected int raddix = 10;

	// N_Shell, number & name shell

	public aNumber() {
		this.value = 0;
		this.i = 1;
		this.label = "" + this.value;
		this.node = this;
	}

	public aNumber(Number n) {
		this.value = n;
		this.i = 1;
		this.label = "" + this.value;
		this.node = this;
	}

	public aNumber set(Number n) {
		this.value = n;
		this.i = 1;
		this.label = "" + this.value;
		this.node = this;
		return this;
	}

	////
	// iSymbol

	protected void prelabel() {
		this.label = "" + this.value;

	}

	@Override
	public Number as(_N n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number getValue() {
		return this.value;
	}

	@Override
	public Number getKey() {
		return this.value;
	}

	@Override
	public Number setValue(Object value) {

		return this.value = (Number) value;
	}

	public Number i() {
		return this.add((this.i));
	}

	public Number s() {

		return this.add((this.t * this.value.floatValue()));
	}

	// iSymbol
	////

	////
	// OPERATORS

	public Number add(Number other) {

		if (Float.class.isAssignableFrom(this.value.getClass())) {

			return this.floatValue() + other.floatValue();
		}

		if (Integer.class.isAssignableFrom(this.value.getClass())) {
			return (this.intValue() + other.intValue());
		}

		if (Short.class.isAssignableFrom(this.value.getClass())) {
			return this.shortValue() + other.shortValue();
		}

		if (Long.class.isAssignableFrom(this.value.getClass())) {
			return this.longValue() + other.longValue();
		}

		if (Double.class.isAssignableFrom(this.value.getClass())) {
			return this.doubleValue() + other.doubleValue();
		}

		if (Byte.class.isAssignableFrom(this.value.getClass())) {
			return this.byteValue() + other.byteValue();
		}
		return this;
	}

	public Number sub(Number other) {
		if (other instanceof Float) {
			return this.floatValue() - other.floatValue();
		}

		if (other instanceof Integer) {
			return this.intValue() - other.intValue();
		}

		if (other instanceof Short) {
			return this.shortValue() - other.shortValue();
		}

		if (other instanceof Long) {
			return this.longValue() - other.longValue();
		}

		if (other instanceof Double) {
			return this.doubleValue() - other.doubleValue();
		}

		if (other instanceof Byte) {
			return this.byteValue() - other.byteValue();
		}

		return this;
	}

	public Number mul(Number other) {
		if (other instanceof Float) {
			return this.floatValue() * other.floatValue();
		}

		if (other instanceof Integer) {
			return this.intValue() * other.intValue();
		}

		if (other instanceof Short) {
			return this.shortValue() * other.shortValue();
		}

		if (other instanceof Long) {
			return this.longValue() * other.longValue();
		}

		if (other instanceof Double) {
			return this.doubleValue() * other.doubleValue();
		}

		if (other instanceof Byte) {
			return this.byteValue() * other.byteValue();
		}

		return this;
	}

	public Number div(Number other) {
		if (other instanceof Float) {
			return this.floatValue() / other.floatValue();
		}

		if (other instanceof Integer) {
			return this.intValue() / other.intValue();
		}

		if (other instanceof Short) {
			return this.shortValue() / other.shortValue();
		}

		if (other instanceof Long) {
			return this.longValue() / other.longValue();
		}

		if (other instanceof Double) {
			return this.doubleValue() / other.doubleValue();
		}

		if (other instanceof Byte) {
			return this.byteValue() / other.byteValue();
		}

		return this;
	}

	public Number pow(Number p) {

		if (p instanceof Float) {
			return (float) Math.pow(this.floatValue(), p.floatValue());

		}

		if (p instanceof Integer) {
			return (int) Math.pow(this.intValue(), p.intValue());
		}

		if (p instanceof Short) {
			return (short) Math.pow(this.shortValue(), p.shortValue());
		}

		if (p instanceof Long) {
			return (long) Math.pow(this.longValue(), p.longValue());
		}

		if (p instanceof Double) {
			return (double) Math.pow(this.doubleValue(), p.doubleValue());
		}

		if (p instanceof Byte) {
			return (byte) Math.pow(this.byteValue(), p.byteValue());
		}

		if (p instanceof Byte) {
			return (byte) Math.pow(this.byteValue(), p.byteValue());
		}

		if (p instanceof aNumber) {
			return this.pow(((aNumber) p).value);
		}

		if (p instanceof aVector) {
			return this.pow(((aVector) p).i);
		}

		return this;
	}

	public Number root(Number r) {
		// aNumber N = aNumUtils.resolveA(aNumUtils.div(1d, aNumUtils.resolve(r)));
		// return this.pow(aNumUtils.div(1d, aNumUtils.resolve(r)));
		if (r instanceof Float) {
			return (float) Math.pow(this.floatValue(), 1f / r.floatValue());
		}

		if (r instanceof Integer) {
			return (int) Math.pow(this.intValue(), 1 / r.intValue());
		}

		if (r instanceof Short) {
			return (short) Math.pow(this.shortValue(), 1 / r.shortValue());
		}

		if (r instanceof Long) {
			return (long) Math.pow(this.longValue(), 1l / r.longValue());
		}

		if (r instanceof Double) {
			return (double) Math.pow(this.doubleValue(), 1d / r.doubleValue());
		}

		if (r instanceof Byte) {
			return (byte) Math.pow(this.byteValue(), 1 / r.byteValue());
		}

		if (r instanceof aNumber) {
			// return this.root(aNumUtils.resolve(((aNumber) r).value));
			return NumberUtils.root(this, r);
		}

		if (r instanceof aVector) {
			// return this.root(aNumUtils.resolve(((aVector) r).value));
			return NumberUtils.root(this, r);
		}

		return this;
	}

	//
	// sin
	// cos
	// tan

	// OPERATORS
	////

	public Number mod(Number by) {
		if (by instanceof Float) {
			return (float) (this.floatValue() % by.floatValue());
		}

		if (by instanceof Integer) {
			return (int) (this.intValue() % by.intValue());
		}

		if (by instanceof Short) {
			return (short) (this.shortValue() % by.shortValue());
		}

		if (by instanceof Long) {
			return (long) (this.longValue() % by.longValue());
		}

		if (by instanceof Double) {
			return (double) (this.doubleValue() % by.doubleValue());
		}

		if (by instanceof Byte) {
			return (byte) (this.byteValue() % by.byteValue());
		}

		return this;
	}

	public Number abs() {

		Number a = this.value;
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

		return this;
	}

	public Number max(Number a, Number b) {
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

		return this;
	}

	public Number min(Number a, Number b) {
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

		return this;
	}

	public Number reflect() {
		return this.mul(-1);
	}

	/*
	 * public boolean oddEven() {
	 * 
	 * }
	 */

	public Number eValue() {
		return this.E();
	}

	protected Number E() {
		// e=(1+1/n)^n
		// e=(i+i/n)^n
		// e=(1+1/n)^n
		// e=(i+i/n)^n
		double inner = (this.i + (this.i / this.doubleValue()));
		double result = Math.pow(inner, this.doubleValue());
		return result;
	}

	@Override
	public int intValue() {
		return this.value.intValue();
	}

	@Override
	public long longValue() {
		return this.value.intValue();
	}

	@Override
	public float floatValue() {
		return this.value.floatValue();
	}

	@Override
	public double doubleValue() {
		return this.value.doubleValue();
	}

	@Override
	public short shortValue() {
		return this.value.shortValue();
	}

	@Override
	public byte byteValue() {
		return this.value.byteValue();
	}

	@Override
	public byte[] getBytes() {

		return new byte[] { this.byteValue() };
	}

	@Override
	public Object[] getBits() {
		Object[] res = new Object[] { this };
		return res;
	}

	@Override
	public Number Name() {
		return this.value;
	}

	@Override
	public _N Index() {
		return this.node;
	}

	@Override
	public String Symbol() {
		return "" + this.Name() + "&" + this.Index();
	}

	@Override
	public String toString() {

		return "" + this.value;
	}

	public String toLog() {
		String log = "(" + this.toString() + ")";
		log += "\n";

		return log;
	}

	@Override
	public _N new_N(_N index, Number name, String symbol) {
		// TODO Auto-generated method stub
		return null;
	}

}
