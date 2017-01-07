package org.cinnamon.core.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cinnamon.core.config.SystemConfigureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private SystemConfigureService systemConfigurerManager;
	
	/**
	 * 서버 초기화 되어 있는지 여부
	 */
	private boolean isInitialize;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		
		logger.info("start {}", request.getRequestURI());
		
		if (!isInitialize) {
			isInitialize = systemConfigurerManager.isInitialized();
		}
		
		if (isInitialize) {
			if (request.getRequestURI().startsWith("/configuration/initWizard")) {
				response.sendRedirect("/");
				return false;
			}
		} else {
			String nextStep = systemConfigurerManager.findNextInitializeStep();
			if (nextStep == null) {
				return true;
			}
			
			logger.info("서버 초기화 진행해야 함. 초기화 마법사 실행");
			
			String nextInitUri = "/initWizard";
			if (!"".equals(nextStep)) {
				nextInitUri += "/${nextStep}";
			}
			
			if (request.getRequestURI().equalsIgnoreCase(nextInitUri)) {
				return true;
			}
			
			response.sendRedirect(nextInitUri);
			return false;
		}
		
		return true;
	}
}
