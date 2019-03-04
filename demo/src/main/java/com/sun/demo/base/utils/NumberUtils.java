package com.sun.demo.base.utils;

import com.sun.demo.base.Exception.SystemException;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @date 2018/03
 * @author szy
 *
 */
public class NumberUtils {

	protected NumberUtils() {
	}

	/**
	 * 文字判定
	 *
	 * @param number
	 * @return
	 */
	public static boolean checkNumberValid(String number) {
		number = StringUtils.nvl(number);
		if ("".equals(number)) {
			return false;
		}
		boolean bDot = false;
		int nChar;
		for (int i = 0; i < number.length(); i++) {
			nChar = number.charAt(i);
			if (nChar == '-' && number.length() == 1) {
				return false;
			}
			if (nChar == '-' && i == 0) {
				continue;
			} else if (nChar == '-' && i != 0) {
				return false;
			}
			if (nChar > '9') {
				return false;
			}
			if ((nChar < '0') && (nChar != ',') && (nChar != '.')) {
				return false;
			}
			if (nChar == '.') {
				if (!bDot) {
					bDot = true;
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 数字check
	 *
	 * @param number
	 * @return
	 */
	public static boolean checkPositiveNumberValid(String number) {
		number = StringUtils.nvl(number);
		if ("".equals(number)) {
			return false;
		}
		boolean bDot = false;
		int nChar;
		int zeroJudge = 0;
		for (int i = 0; i < number.length(); i++) {
			nChar = number.charAt(i);
			if (nChar == '-') {
				return false;
			}
			if (nChar > '9') {
				return false;
			}
			if ((nChar < '0') && (nChar != '.')) {
				return false;
			}
			if (nChar == '.') {
				if (number.length() == 1) {
					return false;
				}
				if (!bDot) {
					bDot = true;
					continue;
				} else {
					return false;
				}
			}
			if (nChar == '0' || nChar == '.') {
				zeroJudge++;
			}
		}
		if (zeroJudge >= number.length()) {
			return false;
		}
		return true;
	}

	/**
	 * 数字のcheck
	 *
	 * @param
	 *
	 * @return boolean
	 */
	public static boolean checkIntNumberValid(String s) {
		if (s == null || s.length() == 0) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) < '0' || s.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 数字のcheck
	 *
	 * @param s
	 *            数字の文字列
	 * @return boolean
	 */
	public static boolean checkIntValid(String s) {
		if (checkIntNumberValid(s)) {
			if (s.length() > 1 && s.startsWith("0")) {
				return false;
			}

			return true;
		}
		return false;

	}

	/**
	 * String转Int
	 *
	 * @param str
	 *
	 * @return int
	 */
	public static int convertToInt(String str) {
		int result = 0;
		try {
			str = str.trim();
			result = Integer.parseInt(str);
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
		return result;
	}

	/**
	 * Stirng转Integer
	 *
	 * @param munStr
	 *
	 * @return munInt Integer
	 */
	public static Integer convertToInteger(String munStr) {
		Integer munInt = null;
		if (!"".equals(StringUtils.nvl(munStr))) {
			munInt = Integer.valueOf(munStr);
		}
		return munInt;
	}

	/**
	 * Stirng转BigDecimal
	 *
	 * @param munStr
	 *            Strin
	 * @return munBig BigDecimal
	 */

	public static BigDecimal convertToBigDecimal(String munStr) {
		return convertToBigDecimal(munStr, false);
	}

	/**
	 * Stirng转BigDecimal
	 *
	 * @param munStr
	 *            String
	 * @param autoZero
	 *            true = 0
	 * @return munBig BigDecimal
	 */

	public static BigDecimal convertToBigDecimal(String munStr, boolean autoZero) {
		BigDecimal munBig = null;
		if (!"".equals(StringUtils.nvl(munStr))) {
			munBig = new BigDecimal(munStr);
		} else {
			if (autoZero) {
				munBig = new BigDecimal(0);
			}
		}
		return munBig;
	}

	public static String convertLongToStr(Long munStr, boolean autoZero,
			int divideValue) {
		if (munStr == null) {
			if (autoZero)
				return "0";
			else
				return "";
		}
		return String.valueOf(munStr / divideValue);
	}

	/**
	 * StringをLongに変換す�?
	 *
	 * @param str
	 *            文字�?
	 * @return Long Longの文字列 2003/11/07 新規作成 zhanjc
	 */
	public static long convertToLong(String str) {
		long result = 0;
		try {
			str = str.trim();
			result = Long.parseLong(str);
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
		return result;
	}

	/**
	 * String转Long
	 *
	 * @param str
	 * @param autoZero
	 * @return Long Long
	 */
	public static Long convertToLong(String str, boolean autoZero) {
		Long result = null;

		str = StringUtils.nvl(str).trim();
		if ("".equals(str)) {
			result = autoZero ? 0l : null;
		} else {
			try {
				result = Long.parseLong(str);
			} catch (NumberFormatException ex) {
				System.out.println("convertToLong error");
				result = autoZero ? 0l : null;
			}
		}

		return result;
	}

	/**
	 * String转Float
	 *
	 * @param str
	 *
	 * @return Float
	 */
	public static float convertToFloat(String str) {
		float result = 0;
		try {
			str = str.trim();
			result = Float.parseFloat(str);
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
		return result;
	}

	/**
	 * String转Float
	 *
	 * @param size
	 *
	 * @return Float
	 */
	public static int convertMokoSizeToInt(String size) {
		BigDecimal sizeBefore = NumberUtils.convertToBigDecimal(size, true);
		BigDecimal convertSize = sizeBefore.multiply(new BigDecimal(100));
		return convertSize.intValue();

	}

	/**
	 * String转Float
	 *
	 * @param size
	 *
	 * @return Float
	 */
	public static long convertMokoSizeToLong(String size) {
		BigDecimal sizeBefore = NumberUtils.convertToBigDecimal(size, true);
		BigDecimal convertSize = sizeBefore.multiply(new BigDecimal(100));
		return convertSize.longValue();

	}

	/**
	 * StringをFloatに変換す�?
	 *
	 * @param str
	 *            文字�?
	 * @return Float Floatの文字列 2003/11/07 新規作成 zhanjc
	 */
	public static float convertToFloatForNull(String str) {
		if ("".equals(StringUtils.nvl(str))) {
			str = "0";
		}
		float result = 0;
		try {
			str = str.trim();
			result = Float.parseFloat(str);
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
		return result;
	}

	/**
	 * StringをIntegerに変換す�?
	 *
	 * @param str
	 *            �?文字�?
	 * @param autoZero
	 * @return Integer
	 */
	public static Integer convertToInteger(String str, boolean autoZero) {
		Integer result = null;

		str = StringUtils.nvl(str).trim();
		if ("".equals(str)) {
			result = autoZero ? 0 : null;
		} else {
			try {
				result = Integer.parseInt(str);
			} catch (NumberFormatException ex) {
				System.out.println("convertToLong error");
				result = autoZero ? 0 : null;
			}
		}

		return result;
	}

	/**
	 * String转Int
	 *
	 * @param value
	 *
	 * @param flag
	 *
	 * @return int
	 */
	public static int parseInt(String value, boolean flag) {
		if (!flag) {
			if (value == null || "".equals(value.trim())) {
				return 0;
			}
		}

		try {
			return Integer.parseInt(value.trim());
		} catch (NumberFormatException ex) {
			System.out.println("parseInt error");
			if (flag) {
				throw new SystemException(ex);
			} else {
				return 0;
			}
		}
	}

	/**
	 * String转long
	 *
	 * @param value
	 *
	 * @param flag
	 *
	 * @return long
	 */
	public static long parseLong(String value, boolean flag) {
		if (!flag) {
			if (value == null || "".equals(value.trim())) {
				return 0;
			}
		}

		try {
			return Long.parseLong(value.trim());
		} catch (NumberFormatException ex) {
			System.out.println("parseLong error");
			if (flag) {
				throw new SystemException(ex);
			} else {
				return 0;
			}
		}
	}

	/**
	 * if null -> 0 else long
	 *
	 * @param l
	 *
	 * @return long
	 */
	public static long nvl(Long l) {
		if (l == null) {
			return 0L;
		}

		return l.longValue();
	}

	/**
	 * if null -> 0 else Integer
	 *
	 * @param i
	 *
	 * @return int
	 */
	public static int nvl(Integer i) {
		if (i == null) {
			return 0;
		}

		return i.intValue();
	}

	/**
	 * check数字格式
	 *
	 * @param value
	 * @param intLength
	 * @param decimalLength
	 * @return
	 */
	public static boolean checkNumberFormat(String value, int intLength,
			int decimalLength) {
		if (intLength <= 0) {
			return false;
		}
		if (decimalLength < 0) {
			return false;
		}
		String regNumberFomat;
		if (decimalLength == 0) {
			regNumberFomat = "^[0-9]{1," + intLength + "}?$";
		} else {
			regNumberFomat = "^[0-9]{1," + intLength + "}+(\\.[0-9]{1,"
					+ decimalLength + "})?$";
		}
		Pattern p = Pattern.compile(regNumberFomat);
		Matcher m = p.matcher(value);

		return m.matches();
	}

	/**
	 *
	 * <pre>
	 *  9999999.9999999�?-->9999000�?
	 * </pre>
	 *
	 * @param number
	 * @return
	 */
	public static long floorSenYen(double number) {
		return new BigDecimal(number / 1000).setScale(0,
				BigDecimal.ROUND_HALF_UP).longValue() * 1000;
	}

	/**
	 *
	 * @param value
	 * @param intLength
	 * @param decimalLength
	 * @return
	 */
	public static boolean checkNegativeNumberFormat(String value,
			int intLength, int decimalLength) {
		if (intLength <= 0) {
			return false;
		}
		if (decimalLength < 0) {
			return false;
		}
		String regNumberFomat;
		if (decimalLength == 0) {
			regNumberFomat = "^-[0-9]{1," + intLength + "}?$";
		} else {
			regNumberFomat = "^-[0-9]{1," + intLength + "}+(\\.[0-9]{1,"
					+ decimalLength + "})?$";
		}
		Pattern p = Pattern.compile(regNumberFomat);
		Matcher m = p.matcher(value);

		return m.matches();
	}
}
