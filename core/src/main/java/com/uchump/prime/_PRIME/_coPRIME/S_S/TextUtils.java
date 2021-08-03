package com.uchump.prime._PRIME._coPRIME.S_S;

public class TextUtils {

	//public static char[] Alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	public static String Alphabet = "abcdefghijklmnopqrstuvwxyz";
	public static String Capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String Special = "`~@#$%^&";
	public static String Numbers = "0123456789";
	public static String Punctuation = ".,;:!?";
	public static String Opperation = "+-*/=";
	public static String Sepperation = "/|\\_";
	public static String Encapsulation = " <>{}[]()";
	public static String Scientific = "θ"; //θ-Theta
	
	

	
	public static String toLog()
	{
		String log = "";
		log += "\n";
		
		log += Alphabet;
		log += "\n";
		log += Capitals
				;
		log += "\n";
		log += Special;
		log += "\n";
		log += Numbers;
		log += "\n";
		log += Punctuation;
		log += "\n";
		log += Opperation;
		log += "\n";
		log += Sepperation;
		log += "\n";
		log += Encapsulation;
		log += "\n";
		
		return log;
	}
	
	
}
