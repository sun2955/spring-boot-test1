package com.sun.demo.base.Exception;

/**
 * @date 2018/03
 * @author szy
 *
 */
public class BaseException extends RuntimeException
{


	private static final long serialVersionUID = 1L;

	public BaseException() {
	}

	
	/**
	 * 详细message内容
	 * @param message
	 */
	public BaseException(final String message) {
		super(message);
	}

	/**
	 * コンストラク�?.
	 *
	 * @param cause
	 *            原因
	 */
	public BaseException(final Exception cause) {
		super(cause);
	}
	
	/**
	 * 
	 * @param message
	 * 详细message内容
	 * @param cause
	 *  原因
	 */
	public BaseException(final String message, final Exception cause) {
		super(message, cause);
	}
}
