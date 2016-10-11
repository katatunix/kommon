package com.nghiabui.kommon;

public class DebugException extends RuntimeException {
	
	public DebugException() {
		super();
	}
	
	public DebugException(Exception e) {
		super(e);
	}
	
	public DebugException(String msg) {
		super(msg);
	}
	
}
