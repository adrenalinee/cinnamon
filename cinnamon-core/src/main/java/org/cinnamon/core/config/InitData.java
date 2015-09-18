package org.cinnamon.core.config;

import javax.persistence.EntityManager;

/**
 * 
 * @author 동성
 * @since 2015. 1. 7.
 */
public interface InitData {
	
	void save(EntityManager em) throws Exception ;
}
