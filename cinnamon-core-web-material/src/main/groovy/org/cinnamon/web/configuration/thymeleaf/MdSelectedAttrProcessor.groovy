package org.cinnamon.web.configuration.thymeleaf

import org.thymeleaf.Arguments
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Attribute;
import org.thymeleaf.dom.Element
import org.thymeleaf.processor.attr.AbstractAttributeModifierAttrProcessor.ModificationType

import org.thymeleaf.spring4.requestdata.RequestDataValueProcessorUtils
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.standard.processor.attr.AbstractStandardSingleAttributeModifierAttrProcessor



/**
 * 
 * @author 신동성
 * @since 2016. 12. 5.
 */
class MdSelectedAttrProcessor extends AbstractStandardSingleAttributeModifierAttrProcessor {
	
	MdSelectedAttrProcessor() {
		super("md-selected")
	}
	

	@Override
	public int getPrecedence() {
		return 10001;
	}
	
	protected String getTargetAttributeName(
		final Arguments arguments, final Element element,
		final String attributeName) {
		
		return "md-selected"
	}
	
	@Override
	protected String getTargetAttributeValue(
			final Arguments arguments, final Element element, final String attributeName) {

		final String attributeValue = element.getAttributeValue(attributeName)
		
		String pre = ""
		String ex = attributeValue
		String sub = ""
		int index = attributeValue.indexOf('${')
		if (index > -1) {
			int subIndex = attributeValue.indexOf('}')
			if (subIndex > -1 && subIndex > index) {
				pre = attributeValue.substring(0, index)
				ex = attributeValue.substring(index, subIndex + 1)
				sub = attributeValue.substring(subIndex + 1, attributeValue.size())
			}
		}
		

		final Configuration configuration = arguments.getConfiguration()
		final IStandardExpressionParser expressionParser = StandardExpressions.getExpressionParser(configuration)
		
		final IStandardExpression expression = expressionParser.parseExpression(configuration, arguments, ex)
		
		final Object result = expression.execute(configuration, arguments)
		return (result == null ? "" : pre + result.toString() + sub)
	}

	
	@Override
	protected ModificationType getModificationType(
			final Arguments arguments, final Element element, final String attributeName, final String newAttributeName) {
		return ModificationType.SUBSTITUTION;
	}



	@Override
	protected boolean removeAttributeIfEmpty(
			final Arguments arguments, final Element element, final String attributeName, final String newAttributeName) {
		return false;
	}

}
