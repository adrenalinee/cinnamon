package org.cinnamon.core.util;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 
 * create date: 2015. 6. 17.
 * @author 신동성
 *
 */
public class ObjectMerger {
	
//	private static Map<String, Void> availableTypeMap = new LinkedHashMap<String, Void>();
//	
//	static {
//		availableTypeMap.put(String.class.getName(), null);
//		availableTypeMap.put(Float.class.getName(), null);
//		availableTypeMap.put(Date.class.getName(), null);
//		availableTypeMap.put(Integer.class.getName(), null);
//		availableTypeMap.put(Boolean.class.getName(), null);
//	}
	
	
//	public static void merge(Object source, Object target) throws Exception {
//		copyFields(source.getClass().getDeclaredFields(), source, target);
////		
////		Class<?> superClass = source.getClass().getSuperclass();
////		while (superClass != null) {
////			copyFields(superClass.getDeclaredFields(), source, target);
////			
////			superClass = superClass.getSuperclass();
////		}
//		
//		
//		
//		
//	}
//	
//	private static void copyFields(Field[] fields, Object source, Object target) throws Exception {
//		for (Field field: fields) {
//			field.setAccessible(true);
//			Object fieldValue = field.get(source);
//			if (fieldValue == null) {
//				continue;
//			}
//			
//			if (!isAvailableType(fieldValue)) {
//				continue;
//			}
//			
//			
////			System.out.println(field.getName() + ": " + fieldValue);
//			
//			try {
//				Field targetField = target.getClass().getDeclaredField(field.getName());
//				if (targetField != null) {
//					if (!targetField.isAccessible()) {
//						targetField.setAccessible(true);
//					}
//					
//					System.out.println("copy field: " + field.getName());
//					targetField.set(target, fieldValue);
//					return;
//				}
//			} catch (NoSuchFieldException e) {
////				e.printStackTrace();
//				continue;
//			}
//			
//			Class<?> superClass = target.getClass().getSuperclass();
//			while (superClass != null) {
//				copyFields(fields, source, target);
//				
//				superClass = superClass.getSuperclass();
//			}
//			
//			
//			
////			String methodName = field.getName();
////			methodName = "set" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
////			
////			try {
////				Method method = target.getClass().getMethod(methodName, field.getType());
////				if (method != null) {
////					method.invoke(target, field.get(source));
////				}
////			} catch (Exception e) {
//////				e.printStackTrace();
////				continue;
////			}
//		};
//	}
	
	private static boolean isAvailableType(Object value) {
		if (value.getClass().isPrimitive()) {
			return true;
		}
		if (value instanceof Number) {
			return true;
		}
		if (value instanceof CharSequence) {
			return true;
		}
		if (value instanceof Enum) {
			return true;
		}
		if (value instanceof Date) {
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * source객체의 필드에 값이 있는 부분만 target 객체로 값 셋팅
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public static void merge(Object source, Object target) throws Exception {
		exploreSourceFeild(source, target, source.getClass());
		
		Class<?> superClass = source.getClass().getSuperclass();
		while (superClass != null) {
			exploreSourceFeild(source, target, superClass);
			
			superClass = superClass.getSuperclass();
		}
	}
	
	private static void exploreSourceFeild(Object source, Object target, Class<?> sourceCalss) throws Exception {
		for (Field field: sourceCalss.getDeclaredFields()) {
			field.setAccessible(true);
			Object fieldValue = field.get(source);
			if (fieldValue == null) {
				continue;
			}
			
			if (!isAvailableType(fieldValue)) {
				continue;
			}
			
			target.getClass();
			applyField(source, target, target.getClass(), field.getName(), fieldValue);
			
			Class<?> superClass = target.getClass().getSuperclass();
			while (superClass != null) {
				applyField(source, target, superClass, field.getName(), fieldValue);
				
				superClass = superClass.getSuperclass();
			}
		}
	}
	
	
	private static void applyField(Object source, Object target, Class<?> targetCalss, String fieldName, Object fieldValue) throws Exception {
		try {
			Field targetField = targetCalss.getDeclaredField(fieldName);
			if (targetField != null) {
				if (!targetField.isAccessible()) {
					targetField.setAccessible(true);
				}
				
				System.out.println("copy field: " + fieldName);
				targetField.set(target, fieldValue);
				return;
			}
		} catch (NoSuchFieldException e) {
			return;
		}
	}
	
}

class A {
	String s;
	Float f;
}
