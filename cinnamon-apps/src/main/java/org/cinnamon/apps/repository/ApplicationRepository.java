package org.cinnamon.apps.repository;

import org.cinnamon.apps.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 9. 30.
 * @author 신동성
 */
public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
