package org.cinnamon.web.configuration.vo.search;

/**
 * 
 * create date: 2015. 4. 20.
 * @author 동성
 *
 */
public class EmailServerSearch {
	
	Long emailServerId;
	
	String name;
	
	String address;

	public Long getEmailServerId() {
		return emailServerId;
	}

	public void setEmailServerId(Long emailServerId) {
		this.emailServerId = emailServerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
