package org.cinnamon.web.configuration.repository;

import org.cinnamon.web.configuration.domain.EmailServer;
import org.cinnamon.web.configuration.vo.search.EmailServerSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface EmailServerRepositoryCustom {

	Page<EmailServer> search(EmailServerSearch emailServerSearch, Pageable pageable);

	EmailServer getDefault();

}
