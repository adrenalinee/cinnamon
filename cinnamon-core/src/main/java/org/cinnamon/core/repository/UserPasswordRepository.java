package org.cinnamon.core.repository;

import org.cinnamon.core.domain.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 
 * create date: 2015. 6. 17.
 * @author 신동성
 *
 */
@RepositoryRestResource(exported=false)
public interface UserPasswordRepository extends JpaRepository<UserPassword, String> {

}
