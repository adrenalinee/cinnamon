package org.cinnamon.core.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author 동성
 * @since 2015. 2. 3.
 */
@Entity
@Table(name="permission_menu")
public class MenuAuthority {
	
	@Id
	@GeneratedValue
	@Column(name="permission_menu_id")
	Long menuAuthorityId;
	
	@JsonIgnore
	@ManyToOne
	UserAuthority authority;
	
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
	
//	boolean readOnly;
	
	@MapKey(name="name")
	@OneToMany(mappedBy="menuAuthority")
	Map<String, MenuAuthorityDetail> details = new LinkedHashMap<String, MenuAuthorityDetail>();
	
	
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
		MenuAuthorityDetail detail = new MenuAuthorityDetail();
		detail.setMenuAuthority(this);
		detail.setName("create");
		
		details.put("create", detail);
	}
	
	public void permitView() {
		MenuAuthorityDetail detail = new MenuAuthorityDetail();
		detail.setMenuAuthority(this);
		detail.setName("view");
		
		details.put("view", detail);
	}
	
	public void permitModify() {
		MenuAuthorityDetail detail = new MenuAuthorityDetail();
		detail.setMenuAuthority(this);
		detail.setName("modify");
		
		details.put("modify", detail);
	}
	
	public void permitSearch() {
		MenuAuthorityDetail detail = new MenuAuthorityDetail();
		detail.setMenuAuthority(this);
		detail.setName("search");
		
		details.put("search", detail);
	}
	
	public void permitSelect() {
		MenuAuthorityDetail detail = new MenuAuthorityDetail();
		detail.setMenuAuthority(this);
		detail.setName("select");
		
		details.put("select", detail);
	}
	
	/**
	 * 사용자 정의 퍼미션
	 * @param name
	 */
	public void permit(String name) {
		MenuAuthorityDetail detail = null;
		if (details.containsKey(name)) {
			detail = details.get(name);
		} else {
			detail = new MenuAuthorityDetail();
		}
		
		detail.setMenuAuthority(this);
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

	public Map<String, MenuAuthorityDetail> getDetails() {
		return details;
	}

	public void setDetails(Map<String, MenuAuthorityDetail> details) {
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

	public UserAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(UserAuthority authority) {
		this.authority = authority;
	}

	public Long getMenuAuthorityId() {
		return menuAuthorityId;
	}

	public void setMenuAuthorityId(Long menuAuthorityId) {
		this.menuAuthorityId = menuAuthorityId;
	}
	
}
