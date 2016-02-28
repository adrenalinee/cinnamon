package org.cinnamon.web.configuration.restController

import javax.validation.Valid

import org.cinnamon.core.domain.Site
import org.cinnamon.core.domain.UserGroup
import org.cinnamon.core.service.SiteService
import org.cinnamon.core.vo.SiteVo
import org.cinnamon.core.vo.search.SiteSearch
import org.dozer.Mapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder

/**

 */
@RestController
@RequestMapping("/rest/configuration/sites")
class SiteRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	SiteService siteService
	
	
	@RequestMapping(value="", method=RequestMethod.POST)
	ResponseEntity<Void> postSites(@RequestBody @Valid SiteVo siteVo, UriComponentsBuilder builder) {
		logger.info("String")
		
		Site site = siteService.save(siteVo)
		
		URI location = MvcUriComponentsBuilder
			.fromMethodName(
				builder,
				SiteRestController.class,
				"getSites",
				site.getSiteId())
			.build()
			.toUri()
		
		return ResponseEntity
			.created(location)
			.build()
	}
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<Site> getSites(SiteSearch siteSearch, Pageable pageable) {
		logger.info("String")
		
		return siteService.getList(siteSearch, pageable)
	}
	
	
	@RequestMapping(value="{SiteId}", method=RequestMethod.GET)
	UserGroup getSite(@PathVariable Long SiteId) {
		logger.info("String")
		
		return siteService.get(SiteId)
	}
	
	
	@RequestMapping(value="{SiteId}", method=RequestMethod.PUT)
	void putSite(@PathVariable Long SiteId, @RequestBody @Valid SiteVo siteVo) {
		logger.info("String")
		
		siteService.save(SiteId, userGroupVo)
	}
}
