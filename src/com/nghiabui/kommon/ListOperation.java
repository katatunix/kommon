package com.nghiabui.kommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListOperation {
	
	public static <T> List<T> op2list(Optional<T> op) {
		final List<T> result = new ArrayList<>(1);
		if (op.isPresent()) {
			result.add(op.get());
		}
		return result;
	}
	
	public static <T> List<T> concat(List<T> list1, List<T> list2) {
		final List<T> result = new ArrayList<>(list1.size() + list2.size());
		result.addAll(list1);
		result.addAll(list2);
		return result;
	}
	
	public static <T, U> Optional<List<U>> mapWithEarlyExit(Collection<T> list, Function<T, Optional<U>> mapper) {
		final List<U> result = new ArrayList<>();
		for (T t : list) {
			final Optional<U> op = mapper.apply(t);
			if (!op.isPresent()) return Optional.empty();
			result.add(op.get());
		}
		return Optional.of(result);
	}
	
}
