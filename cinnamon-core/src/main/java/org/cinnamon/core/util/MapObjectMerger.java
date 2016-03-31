package org.cinnamon.core.util;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 
 * @author 신동성
 * @since 2015. 10. 28.
 */
public class MapObjectMerger {
	
	static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	
	public static void copy(Map<String, ?> source,  Object target) throws Exception {
//		DateTimeFormat.forPattern("yyyyMMdd")
		apply(source, target);
	}
	
	@SuppressWarnings("unchecked")
	private static void apply(Map<String, ?> source,  Object target) throws Exception {
		Class<?> targetClass = target.getClass();
		for (Entry<String, ?> entry: source.entrySet()) {
			String fieldName = entry.getKey();
			
			if (entry.getValue() instanceof Map) {
				apply((Map<String, ?>) entry.getValue(), target);
			}
			
			Field field = getField(fieldName, targetClass);
			if (field == null) {
				continue;
			}
			
			field.setAccessible(true);
//			field.set(target, entry.getValue());
//			field.set(target, field.getType().cast(entry.getValue()));
			
			setField(entry.getValue(), target, field);
		}
	}
	
	private static Field getField(String fieldName, Class<?> targetClass) throws SecurityException {
		System.out.println(targetClass);
		Arrays.asList(targetClass.getDeclaredFields()).forEach(f -> {
			System.out.println(f.getName());
		});
		
		try {
			return targetClass.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			if (!targetClass.getSuperclass().equals(Object.class)) {
				return getField(fieldName, targetClass.getSuperclass());
			}
			
			return null;
		}
	}
	
	private static void setField(Object value, Object target, Field targetField) throws InstantiationException, IllegalAccessException {
		if (value == null) {
			targetField.set(target, null);
			return;
		}
		
		Class<?> targetFieldType = targetField.getType();
		
		if (targetFieldType.equals(Float.class)) {
			targetField.set(target, Float.valueOf(value.toString()));
		} else if (targetFieldType.equals(Double.class)) {
			targetField.set(target, Double.valueOf(value.toString()));
		} else if (targetFieldType.equals(Integer.class)) {
			targetField.set(target, Integer.valueOf(value.toString()));
		} else if (targetFieldType.equals(Long.class)) {
			targetField.set(target, Long.valueOf(value.toString()));
		} else if (targetFieldType.equals(Short.class)) {
			targetField.set(target, Short.valueOf(value.toString()));
		} else if (targetFieldType.equals(Byte.class)) {
			targetField.set(target, Byte.valueOf(value.toString()));
		} else if (targetFieldType.equals(Boolean.class)) {
			targetField.set(target, Boolean.valueOf(value.toString()));
		} else if (targetFieldType.equals(Date.class)) {
			targetField.set(target, LocalDateTime.parse(value.toString(), dateTimeFormatter));
		} else {
			targetField.set(target, value);
		}
		
		
	}
}
