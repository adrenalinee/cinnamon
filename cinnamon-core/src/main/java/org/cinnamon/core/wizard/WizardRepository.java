package org.cinnamon.core.wizard;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author 동성
 * @since 2015. 1. 19.
 */
@Repository
public class WizardRepository {
	
	@Autowired
	EntityManager em;
	
	public Wizard findById(Long wizardId) {
		return em.find(Wizard.class, wizardId);
	}
}
