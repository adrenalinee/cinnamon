package org.cinnamon.web.configuration.controller;

import org.cinnamon.core.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Autowired
	SiteRepository siteRepository;
	
	@RequestMapping("/test")
	String test() {
		return "test";
	}
	
}
