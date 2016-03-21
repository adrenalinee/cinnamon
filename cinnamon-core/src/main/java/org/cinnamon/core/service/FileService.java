package org.cinnamon.core.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.cinnamon.core.domain.FileChunk;
import org.cinnamon.core.domain.FileInformation;
import org.cinnamon.core.exception.BadRequestException;
import org.cinnamon.core.repository.FileChunkRepository;
import org.cinnamon.core.repository.FileInfoRepository;
import org.cinnamon.core.util.MD5Creator;
import org.cinnamon.core.util.PathUtil;
import org.cinnamon.core.vo.UploadFileInfo;
import org.cinnamon.core.vo.search.FileInformationSearch;
import org.cinnamon.core.vo.search.FileInformationSearch;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 
 * @author 동성
 * @since 2015. 1. 29.
 */
@Service
public class FileService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${web.webroot}")
	String webroot;
	
	@Value("${internalFile.root}")
	String internalFileRoot;
	
	@Autowired
	FileInfoRepository fileInfoRepository;
	
	@Autowired
	FileChunkRepository fileChunkRepository;
	
	
	@Transactional(readOnly=true)
	public FileInformation get(Long fileId) {
		return fileInfoRepository.findOne(fileId);
	}
	
	/**
	 * 파일 리스트 조회
	 * @author 정명성
	 * create date : 2016. 3. 14.
	 * @param fileInformationSearch
	 * @param pageable
	 * @return
	 */
	public Page<FileInformation> search(FileInformationSearch fileInformationSearch, Pageable pageable) {
		
		return fileInfoRepository.search(fileInformationSearch, pageable);
	}
	
	
	/**
	 * 같은 파일을 여러개로 쪼개서(chunk) 한번에 조금씩 저장하는 기능 
	 * 
	 * @param uploadFileInfo
	 * @param fileContentStream
	 * @return 저장중일 경우에는 null 반환, 저장완료되면 저장된 FileInformation 객체 반환
	 * @throws IOException
	 */
	@Transactional
	public FileInformation saveInternal(String prePath, String sufPath, UploadFileInfo uploadFileInfo, InputStream fileContentStream) throws IOException {
		logger.info("start");
		
		int chunkNumber = uploadFileInfo.getChunkNumber();
		int totalChunks = uploadFileInfo.getTotalChunks();
		
		
		
		if (chunkNumber == 1) {
			firstChunkSaveFile(prePath, sufPath, uploadFileInfo, fileContentStream);
		} else if (chunkNumber >= totalChunks) {
			return lastChunkSaveFile(uploadFileInfo, fileContentStream);
		} else {
			middleChunkSaveFile(uploadFileInfo, fileContentStream);
		}
		
		
		return null;
	}
	
	
	private void middleChunkSaveFile(UploadFileInfo uploadFileInfo, InputStream fileContentStream) throws IOException {
		String identifier = uploadFileInfo.getIdentifier();
		int chunkNumber = uploadFileInfo.getChunkNumber();
		
		FileChunk fileChunk = fileChunkRepository.findOne(identifier);
		if (fileChunk == null) {
			throw new BadRequestException("파일 업로드 기록이 없습니다. identifier: " + identifier);
		}
		
		String savePath = fileChunk.getPath();
		String saveFileName = fileChunk.getName();
		
		File destination = FileUtils.getFile(new File(savePath), saveFileName);
		if (destination == null) {
			throw new BadRequestException("파일 업로드 기록이 없습니다. identifier: " + identifier);
		}
		
//		FileUtils.copyInputStreamToFile(fileContentStream, destination);
		
		FileOutputStream fo = new FileOutputStream(destination);
		IOUtils.copy(fileContentStream, fo);
		
		fileChunk.setChunkNumber(chunkNumber);
	}


	private FileInformation lastChunkSaveFile(UploadFileInfo uploadFileInfo, InputStream fileContentStream) throws IOException {
		String identifier = uploadFileInfo.getIdentifier();
		String originFileName = uploadFileInfo.getOriginFileName();
		long totlaSize = uploadFileInfo.getTotalSize();
		
		FileChunk fileChunk = fileChunkRepository.findOne(identifier);
		if (fileChunk == null) {
			throw new BadRequestException("파일 업로드 기록이 없습니다. identifier: " + identifier);
		}
		
		String savePath = fileChunk.getPath();
		String saveFileName = fileChunk.getName();
		
		File destination = FileUtils.getFile(new File(savePath), saveFileName);
		if (destination == null) {
			throw new BadRequestException("파일 업로드 기록이 없습니다. identifier: " + identifier);
		}
		
		FileOutputStream fo = new FileOutputStream(destination);
		IOUtils.copy(fileContentStream, fo);
		
		if (fileChunk.getTotalSize() != totlaSize) {
			throw new BadRequestException("업로드된 파일이 손상되었습니다. identifier: " + identifier);
		}
		
		
		
		File destinationMd5 = FileUtils.getFile(new File(savePath), saveFileName + ".md5");
		String md5String = MD5Creator.makeMD5String(destination.getAbsolutePath());
		FileUtils.writeStringToFile(destinationMd5, md5String);
		
		FileInformation fileInformation = new FileInformation();
		fileInformation.setSize(destination.length());
		fileInformation.setName(saveFileName);
		fileInformation.setMd5FileName(saveFileName + ".md5");
		fileInformation.setOriginFileName(originFileName);
		fileInformation.setPath(savePath);
//		fileInformation.setUri(uri);
		
		fileInfoRepository.save(fileInformation);
		fileChunkRepository.delete(fileChunk);
		
		return fileInformation;
	}
	
	
	private void firstChunkSaveFile(String prePath, String sufPath, UploadFileInfo uploadFileInfo, InputStream fileContentStream) throws IOException {
		String identifier = uploadFileInfo.getIdentifier();
		String originFileName = uploadFileInfo.getOriginFileName();
		int totalChunks = uploadFileInfo.getTotalChunks();
		long totalSize = uploadFileInfo.getTotalSize();
		
		if (prePath == null) {
			throw new BadRequestException("prePath is null.");
		}
		
		if (sufPath == null) {
			throw new BadRequestException("sufPathj is null.");
		}
		
		if (StringUtils.isEmpty(originFileName)) {
			throw new BadRequestException("file name이 없습니다.");
		}
		
		
		
		
//		String extention = "";
//		int extIndex = originFileName.lastIndexOf(".");
//		if (extIndex > -1) {
//			extention = originFileName.substring(extIndex);
//		}
		
		String extention = FilenameUtils.getExtension(originFileName);
		
		
//		String webPath = PathUtil.create("/uploaded/icons", iconId.toString());
		String fullPath = PathUtil.create(prePath, sufPath);
		
		String savePath = internalFileRoot + fullPath;
		String saveFileName = DateTime.now().toString("yyyyMMddkkmmssSSS") + extention;
//		System.out.println("savePath: " + savePath);
//		System.out.println("saveName: " + saveFileName);
		
		File destination = FileUtils.getFile(new File(savePath), saveFileName);
		FileUtils.copyInputStreamToFile(fileContentStream, destination);
		
//		File destinationMd5 = FileUtils.getFile(new File(savePath), saveFileName + ".md5");
//		String md5String = MD5Creator.makeMD5String(destination.getAbsolutePath());
//		FileUtils.writeStringToFile(destinationMd5, md5String);
		
		
		
		
		FileChunk fileChunk = new FileChunk();
		fileChunk.setIdentifier(identifier);
		fileChunk.setChunkNumber(1);
		fileChunk.setTotalChunks(totalChunks);
		fileChunk.setOriginFileName(originFileName);
		fileChunk.setTotalSize(totalSize);
		fileChunk.setPath(savePath);
		fileChunk.setName(saveFileName);
		
		
		fileChunkRepository.save(fileChunk);
		
		
//		String uri = fullPath + "/" + saveFileName;
		
//		FileInformation fileInformation = new FileInformation();
//		fileInformation.setSize(destination.length());
//		fileInformation.setName(saveFileName);
//		fileInformation.setMd5FileName(saveFileName + ".md5");
//		fileInformation.setOriginFileName(originFileName);
//		fileInformation.setPath(savePath);
////		fileInformation.setUri(uri);
//		
//		fileInfoRepository.persist(fileInformation);
//		
//		return fileInformation;
	}


	/**
	 * 프로그램이 내부에 파일을 저장할 위치(경로)를 기준으로 한
	 * 지경경로에 파일을 저장하고 저장된 정보를 돌려준다.
	 * 
	 * 
	 * @param path - 내부파일 저장 경로를 최상위로한 일 저장 경로
	 * @param originFileName
	 * @param fileContentStream
	 * @return
	 */
	@Transactional
	public FileInformation saveInternal(String prePath, String sufPath, String originFileName, InputStream fileContentStream) throws IOException {
		logger.info("start");
		
		if (prePath == null) {
			throw new BadRequestException("prePath is null.");
		}
		
		if (sufPath == null) {
			throw new BadRequestException("sufPathj is null.");
		}
		
		if (StringUtils.isEmpty(originFileName)) {
			throw new BadRequestException("file name이 없습니다.");
		}
		
		
//		String extention = "";
//		int extIndex = originFileName.lastIndexOf(".");
//		if (extIndex > -1) {
//			extention = originFileName.substring(extIndex);
//		}
		
		String extention = FilenameUtils.getExtension(originFileName);
		
		
//		String webPath = PathUtil.create("/uploaded/icons", iconId.toString());
		String fullPath = PathUtil.create(prePath, sufPath);
		
		String savePath = internalFileRoot + fullPath;
		String saveFileName = DateTime.now().toString("yyyyMMddkkmmssSSS") + "." + extention;
		System.out.println("savePath: " + savePath);
		System.out.println("saveName: " + saveFileName);
		
		File destination = FileUtils.getFile(new File(savePath), saveFileName);
		FileUtils.copyInputStreamToFile(fileContentStream, destination);
		
		File destinationMd5 = FileUtils.getFile(new File(savePath), saveFileName + ".md5");
		String md5String = MD5Creator.makeMD5String(destination.getAbsolutePath());
		FileUtils.writeStringToFile(destinationMd5, md5String);
		
		
//		String base64EncodedThumbnail = ThumbnailCreator.create(64, 64, destination.getAbsolutePath());
//		logger.info("base64EncodedThumbnail: {}", base64EncodedThumbnail);
		
		
//		String uri = fullPath + "/" + saveFileName;
		
		FileInformation fileInformation = new FileInformation();
		fileInformation.setSize(destination.length());
		fileInformation.setName(saveFileName);
		fileInformation.setMd5FileName(saveFileName + ".md5");
		fileInformation.setOriginFileName(originFileName);
		fileInformation.setPath(savePath);
		fileInformation.setExt(extention);
		fileInformation.setMd5(md5String);
//		fileInformation.setUri(uri);
		
		fileInfoRepository.save(fileInformation);
		
		return fileInformation;
	}
}
