package org.cinnamon.core.repository;

import org.cinnamon.core.domain.EmailServer;
import org.cinnamon.core.vo.search.EmailServerSearch;
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
