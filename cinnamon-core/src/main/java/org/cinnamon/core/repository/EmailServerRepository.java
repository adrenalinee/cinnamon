package org.cinnamon.core.repository;

import org.cinnamon.core.domain.EmailServer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface EmailServerRepository extends JpaRepository<EmailServer, Long>, EmailServerRepositoryCustom {

}
