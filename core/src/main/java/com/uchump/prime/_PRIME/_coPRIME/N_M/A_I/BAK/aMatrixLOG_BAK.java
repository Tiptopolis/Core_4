package com.uchump.prime._PRIME._coPRIME.N_M.A_I;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.math.MathUtils;
import com.uchump.prime._PRIME._coPRIME.META.iCypher;
import com.uchump.prime._PRIME._coPRIME.N_M.NumberUtils;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uNumber;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

public class aMatrixLOG_BAK<M extends aVector<? extends Number>> extends aVector<aVector<Number>> {

	// each entry is an array of numbers, ie. aVector
	// matrix use big indian where vector use little indian, lololol
	// the Elements are a 1-D array of labels tMi, Mit?
	// this.value/t = number of colums

	// public HashMap<String, aVector<Number>> Index;
	public aVector Identity;
	public float density = 1f;

	public aMatrix() {
		super();
		// Object[] a = this.

	}

	public aMatrix(String n) {
		this(NumberUtils.str(n));
	}

	// DO NOT GO ABOVE 10
	public aMatrix(Number n) {
		super(new uVector(n.intValue()));
		this.t = n.floatValue() % 9;
		this.i = n.intValue() % 9;
		this.Identity = new uVector(n.intValue() * n.intValue());

		String[] l = new String[n.intValue() * n.intValue()];

		int c = 0;
		for (int i = 0; i < (n.intValue() * n.intValue()); i++) {

			String s = iCypher.zTo(iCypher.Digit(i, n.intValue()), n.intValue(), '0');
			l[i] = "M" + s;
			aNumber id = new uNumber(NumberUtils.str(s, Integer.class));
			uVector v = new uVector((i / n.intValue()) + n.intValue() * (i % n.intValue()));
			this.Identity.elements.add((i / n.intValue()) + n.intValue() * (i % n.intValue()));
			this.elements.add(new uVector(1));
			// ((aNumber)this.Identity.getElement(i)).setValue(Integer.parseInt(s));
		}

		this.label(l, true);
	}

	public aMatrix(aVector v) {
		super(v.value);
		this.i = v.i;
		this.t = v.t;
		// this.set(v);

	}

	public aMatrix(Number type, int indexLen) {
		super(indexLen);

	}

	public aMatrix(Collection<Number> elements) {
		super(elements.size());

	}

	public aMatrix(Number[] elements) {
		super(elements.length);

	}

	@Override
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

	@Override
	public _N Index() {
		return this.Identity;
	}

	public String toLog() {
		String log = "";
		log += "\n";
		log += "Matrix" + this.Identity + "{" + this.labels.size() + "}{";
		log += "\n";
		log += "<" + this.of.getSimpleName() + "{" + this.type + "}> + n_[" + this.value + "]";
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
		log += "\n";
		log += this.Identity + "\n"; // rank?

		String S = "" + this.Identity.elements.size();
		int l = S.length() - 1;
		////
		log += "\n";
		for (int i = 0; i < MathUtils.PI2 - 1; i++) {
			log += "_";
		}

		////

		for (int j = 0; j < (this.i * this.i); j++) {
			if (j % this.i != 0)
				log += "-";
			if (j % (this.i) == 0)
				log += "[" + (j % (this.i)) + (j / i) + "]";
		}

		////

		for (int j = 0; j < (this.i * this.i); j++) {
			String cm = "_";
			if (j % (this.i) == 0) {
				log += "\n" + "[" + (j % (this.i)) + (j / i) + "]";
				cm = ":";
			} else
				for (int i = j % (this.i); i <= ((l) + (j % (this.i))); i++)
					cm += "_";

			// log += cm + "{" + this.Identity.getElement(j) + "}";
			log += cm + "{" + iCypher.zTo(this.Identity.getElement(j).toString(), this.i - l, ' ') + "}";
		}
		log += "\n";
		log += "}";
		log += "\n";
		return log;
	}

}
