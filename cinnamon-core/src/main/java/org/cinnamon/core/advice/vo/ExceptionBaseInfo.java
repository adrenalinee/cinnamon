package org.cinnamon.core.advice.vo;


/**
 * 
 * @author 동성
 * @since 2015. 1. 19.
 */
public class ExceptionBaseInfo {
	
	/**
	 * 에외 발생 일시
	 */
	Long timestamp;
	
	/**
	 * 에외 발생 메시지
	 */
	String message;
	
	/**
	 * response status code
	 */
	Integer status;
	
	/**
	 * response status text
	 */
	String error;
	
	/**
	 * 발생된 예외 객체
	 */
	String exception;
	
	/**
	 * 에외를 발생시킨 uri 경록
	 */
	String path;
	
	/**
	 * 요청한  http method
	 */
	String method;

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
}
