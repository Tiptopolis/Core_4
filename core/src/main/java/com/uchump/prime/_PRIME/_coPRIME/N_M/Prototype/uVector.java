package com.uchump.prime._PRIME._coPRIME.N_M.Prototype;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;
import java.util.Collection;

import com.uchump.prime._PRIME._coPRIME.N_M.NumberUtils;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aVector;

public class uVector extends aVector<Number> {

	public float x = 0;
	public float y = 0;
	public float z = 0;
	public float w = 0;

	public uVector() {
		super(1);
	}

	public uVector(Number n) {
		super(n);
		this.prelabel();
	}

	public uVector(aVector v) {
		super(v);
		this.prelabel();
	}

	public uVector(Object type, int indexLen) {
		super(indexLen);
		this.i = indexLen;
		this.t = 1;
		this.type = (Number) type;
		this.of = type.getClass();
		this.elements.ensureCapacity(i);
		this.prelabel();

	}

	public uVector(Collection<Number> elements) {
		super(elements.size());
		this.elements.ensureCapacity(elements.size());
		this.elements.addAll(elements);
		this.i = elements.size();
		this.t = 1;
		Class ofArray = elements.toArray().getClass().getComponentType();
		this.of = ofArray;
		this.type = (Number) (NumberUtils.resolve(NumberUtils.mul(NumberUtils.resolve(elements.toArray()[0]), 0)));
		this.prelabel();

	}

	public uVector(Number[] elements) {
		super(elements.length);
		for (Number n : elements) {
			if (!(n == null))
				this.elements.add(n);
			else {
				this.elements.add(new uNumber((Number) 0));
			}
		}
		this.i = elements.length;
		Class ofArray = elements.getClass().getComponentType();
		this.of = ofArray;
		this.type = (Number) (NumberUtils.resolve(NumberUtils.mul(NumberUtils.resolve(elements[0]), 0)));
		this.prelabel();

	}

	public uVector(com.badlogic.gdx.math.Vector2 v) {
		super(new Float[] { v.x, v.y });
		this.prelabel();
	}

	public uVector(com.badlogic.gdx.math.Vector3 v) {
		super(new Float[] { v.x, v.y, v.z });
		this.prelabel();
	}

	public uVector(com.badlogic.gdx.math.Quaternion q) {
		super(new Float[] { q.x, q.y, q.z, q.w });
		this.prelabel();
	}

	@Override
	protected void prelabel() {
		super.prelabel();

		if (this.elements != null && !this.elements.isEmpty())
			for (int i = 0; i < this.elements.size() - 1; i++) {
				if (i == 0)
					this.x = (float) this.elements.get(0).floatValue();
				if (i == 1)
					this.y = (float) this.elements.get(1).floatValue();
				if (i == 2)
					this.y = (float) this.elements.get(2).floatValue();
				if (i == 3)
					this.y = (float) this.elements.get(3).floatValue();
			}

		this.label(new char[] { 'x', 'y', 'z', 'w' }, true);
	}

	@Override
	public void label(char[] lbls, boolean override) {
		
		super.label(lbls, override);
	}

	public Number getElement(String label) {
		Number i = -1;

		for (String s : this.labels) {
			if (s.equals(label))
				i = this.labels.indexOf(s);
		}

		return i;
	}

	public String getLabel(int index) {
		if (this.labels.get(index) != null)
			return this.labels.get(index);
		else
			return "" + index;

	}

	@Override
	public uVector abs() {
		return (uVector) super.abs();
	}

	@Override
	public Object[] keyArray() {
		return this.labels.toArray();
	}

	@Override
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
				log += "[" + i + "]" + "{" + this.labels.get(i) + "}" + "(" + n + ")" + "<"
						+ n.getClass().getSimpleName() + ">";
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

}
