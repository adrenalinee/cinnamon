package org.cinnamon.common.exceptions;


/**
 * 
 * @author dsshin
 * created date: 2014. 4. 21.
 */
public class BadRequestException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3773883653794586404L;
	
	public BadRequestException() {
		super();
	}
	
	public BadRequestException(String message) {
		super(message);
	}

	@Override
	public String getErrorCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
