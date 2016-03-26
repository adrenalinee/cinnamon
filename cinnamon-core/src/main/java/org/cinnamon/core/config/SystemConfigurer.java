package org.cinnamon.core.config;

import org.cinnamon.core.config.builder.BaseDataBuilder;

/**
 * 
 *
 * created date: 2015. 9. 17.
 * @author 신동성
 */
public interface SystemConfigurer {
	
	void configure(BaseDataBuilder baseData);
	
	/**
	 * 시스템 설정이 실행될 순서를 결정
	 * 
	 * @return
	 */
	int order();
}
