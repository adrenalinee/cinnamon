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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
