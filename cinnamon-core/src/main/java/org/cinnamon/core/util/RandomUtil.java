package org.cinnamon.core.util;

import java.util.Random;

/**
 * 
 * 
 * create: 2012. 7. 16.
 * @author hylee
 *
 */
public class RandomUtil {
	
	public static String getRandom(int length) {
		String rndValue = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		
		StringBuffer sbf = new StringBuffer();
		
		Random random = new Random();
		for (int i=0; i<length; i++) {
				int rnd = random.nextInt(36);
				sbf.append(rndValue.substring(rnd, rnd+1));
		}
		
		return sbf.toString();
	}
	
	public static String getRandomNumber(int length) {
		String rndValue = "0123456789";
		
		StringBuffer sbf = new StringBuffer();
		
		Random random = new Random();
		for (int i=0; i<length; i++) {
				int rnd = random.nextInt(10);
				sbf.append(rndValue.substring(rnd, rnd+1));
		}
		
		return sbf.toString();
	}
	
}


