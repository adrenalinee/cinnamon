package org.cinnamon.common.mapping;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.cinnamon.common.mapping.vo.ApiCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author 동성
 * @since 2015. 2. 27.
 */
@Component
public class ApiFinder {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	public List<ApiCategory> getCategories() {
		List<ApiCategory> apiCategories = new LinkedList<>();
		
		Map<String, Object> restControllerBeanMap = applicationContext.getBeansWithAnnotation(RestController.class);
		Iterator<String> controllerNameIter =  restControllerBeanMap.keySet().iterator();
		while (controllerNameIter.hasNext()) {
			String controllerName = controllerNameIter.next();
			
			Object controller = restControllerBeanMap.get(controllerName);
			
			ApiCategory apiCategory = new ApiCategory();
			apiCategory.setName(controllerName);
			apiCategories.add(apiCategory);
			
			String parentMappingPath = "";
			RequestMapping requestMapping = controller.getClass().getAnnotation(RequestMapping.class);
			if (requestMapping != null) {
				String[] mappingPathArray = requestMapping.value();
				if (mappingPathArray != null) {
					parentMappingPath = mappingPathArray[0];
					if (!parentMappingPath.endsWith("/")) {
						parentMappingPath += "/";
					}
				}
			}
					
					
//			List<ApiName> apiNameList = new LinkedList<>();
//			
//			Method[] methodArray = controller.getClass().getDeclaredMethods();
//			if (methodArray != null) {
//				for (Method method: methodArray) {
//					RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
//					if (methodRequestMapping == null) {
//						continue;
//					}
//					
//					String mappingPath = "";
//					String[] methodMappingPathArray = methodRequestMapping.value();
//					if (methodMappingPathArray != null) {
//						mappingPath = methodMappingPathArray[0];
//					}
//					
//					String requestMethod = "GET";
//					RequestMethod[] requestMethodArray = methodRequestMapping.method();
//					if (!(requestMethodArray == null || requestMethodArray.length <= 0)) {
//						requestMethod = requestMethodArray[0].name();
//					}
//					
//					String apiNameValue = "";
//					if (mappingPath.startsWith("/")) {
//						apiNameValue = mappingPath;
//					} else {
//						apiNameValue = parentMappingPath + mappingPath;
//					}
//					
//					ApiName apiName = new ApiName();
//					apiName.setName(apiNameValue);
//					apiName.setRequestMethod(requestMethod);
//					
//					apiNameList.add(apiName);
//				}
//				
//				
//				apiCategory.setApiNames(apiNameList);
//			}
			
		}
		
		return apiCategories;
	}
}
