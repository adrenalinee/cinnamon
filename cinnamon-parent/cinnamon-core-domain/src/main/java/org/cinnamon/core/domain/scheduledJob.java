package org.cinnamon.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 
 * @author 동성
 *
 */
@Entity
public class scheduledJob {
	
	@Id
	@GeneratedValue
	Long scheduledJobId;
	
	@Column(nullable=false)
	String name;
	
	@ManyToOne
	Group shceduleGroup;
	
	
	
}
