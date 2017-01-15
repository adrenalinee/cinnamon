package org.cinnamon.web.configuration.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value="/configuration/roles")
class RoleController extends BasePageController {
	
	@RequestMapping(value="**")
	def index() {
		"configuration/roles"
	}
}
