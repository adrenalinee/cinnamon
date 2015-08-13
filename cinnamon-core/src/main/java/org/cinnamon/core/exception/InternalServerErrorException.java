package org.cinnamon.core.exception;

/**
 * 
 * @author 동성
 * @since 2015. 1. 13.
 */
public class InternalServerErrorException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3235794368703103235L;

	public InternalServerErrorException(String message) {
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
