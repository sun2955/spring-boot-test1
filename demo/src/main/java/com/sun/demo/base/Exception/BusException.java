package com.sun.demo.base.Exception;

/**
 * 业务异常
 * 
 * @author szy
 * 
 */
public class BusException extends Exception {

	/**
	 * 序列化UID
	 */
	private static final long serialVersionUID = 4116662609025831113L;

	/**
	 * 默认构造器
	 */
	public BusException() {
		super();

	}

	/**
	 * 构造器
	 * @param message 消息
	 */
	public BusException(String message) {
		super(message);
	}
	
	/**
	 * 存储对象引用
	 */
	private Object obj = null;

	/**
	 * 获取对象
	 * @return
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * 设置对象
	 * @param obj
	 */
	public BusException setObj(Object obj) {
		this.obj = obj;
		return this;
	}

}
