package org.cinnamon.core.service;

import org.cinnamon.core.domain.EmailServer;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.repository.EmailServerRepository;
import org.cinnamon.core.util.EmailUtil;
import org.cinnamon.core.vo.EmailServerVo;
import org.cinnamon.core.vo.search.EmailServerSearch;
import org.dozer.Mapper;
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
	
	@Autowired
	Mapper mapper;
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
	public EmailServer saveEmailServer(EmailServerVo emailServerVo) {
		logger.info("start");
		
		EmailServer emailServer = new EmailServer();
		BeanUtils.copyProperties(emailServerVo, emailServer);
		
		
		emailServerRepository.save(emailServer);
		
		return emailServer;
	}
	
	/**
	 * 메일 서버 상세 조회
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerId
	 * @return
	 */
	@Transactional(readOnly=true)
	public EmailServer getEmailServer(Long emailServerId) {
		EmailServer emailServer = emailServerRepository.findOne(emailServerId);
		
		if(emailServer == null || emailServer.getUseStatus() != UseStatus.enable) {
			throw new NotFoundException("이메일 서버가 존재하지 않습니다. emailServerId : " + emailServerId);
		}
		
		return emailServer;
	}
	
	/**
	 * 메일 서버 삭제
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerId
	 */
	@Transactional
	public void removeEmailServer(Long emailServerId) {
		EmailServer emailServer = emailServerRepository.findOne(emailServerId);
		if(emailServer == null || emailServer.getUseStatus() == UseStatus.deleted) {
			throw new NotFoundException("이메일 서버가 존재하지 않습니다. emailServerId : " + emailServerId);
		}
		emailServer.setUseStatus(UseStatus.deleted);
	}
	
	/**
	 * 메일 서버 정보 변경
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerId
	 * @param emailServerVo
	 */
	@Transactional
	public void modifyEmailServer(Long emailServerId, EmailServerVo emailServerVo) {
		EmailServer emailServer = emailServerRepository.findOne(emailServerId);
		if(emailServer == null || emailServer.getUseStatus() != UseStatus.enable) {
			throw new NotFoundException("이메일 서버가 존재하지 않습니다. emailServerId : " + emailServerId);
		}
		mapper.map(emailServerVo, emailServer);
	}
	
	
	/**
	 * 기몬 메일 서버 설정
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerId
	 */
	@Transactional
	public void setDefaultEmailServer(Long emailServerId) {
		EmailServer emailServer = emailServerRepository.findByUseStatusAndDefaultServer(UseStatus.enable, true);
		if (emailServer != null) {
			emailServer.setDefaultServer(false);
			// 기존 메일 기본서버 갱신
		}
		emailServer = emailServerRepository.findOne(emailServerId);
		if (emailServer == null || emailServer.getUseStatus() != UseStatus.enable) {
			throw new NotFoundException("메일 서버 정보가 없습니다. emailServerId : " + emailServerId);
		}
		// 전달 받은 메일 서버 아이디 기본 서버 저장
		emailServer.setDefaultServer(true);
	}
	
	@Autowired
	private EmailUtil emailUtil;
	
	/**
	 * 메일 서버 발송 테스트
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerId
	 * @return
	 */
	public String mailSendTest(Long emailServerId) throws Exception {
		EmailServer emailServer = emailServerRepository.findOne(emailServerId);
		if (emailServer == null || emailServer.getUseStatus() != UseStatus.enable) {
			throw new NotFoundException("메일 서버 정보가 없습니다. emailServerId : " + emailServerId);
		}
		return emailUtil.sendMailTest(emailServer);
	}
}
