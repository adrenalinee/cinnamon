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
		return "ER0103";
	}

	@Override
	public String getDescription() {
		return "이미 사용중인 이메일주소 입니다.";
	}
}
