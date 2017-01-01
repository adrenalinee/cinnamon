package org.cinnamon.web.configuration.rememberme

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

/**
 * 
 * @author 신동성
 * @since 2016. 2. 3.
 */
@Entity
class PersistentLogins {
	
	@Id
	@Column(length=64)
	String series
	
	@Column(nullable=false, length=64)
	String username
	
	@Column(nullable=false, length=64)
	String token
	
	@Column(nullable=false)
	Date lastUsed
}
