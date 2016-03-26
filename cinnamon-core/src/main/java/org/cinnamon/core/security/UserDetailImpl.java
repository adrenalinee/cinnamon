package org.cinnamon.core.security;

import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserGroup;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author 동성
 * @since 2014. 12. 26.
 */
public class UserDetailImpl implements UserDetails {
	private static final long serialVersionUID = -56609030798300120L;
	
	UserBase userBase;
	
	Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
	
	String password;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public UserDetailImpl(UserBase user/*, Set<? extends GrantedAuthority> authorities*/) {
		this.userBase = user;
		password = user.getUserPassword().getPassword();
		
		logger.info(ToStringBuilder.reflectionToString(user.getUserGroups()));
		
		for (UserGroup userGroup: user.getUserGroups()) {
			String authority = userGroup.getPermission().getAuthority();
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
			authorities.add(simpleGrantedAuthority);
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return userBase.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return userBase.getUseStatus().equals(UseStatus.enable);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

}
