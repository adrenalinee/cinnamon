package org.cinnamon.apps.domain;

import java.io.Serializable;

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
public class ApplicationGcm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8602472233835327676L;

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
