package org.cinnamon.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 *
 * created date: 2015. 9. 9.
 * @author 신동성
 */
@Controller
@RequestMapping("/configuration/userGroups")
public class UserGroupController extends BasePageController {
	
	@RequestMapping("**")
	String main() {
		return "configuration/userGroups";
	}
}
