package org.cinnamon.apps.repository;

import org.cinnamon.apps.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author 신동성
 * @since 2016. 10. 24.
 */
public interface ResourceRepository extends JpaRepository<Resource, String> {

}
