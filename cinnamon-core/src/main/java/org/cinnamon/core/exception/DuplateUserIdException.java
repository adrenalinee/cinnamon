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
		return "ER0102";
	}

	@Override
	public String getDescription() {
		return "이미 사용중인 사용자 아이디 입니다.";
	}
}
