package com.wideka.club.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;

/**
 * 
 * @author
 * 
 */
public final class DateUtil {

	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	public static final String DEFAULT_DATEFULLTIME_FORMAT = "yyyyMMddHHmmss";

	public static final String DEFAULT_DATEFULLDATE_FORMAT = "yyyyMMdd";

	public static final String DEFAULT_YEAR_FORMAT = "yyyy";

	public static final String DEFAULT_MONTH_FORMAT = "MM";

	private static final long MILLISECONDS_A_DAY = 24 * 3600 * 1000;

	private static Logger logger = Logger.getLogger(DateUtil.class);

	/**
	 * 验证日期字符串，有效日期范围1900-1-1到2099-12-31.
	 * 
	 */
	private static final Pattern PATTERN = Pattern
		.compile("(?:(?:19|20)\\d{2})-(?:0?[1-9]|1[0-2])-(?:0?[1-9]|[12][0-9]|3[01])");

	private static final int FOUR = 4;

	private static final int TEN = 10;

	/**
	 * 获取当前年份.
	 * 
	 * @return
	 */
	public static int getYear() {
		return DateTime.now().getYear();
	}

	/**
	 * 获取某日期的年份字符串.
	 * 
	 * @param date
	 * @return 字符串类型的年份
	 */
	public static String getYearString(Date date) {
		return datetime(date, DEFAULT_YEAR_FORMAT);
	}

	/**
	 * 获取某日期的年份数字.
	 * 
	 * @param date
	 * @return 数字类型的年份
	 */
	public static int getYearInteger(Date date) {
		return Integer.parseInt(datetime(date, DEFAULT_YEAR_FORMAT));
	}

	/**
	 * 获取当前月.
	 * 
	 * @return
	 */
	public static int getMonth() {
		return DateTime.now().getMonthOfYear();
	}

	/**
	 * 获取某日期的月份字符串.
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthString(Date date) {
		return datetime(date, DEFAULT_MONTH_FORMAT);
	}

	/**
	 * 获取某日期的月份数字.
	 * 
	 * @param date
	 * @return 数字类型的月份
	 */
	public static int getMonthInteger(Date date) {
		return Integer.parseInt(datetime(date, DEFAULT_MONTH_FORMAT));
	}

	/**
	 * 获取当前日.
	 * 
	 * @return
	 */
	public static int getDate() {
		return DateTime.now().getDayOfMonth();
	}

	/**
	 * 获取当前月的最小日期.
	 * 
	 * @return
	 */
	public static Date getMinDate() {
		return DateTime.now().dayOfMonth().withMinimumValue().toDate();
	}

	/**
	 * 获取当前月的最大日期.
	 * 
	 * @return
	 */
	public static Date getMaxDate() {
		return DateTime.now().dayOfMonth().withMaximumValue().toDate();
	}

	/**
	 * 获取指定月的最小时间.
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMinDateByMonth(Date date) {
		return new DateTime(date).dayOfMonth().withMinimumValue().toDate();
	}

	/**
	 * 获取指定月的最大时间.
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMaxDateByMonth(Date date) {
		return new DateTime(date).dayOfMonth().withMaximumValue().toDate();
	}

	/**
	 * 取得某月的的最后一天.
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfLastMonth(int year, int month) {
		return new DateTime(year, month, 1, 0, 0).dayOfMonth().withMaximumValue().toDate();
	}

	/**
	 * 取得当前月的的最后一天.
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfCurMonth(int year, int month) {
		return new DateTime(year, month, 1, 0, 0).dayOfMonth().withMaximumValue().toDate();
	}

	/**
	 * 取得当前月的的第一天.
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfCurMonth(int year, int month) {
		return new DateTime(year, month, 1, 0, 0).toDate();
	}

	/**
	 * 取得某天所在周的第一天.
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		return new DateTime(date).dayOfWeek().withMinimumValue().toDate();
	}

	/**
	 * 取得某天所在周的最后一天.
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		return new DateTime(date).dayOfWeek().withMaximumValue().toDate();
	}

	/**
	 * 当前时间加上years年.
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYears(Date date, int years) {
		return new DateTime(date).year().addToCopy(years).toDate();
	}

	/**
	 * 当前时间加上days月.
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonths(Date date, int months) {
		return new DateTime(date).monthOfYear().addToCopy(months).toDate();
	}

	/**
	 * 当前时间加上days天.
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		return new DateTime(date).dayOfMonth().addToCopy(days).toDate();
	}

	/**
	 * 当前时间加上minutes分.
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinutes(Date date, int minutes) {
		return new DateTime(date).minuteOfHour().addToCopy(minutes).toDate();
	}

	/**
	 * 当前时间加上seconds秒.
	 * 
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date addSeconds(Date date, int seconds) {
		return new DateTime(date).secondOfMinute().addToCopy(seconds).toDate();
	}

	/**
	 * 获得"yyyy-MM-dd"格式的当前日期字符串.
	 * 
	 * @return
	 */
	public static String getNowDateStr() {
		return getNowDatetimeStr(DEFAULT_DATE_FORMAT);
	}

	/**
	 * 获得"yyyy-MM-dd HH:mm:ss"格式的当前日期时间字符串.
	 * 
	 * @return
	 */
	public static String getNowDatetimeStr() {
		return getNowDatetimeStr(DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 获得"yyyyMMddHHmmss"格式的当前日期时间字符串.
	 * 
	 * @return
	 */
	public static String getNowDateminStr() {
		return getNowDatetimeStr(DEFAULT_DATEFULLTIME_FORMAT);
	}

	/**
	 * 获得当前日期时间字符串.
	 * 
	 * @param pattern
	 *            日期格式
	 * @return 日期时间字符串
	 */
	public static String getNowDatetimeStr(String pattern) {
		return DateTime.now().toString(pattern);
	}

	/**
	 * 返回日期字符串："yyyy-MM-dd HH:mm" 格式.
	 * 
	 * @param date
	 * @return
	 */
	public static String datetime(Date date) {
		return datetime(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 获得指定格式的日期时间字符串.
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String datetime(Date date, String pattern) {
		return new DateTime(date).toString(pattern);
	}

	/**
	 * 将字符串转换为日期型 格式为: yyyy-MM-dd HH:mm:ss.
	 * 
	 * @param date
	 * @return
	 */
	public static Date datetime(String date) {
		return datetime(date, DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 获得指定格式的日期时间字符串.
	 * 
	 * @param 日期字符串
	 * @param pattern
	 * @return
	 */
	public static Date datetime(String date, String pattern) {
		return DateTime.parse(date, DateTimeFormat.forPattern(pattern)).toDate();
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date datetime(int year, int month, int day) {
		return new DateTime(year, month, day, 0, 0).toDate();
	}

	/**
	 * ֻ只取当前时间的日期部分，小时、分、秒等字段归零.
	 */
	public static Date dateOnly(Date date) {
		return new Date(date.getTime() / MILLISECONDS_A_DAY);
	}

	/**
	 * ֻ只取当前时间的日期部分，小时、分、秒等字段归零.
	 */
	public static Date dateOnlyExt(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * ֻ只取当前时间的日期部分，小时、分、秒等字段归零.
	 */
	public static Date dateMinTime(Date date) {
		return dateOnlyExt(date);
	}

	/**
	 * 把类似2007-2-2 7:1:8的时间串变为标准的2007-02-02 07:01:08.
	 * 
	 * @param dateTimeStr
	 *            未校正日期值
	 * @return 日期对象
	 */
	public static String getStandDateTimeStr(String dateTimeStr) {
		if (dateTimeStr == null || "".equals(dateTimeStr)) {
			return "";
		}

		String str = dateTimeStr.replaceAll("\\s+", "|");
		String[] a = str.split("\\|");
		List<String> list = Arrays.asList(a);
		String datetime = "";
		int count = 1;
		for (int i = 0; i < list.size(); i++) {
			String temp = (String) list.get(i);
			StringTokenizer st;
			if (i == 0) {
				st = new StringTokenizer(temp, "-");
			} else {
				st = new StringTokenizer(temp, ":");
			}
			int k = st.countTokens();
			for (int j = 0; j < k; j++) {
				String sttemp = (String) st.nextElement();
				if (count == 1) {
					datetime = sttemp;
				} else {
					if (("0".equals(sttemp)) || ("00".equals(sttemp))) {
						sttemp = "0";
					} else if ((Integer.valueOf(sttemp).intValue()) < TEN) {
						sttemp = sttemp.replaceAll("0", "");
					}
					if (count < FOUR) {
						if ((Integer.valueOf(sttemp).intValue()) < TEN) {
							datetime = datetime + "-0" + sttemp;
						} else {
							datetime = datetime + "-" + sttemp;
						}
					}
					if (count == FOUR) {
						if ((Integer.valueOf(sttemp).intValue()) < TEN) {
							datetime = datetime + " 0" + sttemp;
						} else {
							datetime = datetime + " " + sttemp;
						}
					}
					if (count > FOUR) {
						if ((Integer.valueOf(sttemp).intValue()) < TEN) {
							datetime = datetime + ":0" + sttemp;
						} else {
							datetime = datetime + ":" + sttemp;
						}
					}
				}
				count++;
			}
		}

		try {
			getDateFromStr(datetime);
			return datetime;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 把标准的2007-02-02 07:01:08格式转换成日期对象.
	 * 
	 * @param datetime
	 *            日期,标准的2007-02-02 07:01:08格式
	 * @return 日期对象
	 */
	@SuppressWarnings("deprecation")
	public static Date getDateFromStr(String datetime) {
		if (datetime == null || "".equals(datetime)) {
			return new Date();
		}

		String nyr = datetime.trim();

		if (nyr.indexOf(' ') > 0) {
			nyr = nyr.substring(0, nyr.indexOf(' '));
		} else {
			nyr = nyr.substring(0, nyr.length());
		}

		StringTokenizer st = new StringTokenizer(nyr, "-");
		Date date = new Date();
		String temp = "";
		int count = st.countTokens();
		for (int i = 0; i < count; i++) {
			temp = (String) st.nextElement();
			if (i == 0) {
				date.setYear(Integer.parseInt(temp) - 1900);
			}
			if (i == 1) {
				date.setMonth(Integer.parseInt(temp) - 1);
			}
			if (i == 2) {
				date.setDate(Integer.parseInt(temp));
			}
		}

		if (datetime.length() > TEN) {
			String sfm = datetime.substring(11, 19);
			StringTokenizer st2 = new StringTokenizer(sfm, ":");
			count = st2.countTokens();
			for (int i = 0; i < count; i++) {
				temp = (String) st2.nextElement();
				if (i == 0) {
					date.setHours(Integer.parseInt(temp));
				}
				if (i == 1) {
					date.setMinutes(Integer.parseInt(temp));
				}
				if (i == 2) {
					date.setSeconds(Integer.parseInt(temp));
				}
			}
		}
		return date;
	}

	/**
	 * 返回两个日期相差天数.
	 * 
	 * @param startDate
	 *            起始日期对象
	 * @param endDate
	 *            截止日期对象
	 * @return
	 */
	public static int getQuot(Date startDate, Date endDate) {
		return Days.daysBetween(new DateTime(startDate), new DateTime(endDate)).getDays();
	}

	/**
	 * 返回两个日期相差的小时.
	 * 
	 * @param startDateStr
	 * @param endDateStr
	 * @param format
	 * @return
	 */
	public static int getQuotHours(Date startDate, Date endDate) {
		return Hours.hoursBetween(new DateTime(startDate), new DateTime(endDate)).getHours();
	}

	/**
	 * 返回两个日期相差的秒.
	 * 
	 * @param startDateStr
	 * @param endDateStr
	 * @param format
	 * @return
	 */
	public static int getQuotSeconds(Date startDate, Date endDate) {
		return Seconds.secondsBetween(new DateTime(startDate), new DateTime(endDate)).getSeconds();
	}

	/**
	 * 返回两个日期相差天数.
	 * 
	 * @param startDate
	 *            起始日期字符串
	 * @param endDate
	 *            截止期字符串
	 * @param pattern
	 *            时间格式
	 * @return
	 */
	public static int getQuot(String startDate, String endDate, String pattern) {
		return Days.daysBetween(DateTime.parse(startDate, DateTimeFormat.forPattern(pattern)),
			DateTime.parse(endDate, DateTimeFormat.forPattern(pattern))).getDays();
	}

	/**
	 * 返回 yyyy-MM-dd'T'HH:mm:ss.
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return
	 */
	public static String getUTCDateTime(String dateTime, String pattern) {
		if (StringUtils.isBlank(dateTime)) {
			return null;
		}

		try {
			DateFormat format1 = new SimpleDateFormat(pattern);
			Date date = format1.parse(dateTime);

			DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			format2.setTimeZone(TimeZone.getTimeZone("GMT+0"));

			return format2.format(date);
		} catch (ParseException e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * 返回 yyyy-MM-dd HH:mm:ss.
	 * 
	 * @param dateTime
	 * @return
	 */
	public static Date getGMTDateTime(String dateTime) {
		if (StringUtils.isBlank(dateTime)) {
			return null;
		}

		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			format.setTimeZone(TimeZone.getTimeZone("UTC+0"));

			return datetime(datetime(format.parse(dateTime), DEFAULT_DATETIME_FORMAT));
		} catch (ParseException e) {
			logger.error(e);
		}

		return null;
	}

	/**
	 * 取当前的时间戳，在页面上保证URL唯一，防止缓存.
	 * 
	 * @return
	 */
	public static long getDtSeq() {
		return System.currentTimeMillis();
	}

	/**
	 * 判断是否在参数日期的最大值和最小值之间.
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isBetween(Date min, Date compare) {
		Boolean ret = false;
		Date minDate = DateUtil.dateOnlyExt(min);
		Date maxDate = DateUtil.dateOnlyExt(DateUtil.addDays(min, 1));
		if (compare.after(minDate) && compare.before(maxDate)) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 获取本月/上月/本季度的月初和月末.
	 * 
	 * @param monthRange
	 *            取值范围{cm:本月，pm:上月，sm:本季度}
	 * @return Map{firstDay:yyyy-MM-dd, lastDay:yyyy-MM-dd}
	 */
	public static Map<String, String> getFLDayMap(String monthRange) {
		return getFLDayMap(monthRange, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 获取本月/上月/本季度的月初和月末.
	 * 
	 * @param monthRange
	 *            取值范围{cm:本月，pm:上月，sm:本季度}
	 * @param pattern
	 * @return Map{firstDay:yyyy-MM-dd, lastDay:yyyy-MM-dd}
	 */
	public static Map<String, String> getFLDayMap(String monthRange, String pattern) {
		Map<String, String> rs = new LinkedHashMap<String, String>();

		SimpleDateFormat df = new SimpleDateFormat(pattern);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());

		String range = monthRange;

		if (StringUtils.isBlank(range)) {
			range = "cm";
		}

		if (!"sm".equals(range)) {
			if ("pm".equals(range)) {
				calendar.add(Calendar.MONTH, -1);
			}

			calendar.set(Calendar.DAY_OF_MONTH, 1);
			rs.put("firstDay", df.format(calendar.getTime()));

			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			rs.put("lastDay", df.format(calendar.getTime()));

			return rs;
		}

		/*
		 * 本季度的月初和月末
		 */
		int[][] seasons = { { 2, 4 }, { 5, 7 }, { 8, 10 }, { 11, 1 } };
		int cm = calendar.get(Calendar.MONTH) + 1;

		for (int[] im : seasons) {
			if (cm >= im[0] && cm <= im[1]) {
				calendar.set(Calendar.MONTH, im[0] - 1);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				rs.put("firstDay", df.format(calendar.getTime()));

				calendar.set(Calendar.MONTH, im[1] - 1);
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				rs.put("lastDay", df.format(calendar.getTime()));

				break;
			}
		}

		return rs;
	}

	/**
	 * 验证日期是否有效，有效日期范围1900-1-1到2099-12-31.
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isValidDate(String date) {
		if (StringUtils.isBlank(date)) {
			return false;
		}
		return PATTERN.matcher(date).matches();
	}

	/**
	 * 验证日期是否有效，有效日期范围1900-1-1到2099-12-31.
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isValidDate(Date date) {
		if (date == null) {
			return false;
		}
		return PATTERN.matcher(datetime(date, DEFAULT_DATE_FORMAT)).matches();
	}

}
