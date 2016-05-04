package org.cinnamon.core.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * create date: 2015. 3. 24.
 * @author 동성
 *
 */
public class SiteVo {
	
	@NotEmpty
	@Size(max=200)
	String siteId;
	
	@NotEmpty
	@Size(max=200)
	String name;
	
//	@NotEmpty
	@Size(max=200)
	String url;
	
	@Size(max=50)
	String label;
	
	@Size(max=4000)
	String description;
	
//	UseStatus useStatus;
//	
//	Date createdAt;

//	public String getSiteId() {
//		return siteId;
//	}
//
//	public void setSiteId(String siteId) {
//		this.siteId = siteId;
//	}

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

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
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
//
//	public UseStatus getUseStatus() {
//		return useStatus;
//	}
//
//	public void setUseStatus(UseStatus useStatus) {
//		this.useStatus = useStatus;
//	}
	
}
