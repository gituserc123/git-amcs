package com.aier.cloud.biz.ui.biz.adverse.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Title: JudgmentUtil
 * 
 * 
 * @Description: 判断工具类
 * @author lic
 * @date 2018年3月28日
 */
public class JudgmentUtil {

	private static int idCardLen = 18;

	private static int barCodeLen = 10;

	private static int patientIdLen = 19;

	private static int[] visitCardLen = { 8, 9, 12, 14, 16 };

	public static boolean isVisitCard(String str) {
		if (StringUtils.isNoneBlank(str) && !isContainChinese(str)) {
			for (int i = 0; i < visitCardLen.length; i++) {
				if (visitCardLen[i] == str.length()) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isPatientId(String str) {
		if (StringUtils.isNoneBlank(str) && !isContainChinese(str) && StringUtils.isNumeric(str)) {
			if (patientIdLen == str.length()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isBarCode(String str) {
		if (StringUtils.isNoneBlank(str) && !isContainChinese(str)) {
			if (barCodeLen == str.length()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isIdCard(String str) {
		if (StringUtils.isNoneBlank(str) && !isContainChinese(str)) {
			if (idCardLen == str.length()) {
				boolean existed = isNumeric(str) || (isNumeric(StringUtils.substringBeforeLast(str.toLowerCase(), "x"))
						&& str.toLowerCase().endsWith("x"));
				if (existed) {
					return true;
				}
			}
		}
		return false;
	}

	private static int mobileLen = 11;

	private static String startStr = "1";

	public static boolean isMobile(String str) {
		if (StringUtils.isNoneBlank(str)) {
			if (mobileLen == str.length() && isNumeric(str) && str.startsWith(startStr)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串中是否存在汉字
	 * @param str
	 * @return
	 */
	public static boolean isContainChinese(String str) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static boolean isEnglish(String str) {
		return str.matches("^[a-zA-Z]*");
	}

	// public static void main(String[] args) {
	// System.out.println(StringUtils.substringBeforeLast("00000000000000000X",
	// "x"));
	// System.out.println(isNumeric(StringUtils.substringBeforeLast("00000000000000000X",
	// "x")));
	// System.out.println("00000000000000000X".toLowerCase().endsWith("x"));
	// System.out.println(isIdCard("00000000000000000X"));
	// System.out.println(isIdCard("030304198308161551"));
	// System.out.println(isNumeric("01101013123213"));
	// System.out.println(isIdCard("11010220000101347x"));
	// System.out.println(isIdCard("110102200001014472"));
	// System.out.println(isMobile("13812345678"));
	// }
}
