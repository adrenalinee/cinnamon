package org.cinnamon.core.vo;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.domain.enumeration.UseStatus;

/**
 * 
 * create date: 2015. 3. 24.
 * @author 동성
 *
 */
public class MenuVo {
	Long menuId;
	
	Menu parent;
	
	String name;
	
	String iconClass;
	
	String uri;
	
	/**
	 * 툴팁 팝업에 표시될 메시지
	 */
	String toolip;
	
	/**
	 * 메뉴가 표시되는 방법 결정
	 */
	String type;
	
	Integer orders;
	
	UseStatus useStatus;
	
	String description;
	
//	/**
//	 * 활성화된 메뉴인지 알려줌
//	 */
//	Boolean active;
	
	
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

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getToolip() {
		return toolip;
	}

	public void setToolip(String toolip) {
		this.toolip = toolip;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Boolean getActive() {
//		return active;
//	}
//
//	public void setActive(Boolean active) {
//		this.active = active;
//	}
}
