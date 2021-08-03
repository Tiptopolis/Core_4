package com.uchump.prime._PRIME._coPRIME.N_M.A_I;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.math.MathUtils;
import com.uchump.prime._PRIME._coPRIME.META.iCypher;
import com.uchump.prime._PRIME._coPRIME.N_M.NumberUtils;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uMatrix;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uNumber;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

public class aMatrix<M extends aVector<? extends Number>> extends aVector<aVector<Number>> {
	// a colum-major matrix :!: DO NOT GO OVER >9<
	// can use Number or String
	// each entry is an array of numbers, ie. aVector
	// matrix use big indian where vector use little indian, lololol
	// the Elements are a 1-D array of labels tMi, Mit?
	// this.value/t = number of colums

	// public HashMap<String, aVector<Number>> Index;
	public aVector index;
	public float density = 1f;

	public aMatrix() {
		super();
		// Object[] a = this.

	}

	public aMatrix(String n) {
		this(NumberUtils.str(n));
	}

	public aMatrix(uMatrix M) {
		this(M.elements);

	}

	// DO NOT GO ABOVE 10
	public aMatrix(Number n) {
		super(new uVector(n.intValue()));
		this.i = n.intValue();
		this.t = n.floatValue();		
		this.index = new uVector(n.intValue() * n.intValue());

		String[] l = new String[n.intValue() * n.intValue()];
		this.genIndex(l, 1f);
		

		this.label(l, true);
	}

	

	public aMatrix(aVector v) {
		super(v);
		this.t = type.floatValue();
		this.index = new uVector(this.i * this.i);
		// this.set(v);

	}

	// USE THIS FOR 10-LIMIT PROBLEM TESTING
	public aMatrix(Number indexLen, Number type) {
		super(new uVector(type.floatValue()));
		this.i = indexLen.intValue();
		this.t = type.floatValue();
		this.index = new uVector(this.i * this.i);

		String[] l = new String[this.i * this.i];
		this.genIndex( l,type);		
		this.label(l, true);
	}

	public aMatrix(Collection<Number> elements) {
		super(elements.size());
		this.i = elements.size();
		this.t = type.floatValue();
		this.index = new uVector(this.i * this.i);

	}

	public aMatrix(Number[] elements) {
		super(elements);
		this.i = elements.length;
		this.t = NumberUtils.resolve(type).floatValue();
		this.index = new uVector(this.i * this.i);

	}
	
	public aMatrix(Object[] elements) {
		super(elements);
		this.i = elements.length;
		this.t = type.floatValue();
		this.index = new uVector(this.i * this.i);
	}

	protected uVector unfold(Number type) {

		return new uVector(type);
	}
	
	protected void genIndex(String[] l,Number type)
	{
		int c = 0;
		for (int i = 0; i < (this.i * this.i); i++) {

			String label = "" + this.i;
			int j = label.length();
			int J = j % this.i;
			J = (j - i);

			// int I = MathUtils.clamp(J, 3, 3+(3-j));
			int I = MathUtils.clamp(j, 3, J);

			// String s = iCypher.zTo(iCypher.Digit(i, indexLen.intValue()), /* n.intValue()
			// 3 */3, '0');
			String s = iCypher.zTo(iCypher.Digit(i, this.i), /* n.intValue() 3 */I, '0');
			l[i] = "M" + s;
			aNumber id = new uNumber(NumberUtils.str(s, Integer.class));
			uVector v = new uVector((i / this.i) + this.i * (i % this.i));
			this.index.elements.add((i / this.i) + this.i * (i % this.i));
			// this.elements.add(new uVector(index.elements.size() / MathUtils.PI));
			
			// ((aNumber)this.Identity.getElement(i)).setValue(Integer.parseInt(s));
			this.elements.add(this.unfold(type));
		}
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
		return this.index;
	}

	////////////////////////////
	////////

	@Override
	public aMatrix set(Object[] values) {
		super.set(values);
		return this;
	}

	////

	public aMatrix add(aMatrix n) {
		return this;
	}

	public aMatrix sub(aMatrix n) {
		return this;
	}

	public aMatrix mul(aMatrix m) {
		return this;
	}

	public aMatrix div(aMatrix n) {
		return this;
	}

	////

	////////
	////////////////////////////

	public String toLog() {
		String log = "";
		log += "\n";
		log += "Matrix" + this.index + "{" + this.labels.size() + "}{";
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
		log += this.index + "\n"; // list of all values in order 0-(i^2)

		String S = "" + this.index.elements.size();
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
			log += cm + "{:" + iCypher.zTo(this.index.getElement(j).toString(), this.i - l, ' ') + ":"
					+ this.labels.get(j) + "}";
		}
		log += "\n";
		log += "}";
		log += "\n";
		log += this.logValues();
		log += "___________________________";
		log += "\n";
		return log;
	}

	public String logValues() {
		String log = "";
		log += "---[VALUES]\n";
		String S = "" + this.index.elements.size();
		int l = S.length() - 1;
		String cm = " ";
		for (Number n : this.elements) {
			int i = this.elements.indexOf(n);
			if (i == 0)
				log += cm;
			log += "[" + i + "]:(" + n + cm + ")";
		}
		log += "\n";

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
			cm = "_";
			if (j % (this.i) == 0) {
				log += "\n" + "[" + (j % (this.i)) + (j / i) + "]";
				cm = ":";
			} else
				for (int i = j % (this.i); i <= ((l) + (j % (this.i))); i++)
					cm += "_";

			// log += cm + "{" + this.Identity.getElement(j) + "}";
			log += cm + "{:" + this.labels.get(j) + ": " + this.elements.get(j) + "}";
		}
		log += "\n";
		log += "}";
		log += "\n";
		return log;
	}

}
