package com.sun.demo.base.utils;

import com.sun.demo.base.Exception.SystemException;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @date 2018/03
 * @author szy
 * 
 */
public class StringUtils {

	public static final int INDEX_NOT_FOUND = -1;

	private static final String FORMAT_TEXT = "#,###.#################################";

	public static final String SPLIT_CHAR_WHITESPACE = " ";

	public static final String SPLIT_CHAR_FULL_THRASH = "／";

	public static final String SPLIT_CHAR_THRASH = "/";

	public static final String SPLIT_CHAR_UNDERBAR = "_";

	/**
	 * string判空
	 * 
	 * @param input
	 * @return
	 */
	public static String nvl(String input) {
		if (input != null) {
			return input.trim();
		}

		return "";
	}

	/**
	 * object转String
	 * 
	 * @param input
	 * @return
	 */
	public static String objectToString(Object input) {
		return (input == null) ? null : String.valueOf(input);
	}

	/**
	 * 字符串比较
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean isEqual(String s1, String s2) {
		if (s1 == null) {
			s1 = "";
		}
		if (s2 == null) {
			s2 = "";
		}

		return (s1.equals(s2));
	}

	/**
	 * Checks if a CharSequence is empty ("") or null. StringUtils.isEmpty(null)
	 * = true StringUtils.isEmpty("") = true StringUtils.isEmpty(" ") = false
	 * StringUtils.isEmpty("bob") = false StringUtils.isEmpty("  bob  ") = false
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return if the CharSequence is empty or null
	 */
	public static boolean isEmpty(String cs) {
		return cs == null || cs.length() == 0;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty ("") and not null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotEmpty(null)      = false
	 * StringUtils.isNotEmpty("")        = false
	 * StringUtils.isNotEmpty(" ")       = true
	 * StringUtils.isNotEmpty("bob")     = true
	 * StringUtils.isNotEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return if the CharSequence is not empty and not null
	 */
	public static boolean isNotEmpty(String cs) {
		return !StringUtils.isEmpty(cs);
	}

	/**
	 * <p>
	 * Checks if a CharSequence is whitespace, empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return if the CharSequence is null, empty or whitespace
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty (""), not null and not whitespace
	 * only.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return if the CharSequence is not empty and not null and not whitespace
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return !StringUtils.isBlank(cs);
	}

	/**
	 * hump转换
	 * 
	 * @param s
	 * @return
	 */
	public static String decamelize(String s) {
		if (s == null) {
			return null;
		}
		if (s.length() == 1) {
			return s.toUpperCase();
		}
		StringBuffer buf = new StringBuffer(40);
		int pos = 0;
		for (int i = 1; i < s.length(); ++i) {
			if (Character.isUpperCase(s.charAt(i))) {
				if (buf.length() != 0) {
					buf.append('_');
				}
				buf.append(s.substring(pos, i).toUpperCase());
				pos = i;
			}
		}
		if (buf.length() != 0) {
			buf.append('_');
		}
		buf.append(s.substring(pos, s.length()).toUpperCase());
		return buf.toString();
	}

	// Replacing
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Replaces a String with another String inside a larger String, once.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.replaceOnce(null, *, *)        = null
	 * StringUtils.replaceOnce("", *, *)          = ""
	 * StringUtils.replaceOnce("any", null, *)    = "any"
	 * StringUtils.replaceOnce("any", *, null)    = "any"
	 * StringUtils.replaceOnce("any", "", *)      = "any"
	 * StringUtils.replaceOnce("aba", "a", null)  = "aba"
	 * StringUtils.replaceOnce("aba", "a", "")    = "ba"
	 * StringUtils.replaceOnce("aba", "a", "z")   = "zba"
	 * </pre>
	 * 
	 * @see #replace(String text, String searchString, String replacement, int
	 *      max)
	 * @param text
	 *            text to search and replace in, may be null
	 * @param searchString
	 *            the String to search for, may be null
	 * @param replacement
	 *            the String to replace with, may be null
	 * @return the text with any replacements processed,
	 */
	public static String replaceOnce(String text, String searchString,
			String replacement) {
		return replace(text, searchString, replacement, 1);
	}

	/**
	 * <p>
	 * Replaces all occurrences of a String within another String.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.replace(null, *, *)        = null
	 * StringUtils.replace("", *, *)          = ""
	 * StringUtils.replace("any", null, *)    = "any"
	 * StringUtils.replace("any", *, null)    = "any"
	 * StringUtils.replace("any", "", *)      = "any"
	 * StringUtils.replace("aba", "a", null)  = "aba"
	 * StringUtils.replace("aba", "a", "")    = "b"
	 * StringUtils.replace("aba", "a", "z")   = "zbz"
	 * </pre>
	 * 
	 * @see #replace(String text, String searchString, String replacement, int
	 *      max)
	 * @param text
	 *            text to search and replace in, may be null
	 * @param searchString
	 *            the String to search for, may be null
	 * @param replacement
	 *            the String to replace it with, may be null
	 * @return the text with any replacements processed,
	 */
	public static String replace(String text, String searchString,
			String replacement) {
		return replace(text, searchString, replacement, -1);
	}

	/**
	 * <p>
	 * Replaces a String with another String inside a larger String, for the
	 * first {@code max} values of the search String.
	 * </p>
	 * 
	 * <pre>
	 * StringUtils.replace(null, *, *, *)         = null
	 * StringUtils.replace("", *, *, *)           = ""
	 * StringUtils.replace("any", null, *, *)     = "any"
	 * StringUtils.replace("any", *, null, *)     = "any"
	 * StringUtils.replace("any", "", *, *)       = "any"
	 * StringUtils.replace("any", *, *, 0)        = "any"
	 * StringUtils.replace("abaa", "a", null, -1) = "abaa"
	 * StringUtils.replace("abaa", "a", "", -1)   = "b"
	 * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
	 * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
	 * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
	 * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
	 * </pre>
	 * 
	 * @param text
	 *            text to search and replace in, may be null
	 * @param searchString
	 *            the String to search for, may be null
	 * @param replacement
	 *            the String to replace it with, may be null
	 * @param max
	 *            maximum number of values to replace, or {@code -1} if no
	 *            maximum
	 * @return the text with any replacements processed,
	 */
	public static String replace(String text, String searchString,
			String replacement, int max) {
		if (isEmpty(text) || isEmpty(searchString) || replacement == null
				|| max == 0) {
			return text;
		}
		int start = 0;
		int end = text.indexOf(searchString, start);
		if (end == INDEX_NOT_FOUND) {
			return text;
		}
		int replLength = searchString.length();
		int increase = replacement.length() - replLength;
		increase = increase < 0 ? 0 : increase;
		increase *= max < 0 ? 16 : max > 64 ? 64 : max;
		StringBuilder buf = new StringBuilder(text.length() + increase);
		while (end != INDEX_NOT_FOUND) {
			buf.append(text.substring(start, end)).append(replacement);
			start = end + replLength;
			if (--max == 0) {
				break;
			}
			end = text.indexOf(searchString, start);
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * 前面加0
	 * 
	 * @param val
	 * @param length
	 * @return
	 */
	public static String addPreZero(Object val, int length) {
		if (val == null) {
			val = "";
		}
		String result = val.toString();
		int strLen = result.length();
		if (strLen < length) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < length - strLen; i++) {
				sb.append('0');
			}
			sb.append(val);
			result = sb.toString();
		} else {
			result = result.substring(0, length);
		}

		return result;
	}

	/**
	 * 文件path的
	 * 
	 * @param filePath1
	 * @param filePath2
	 * @return
	 */
	public static String linkFilePath(String filePath1, String filePath2) {
		String fileSeparator = System.getProperty("file.separator");
		String repFileSeparator = fileSeparator;
		if ("\\".equals(fileSeparator)) {
			repFileSeparator = repFileSeparator + "\\";
		}
		String newFilePath1 = filePath1.replaceAll("[\\\\/]{1}",
				repFileSeparator).replaceAll("[\\\\/]{1}$", "");
		String newFilePath2 = filePath2.replaceAll("[\\\\/]{1}",
				repFileSeparator).replaceAll("^[\\\\/]{1}", "");
		return newFilePath1 + fileSeparator + newFilePath2;
	}

	/**
	 * 文件地址取得
	 * 
	 * @param inputFile
	 * @return
	 */
	public static String getFilePath(String inputFile) {
		String result = inputFile.replaceAll("[\\\\/][^\\\\/]+$", "");
		return result;
	}

	/**
	 * 文件名的取得
	 * 
	 * @param inputFile
	 * @return
	 */
	public static String getFileName(String inputFile) {
		String result = inputFile.replaceAll(".*[\\\\/]", "");
		return result;
	}

	public static String combinePath(String start, String end) {
		if (end == null) {
			return start;
		}
		if (start == null) {
			return end;
		}

		boolean startEnds = start.endsWith("/");
		boolean endStarts = end.startsWith("/");
		if (startEnds ^ endStarts) // one
		{
			return start + end;
		} else if (startEnds & endStarts) // both
		{
			return start + end.substring(1, end.length());
		} else // neither
		{
			return start + '/' + end;
		}
	}

	public static String formatNumber(String number, boolean formatToLong) {
		try {
			return formatNumber(number, formatToLong, false);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * 9999999.99 ----> 9,999,999.99转换
	 * 
	 * @param number
	 * @param formatToLong
	 * @param nullToZero
	 * @return
	 */
	public static String formatNumber(String number, boolean formatToLong,
			boolean nullToZero) {

		try {
			return formatNumber(number, formatToLong, nullToZero, false);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public static String formatNumber(String number, boolean formatToLong,
			boolean nullToZero, boolean zeroToNull) {
		if (number == null || number.trim().equals("")) {
			if (nullToZero) {
				return "0";
			} else {
				return "";
			}
		}
		if (number.trim().equals("0") || number.trim().equals("-0")) {
			if (zeroToNull) {
				return "";
			} else {
				return "0";
			}
		}
		if (!NumberUtils.checkNumberValid(number)) {
			return number.trim();
		}
		String sFormatNumber = "";
		double dbNum;
		NumberFormat defForm = new DecimalFormat(FORMAT_TEXT);
		try {
			dbNum = Double.parseDouble(number);
			if (formatToLong) {
				dbNum = Math.round(dbNum);
			}
			sFormatNumber = defForm.format(dbNum); // 書式�?
		} catch (NumberFormatException numEx) {
			try {
				System.out.println("formatNumber error");
				// 99,9999,9 --> 99,999,999
				Number numNumber = NumberFormat.getNumberInstance().parse(
						number);
				dbNum = Double.parseDouble(String.valueOf(numNumber));
				if (formatToLong) {
					dbNum = Math.round(dbNum);
				}
				sFormatNumber = defForm.format(dbNum);
			} catch (ParseException pEx) {
				throw new SystemException(pEx);
			}
		}
		return convertToValid(sFormatNumber);
	}

	/**
	 * string中-0 -> 0
	 * 
	 * @param number
	 * @return
	 */
	private static String convertToValid(String number) {
		if (number.trim().equals("-0")) {
			return "0";
		} else {
			return number;
		}
	}

	/**
	 * 数组转化为string
	 * 
	 * @param str
	 * @param sep
	 * @return
	 */
	public static String array2String(String[] str, String sep) {
		int num = 0;
		StringBuffer result = new StringBuffer("");

		if (str == null) {
			return "";
		}

		num = str.length;
		for (int i = 0; i < num; i++) {
			if (str[i] != null) {
				result.append(sep);
				result.append(str[i]);
			}
		}

		String resultStr = result.toString();
		if (resultStr.length() > 0) {
			resultStr = resultStr.substring(1);
		}
		return resultStr;
	}

	/**
	 * string转化为list
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> stringToList(String str) {
		List<String> outputList = new ArrayList<String>();
		if (str == null) {
			return outputList;
		}
		if (!"".equals(StringUtils.nvl(str))) {
			if (str.trim().indexOf(",") > 0) {
				String[] arry = str.trim().split(",");
				for (int i = 0; i < arry.length; i++) {
					outputList.add(arry[i]);
				}
			} else if (str.length() > 0) {
				outputList.add(str);
			}
		}
		return outputList;
	}

	/**
	 * list转化为string
	 * 
	 * @param inputList
	 * @return
	 */
	public static String listToString(List<String> inputList) {
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < inputList.size(); i++) {
			output.append(inputList.get(i));
			output.append(",");
		}
		if (output.length() > 0) {
			output.delete(output.length() - 1, output.length());
		}
		return output.toString();
	}

	/**
	 * 半角空格转换为全角空格
	 * 
	 * @param s
	 * @return
	 */
	public static String hankakuAlphabetToZenkakuAlphabet(String s) {
		if (s == null)
			return null;
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z') {
				sb.setCharAt(i, (char) (c - 'a' + 'ａ'));
			} else if (c >= 'A' && c <= 'Z') {
				sb.setCharAt(i, (char) (c - 'A' + 'Ａ'));
			}
		}
		return sb.toString();
	}

	/**
	 * 半角数字转化为全角数字
	 * 
	 * @param s
	 * @return
	 */
	public static String hankakuNumberToZenkakuNumber(String s) {
		if (s == null)
			return null;
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				sb.setCharAt(i, (char) (c - '0' + '０'));
			}
		}
		return sb.toString();
	}

	/**
	 * 全角数字转化为半角数字
	 * 
	 * @param s
	 * @return
	 */
	public static String zenkakuNumToHankaku(String s) {
		if (s == null)
			return null;
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c >= '０' && c <= '９') {
				sb.setCharAt(i, (char) (c - '０' + '0'));
			}
		}
		return sb.toString();
	}

	/**
	 * 查找字符出现的次数
	 * 
	 * @param str
	 * @param searchStr
	 * @return
	 */
	public static int getStrNum(String str, String searchStr) {
		int count = 0;
		int index = 0;
		if (str != null && StringUtils.isNotEmpty(str)) {
			do {
				index = str.indexOf(searchStr);
				if (index >= 0) {
					count++;
					str = str.substring(index + 1, str.length());
				}
			} while (index != -1);
		}
		return count;
	}

	/**
	 * string匹配文字删除
	 * 
	 * @param arr
	 * @return
	 */
	public static List<String> getStringList(String[] arr) {
		List<String> stringList = new ArrayList<String>();
		for (String str : arr) {
			if (!str.equals("")) {
				Boolean isNew = true;
				for (String str2 : stringList) {
					if (str2.equals(str)) {
						isNew = false;
					}
				}
				if (isNew) {
					stringList.add(str);
				}
			}
		}
		return stringList;
	}

	/**
	 * tablename大小写的转换
	 * 
	 * @param str
	 * @param isUpperOne
	 * @return
	 */
	public static String tableName(String str, boolean isUpperOne) {
		str = str.trim();
		str = str.toLowerCase();
		String tableName = "";
		while (str.indexOf("_") != -1) {
			if (str.indexOf("_") == 0) {
				if (str.length() == 1) {
					str = "";
				} else {
					str = str.substring(str.indexOf("_") + 1);
				}
			} else {
				tableName = tableName + str.substring(0, 1).toUpperCase()
						+ str.substring(1, str.indexOf("_"));
				str = str.substring(str.indexOf("_") + 1);
			}
		}
		if (str.length() != 0) {
			if (str.length() == 1) {
				tableName = tableName + str.toUpperCase();
			} else {
				tableName = tableName + str.substring(0, 1).toUpperCase()
						+ str.substring(1, str.length());
			}
		}
		if (!isUpperOne) {
			if (tableName.length() == 0) {
				tableName = "";
			} else if (tableName.length() == 1) {
				tableName = tableName.toLowerCase();
			} else {
				tableName = tableName.substring(0, 1).toLowerCase()
						+ tableName.substring(1, tableName.length());
			}
		}
		return tableName;

	}

	/**
	 * 大文字和小文字的转换
	 * 
	 * @param src
	 * @return
	 */
	public static String convertString(String src) {
		char[] array = src.toCharArray();
		int temp = 0;
		for (int i = 0; i < array.length; i++) {
			temp = (int) array[i];
			if (temp <= 90 && temp >= 65) {
				array[i] = (char) (temp + 32);
			}
		}
		return String.valueOf(array);
	}

	/**
	 * 文件size的转换
	 * 
	 * @param size
	 * @return
	 */
	public static String formatFileSize(long size) {
		int kb = 1024;
		int mb = kb * kb;

		if (size < 0) {
			size = 0;
		}

		DecimalFormat df = new DecimalFormat("#,###.##");

		if (size < kb) {
			return size + "B";
		} else if (size < mb) {
			return df.format(size * 1.0 / kb) + "KB";
		} else {
			return df.format(size * 1.0 / mb) + "MB";
		}
	}

	/**
	 * 文字列中に指定文字が存在するかチェッ�?
	 * 
	 * @param str
	 * @param strArr
	 * @return
	 */
	public static Boolean strExist(String str, String[] strArr) {
		if (strArr == null || strArr.length == 0) {
			return false;
		}
		for (String str1 : strArr) {
			if (str1.equals(str)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 数字を英大文字に変換
	 * 
	 * @param numeral
	 * @return
	 */
	public static String numeralToAlphabet(final int numeral) {
		int postfix = numeral;
		String alphabet = "";
		do {
			alphabet = (char) (65 + (postfix - 1) % 26) + alphabet;
			postfix = (postfix - 1) / 26;
		} while (postfix > 0);

		return alphabet;
	}

	/**
	 * 英大文字を数字に変換
	 * 
	 * @param alphabet
	 * @return
	 */
	public static int AlphabetToNumeral(final String alphabet) {
		if (StringUtils.isBlank(alphabet)) {
			return 0;
		}
		int numeral = 0;
		for (byte b : alphabet.getBytes()) {
			numeral = numeral * 26 + (b - 64);
		}

		return numeral;
	}

	/**
	 * 数字を英小文字に変換
	 * 
	 * @param numeral
	 * @return
	 */
	public static String numToSmallAlpha(final int numeral) {
		int postfix = numeral;
		String alphabet = "";
		do {
			alphabet = (char) (97 + (postfix - 1) % 26) + alphabet;
			postfix = (postfix - 1) / 26;
		} while (postfix > 0);

		return alphabet;
	}

	/**
	 * 英小文字を数字に変換
	 * 
	 * @param alphabet
	 * @return
	 */
	public static int smallAlphaToNum(final String alphabet) {
		if (StringUtils.isBlank(alphabet)) {
			return 0;
		}
		int numeral = 0;
		for (byte b : alphabet.getBytes()) {
			numeral = numeral * 26 + (b - 96);
		}

		return numeral;
	}

	/**
	 * xml转换为text
	 * 
	 * @param xmlText
	 * @return
	 */
	public static String forMatSoapText(String xmlText) {
		String prefixStart = "<CommonResInfo";
		String prefixEnd = "ResHeader";
		if (xmlText == null) {
			return "";
		} else {
			String dest = "";
			String tempStr = xmlText.substring(xmlText.indexOf(prefixStart),
					xmlText.lastIndexOf(prefixEnd) + prefixEnd.length() + 1);
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(tempStr);
			dest = m.replaceAll("");
			if (dest.length() > 900) {
				return dest.substring(0, 900);
			} else {
				return dest;
			}
		}
	}

	/**
	 * 文件名特殊文件的check
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkStrCharacter(String str) {
		String regEx = "[\\\\/:*?\"<>|]";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

	/**
	 * tsv文件生成
	 * 
	 * @param params
	 * @return
	 */
	public static String tsvTransfer(String params) {
		if (StringUtils.isNotEmpty(params)) {
			params = StringUtils.replace(StringUtils.replace(
					StringUtils.replace(params, "\\\\t", ""), "\\\\n", ""),
					"\\\\r", "");
			params = StringUtils.replace(StringUtils.replace(
					StringUtils.replace(params, "\\t", ""), "\\n", ""), "\\r",
					"");
			params = StringUtils.replace(StringUtils.replace(
					StringUtils.replace(params, "\t", ""), "\n", ""), "\r", "");
		}
		return params;
	}

	/**
	 * 相同内容删除
	 * 
	 * @param str
	 * @return
	 */
	public static String clearSameStr(String str) {
		String message = "";

		if (StringUtils.isBlank(str)) {
			return message;
		}

		String[] bb = StringUtils.replace(str, "\r", "").split("\n");
		List<String> list = Arrays.asList(bb);
		HashSet<String> set = new HashSet<String>(list);
		String[] rid = (String[]) set.toArray(new String[0]);
		for (String s : rid) {
			message = message + s + "\n";
		}
		return message;
	}
	
	/**
	 * 字符串转Integer对象
	 * 
	 * @param number
	 * @return
	 */
	public static Integer strToInteger(String number, Integer defaultVal) {
		try {
			Integer _number = Integer.parseInt(number);
			return _number;
		} catch (Exception e) {
			return defaultVal;
		}
	}


	/**
	 * 字符串转int
	 * 
	 * @param number
	 * @return
	 */
	public static Long strToLong(String number, Long defaultVal) {
		try {
			long _number = Long.parseLong(number);
			return Long.valueOf(_number);
		} catch (Exception e) {
			return defaultVal;
		}
	}

	/**
	 * 生成随机数字和字母, 
	 * @param length
	 * @return
	 */
    public static String getStringRandom(int length) {  
          
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }


	/**
	 * 生成随机数字,
	 * @param length
	 * @return
	 */
	public static String getIntRandom(int length) {

		String val = "";
		Random random = new Random();

		//参数length，表示生成几位随机数
		for(int i = 0; i < length; i++) {
				val += String.valueOf(random.nextInt(10));
		}
		return val;
	}

    /**
     * 返回截取指定长度字节数后的字符串,多余部分用“...”代替
     * 
     * @作者 jiyanle
     * @日期 2014-7-6
     * @param strs
     * @param length
     * @return
     */
    public static String cutStr(String strs, int length) {
        int sum = 0;
        String finalStr = "";
        if (null == strs || strs.getBytes().length <= length) {
            finalStr = (strs==null?"":strs);
        } else {
            for (int i = 0; i < strs.length(); i++) {
                String str = strs.substring(i, i + 1);
                // 累加单个字符字节数
                sum += str.getBytes().length;
                if (sum > length) {
                    finalStr = strs.substring(0, i) + "...";
                    break;
                }
            }
        }
        return finalStr;
    }
}
