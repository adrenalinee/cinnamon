package org.cinnamon.core.enumeration;

/**
 * 
 * @author 동성
 * @since 2015. 1. 20.
 */
public enum DefinedDBProperty {
	
	/**
	 * 최초 설치시 DB 초기화가 되어있는지 확인
	 */
	initialize,
	
	/**
	 * 사이트 공개 범위
	 */
	
	
	/**
	 * 회원 가입 방법
	 */
	
	
//	/**
//	 * 기본 서버운영자 그룹
//	 * 최초에 생성한 운영자는 바로 기본 서버운영자 그룹에 포함된다.
//	 */
//	defaultSystemMasterGroup,
//	
//	
//	/**
//	 * 기본 사용자 그룹
//	 * 가입시 기본 그룹에 바로 포함된다.
//	 */
//	defaultUserGroup,
	
	
	/**
	 * 전체 시스템의 최초 운영자.
	 * 최초 운영자는 무조건 최고 권한을 가지며 시스템에서 지워질 수 없다.
	 */
	systemFirstUserId
}
