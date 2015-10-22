package org.cinnamon.web.configuration.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.enumeration.UseStatus;
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
	
	Set<? extends GrantedAuthority> authorities = new HashSet<>();
	
	String password;
	
	UserBase user;
	
	public UserDetailImpl(UserBase user) {
		this.user = user;
		
		password = user.getUserPassword().getPassword();
		
		Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
		user.getUserGroups().forEach(userGroup -> {
			simpleGrantedAuthorities.add(new SimpleGrantedAuthority(userGroup.getAuthority().getAuthority()));
		});
		authorities = simpleGrantedAuthorities;
		
		
//		System.out.println("UserDetailImpl: ");
//		authorities.forEach(ga -> {
//			System.out.println(ga.getAuthority());
//		});
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
		return user.getUserId();
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
		return user.getUseStatus().equals(UseStatus.enable);
	}

	@Override
	public String getPassword() {
		return password;
	}

}
