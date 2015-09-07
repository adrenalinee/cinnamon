package org.cinnamon.web.configuration.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * create date: 2015. 4. 9.
 * @author 동성
 *
 */
@Entity
public class FileChunk {
	
	@Id
	String identifier;
	
//	@ManyToOne(optional=false)
//	FileInformation file;
	
	/**
	 * 파일을 쪼게서 보낼때 몇번째 인지 알려줌
	 * (1부터 시작)
	 */
	int chunkNumber;
	
	int totalChunks;
	
	/**
	 * 전체 파일 사이즈
	 */
	long totalSize;
	
	String name;
	
	String path;
	
	String originFileName;
	
	

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

//	public FileInformation getFile() {
//		return file;
//	}
//
//	public void setFile(FileInformation file) {
//		this.file = file;
//	}

	public int getChunkNumber() {
		return chunkNumber;
	}

	public void setChunkNumber(int chunkNumber) {
		this.chunkNumber = chunkNumber;
	}

	public int getTotalChunks() {
		return totalChunks;
	}

	public void setTotalChunks(int totalChunks) {
		this.totalChunks = totalChunks;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	
}
