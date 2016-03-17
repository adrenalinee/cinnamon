package org.cinnamon.apps.repository;

import org.cinnamon.apps.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 9. 30.
 * @author 신동성
 */
public interface ClientRepository extends JpaRepository<Client, String> {

}
