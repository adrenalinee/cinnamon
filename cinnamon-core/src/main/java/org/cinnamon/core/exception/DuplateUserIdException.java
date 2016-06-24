package org.cinnamon.core.exception;

/**
 * 사용자 아이디 중복
 * 
 * @author 신동성
 * @since 2016. 6. 23.
 */
public class DuplateUserIdException extends BadRequestException {

	private static final long serialVersionUID = 6506995831067087418L;
	
	public DuplateUserIdException(String message) {
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
