package com.iemes.util;

public class RandomUitls {
	
	private static int num = 0;
	
	private static String zero = "0000000000000000000000000000000000000000000000000000000000000000"; 

	public static String getRandom(int lenght) {
		String rs = zero.substring(0, lenght)+num+"";
		rs = rs.substring(rs.length()-lenght, rs.length());
		num++;
		return rs;
	}
}
