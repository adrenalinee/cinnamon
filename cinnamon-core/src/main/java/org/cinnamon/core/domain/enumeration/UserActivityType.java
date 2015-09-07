package org.cinnamon.core.domain.enumeration;

/**
 * 
 * @author 동성
 * @since 2015. 1. 26.
 */
public enum UserActivityType {
	
	/**
	 * 로그인
	 */
	login,
	
	/**
	 * 가입
	 */
	join,
	
	/**
	 * 탈퇴
	 */
	leave,
	
	/**
	 * 개인 상태 변경
	 * (user table 수정..)
	 */
	changeState,
	
	/**
	 * 비밀번호 수정
	 */
	changePassword,
	
	/**
	 * 특정 페이지 수정
	 */
	update,
	
	/**
	 * 특정 페이지 생성
	 */
	create,
	
	
}
