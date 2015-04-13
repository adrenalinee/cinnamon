package org.cinnamon.common.mapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.cinnamon.common.mapping.vo.ApiCategory;
import org.cinnamon.common.mapping.vo.ApiDefinition;
import org.cinnamon.common.mapping.vo.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author 동성
 * @since 2015. 2. 27.
 */
@Component
public class ApiFinder {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private ApplicationContext applicationContext;
	
	
	/**
	 * 
	 * @return
	 */
	public List<ApiCategory> getCategories() {
		logger.info("start");
		
		List<ApiCategory> apiCategories = new LinkedList<>();
		
		Map<String, Object> restControllerBeanMap = applicationContext.getBeansWithAnnotation(RestController.class);
		Iterator<String> controllerNameIter =  restControllerBeanMap.keySet().iterator();
		while (controllerNameIter.hasNext()) {
			String controllerName = controllerNameIter.next();
			Object controller = restControllerBeanMap.get(controllerName);
			
			if (controllerName.endsWith("Controller")) {
				controllerName = controllerName.substring(0, controllerName.lastIndexOf("Controller"));
			}
			
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
					
					
			List<ApiDefinition> apiDefinetions = new LinkedList<>();
			
			Method[] methodArray = controller.getClass().getDeclaredMethods();
			if (methodArray != null) {
				for (Method method: methodArray) {
					RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
					if (methodRequestMapping == null) {
						continue;
					}
					
					String mappingPath = "";
					String[] methodMappingPathArray = methodRequestMapping.value();
					if (methodMappingPathArray != null) {
						mappingPath = methodMappingPathArray[0];
					}
					
					String requestMethod = "GET";
					RequestMethod[] requestMethodArray = methodRequestMapping.method();
					if (!(requestMethodArray == null || requestMethodArray.length <= 0)) {
						requestMethod = requestMethodArray[0].name();
					}
					
					String apiNameValue = "";
					if (mappingPath.startsWith("/")) {
						apiNameValue = mappingPath;
					} else {
						apiNameValue = parentMappingPath + mappingPath;
					}
					
					ApiDefinition apiName = new ApiDefinition();
					apiName.setUrl(apiNameValue);
					apiName.setMethod(requestMethod);
					
					apiDefinetions.add(apiName);
				}
				
				
				apiCategory.setApiDefs(apiDefinetions);
			}
			
		}
		
		return apiCategories;
	}
	
	
	
	public ApiDefinition getDefinition(String category, String apiName, String requestMethod) {
		logger.info("start");
		
		if (!category.contains("Controller")) {
			category += "Controller";
		}
		
		Object controller = applicationContext.getBean(category);
		if (controller == null) {
			//TODO 없는 컨트롤러 입니다.
			
			return null;
		}
		
		String parentMappingPath = "";
		
		RequestMapping requestMapping = controller.getClass().getAnnotation(RequestMapping.class);
		if (requestMapping != null) {
			String[] mappingPathArray = requestMapping.value();
			if (mappingPathArray != null) {
				parentMappingPath = mappingPathArray[0];
			}
		}
		
		Method[] methodArray = controller.getClass().getDeclaredMethods();
		if (methodArray == null) {
			return null;
		}
		
		
		ApiDefinition apiDefinition = new ApiDefinition();
		
		
		String apiMappingPath = apiName;
		if (!"".equals(parentMappingPath)) {
			apiMappingPath = apiName.replace(parentMappingPath, "");
		}
		
		String methodMappingPath;
		for (Method method: methodArray) {
			RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
			if (methodRequestMapping == null) {
				continue;
			}
			
			String[] methodMappingPathArray = methodRequestMapping.value();
			if (methodMappingPathArray == null) {
				methodMappingPath = "";
			}
			
			methodMappingPath = methodMappingPathArray[0];
			if (methodMappingPath.startsWith("/")) {
				if (!methodMappingPath.equals(apiMappingPath)) {
					continue;
				}
			} else if (apiMappingPath.startsWith("/")) {
				if (!methodMappingPath.equals(apiMappingPath.substring(1))) {
					continue;
				}
			} else {
				System.out.println("error error");
			}
			
			String requestBody = null;
			try {
				requestBody = createRequestBody(method);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
			apiDefinition.setRequestBody(requestBody);
			
			
			String methodRequestMethod = "GET";
			RequestMethod[] requestMethodArray = methodRequestMapping.method();
			if (!(requestMethodArray == null || requestMethodArray.length <= 0)) {
				methodRequestMethod = requestMethodArray[0].name();
			}
			
			if (!methodRequestMethod.equals(requestMethod)) {
				continue;
			}
			
			apiDefinition.setMethod(methodRequestMethod);
			apiDefinition.setUrl(apiName);
			
			
//			int searchStartIndex = 0;
			List<NameValuePair> urlParameters =  new LinkedList<>();
			int paramStartIndex = apiName.indexOf("{");
			while (paramStartIndex > -1) {
				int paramEndIndex = apiName.indexOf("}", paramStartIndex);
				
				if (paramEndIndex <= -1) {
					break;
				}
				
				
				String urlParamName = apiName.substring(paramStartIndex + 1, paramEndIndex);
				paramStartIndex = apiName.indexOf("{", paramEndIndex);
				
				if ("".equals(urlParamName)) {
					continue;
				}
				
				NameValuePair nameValuePair = new NameValuePair();
				nameValuePair.setName(urlParamName);
				
				urlParameters.add(nameValuePair);
			}
			
			if (! urlParameters.isEmpty()) {
				apiDefinition.setUrlParameters(urlParameters);
			}
		}
		
		
		return apiDefinition;
	}
	
	
	
	private String createRequestBody(Method method) throws Exception {
		
		Annotation[][] parametersAnnotationArray = method.getParameterAnnotations();
		if (parametersAnnotationArray == null) {
			return null;
		}
		
		
		
		for (int i = 0, limit =parametersAnnotationArray.length; i < limit; i++) {
			Annotation[] paramAnnoArray = parametersAnnotationArray[i];
			for (Annotation paramAnno: paramAnnoArray) {
				if (paramAnno instanceof RequestBody) {
					
					Class<?> requestBodyObjectClass = method.getParameterTypes()[i];
					Object requestBodyObject = createMockObject(requestBodyObjectClass);
					
					ObjectMapper objectMapper = new ObjectMapper();
					return objectMapper.writeValueAsString(requestBodyObject);
				}
			}
		}
		
		
		
		return null;
	}
	
	
	private Object createMockObject(Class<?> objectClass) throws Exception {
		logger.info("start");
		
		if (isPrimitiveType(objectClass)) {
			return null;
		}
		
		Object object = objectClass.newInstance();
		
		Field[] fieldArray = object.getClass().getDeclaredFields();
		if (fieldArray == null) {
			return object;
		}
		
		for (Field field: fieldArray) {
//			System.out.println(field.getName());
			if (isPrimitiveType(field.getType())) {
				continue;
			} else if (isListType(field.getType())) {
				
				Class<?> fieldObjectClass = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
				
				Object mockObject = createMockObject(fieldObjectClass);
				
				List<Object> listField = new LinkedList<>();
				listField.add(mockObject);
				field.setAccessible(true);
				try {
					field.set(object, listField);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
//					Object fieldObject = field.getType().newInstance();
					
					Class<?> fieldObjectClass = field.getType();
					Object mockObject = createMockObject(fieldObjectClass);
					
					field.setAccessible(true);
					field.set(object, mockObject);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return object;
	}
	
	
	private boolean isListType(Class<?> type) {
		System.out.println(type);
		
		if (type.equals(List.class)) {
			return true;
		}
		
		return false;
	}


	private boolean isPrimitiveType(Class<?> type) {
		if (type.isPrimitive()) {
			return true;
		}
		
		if (type.equals(String.class)) {
			return true;
		}
		
		if (type.equals(Integer.class)) {
			return true;
		}
		if (type.equals(Long.class)) {
			return true;
		}
		if (type.equals(Float.class)) {
			return true;
		}
		if (type.equals(Boolean.class)) {
			return true;
		}
		
		
		return false;
	}
}
