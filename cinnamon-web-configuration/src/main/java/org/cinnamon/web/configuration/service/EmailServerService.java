package org.cinnamon.web.configuration.service;

import org.cinnamon.web.configuration.domain.EmailServer;
import org.cinnamon.web.configuration.repository.EmailServerRepository;
import org.cinnamon.web.configuration.vo.EmailServerVo;
import org.cinnamon.web.configuration.vo.search.EmailServerSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * create date: 2015. 4. 20.
 * @author 동성
 *
 */
@Service
public class EmailServerService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EmailServerRepository emailServerRepository;
	
	
	/**
	 * 
	 * @param emailServerSearch
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page<EmailServer>getList(EmailServerSearch emailServerSearch, Pageable pageable) {
		logger.info("start");
		
		return emailServerRepository.search(emailServerSearch, pageable);
		
//		int size = pageable.getPageSize();
//		
//		Page<EmailServer> domains = emailServerRepository.find(emailServerSearch, pageable);
//		PagingUtil paging = new PagingUtil(domains.getNumber() + 1, size, domains.getTotalElements());
//		
//		
//		ListPage<EmailServer> domainPage = new ListPage<EmailServer>();
//		domainPage.setContent(domains.getContent());
//		domainPage.setPaging(paging);
//		
//		return domainPage;
	}
	
	
	/**
	 * 
	 * @param emailServer
	 */
	@Transactional
	public EmailServer save(EmailServerVo emailServerVo) {
		logger.info("start");
		
		EmailServer emailServer = new EmailServer();
		BeanUtils.copyProperties(emailServerVo, emailServer);
		
		
		emailServerRepository.save(emailServer);
		
		return emailServer;
	}
}
