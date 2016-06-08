package org.cinnamon.core.vo.search;

/**
 * 
 * create date: 2015. 4. 20.
 * @author 동성
 *
 */
public class EmailServerSearch {
	
	String keyword;
	
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
