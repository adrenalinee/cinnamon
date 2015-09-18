package org.cinnamon.core.config;

import static org.cinnamon.core.config.baseData.BaseDataBuilder.menu;
import static org.cinnamon.core.config.baseData.BaseDataBuilder.menuGroup;
import static org.cinnamon.core.config.baseData.BaseDataBuilder.role;
import static org.cinnamon.core.config.baseData.BaseDataBuilder.site;
import static org.cinnamon.core.config.baseData.BaseDataBuilder.userGroup;

import org.cinnamon.core.config.baseData.BaseDataBuilder;
import org.cinnamon.core.enumeration.DefinedUserAuthority;
import org.springframework.stereotype.Component;

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
		addRoles(baseData);
		addSites(baseData);
	}
	
	private void addRoles(BaseDataBuilder baseData) {
		baseData.addRoles(
			role("시스템 최고 운영 권한", DefinedUserAuthority.systemMaster).addUserGroup(
				userGroup("시스템 최고 운영자 모임").setDefault()
			),
			role("기본 권한", DefinedUserAuthority.normal).addUserGroup(
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
				).addMenusAtHeaderLeft(
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
