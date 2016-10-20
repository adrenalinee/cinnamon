package org.cinnamon.core.exception;

/**
 * 비밀번호 틀림
 * 
 * @author 신동성
 * @since 2016. 6. 23.
 */
public class IncorrectPasswordException extends BadRequestException {

	private static final long serialVersionUID = -8602767138722400196L;
	
	public IncorrectPasswordException(String message) {
		super(message);
	}
	
	@Override
	public String getErrorCode() {
		return "ER0104";
	}

	@Override
	public String getDescription() {
		return "비밀번호가 정확하지 않습니다.";
	}

}
