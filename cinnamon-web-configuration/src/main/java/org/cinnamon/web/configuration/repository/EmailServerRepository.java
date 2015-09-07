package org.cinnamon.web.configuration.repository;

import org.cinnamon.web.configuration.domain.EmailServer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface EmailServerRepository extends JpaRepository<EmailServer, Long>, EmailServerRepositoryCustom {

}
