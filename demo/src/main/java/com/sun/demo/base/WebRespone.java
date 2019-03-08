package com.sun.demo.base;

import java.io.Serializable;
import java.util.HashMap;
public class WebRespone implements Serializable{

	/**
	 * 序列化Id
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final String STATUS_SUCCESS = "SUCCESS";
	
	public static final String STATUS_ERROR = "ERROR";
	
	/**
	 * 返回状态码
	 */
	private Integer code;
	
	/**
	 * 返回消息
	 */
	private Object resultMsg;

	/**
	 * 返回结果
	 */
	private String resultState;
	
	/**
	 * 返回数据对象
	 */
	private Object resultObj;

	public Object getResultMsg() {
		return resultMsg;
	}

	public String getResultState() {
		return resultState;
	}

	public Object getResultObj() {
		return resultObj;
	}

	public void setResultMsg(Object resultMsg) {
		this.resultMsg = resultMsg;
	}

	public void setResultState(String resultState) {
		this.resultState = resultState;
	}

	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public WebRespone() {
		
	}	


	public static WebRespone success() {
		WebRespone webRespone = new WebRespone();
		/*webRespone.setCode(0);
		webRespone.setResultState(WebRespone.STATUS_SUCCESS);*/
		webRespone.setCode(0);
		return webRespone;
		
	}
	
	public static WebRespone success(Object message) {
		WebRespone webRespone = new WebRespone();
		webRespone.setCode(0);
		webRespone.setResultMsg(message);
		webRespone.setResultState(WebRespone.STATUS_SUCCESS);
		return webRespone;
	}
	
	
	public static WebRespone success(Object message,Object resultObj) {
		WebRespone webRespone = new WebRespone();
		webRespone.setCode(0);
		webRespone.setResultMsg(message);
		webRespone.setResultObj(resultObj);
		webRespone.setResultState(WebRespone.STATUS_SUCCESS);
		return webRespone;
	}
	
	
	public static WebRespone error(Object message) {
		WebRespone webRespone = new WebRespone();
		webRespone.setCode(404);
		webRespone.setResultMsg(message);
		webRespone.setResultState(WebRespone.STATUS_ERROR);
		return webRespone;
		
	}

}
