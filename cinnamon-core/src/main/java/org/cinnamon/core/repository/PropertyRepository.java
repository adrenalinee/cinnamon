package org.cinnamon.core.repository;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.Property;
import org.cinnamon.core.enumeration.DefinedDBProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author 동성
 * @since 2015. 1. 6.
 */
@Repository
public class PropertyRepository {
	
	@Autowired
	EntityManager em;
	
	public void save(Property property) {
		em.merge(property);
	}
	
	public Property findByName(String name) {
		return em.find(Property.class, name);
	}
	
	public Property findByName(DefinedDBProperty name) {
		return em.find(Property.class, name.name());
	}
	
}
