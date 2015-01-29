package com.adrenalinee.common.api.authentication;

import java.security.MessageDigest;

/**
 * 비밀번호 단방향 암호화 변환기
 * @author 동성
 * @since 2015. 1. 28.
 */
public class PasswordEncoder {
	
	/**
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public String encode(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-384");
		
		md.update(password.getBytes());
		
		byte[] eps = md.digest();
		
		StringBuffer sb = new StringBuffer();
		for (byte b: eps) {
//			System.out.println(b);
			sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param password - 확인하려는 암호
	 * @param encryptedPassword - 이미 암호화된 올바른 비밀번호
	 * @return
	 * @throws Exception
	 */
	public boolean match(String password, String encryptedPassword) throws Exception {
		String expectedEncryptedPassword = encode(password);
		
//		System.out.println(password);
//		System.out.println(expectedEncryptedPassword);
//		System.out.println(encryptedPassword);
		
		return expectedEncryptedPassword.equals(encryptedPassword);
	}
}
