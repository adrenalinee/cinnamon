package org.cinnamon.core.repository;

import org.cinnamon.core.domain.FileInformation;
import org.cinnamon.core.vo.search.FileInformationSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface FileInfoRepositoryCustom {
	
	/**
	 * 파일 목록 조회
	 * @author 정명성
	 * create date : 2016. 3. 14.
	 * @param fileInfomationSearch
	 * @return
	 */
	public Page<FileInformation> search(FileInformationSearch fileInfomationSearch, Pageable pageable);
}
