package org.cinnamon.apps.config.builder;

import org.cinnamon.apps.domain.Scope;

/**
 * 
 *
 * created date: 2015. 10. 21.
 * @author 신동성
 */
public class ScopeWrapper {
	
	Scope scope;
	
	ScopeWrapper(String scope) {
		this.scope = new Scope(scope);
	}
	
	public ScopeWrapper description(String description) {
		scope.setDescription(description);
		return this;
	}
}
