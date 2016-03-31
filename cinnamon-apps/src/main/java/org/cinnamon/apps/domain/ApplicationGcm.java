package org.cinnamon.apps.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 
 * @author 신동성
 * @since 2016. 3. 30.
 */
@Entity
public class ApplicationGcm {
	
	@Id
	Long appId;
	
	@OneToOne
	@JoinColumn(name="appId", insertable=false, updatable=false)
	Application application;
	
	@Column(length=200)
	String apiKey;

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
}
