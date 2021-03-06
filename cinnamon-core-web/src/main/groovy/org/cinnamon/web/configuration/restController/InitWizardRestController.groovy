package org.cinnamon.web.configuration.restController

import javax.validation.Valid

import org.cinnamon.core.config.SystemConfigureService
import org.cinnamon.core.vo.UserJoinVo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * 
 *
 * created date: 2015. 9. 16.
 * @author 신동성
 */
@RestController
@RequestMapping("/rest/configuration/initWizard")
class InitWizardRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	SystemConfigureService systemConfigurerService
	
	@RequestMapping(value="baseData", method=RequestMethod.POST)
	def baseData() {
		logger.info("start")
		
		systemConfigurerService.createBaseData()
		
		new ResponseEntity<Void>(HttpStatus.CREATED)
	}
	
	
	@RequestMapping(value="firstUser", method=RequestMethod.POST)
	def firstUser(@RequestBody @Valid UserJoinVo userVo) {
		logger.info("start")
		
		systemConfigurerService.joinFirstSystemMaster(userVo)
		
		new ResponseEntity<Void>(HttpStatus.CREATED)
	}
}
