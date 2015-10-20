package org.cinnamon.apps.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 *
 * created date: 2015. 10. 19.
 * @author 신동성
 */
@Entity
public class Resource {
	
	@Id
	@Column(length=100)
	String resourceId;
	
	@Column(length=100)
	String name;
	
}
