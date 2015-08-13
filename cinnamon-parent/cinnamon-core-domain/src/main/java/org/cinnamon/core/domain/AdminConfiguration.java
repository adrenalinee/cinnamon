package org.cinnamon.core.domain;

import javax.persistence.Id;

/**
 * 
 * create date: 2015. 4. 20.
 * @author 동성
 *
 */
public class AdminConfiguration {
	
	@Id
	String adminId;
	
	String name;
	
	
}
