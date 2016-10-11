package com.nghiabui.kommon;


import java.util.ArrayList;
import java.util.List;

public class Arguments {
	
	private final String[] args;
	
	public Arguments(String[] args) {
		this.args = args;
	}
	
	public List<String> get(char option) {
		final List<String> result = new ArrayList<>();
		final String op = "" + option;
		
		for (int i = 0; i < args.length; ++i) {
			if (!isOptionSingle(args[i])) continue;
			if (!args[i].contains(op)) continue;
			
			for (int j = i + 1; j < args.length; ++j) {
				if (isOption(args[j])) break;
				result.add(args[j]);
			}
		}
		
		return result;
	}
	
	public List<String> get(String op) {
		final List<String> result = new ArrayList<>();
		
		for (int i = 0; i < args.length; ++i) {
			if (!isOptionDouble(args[i])) continue;
			if (!args[i].substring(2).equals(op)) continue;
			
			for (int j = i + 1; j < args.length; ++j) {
				if (isOption(args[j])) break;
				result.add(args[j]);
			}
		}
		
		return result;
	}
	
	private boolean isOption(String s) {
		return s.startsWith("-");
	}
	
	private boolean isOptionSingle(String s) {
		return isOption(s) && !s.startsWith("--");
	}
	
	private boolean isOptionDouble(String s) {
		return s.startsWith("--");
	}
	
}
