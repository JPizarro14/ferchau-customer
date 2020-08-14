package com.ferchau.exception;

public class ErrorMessage {
	
	private final String error;
	private final String detail;

	public ErrorMessage(String error, String detail) {
		super();
		this.error = error;
		this.detail = detail;
	}

	public String getError() {
		return error;
	}

	public String getDetail() {
		return detail;
	}
	
}
