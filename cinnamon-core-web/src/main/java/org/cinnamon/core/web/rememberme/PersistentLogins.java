package org.cinnamon.core.web.rememberme;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author shindongseong
 * @since 2016. 12. 20.
 */
@Entity
public class PersistentLogins {
	@Id
	@Column(length=64)
	private String series;
	
	@Column(nullable=false, length=64)
	private String username;
	
	@Column(nullable=false, length=64)
	private String token;
	
	@Column(nullable=false)
	private Date lastUsed;
	
}
