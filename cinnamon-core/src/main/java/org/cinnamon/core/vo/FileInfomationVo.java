package org.cinnamon.core.vo;

import org.cinnamon.core.domain.enumeration.UseStatus;

public class FileInfomationVo {

	Long fileId;
	
	String name;
	
	String path;
	
	/**
	 * 확장자
	 */
	String ext;
	
	/**
	 * byte
	 */
	Long size;
	
	String originFileName;
	
	/**
	 * 알려진 확장자를 바탕으로 타입 지정
	 * doc:문서
	 * text: 
	 * image: 이미지
	 * video: 동영상
	 * audio: 음악
	 */
	String type;
	
	String md5FileName;
	
	String md5;
	
	UseStatus useStatus = UseStatus.enable;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
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

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMd5FileName() {
		return md5FileName;
	}

	public void setMd5FileName(String md5FileName) {
		this.md5FileName = md5FileName;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public UseStatus getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(UseStatus useStatus) {
		this.useStatus = useStatus;
	} 
	
	
}
