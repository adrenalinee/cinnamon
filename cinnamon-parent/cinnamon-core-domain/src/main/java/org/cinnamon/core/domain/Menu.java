package org.cinnamon.core.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.domain.enumeration.MenuType;
import org.cinnamon.core.domain.enumeration.UseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author 동성
 *
 */
@Entity
public class Menu {
	
	@Id
	@GeneratedValue
	Long menuId;
	
	@ManyToOne
	Menu parent;
	
	@JsonIgnore
	@OneToMany(mappedBy="parent")
	@OrderBy("orders asc")
	List<Menu> childs;
	
	@Column(nullable=false)
	String name;
	
	@Column
	String iconClass;
	
	@Column
	String uri;
	
	/**
	 * 툴팁 팝업에 표시될 메시지
	 */
	String toolip;
	
	/**
	 * 메뉴가 표시되는 방법 결정
	 */
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	MenuType type = MenuType.normal;
	
	//@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	MenuPosition position;
	
//	@JsonIgnore
//	@ManyToMany(mappedBy="menus")
//	Set<MenuGroup> menuGroups;
	
	@JsonIgnore
	@ManyToOne
	MenuGroup menuGroup;
	
	@JsonIgnore
	@OneToMany(mappedBy="menu")
	Set<PermissionMenu> permissionMenus;
	
	
	/**
	 * 순서
	 */
	@Column
	Integer orders;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	UseStatus useStatus = UseStatus.enable; 
	
	@Column
	String description;

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public UseStatus getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(UseStatus useStatus) {
		this.useStatus = useStatus;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public MenuType getType() {
		return type;
	}

	public void setType(MenuType type) {
		this.type = type;
	}

	public String getToolip() {
		return toolip;
	}

	public void setToolip(String toolip) {
		this.toolip = toolip;
	}

	public List<Menu> getChilds() {
		return childs;
	}

	public void setChilds(List<Menu> childs) {
		this.childs = childs;
	}

	public Set<PermissionMenu> getPermissionMenus() {
		return permissionMenus;
	}

	public void setPermissionMenus(Set<PermissionMenu> permissionMenus) {
		this.permissionMenus = permissionMenus;
	}

	public MenuPosition getPosition() {
		return position;
	}

	public void setPosition(MenuPosition position) {
		this.position = position;
	}

	public MenuGroup getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}

//	public Set<SiteScene> getSiteScenes() {
//		return siteScenes;
//	}
//
//	public void setSiteScenes(Set<SiteScene> siteScenes) {
//		this.siteScenes = siteScenes;
//	}
	
}
