package com.uchump.prime.CORE.Device.Struct;

import static com.uchump.prime._PRIME.uAppUtils.*;
import static com.uchump.prime._PRIME.uSketcher.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.TypeVariable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.text.AttributeSet;

import com.badlogic.gdx.math.Vector3;
import com.uchump.prime.CORE.UI.iSpace;
import com.uchump.prime._PRIME.METATRON.Agents.A_I.iProperty;
import com.uchump.prime._PRIME.METATRON.M.File.A_I.iStruct;
import com.uchump.prime._PRIME.METATRON.M.File.A_I.iType;
import com.uchump.prime._PRIME._coPRIME.N_M._N;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iNode;
import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uTransform;


public class aTree<N extends iNode, V>  implements iTree<N, V>, iMonad, Iterable<iNode> {

	protected uTransform RootTransform;
	// List exEmployeeList = employeeList.stream().filter((p) -&gt;
	// p.isExEmployee()).collect(Collectors.toList());

	public iNode RootNode;

	public ArrayList<iNode> Members;

	public aTree() {
		super();
		this.RootTransform = new uTransform(this);
		this.RootTransform.getTransform().setDepth(0);
		this.Members = new ArrayList<iNode>();
	}

	@Override
	public int size() {

		return this.Members.size();
	}

	@Override
	public boolean isEmpty() {
		return this.Members.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		if (!this.isEmpty()) {
			if (key instanceof iNode) {
				iNode N = (iNode) key;
				if (this.Members.contains(N))
					return true;
				
			}
			return false;

		} else
			return false;
	}

	@Override
	public boolean containsValue(Object value) {

		return this.Members.contains(value);
	}

	@Override
	public V get(Object key) {

		if (this.contains(key))
			return (V) this.Members.get(this.Members.indexOf(key));
		else return null;
	}

	@Override
	public V put(N key, V value) {
		this.Members.add((iNode) key);
		// this.Nodes.put(key, value);
		return value;
	}

	@Override
	public V remove(Object key) {

		if (this.Members.contains(key)) {
			Object o = this.Members.get(this.Members.indexOf(key));
			this.Members.remove(key);
			return (V)o;
			
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends N, ? extends V> m) {

		this.Members.addAll(m.keySet());
		//this.Nodes.putAll(m);
	}

	@Override
	public Set<N> keySet() {		
		HashSet n = new HashSet(this.Members);		
		return n;
	}

	@Override
	public Collection<V> values() {
		return (Collection<V>) this.Members;
	}

	@Override
	public Set<Entry<N, V>> entrySet() {

		HashMap<N,V> nEnts = new HashMap<N,V>();
		for(iNode n : this.Members)
		{
			nEnts.put((N)n, (V)n.getValue());
		}

		return nEnts.entrySet();
	}

	@Override
	public uTransform getTransform() {
		return this.RootTransform;
	}

	@Override
	public void clear() {
		this.Members.clear();
		//this.Nodes.clear();
		this.RootNode = null;
	}

	@Override
	public void transformUpdated() {

	}

	@Override
	public uTransform metric() {
		// TODO Auto-generated method stub
		return this.RootTransform;
	}

	@Override
	public Vector3 getSize() {
		return new Vector3();
	}

	@Override
	public Vector3 getUnit() {
		return new Vector3();
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toLog() {

		String log = "\n";
		String spc = "_";
		String indt = " ";
		String idnt = " ";
		String dndt = " ";

		for (iNode N : this) {
			String d = "[" + N.getDepth() + "]{:";

			for (int i = 0; i < N.getRootDist(); i++) {
				indt += spc;
				idnt += spc;
				dndt += spc;
			}

			if (!(N instanceof iNode)) {
				log += ("\n" + d + N.toLog());
				for (int i = 0; i <= N.getDepth(); i++) {
					{
						idnt += spc;
						dndt += spc;
					}
				}
			}

			else {
				log += ("\n" + d + ((iNode) N).toLog());
				for (int i = 0; i <= N.getDepth(); i++) {
					{
						idnt += spc;
						dndt += spc;
					}
				}
			}

			log += ("\n" + indt + d + N.toString() + ":}" + "\n");

		}
		return log + "\n";
	}

	@Override
	public Iterator<iNode> iterator() {
		return this.Members.iterator();
	}


	@Override
	public _N getType() {
		// TODO Auto-generated method stub
		return new Name(this.getClass().getSimpleName());
	}

	@Override
	public Collection<iProperty> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public iStruct copyTo(Object newOwner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public iSpace getGraph() {
		// TODO Auto-generated method stub
		return this;
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
	public Collection<?> IndexAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<iType, ?> All() {
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
