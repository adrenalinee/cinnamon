package org.cinnamon.common.exceptions;

/**
 * http 401 Unauthorized 에러 정의
 * 
 * @author 동성
 * @since 2014. 12. 16.
 */
public abstract class UnauthorizedException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5556063789008655936L;
	
	public UnauthorizedException(String message) {
		super(message);
	}
}
