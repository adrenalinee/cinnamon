package org.cinnamon.core.config;

import static org.cinnamon.core.config.builder.BaseDataBuilder.group;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.cinnamon.core.config.builder.BaseDataBuilder;
import org.cinnamon.core.config.builder.GroupWrapper;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.enumeration.DefinedGroups;
import org.springframework.util.StringUtils;

/**
 * 
 *
 * created date: 2015. 9. 17.
 * @author 신동성
 */
//@Component
public class CoreGroupSystemConfigurer implements SystemConfigurer {

	@Override
	public void configure(BaseDataBuilder baseData) {
		addNationGroups(baseData);
		baseData.addGroups(
			group("사용상태", DefinedGroups.useStatus).addChildGroup(
				group("사용중", UseStatus.enable),
				group("미사용중", UseStatus.disable)
			)
		).addGroups(
			group("메뉴 위치", DefinedGroups.menuPositions).addChildGroup(
					group("사이드바", MenuPosition.sidebar),
					group("해더 왼쪽", MenuPosition.headerLeft),
					group("해더 오른쪽", MenuPosition.headerRight)
				)
		);
	}

	private void addNationGroups(BaseDataBuilder baseData) {
		GroupWrapper nations = group("국가", DefinedGroups.nations);
		nations.addChildGroup(group("통합국가", "INTERNATIONAL"));
		
		Map<String, Locale> countries = new TreeMap<>();
		for (Locale locale: Locale.getAvailableLocales()) {
			countries.put(locale.getCountry(), locale);
		}
		
		for (Entry<String, Locale> e: countries.entrySet()) {
			Locale locale = e.getValue();
			
			if (StringUtils.isEmpty(locale.getCountry())) {
				continue;
			}
			
			nations.addChildGroup(group(locale.getDisplayCountry(), locale.getCountry()));
		}
		
		baseData.addGroups(nations);
	}

}
