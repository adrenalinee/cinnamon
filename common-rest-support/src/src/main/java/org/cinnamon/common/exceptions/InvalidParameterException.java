package org.cinnamon.common.exceptions;

import java.util.List;

import org.springframework.validation.ObjectError;


/**
 *  
 * @author dsshin
 * created date: 2014. 4. 11.
 */
public class InvalidParameterException extends BadRequestException {
	
	List<ObjectError> objectErrors;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2499108754682152203L;

	public InvalidParameterException(String message) {
		super(message);
	}
	
	public InvalidParameterException(List<ObjectError> objectErrors) {
		this.objectErrors = objectErrors;
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

	public List<ObjectError> getObjectErrors() {
		return objectErrors;
	}
}
