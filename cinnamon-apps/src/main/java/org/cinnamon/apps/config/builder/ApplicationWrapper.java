package org.cinnamon.apps.config.builder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.cinnamon.apps.domain.Application;

/**
 * 
 *
 * created date: 2015. 10. 21.
 * @author 신동성
 */
public class ApplicationWrapper {
	
	Application app;
	
	List<ClientWrapper> clientWrappers = new LinkedList<>();
	
	List<String> authorities = new LinkedList<>();
	
	
	ApplicationWrapper(String name) {
		app = new Application();
		app.setName(name);
	}
	
	public ApplicationWrapper description(String description) {
		app.setDescription(description);
		return this;
	}
	
	public ApplicationWrapper addClients(ClientWrapper... clientWrappers) {
		this.clientWrappers.addAll(Arrays.asList(clientWrappers));
		return this;
	}
	
	public ApplicationWrapper addAuthorities(Object... authorities) {
		Arrays.asList(authorities).forEach(authority -> {
			this.authorities.add(authority.toString());
		});
		return this;
	}
}
