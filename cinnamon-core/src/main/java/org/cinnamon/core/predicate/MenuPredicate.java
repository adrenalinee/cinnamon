package org.cinnamon.core.predicate;

import java.util.List;

import org.cinnamon.core.domain.QMenu;
import org.cinnamon.core.domain.enumeration.MenuPosition;
import org.cinnamon.core.domain.enumeration.UseStatus;

import com.mysema.query.types.Predicate;

/**
 * 
 * @author shindongseong
 *
 */
public class MenuPredicate {
	
	public static Predicate menus(String dimension, MenuPosition position, List<String> grantedAuthorities) {
		QMenu menu = QMenu.menu;
		
		return menu.position.eq(position)
		.and(menu.useStatus.eq(UseStatus.enable))
		.and(menu.parent.isNotNull())
		.and(menu.menuGroup.dimension.eq(dimension))
		.and(menu.grantedAuthorities.any().authority.authority.in(grantedAuthorities));
	}
}
