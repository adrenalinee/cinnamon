package org.cinnamon.core.rest.restcontroller;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.service.MenuService2;
import org.cinnamon.core.vo.MenuForm;
import org.cinnamon.core.vo.resource.MenuR;
import org.cinnamon.core.vo.search.MenuSearch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menus")
public class MenuRestController
	extends BaseRestController<Menu, Long, MenuForm, MenuSearch, MenuR,
	MenuService2, MenuRepository> {

	public MenuRestController() {
		super(MenuR.class);
	}
	
}
