package org.cinnamon.core.rest.restcontroller;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromController;

import java.net.URI;
import java.util.Arrays;

import javax.persistence.Id;
import javax.validation.Valid;

import org.cinnamon.core.exception.NotFoundException;
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

import lombok.Getter;

/**
 * 
 * @author malibu
 *
 * @param <ENT> entity
 * @param <ID> entity id
 * @param <FORM> form
 * @param <SEAR> search
 * @param <RES> resource
 * @param <BS> BaseService
 * @param <REPO> repository
 */
public abstract class BaseRestController<ENT, ID, FORM, SEAR, RES,
	BS extends BaseService<ENT, ID, FORM, SEAR, REPO>,
	REPO extends JpaRepository<ENT, ID> & QuerydslPredicateExecutor<ENT>> {

	@Getter
	private final Class<RES> resourceClass;
	
	@Getter
	@Autowired
	protected BS service;
	
	@Getter
	@Autowired
	private Mapper beanMapper;

	public BaseRestController(Class<RES> resourceClass) {
		this.resourceClass = resourceClass;
	}
	
	@PostMapping("")
	public ResponseEntity<RES> postEntities(@RequestBody @Valid FORM form, UriComponentsBuilder builder) {
		final ENT entity = service.save(form);
		
		final URI location = fromController(getClass()).pathSegment(findId(entity).toString())
			.buildAndExpand(findId(entity))
			.toUri();
		
		return ResponseEntity
			.created(location)
			.body(beanMapper.map(entity, resourceClass));
	}
	
	@GetMapping("")
	public ResponseEntity<Page<RES>> getEntities(SEAR search, Pageable pageable) {
		final Page<RES> page = service
			.findAll(search, pageable)
			.map(entity -> beanMapper.map(entity, resourceClass));
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<RES> getEntity(@PathVariable ID id) {
		final ENT entity = service.findById(id)
				.orElseThrow(NotFoundException::new);
		
		return ResponseEntity.ok(beanMapper.map(entity, resourceClass));
	}
	
	@PatchMapping("{id")
	public ResponseEntity<Void> patchEntity(@PathVariable ID id, @RequestBody FORM form) {
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
	
	protected ID findId(ENT entity) {
		try {
			return (ID) Arrays.asList(entity.getClass().getDeclaredFields())
			.stream()
			.filter(field -> {
				final Id id = field.getAnnotation(Id.class);
				if (id != null) {
					return true;
				}
				
				return false;
			})
			.peek(field -> field.setAccessible(true))
			.findFirst()
			.orElseThrow(RuntimeException::new)
			.get(entity);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		Arrays.asList(BaseRestController.class.getMethods())
		.forEach(method -> System.out.println(method.getName()));
	}
}
