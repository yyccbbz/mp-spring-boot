package com.evergrande.springboot.excel.exception;

/**
 * Excel异常
 * @author lisuo
 *
 */
public class ExcelException extends RuntimeException{

	private static final long serialVersionUID = 3240288821877252548L;

	public ExcelException() {
		super();
	}

	public ExcelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExcelException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExcelException(String message) {
		super(message);
	}

	public ExcelException(Throwable cause) {
		super(cause);
	}
	
	
	
}
