package org.cinnamon.apps.repository;

import org.cinnamon.apps.domain.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author 신동성
 * @since 2016. 10. 24.
 */
public interface ScopeRepository extends JpaRepository<Scope, String> {

}
