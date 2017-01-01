package org.cinnamon.users.web.controller.configuration

import org.cinnamon.web.configuration.controller.BasePageController;
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
