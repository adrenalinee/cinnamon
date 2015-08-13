package org.cinnamon.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.util.FileCopyUtils;

/**
 * 
 * create date: 2015. 4. 10.
 * @author 동성
 *
 */
public class FileDownload {
	
	
	public static void fileDownload(String path, String name, String originFileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String path = fileInformation.getPath();
//		String name = fileInformation.getName();
//		String originFileName = fileInformation.getOriginFileName();
		
		File file = FileUtils.getFile(new File(path), name);
		
		String filename = null;
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		if (ie) {
			filename = URLEncoder.encode(originFileName, "utf-8");
		} else {
			filename = new String(originFileName.getBytes("utf-8"));
		}
		
		
		System.out.println(filename);
		
		
		response.setContentLengthLong(file.length());
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Dispositioin", "attachment;filename=\"" + filename + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		
		OutputStream out = null;
		FileInputStream fis = null;
		try {
			out = response.getOutputStream();
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} finally {
			if (out != null) {
				out.close();
			}
			
			if (fis != null) {
				fis.close();
			}
		}
	}
}
