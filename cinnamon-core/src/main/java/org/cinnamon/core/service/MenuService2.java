package org.cinnamon.core.service;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.vo.MenuForm;
import org.cinnamon.core.vo.search.MenuSearch;
import org.springframework.stereotype.Service;

@Service
public class MenuService2 extends BaseService<Menu, Long, MenuForm, MenuSearch, MenuRepository> {

	public MenuService2() {
		super(Menu.class);
	}

}
