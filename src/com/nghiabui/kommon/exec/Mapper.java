package com.nghiabui.kommon.exec;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class Mapper {

	public <T, U> Set<U> map(Collection<T> source, Function<T, U> mapFunc) {
		final Set<U> res = new HashSet<>();
		map(source, mapFunc, (t, u) -> res.add(u));
		return res;
	}
	
	public <T, U> Set<U> flatMap(Collection<T> source, Function<T, Set<U>> mapFunc) {
		final Set<U> res = new HashSet<>();
		flatMap(source, mapFunc, (t, us) -> res.addAll(us));
		return res;
	}

	public abstract <T, U> void map(Collection<T> source,
	                                Function<T, U> mapFunc,
	                                BiConsumer<T, U> consumer);
	
	public abstract <T, U> void flatMap(Collection<T> source,
	                                    Function<T, Set<U>> mapFunc,
	                                    BiConsumer<T, Set<U>> consumer);

}
