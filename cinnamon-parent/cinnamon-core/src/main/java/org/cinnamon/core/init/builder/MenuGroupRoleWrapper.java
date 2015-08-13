package org.cinnamon.core.init.builder;


/**
 * 메뉴 그룹에 포함된 전체 메뉴에 대한 일괄 권한 설정
 * 
 * create date: 2015. 5. 27.
 * @author 신동성
 *
 */
public class MenuGroupRoleWrapper {
	
//	private MenuGroupWrapper menuGroupWrapper;
	
//	private PermissionMenu permissionMenu;
	
	private String authority;
	
	/**
	 * 조회 & 상세 화면
	 */
//	private boolean readPower;
//	
//	private boolean createPower;
//	
//	private boolean updatePower;
//	
//	private boolean deletePower;
	
	private boolean permitAll;
	
	private boolean permitElse;
	
	private boolean permitRoot;
	
	private boolean permitCreate;
	
	private boolean permitView;
	
	private boolean permitModify;
	
	private boolean permitSearch;
	
	
	
	
	MenuGroupRoleWrapper(String authority) {
//		this.menuGroupWrapper = menuWrapper;
		this.authority = authority;
	}
	
	boolean isPermitElse() {
		return permitElse;
	}
	
	boolean isPermitRoot() {
		return permitRoot;
	}
	
	boolean isPermitCreate() {
		return permitCreate;
	}
	
	boolean isPermitView() {
		return permitView;
	}
	
	boolean isPermitModify() {
		return permitModify;
	}
	
	boolean isPermitSearch() {
		return permitSearch;
	}
	
//	public MenuGroupRoleWrapper permitNone() {
//		permitElse = false;
//		permitRoot = false;
//		permitCreate = false;
//		permitView = false;
//		permitModify = false;
//		permitSearch = false;
//		return this;
//	}
	

	public void permitAll() {
		permitAll = true;
	}
	
	public MenuGroupRoleWrapper denyElse() {
		permitElse = false;
		return this;
	}
	
	public MenuGroupRoleWrapper permitElse() {
		permitElse = true;
		return this;
	}
	
	public MenuGroupRoleWrapper permitRoot() {
		permitRoot = true;
		return this;
	}
	
	public MenuGroupRoleWrapper permitCreate() {
		permitCreate = true;
		return this;
	}
	
	public MenuGroupRoleWrapper permitView() {
		permitView = true;
		return this;
	}
	
	public MenuGroupRoleWrapper permitModify() {
		permitModify = true;
		return this;
	}
	
	public MenuGroupRoleWrapper permitSearch() {
		permitSearch = true;
		return this;
	}
	
	
	
	
//	public MenuGroupWrapper role(String authority) {
//		return menuGroupWrapper.role(authority);
//	}
//	
//	public MenuGroupWrapper and() {
//		return menuGroupWrapper;
//	}
}
