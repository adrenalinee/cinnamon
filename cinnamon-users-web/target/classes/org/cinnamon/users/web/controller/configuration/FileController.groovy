package org.cinnamon.users.web.controller.configuration

import org.cinnamon.web.configuration.controller.BasePageController;
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * file controller
 * @author 정명성
 * create date : 2016. 3. 14.
 * org.cinnamon.web.configuration.controller.FileController.groovy
 */
@Controller
@RequestMapping(value="/configuration/files")
class FileController extends BasePageController {
	
	/**
	 * file index 페이지
	 * @author 정명성
	 * create date : 2016. 3. 14.
	 * @return
	 */
	@RequestMapping(value="**", method=RequestMethod.GET)
	def index() {
		"configuration/files"
	}
}
