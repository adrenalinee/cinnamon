package org.cinnamon.core.repository;

import org.cinnamon.core.domain.FileChunk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
@RepositoryRestResource(exported=false)
public interface FileChunkRepository extends JpaRepository<FileChunk, String> {

}
