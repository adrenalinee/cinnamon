package org.cinnamon.core.repository;

import org.cinnamon.core.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface PropertyRepository extends JpaRepository<Property, String> {
	
	Property findByName(String name);
}
