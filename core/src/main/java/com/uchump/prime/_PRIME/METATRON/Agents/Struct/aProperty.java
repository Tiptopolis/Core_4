package com.uchump.prime._PRIME.METATRON.Agents.Struct;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.swing.text.AttributeSet;

import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime._PRIME.METATRON.Agents.A_I.iProperty;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;


public abstract class aProperty<T> implements iProperty<T> {

	public String name = "";
	public String post = "";
	public T value; // Type
	public Object index; // ultimate parent of this property, property 'of' index
	public aProperty<?> header; // adhoc namespace, sub-property?

	protected OpCode of = OpCode.INDEX; // determines wether this property is a property of the Index or the// Header
										// property

	public aProperty() {
		this.index = this;
		this.value = (T) this.index.getClass();

		
	}

	public aProperty(String name, Object index) {
		this(name, index, "");

	}

	public aProperty(String name, Object index, T value) {
		this.name = name;
		this.index = index;
		if (value != null)
			this.value = value;
		else {
			String t = (String) value;
			this.value = (T) t;
		}


	}

	public aProperty(String name, Object index, String value) {
		this(name, index, (T) value);
	}

	@Override
	public String getName() {
		return this.name;
	}


	@Override
	public T getValue() {
		return this.value;
	}
	

	@Override
	public void setIndex(T index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeader(iProperty header) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasHeader() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasHeader(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public iProperty getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<iProperty> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public iSpace getGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getExpanded() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setExpanded(boolean is) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public uTransform getTransform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transformUpdated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object Name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public _N Index() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Symbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public _N new_N(_N index, Object name, String symbol) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object as(_N n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object setValue(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAttributeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isDefined(Object attrName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEqual(AttributeSet attr) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AttributeSet copyAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getAttribute(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<?> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsAttribute(Object name, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAttributes(AttributeSet attributes) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AttributeSet getResolveParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public aProperty copyTo(Object index) {
		aProperty newProperty = new uProperty(this.name, index, this.value);

		return newProperty;
	}
	
	@Override
	public ArrayList<Object> All() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public String toString() {

		String result = "";
		String ind = " : ";

		result += this.of.op();

		result += " {" + this.name + "} * <" + this.getType() + "> * " + this.getValue();

		return result;

	}

	public enum OpCode {
		INDEX("-"), HEADER("_");

		public final String op;

		private OpCode(String op) {
			this.op = op;
		}

		public String op() {
			return this.op;
		}

	}
}
