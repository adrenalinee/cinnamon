package org.cinnamon.web.configuration.restController

import javax.validation.Valid

import org.cinnamon.core.domain.EmailServer
import org.cinnamon.core.service.EmailServerService
import org.cinnamon.core.util.ListPage
import org.cinnamon.core.util.PagingUtil
import org.cinnamon.core.vo.EmailServerVo
import org.cinnamon.core.vo.search.EmailServerSearch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping(value="/rest/configuration/email")
class EmailRestController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EmailServerService emailServerService;
	
	/**
	 * 이메일 서버 목록
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerSearch
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value="/servers")
	ListPage<EmailServer> emailList(EmailServerSearch emailServerSearch, Pageable pageable) {
		logger.info("start")
		
		int size = pageable.getPageSize()

		Page<EmailServer> domains = emailServerService.getList(emailServerSearch, pageable)
		PagingUtil paging = new PagingUtil(domains.getNumber() + 1, size, domains.getTotalElements())

		ListPage<EmailServer> domainPage = new ListPage<EmailServer>()
		domainPage.setContent(domains.getContent())
		domainPage.setPaging(paging)

		domainPage 
	}
	
	/**
	 * 이메일 서버 등록
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerVo
	 * @param builder
	 * @return
	 */
	@RequestMapping(value="/servers", method=RequestMethod.POST)
	ResponseEntity<Void> createEmailServer( @Valid @RequestBody EmailServerVo emailServerVo, UriComponentsBuilder builder) {
		logger.info("start")
		EmailServer emailServer = emailServerService.saveEmailServer(emailServerVo);
		
		URI location = builder.path("/configuration/email/servers/{emailServerId}")
				.buildAndExpand(emailServer.getEmailServerId()).toUri()

		ResponseEntity.created(location).build()
	}
	
	
	/**
	 * 메일 서버 조회
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerId
	 * @return
	 */
	@RequestMapping(value="/server/{emailServerId}")
	EmailServer findEmailServer(@PathVariable Long emailServerId) {
		logger.info("start")
		emailServerService.getEmailServer(emailServerId);
	}
	
	/**
	 * 메일 서버 정보 수정
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerId
	 * @param emailServerVo
	 */
	@RequestMapping(value="/server/{emailServerId}", method=RequestMethod.PUT)
	void putEmailServer(@PathVariable Long emailServerId, @Valid @RequestBody EmailServerVo emailServerVo) {
		logger.info("start")
		emailServerService.modifyEmailServer(emailServerId, emailServerVo)
	}
	
	/**
	 * 메일 서버 삭제
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerId
	 */
	@RequestMapping(value="/server/{emailServerId}", method=RequestMethod.DELETE)
	void removeEmailServer(@PathVariable Long emailServerId) {
		logger.info("start")
		emailServerService.removeEmailServer(emailServerId)
	}
	
	/**
	 * 메일 발송 테스트
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerId
	 * @return
	 */
	
	@RequestMapping(value="/server/{emailServerId}/test")
	@ResponseBody
	Map emailServerSendTest(@PathVariable Long emailServerId, @ModelAttribute EmailServerVo emailServerVo) {
		logger.info("start")
		String message = emailServerService.mailSendTest(emailServerId, emailServerVo)
		Map resultMap = new HashMap()
		resultMap.put("msg", message)
		return resultMap
	}
	
	/**
	 * 기본 이메일 서버 설정
	 * @author 정명성
	 * create date : 2016. 3. 8.
	 * @param emailServerId
	 */
	@RequestMapping(value="/server/{emailServerId}", method=RequestMethod.PATCH)
	void patchDefaultEmailServer(@PathVariable Long emailServerId) {
		logger.info("start")
		emailServerService.setDefaultEmailServer(emailServerId)
	}
}
