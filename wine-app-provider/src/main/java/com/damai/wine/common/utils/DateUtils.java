package com.damai.wine.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @ClassName: DateUtils
 * @author yueyp
 */
public class DateUtils {

	private static final long MILLIS_IN_A_SECOND = 1000;

	private static final long SECONDS_IN_A_MINUTE = 60;

	private static final long MINUTES_IN_AN_HOUR = 60;

	private static final long HOURS_IN_A_DAY = 24;

	private static final int DAYS_IN_A_WEEK = 7;

	private static final int MONTHS_IN_A_YEAR = 12;

	public static final String FORMAT_YYYY_MM_DD_HH_MM_SS="yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_YYYY_MM_DD="yyyy-MM-dd";

	public static final String FORMAT_ISO_DATETIME = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String FORMAT_CN_DATE = "yyyy年MM月dd日";
	/**
	 * 定义日期时间格式的中间符号，可以是"-"或"/"或":"。日期默认为"-"时间默认为":"
	 */
	private static final String formatDateSign = "-";

	private static final String formatDandTSign = "/";

	private static final String formatTimeSign = ":";

	// private static final int[] daysInMonth = new int[] { 31, 28, 31, 30, 31,
	// 30, 31, 31, 30, 31, 30, 31 };

	/**
	 *
	 * 最小日期，设定为1000年1月1日
	 */
	public static final Date MIN_DATE = date(1000, 1, 1);

	/**
	 *
	 * 最大日期，设定为8888年1月1日
	 */
	public static final Date MAX_DATE = date(8888, 1, 1);

	/**
	 *
	 * 根据年月日构建日期对象。注意月份是从1开始计数的，即month为1代表1月份。
	 *
	 * @param year
	 *            年
	 *
	 * @param month
	 *            月。注意1代表1月份，依此类推。
	 *
	 * @param date
	 *            日
	 *
	 * @return
	 */
	public static Date date(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, date, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 计算两个日期（不包括时间）之间相差的周年数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getYearDiff(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new InvalidParameterException(
					"date1 and date2 cannot be null!");
		}
		if (date1.after(date2)) {
			throw new InvalidParameterException("date1 cannot be after date2!");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DATE);
		calendar.setTime(date2);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DATE);
		int result = year2 - year1;
		if (month2 < month1) {
			result--;
		} else if (month2 == month1 && day2 < day1) {
			result--;
		}
		return result;
	}

	/**
	 * 计算两个日期（不包括时间）之间相差的整月数
	 *
	 * @param date1
	 *
	 * @param date2
	 *
	 * @return
	 */
	public static int getMonthDiff(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new InvalidParameterException(
					"date1 and date2 cannot be null!");
		}
		if (date1.after(date2)) {
			throw new InvalidParameterException("date1 cannot be after date2!");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DATE);
		calendar.setTime(date2);
		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DATE);
		int months = 0;
		if (day2 >= day1) {
			months = month2 - month1;
		} else {
			months = month2 - month1 - 1;
		}
		return (year2 - year1) * MONTHS_IN_A_YEAR + months;
	}

	/**
	 *
	 * 统计两个日期之间包含的天数。包含date1，但不包含date2
	 *
	 * @param date1
	 *
	 * @param date2
	 *
	 * @return
	 */

	public static int getDayDiff(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new InvalidParameterException(
					"date1 and date2 cannot be null!");
		}
		Date startDate = org.apache.commons.lang3.time.DateUtils.truncate(
				date1, Calendar.DATE);
		Date endDate = org.apache.commons.lang3.time.DateUtils.truncate(date2,
				Calendar.DATE);
		if (startDate.after(endDate)) {
			throw new InvalidParameterException("date1 cannot be after date2!");
		}
		long millSecondsInOneDay = HOURS_IN_A_DAY * MINUTES_IN_AN_HOUR
				* SECONDS_IN_A_MINUTE * MILLIS_IN_A_SECOND;
		return (int) ((endDate.getTime() - startDate.getTime()) / millSecondsInOneDay);

	}

	/**
	 * 计算time2比time1晚多少分钟，忽略日期部分
	 * @param time1
	 * @param time2
	 *
	 * @return
	 */
	public static int getMinuteDiffByTime(Date time1, Date time2) {
		long startMil = 0;
		long endMil = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time1);
		calendar.set(1900, 1, 1);
		startMil = calendar.getTimeInMillis();
		calendar.setTime(time2);
		calendar.set(1900, 1, 1);
		endMil = calendar.getTimeInMillis();
		return (int) ((endMil - startMil) / MILLIS_IN_A_SECOND / SECONDS_IN_A_MINUTE);
	}

	/**
	 * 计算指定日期的前一天
	 * @param date
	 *
	 * @return
	 */

	public static Date getPrevDay(Date date) {

		return org.apache.commons.lang3.time.DateUtils.addDays(date, -1);

	}

	/**
	 * 计算指定日期前一周的日期
	 * @param date
	 * @return
	 */

	public static Date getPrevWeekDay(Date date) {
		return org.apache.commons.lang3.time.DateUtils.addDays(date, -7);
	}

	/**
	 * 计算指定日期前N个月的日期
	 * @param date
	 *
	 * @return
	 */
	public static Date getPrevNMonth(Date date, int mount) {
		return org.apache.commons.lang3.time.DateUtils.addMonths(date, mount);

	}

	/**
	 * 计算指定日期的后一天
	 * @param date
	 * @return
	 */

	public static Date getNextDay(Date date) {
		return org.apache.commons.lang3.time.DateUtils.addDays(date, 1);
	}

	/**
	 * 判断date1是否在date2之后，忽略时间部分
	 * @param date1
	 * @param date2
	 * @return
	 */

	public static boolean isDateAfter(Date date1, Date date2) {
		Date theDate1 = org.apache.commons.lang3.time.DateUtils.truncate(date1,
				Calendar.DATE);
		Date theDate2 = org.apache.commons.lang3.time.DateUtils.truncate(date2,
				Calendar.DATE);
		return theDate1.after(theDate2);

	}

	/**
	 *
	 * 判断date1是否在date2之前，忽略时间部分
	 * @param date1
	 * @param date2
	 * @return
	 */

	public static boolean isDateBefore(Date date1, Date date2) {
		return isDateAfter(date2, date1);
	}

	/**
	 * 判断time1是否在time2之后，忽略日期部分
	 * @param time1
	 * @param time2
	 * @return
	 */

	public static boolean isTimeAfter(Date time1, Date time2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(time1);
		calendar1.set(1900, 1, 1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(time2);
		calendar2.set(1900, 1, 1);
		return calendar1.after(calendar2);
	}

	/**
	 * 判断time1是否在time2之前，忽略日期部分
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean isTimeBefore(Date time1, Date time2) {
		return isTimeAfter(time2, time1);
	}

	/**
	 * 判断两个日期是否同一天（忽略时间部分）
	 * @param date1
	 * @param date2
	 * @return
	 */

	public static boolean isSameDay(Date date1, Date date2) {
		return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
	}

	/**
	 * 判断两个日历天是否同一天（忽略时间部分）
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Calendar date1, Calendar date2) {
		return org.apache.commons.lang3.time.DateUtils.isSameDay(date1, date2);
	}

	/**
	 * 将字符串形式的日期表示解析为日期对象
	 * @param dateString
	 * @return
	 */

	public static Date parseDate(String dateString) {
		if (dateString == null) {
			return null;
		}
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(
					dateString, new String[] { "yyyy-MM-dd", "yyyy-M-d","yyyyMMdd",
							"yyyy-MM-d", "yyyy-M-dd", "yyyy年MM月dd日", "yyyy年MM月", "yyyy/MM/dd" });
		} catch (ParseException e) {
			return null;
		}

	}

	/**
	 * 将字符串形式的时间表示解析为日期时间对象
	 * @param timeString
	 * @return
	 */
	public static Date parseTime(String timeString) {
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(
					timeString, new String[] { "hh:mm:ss", "h:m:s", "hh:mm",
							"h:m" });
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将字符串形式的日期时间表示解析为时间对象
	 * @param timeString
	 *
	 * @return
	 */
	public static Date parseDateTime(String timeString) {
		if(StringUtils.isEmpty(timeString)){
			return null;
		}
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(
					timeString, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyyMMdd HH:mm:ss",
							"yyyy-M-d H:m:s", "yyyy-MM-dd H:m:s",
							"yyyy-M-d HH:mm:ss","yyyy-MM-dd" });
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 将字符串形式的日期时间表示解析为时间对象
	 * @param timeString
	 *
	 * @return
	 */
	public static Date parseDateMinute(String timeString) {
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(
					timeString, new String[] { "yyyy-MM-dd HH:mm", "yyyyMMdd HH:mm",
							"yyyy-M-d H:m", "yyyy-MM-dd H:m",
							"yyyy-M-d HH:mm" });
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 计算两个日期之间包含的星期X的天数。
	 * @param fromDate  起始日期
	 * @param toDate 结束日期
	 * @param dayOfWeek 星期，例如星期三，星期四
	 * @return
	 */
	public static int getWeekDaysBetween(Date fromDate, Date toDate, int dayOfWeek) {
		int result = 0;
		Date firstDate = getFirstWeekdayBetween(fromDate, toDate, dayOfWeek);
		if (firstDate == null) {
			return 0;
		}
		Calendar aDay = Calendar.getInstance();
		aDay.setTime(firstDate);
		while (aDay.getTime().before(toDate)) {
			result++;
			aDay.add(Calendar.DATE, DAYS_IN_A_WEEK);
		}
		return result;
	}

	/**
	 * 获取在两个日期之间的第一个星期X
	 * @param fromDate 起始日期
	 * @param toDate 结束日期
	 * @param dayOfWeek  星期，例如星期三，星期四
	 * @return
	 */
	public static Date getFirstWeekdayBetween(Date fromDate, Date toDate,
											  int dayOfWeek) {
		Calendar aDay = Calendar.getInstance();
		aDay.setTime(fromDate);
		while (aDay.getTime().before(toDate)) {
			if (aDay.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
				return aDay.getTime();
			}
			aDay.add(Calendar.DATE, 1);
		}
		return null;
	}

	/**
	 *
	 * 取得参数year指定的年份的总天数
	 * @param year
	 * @return
	 */
	public static int getDaysInYear(int year) {
		Calendar aDay = Calendar.getInstance();
		aDay.set(year, 1, 1);
		Date from = aDay.getTime();
		aDay.set(year + 1, 1, 1);
		Date to = aDay.getTime();
		return getDayDiff(from, to);
	}

	/**
	 *
	 * 取得指定年月的总天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysInMonth(int year, int month) {
		Calendar aDay = Calendar.getInstance();
		aDay.set(year, month, 1);
		Date from = aDay.getTime();
		if (month == Calendar.DECEMBER) {
			aDay.set(year + 1, Calendar.JANUARY, 1);
		} else {
			aDay.set(year, month + 1, 1);
		}
		Date to = aDay.getTime();
		return getDayDiff(from, to);
	}

	/**
	 * 获得指定日期的年份
	 * @param date
	 * @return
	 */

	public static int getYear(Date date) {

		return getFieldValue(date, Calendar.YEAR);

	}

	/**
	 *
	 * 获得指定日期的月份
	 * @param date
	 *
	 * @return
	 */

	public static int getMonth(Date date) {

		return getFieldValue(date, Calendar.MONTH) + 1;

	}

	/**
	 *
	 * 获得指定日期是当年的第几天
	 * @param date
	 *
	 * @return
	 */

	public static int getDayOfYear(Date date) {

		return getFieldValue(date, Calendar.DAY_OF_YEAR);

	}

	/**
	 *
	 * 获得指定日期是当月的第几天
	 * @param date
	 *
	 * @return
	 */

	public static int getDayOfMonth(Date date) {

		return getFieldValue(date, Calendar.DAY_OF_MONTH);

	}

	/**
	 *
	 * 获得指定日期是当周的第几天
	 *
	 *
	 *
	 * @param date
	 *
	 * @return
	 */

	public static int getDayOfWeek(Date date) {

		return getFieldValue(date, Calendar.DAY_OF_WEEK);

	}

	private static int getFieldValue(Date date, int field) {

		if (date == null) {

			throw new InvalidParameterException("date cannot be null!");

		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(field);

	}

	/**
	 *
	 * 获得指定日期之后一段时期的日期。例如某日期之后3天的日期等。
	 *
	 * @param origDate
	 *            基准日期
	 *
	 * @param amount
	 *            时间数量
	 *
	 * @param timeUnit
	 *            时间单位，如年、月、日等。用Calendar中的常量代表
	 *
	 * @return
	 */

	public static final Date dateAfter(Date origDate, int amount, int timeUnit) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(origDate);

		calendar.add(timeUnit, amount);

		return calendar.getTime();

	}

	/**
	 *
	 * 获得指定日期之前一段时期的日期。例如某日期之前3天的日期等。
	 *
	 * @param origDate
	 *            基准日期
	 *
	 * @param amount
	 *            时间数量
	 *
	 * @param timeUnit
	 *            时间单位，如年、月、日等。用Calendar中的常量代表
	 *
	 * @return
	 */

	public static final Date dateBefore(Date origDate, int amount, int timeUnit) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(origDate);

		calendar.add(timeUnit, -amount);

		return calendar.getTime();

	}

	/**
	 * 获得当前时间之后或者之前一段时间。例如某日期之后3天的日期等。
	 * @param amount
	 *            时间数量
	 * @param timeUnit
	 *            时间单位，如年、月、日等。用Calendar中的常量代表
	 * @return
	 */
	public static final Date nowAfter(int amount, int timeUnit) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(timeUnit, amount);
		return calendar.getTime();
	}

	public static Date parseDate(String value, String pattern)
			throws ParseException {
		if (StringUtils.isBlank(value) || StringUtils.isBlank(pattern)) {
			return null;
		}
		return new SimpleDateFormat(pattern).parse(value);
	}

	public static String formatString(Date date, String pattern) {
		if (date == null || StringUtils.isBlank(pattern)) {
			return null;
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	public static String formatString(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static int getDayOfHour(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	public static Date getArriveTime(Date date, Integer min) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, min);
		return calendar.getTime();
	}

	public static Date getDate(Integer minnute) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, minnute);
		return calendar.getTime();
	}

	/**
	 *
	 *
	 * @Title: getNextDayTimestamp
	 *
	 * @Description: 获取N天后的日期
	 *
	 * @param @param day
	 * @param @return 设定文件
	 *
	 * @return Timestamp 返回类型
	 *
	 * @throws
	 */
	public static Timestamp getNextDayTimestamp(Integer day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return new Timestamp(calendar.getTime().getTime());
	}

	/**
	 *
	 *
	 * @Title: isAfter
	 *
	 * @Description: 判断date1是否晚于date2
	 *
	 * @param @param date1
	 * @param @param date2
	 * @param @return 设定文件
	 *
	 * @return boolean 返回类型
	 *
	 * @throws
	 */
	public static boolean isAfter(Date date1, Date date2) {
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(date1);
		Calendar calendar2 = new GregorianCalendar();
		calendar2.setTime(date2);
		return calendar1.after(calendar2);
	}

	/**
	 * 当前系统时间的Timestamp取得
	 *
	 * @return
	 */
	public static Timestamp getTimestamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	/**
	 * 获得当前系统时间的0时0分0秒000
	 *
	 * @return
	 */
	public static Date getSystemDate() {
		return new Date(System.currentTimeMillis() / 86400000 * 86400000
				- (23 - Calendar.ZONE_OFFSET) * 3600000);
	}

	/**
	 *
	 *
	 * @Title: getDayStart
	 *
	 * @Description: 获取某天的开始时间00:00:00
	 *
	 * @param @param date
	 * @param @return 设定文件
	 *
	 * @return Date 返回类型
	 *
	 * @throws
	 */
	public static Date getDateStart(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 *
	 *
	 * @Title: getDateEnd
	 *
	 * @Description: 获取某个日期时间的结束时间23:59:59
	 *
	 * @param @param date
	 * @param @return 设定文件
	 *
	 * @return Date 返回类型
	 *
	 * @throws
	 */
	public static Date getDateEnd(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取时段名称，暂时是中文，后面可以改成I18N
	 *
	 * @return
	 */
	public static String getTimeBucket() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour >= 7 && hour < 11) {
			return "上午";
		} else if (hour >= 11 && hour < 13) {
			return "中午";
		} else if (hour >= 13 && hour < 18) {
			return "下午";
		} else if (hour >= 18 && hour < 23) {
			return "晚上";
		} else {
			return "午夜";
		}
	}
	/**
	 * @函数名称：dateTo8 @功能描述：10位yyyy/MM/dd,yyyy-MM-dd,yyyy:MM:dd 转换为8位yyyyMMdd
	 * @param date
	 *            要格式化的日期字符串: 10位 yyyy/MM/dd或yyyy-MM-dd或yyyy:MM:dd
	 * @return 返回格式化后的日期
	 */
	public static String dateTo8(String date) {
		if (date == null) {
			return "";
		}
		if (date.trim().length() != 10) {
			return date;
		}
		return date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10);
	}

	/*public static String dateTo8Ext(String date) {
		if (null == date || date.equals("")) {
			return "";
		}
		String datestr[] = DataTypeUtil.spiltStr(date, "-");
		String mon = "";
		String day = "";
		if (datestr[1].trim().length() != 2) {
			mon = "0" + datestr[1];
		} else {
			mon = datestr[1];
		}
		if (datestr[2].trim().length() != 2) {
			day = "0" + datestr[2];
		} else {
			day = datestr[2];
		}

		return datestr[0] + mon + day;
	}*/

	/**
	 * @函数名称：dateTo10 @功能描述：8位yyyyMMdd 转换为yyy/MM/dd,yyyy-MM-dd,yyyy:MM:dd
	 * @param date
	 *            要格式化的日期字符串: 8位yyyyMMdd
	 * @return 返回格式化后的日期
	 */
	public static String dateTo10(String date) {
		if (date == null) {
			return "";
		}
		if (date.trim().length() < 8) {
			return "";
		}
		date = date.replace(formatDateSign, "");
		return date.substring(0, 4) + formatDateSign + date.substring(4, 6) + formatDateSign
				+ date.substring(6, 8);
	}

	public static String dateTo19(String date) {
		int len = date.length();
		if (len != 14) {
			return date;
		}
		return date.substring(0, 4) + formatDateSign + date.substring(4, 6) + formatDateSign
				+ date.substring(6, 8) + formatDandTSign + date.substring(8, 10) + formatTimeSign
				+ date.substring(10, 12) + formatTimeSign + date.substring(12, 14);
	}
	/**
	 * @函数名称：dateTo16
	 * @param date 要格式化的日期字符串: yyyy-mm-dd hh:mi:ss.0
	 * @return 返回格式化后的日期: 16位yyyy-mm-dd hh:mi
	 */
	public static String dateTo16(String date) throws Exception{
		if (date == null) {
			return "";
		}
		if (date.trim().length() < 16) {
			return "";
		}
		return date.substring(0, 16);
	}

	/**
	 * @函数名称：dateTo14 @功能描述：8位yyyyMMdd 转换为yyy/MM/dd,yyyy-MM-dd,yyyy:MM:dd
	 * @param date
	 *            要格式化的日期字符串: 8位yyyyMMdd
	 * @return 返回格式化后的日期
	 */
	public static String dateTo14(String date) {
		if (date == null) {
			return "";
		}
		if (date.trim().length() != 19) {
			return date;
		}
		return date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10)
				+ date.substring(11, 13) + date.substring(14, 16) + date.substring(17);
	}
	/**
	 * @函数名称：dateTimeTo16 @功能描述：8位yyyyMMdd 转换为yyy/MM/dd,yyyy-MM-dd,yyyy:MM:dd
	 * @param date
	 *            要格式化的日期字符串: 8位yyyyMMdd
	 * @return 返回格式化后的日期
	 */
	public static String dateTimeTo16(String date) {
		if (date == null) {
			return "";
		}
		if (date.trim().length() < 16) {
			return date;
		}
		return date.substring(0, 16);
	}
	/**
	 * 时间格式化。 <br>
	 * 8位(HH:mm:ss)或7位(H:mm:ss)的时间转换为6位(HHmmss)或5位(Hmmss) <br>
	 * 时间的分隔字符可以是任意字符，一般为冒号(:)
	 *
	 * @param time
	 *            -要格式化的时间字符串:8位(HH:mm:ss)或7位(H:mm:ss)
	 * @return String - 返回格式化后的时间 <br>
	 *         若time长度不为8或7，即格式不为8位(HH:mm:ss)或7位(H:mm:ss)形式的时间，则直接返回date。 <br>
	 *         若time为null, 则返回""
	 */
	public static String timeTo6(String time) {
		int len = time.length();
		if (len < 7 || len > 8) {
			return "";
		}
		return time.substring(0, len - 6) + time.substring(len - 5, len - 3)
				+ time.substring(len - 2, len);
	}
	/**
	 * 时间格式化。 <br>
	 * 6位(HHmmss)或5位(Hmmss)的时间转换为8位(HH:mm:ss)或7位(H:mm:ss)
	 *
	 * @param time
	 *            -要格式化的时间字符串: 6位(HHmmss)或5位(Hmmss)
	 * @return String - 返回格式化后的时间 <br>
	 *         若time长度不为5或6，即格式不为6位(HHmmss)或5位(Hmmss)形式的时间，则直接返回date。 <br>
	 *         若time为null, 则返回""
	 */
	public static String timeTo8(String time) {
		int len = time.length();
		if (len < 5 || len > 6) {
			return "";
		}
		return time.substring(0, len - 4) + formatTimeSign + time.substring(len - 4, len - 2)
				+ formatTimeSign + time.substring(len - 2, len);
	}
	/**
	 * @函数名称：getCurrentTime
	 * @功能描述：获取当前时间(只带时间)
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat dataFormat = new SimpleDateFormat("HHmmss");
		Date date = new Date();
		String timeString = dataFormat.format(date);
		return timeTo8(timeString);
	}
	/**
	 * @函数名称：getCurrentTime
	 * @功能描述：获取日期时间(只带时间)
	 * @return
	 */
	public static String getTimeToSecond(Date date) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");
		String timeString = dataFormat.format(date);
		return timeString;
	}
	/**
	 * @函数名称：getCurrentTime
	 * @功能描述：获取日期时间(只带时间)
	 * @return
	 */
	public static String getTimeToMinute(Date date) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm");
		String timeString = dataFormat.format(date);
		return timeString;
	}

	/**
	 * @函数名称：getCurrentTime
	 * @功能描述：获取当前时间(只带时间)
	 * @return
	 */
	public static String getCurrentDateTimeStr() {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String timeString = dataFormat.format(date);
		return timeString;
	}

	/**
	 * 日期加天
	 * @param dateTime yyyy-MM-dd HH:mm:ss
	 * @param days
	 * @return
	 */
	public static String getDateTimeAddDaysStr(String dateTime, int days) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
			Date date = sdf.parse(dateTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);// 让日期加
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 日期减days天
	 * @param dateTime yyyy-MM-dd HH:mm:ss
	 * @param days
	 * @return
	 */
	public static String getDateTimeDelDaysStr(String dateTime, int days) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
			Date date = sdf.parse(dateTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - days);// 让日期减
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 时间加x分钟
	 * @param dateTime yyyy-MM-dd HH:mm:ss
	 * @param mins
	 * @return
	 */
	public static String getDateTimeAddMinStr(String dateTime, int mins) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
			Date date = sdf.parse(dateTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, mins);// 让时间加mins分钟
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String getCurrentDateTimeStr1() {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String timeString = dataFormat.format(date);
		return timeString;
	}

	public static String getCurrentDateStr() {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String timeString = dataFormat.format(date);
		return timeString;
	}

	public static String getCurrentDateStr1() {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String timeString = dataFormat.format(date);
		return timeString;
	}

	/**
	 *
	 * 获取当前月份
	 *
	 * @return
	 */
	public static String getCurrentDateMonth() {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMM");
		Date date = new Date();
		String timeString = dataFormat.format(date);
		return timeString;
	}


	public static boolean compareDateTime(String dateTime1, String dateTime2){
		return isAfter(parseDateTime(dateTime1),parseDateTime(dateTime2));
	}

	/**
	 * iso时间格式转换
	 * @param date
	 * @return
	 */
	public static String getISODateTime(Date date){
		return DateFormatUtils.format(date, FORMAT_ISO_DATETIME);
	}

	/**
	 * 转unix时间戳
	 * @param dateString
	 * @return
	 */
	public static String parseUnixTimeStam(String dateString) {
		if (dateString == null) {
			return null;
		}
		try {
			return String.valueOf(parseDateTime(dateString).getTime() / 1000L);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * unix时间戳转系统时间
	 * @param dateString
	 * @return
	 */
	public static String unixTimeToDateTime(String dateString) {
		if (dateString == null) {
			return null;
		}
		try {
			Long timestamp = Long.parseLong(dateString) * 1000L;
			return formatString(new Date(timestamp), FORMAT_YYYY_MM_DD_HH_MM_SS);
		} catch (Exception e) {
			return null;
		}
	}

	public static String addDay(String date,int n){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(format.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.DATE, n);
		String backTime = format.format(calendar.getTime());
		return backTime;
	}

	public static void main(String[] args) throws Exception {
		String a = getCurrentDateTimeStr();
		System.out.println(new Date());
		System.out.println(a);

		Date date = DateUtils.parseDate("2020-11-06");

		System.out.println(date);

		String s = formatString(DateUtils.getDateEnd(date), "yyyy-MM-dd HH:mm:ss");
		System.out.printf(s);


		String ab = "123";
		ab = null;
		if (ab == null){
			System.out.println("true============");
		}

	}
}
