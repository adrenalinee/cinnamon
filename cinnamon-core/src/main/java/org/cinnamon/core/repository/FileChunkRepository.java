package org.cinnamon.core.repository;

import org.cinnamon.core.domain.FileChunk;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 *
 * created date: 2015. 8. 20.
 * @author 신동성
 */
public interface FileChunkRepository extends JpaRepository<FileChunk, String> {

}
