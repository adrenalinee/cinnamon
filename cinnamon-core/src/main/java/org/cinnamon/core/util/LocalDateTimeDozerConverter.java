package org.cinnamon.core.util;

import java.time.LocalDateTime;

import org.dozer.DozerConverter;

/**
 * 
 * @author 신동성
 * @since 2015. 12. 16.
 */
public class LocalDateTimeDozerConverter extends DozerConverter<LocalDateTime, LocalDateTime> {
	
	public LocalDateTimeDozerConverter() {
		super(LocalDateTime.class, LocalDateTime.class);
	}
	
	@Override
	public LocalDateTime convertTo(LocalDateTime source, LocalDateTime destination) {
		return LocalDateTime.from(source);
	}

	@Override
	public LocalDateTime convertFrom(LocalDateTime source, LocalDateTime destination) {
		return LocalDateTime.from(source);
	}

}
