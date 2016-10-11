package com.nghiabui.kommon.exec;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class ParallelMapper extends Mapper {

	@Override
	public <T, U> void map(Collection<T> source,
	                       Function<T, U> mapFunc,
	                       BiConsumer<T, U> consumer) {
		final Object lock = new Object();
		final Iterator<T> iter = source.iterator();
		final JobExecutor je = new JobExecutor();
		je.execute(() -> {
			final T t;
			synchronized (lock) {
				if (!iter.hasNext()) return true;
				t = iter.next();
			}
			final U u = mapFunc.apply(t);
			synchronized (lock) {
				consumer.accept(t, u);
			}
			return false;
		});
	}
	
	@Override
	public <T, U> void flatMap(Collection<T> source,
	                           Function<T, Set<U>> mapFunc,
	                           BiConsumer<T, Set<U>> consumer) {
		final Object lock = new Object();
		final Iterator<T> iter = source.iterator();
		final JobExecutor je = new JobExecutor();
		je.execute(() -> {
			final T t;
			synchronized (lock) {
				if (!iter.hasNext()) return true;
				t = iter.next();
			}
			final Set<U> us = mapFunc.apply(t);
			synchronized (lock) {
				consumer.accept(t, us);
			}
			return false;
		});
	}
	
}
