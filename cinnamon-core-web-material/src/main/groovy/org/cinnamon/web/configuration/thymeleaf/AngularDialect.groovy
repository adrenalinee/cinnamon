package org.cinnamon.web.configuration.thymeleaf

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractDialect
import org.thymeleaf.processor.IProcessor

/**
 * 
 * @author 신동성
 * @since 2016. 5. 17.
 */
@Component
class AngularDialect extends AbstractDialect {
	
	AngularDialect() {
		super()
	}
	
	@Override
	String getPrefix() {
		return "ng"
	}
	
	@Override
	Set<IProcessor> getProcessors() {
		final Set<IProcessor> processors = new HashSet<IProcessor>()
		processors.add(new NgClickToAttrProcessor())
		
		return processors
	}
}
