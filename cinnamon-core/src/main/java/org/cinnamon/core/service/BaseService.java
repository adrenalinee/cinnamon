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

public abstract class BaseService<T, ID, F, S, R extends JpaRepository<T, ID> & QuerydslPredicateExecutor<T>> {
	
	private final Class<T> entityClass;
	
	@Autowired
	private R repository;
	
	@Autowired
	private Mapper beanMapper;
	
	public BaseService(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Transactional(readOnly = true)
	public Page<T> findAll(S search, Pageable pageable) {
		//TODO
		Predicate predicate = null;
		
		return repository.findAll(predicate, pageable);
	}
	
	@Transactional
	public T save(F form) {
		return repository.save(beanMapper.map(form, entityClass));
	}
	
	@Transactional(readOnly = true)
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}
	
	@Transactional
	public void merge(ID id, F target) {
		final T entity = repository.findById(id)
			.orElseThrow(NotExistException::new);
		
		beanMapper.map(target, entity);
	}
	
	@Transactional
	public void deleteById(ID id) {
		repository.deleteById(id);
	}
	
	public long count() {
		return repository.count();
	}
	
	@Transactional(readOnly = true)
	public boolean existsById(ID id) {
		return repository.existsById(id);
	}
}
