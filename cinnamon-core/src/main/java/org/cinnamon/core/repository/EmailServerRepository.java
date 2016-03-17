package org.cinnamon.core.repository;

import org.cinnamon.core.domain.EmailServer;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface EmailServerRepository extends JpaRepository<EmailServer, Long>, EmailServerRepositoryCustom {
	/**
	 * 기본 메일 서버 조회
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param useStatus
	 * @param defaultServer
	 * @return
	 */
	EmailServer findByUseStatusAndDefaultServer(UseStatus useStatus, boolean defaultServer);
}
