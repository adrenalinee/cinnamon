package org.cinnamon.core.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * create date: 2015. 3. 24.
 * @author 동성
 *
 */
public class SiteVo {
	
	@NotEmpty
	String siteId;
	
	@NotEmpty
	String name;
	
	@NotEmpty
	String url;
	
	String description;
	
//	UseStatus useStatus;
//	
//	Date createdAt;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public UseStatus getUseStatus() {
//		return useStatus;
//	}
//
//	public void setUseStatus(UseStatus useStatus) {
//		this.useStatus = useStatus;
//	}
	
}
