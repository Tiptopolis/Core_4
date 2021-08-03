package com.uchump.prime._PRIME.METATRON;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime._PRIME.METATRON.M.File.A_I.iType;
import com.uchump.prime._PRIME._coPRIME.N_M.NumberUtils;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uNumber;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public enum CoreTypeRegistry implements iType, iSpace {
	OBJECT;

	public static CoreTypeRegistry TYPE = OBJECT;
	public static ArrayList<iType> Index = new ArrayList<iType>();
	public static HashMap<_N, iType> Find = new HashMap<_N, iType>();
	public static HashMap<iType, CoreTypeRegistry> All = new HashMap<iType, CoreTypeRegistry>();

	protected boolean expanded = false;

	private CoreTypeRegistry() {

	}

	
	
	@Override
	public iSpace getGraph() {

		return this;
	}

	@Override
	public boolean getExpanded() {
		return expanded;
	}

	@Override
	public void setExpanded(boolean is) {
		expanded = is;
	}

	@Override
	public uTransform getTransform() {
		return new uTransform();
	}
	
	@Override
	public CoreTypeRegistry new_N(_N index, Object name, String symbol) {

		return this;
	}

	@Override
	public Object as(_N n) {
		
		return this;
	}

	@Override
	public void update(float deltaTime) {

		transformUpdated();
	}

	@Override
	public void transformUpdated() {

	}

	@Override
	public Object Name() {
		return new Name("CoreTypeRegistry");
	}

	@Override
	public _N<Number> Index() {
		return new uNumber(-1);
	}

	@Override
	public String Symbol() {
		return CoreTypeRegistry.TYPE.toString();
	}



	@Override
	public Object getKey() {
		return this.Name();
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return this.Symbol();
	}

	@Override
	public Object setValue(Object value) {
		// if matches pattern, add & update
		return this;
	}

	@Override
	public Map<iType, CoreTypeRegistry> All() {
		return All;
	}

	@Override
	public ArrayList<iType> IndexAll() {

		return Index;
	}

	@Override
	public _N getType() {
		return new Name("Type");
	}

	@Override
	public String descriptorString() {
		return this.Symbol();
	}

	
	@Override
	public Vector3 getSize() {
		return new Vector3(Find.size(), Find.getClass().getSimpleName().length(),
				Metatron.TheMetatron.RealSecond.I.floatValue());
	}

	@Override
	public Vector3 getUnit() {
		return new Vector3(MathUtils.PI, MathUtils.PI, MathUtils.PI);
	}

	@Override
	public boolean isEmpty() {
		return this.All().isEmpty();
	}

	@Override
	public uTransform metric() {
		return new uTransform();
	}



	@Override
	public TypeVariable<?>[] getTypeParameters() {
		return null;
	}



	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
		return null;
	}



	@Override
	public Annotation[] getAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Annotation[] getDeclaredAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean isArray() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isPrimitive() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Class<?> componentType() {
		return Object.class;
	}



	@Override
	public Class<?> arrayType() {
		return Type.class;
	}



	@Override
	public Class<? extends Annotation> annotationType() {
		return iType.class;
	}




}
