package org.cinnamon.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * create date: 2015. 6. 1.
 * @author 신동성
 *
 */
@Entity
@Table(name="permission_menu_detail")
public class MenuAuthorityDetail {
	
	@Id
	@GeneratedValue
	@Column(name="permission_menu_detail_id")
	Long menuAuthorityDetailId;
	
	@ManyToOne
	MenuAuthority menuAuthority;
	
	@Column(length=100)
	String name;
	
	boolean permit = true;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPermit() {
		return permit;
	}

	public void setPermit(boolean permit) {
		this.permit = permit;
	}

	public Long getMenuAuthorityDetailId() {
		return menuAuthorityDetailId;
	}

	public void setMenuAuthorityDetailId(Long menuAuthorityDetailId) {
		this.menuAuthorityDetailId = menuAuthorityDetailId;
	}

	public MenuAuthority getMenuAuthority() {
		return menuAuthority;
	}

	public void setMenuAuthority(MenuAuthority menuAuthority) {
		this.menuAuthority = menuAuthority;
	}
	
	
}
