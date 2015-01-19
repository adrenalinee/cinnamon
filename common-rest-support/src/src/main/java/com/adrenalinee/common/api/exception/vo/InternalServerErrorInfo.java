package com.adrenalinee.common.api.exception.vo;

/**
 * response status 500 wrapper
 * @author 동성
 * @since 2015. 1. 19.
 */
public class InternalServerErrorInfo extends ExceptionBaseInfo {
	
	/**
	 * vm에서 생성하는 에외 trace 내용
	 */
	String trace;

	public String getTrace() {
		return trace;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}
	
}
