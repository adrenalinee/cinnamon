package org.cinnamon.core.vo;

import javax.validation.constraints.NotNull;

/**
 * 메뉴 권한 Vo 생성
 * @author 정명성
 * create date : 2016. 3. 7.
 * org.cinnamon.core.vo.PermissionMenuVo.java
 */
public class PermissionMenuVo {
	
	private boolean permitRoot;
	
	private boolean permitElse;
	
	@NotNull
	private long menuId;

	public boolean isPermitRoot() {
		return permitRoot;
	}

	public void setPermitRoot(boolean permitRoot) {
		this.permitRoot = permitRoot;
	}

	public boolean isPermitElse() {
		return permitElse;
	}

	public void setPermitElse(boolean permitElse) {
		this.permitElse = permitElse;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

}
