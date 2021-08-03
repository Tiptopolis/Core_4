package com.uchump.prime._PRIME.METATRON.Agents.Struct;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;



public abstract class aCompoundProperty extends aProperty {

	public ArrayList<aProperty> index = new ArrayList<aProperty>();

	protected aProperty that;
	protected ArrayList<aProperty> those = new ArrayList<aProperty>();

	public aCompoundProperty() {
		super();
	}

	public aCompoundProperty(String name, Object index) {
		super(name, index);
	}

	public ArrayList<aProperty> getThose() {
		return this.those;
	}

	public aProperty getThat() {
		return this.that;
	}

	public void addProperty(aProperty property) {
		this.index.add(property);
		property.header = this;
		property.of = OpCode.HEADER;		
		this.that = property;

	}

	public ArrayList<aProperty> getValues() {
		return this.index;
	}

	@Override
	public aProperty getValue(String name) {
		for (aProperty p : this.index) {

			if (p.name.equals(name)) {
				this.that = p;
				return p;
			}
		}

		return null;
	}

	public boolean hasValue(String name) {
		this.those.clear();
		this.that = null;

		for (aProperty p : this.index) {
			if (p.name.equals(name)) {
				this.that = p;
				this.those.add(p);
			}
		}
		if (!this.those.isEmpty())
			return true;

		return false;

	}

	@Override
	public aCompoundProperty copyTo(Object toIndex) {
		ArrayList<aProperty> i = (ArrayList<aProperty>) this.index.clone();
		aCompoundProperty newProperty = new uComponent(this.name, toIndex);

		for (aProperty p : i) {
			newProperty.addProperty(p.copyTo(toIndex));

		}
		this.that = newProperty;
		this.those = (ArrayList<aProperty>) newProperty.getValues().clone();
		
		
		return newProperty;
	}

	@Override
	public ArrayList<Object> All() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		String log = "";
		log += "\n";
		log += super.toString();
		log += "\n";
		for (aProperty p : this.index) {
			log += "   " + p.toString();
			log += "\n";

		}

		return log;
	}
}
