package org.cinnamon.core.exception;

/**
 * 
 * @author dsshin
 *
 */
@SuppressWarnings("serial")
public abstract class BaseException extends RuntimeException {
	
//	/**
//	 * 
//	 */
//	static final long serialVersionUID = 8354030469784217227L;

	public BaseException() {
		super();
	}
	
	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(String msg, Throwable cause) {
		super(msg);
	}
	
	public abstract String getErrorCode();
	
	public abstract String getDescription();
}
