package org.cinnamon.core.vo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author 동성
 * @since 2015. 1. 16.
 */
public class UserJoinVo {
	
	@NotEmpty
	@Size(min=6, max=20)
	@Pattern(regexp="^[a-z0-9]*$")
	String userId;
	
	@NotEmpty
	@Size(max=100)
	@Pattern(regexp="^[a-zA-Z0-9가-힣\\s]*$")
	String name;
	
	@Email
	@Size(max=200)
	String email;
	
	@NotEmpty
	@Size(min=6, max=30)
	@Pattern(regexp="^[a-zA-Z0-9`~!@#$%^&*()-=_+\\[\\]{}:;',./<>?\\\\|]*$")
	String password;
	
	@NotEmpty
	@Size(min=6, max=30)
	String password2;

//	public String getAdminId() {
//		return adminId;
//	}
//
//	public void setAdminId(String adminId) {
//		this.adminId = adminId;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
