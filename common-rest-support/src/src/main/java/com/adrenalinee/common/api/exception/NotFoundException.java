package com.adrenalinee.common.api.exception;



/**
 * 
 * @author dsshin
 *
 */
public class NotFoundException extends BaseException {
	
	public NotFoundException(String message) {
		super(message);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1790325113214176477L;

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
