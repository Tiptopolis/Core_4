package com.uchump.prime._PRIME._coPRIME.N_M.A_I;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.SnapshotArray;
import com.uchump.prime._PRIME._coPRIME.N_M.Maths;
import com.uchump.prime._PRIME._coPRIME.N_M.NumberUtils;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector2;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.Vector3;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uNumber;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

import net.dermetfan.utils.Pair;

public abstract class aVector<T extends Number> extends aNumber implements Serializable, Vector<aVector<T>> {

	// attempt to create a universal n-dimensional vector
	// defaults to float value

	public ArrayList<String> labels = new ArrayList<String>();
	public ArrayList<Number> elements = new ArrayList<Number>(); // terms, components
	protected Class of;
	protected T type;// type of backing Number

	public Number first;
	public Number last;

	// GL Attribute?

	public aVector() {
		super(1);

	}

	public aVector(Number n) {
		super(n);
		// this.value = 1;
		this.i = n.intValue();
		this.t = 1;
		this.of = n.getClass();
		this.type = (T) (NumberUtils.resolve(n));

	}

	public aVector(aVector v) {
		super(v.value);
		this.set(v);

	}

	public aVector(Number type, Number indexLen) {
		super(indexLen);
		this.i = type.intValue();
		this.t = indexLen.intValue();
		this.type = (T) type;
		this.of = type.getClass();
		this.elements.ensureCapacity(i);

	}

	public aVector(Collection<Number> elements) {
		super(elements.size());
		this.elements.addAll(elements);

		this.i = elements.size();
		this.t = 1;
		Class ofArray = elements.toArray().getClass().getComponentType();
		this.of = ofArray;
		this.type = (T) (NumberUtils.resolve(NumberUtils.mul(NumberUtils.resolve(elements.toArray()[0]), 0)));
	}

	public aVector(Number[] elements) {
		super(elements.length);
		for (Number n : elements) {
			if (!(n == null))
				this.elements.add(n);
			else {
				this.elements.add(new uNumber(0));
			}
		}
		this.i = elements.length;
		Class ofArray = elements.getClass().getComponentType();
		this.of = ofArray;
		this.type = (T) (NumberUtils.resolve(NumberUtils.mul(NumberUtils.resolve(elements[0]), 0)));
	}

	public aVector(Object[] elements) {
		super(elements.length);
		this.i = elements.length;
		Class ofArray = elements.getClass().getComponentType();
		this.of = ofArray;
		this.type = (T) (NumberUtils.resolve(NumberUtils.mul(NumberUtils.resolve(elements[0]), 0)));
		this.set(elements);
	}

	/////////////////////////////////////////////////////
	//
	@Override
	public Object[] getBits() {
		if (this.elements == null || this.elements.isEmpty()) {
			return super.getBits();
		}

		return this.elements.toArray();
	}

	public Object[] keyArray() {
		return this.elements.toArray();
	}

	public Object[] valueArray() {
		return this.elements.toArray();
	}

	public Number getElement(int index) {
		return this.elements.get(index);
	}

	/////////////////////////////////////////////////////
	//

	public aVector idt() {
		for (Number n : this.elements) {
			if (n instanceof aVector) {
				((aVector) n).set(1);
			} else
				n = 1;
		}
		return this;
	}

	@Override
	protected void prelabel() {
		super.prelabel();
		this.labels = new ArrayList<String>();
		for (Number n : this.elements) {
			this.labels.add("" + n);
		}
	}

	protected void label(char[] lbls, boolean override) {
		this.labels.clear();
		int i = 0;
		if (override) {
			i = (lbls.length);
		} else {
			i = (this.elements.size());
		}
		this.labels.ensureCapacity(i);
		for (int e = 0; e < i; e++) {
			this.labels.add("" + lbls[e]);
		}
	}

	protected void label(String[] lbls, boolean override) {
		this.labels.clear();
		int i = 0;
		if (override) {
			i = (lbls.length);
		} else {
			i = (this.elements.size());
		}
		this.labels.ensureCapacity(i);
		for (int e = 0; e < i; e++) {
			this.labels.add(lbls[e]);
		}
	}

	public aVector<T> set(Number n) {
		for (Number N : this.elements) {
			N = n;
		}

		return this;
	}

	@Override
	public aVector<T> set(aVector<T> v) {
		this.value = v.value;
		for (Number o : v.elements) {
			this.elements.add(o);
		}
		this.i = v.elements.size();
		this.of = v.of;
		this.type = v.type;

		// this.type = aNumUtils.resolveF(v.value);

		return this;
	}

	public aVector<T> set(Number[] elements) {
		this.elements.clear();
		this.value = elements.length;
		for (Number o : elements) {
			this.elements.add(o);
		}
		this.i = elements.length;
		Class<T> ofArray = (Class<T>) elements.getClass().getComponentType();
		this.of = ofArray;
		this.type = (T) (NumberUtils.resolve(NumberUtils.mul(NumberUtils.resolve(elements[0]), 0)));

		return this;
	}

	public aVector<T> set(Object[] elements) {
		Number[] N = new Number[elements.length];
		if (elements.getClass().arrayType().isAssignableFrom(Number.class)) {
			for (int i = 0; i < elements.length; i++)
				if (elements[i].getClass().arrayType().isAssignableFrom(Number.class))
					N[i] = NumberUtils.resolve(elements[i]);
				else
					N[i] = 0;
		}
		return this.set(N);
	}

	public aVector<T> set(Collection<Number> elements) {
		this.value = elements.size();
		for (Number o : elements) {
			this.elements.add(o);
		}
		this.i = elements.size();
		Class ofArray = elements.getClass().getComponentType();
		this.of = ofArray;
		this.type = (T) (NumberUtils.resolve(NumberUtils.mul(NumberUtils.resolve(elements.toArray()[0]), 0)));

		return this;
	}

	@Override
	public aVector add(aVector v) {
		// defaults to 0

		if (!(Number.class.isAssignableFrom(v.of))) {
			return this;
		}
		for (int u = 0; u < this.i; u += t) {
			Number v1;
			Number v2;
			if (u < v.i) {
				v1 = (Number) this.elements.get(u);
				v2 = (Number) v.elements.get(u);
			} else {
				v1 = 0;
				v2 = 0;
			}
			this.elements.set(u, NumberUtils.add(v1, v2));

		}

		return this;
	}

	@Override
	public aVector add(Number n) {
		for (Object o : this.elements) {
			int index = this.elements.indexOf(o);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				Number E = (Number) o;
				// E.add(n);
				this.elements.set(index, NumberUtils.add(NumberUtils.resolve(E), NumberUtils.resolve(n)));
			}
		}

		return this;
	}

	@Override
	public aVector sub(aVector v) {
		if (!(Number.class.isAssignableFrom(v.of))) {
			return this;
		}
		for (int u = 0; u < this.i; u += t) {
			Number v1;
			Number v2;
			if (u < v.i) {
				v1 = (Number) this.elements.get(u);
				v2 = (Number) v.elements.get(u);
			} else {
				v1 = 0;
				v2 = 0;
			}
			this.elements.set(u, NumberUtils.sub(v1, v2));
		}

		return this;
	}

	@Override
	public aVector sub(Number n) {
		for (Object o : this.elements) {
			int index = this.elements.indexOf(o);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				Number E = (Number) o;
				// E.sub(n);
				this.elements.set(index, NumberUtils.sub(NumberUtils.resolve(E), NumberUtils.resolve(n)));
			}
		}

		return this;
	}

	public aVector mul(aVector v) {
		// acts on components

		if (!(Number.class.isAssignableFrom(v.of))) {
			return this;
		}
		for (int u = 0; u < this.i; u += t) {
			Number v1;
			Number v2;
			if (u < v.i) {
				v1 = (Number) this.elements.get(u);
				v2 = (Number) v.elements.get(u);
			} else {
				v1 = 0;
				v2 = 0;
			}
			this.elements.set(u, NumberUtils.mul(v1, v2));
		}

		return this;
	}

	@Override
	public aVector mul(Number n) {
		for (Object o : this.elements) {
			int index = this.elements.indexOf(o);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				Number E = (Number) o;
				// E.mul(n);
				this.elements.set(index, NumberUtils.mul(NumberUtils.resolve(E), NumberUtils.resolve(n)));
			}
		}

		return this;
	}

	public aVector div(aVector v) {
		// divide each by coresponding v.element if available
		if (!(Number.class.isAssignableFrom(v.of))) {
			return this;
		}
		for (int u = 0; u < this.i; u += t) {
			Number v1;
			Number v2;
			if (u < v.i) {
				v1 = (Number) this.elements.get(u);
				v2 = (Number) v.elements.get(u);
			} else {
				v1 = -0;
				v2 = -1;
			}
			this.elements.set(u, NumberUtils.div(v1, v2));
		}

		return this;
	}

	@Override
	public aVector div(Number n) {
		// divide each element by n
		for (Object o : this.elements) {
			int index = this.elements.indexOf(o);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				Number E = (Number) o;
				// E.div(n);
				this.elements.set(index, NumberUtils.div(NumberUtils.resolve(E), NumberUtils.resolve(n)));
			}
		}

		return this;
	}

	public aVector pow(aVector v) {
		// calculate each elements to the n-th power
		if (!(Number.class.isAssignableFrom(v.of))) {
			return this;
		}
		for (int u = 0; u < this.i; u += t) {
			Number v1 = (Number) this.elements.get(u);
			Number v2;
			if (u < v.i)
				v2 = (Number) v.elements.get(u);
			else
				v2 = 1;
			this.elements.set(u, NumberUtils.pow(v1, v2));
		}

		return this;
	}

	@Override
	public aVector pow(Number n) {
		// calculate each elements to the n-th power

		for (Object o : this.elements) {
			int index = this.elements.indexOf(o);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				Number E = NumberUtils.resolveTo(NumberUtils.resolve(o));
				// E.pow(n);
				this.elements.set(index, Math.pow(E.doubleValue(), n.doubleValue()));
			}
		}

		return this;
	}

	public aVector root(aVector v) {
		// calculate each elements to the n-th power
		if (!(Number.class.isAssignableFrom(v.of))) {
			return this;
		}
		for (int u = 0; u < this.i; u += t) {
			Number v1 = (Number) this.elements.get(u);
			Number v2;
			if (u < v.i)
				v2 = (Number) v.elements.get(u);
			else
				v2 = 1;
			this.elements.set(u, NumberUtils.root(v1, v2));
		}

		return this;
	}

	@Override
	public aVector root(Number r) {
		// calculate each elements to the r-th root
		Number R = NumberUtils.div(1d, NumberUtils.resolve(r));

		for (Object o : this.elements) {
			int index = this.elements.indexOf(o);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				// E.pow(1/t);
				Number E = new uNumber(NumberUtils.resolve(o));
				this.elements.set(index, Math.pow(E.floatValue(), R.floatValue()));
			}

		}

		return this;
	}

	public aVector abs() {

		for (Object o : this.elements) {
			int index = this.elements.indexOf(o);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				Number E = (Number) o;
				// E.mul(n);
				this.elements.set(index, NumberUtils.abs(NumberUtils.resolve(E)));
			}
		}

		return this;
	}

	public aVector mod(aVector modby) {
		// acts on components

		if (!(Number.class.isAssignableFrom(modby.of))) {
			return this;
		}
		for (int u = 0; u < this.i; u += t) {
			Number v1;
			Number v2;
			if (u < modby.i) {
				v1 = (Number) this.elements.get(u);
				v2 = (Number) modby.elements.get(u);
			} else {
				v1 = 0;
				v2 = 0;
			}
			this.elements.set(u, NumberUtils.mod(v1, v2));
		}

		return this;
	}

	public Number mod(Number modBy) {/// %mod lol

		for (Object o : this.elements) {
			int index = this.elements.indexOf(o);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				Number E = (Number) o;
				// E.mul(n);
				this.elements.set(index, NumberUtils.mod(NumberUtils.resolve(E), NumberUtils.resolve(modBy)));
			}
		}

		return this;

	}

	public Number rem(Number modBy) {
		return this.mod(modBy);
	}

	public aVector rem(aVector modBy) {
		return this.mod(modBy);
	}

	public aVector max(aVector modby) {
		// acts on components

		if (!(Number.class.isAssignableFrom(modby.of))) {
			return this;
		}
		for (int u = 0; u < this.i; u += t) {
			Number v1;
			Number v2;
			if (u < modby.i) {
				v1 = (Number) this.elements.get(u);
				v2 = (Number) modby.elements.get(u);
			} else {
				v1 = 0;
				v2 = 0;
			}
			this.elements.set(u, NumberUtils.max(v1, v2));
		}

		return this;
	}

	public aVector max(Number modBy) {/// %mod lol

		for (Object o : this.elements) {
			int index = this.elements.indexOf(o);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				Number E = (Number) o;
				// E.mul(n);
				this.elements.set(index, NumberUtils.max(NumberUtils.resolve(E), NumberUtils.resolve(modBy)));
			}
		}

		return this;
	}

	public aVector min(aVector modby) {
		// acts on components

		if (!(Number.class.isAssignableFrom(modby.of))) {
			return this;
		}
		for (int u = 0; u < this.i; u += t) {
			Number v1;
			Number v2;
			if (u < modby.i) {
				v1 = (Number) this.elements.get(u);
				v2 = (Number) modby.elements.get(u);
			} else {
				v1 = 0;
				v2 = 0;
			}
			this.elements.set(u, NumberUtils.min(v1, v2));
		}

		return this;
	}

	public aVector min(Number modBy) {/// %mod lol

		for (Object o : this.elements) {
			int index = this.elements.indexOf(o);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				Number E = (Number) o;
				// E.mul(n);
				this.elements.set(index, NumberUtils.min(NumberUtils.resolve(E), NumberUtils.resolve(modBy)));
			}
		}

		return this;
	}

	////
	// Stats
	// &&?
	public float sum() {
		// Sum
		// sigma integral direct sum of all elements
		float c = 0f;
		for (int u = 0; u < this.i; u++) {

			Object o = this.elements.get(u);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				c += NumberUtils.resolve(o).floatValue();
			}
		}
		return c;
	}

	public float dif() {
		// Difference
		// sigma anti-sum of all elements
		float c = 0f;
		for (int u = 0; u < this.i; u++) {

			Object o = this.elements.get(u);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				c -= NumberUtils.resolve(o).floatValue();
			}
		}
		return c;
	}

	public float pro() {
		// Product
		float c = 1f;
		for (int u = 0; u < this.i; u++) {

			Object o = this.elements.get(u);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				if (NumberUtils.resolve(o).floatValue() != 0)
					c *= NumberUtils.resolve(o).floatValue();

			}
		}
		return c;
	}

	public float quo() {
		// Quotient
		float c = 1f;
		for (int u = 0; u < this.i; u++) {

			Object o = this.elements.get(u);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				if (NumberUtils.resolve(o).floatValue() != 0)
					c /= NumberUtils.resolve(o).floatValue();

			}
		}
		return c;

	}

	public float avg() {
		// average
		float c = 0f;
		for (int u = 0; u < this.i; u++) {
			Object o = this.elements.get(u);
			if ((Number.class.isAssignableFrom(o.getClass()))) {
				c += NumberUtils.resolve(o).floatValue();
			}
		}
		return (c / this.i);
	}

	public float med() {
		// median value, even odd
		float c = 0f;
		if (this.i == 0)
			return 0;
		if (this.i == 1)
			return this.value.floatValue();

		int m = this.i;
		if (Maths.isEven(m))
			c = this.i / 2f;
		if (Maths.isOdd(m))
			c = Math.round((this.i) / 2);

		Number result = (Number) this.elements.get((int) c);
		return result.floatValue();
	}

	public float mode() {
		// mode
		// useful in finding the 'degree' of a polynomial lol
		Number c = 0f;
		HashMap<Number, Integer> counter = new HashMap<Number, Integer>();
		// count
		for (Object o : this.elements) {
			if ((Number.class.isAssignableFrom(o.getClass()))) {

				c = NumberUtils.resolve(o);
				if (!counter.containsKey(c))
					counter.putIfAbsent(c, 0);
				else {
					// map.put(key, map.get(key) + 1);
					counter.put(c, counter.getOrDefault(c, 0) + 1);
				}
			}
		}

		// compare
		Iterator<Map.Entry<Number, Integer>> it = counter.entrySet().iterator();
		int count = -1;
		float mode = 0;
		while (it.hasNext()) {
			Map.Entry<Number, Integer> e = (Map.Entry<Number, Integer>) it.next();
			if (e.getValue().intValue() >= count) {
				count = e.getValue().intValue();
				mode = e.getKey().floatValue();
			}
			it.remove();
		}

		return mode;
	}

	// Stats
	////

	@Override
	public aVector limit(float limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public aVector limit2(float limit2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public aVector clamp(float min, float max) {
		final float len2 = len2();
		if (len2 == 0f) {
			return this;
		}
		final float max2 = max * max;
		if (len2 > max2) {
			return scl((float) Math.sqrt(max2 / len2));
		}
		final float min2 = min * min;
		if (len2 < min2) {
			return mul((float) Math.sqrt(min2 / len2));
		}
		return this;
	}

	public static aVector getShorter(aVector a, aVector b) {
		if (a.elements.size() == b.elements.size())
			return a;

		if (a.elements.size() > b.elements.size())
			return a;
		else
			return b;
	}

	public static aVector getLonger(aVector a, aVector b) {
		if (a.elements.size() == b.elements.size())
			return b;
		if (b.elements.size() > a.elements.size())
			return b;
		else
			return a;
	}

	//////
	//

	@Override
	public aVector nor() {

		final float len2 = this.len2();
		final float len = this.len();
		Number R = NumberUtils.div(1d, NumberUtils.resolve(len));
		if (len2 == 0f || len2 == 1f) {
			return this;
		}
		this.mul(R);
		return this;

		// return this;
	}

	public aVector crs(aVector other) {
		// wedge, inner product technical crs only works in 3D
		// return this.set(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x *
		// vector.y - y * vector.x);
		// Vector3 V = V.crs(v);
		// return this.set(y * vector.z - z * vector.y, z * vector.x - x * vector.z, x *
		// vector.y - y * vector.x);

		// n is r? r is rdx? //x,y,z->y,z,x
		// so the phantom n%1 is the shif-raddix r
		// where n is len of vector with shortest len lol
		// for(int i = 1; i>=n; i++) //starts w/ 1, ends with 0, shif-left lol
		// if i>n, i=0;
		// this.setX((a[1/n]*b[n-1])-(a[n-1]*b[1/n]));
		// this.setY((a[]
		// this.setZ()
		// this.setN()
		// scanning inwards... hmm...

		// Since {eiâˆ§ej, 1â‰¤i<jâ‰¤n} turns out to be a basis for Î›2(V) //TimeKey i,
		// e,
		// j:t:o, d:n

		Number[] IndexA = NumberUtils.scanFrom(this.elements.toArray());
		Number[] IndexB = NumberUtils.scanFrom(other.elements.toArray());

		Number[] crsRes = new Number[3];
		crsRes[0] = ((IndexA[1].floatValue() * IndexB[2].floatValue())
				- (IndexA[2].floatValue() * IndexB[1].floatValue()));
		crsRes[1] = ((IndexA[2].floatValue() * IndexB[0].floatValue())
				- (IndexA[0].floatValue() * IndexB[2].floatValue()));
		crsRes[2] = ((IndexA[0].floatValue() * IndexB[1].floatValue())
				- (IndexA[1].floatValue() * IndexB[0].floatValue()));
		this.set(crsRes);

		// j= a x b
		// k= -b x a
		// sum
		// result is this.set(j.add(k));

		//

		return this;
	}

	@Override
	public float dot(aVector v) {
		// outter product

		int count = this.i; //
		float result = 0f;
		for (int i = 0; i < count; i++) {
			if (v.elements.get(i) != null)
				result += NumberUtils.resolveF(NumberUtils.mul(NumberUtils.resolve(this.elements.get(i)),
						NumberUtils.resolve(v.elements.get(i))));
			else
				result += 0f;
		}
		return result;

	}

	public float dot(aVector v, int offset) {

		int count = this.i; //
		float result = 0f;
		for (int i = 0; i < count; i++) {
			if (v.elements.get(offset + i) != null)
				result += NumberUtils.resolveF(NumberUtils.mul(NumberUtils.resolve(this.elements.get(i)),
						NumberUtils.resolve(v.elements.get(offset + i))));
		}
		return result;

	}

	public float dot(Number[] data, int offset) {

		int count = this.i; //
		float result = 0.0f;
		for (int i = 0; i < count; i++) {
			if (data[offset + i] != null)
				result += NumberUtils.resolveF(NumberUtils.mul(NumberUtils.resolve(this.elements.get(i)),
						NumberUtils.resolve(data[offset + i])));
			else
				result += 0f;
		}
		return result;

	}

	public float dot(Number[] data) {

		int count = this.i; //
		float result = 0.0f;
		for (int i = 0; i < count; i++) {
			if (data[i] != null)
				result += NumberUtils.resolveF(
						NumberUtils.mul(NumberUtils.resolve(this.elements.get(i)), NumberUtils.resolve(data[i])));
			else
				result += 0f;
		}
		return result;

	}

	////
	//

	////
	// Geometry

	@Override
	public float len() {
		// normalized euclidean magnitude/length of vector
		if (this.elements == null)
			return 0;
		if (this.elements.isEmpty())
			return 1;
		return (float) Math.sqrt(this.len2());
		// return this.i;
	}

	@Override
	public float len2() {
		// normalized euclidean magnitude/length^2 of vector
		if (this.elements == null)
			return 0;
		if (this.elements.isEmpty())
			return 1;
		uVector tmp = new uVector(this.cpy());
		// return tmp.pow(2f).sum();
		return tmp.pow(2f).sum();
	}

	public float mag() {
		return this.len2();
	}

	public float length() {
		return this.len();
	}

	public float lenPow() {
		// normalized euclidean magnitude/length^2 of vector
		if (this.elements == null)
			return 0;
		if (this.elements.isEmpty())
			return 1;
		uVector tmp = new uVector(this.cpy());
		// return tmp.pow(2f).sum();
		return tmp.pow(this.elements.size()).sum();
	}

	// acts on t, len, e?
	// OR
	// expands the index to fit
	@Override
	public aVector scl(float scalar) {
		this.setLength2(scalar);
		return this;
	}

	@Override
	public aVector scl(aVector v) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public float dst(aVector v) {
		// euclidean distance, len without nor()
		return 0;
	}

	@Override
	public float dst2(aVector v) {
		// euclidean distance^2, len2 without nor()
		return 0;
	}

	@Override
	public aVector lerp(aVector target, float alpha) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public aVector setLength(float len) {
		// resize
		// cull
		// SnapshotArray<Object> tmp = new
		// SnapshotArray<Object>(this.elements.toArray());

		ArrayList<Number> tmp = (ArrayList<Number>) this.elements.clone();
		this.elements.clear();
		for (int u = 0; u < (len * len); u += t) {
			Number v1;

			if (u <= this.i)
				this.elements.add(tmp.get(u));
			else
				this.elements.add(0);

		}

		return this;
	}

	@Override
	public aVector setLength2(float len2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public aVector interpolate(aVector target, float alpha, Interpolation interpolator) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public aVector setToRandomDirection() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Number eValue() {
		return this.E();
	}

	@Override
	protected Number E() {
		// e=(1+1/n)^n
		// e=(i+i/n)^n
		// e=(1+1/n)^n
		// e=(i+i/n)^n

		// final float len2 = this.len2();
		// final float len = this.len();
		// Number R = NumberUtils.div(1d, NumberUtils.resolve(len));
		// if (len2 == 0f || len2 == 1f) {
		// return this;
		// }
		// this.mul(R);
		// return this;

		if (this.elements != null) {

			if (!this.elements.isEmpty()) {
				// return this.cpy().nor().len();
				float result = (float) Math.pow(1 + (1 / this.len()), this.len());
				// float result = (float)
				// Math.pow(1+(this.elements.size()/this.len()),this.len());
				return result;
			} else
				return super.E();
		} else
			return 1;

	}

	@Override
	public boolean isUnit() {
		return isUnit(0.000000001f);
	}

	@Override
	public boolean isUnit(final float margin) {
		return Math.abs(len2() - 1f) < margin;
	}

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isZero(float margin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOnLine(aVector other, float epsilon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOnLine(aVector other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollinear(aVector other, float epsilon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollinear(aVector other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollinearOpposite(aVector other, float epsilon) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCollinearOpposite(aVector other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPerpendicular(aVector other) {
		// TODO Auto-generated method stub
		return this.isPerpendicular(other, this.epsilon);
	}

	@Override
	public boolean isPerpendicular(aVector other, float epsilon) {
		this.epsilon = epsilon;
		return false;
	}

	@Override
	public boolean hasSameDirection(aVector other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasOppositeDirection(aVector other) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean epsilonEquals(aVector other) {
		return this.epsilonEquals(other, epsilon);
	}

	@Override
	public boolean epsilonEquals(aVector other, float epsilon) {
		this.epsilon = epsilon;
		return false;
	}

	public boolean epsilonEquals(aNumber other) {
		return this.epsilonEquals(other, epsilon);
	}

	public boolean epsilonEquals(aNumber other, float epsilon) {
		this.epsilon = epsilon;
		return false;
	}

	@Override
	public aVector mulAdd(aVector v, float scalar) {
		// like how sum (x^2)+(y^2)+(z^2)+...(n^2)
		return null;
	}

	@Override
	public aVector mulAdd(aVector v, aVector mulVec) {

		return null;
	}

	@Override
	public aVector setZero() {
		// TODO Auto-generated method stub
		return this.set(new Integer[] { 0 });
	}

	public Number[] forEach() {
		return this.forEach(0, this.i);
	}

	public Number[] forEach(int indexFrom, int indexTo) {

		// experimental and unused, be careful dickhead

		// clamp
		MathUtils.clamp(indexFrom, 0, this.elements.size() - 1);
		MathUtils.clamp(indexTo, indexFrom, this.elements.size() - 1);
		// index offset, 1d
		int i = 1;
		if (indexFrom == indexTo)
			i = 0;
		else
			i = indexTo - indexFrom;
		Number[] offsetIndex = new Number[i];

		// lookup
		for (int I = 0; I < i; I++) {
			offsetIndex[I] = NumberUtils.resolve(this.elements.get(indexFrom + i));
		}

		return offsetIndex;

	}

	@Override
	public aVector<T> cpy() {
		aVector<T> newVect = (aVector<T>) new uVector(this);

		// newVect.set(this);

		return newVect;
	}

	public void overrideType(T type) {
		this.type = (T) (NumberUtils.resolve(type));
	}

	@Override
	public String toString() {
		String vect = "";

		if (!this.elements.isEmpty()) {
			vect += "(";
			for (Object n : this.elements) {
				if (n != null) {
					if (!(Number.class.isAssignableFrom(n.getClass()))) {
						vect = ("non-Number: " + n.getClass().getSimpleName());
						return vect;
					}

					Number N = (Number) n;
					vect += N.toString();
					if (this.elements.indexOf(n) < this.elements.size() - 1)
						vect += ",";
				}
			}
			vect += ")";
			return vect;
		} else
			return this.value.toString();
	}

	public String toLog() {
		String log = "";
		log += "\n";
		log += "Vector" + this.elements.size() + "{" + this.labels.size() + "}{";
		log += "\n";
		log += "<" + this.of.getSimpleName() + "{" + this.type.getClass().getSimpleName() + ":" + this.type + "}> + n_["
				+ this.value + "]";
		log += "\n";
		log += "[i: " + this.i + " |t: " + this.t + "] e_" + this.E();
		log += "\n";
		if (!this.elements.isEmpty()) {
			for (Object n : this.elements) {
				int i = this.elements.indexOf(n);
				log += "[" + i + "]" + "(" + n + ")";
				log += "\n";
			}
		} else {
			log += this.value.toString();
			log += "\n";
		}
		log += "}";
		log += "\n";
		return log;
	}

	
	public aVector gdx(com.badlogic.gdx.math.Vector2 vector)
	{
		return new uVector(new Float[] {vector.x,vector.y});
		
	}
	
	public aVector gdx(com.badlogic.gdx.math.Vector3 vector)
	{
		return new uVector(new Float[] {vector.x,vector.y, vector.z});
		
	}
	
	public aVector gdx(com.badlogic.gdx.math.Quaternion q)
	{
		return new uVector(new Float[] {q.x,q.y, q.z, q.w});
		
	}
	
	public com.badlogic.gdx.math.Vector gdx() {
		if (this.elements.size() <= 2) {
			return this.gdxV2();
		}
		
		return this.gdxV3();
		
		
	}
	
	public Vector2 V2()
	{
		float x = 0;
		float y = 0;

		if (this.elements.get(0) != null)
			x = this.elements.get(0).floatValue();
		if (this.elements.get(1) != null)
			y = this.elements.get(1).floatValue();
		return new Vector2(x,y);
	}
	
	public Vector3 V3()
	{
		float x = 0;
		float y = 0;
		float z = 0;

		if (this.elements.get(0) != null)
			x = this.elements.get(0).floatValue();
		if (this.elements.get(1) != null)
			y = this.elements.get(1).floatValue();
		if (this.elements.get(1) != null)
			z = this.elements.get(1).floatValue();
		return new Vector3(x,y,z);
	}

	public com.badlogic.gdx.math.Vector2 gdxV2() {
		float x = 0;
		float y = 0;

		if (this.elements.get(0) != null)
			x = this.elements.get(0).floatValue();
		if (this.elements.get(1) != null)
			y = this.elements.get(1).floatValue();

		com.badlogic.gdx.math.Vector2 res = new com.badlogic.gdx.math.Vector2(x, y);

		return res;
	}

	public com.badlogic.gdx.math.Vector3 gdxV3() {
		float x = 0;
		float y = 0;
		float z = 0;

		if (this.elements.get(0) != null)
			x = this.elements.get(0).floatValue();
		if (this.elements.get(1) != null)
			y = this.elements.get(1).floatValue();
		if (this.elements.get(2) != null)
			z = this.elements.get(2).floatValue();

		com.badlogic.gdx.math.Vector3 res = new com.badlogic.gdx.math.Vector3(x, y, z);

		return res;
	}

	public com.badlogic.gdx.math.Quaternion gdxQ() {
		float x = 0;
		float y = 0;
		float z = 0;
		float w = 1;

		if (this.elements.get(0) != null)
			x = this.elements.get(0).floatValue();
		if (this.elements.get(1) != null)
			y = this.elements.get(1).floatValue();
		if (this.elements.get(2) != null)
			z = this.elements.get(2).floatValue();
		if (this.elements.get(3) != null)
			z = this.elements.get(3).floatValue();

		com.badlogic.gdx.math.Quaternion res = new com.badlogic.gdx.math.Quaternion(x, y, z, w);

		return res;
	}

	public static class LinearCombination<L, R> extends Pair<L, R> {
		// an expression constructed from a set of terms by multiplying each term by a
		// constant and adding the results
		// (e.g. a linear combination of x and y would be any expression of the form ax
		// + by, where a and b are constants)

		// Doubly-dependent, anchors x&y to scalars a&b

		// Sum();

		// TYPE = CONSTRAINTS : NameOfSet : ModelSpace
		// Linear = unrestricted : VectorSubspace : {R^n}
		// Affine = $KE (Sigma(a_i)=1) : AffineSubspace : AffineHyperplane
		// Conical= a_i>=0 : ConvexCone : Quadrant, Octant, Orthant
		// Convex = Connical&Affine : ConvexSet : Simplex

		//
	}

}
