package com.nghiabui.kommon;

public class AppException extends RuntimeException {
	
	public AppException() {
		super();
	}
	
	public AppException(Exception e) {
		super(e);
	}
	
	public AppException(String msg) {
		super(msg);
	}
	
}
