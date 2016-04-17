package org.cinnamon.core.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author shindongseong
 * @since 2016. 4. 13.
 */
public class UserPasswordVo {
	
	@NotEmpty
	String currentPassword;
	
	@NotEmpty
	String newPassword;
	
	@NotEmpty
	String newPasswordConfirm;

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
	
}
