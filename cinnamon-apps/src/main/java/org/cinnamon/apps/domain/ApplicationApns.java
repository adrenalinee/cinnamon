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
public class ApplicationApns implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5429699740177081782L;

	@Id
	Long appId;
	
	@OneToOne
	@JoinColumn(name="appId", insertable=false, updatable=false)
	Application application;
	
	/**
	 * cert file path
	 */
	@Column(length=500)
	String certFile;
	
	/**
	 * cert password. 
	 * 암호화 됨.
	 */
	@Column(length=500)
	String certPassword;
	
	
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

	public String getCertFile() {
		return certFile;
	}

	public void setCertFile(String certFile) {
		this.certFile = certFile;
	}

	public String getCertPassword() {
		return certPassword;
	}

	public void setCertPassword(String certPassword) {
		this.certPassword = certPassword;
	}
	
	
}
