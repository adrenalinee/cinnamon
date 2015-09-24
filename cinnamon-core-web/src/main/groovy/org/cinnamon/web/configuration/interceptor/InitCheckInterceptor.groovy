package org.cinnamon.web.configuration.interceptor

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.cinnamon.core.config.SystemConfigurerManager;
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
	
	@Autowired
	SystemConfigurerManager systemConfigurerManager
	
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
			isInitialize = systemConfigurerManager.isInitialized()
		}
		
		if (!isInitialize) {
			String nextStep = systemConfigurerManager.findNextInitializeStep()
			println nextStep
			if (nextStep == null) {
				return true
			}
			
			logger.info("서버 초기화 진행해야 함. 초기화 마법사 실행")
			
			String nextInitUri = "/configuration/initWizard"
			if (!"".equals(nextStep)) {
				nextInitUri += "/${nextStep}"
			}
			
			if (request.getRequestURI().equalsIgnoreCase(nextInitUri)) {
				return true
			}
			
			response.sendRedirect(nextInitUri)
			return false
		}
		
		return true
	}
}
