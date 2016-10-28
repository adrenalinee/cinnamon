package org.cinnamon.apps.repository;

import org.cinnamon.apps.domain.ClientAuthorizedGrantType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author 신동성
 * @since 2016. 10. 24.
 */
public interface ClientAuthorizedGrantTypeRepository extends JpaRepository<ClientAuthorizedGrantType, Long> {

}
