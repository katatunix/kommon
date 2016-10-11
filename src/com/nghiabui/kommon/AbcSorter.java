package com.nghiabui.kommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AbcSorter {
	
	public static List<Path> sortedPaths(Collection<Path> paths) {
		final List<Path> result = new ArrayList<>(paths);
		result.sort((p1, p2) -> p1.canonical().compareToIgnoreCase(p2.canonical()));
		return result;
	}
	
	public static <T> List<T> sortedByString(Collection<T> list) {
		final List<T> result = new ArrayList<>(list);
		result.sort((o1, o2) -> o1.toString().compareToIgnoreCase(o2.toString()));
		return result;
	}
	
}
