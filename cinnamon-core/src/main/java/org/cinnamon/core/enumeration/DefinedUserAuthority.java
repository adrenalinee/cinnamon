package org.cinnamon.core.enumeration;

/**
 * 
 * create date: 2015. 3. 27.
 * @author 동성
 *
 */
public enum DefinedUserAuthority {
	
	/**
	 * 익명 권한 (비로그인)
	 */
	ROLE_ANONYMOUS,
	
	/**
	 * 일반
	 */
	user,
	
	/**
	 * 시스템 최고 운영자
	 * (최초 운영자의 권한임)
	 */
	systemMaster
}
