package com.uchump.prime._PRIME.METATRON.M.File.A_I;

import java.lang.annotation.Annotation;
import java.lang.invoke.TypeDescriptor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.aNode;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iNode;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;

public interface iType extends iNode<aNode, _N, _N>, _N , Type,  TypeDescriptor.OfField<Class<?>>, GenericDeclaration, Annotation{
//Class k;
	// can .expand & .contract

	public Map<iType, ?> All();
	public Collection<?> IndexAll();

	public _N getType();
	@Override
	public default String getTypeName()
	{
		return this.getType().Symbol();
	}

	public default _N[] Types() {
		return new _N[] { this };

	}
	
	
	

}
