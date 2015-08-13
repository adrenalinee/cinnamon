package org.cinnamon.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * create date: 2015. 3. 13.
 * @author 동성
 *
 */
@Entity
public class UserIntroduce {
	
	@Id
	String userId;
	
	/**
	 * 자기 소개
	 */
	String introduce;
	
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
