package org.cinnamon.core.service;

import java.util.Optional;

import org.cinnamon.core.exception.NotExistException;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

public abstract class BaseService<ENT, ID, FORM, SEAR, REPO
	extends JpaRepository<ENT, ID> & QuerydslPredicateExecutor<ENT>> {
	
	private final Class<ENT> entityClass;
	
	@Autowired
	private REPO repository;
	
	@Autowired
	private Mapper beanMapper;
	
	public BaseService(Class<ENT> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Transactional(readOnly = true)
	public Page<ENT> findAll(SEAR search, Pageable pageable) {
		//TODO
		Predicate predicate = null;
		
		return repository.findAll(predicate, pageable);
	}
	
	@Transactional
	public ENT save(FORM form) {
		return repository.save(beanMapper.map(form, entityClass));
	}
	
	@Transactional(readOnly = true)
	public Optional<ENT> findById(ID id) {
		return repository.findById(id);
	}
	
	@Transactional
	public void merge(ID id, FORM target) {
		final ENT entity = repository.findById(id)
			.orElseThrow(NotExistException::new);
		
		beanMapper.map(target, entity);
	}
	
	@Transactional
	public void deleteById(ID id) {
		repository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public long count() {
		return repository.count();
	}
	
	@Transactional(readOnly = true)
	public boolean existsById(ID id) {
		return repository.existsById(id);
	}
}
