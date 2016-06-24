package org.cinnamon.core.vo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author shindongseong
 * @since 2016. 4. 13.
 */
public class UserPasswordVo {
	
	@NotEmpty
	@Size(min=6, max=30)
	@Pattern(regexp="^[a-zA-Z0-9`~!@#$%^&*()-=_+\\[\\]{}:;',./<>?\\\\|]*$")
	String currentPassword;
	
	@NotEmpty
	@Size(min=6, max=30)
	@Pattern(regexp="^[a-zA-Z0-9`~!@#$%^&*()-=_+\\[\\]{}:;',./<>?\\\\|]*$")
	String newPassword;
	
	@NotEmpty
	@Size(min=6, max=30)
	@Pattern(regexp="^[a-zA-Z0-9`~!@#$%^&*()-=_+\\[\\]{}:;',./<>?\\\\|]*$")
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
