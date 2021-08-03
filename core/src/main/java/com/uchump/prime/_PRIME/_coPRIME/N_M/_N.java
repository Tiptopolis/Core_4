package com.uchump.prime._PRIME._coPRIME.N_M;

import java.util.Map;

import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iMonad;
import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iSymbol;

public interface _N<N> extends iSymbol<N> {

	// name/number/node/noun/null type
	public N Name();

	public _N Index();

	public String Symbol();

	public static _N new_N() {
		return null;
	}

	public static _N new_N(Object... _N) {

		return null;
	}

	public _N new_N(_N index, N name, String symbol);

	public default String Outter() {
		return "" + this.Name();
	}

	public default String Inner() {
		return "" + this.Symbol();
	}

	public default N as(_N n, Object... ie) {
		return (this.as(n));
	}

	public N as(_N n);

	public default boolean has(_N n) {
		if (this.equals(n))
			return true;
		else
			return false;

	}

	public default boolean has(_N n, Object... ie) {
		if (this.equals(n))
			return true;
		else
			return false;

	}

	public static enum _Types {
		Name, Number, Node, Noun, Null;

	}

	public class Name implements CharSequence, _N<String> {
		public _N<String> TAG; // all same case
		public String Name;
		public String name; // all same case

		public Name(String name, _N index, String symbol) {
			this.TAG = index;
		}

		public Name(String n) {
			TAG = this;
			Name = n;
			this.name = n.toLowerCase();
		}

		@Override
		public String getKey() {
			return name; // all same case
		}

		@Override
		public Object getValue() {
			return Name;
		}

		@Override
		public CharSequence setValue(Object value) {
			return value.toString();
		}

		@Override
		public String toString() {
			return (String) TAG.getValue();
		}

		@Override
		public String Name() {
			return this.Name;
		}

		@Override
		public _N Index() {
			return this.TAG;
		}

		@Override
		public String Symbol() {
			return this.name;
		}

		@Override
		public _N new_N(_N index, String name, String symbol) {

			return new Name(name, index, symbol);
		}

		@Override
		public String as(_N n) {

			return n.toString();
		}

		@Override
		public int length() {
			return this.Symbol().length();
		}

		@Override
		public char charAt(int index) {
			return this.Symbol().charAt(index);
		}

		@Override
		public CharSequence subSequence(int start, int end) {
			return this.Symbol().subSequence(start, end);
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof _N) {
				_N n = (_N) other;
				if(((_N) other).Name().equals(this.Name()))
					return true;
				
				if (this.Symbol().equals(n.Symbol())||this.Symbol().equals(n.Name()))
					return true;
			}
			if (other instanceof iMonad) {
				iMonad m = (iMonad) other;
				if (this.Symbol().equals(m.getName()))
					return true;
			}
			
			
			
			return false;
		}

	}

}
