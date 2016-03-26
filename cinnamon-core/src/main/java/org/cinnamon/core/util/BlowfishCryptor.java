package org.cinnamon.core.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import com.mysema.commons.lang.URLEncoder;


/**
 * 
 * create date: 2010. 6. 21.
 * 
 * @author 신동성
 * @version 1.1
 */
public class BlowfishCryptor {
	private static final String ENCRYPT_ALGORITHM = "Blowfish";
	private static final String READY_MADE_KEY = "747269706c6541212553";
	private Cipher encryptCipher;

	private Cipher decryptCipher;
	
	
	public BlowfishCryptor() {
		this(READY_MADE_KEY);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	public BlowfishCryptor(String secretkeyString) {
		if (secretkeyString == null) {
			throw new IllegalArgumentException("secretkeyString is null.");
		}
		
		// create a key
		try {
			SecretKey secretkey = getSecretKeyFromHex(secretkeyString);

			// KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish");
			//
			// SecretKey secretkey = keygenerator.generateKey();

			// SecretKey key = null;
			//
			// byte[] keyBytes = "asdf".getBytes();
			// SecretKey secretkey = new SecretKeySpec(keyBytes,
			// ENCRYPT_ALGORITHM);

			// encryptCipher = Cipher.getInstance("Blowfish");
			encryptCipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
			encryptCipher.init(Cipher.ENCRYPT_MODE, secretkey);

			// decryptCipher = Cipher.getInstance("Blowfish");
			decryptCipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
			decryptCipher.init(Cipher.DECRYPT_MODE, secretkey);
		} catch (Exception e) {
			throw new RuntimeException("BlowfishCryptor 생성중 오류가 발생하였습니다.", e);
		}

	}

	/**
	 * 
	 */
	public String decrypt(String value) {
		byte[] decryptedData;
		try {
			decryptedData = decryptCipher.doFinal(Hex.decodeHex(value.toCharArray()));
		} catch (Exception e) {
			throw new DecryptException("복호화 도중 오류가 발생하였습니다.", e);
		}
		return new String(decryptedData);
	}

	/**
	 * 
	 */
	public String encrypt(String value) throws Exception {
		byte[] encryptedData;
		try {
			encryptedData = encryptCipher.doFinal(value.getBytes());
			return new String(Hex.encodeHex(encryptedData));
		} catch (Exception e) {
			throw new EncryptException("암호화 도중 오류가 발생하였습니다.", e);
		}

		// System.out.println("----------------------");
		// for (byte b: encryptedData) {
		// System.out.println(b);
		// }
		//
		// System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		// String s = new String(encryptedData);
		//
		// for (byte b: s.getBytes()) {
		// System.out.println(b);
		// }

	}

	/**
	 * 
	 * @param keyHex
	 * @return
	 * @throws Exception
	 */
	private static SecretKey getSecretKeyFromHex(String keyHex) throws Exception {
		SecretKey key = null;

		byte[] keyBytes = Hex.decodeHex(keyHex.toCharArray());

		key = new SecretKeySpec(keyBytes, ENCRYPT_ALGORITHM);
		return key;
	}

	public static void main(String[] args) throws Exception {
//		byte[] keyBytes = Hex.decodeHex("8768acd3687c0dee1b".toCharArray());
//		
//		System.out.println(new String(keyBytes));
		
		
		System.out.println(URLEncoder.encodeURL("&clientIp=0:0:0:0:0:0:0:1&companyId=1&loginDateTime=20150303 205437&serialNo=0000000010&userId=dreamer"));
	}
}

/**
 * 
 * @author 동성
 * @since 2014. 12. 18.
 */
@SuppressWarnings("serial")
class DecryptException extends RuntimeException {
	
	public DecryptException(String message, Throwable cause) {
		super(message, cause);
	}
}


/**
 * 
 * @author 동성
 * @since 2014. 12. 18.
 */
@SuppressWarnings("serial")
class EncryptException extends RuntimeException {
	
	public EncryptException(String message, Throwable cause) {
		super(message, cause);
	}
}
