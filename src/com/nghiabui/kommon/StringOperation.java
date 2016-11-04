package com.nghiabui.kommon;

import java.util.Collection;

public class StringOperation {
	
	public static String join(Collection<String> list) {
		return join(list, " ");
	}
	
	public static String join(Collection<String> list, String sep) {
		final StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String e : list) {
			if (first) {
				first = false;
			} else {
				sb.append(sep);
			}
			sb.append(e);
		}
		return sb.toString();
	}
	
	public static String join(String... list) {
		return join(list, " ");
	}
	
	public static String join(String[] list, String sep) {
		if (list.length == 0) return "";
		final StringBuilder sb = new StringBuilder();
		sb.append(list[0]);
		for (int i = 1; i < list.length; ++i) {
			sb.append(sep).append(list[i]);
		}
		return sb.toString();
	}
	
}
