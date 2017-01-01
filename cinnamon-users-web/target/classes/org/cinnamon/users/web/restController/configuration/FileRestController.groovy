package org.cinnamon.users.web.restController.configuration

import org.cinnamon.core.service.FileService
import org.cinnamon.core.vo.search.FileInformationSearch
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerimport.*

/**
 * 첨부파일 관리 RestController
 * @author 정명성
 * create date : 2016. 3. 14.
 * org.cinnamon.web.configuration.restController.FileRestController.groovy
 */
@RestController
@RequestMapping(value="/rest/configuration/files")
class FileRestController {
	
	@Autowired
	FileService fileService;
	
	/**
	 * 목록 조회
	 * @author 정명성
	 * create date : 2016. 3. 14.
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	def list(FileInformationSearch fileInfomationSearch, Pageable pageable) {
		fileService.search(fileInfomationSearch, pageable)
	}
	
	/**
	 * 상세 조회
	 * @author 정명성
	 * create date : 2016. 3. 14.
	 * @param fileId
	 * @return
	 */
	@RequestMapping(value="{fileId}", method=RequestMethod.GET)
	def get(@PathVariable Long fileId) {
		fileService.get(fileId)
	}
}
