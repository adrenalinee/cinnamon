package org.cinnamon.core.rest.restcontroller;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

import java.net.URI;

import javax.validation.Valid;

import org.cinnamon.core.service.BaseService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 
 * @author malibu
 *
 * @param <T> entity
 * @param <ID> entity id
 * @param <F> form
 * @param <S> search
 * @param <D> dto
 * @param <BS> BaseService
 * @param <R> repository
 */
public abstract class BaseRestController<T, ID, F, S, D,
	BS extends BaseService<T, ID, F, S, R>,
	R extends JpaRepository<T, ID> & QuerydslPredicateExecutor<T>> {

	private final Class<D> dtoClass;
	
	public BaseRestController(Class<D> dtoClass) {
		this.dtoClass = dtoClass;
	}
	
	@Autowired
	private BS service;
	
	@Autowired
	private Mapper beanMapper;
	
	
	@PostMapping("")
	@SuppressWarnings("unchecked")
	public ResponseEntity<D> postEntities(@RequestBody @Valid F form, UriComponentsBuilder builder) {
		final T entity = service.save(form);
		final URI location = fromMethodCall(builder, on(getClass()).getEntity(findId(entity)))
			.build()
			.toUri();
		
		return ResponseEntity
			.created(location)
			.body(beanMapper.map(entity, dtoClass));
	}
	
	@GetMapping("")
	public ResponseEntity<Page<D>> getEntities(S search, Pageable pageable) {
		final Page<D> page = service
			.findAll(search, pageable)
			.map(entity -> beanMapper.map(entity, dtoClass));
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<D> getEntity(@PathVariable ID id) {
		final T entity = service.findById(id)
				.orElseThrow(RuntimeException::new);
		
		return ResponseEntity.ok(beanMapper.map(entity, dtoClass));
	}
	
	@PatchMapping("{id")
	public ResponseEntity<Void> patchEntity(@PathVariable ID id, @RequestBody F form) {
		service.merge(id, form);
		
		return ResponseEntity
			.ok()
			.build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteEntity(@PathVariable ID id) {
		service.deleteById(id);
		
		return ResponseEntity
			.ok()
			.build();
	}
	
	protected ID findId(T entity) {
		return null;
	}
}
