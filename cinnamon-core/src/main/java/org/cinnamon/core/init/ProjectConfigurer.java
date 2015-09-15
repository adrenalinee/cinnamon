package org.cinnamon.core.init;

import org.cinnamon.core.init.wrapper.ProjectBuilder;

/**
 * 
 * @author shindongseong
 *
 */
public interface ProjectConfigurer {
	
	/**
	 * 
	 * @param projectBuilder
	 */
	void configure(ProjectBuilder projectBuilder);
}
