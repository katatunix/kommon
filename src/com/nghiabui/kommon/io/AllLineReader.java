package com.nghiabui.kommon.io;

import com.nghiabui.kommon.AppException;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class AllLineReader {
	
	private final BufferedReader bufferedReader;
	
	public AllLineReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	public List<String> read() {
		try {
			final List<String> lines = new ArrayList<>();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
			return lines;
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
	
}
