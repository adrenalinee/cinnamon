package org.cinnamon.core.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * create date: 2015. 3. 19.
 * @author 동성
 *
 */
@Entity
public class MenuGroup {
	
	@Id
	@GeneratedValue
	Long menuGroupId;
	
//	String site;
	
	@JsonIgnore
	@ManyToOne
	Site site;
	
	@Column(nullable=false)
	String name;
	
	@Column(nullable=false)
	String dimension;
	
//	@Column(nullable=false)
//	@Enumerated(EnumType.STRING)
//	MenuGroupPosition position;
	
//	@JsonIgnore
//	@ManyToMany
//	List<Menu> menus;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="menuGroup")
	List<Menu> menus;
	
	String description;
	
//	@Column(nullable=false, updatable=false)
//	@Temporal(TemporalType.TIMESTAMP)
//	Date createdAt;
//
//	@PrePersist
//	protected void onCreate() {
//		createdAt = new Date();
//	}
	
	public Long getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(Long menuGroupId) {
		this.menuGroupId = menuGroupId;
	}

//	public String getSite() {
//		return site;
//	}
//
//	public void setSite(String site) {
//		this.site = site;
//	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

//	public MenuGroupPosition getPosition() {
//		return position;
//	}
//
//	public void setPosition(MenuGroupPosition position) {
//		this.position = position;
//	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
	
}
