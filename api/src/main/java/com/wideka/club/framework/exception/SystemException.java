package com.wideka.club.framework.exception;

/**
 * Exception.
 * 
 * @author xujiakun
 * 
 */
public class SystemException extends Exception {

	private static final long serialVersionUID = 5259805918456538208L;

	private final String errorCode;

	public SystemException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public SystemException(String errorCode, Throwable cause) {
		super(errorCode, cause);
		this.errorCode = errorCode;
	}

	public SystemException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public SystemException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
