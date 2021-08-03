package com.uchump.prime._PRIME._coPRIME.META;


import static com.uchump.prime._PRIME.uAppUtils.*;
import com.uchump.prime._PRIME.DefaultResources;

public class xEnum extends xIndex {
	// uses Strings contained in Mask as Keys to generate next Entry<Mask,Name>
	// Codix, Radix
	// get next symbol in pattern
	public String Mask = DefaultResources.HexDigits;

	public int MaskDepth = 0;

	public xEnum() {
		super();
	}

	public xEnum(String name) {
		super(name, new Object[0]);
	}

	public xEnum(String name, Object[] input) {
		super(name, input);
	}

	public String mask(int index) {

		// while num > 0
		// rem=num%16;
		// str2=hex[rem]+str2;
		// num=num/16;

		int num = index;
		String R = "";
		String r = "";

		int rem = 0;
		if (index == 0)
			return "0";

		for (int i = index; i > 0; i /= Mask.length()) {

			int nextRdx = i % Mask.length();

			r += Mask.charAt(nextRdx) + "";

		}

		// reverse it
		byte[] strAsByteArray = r.getBytes();
		byte[] result = new byte[strAsByteArray.length];

		for (int i = 0; i < strAsByteArray.length; i++)
			result[i] = strAsByteArray[strAsByteArray.length - i - 1];

		R = new String(result);

		return R;
	}

	@Override
	public boolean equals(Object other) {
		if (this.toString().equals(other.toString()))
			return true;

		if (other instanceof xIndex) {
			xIndex I = (xIndex) other;
			if (I.name == this.name)
				return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "[<" + this.name + ">" + "{0->" + (this.size().intValue() - 1) + "%" + this.Mask.length() + ":"
				+ this.Entries.size() + "}]";
	}

	public String toLog() {

		String log = "";
		log += this.toString();
		log += "\n";

		// map raddix Dec-Hex
		int c = -1;
		for (Entry E : this.Entries) {
			log += "[" + (this.Entries.indexOf(E)) + "]";
			log += "(" + E.toString() + ")";
			log += "<" + E.getClass().getSimpleName() + ">";
			log += "{" + (this.mask(this.Entries.indexOf(E))) + "}";
			log += "\n";
		}

		log += "\n";

		return log;
	}
}
