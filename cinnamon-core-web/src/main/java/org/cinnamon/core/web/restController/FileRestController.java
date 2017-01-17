package org.cinnamon.core.web.restController;

import org.cinnamon.core.domain.FileInformation;
import org.cinnamon.core.service.FileService;
import org.cinnamon.core.vo.search.FileInformationSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 첨부파일 관리 RestController
 * @author 정명성
 * create date : 2016. 3. 14.
 * org.cinnamon.web.configuration.restController.FileRestController.groovy
 */
@RestController
@RequestMapping(value="/rest/configuration/files")
public class FileRestController {
	
	@Autowired
	private FileService fileService;
	
	/**
	 * 목록 조회
	 * @author 정명성
	 * create date : 2016. 3. 14.
	 * @return
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<FileInformation> list(FileInformationSearch fileInfomationSearch, Pageable pageable) {
		return fileService.search(fileInfomationSearch, pageable);
	}
	
	/**
	 * 상세 조회
	 * @author 정명성
	 * create date : 2016. 3. 14.
	 * @param fileId
	 * @return
	 */
	@RequestMapping(value="{fileId}", method=RequestMethod.GET)
	FileInformation get(@PathVariable Long fileId) {
		return fileService.get(fileId);
	}
}
