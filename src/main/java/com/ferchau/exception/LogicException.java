package com.ferchau.exception;

public class LogicException extends Exception {
	
	private static final long serialVersionUID = -40387785144821555L;
	private String errCode;

	public LogicException() {
		super();
	}

	public LogicException(String errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
}
