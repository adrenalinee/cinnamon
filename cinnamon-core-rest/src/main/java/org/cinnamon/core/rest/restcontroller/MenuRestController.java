package org.cinnamon.core.rest.restcontroller;

import org.cinnamon.core.domain.Menu;
import org.cinnamon.core.repository.MenuRepository;
import org.cinnamon.core.service.MenuService2;
import org.cinnamon.core.vo.MenuVo;
import org.cinnamon.core.vo.resource.MenuResource;
import org.cinnamon.core.vo.search.MenuSearch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menus")
public class MenuRestController
	extends BaseRestController<Menu, Long, MenuVo, MenuSearch, MenuResource,
	MenuService2, MenuRepository> {

	public MenuRestController() {
		super(MenuResource.class);
	}
	
}
