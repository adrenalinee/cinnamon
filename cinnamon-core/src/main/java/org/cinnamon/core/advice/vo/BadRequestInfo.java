package org.cinnamon.core.advice.vo;

/**
 * 
 * @author 동성
 * @since 2015. 1. 19.
 */
public class BadRequestInfo extends ExceptionBaseInfo {
	
	String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
