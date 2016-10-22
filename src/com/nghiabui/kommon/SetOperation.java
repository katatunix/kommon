package com.nghiabui.kommon;

import java.util.*;
import java.util.stream.Collectors;

public class SetOperation {
	
	public static <T> boolean areEqual(Set<T> lhs, Set<T> rhs) {
		return lhs.size() == rhs.size() && lhs.containsAll(rhs) && rhs.containsAll(lhs);
	}
	
	@SafeVarargs
	public static <T> Set<T> newSet(T... a) {
		final Set<T> set = new HashSet<>();
		Collections.addAll(set, a);
		return set;
	}
	
	public static <T> Set<T> intersection(Set<T> left, Set<T> right) {
		return left.stream()
			.filter(right::contains)
			.collect(Collectors.toSet());
	}
	
	public static <T> Set<T> intersection(Collection<Set<T>> list) {
		final Optional<Set<T>> result = list.stream().reduce(SetOperation::intersection);
		return result.isPresent() ? result.get() : Collections.EMPTY_SET;
	}
	
	public static <T> int intersectionSize(Set<T> left, Set<T> right) {
		int count = 0;
		for (T a : left) {
			if (right.contains(a)) ++count;
		}
		return count;
	}
	
	public static <T> Set<T> subtract(Set<T> left, Set<T> right) {
		return left.stream()
			.filter(item -> !right.contains(item))
			.collect(Collectors.toSet());
	}
	
	@SafeVarargs
	public static <T> Set<T> subtract(Set<T> left, Set<T>... rights) {
		Set<T> result = new HashSet<>(left);
		for (Set<T> set : rights) {
			result = subtract(result, set);
		}
		return result;
	}
	
	public static <T> Set<T> union(Collection<Set<T>> sets) {
		final Set<T> result = new HashSet<>();
		for (Set<T> set : sets) {
			result.addAll(set);
		}
		return result;
	}
	
	@SafeVarargs
	public static <T> Set<T> union(Set<T>... sets) {
		final Set<T> result = new HashSet<>();
		for (Set<T> set : sets) {
			result.addAll(set);
		}
		return result;
	}
	
	public static <T> boolean isSub(Set<T> set1, Set<T> set2) {
		return subtract(set1, set2).isEmpty();
	}
	
	@SafeVarargs
	public static <T> boolean found(T e, Set<T>... sets) {
		for (Set<T> set : sets) {
			if (set.contains(e)) return true;
		}
		return false;
	}
	
	@SafeVarargs
	public static <T> boolean hasSpecialElement(Set<T> set, Set<T>... sets) {
		for (T e : set) {
			if (!found(e, sets)) return true;
		}
		return false;
	}
	
	public static <T> Set<T> op2set(Optional<T> op) {
		if (!op.isPresent()) return Collections.emptySet();
		final Set<T> result = new HashSet<>();
		result.add(op.get());
		return result;
	}
	
}
