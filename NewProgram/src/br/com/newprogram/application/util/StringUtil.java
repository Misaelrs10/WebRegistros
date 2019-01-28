package br.com.newprogram.application.util;

public class StringUtil {

	public static boolean isEmpty(String s) {
		if(s==null) {
			return true;
		}
		
		return s.trim().length() == 0;
	}
	
	public static String leftZeroes(int value, int finalsize) {
		return String.format("%0" + finalsize + "d", value);
	}
	
	
	public static void main(String[] args) {
		
		String str = " s ";
		
		boolean b = StringUtil.isEmpty(str);
		System.out.println(b);
	}
}
