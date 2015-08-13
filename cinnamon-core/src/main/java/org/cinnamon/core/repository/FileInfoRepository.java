package org.cinnamon.core.repository;

import javax.persistence.EntityManager;

import org.cinnamon.core.domain.FileChunk;
import org.cinnamon.core.domain.FileInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author 동성
 * @since 2015. 1. 29.
 */
@Repository("configuration.fileInfoRepository")
public class FileInfoRepository {
	
	@Autowired
	EntityManager em;
	
	public FileInformation findById(Long fileId) {
		return em.find(FileInformation.class, fileId);
	}
	
	public void persist(FileInformation fileInformation) {
		em.persist(fileInformation);
	}
	
	
	public void persist(FileChunk fileChunk) {
		em.persist(fileChunk);
	}
	
	
	public void remove(FileChunk fileChunk) {
		em.remove(fileChunk);
	}
	
	public FileInformation findByIdentifier(String identifier) {
		return em.createQuery("select f from FileInformation f where f.identifier = :identifier", FileInformation.class)
				.setParameter("identifier", identifier)
				.getSingleResult();
	}
	
	
	public FileChunk findChunkById(String identifier) {
		return em.find(FileChunk.class, identifier);
	}
}
