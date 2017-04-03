package org.cinnamon.core.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cinnamon.core.config.SystemConfigureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * created at: 2015. 9. 16.
 * @author shin dongseong
 *
 */
@Component
public class InitCheckInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
//	@Autowired
//	private PropertyRepository propertyRepository;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private SystemConfigureService systemConfigurerManager;
	
	/**
	 * 서버 초기화 되어 있는지 여부
	 */
	private boolean isInitialize;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		
		logger.info("start");
		logger.info(request.getRequestURI());
		
		String[] activeProfiles = environment.getActiveProfiles();
		if (activeProfiles != null ) {
			if (activeProfiles.length > 0) {
				StringBuffer activeProfilesSb = new StringBuffer();
				for (String activeProfile: activeProfiles) {
					if (activeProfile.length() <= 0) {
						continue;
					}
					
					activeProfilesSb.append(activeProfile);
					activeProfilesSb.append(",");
				}
				
				activeProfilesSb.deleteCharAt(activeProfilesSb.length() - 1);
				
				request.setAttribute("activeProfiles", activeProfilesSb.toString());
			}
		}
		
		
		if (!isInitialize) {
			isInitialize = systemConfigurerManager.isInitialized();
		}
		
		if (!isInitialize) {
			String nextStep = systemConfigurerManager.findNextInitializeStep();
			if (nextStep == null) {
				return true;
			}
			
			logger.info("서버 초기화 진행해야 함. 초기화 마법사 실행");
			
			String nextInitUri = "/core/initWizard";
			if (!"".equals(nextStep)) {
				nextInitUri += "/${nextStep}";
			}
			
			if (request.getRequestURI().equalsIgnoreCase(nextInitUri)) {
				return true;
			}
			
			response.sendRedirect(nextInitUri);
			return false;
		}
		
		if (isInitialize) {
			if (request.getRequestURI().startsWith("/core/initWizard")) {
				response.sendRedirect("/");
				return false;
			}
		}
		
		return true;
	}
}
