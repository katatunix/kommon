package com.nghiabui.kommon;

public class CppSourceExt {
	
	public static boolean isC(String ext) {
		return ext.toLowerCase().equals("c");
	}
	
	public static boolean isCpp(String ext) {
		ext = ext.toLowerCase();
		return ext.equals("cpp") || ext.equals("cc") || ext.equals("cxx");
	}
	
	public static boolean isS(String ext) {
		return ext.toLowerCase().equals("s");
	}
	
}
