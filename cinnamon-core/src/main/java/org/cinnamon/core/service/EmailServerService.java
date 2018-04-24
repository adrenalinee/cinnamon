package org.cinnamon.core.service;

import org.cinnamon.core.domain.EmailServer;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.exception.InternalServerErrorException;
import org.cinnamon.core.exception.InvalidParameterException;
import org.cinnamon.core.exception.NotFoundException;
import org.cinnamon.core.repository.EmailServerRepository;
import org.cinnamon.core.util.BlowfishCryptor;
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
	}
	
	
	/**
	 * 서버 정보 등록
	 * @param emailServer
	 */
	@Transactional
	public EmailServer saveEmailServer(EmailServerVo emailServerVo) {
		logger.info("start");
		
		EmailServer emailServer = new EmailServer();
		BeanUtils.copyProperties(emailServerVo, emailServer);
		BlowfishCryptor e = new BlowfishCryptor();
		
		if(emailServer.getPassword() != null) {
			try {
				emailServer.setPassword(e.encrypt(emailServer.getPassword()));
			} catch (Exception e1) {
				throw new InternalServerErrorException("암호화에 실패했습니다. [서버 정보 등록] " + e1);
			}
		}
		
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
		logger.info("start");
		
		EmailServer emailServer = emailServerRepository.findById(emailServerId).get();
		
		if(emailServer == null || emailServer.getUseStatus() != UseStatus.enable) {
			throw new NotFoundException("이메일 서버가 존재하지 않습니다. emailServerId : " + emailServerId);
		}
		
		if(emailServer.getPassword() != null) {
			BlowfishCryptor e = new BlowfishCryptor();
			emailServer.setPassword(e.decrypt(emailServer.getPassword()));
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
		logger.info("start");
		
		EmailServer emailServer = emailServerRepository.findById(emailServerId).get();
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
		logger.info("start");
		
		EmailServer emailServer = emailServerRepository.findById(emailServerId).get();
		if(emailServer == null || emailServer.getUseStatus() != UseStatus.enable) {
			throw new NotFoundException("이메일 서버가 존재하지 않습니다. emailServerId : " + emailServerId);
		}
		
		if(emailServerVo.getPassword() != null) {
			BlowfishCryptor e = new BlowfishCryptor();
			try {
				emailServerVo.setPassword(e.encrypt(emailServerVo.getPassword()));
			} catch (Exception e1) {
				throw new InternalServerErrorException("암호화에 실패했습니다. [서버 정보 변경] " + e1);
			}
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
		logger.info("start");
		
		EmailServer emailServer = emailServerRepository.findByUseStatusAndDefaultServer(UseStatus.enable, true);
		if (emailServer != null) {
			emailServer.setDefaultServer(false);
			// 기존 메일 기본서버 갱신
		}
		emailServer = emailServerRepository.findById(emailServerId).get();
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
	public String mailSendTest(Long emailServerId, EmailServerVo emailServerVo) throws Exception {
		logger.info("start");
		
		EmailServer emailServer = emailServerRepository.findById(emailServerId).get();
		if (emailServer == null || emailServer.getUseStatus() != UseStatus.enable) {
			throw new NotFoundException("메일 서버 정보가 없습니다. emailServerId : " + emailServerId);
		}
		if(emailServerVo.getToAddress() == null){
			throw new InvalidParameterException("테스트 메일 주소가 없습니다. toAddress : " + emailServerVo.getToAddress());
		}
		return emailUtil.sendMailTest(emailServer, emailServerVo.getToAddress());
	}
}
