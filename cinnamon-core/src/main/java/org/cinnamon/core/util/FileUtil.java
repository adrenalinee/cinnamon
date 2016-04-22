package org.cinnamon.core.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.cinnamon.core.domain.FileInformation;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

@Component
public class FileUtil {
	
	@Value("${internalFile.root}")
	String internalFileRoot;
	
	public List<FileInformation> getFileInfomationList(String prePath, String sufPath, MultipartRequest multiRequest) throws IOException {
		
		Map<String, MultipartFile> files = multiRequest.getFileMap();
		List<FileInformation> fileInformationList = new ArrayList<FileInformation>();
		FileInformation fileInformation;
		
		Iterator<Entry<String, MultipartFile>> it = files.entrySet().iterator();
		MultipartFile file;
		
		while(it.hasNext()) {
			
			Entry<String, MultipartFile> entry = it.next();
			file = entry.getValue();
			fileInformation = new FileInformation();
			
			
			String extention = FilenameUtils.getExtension(file.getOriginalFilename());
			String fullPath = PathUtil.create(prePath, sufPath);
			String saveFileName = DateTime.now().toString("yyyyMMddkkmmssSSS") + "." + extention;
			String savePath = internalFileRoot + fullPath;
			File destinationMd5 = FileUtils.getFile(new File(savePath), saveFileName + ".md5");
			File destination = FileUtils.getFile(new File(savePath), saveFileName);
			String md5String = MD5Creator.makeMD5String(destination.getAbsolutePath());
			
			// 파일 생성
			FileUtils.writeStringToFile(destinationMd5, md5String);
			
			fileInformation.setSize(file.getSize());
			fileInformation.setName(saveFileName);
			fileInformation.setMd5FileName(saveFileName + ".md5");
			fileInformation.setOriginFileName(file.getName());
			fileInformation.setPath("/machineClients/clientFiles");
			fileInformation.setExt(extention);
			fileInformation.setMd5(md5String);
			
			fileInformationList.add(fileInformation);
		}
		return fileInformationList;
	}
}
