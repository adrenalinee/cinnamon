package org.cinnamon.core.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * file controller
 * @author 정명성
 * create date : 2016. 3. 14.
 */
@Controller
@RequestMapping(value="/configuration/files")
public class FileController extends BasePageController {
	
	/**
	 * file index 페이지
	 * @author 정명성
	 * create date : 2016. 3. 14.
	 * @return
	 */
	@RequestMapping(value="**", method=RequestMethod.GET)
	String index() {
		return "configuration/files";
	}
}
