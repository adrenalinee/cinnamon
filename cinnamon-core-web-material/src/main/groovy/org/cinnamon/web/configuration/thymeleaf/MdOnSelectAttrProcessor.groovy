package org.cinnamon.web.configuration.thymeleaf

import org.thymeleaf.Arguments
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Attribute;
import org.thymeleaf.dom.Element
import org.thymeleaf.processor.attr.AbstractAttributeModifierAttrProcessor.ModificationType
import org.thymeleaf.spring4.processor.attr.AbstractSpringFieldAttrProcessor;
import org.thymeleaf.spring4.requestdata.RequestDataValueProcessorUtils
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.standard.processor.attr.AbstractStandardSingleAttributeModifierAttrProcessor

/**
 * 
 * thyemleaf 에서 넘어간 el값과 anguar의 attribute directive를 같이 사용하기 위해 만듬
 * 예를 들어서
 * <div ng:ng-click="selectMenu('${menu.uri}')"> </div>
 * 위코드를 변환하면 아래와 같이 출력된다.
 * <div ng-click="selectMenu('/configuration/userGroups')"> </div>
 * 
 * 
 * @author 신동성
 * @since 2016. 5. 19.
 */
class MdOnSelectAttrProcessor extends AbstractStandardSingleAttributeModifierAttrProcessor {

	MdOnSelectAttrProcessor() {
		super("md-on-select")
	}

	@Override
	public int getPrecedence() {
		return 10001;
	}
	
	protected String getTargetAttributeName(
		final Arguments arguments, final Element element,
		final String attributeName) {
		
		return "md-on-select"
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
