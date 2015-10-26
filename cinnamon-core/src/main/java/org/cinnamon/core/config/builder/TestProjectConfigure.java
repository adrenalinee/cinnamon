package org.cinnamon.core.config.builder;

import static org.cinnamon.core.config.builder.BaseDataBuilder.menu;
import static org.cinnamon.core.config.builder.BaseDataBuilder.menuGroup;
import static org.cinnamon.core.config.builder.BaseDataBuilder.role;
import static org.cinnamon.core.config.builder.BaseDataBuilder.site;

import org.cinnamon.core.enumeration.DefinedUserAuthority;


public class TestProjectConfigure {
	
	
	public static void main() {
		BaseDataBuilder projectBuilder = new BaseDataBuilder(null);
		
		projectBuilder.addSite(
			site("콘솔", "console").addMenuGroup(
				menuGroup("기본 설정 메뉴", "configuration").addMenusAtSidebar(
					menu("사용자")
						.uri("/configuration/users")
						.iconClass("glyphicon glyphicon-user"),
//						.addGrantedAuthorities(DefinedUserAuthority.systemMaster),
					menu("사용자 모임")
						.uri("/configuration/userGroups")
						.iconClass("fa fa-users"),
//						.addGrantedAuthorities(DefinedUserAuthority.systemMaster),
					menu("사이트")
						.uri("/configuration/sites")
						.iconClass("fa fa-sitemap"),
//						.addGrantedAuthorities(DefinedUserAuthority.systemMaster),
					menu("역할 및 권한")
						.uri("/configuration/permissions")
						.iconClass("fa fa-shield"),
//						.addGrantedAuthorities(DefinedUserAuthority.systemMaster),
					menu("이메일")
						.iconClass("fa fa-envelope")
						.addChilds(
							menu("메일 서버")
								.uri("/configuration/emailServers")
								.iconClass("fa fa-server")
//								.addGrantedAuthorities(DefinedUserAuthority.systemMaster)
						).addChilds(
							menu("메일 템플릿")
								.uri("/configuration/emailTemplates")
								.iconClass("fa fa-envelope-o")
//								.addGrantedAuthorities(DefinedUserAuthority.systemMaster)
						),//.addGrantedAuthorities(DefinedUserAuthority.systemMaster),
					menu("코드")
						.uri("/configuration/groups")
						.iconClass("fa fa-folder"),
//						.addGrantedAuthorities(DefinedUserAuthority.systemMaster),
					menu("첨부파일")
						.uri("/configuration/files")
						.iconClass("fa fa-upload")
//						.addGrantedAuthorities(DefinedUserAuthority.systemMaster)
				).addMenusAtHeaderLeft(
					menu("설정")
						.iconClass("fa fa-gear")
						.addChilds(
							menu("기본 설정")
								.uri("/configuration")
								.iconClass("fa fa-cogs")
//								.addGrantedAuthorities(DefinedUserAuthority.systemMaster)
						)//.addGrantedAuthorities(DefinedUserAuthority.systemMaster)
				)
			)
		).addAuthorities(
			role("시스템 최고 운영자", DefinedUserAuthority.systemMaster),
			role("기본 사용자", DefinedUserAuthority.user)
		);
		
		
		projectBuilder.print();
//		projectBuilder.build();
	}
}
