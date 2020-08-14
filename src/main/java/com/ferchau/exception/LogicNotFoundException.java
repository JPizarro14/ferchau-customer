package com.ferchau.exception;

public class LogicNotFoundException extends LogicException {

	private static final long serialVersionUID = 5855692084408432521L;

	public LogicNotFoundException(String errCode, String errMsg) {
		super(errCode, errMsg);
	}
}
