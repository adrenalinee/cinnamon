package org.cinnamon.core.domain.enumeration;

/**
 * 
 * create date: 2015. 3. 19.
 * @author 동성
 *
 */
public enum MenuType {
	
	/**
	 * 특정 경로로 이동하는 링크 메뉴와
	 * 하위 메뉴를 포함하고 있는 container형 메뉴
	 */
	normal,
	
	/**
	 * 메뉴 구분자
	 */
	separater,
	
	/**
	 * 링크는 없는 단순 텍스트
	 */
	label,
	
	/**
	 * 기능까지 구현된 특정 컴포넌트를 붙임
	 * (메뉴 토글 버튼, 사용자 프로필 버튼..)
	 */
	component
	
}
