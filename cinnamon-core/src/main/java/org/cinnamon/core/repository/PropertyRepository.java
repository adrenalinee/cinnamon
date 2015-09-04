package org.cinnamon.core.repository;

import org.cinnamon.core.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
@RepositoryRestResource(exported=false)
public interface PropertyRepository extends JpaRepository<Property, String> {
	
	Property findByName(String name);
}
