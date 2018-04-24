package org.cinnamon.core.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.FileInformation;
import org.cinnamon.core.domain.QFileInformation;
import org.cinnamon.core.vo.search.FileInformationSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;


/**
 * 
 * @author 동성
 * @since 2015. 1. 29.
 */
public class FileInfoRepositoryImpl extends QuerydslRepositorySupport implements FileInfoRepositoryCustom {
	
	public FileInfoRepositoryImpl() {
		super(FileInformation.class);
	}

	@Autowired
	EntityManager em;
	
	/**
	 * 파일 리스트 가져오기
	 * @author 정명성
	 * create date : 2016. 3. 14.
	 * @param fileInformationSearch
	 * @param pageable
	 * @return
	 */
	public Page<FileInformation> search(FileInformationSearch fileInformationSearch, Pageable pageable) {
		QFileInformation fileInformation = QFileInformation.fileInformation;
		
		BooleanBuilder builder = new BooleanBuilder();
		if(!StringUtils.isEmpty(fileInformationSearch.getName())) {
			builder.and(fileInformation.name.like("%" + fileInformationSearch.getName() + "%"));
		}
		if(!StringUtils.isEmpty(fileInformationSearch.getExt())) {
			builder.and(fileInformation.ext.eq(fileInformationSearch.getExt()));
		}
		if(!StringUtils.isEmpty(fileInformationSearch.getOriginFileName())) {
			builder.and(fileInformation.originFileName.like("%" + fileInformationSearch.getOriginFileName() + "%"));
		}
		if(!StringUtils.isEmpty(fileInformationSearch.getType())) {
			builder.and(fileInformation.type.eq(fileInformationSearch.getType()));
		}
		
		JPQLQuery<FileInformation> query = from(fileInformation).where(builder);
		
		List<FileInformation> domains = getQuerydsl().applyPagination(pageable, query).fetch();
		long totalCount = query.fetchCount();
		
		Page<FileInformation> page = new PageImpl<FileInformation>(domains, pageable, totalCount);
		
		return page;
	}
	
	
}
