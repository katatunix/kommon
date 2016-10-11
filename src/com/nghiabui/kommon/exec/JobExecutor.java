package com.nghiabui.kommon.exec;

import com.nghiabui.kommon.DebugException;

import java.util.function.Supplier;

public class JobExecutor {

	private final int jobs;

	public JobExecutor() {
		this(Runtime.getRuntime().availableProcessors());
	}

	public JobExecutor(int jobs) {
		this.jobs = jobs;
	}

	public void execute(Supplier<Boolean> job) {
		if (jobs < 1 || jobs > 32) {
			throw new DebugException("Invalid jobs: " + jobs);
		}

		final Thread[] threads = new Thread[jobs];
		for (int i = 0; i < jobs; ++i) {
			threads[i] = new Thread(() -> {
				while (true) {
					if (job.get()) break;
				}
			});
			threads[i].start();
		}
		try {
			for (int i = 0; i < jobs; ++i) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			throw new DebugException(e);
		}
	}

}
