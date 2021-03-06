package org.cinnamon.core.repository;

import org.cinnamon.core.domain.UserBase;
import org.cinnamon.core.domain.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface UserGroupRepository extends JpaRepository<UserGroup, Long>, UserGroupRepositoryCustom<UserBase>{

}
