package com.nghiabui.kommon.io;

import com.nghiabui.kommon.AppException;
import com.nghiabui.kommon.Tuple;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MyProcess {
	
	private final String command;
	
	public MyProcess(String command) {
		this.command = command;
	}
	
	public Tuple<Integer, List<String>> exec() {
		final Process process;
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (Exception e) {
			throw new AppException(e);
		}
		
		try (   InputStream is = process.getInputStream();
		        InputStreamReader isr = new InputStreamReader(is);
		        BufferedReader br = new BufferedReader(isr)) {
			final List<String> lines = new AllLineReader(br).read();
			final int exitCode = process.waitFor();
			return new Tuple<>(exitCode, lines);
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
	
}
