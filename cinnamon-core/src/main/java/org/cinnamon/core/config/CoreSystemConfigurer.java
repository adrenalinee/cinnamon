package org.cinnamon.core.config;

import static org.cinnamon.core.config.builder.BaseDataBuilder.group;
import static org.cinnamon.core.config.builder.BaseDataBuilder.menu;
import static org.cinnamon.core.config.builder.BaseDataBuilder.menuGroup;
import static org.cinnamon.core.config.builder.BaseDataBuilder.role;
import static org.cinnamon.core.config.builder.BaseDataBuilder.site;
import static org.cinnamon.core.config.builder.BaseDataBuilder.userGroup;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.cinnamon.core.config.builder.BaseDataBuilder;
import org.cinnamon.core.config.builder.GroupWrapper;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.domain.enumeration.MenuType;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.enumeration.DefinedUserAuthority;
import org.cinnamon.core.enumeration.DefinedGroups;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 
 *
 * created date: 2015. 9. 17.
 * @author 신동성
 */
@Component
public class CoreSystemConfigurer implements SystemConfigurer {

	@Override
	public void configure(BaseDataBuilder baseData) {
		addGroups(baseData);
		addAuthority(baseData);
		addSites(baseData);
	}
	
	private void addGroups(BaseDataBuilder baseData) {
		baseData.addGroups(
			group("사용 상태", DefinedGroups.useStatus).addChildGroup(
				group("사용중", UseStatus.enable),
				group("미사용중", UseStatus.disable)
			),
			group("메뉴 위치", DefinedGroups.menuPositions).addChildGroup(
				group("사이드바", MenuPosition.sidebar),
				group("해더 왼쪽", MenuPosition.headerLeft),
				group("해더 오른쪽", MenuPosition.headerRight)
			),
			group("메뉴 종류", DefinedGroups.menuTypes).addChildGroup(
				group("구분자", MenuType.separater),
				group("라벨", MenuType.label),
				group("컴포넌트", MenuType.component),
				group("일반메뉴", MenuType.normal)
			),
			group("사용자 권한", DefinedGroups.userAuthority).addChildGroup(
				group("일반 사용자 권한", DefinedUserAuthority.user),
				group("시스템 최고 운영자 권한", DefinedUserAuthority.systemMaster)
			)
		);
		
		GroupWrapper nationGroupWrapper = group("국가", DefinedGroups.nations);
		Map<String, Locale> countries = new TreeMap<>();
		for (Locale locale: Locale.getAvailableLocales()) {
			countries.put(locale.getCountry(), locale);
		}
		
		for (Entry<String, Locale> e: countries.entrySet()) {
			Locale l = e.getValue();
			if (StringUtils.isEmpty(l.getCountry())) {
				continue;
			}
			
			nationGroupWrapper.addChildGroup(group(l.getDisplayCountry(), l.getCountry()));
		}
		
		nationGroupWrapper.addChildGroup(group("인터내셔날", "INTERNATIONAL"));
	}
	
	private void addAuthority(BaseDataBuilder baseData) {
		baseData.addAuthorities(
			role("시스템 최고 운영 권한", DefinedUserAuthority.systemMaster).addUserGroup(
				userGroup("시스템 최고 운영자 모임").setDefault()
			),
			role("기본 권한", DefinedUserAuthority.user).addUserGroup(
				userGroup("기본 사용자 모임").setDefault()
			)
		);
	}
	
	private void addSites(BaseDataBuilder baseData) {
		baseData.addSite(
			site("콘솔", "console")
			.indexPage("/configuration")
			.addMenuGroup(
				menuGroup("기본 설정 메뉴", "configuration").addMenusAtSidebar(
					menu("사용자")
						.uri("/configuration/users")
						.iconClass("glyphicon glyphicon-user"),
					menu("사용자 모임")
						.uri("/configuration/userGroups")
						.iconClass("fa fa-users"),
					menu("사이트")
						.uri("/configuration/sites")
						.iconClass("fa fa-sitemap"),
					menu("역할 및 권한")
						.uri("/configuration/roles")
						.iconClass("fa fa-shield"),
					menu("이메일")
						.iconClass("fa fa-envelope")
						.addChilds(
							menu("메일 서버")
								.uri("/configuration/email/servers")
								.iconClass("fa fa-server")
						).addChilds(
							menu("메일 템플릿")
								.uri("/configuration/email/templates")
								.iconClass("fa fa-envelope-o")
						),
					menu("코드")
						.uri("/configuration/groups")
						.iconClass("fa fa-folder"),
					menu("첨부파일")
						.uri("/configuration/files")
						.iconClass("fa fa-upload")
				).addMenusAtHeaderRight(
					menu("설정")
						.iconClass("fa fa-gear")
						.addChilds(
							menu("기본 설정")
								.uri("/configuration")
								.iconClass("fa fa-cogs")
						)
				)
			)
		);
	}
}
