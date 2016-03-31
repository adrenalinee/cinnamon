package org.cinnamon.core;

import java.util.Arrays;

import org.cinnamon.core.domain.UserBase;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 
 * @author sds
 *
 */
@Configuration
@ComponentScan
@EntityScan(basePackageClasses=UserBase.class)
@EnableJpaRepositories
public class CinnamonCoreConfiguration {
	
	
	@Bean
	Mapper beanMapper() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Arrays.asList("dozer-global-configuration.xml"));
		
		return mapper;
	}
	
//	/**
//	 * request를 json으로 리턴할때  처리
//	 * 1. 하이버네이트 lazy object 를 읽을때 오류나는 문제 해결
//	 * 2. Date 포맷을 전세계 시간 으로 변환 가능한 표준 포맷으로 변경 (ex: 2016-02-24T16:35.000+09:00)
//	 * @return
//	 */
//	@Bean
//	MappingJackson2HttpMessageConverter jacksonMessageConverter() {
//		DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//		defaultDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
//		
//		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
//		
//		
//		ObjectMapper mapper = new ObjectMapper();
//		// Registering Hibernate4Module to support lazy objects
//		mapper.registerModule(new Hibernate4Module());
//		
//		mapper.setSerializationInclusion(Include.NON_NULL);
//		mapper.setDateFormat(defaultDateFormat);
//		
//		messageConverter.setObjectMapper(mapper);
//		return messageConverter;
//
//	}
}
