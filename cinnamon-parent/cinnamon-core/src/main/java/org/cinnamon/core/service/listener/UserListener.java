package org.cinnamon.core.service.listener;

import org.cinnamon.core.domain.UserBase;

/**
 * 
 * create date: 2015. 5. 11.
 * @author 신동성
 *
 */
public interface UserListener {
	
	/**
	 * 개발과정에서 사용할 리스너의 이름을 정의 한다.
	 * @return
	 */
	String name();
	
	/**
	 * 회원가입 과정이 완료되고 트렌젝션이 닫히지 않은 상태에서 오출되는 이벤트임.
	 * afterJoin에서 에러가 발생하면 회원가입 로직이 롤백된다.
	 */
	void afterJoin(UserBase user) throws Exception;
}
