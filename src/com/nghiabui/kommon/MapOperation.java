package com.nghiabui.kommon;

import java.util.*;
import java.util.function.BiFunction;

public class MapOperation {
	
	public static <T, U> Map<T, Set<U>> union(Map<T, Set<U>> map1, Map<T, Set<U>> map2) {
		final Map<T, Set<U>> result = new HashMap<>(map1);
		map2.forEach((key, value) -> {
			if (result.containsKey(key)) {
				result.put(key, SetOperation.union(result.get(key), value));
			} else {
				result.put(key, value);
			}
		});
		return result;
	}
	
	public static <T, U> Map<T, Set<U>> union(Collection<Map<T, Set<U>>> maps) {
		final Optional<Map<T, Set<U>>> result = maps.stream().reduce(MapOperation::union);
		return result.isPresent() ? result.get() : Collections.emptyMap();
	}
	
	public static <T, U> Map<T, Set<U>> intersection(Map<T, Set<U>> map1, Map<T, Set<U>> map2) {
		final Map<T, Set<U>> result = new HashMap<>();
		map1.forEach((key, value1) -> {
			if (map2.containsKey(key)) {
				final Set<U> value2 = map2.get(key);
				result.put(key, SetOperation.intersection(value1, value2));
			}
		});
		return result;
	}
	
	public static <T, U> Map<T, Set<U>> intersection(Collection<Map<T, Set<U>>> maps) {
		final Optional<Map<T, Set<U>>> result = maps.stream().reduce(MapOperation::intersection);
		return result.isPresent() ? result.get() : Collections.emptyMap();
	}
	
	public static <T, U> Map<T, Set<U>> subtract(Map<T, Set<U>> map1, Map<T, Set<U>> map2) {
		final Map<T, Set<U>> result = new HashMap<>();
		map1.forEach((key, value1) -> {
			if (map2.containsKey(key)) {
				final Set<U> value2 = map2.get(key);
				final Set<U> sub = SetOperation.subtract(value1, value2);
				if (!sub.isEmpty()) {
					result.put(key, sub);
				}
			} else {
				result.put(key, value1);
			}
		});
		return result;
	}
	
	public static <T, U> Map<T, U> unionOverride(Map<T, U> map1, Map<T, U> map2) {
		final Map<T, U> result = new HashMap<>(map1);
		map2.forEach(result::put);
		return result;
	}
	
	public static <T, U, V> Map<T, V> transform(Map<T, U> source, BiFunction<T, U, V> func) {
		final Map<T, V> result = new HashMap<>();
		source.forEach((key, value) -> result.put(key, func.apply(key, value)));
		return result;
	}
	
}
