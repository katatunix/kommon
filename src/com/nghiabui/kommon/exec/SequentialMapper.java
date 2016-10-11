package com.nghiabui.kommon.exec;

import java.util.Collection;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class SequentialMapper extends Mapper {

	@Override
	public <T, U> void map(Collection<T> source,
	                       Function<T, U> mapFunc,
	                       BiConsumer<T, U> consumer) {
		for (T t : source) {
			final U u = mapFunc.apply(t);
			consumer.accept(t, u);
		}
	}
	
	@Override
	public <T, U> void flatMap(Collection<T> source,
	                           Function<T, Set<U>> mapFunc,
	                           BiConsumer<T, Set<U>> consumer) {
		for (T t : source) {
			final Set<U> us = mapFunc.apply(t);
			consumer.accept(t, us);
		}
	}
	
}
