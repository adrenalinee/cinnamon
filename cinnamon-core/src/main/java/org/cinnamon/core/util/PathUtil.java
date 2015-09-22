package org.cinnamon.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * create date: 2010. 5. 9.
 * 
 * @author 신동성
 * @version 1.0
 * 
 */
public class PathUtil {
	// private static final int MAX_FILES_IN_FOLDER = 1000;

	private static SimpleDateFormat sdfYyyy = new SimpleDateFormat("yyyy");
	private static SimpleDateFormat sdfMMdd = new SimpleDateFormat("MMdd");
	private static SimpleDateFormat sdfHH = new SimpleDateFormat("HH");

	/**
	 * 
	 * @param prePath
	 */
	public static String create(String prePath) {
		// String hashFolder = computeHash(Integer.parseInt(key));

		Date nowDate = new Date();
		// String folders[] = new String[] {
		// prePath,
		// DateUtils.format(nowDate, "yyyy"),
		// DateUtils.format(nowDate, "MMdd")
		// };

		String folders[] = new String[] { prePath, sdfYyyy.format(nowDate), sdfMMdd.format(nowDate), sdfHH.format(nowDate) };

		String targetFolderPath = makeFullPath(folders);

		return targetFolderPath;
	}

	/**
	 * 
	 * @param prePath
	 *            - 자동생성되는 경로 앞에 붙을 경로
	 * @param sufPath
	 *            - 자동생성되는 경로 뒤에 붙을 경로
	 * @return
	 */
	public static String create(String prePath, String sufPath) {
		// String hashFolder = computeHash(Integer.parseInt(key));

		Date nowDate = new Date();
		// String folders[] = new String[] {
		// prePath,
		// DateUtils.format(nowDate, "yyyy"),
		// DateUtils.format(nowDate, "MMdd"),
		// sufPath
		// };
		String folders[];
		if (sufPath != null && !"".equals(sufPath)) {
			folders = new String[] {
					prePath,
					sdfYyyy.format(nowDate),
					sdfMMdd.format(nowDate),
//					sdfHH.format(nowDate),
					sufPath
			};
		} else {
			folders = new String[] {
					prePath, sdfYyyy.format(nowDate),
					sdfMMdd.format(nowDate),
//					sdfHH.format(nowDate)
			};
		}

		String targetFolderPath = makeFullPath(folders);

		return targetFolderPath;

		// StringBuilder destPath = new StringBuilder(basePath);
		// destPath.append(getHashPath(key));
		//
		// return destPath.toString();
	}

	// /**
	// * 폴더 생성규칙에 따른 폴더를 반환한다.
	// *
	// * @param fileName
	// * @return
	// */
	// private static String getHashPath(int val) {
	// return String.valueOf((val / 1000) + 1);
	// }

	// private static String getHashPath(String val) {
	// return getHashPath(Integer.parseInt(val));
	// }
	//
	// private static String computeHash(int key) {
	// return Integer.toString(key / MAX_FILES_IN_FOLDER + 1);
	// }

	/**
	 * 배열에 들어 있는 순서대로 경로를 만든다.
	 * 
	 * 형식은 /..../.../... 와 같다.(가장 앞에 '/' 가 붙고 뒤에 아무것도 안붙는다.)
	 */
	private static String makeFullPath(String[] folders) {
		StringBuilder sb = new StringBuilder();

		for (String folder : folders) {
			if (folder.charAt(0) != '/') {
				sb.append('/');
			}
			sb.append(folder);

			// sb.append(folder);
			// if (folder.charAt(folder.length() - 1) != '/') {
			// sb.append('/');
			// }
		}

		return sb.toString();
	}

//	public static void main(String[] args) {
//		//
//
//		String webPath = PathUtil.create("uploaded", "");
//
//		System.out.println(webPath);
//	}
}
