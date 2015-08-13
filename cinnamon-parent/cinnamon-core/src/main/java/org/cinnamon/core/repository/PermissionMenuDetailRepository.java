package org.cinnamon.core.repository;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.PermissionMenuDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * create date: 2015. 6. 1.
 * @author 신동성
 *
 */
@Repository
public class PermissionMenuDetailRepository {
	
	@Autowired
	EntityManager em;
	
	public void persist(PermissionMenuDetail permissionMenuDetail) {
		em.persist(permissionMenuDetail);
	}
}
