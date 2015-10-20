package org.cinnamon.web.configuration.security;

import org.cinnamon.core.domain.UserAuthority;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author 동성
 * @since 2015. 2. 6.
 */
public class RoleGrantedAuthority extends UserAuthority implements GrantedAuthority {
	
	private static final long serialVersionUID = 1684136781404098733L;
	
	
	public RoleGrantedAuthority(UserAuthority permission) {
		if (permission != null) {
			BeanUtils.copyProperties(permission, this);
		}
	}
	
	@Override
	public String getAuthority() {
		return super.getAuthority();
	}

}
