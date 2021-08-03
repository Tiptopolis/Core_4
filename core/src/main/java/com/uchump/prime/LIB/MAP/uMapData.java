package com.uchump.prime.LIB.MAP;

import java.lang.annotation.Annotation;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime.LIB.MAP.A_I.aMap;
import com.uchump.prime.LIB.MAP.A_I.aMapData;
import com.uchump.prime._PRIME.METATRON.M.File.A_I.iType;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public class uMapData extends aMapData{

	//2D lolol
	
	public aMap Map;
	public ArrayList<Object> Objects=new ArrayList<Object>();
	
	//aTree and eMatrix lololol
	
	public uMapData()
	{		
		
	}

	@Override
	public java.util.Map<iType, ?> All() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> IndexAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public _N getType() {
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
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object setValue(Object value) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<?> arrayType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String descriptorString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TypeVariable<?>[] getTypeParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
		// TODO Auto-generated method stub
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
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}
}
