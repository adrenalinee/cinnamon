package org.cinnamon.core.domain;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

/**
 * 
 * @author 동성
 *
 */
@Data
@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="menuId")
public class Menu implements Serializable { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7485820146855184146L;

	@Id
	@GeneratedValue
	Long menuId;
	
//	@JsonManagedReference
	@ManyToOne
	Menu parent;
	
//	@JsonIgnore
//	@JsonBackReference
	@OneToMany(mappedBy="parent")
	@OrderBy("orders asc")
	List<Menu> childs;
	
	@Column(nullable=false)
	String name;
	
	@Column
	String code;
	
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
	
//	@JsonIgnore
	@ManyToOne
	MenuGroup menuGroup;
	
//	@JsonIgnore
//	@OneToMany(mappedBy="menu")
//	Set<PermissionMenu> permissionMenus;
	
	/**
	 * 메뉴 접근이 허용된 역할
	 */
//	@JsonIgnore
	@OneToMany(mappedBy="menu")
	Set<PermissionMenu> grantedAuthorities;
	
	/**
	 * 순서
	 */
	@Column
	Integer orders;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	UseStatus useStatus = UseStatus.enable; 
	
	@Column(length=4000)
	String description;
}
