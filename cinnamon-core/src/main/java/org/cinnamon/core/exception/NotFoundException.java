package org.cinnamon.core.exception;



/**
 * 
 * @author dsshin
 *
 */
public class NotFoundException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1790325113214176477L;
	
	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String message) {
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
