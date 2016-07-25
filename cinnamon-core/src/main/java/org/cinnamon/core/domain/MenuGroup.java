package org.cinnamon.core.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 
 * create date: 2015. 3. 19.
 * @author 동성
 *
 */
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "menuGroupId")
@Entity
public class MenuGroup implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8361116794185060940L;

	@Id
	@GeneratedValue
	Long menuGroupId;
	
//	String site;
	
	@JsonBackReference
	@ManyToOne(optional=false)
	Site site;
	
	@Column(length=100, nullable=false)
	String name;
	
	@Column(length=50, nullable=false, unique=true)
	String dimension;
	
	/**
	 * 화면에 표시할 라벨
	 */
	@Column(length=100)
	String label;
	
//	@Column(nullable=false)
//	@Enumerated(EnumType.STRING)
//	MenuGroupPosition position;
	
//	@JsonIgnore
//	@ManyToMany
//	List<Menu> menus;
	
	/**
	 * 기본으로 접근할 페이지 경로
	 */
	@Column(length=200)
	String defaultPage;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="menuGroup")
	List<Menu> menus;
	
	@Column(length=4000)
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

	public String getDefaultPage() {
		return defaultPage;
	}

	public void setDefaultPage(String defaultPage) {
		this.defaultPage = defaultPage;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
	
}
