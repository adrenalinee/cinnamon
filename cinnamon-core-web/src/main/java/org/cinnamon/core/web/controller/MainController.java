package org.cinnamon.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * created at 2016. 12. 26.
 * @author shindongseong
 */
@Controller
public class MainController {
	
	@RequestMapping("/core/components/**")
	void components() {
		
	}
}
