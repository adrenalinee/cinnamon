package org.cinnamon.core.exception;

/**
 * 이메일 중복
 * 
 * @author 신동성
 * @since 2016. 6. 23.
 */
public class DuplateEmailException extends BadRequestException {

	private static final long serialVersionUID = 3400733869223782133L;
	
	public DuplateEmailException(String message) {
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
