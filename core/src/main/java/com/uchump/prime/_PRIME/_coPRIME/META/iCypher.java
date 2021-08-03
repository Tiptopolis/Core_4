package com.uchump.prime._PRIME._coPRIME.META;

import com.uchump.prime._PRIME.DefaultResources;

public interface iCypher {

	public static String Hex(int index) {
		String rdx = DefaultResources.HexDigits;

		int num = index;
		String R = "";
		String r = "";

		int rem = 0;
		if (index == 0)
			return "0";

		for (int i = index; i > 0; i /= rdx.length()) {

			int nextRdx = i % rdx.length();

			r += rdx.charAt(nextRdx) + "";

		}

		// reverse it
		byte[] strAsByteArray = r.getBytes();
		byte[] result = new byte[strAsByteArray.length];

		for (int i = 0; i < strAsByteArray.length; i++)
			result[i] = strAsByteArray[strAsByteArray.length - i - 1];

		R = new String(result);

		return R;
	}

	public static String Digit(int index) {
		String rdx = DefaultResources.DIGITS;

		int num = index;
		String R = "";
		String r = "";

		int rem = 0;
		if (index == 0)
			return "0";

		for (int i = index; i > 0; i /= rdx.length()) {

			int nextRdx = i % rdx.length();

			r += rdx.charAt(nextRdx) + "";

		}

		// reverse it
		byte[] strAsByteArray = r.getBytes();
		byte[] result = new byte[strAsByteArray.length];

		for (int i = 0; i < strAsByteArray.length; i++)
			result[i] = strAsByteArray[strAsByteArray.length - i - 1];

		R = new String(result);

		return R;
	}

	public static String Digit(int index, int iMod) {
		String rdx = DefaultResources.DIGITS;

		int num = index;
		String R = "";
		String r = "";

		int rem = 0;
		if (index == 0)
			return "0";

		for (int i = index; i > 0; i /= (iMod)) {

			 int nextRdx = ((i % iMod % (rdx.length())));
			//int nextRdx = ((i % iMod));

			r += rdx.charAt(nextRdx) + "";

		}

		// reverse it
		byte[] strAsByteArray = r.getBytes();
		byte[] result = new byte[strAsByteArray.length];

		for (int i = 0; i < strAsByteArray.length; i++)
			result[i] = strAsByteArray[strAsByteArray.length - i - 1];

		R = new String(result);

		return R;
	}

	public static String Enumerate(int indexLen) {
		String rdx = DefaultResources.DIGITS;
		String R = "";
		String r = "";

		int a = 1;
		while (a-- > 0) {

		}

		if (indexLen == 0)
			return "0";

		for (int i = indexLen; i > 0; i /= rdx.length()) {

			int nextRdx = i % rdx.length();
			r += rdx.charAt(nextRdx) + "";

		}

		// reverse it
		byte[] strAsByteArray = r.getBytes();
		byte[] result = new byte[strAsByteArray.length];

		for (int i = 0; i < strAsByteArray.length; i++)
			result[i] = strAsByteArray[strAsByteArray.length - i - 1];

		R = new String(result);

		return R;
	}

	public static String zTo(String s, int index, char override) {
		// prepends String s with the override character [index]# of times, returns s
		String P = "";
		for (int i = 0; i < (index - (s.length() + 1)); i++) {
			P += "" + override;
		}
		s = P + s;

		return s;
	}
}
