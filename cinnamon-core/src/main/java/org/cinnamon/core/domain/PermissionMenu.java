package org.cinnamon.core.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author 동성
 * @since 2015. 2. 3.
 */
@Entity
//@Table(name="permission_menu")
public class PermissionMenu {
	
	@Id
	@GeneratedValue
//	@Column(name="permission_menu_id")
//	Long menuAuthorityId;
	Long permissionMenuId;
	
	@JsonIgnore
	@ManyToOne
//	@JoinColumn(name="permission_permissionId")
	Permission permission;
	
	@JsonIgnore
	@ManyToOne
	Menu menu;
	
//	boolean permitAll;
//	
//	boolean elsePermitAll;
	
	/**
	 * 메뉴에 접근 권한.
	 */
	boolean permitRoot = true;
	
	/**
	 * details에 정의 하지 않은 모든 접근권한
	 * true: 접근 허용
	 * false: 접근 불가
	 */
	boolean permitElse;
	
//	/**
//	 * 특정 권한(permission)에 대해서 기본 메뉴인지를 알려준다.
//	 * Permission당 하나의 PermissionMenu만 defaultMenu가 true 여야 한다.
//	 */
//	boolean defaultMenu;
	
//	boolean readOnly;
	
	@MapKey(name="name")
	@OneToMany(mappedBy="permissionMenu")
	Map<String, PermissionMenuDetail> details = new LinkedHashMap<String, PermissionMenuDetail>();
	
	
	/**
	 * 조회 & 상세 화면
	 */
//	boolean readPower;
//	
//	boolean createPower;
//	
//	boolean updatePower;
//	
//	boolean deletePower;
//	
//	boolean rootPermit;
//	
//	boolean searchPermit;
//	
//	boolean createPermit;
//	
//	boolean viewPermit;
//	
//	boolean modifyPermit;
	
	
	
	public void permitElse() {
		permitElse = true;
	}
	
	public void denyElse() {
		permitElse = false;
	}

	public void denyRoot() {
		permitRoot = false;
	}
	
	public void permitRoot() {
		permitRoot = true;
//		PermissionMenuDetail detail = new PermissionMenuDetail();
//		detail.setPermissionMenu(this);
//		detail.setName("$root");
//		
//		details.put("$root", detail);
	}
	
	public void permitCreate() {
		PermissionMenuDetail detail = new PermissionMenuDetail();
		detail.setPermissionMenu(this);
		detail.setName("create");
		
		details.put("create", detail);
	}
	
	public void permitView() {
		PermissionMenuDetail detail = new PermissionMenuDetail();
		detail.setPermissionMenu(this);
		detail.setName("view");
		
		details.put("view", detail);
	}
	
	public void permitModify() {
		PermissionMenuDetail detail = new PermissionMenuDetail();
		detail.setPermissionMenu(this);
		detail.setName("modify");
		
		details.put("modify", detail);
	}
	
	public void permitSearch() {
		PermissionMenuDetail detail = new PermissionMenuDetail();
		detail.setPermissionMenu(this);
		detail.setName("search");
		
		details.put("search", detail);
	}
	
	public void permitSelect() {
		PermissionMenuDetail detail = new PermissionMenuDetail();
		detail.setPermissionMenu(this);
		detail.setName("select");
		
		details.put("select", detail);
	}
	
	/**
	 * 사용자 정의 퍼미션
	 * @param name
	 */
	public void permit(String name) {
		PermissionMenuDetail detail = null;
		if (details.containsKey(name)) {
			detail = details.get(name);
		} else {
			detail = new PermissionMenuDetail();
		}
		
		detail.setPermissionMenu(this);
		detail.setName(name);
		detail.setPermit(true);
		
		details.put(name, detail);
	}
	
//	public void allPermission() {
//		readPower = true;
//		createPower = true;
//		updatePower = true;
//		deletePower = true;
//	}
	
	public boolean hasPermission() {
		if (permitElse) {
			return true;
		}
		return !details.isEmpty();
	}
	
//	public UserAuthority getRole() {
//		return role;
//	}
//
//	public void setRole(UserAuthority role) {
//		this.role = role;
//	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

//	public Long getPermissionMenuId() {
//		return permissionMenuId;
//	}
//
//	public void setPermissionMenuId(Long permissionMenuId) {
//		this.permissionMenuId = permissionMenuId;
//	}

//	public boolean isReadPower() {
//		return readPower;
//	}
//
//	public void setReadPower(boolean readPower) {
//		this.readPower = readPower;
//	}
//
//	public boolean isCreatePower() {
//		return createPower;
//	}
//
//	public void setCreatePower(boolean createPower) {
//		this.createPower = createPower;
//	}
//
//	public boolean isUpdatePower() {
//		return updatePower;
//	}
//
//	public void setUpdatePower(boolean updatePower) {
//		this.updatePower = updatePower;
//	}
//
//	public boolean isDeletePower() {
//		return deletePower;
//	}
//
//	public void setDeletePower(boolean deletePower) {
//		this.deletePower = deletePower;
//	}

	public Map<String, PermissionMenuDetail> getDetails() {
		return details;
	}

	public void setDetails(Map<String, PermissionMenuDetail> details) {
		this.details = details;
	}

	public Boolean getPermitElse() {
		return permitElse;
	}

	public void setPermitElse(Boolean permitElse) {
		this.permitElse = permitElse;
	}

	public boolean isPermitElse() {
		return permitElse;
	}

	public void setPermitElse(boolean permitElse) {
		this.permitElse = permitElse;
	}

	public boolean isPermitRoot() {
		return permitRoot;
	}

	public void setPermitRoot(boolean permitRoot) {
		this.permitRoot = permitRoot;
	}

	public Long getPermissionMenuId() {
		return permissionMenuId;
	}

	public void setPermissionMenuId(Long permissionMenuId) {
		this.permissionMenuId = permissionMenuId;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

//	public boolean isDefaultMenu() {
//		return defaultMenu;
//	}
//
//	public void setDefaultMenu(boolean defaultMenu) {
//		this.defaultMenu = defaultMenu;
//	}

//	public Permission getAuthority() {
//		return authority;
//	}
//
//	public void setAuthority(Permission authority) {
//		this.authority = authority;
//	}
//
//	public Long getMenuAuthorityId() {
//		return menuAuthorityId;
//	}
//
//	public void setMenuAuthorityId(Long menuAuthorityId) {
//		this.menuAuthorityId = menuAuthorityId;
//	}
	
}
