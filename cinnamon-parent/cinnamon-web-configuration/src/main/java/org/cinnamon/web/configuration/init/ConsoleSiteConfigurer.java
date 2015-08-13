package org.cinnamon.web.configuration.init;

import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.enumeration.DefinedUserAuthority;
import org.cinnamon.core.init.SiteConfigurer;
import org.cinnamon.core.init.builder.SiteBuilder;

/**
 * 
 * create date: 2015. 5. 26.
 * @author 신동성
 *
 */
public class ConsoleSiteConfigurer implements SiteConfigurer {
	
	@Override
	public void configure(SiteBuilder siteBuilder) {
		siteBuilder.role(DefinedUserAuthority.systemMaster.name())
			.name("시스템 최고 운영 권한")
			.userGroup("시스템 최고 운영자", true)
		.role(DefinedUserAuthority.normal.name())
			.name("기본 권한")
			.userGroup("일반 사용자", true);
		
		
		//configuration 메뉴
		
		siteBuilder.site("console")
		.name("콘솔")
			.menuGroup("configuration", MenuPosition.sidebar)
			.role(DefinedUserAuthority.systemMaster.name())
			.permitElse()
			.name("기본 설정 메뉴")
				.menu("사용자", "/configuration/users")
				.iconClass("glyphicon glyphicon-user")
			.and()
				.menu("사용자 모임", "/configuration/userGroups")
				.iconClass("fa fa-users")
			.and()
				.menu("사이트", "/configuration/sites")
				.iconClass("fa fa-sitemap")
			.and()
				.menu("역할 및 권한", "/configuration/permissions")
				.iconClass("fa fa-shield")
			.and()
				.menu("이메일")
				.iconClass("fa fa-envelope")
					.child("서버", "/configuration/emailServers")
					.iconClass("fa fa-server")
				.parent()
					.child("템플릿", "/configuration/emailTemplates")
					.iconClass("fa fa-envelope-o")
			.and()
				.menu("코드", "/configuration/groups")
				.iconClass("fa fa-folder")
			.and()
				.menu("첨부파일", "/configuration/files")
				.iconClass("fa fa-upload");
		
		
		siteBuilder.site("console")
			.menuGroup("configuration", MenuPosition.headerLeft)
			.role(DefinedUserAuthority.systemMaster.name())
			.permitElse()
				.menu("메뉴")
				.iconClass("fa fa-bars");
		
		
		siteBuilder.site("console")
		.menuGroup("configuration", MenuPosition.headerRight)
		.role(DefinedUserAuthority.systemMaster.name())
		.permitElse()
			.menu("설정")
			.iconClass("fa fa-gear")
				.child("기본 설정", "/configuration")
				.iconClass("fa fa-cogs");
		
		
		//main 메뉴
		
		siteBuilder.site("console")
		.menuGroup("main", MenuPosition.sidebar)
		.name("메인메뉴")
		.role(DefinedUserAuthority.systemMaster.name())
		.permitElse()
		.role(DefinedUserAuthority.normal.name())
		.permitElse()
			.menu("현황판", "/dashboard")
			.iconClass("fa fa-dashboard");
		
		
		siteBuilder.site("console")
		.menuGroup("main", MenuPosition.headerLeft)
		.role(DefinedUserAuthority.systemMaster.name())
		.permitElse()
		.role(DefinedUserAuthority.normal.name())
		.permitElse()
			.menu("메뉴")
			.iconClass("fa fa-bars");
		
		siteBuilder.site("console")
		.menuGroup("main", MenuPosition.headerRight)
		.role(DefinedUserAuthority.systemMaster.name())
		.permitElse()
			.menu("설정")
			.iconClass("fa fa-gear")
				.child("기본 설정", "/configuration")
				.iconClass("fa fa-cogs");
	}

}
