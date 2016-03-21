package org.cinnamon.core.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cinnamon.core.domain.enumeration.UseStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * create date: 2015. 3. 24.
 * @author 동성
 *
 */
@Entity
public class Site {
	
	@Id
	@Column(length=200)
	String siteId;
	
	@Column(length=200)
	String name;
	
	@Column(length=200)
	String url;
	
//	/**
//	 * 기본 사이트인지 여부
//	 */
//	Boolean isDefault;
	
	@JsonIgnore
	@OneToMany(mappedBy="site")
	Set<MenuGroup> menuGroup;
	
	/**
	 * 로그인후 첫 화면에서 보여줄 페이지 경로
	 * 컨텍스트 루트에서 indexPage로 forward 된다.
	 */
	String indexPage;
	
	/**
	 * 기본 메뉴 그룹
	 */
	@ManyToOne
	MenuGroup defaultMenuGroup;
	
	@Column(length=4000)
	String description;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	UseStatus useStatus = UseStatus.enable;
	
	@Column(nullable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	Date createdAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Set<MenuGroup> getMenuGroup() {
		return menuGroup;
	}

	public void setMenuGroup(Set<MenuGroup> menugroup) {
		this.menuGroup = menugroup;
	}

	public UseStatus getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(UseStatus useStatus) {
		this.useStatus = useStatus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIndexPage() {
		return indexPage;
	}

	public void setIndexPage(String indexPage) {
		this.indexPage = indexPage;
	}

	public MenuGroup getDefaultMenuGroup() {
		return defaultMenuGroup;
	}

	public void setDefaultMenuGroup(MenuGroup defaultMenuGroup) {
		this.defaultMenuGroup = defaultMenuGroup;
	}

//	public Boolean getIsDefault() {
//		return isDefault;
//	}
//
//	public void setIsDefault(Boolean isDefault) {
//		this.isDefault = isDefault;
//	}
	
}
