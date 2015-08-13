package org.cinnamon.core.util;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * create date: 2010. 3. 19.
 * @author 신동성
 *
 */
public class MD5Creator {
	
	private static final String MD52 = "MD5";

	/**
	 * md5 문자열 생성
	 * 
	 * @param buf - md5 문자만들 원본 데이터의 byte 배열
	 * @return
	 */
	public static String makeMD5String(byte[] buf) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance(MD52);
		} catch (NoSuchAlgorithmException e) {
			//TODO error 처리
			e.printStackTrace();
		}
		
		md5.update(buf);
		byte[] digest = md5.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			String result = Integer.toHexString(255 & (char) digest[i]);
			sb.append(result.length() > 1 ? result : "0" + result);
		}
		
		return sb.toString();
	}
	
    /**
     * md5 문자열 생성
     * 
     * @param filePath - md5 문자만들 원본 파일 경로
     * @return
     */
	public static String makeMD5String(String filePath) {
		byte[] buf = null;
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(filePath));
			
			buf = new byte[dis.available()];
			while (dis.read(buf) <= 0) {
				break;
			}
			dis.close();
		} catch (Exception e) {
			//TODO error 처리
			e.printStackTrace();
		}
		
		return makeMD5String(buf);
	}
}
