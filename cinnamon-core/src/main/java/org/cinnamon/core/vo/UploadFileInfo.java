package org.cinnamon.core.vo;

/**
 * 
 * create date: 2015. 4. 9.
 * @author 동성
 *
 */
public class UploadFileInfo {
	
//	String prePath;
//	
//	String sufPath;
	
	String originFileName;
	
	String identifier;
	
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

//	public String getPrePath() {
//		return prePath;
//	}
//
//	public void setPrePath(String prePath) {
//		this.prePath = prePath;
//	}
//
//	public String getSufPath() {
//		return sufPath;
//	}
//
//	public void setSufPath(String sufPath) {
//		this.sufPath = sufPath;
//	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

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
	
}
