package org.cinnamon.web.configuration.restController

import org.cinnamon.core.vo.SiteMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author shindongseong
 *
 */
@RestController
@RequestMapping("/rest/configuration/menus")
class MenuRestController {
	Logger logger = LoggerFactory.getLogger(getClass())
	
}
