package org.cinnamon.web.init;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.cinnamon.core.domain.Group;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.domain.enumeration.MenuType;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.domain.enumeration.UserActivityType;
import org.cinnamon.core.enumeration.Groups;
import org.cinnamon.core.init.InitData;

/**
 * 
 * @author 동성
 *
 */
public class GroupInitData implements InitData {
	
	EntityManager em;
	
	/**
	 * 
	 */
	public void save(EntityManager em) {
		this.em = em;
		
		useStatus();
		userActivities();
		menuPositions();
		menuTypes();
//		genders();
		nations();
	}
	
	
	private void menuTypes() {
		Group group = new Group();
		group.setGroupId(Groups.menuTypes.name());
		group.setName("메뉴 종류");
		em.persist(group);
		
		int order = 0;
		
		Group childGroup = new Group();
		childGroup.setGroupId(MenuType.normal.name());
		childGroup.setName("일반");
		childGroup.setDescription("특정 경로로 이동하는 링크 메뉴와 하위 메뉴를 포함하고 있는 container형 메뉴");
		childGroup.setParent(group);
		childGroup.setOrders(order++);
		em.persist(childGroup);
		
		childGroup = new Group();
		childGroup.setGroupId(MenuType.separater.name());
		childGroup.setName("구분자");
		childGroup.setParent(group);
		childGroup.setOrders(order++);
		em.persist(childGroup);
		
		childGroup = new Group();
		childGroup.setGroupId(MenuType.label.name());
		childGroup.setName("단순 라벨");
		childGroup.setParent(group);
		childGroup.setOrders(order++);
		em.persist(childGroup);
		
		childGroup = new Group();
		childGroup.setGroupId(MenuType.component.name());
		childGroup.setName("기능이 구현된 컴포넌트");
		childGroup.setParent(group);
		childGroup.setOrders(order++);
		em.persist(childGroup);
	}


	private void nations() {
		Group group = new Group();
		group.setGroupId(Groups.nations.name());
		group.setName("국가");
		em.persist(group);
		
		
		Map<String, Locale> countries = new TreeMap<>();
		for (Locale locale: Locale.getAvailableLocales()) {
			countries.put(locale.getCountry(), locale);
		}
		
		int order = 0;
//		countries.forEach((country, locale) -> {
		for (Entry<String, Locale> e: countries.entrySet()) {
			Locale l = e.getValue();
			
			if (StringUtils.isEmpty(l.getCountry())) {
				continue;
			}
			
			Group childGroup = new Group();
			childGroup.setGroupId(l.getCountry());
			childGroup.setName(l.getDisplayCountry());
			childGroup.setParent(group);
			childGroup.setOrders(order++);
			em.persist(childGroup);
		}
		
		
			Group childGroup = new Group();
			childGroup.setGroupId("INTERNATIONAL");
			childGroup.setName("인터내셔날");
			childGroup.setParent(group);
			childGroup.setOrders(order);
			em.persist(childGroup);
//		}
	}
	
	
//	private void genders() {
//		Group group = new Group();
//		group.setGroupId("genders");
//		group.setName("성별");
//		em.persist(group);
//		
//		Group childGroup = new Group();
//		childGroup.setGroupId("male");
//		childGroup.setName("남자");
//		childGroup.setParent(group);
//		childGroup.setOrders(0);
//		em.persist(childGroup);
//		
//		childGroup = new Group();
//		childGroup.setGroupId("female");
//		childGroup.setName("여자");
//		childGroup.setParent(group);
//		childGroup.setOrders(1);
//		em.persist(childGroup);
//	}

	private void menuPositions() {
		Group group = new Group();
		group.setGroupId("menuPositions");
		group.setName("메뉴 위치");
		em.persist(group);
		
		int order = 0;
		Group childGroup = new Group();
		childGroup.setGroupId(MenuPosition.sidebar.name());
		childGroup.setName("사이드바");
		childGroup.setParent(group);
		childGroup.setOrders(order++);
		em.persist(childGroup);
		
		childGroup = new Group();
		childGroup.setGroupId(MenuPosition.headerLeft.name());
		childGroup.setName("해더 왼쪽");
		childGroup.setParent(group);
		childGroup.setOrders(order++);
		em.persist(childGroup);
		
		childGroup = new Group();
		childGroup.setGroupId(MenuPosition.headerRight.name());
		childGroup.setName("해더 오른쪽");
		childGroup.setParent(group);
		childGroup.setOrders(order++);
		em.persist(childGroup);
	}

	private void userActivities() {
		Group group = new Group();
		group.setGroupId("userActivities");
		group.setName("사용자 활동");
		em.persist(group);
		
		Group childGroup = new Group();
		childGroup.setGroupId(UserActivityType.login.name());
		childGroup.setName("로그인");
		childGroup.setParent(group);
		childGroup.setOrders(0);
		em.persist(childGroup);
		
		childGroup = new Group();
		childGroup.setGroupId(UserActivityType.join.name());
		childGroup.setName("가입");
		childGroup.setParent(group);
		childGroup.setOrders(1);
		em.persist(childGroup);
		
		childGroup = new Group();
		childGroup.setGroupId(UserActivityType.changeState.name());
		childGroup.setName("상태 변경");
		childGroup.setParent(group);
		childGroup.setOrders(2);
		em.persist(childGroup);
		
		childGroup = new Group();
		childGroup.setGroupId(UserActivityType.changePassword.name());
		childGroup.setName("비밀번호 변경");
		childGroup.setParent(group);
		childGroup.setOrders(3);
		em.persist(childGroup);
		
		childGroup = new Group();
		childGroup.setGroupId(UserActivityType.update.name());
		childGroup.setName("특정 페이지 수정");
		childGroup.setParent(group);
		childGroup.setOrders(4);
		em.persist(childGroup);
		
		childGroup = new Group();
		childGroup.setGroupId(UserActivityType.create.name());
		childGroup.setName("특정 페이지 생성");
		childGroup.setParent(group);
		childGroup.setOrders(5);
		em.persist(childGroup);
	}

	private void useStatus() {
		Group group = new Group();
		group.setGroupId("useStatus");
		group.setName("사용 상태");
		em.persist(group);
		
		Group childGroup = new Group();
		childGroup.setGroupId(UseStatus.enable.name());
		childGroup.setName("사용중");
		childGroup.setParent(group);
		childGroup.setOrders(0);
		em.persist(childGroup);
		
		childGroup = new Group();
		childGroup.setGroupId(UseStatus.disable.name());
		childGroup.setName("미사용중");
		childGroup.setParent(group);
		childGroup.setOrders(1);
		em.persist(childGroup);
	}
	
	public static void main(String[] args) {
//		for (Locale l: Locale.getAvailableLocales()) {
//			System.out.print(l.getCountry() + ", ");
//			System.out.print(l.getDisplayCountry() + ", ");
//			System.out.print(l.getLanguage() + ", ");
//			System.out.print(l.getDisplayLanguage() + ", ");
//			System.out.print(l.getDisplayName() + ", ");
//			System.out.print(l.getDisplayScript() + ", ");
//			System.out.println(l.getDisplayVariant());
//		}
		
		for (String c: Locale.getISOCountries()) {
			System.out.println(c);
		}
		
//		for (String c: Locale.getISOLanguages()) {
//			System.out.println(c);
//		}

	}
	
}
