package org.cinnamon.web.configuration.security;

import java.util.Collection;
import java.util.Set;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author 동성
 * @since 2014. 12. 26.
 */
public class UserDetailImpl extends UserBase implements UserDetails {
	private static final long serialVersionUID = -56609030798300120L;
	
	
	Set<? extends GrantedAuthority> authorities;
	
	String password;
	
	public UserDetailImpl(
			UserBase user,
			Set<? extends GrantedAuthority> authorities) {
		
		BeanUtils.copyProperties(user, this);
		
		this.password = user.getUserPassword().getPassword();
		this.authorities = authorities;
		
		System.out.println("UserDetailImpl: ");
		authorities.forEach(ga -> {
			System.out.println(ga.getAuthority());
		});
	}
	
//	public void addAuthorities(PermissionGrantedAuthority grantedAuthority) {
//		authorities.add((GrantedAuthority) grantedAuthority);
//	}
	
	public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
		
//		return AuthorityUtils.createAuthorityList("ROLE_ADMIN");
	}

	@Override
	public String getUsername() {
		return getUserId();
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
		return getUseStatus().equals(UseStatus.enable);
	}

	@Override
	public String getPassword() {
//		Exception e = new Exception();
//		
//		e.printStackTrace();
		
		
		return this.password;
	}

}
