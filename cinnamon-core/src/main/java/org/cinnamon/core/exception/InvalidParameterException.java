package org.cinnamon.core.exception;

import java.util.List;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;


/**
 * 
 * @deprecated spring 버전업되면서 파라미터 유효성 에러를 체크하지 않으면 {@link MethodArgumentNotValidException} 가 자동 throw된다.
 * 이 exception을 이용해서 유효성 에러 치리를 한다.
 * @author dsshin
 * created date: 2014. 4. 11.
 */
@Deprecated
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
