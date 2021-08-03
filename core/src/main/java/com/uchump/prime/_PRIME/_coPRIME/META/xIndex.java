package com.uchump.prime._PRIME._coPRIME.META;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.uchump.prime._PRIME._coPRIME.N_M.Prototype.uVector;

import regexodus.Pattern;

public class xIndex implements Iterable{

	public String name = "LIST";
	

	public ArrayList<Entry> Entries = new ArrayList<Entry>();
	public LinkedHashMap<String, String> Out = new LinkedHashMap<String, String>();

	public Number Size;

	public xIndex() {

		this.enumerate();
	}

	public xIndex(String name) {
		this.name = name;
		this.enumerate();
	}
	



	public xIndex(String name, String[] Ks, Object[] Vs) {
		this.name = name;
		
		this.enumerate();
	}

	public xIndex(String name, Object[] C) {
		this.name = name;

		for (int k = 0; k < C.length; k++) {
			Object V = C[k];
			this.add("" + k, V);
		}

		this.enumerate();
	}

	////////

	public static xIndex Enumerate() {
		return new xIndex();
	}

	public static xIndex Enumerate(String name) {
		return new xIndex(name);
	}

	protected void enumerate() {

	}

	public void add(Object c)
	{
		//key = newInd
		this.add(""+this.size(),c);
	}
	
	public void add(Object[] C) {

		for (int k = 0; k < C.length; k++) {
			Object V = C[k];
			this.add("" + k, V);
		}

	}

	public void add(Map.Entry<String, Object> e) {
		this.add(e.getKey(), e.getValue());
	}

	public void add(String k, Object v) {
		this.Entries.add(new xIndex.Entry(k, v));
		this.Out.put(k, v.toString());
	}

	public Number size() {
		int s = this.Entries.size();
		if (s < 1)
			s = 1;
		this.Size = new uVector(new Integer[s]);
		return s;
	}

	public Entry Get(int index)
	{
		return this.Entries.get(index);
	}
	
	public Object get(int index)
	{
		return this.Entries.get(index).getValue();
	}
	
	public void set(int index, Entry element)
	{
		this.Entries.set(index, element);
	}
	
	public Object get(String name)
	{
		for(Entry e : this.Entries)
		{
			if(e.get == name)
				return e;
		}
		
		return name+"{?)";
	}
	
	public Entry Get(String name)
	{
		for(Entry e : this.Entries)
		{
			if(e.get == name)
				return e;
		}
		
		return new Entry("D====={?)#" , name);
	}
	
	
	@Override
	public String toString() {
		return "[<" + this.name + ">:{" + this.size() + "}]";
	}

	public String toLog() {
		String log = "";
		log += this.toString();
		log += "\n";
		for (Entry E : this.Entries) {
			log += "|";
			log += E.toString();
			log +="|\n";
		}

		return log;
	}

	///////////////////////////////////
	//////////////////////////////////
	
	

	public class Entry implements Map.Entry<String, Object> {

		public Object val;
		public final String get;
		public String sym ="";

		public Entry(String K, Object V) {
			this.get = K;
			this.val = V;
			
			// String a = INNER.A.get;
		}

		@Override
		public String getKey() {
			return this.get;
		}
		
		public String getSym()
		{
			this.sym = this.get+this.val.toString();
			return this.sym;
		}

		@Override
		public Object getValue() {

			return this.val;
		}

		@Override
		public Object setValue(Object value) {
			this.sym = value.toString();			
			return this;
		}

		@Override
		public String toString() {
			String r = "#";
			r += "["+get+"]" + "(" + this.sym + "){"+ this.val + "}<"+this.val.getClass().getSimpleName()+">";

			return r;
		}

	}

	@Override
	public Iterator iterator() {
		
		return this.Entries.iterator();
	}
}
