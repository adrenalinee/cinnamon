package com.adrenalinee.common.api.exception.vo;

/**
 * 
 * @author 동성
 * @since 2015. 1. 19.
 */
public class Violation {
	
	String paramName;
	
	String message;

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
