package org.cinnamon.core.service.userListener;

import org.cinnamon.core.domain.UserBase;

/**
 * 
 * create date: 2015. 5. 11.
 * @author 신동성
 *
 */
public interface AfterUserJoinListener<T extends UserBase> {
	
	/**
	 * 회원가입 과정이 완료되고 트렌젝션이 닫히지 않은 상태에서 오출되는 이벤트임.
	 * afterJoin에서 에러가 발생하면 회원가입 로직이 롤백된다.
	 */
	void afterJoin(T user) throws Exception;
}
