package org.cinnamon.web.configuration.interceptor

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.cinnamon.core.domain.Property
import org.cinnamon.core.enumeration.DefinedDBProperty
import org.cinnamon.core.repository.PropertyRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.dao.InvalidDataAccessResourceUsageException
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

/**
 * 
 *
 * created date: 2015. 9. 16.
 * @author 신동성
 */
@Component
class InitCheckInterceptor extends HandlerInterceptorAdapter {
	Logger logger = LoggerFactory.getLogger(getClass())
	
	@Autowired
	PropertyRepository propertyRepository
	
	@Autowired
	Environment environment
	
	/**
	 * 서버 초기화 되어 있는지 여부
	 */
	boolean isInitialize
	
	
	@Override
	boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		
		logger.info("start")
		logger.info(request.getRequestURI())
		
		String[] activeProfiles = environment.getActiveProfiles()
		if (activeProfiles != null ) {
			if (activeProfiles.length > 0) {
				StringBuffer activeProfilesSb = new StringBuffer()
				for (String activeProfile: activeProfiles) {
					if (activeProfile.length() <= 0) {
						continue
					}
					
					activeProfilesSb.append(activeProfile)
					activeProfilesSb.append(",")
				}
				
				activeProfilesSb.deleteCharAt(activeProfilesSb.length() - 1)
				
				request.setAttribute("activeProfiles", activeProfilesSb.toString())
			}
		}
		
		
		if (!isInitialize) {
			try {
				Property initProperty = propertyRepository.findByName(DefinedDBProperty.initialize.name())
//				System.out.println(ToStringBuilder.reflectionToString(initProperty))
				
				
				if (initProperty != null) {
					String value = initProperty.getValue()
					if (!StringUtils.isEmpty(value)) {
						if ("true".equals(value.trim().toLowerCase())) {
							isInitialize = true
						}
					}
				}
			} catch (InvalidDataAccessResourceUsageException e) {
				logger.warn("데이터베이스 스키마 생성이 되지 않았습니다.", e)
				logger.info("서버 초기화 안되어 있음. 초기화 마법사 실행")
				response.sendRedirect("/initWizard")
				return false
			}
		}
		
		if (isInitialize) {
//			System.out.println("초기화 되어 있음")
			return true
		}
		
//		String host = request.getRequestURL().toString().replace(request.getRequestURI(), "")
		
//		System.out.println(host)
//		System.out.println(request.getRequestURI())
		
		logger.info("서버 초기화 안되어 있음. 초기화 마법사 실행")
		
		response.sendRedirect("/configuration/initWizard")
		
		return false
	}
}
