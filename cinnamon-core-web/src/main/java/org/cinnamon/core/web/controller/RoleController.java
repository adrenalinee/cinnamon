package org.cinnamon.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author shindongseong
 */
@Controller
@RequestMapping(value="/configuration/roles")
public class RoleController extends BasePageController {
	
	@RequestMapping(value="**")
	String index() {
		return "configuration/roles";
	}
}
