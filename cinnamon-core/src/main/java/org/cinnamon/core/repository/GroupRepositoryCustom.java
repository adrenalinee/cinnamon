package org.cinnamon.core.repository;

import java.util.List;

import org.cinnamon.core.domain.Group;
import org.cinnamon.core.domain.enumeration.UseStatus;
import org.cinnamon.core.vo.search.GroupSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface GroupRepositoryCustom {

	Page<Group> search(GroupSearch groupSearch, Pageable pageable);

	List<Group> childGroups(String parentGroupId, UseStatus useStatus);

}
