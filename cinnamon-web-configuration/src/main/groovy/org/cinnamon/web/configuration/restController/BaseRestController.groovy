package org.cinnamon.web.configuration.restController

import javax.validation.Valid

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.binding.QuerydslPredicate
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.util.UriComponentsBuilder;

import com.mysema.query.types.Predicate

/**
 * 
 *
 * created date: 2015. 9. 14.
 * @author 신동성
 */
class BaseRestController<T, R extends JpaRepository<T, Serializable>> {
	protected Logger logger = LoggerFactory.getLogger getClass()
	
	R repository
	
	@RequestMapping(value="", method=RequestMethod.GET)
	Page<T> list(@QuerydslPredicate(root=T) Predicate predicate, Pageable pageable) {
		repository.findAll predicate, pageable
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	T get(@PathVariable T domain) {
		domain
	}
	
}
