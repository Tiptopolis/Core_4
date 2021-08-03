package com.uchump.prime._PRIME._coPRIME.META;

import java.util.ArrayList;

import com.uchump.prime._PRIME._coPRIME.N_M.A_I.iSymbol;



public enum eTestThingy implements iSymbol<String>{
	 NO_INDEX,
	    FIXNUM,
	    BIGNUM,
	    ARRAY,
	    STRING,
	    NIL,
	    TRUE,
	    FALSE,
	    SYMBOL,
	    REGEXP,
	    HASH,
	    FLOAT,
	    MODULE,
	    CLASS,
	    OBJECT,
	    STRUCT,
	    INTEGER,
	    NUMERIC,
	    RANGE,
	    TIME,
	    COMPLEX,
	    RATIONAL,
	    ENCODING,
	    CONVERTER,
	    GENERATOR,
	    YIELDER,
	    FILE,
	    MATCHDATA,
	    THREADGROUP,
	    THREAD,
	    EXCEPTION,
	    IO,
	    BINDING,
	    PROC,
	    METHOD,
	    DIR,
	    UNBOUNDMETHOD,
	    CONTINUATION,
	    BASICOBJECT,
	    BIGDECIMAL,
	    // insert new values here
	    MAX_CLASSES;
		
		public static ArrayList<eTestThingy> Index;
		
		private eTestThingy()
		{		
			reg(this);
		}
		
		private static void reg(eTestThingy r)
		{
			//werx
			if(Index == null) {
				Index =  new ArrayList<eTestThingy>();
			}
			Index.add(r);
		}

		@Override
		public String getKey() {
			return this.toString();
		}

		@Override
		public Object getValue() {
			return this;
		}

		@Override
		public Object setValue(Object value) {
			return this;
		}

		@Override
		public byte[] getBytes() {
			return getKey().getBytes();
		}

		@Override
		public Object[] getBits() {
			
			return new Object[] {this.getKey(),this.getValue()};
		}

		
}
