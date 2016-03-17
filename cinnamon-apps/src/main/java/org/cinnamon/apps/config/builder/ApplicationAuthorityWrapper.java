package org.cinnamon.apps.config.builder;

import org.cinnamon.apps.domain.ApplicationAuthority;

/**
 * 
 *
 * created date: 2015. 10. 21.
 * @author 신동성
 */
public class ApplicationAuthorityWrapper {
	
	ApplicationAuthority authority;
	
	ApplicationAuthorityWrapper(String name, String authority) {
		this.authority = new ApplicationAuthority();
		this.authority.setAuthority(authority);
		this.authority.setName(name);
	}
	
	public ApplicationAuthorityWrapper description(String description) {
		authority.setDescription(description);
		return this;
	}
}
