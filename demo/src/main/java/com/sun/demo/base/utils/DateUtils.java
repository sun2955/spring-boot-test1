package com.sun.demo.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.sun.demo.base.Exception.SystemException;

/**
 * @date 2018/03
 * @author szy
 * 
 */
public class DateUtils {

	/**
	 * Date Style
	 */
	public static final String DATESTYLE = "yyyyMMddHHmmss";

	/**
	 * Date Style for translation
	 */
	public static final String DATESTYLETRANS = "yyMMddHHmmss";

	/**
	 * /** Date Style for Extention
	 */
	public static final String DATESTYLE_EX = "yyyy-MM-dd_HH-mm-ss";

	/**
	 * Date Style for Year & Month
	 */
	public static final String DATESTYLE_YEAR_MONTH = "yyyyMM";

	/**
	 * Date Style for Year & Month
	 */
	public static final String DATESTYLE_YEAR_MONTH_EX = "yyyy/MM";

	/**
	 * Date Style for Short
	 */
	public static final String DATESTYLE_SHORT = "yyyyMMdd";

	/**
	 * Date Style for Short
	 */
	public static final String DATESTYLE_SHORT_YEAR = "yy/MM/dd";

	/**
	 * Date Style for detail
	 */
	public static final String DATESTYLE_DETAIL = "yyyyMMddHHmmssSSS";

	/**
	 * Date Style for material file upload
	 */
	public static final String DATESTYLE_MATERIAL_FILE_UPLOAD = "yyyyMMdd_HHmmss_SSS";

	/**
	 * Date Style for detail
	 */
	public static final String DATESTYLE_TSV_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Date Style for detail
	 */
	public static final String DATESTYLE_TSV_FORMAT_SHORT = "yyyy-MM-dd";

	/**
	 * Date Style for detail
	 */
	public static final String DATESTYLE_COMMON = "yyyy/MM/dd";

	/**
	 * Date Style for detail
	 */
	public static final String DATESTYLE_SHORT_TIME = "yyyy/MM/dd HH:mm";

	/**
	 * Date Style for detail
	 */
	public static final String DATESTYLE_LONG_TIME = "yyyy/MM/dd HH:mm:ss";

	public static final String CUR_DATE_SQL = "getDbCurDate";

	public static final String DATESTYLE_DTEAIL = " HH:mm:ss";

	/**
	 * 取当前时间
	 * 
	 * @return
	 */
	public static Date getCurDate() {
		Date date = new Date();
		return date;
	}

	/**
	 * 根据时间样式输出时间字符串
	 * 
	 * @param style
	 *            时间
	 * @return
	 */
	public static String getCurrentDate(String style) {
		SimpleDateFormat dFmt = new SimpleDateFormat(style);
		return dFmt.format(new Date());
	}

	/**
	 * 增加月数
	 * 
	 * @param fromDate
	 * @param style
	 * @param addMonth
	 * @return
	 */
	public static String addMonth(Date fromDate, String style, int addMonth) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal.add(Calendar.MONTH, addMonth);
		return (new SimpleDateFormat(style)).format(cal.getTime());
	}

	/**
	 * 增加年
	 * 
	 * @param fromDate
	 * @param style
	 * @param addDate
	 * @return
	 */
	public static String addDate(Date fromDate, String style, int addDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal.add(Calendar.DATE, addDate);
		return (new SimpleDateFormat(style)).format(cal.getTime());
	}

	/**
	 * 增加小时
	 * 
	 * @param fromDate
	 * @param addHour
	 * @return
	 */
	public static Date addHour(Date fromDate, int addHour) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fromDate);
		cal.add(Calendar.HOUR, addHour);
		return cal.getTime();
	}

	/**
	 * 时间字符串转为时间类型
	 * 
	 * @param date
	 * @param style
	 * @return
	 */
	public static Date stringToDate(String date, String style) {
		Date da = null;
		if (!StringUtils.isEmpty(date)) {
			try {
				da = new SimpleDateFormat(style).parse(date);
			} catch (ParseException pe) {
				throw new SystemException("date=" + date + " style=" + style,
						pe);
			}
		}
		return da;
	}

	/**
	 * date 转String
	 * 
	 * @param date
	 * @param style
	 * @return
	 */
	public static String dateToString(Date date, String style) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dFmt = new SimpleDateFormat(style);
		return dFmt.format(date);
	}

	/**
	 * 月末日的取得
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthLastDay(String date) {
		// check input data
		if (date == null) {
			return -1;
		}
		date = date.trim();
		if ("".equals(date)) {
			return -1;
		}

		if (date.length() != 6 && date.length() != 8) {
			return -1;
		}

		try {
			// check if input string consist of number
			if (!NumberUtils.checkIntNumberValid(date)) {
				return -1;
			}

			// check month
			int m = Integer.parseInt(date.substring(4, 6));
			if (m < 1 || m > 12) {
				return -1;
			}

			// check year
			int y = Integer.parseInt(date.substring(0, 4));
			if (y <= 0) {
				return -1;
			}

			// 月末日を取得する
			int k = 0;
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(y, m - 1, 1);
			k = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			if (k < 1) {
				return -1;
			} else {
				return k;
			}
		} catch (Exception e) {
			System.out.println("getMonthLastDay error");
			return -1;
		}
	}

	/**
	 * 
	 * @precondition 日付比較 YYYY/MM/dd
	 * @param DATE1
	 * @param DATE2
	 * @param format
	 * @throws ParseException
	 */

	public static int compareDate(String DATE1, String DATE2, String format)
			throws Exception {

		DateFormat df = new SimpleDateFormat(format);

		Date dt1 = df.parse(DATE1);

		Date dt2 = df.parse(DATE2);

		if (dt1.getTime() > dt2.getTime()) {

			// dt1 比dt2早
			return 1;

		} else if (dt1.getTime() < dt2.getTime()) {

			// dt1はdt2より遅い;

			return -1;

		} else {

			return 0;

		}

	}

	/**
	 * UTC的时间获得
	 * 
	 * @param type
	 * @return
	 */
	public static String getUtcTime(String type) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		DateFormat df = new SimpleDateFormat(type);
		df.setTimeZone(cal.getTimeZone());
		String timestamp = df.format(cal.getTime());
		return timestamp;
	}

	/**
	 * 获得时间戳
	 * 
	 * @param timeZone
	 * @param type
	 * @return
	 */

	public static String getDstTime(String timeZone, String type) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
		DateFormat df = new SimpleDateFormat(type);
		df.setTimeZone(cal.getTimeZone());
		String timestamp = df.format(cal.getTime());
		return timestamp;
	}

}
