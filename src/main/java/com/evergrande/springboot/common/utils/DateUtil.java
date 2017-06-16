package com.evergrande.springboot.common.utils;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * 日期工具类
 * @author CuiCan
 *
 */
public abstract class DateUtil {
	
	/** yyyy-MM-dd */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	/** yyyyMMdd */
	public static final String DATE_FORMAT = "yyyyMMdd";
	/** HH:mm:ss */
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	/** yyyy-MM-dd HH:mm:ss */
	public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/** yyyy年MM月dd日 */
	public static final String CN_DATE_FORMAT = "yyyy年MM月dd日";


	/**
	 * 获取随机日期
	 *
	 * @param beginDate
	 *            起始日期，格式为：yyyy-MM-dd
	 * @param endDate
	 *            结束日期，格式为：yyyy-MM-dd
	 * @return
	 */
	public static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);// 构造开始日期
			Date end = format.parse(endDate);// 构造结束日期
			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}



	/**
	 * 尝试把一个String按照指定的多个pattern进行转换,转换成功返回结果,失败返回null,如果值为空直接返回null
	 * @param value 需要转换为日期的字符串
	 * @param patterns 日期pattern,多个以[,]分割
	 * @return
	 */
	public static Date tryStr2Date(String value, String patterns) {
		return tryStr2Date(value, StringUtils.split(patterns, ","));
	}
	
	/**
	 * 尝试把一个String按照指定的多个pattern进行转换,转换成功返回结果,失败返回null,如果值为空直接返回null
	 * @param value 需要转换为日期的字符串
	 * @param patterns 日期pattern数组
	 * @return
	 */
	public static Date tryStr2Date(String value, String [] patterns) {
		Validate.notEmpty(patterns,"patterns 不能为空");
		Date d = null;
		if (StringUtils.isNotEmpty(value)) {
			for (String p : patterns) {
				try {
					d = DateUtil.str2Date(value, p);
					break;
				} catch (Exception e) {
					continue;
				}
			}
		}
		return d;
	}

	
	/**
	 * 按指定格式将字符串转换为日期
	 * @param dateStr 日期串
	 * @param pattern 格式
	 * @return 日期
	 */
	public static Date str2Date(String dateStr, String pattern){
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		if (null == pattern) {
			pattern = DEFAULT_DATE_FORMAT;
		}
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将字符串转换为日期时间:yyyy-MM-dd
	 * @param dateStr 日期串
	 * @return 日期时间
	*/
	public static Date str2Date(String dateStr){
		return str2Date(dateStr, null);
	}
	
	/**
	 * 将字符串转换为日期时间:yyyy-MM-dd HH:mm:ss
	 * @param dateStr 日期串
	 * @return 日期时间
	*/
	public static Date str2DateTime(String dateStr){
		return str2DateTime(dateStr, null);
	}
	
	/**
	 * 按指定格式将字符串转换为日期时间
	 * 
	 * @param dateStr 日期串
	 * @param pattern 格式
	 * @return 日期时间
	*/
	public static Date str2DateTime(String dateStr, String pattern){
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		if (null == pattern) {
			pattern = DEFAULT_DATE_TIME_FORMAT;
		}
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern(pattern);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将日期格式化为字符串
	 * 
	 * @param date 日期
	 * @param pattern 格式
	 * @return 格式化后的日期串
	 */
	public static String date2Str(Date date, String pattern) {
		if (null == date) {
			return null;
		}
		if (null == pattern) {
			pattern = DEFAULT_DATE_FORMAT;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 将日期时间格式化为字符串
	 * 
	 * @param date 日期
	 * @param pattern 格式
	 * @return 格式化后的日期时间串
	 */
	public static String dateTime2Str(Date date, String pattern) {
		if (null == date) {
			return null;
		}
		if (null == pattern) {
			pattern = DEFAULT_DATE_TIME_FORMAT;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 取得当前时间戳
	 * 
	 * @return 当前时间戳
	 */
	public static String getCurrentTime() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}

	/**
	 * 取得当前日期
	 * 
	 * @return 当前日期
	 */
	public static String thisDate() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(calendar.getTime());
	}

	/**
	 * 取得当前时间
	 * 
	 * @return 当前时间
	 */
	public static String thisTime() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		return new SimpleDateFormat(DEFAULT_TIME_FORMAT).format(calendar.getTime());
	}

	/**
	 * 取得当前完整日期时间
	 * 
	 * @return 当前完整日期时间
	 */
	public static String thisDateTime() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		return new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT).format(calendar.getTime());
	}

	/**
	 * 获取某月最后一天的日期数字
	 * 
	 * @param firstDate 日期
	 * @return 某月最后一天的日期数字
	 */
	public static int getLastDayOfMonth(Date firstDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(firstDate);
		return cal.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 取得今天的最小时间
	 * 
	 * @return 今天的最小时间
	 */
	public static Date getTodayMin() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 取得今天的最大时间
	 * @return 今天的最大时间
	 */
	public static Date getTodayMax() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 取得明天的最小时间
	 * @return 明天的最小时间
	 */
	public static Date getTomorrowMin() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	/**
	 * 取得明天的最大时间
	 * 
	 * @return 明天的最大时间
	 */
	public static Date getTomorrowMax() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		cal.add(Calendar.DATE, 1);

		return cal.getTime();
	}

	/**
	 * 由指定时间、时间域、数额，计算时间值
	 * @param standard 指定时间
	 * @param type 时间域
	 * @param amount 数额
	 * @return 时间值
	 */
	public static Date genDiffDate(Date standard, int type, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(standard);
		cal.add(type, amount);

		return cal.getTime();
	}

	/**
	 * 生成指定时间所在的小时段（清空：分钟、秒、毫秒）
	 * @param standard 指定时间
	 * @return 指定时间所在的小时段
	 */
	public static Date genHourStart(Date standard) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(standard);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * 取得当前天后，减去指定天数后的最小时间
	 * 
	 * @param date 当前日期
	 * @param beforeDay 天数
	 * @return 当前天后，减去指定天数后的最小时间
	 */
	public static Date getBeforeDayMin(Date date, int beforeDay) {
		return getDayMin(date, -beforeDay);
	}

	/**
	 * 取得当前天后，减去指定天数后的最大时间
	 * 
	 * @param date 当前日期
	 * @param beforeDay 天数
	 * @return 当前天后，减去指定天数后的最大时间
	 */
	public static Date getBeforeDayMax(Date date, int beforeDay) {
		return getDayMax(date, -beforeDay);
	}

	/**
	 * 取得当前天后，加上指定天数后的最小时间
	 * 
	 * @param date  当前日期
	 * @param afterDay 天数
	 * @return 当前天后，加上指定天数后的最小时间
	 */
	public static Date getAfterDayMin(Date date, int afterDay) {

		return getDayMin(date, afterDay);
	}

	/**
	 * 取得当前天后，加上指定天数后的最大时间
	 * 
	 * @param date 当前日期
	 * @param afterDay 天数
	 * @return 当前天后，加上指定天数后的最大时间
	 */
	public static Date getAfterDayMax(Date date, int afterDay) {
		return getDayMax(date, afterDay);
	}

	/**
	 * 取得当前天后，加上指定天数后的最小时间
	 * 
	 * @param date 当前日期
	 * @param addDay 天数
	 * @return 当前天后，加上指定天数后的最小时间
	 */
	public static Date getDayMin(Date date, int addDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, addDay);
		return cal.getTime();
	}

	/**
	 * 取得当前天 ,加上指定天数后的最大时间
	 * 
	 * @param date 当前日期
	 * @param addDay 天数
	 * @return 当前天 ,加上指定天数后的最大时间
	 */
	public static Date getDayMax(Date date, int addDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		cal.add(Calendar.DATE, addDay);

		return cal.getTime();
	}

	/**
	 * 取得当前天 ,加上指定天数后的时间
	 * 
	 * @param date 当前日期
	 * @param addDay 天数
	 * @return 当前天 ,加上指定天数后的时间
	 */
	public static Date addDays(Date date, int addDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, addDay);
		return cal.getTime();
	}

	/**
	 * 取得当前天 ,加上指定月份数后的时间
	 * 
	 * @param date 当前日期
	 * @param months 月份数
	 * @return 当前天 ,加上指定月份数后的时间
	 */
	public static Date addMonths(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);
		return cal.getTime();
	}

	/**
	 * 日期差天数(按照时间比较,如果不足一天会自动补足)
	 * @param date1 开始日期
	 * @param date2 结束日期
	 * @return 两日期差天数
	 */
	public static int diff(Date date1, Date date2){
		long day = TimeUnit.DAYS.toMillis(1);
		String str1 = date2Str(date1, "yyyy-MM-dd");
		date1 = str2Date(str1, "yyyy-MM-dd");
		String str2 = date2Str(date2, "yyyy-MM-dd");
		date2 = str2Date(str2, "yyyy-MM-dd");
		return (int) (((date2.getTime() - date1.getTime()) / day));
	}

	/**
	 * 日期差天数(和当前时间比)
	 * @param date 比较日期
	 * @return 和当前日期差天数
	 */
	public static int diff(Date date){
		return diff(new Date(), date);
	}

	/**
	 * 按固定格式比较两个日期
	 * @param date1 日期
	 * @param date2 日期2
	 * @param pattern 格式 默认：yyyy-MM-dd
	 * @return 比较结果
	 */
	public static int compareDate(Date date1, Date date2, String pattern) {
		String d1 = date2Str(date1, pattern);
		String d2 = date2Str(date2, pattern);
		return d1.compareTo(d2);
	}

	/**
	 * 按固定格式比较两个日期+时间
	 * 
	 * @param time1  日期时间
	 * @param time2  日期时间2
	 * @param pattern 格式 默认： yyyy-MM-dd HH:mm:ss
	 * @return 比较结果
	 */
	public static int compareDateTime(Date time1, Date time2, String pattern) {
		String d1 = dateTime2Str(time1, pattern);
		String d2 = dateTime2Str(time2, pattern);
		return d1.compareTo(d2);
	}

	/**
	 * 判断是否是闰年
	 * 
	 * @param date 日期
	 * @return boolean
	 */
	public static boolean isLeapyear(Date date) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		return gregorianCalendar.isLeapYear(gregorianCalendar.get(Calendar.YEAR));
	}

	/**
	 * 根据传入日期得到本月月末
	 * @param date 传入日期
	 * @return date 月末日期
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getLastDateOfMonth(c);
	}

	/**
	 * Description: 根据传入日期得到本月月初
	 * @param
	 * @return Date
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getFirstDateOfMonth(c);
	}

	/**
	 * 根据传入日期得到本月月初
	 * @param calendar 传入日期
	 * @return date 月末日期
	 */
	public static Date getFirstDateOfMonth(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 根据传入日期得到本月月末
	 * @param calendar 传入日期
	 * @return date 月末日期
	 */
	public static Date getLastDateOfMonth(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 根据传入日期得到本月月末，如果传入日期为月末则返回传入日期
	 * @param date 传入日期
	 * @return boolean true为是
	 */
	public static boolean isLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return isLastDateOfMonth(c);
	}

	/**
	 * 根据传入日期得到本月月末，如果传入日期为月末则返回传入日期
	 * @param date 传入日期
	 * @return boolean false为不是
	 */
	public static boolean isLastDateOfMonth(Calendar date) {
		if (date.getActualMaximum(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)) {
			return true;
		}
		return false;
	}

	/**
	 * 根据日期得到年份
	 * @param date 日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 根据日期得到月份
	 * 
	 * @param date 日期
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 根据日期得到日
	 * 
	 * @param date 日期
	 * @return 日
	 */
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 时间格式化
	 * 
	 * @param millonSeconds 毫秒
	 * @param language 语言
	 * @return time
	 */
	public static String formatMilliseconds(long millonSeconds, String language) {
		String v = "";
		long second = millonSeconds / 1000;// 转换为秒
		long millonSecond = millonSeconds - second * 1000;// 多出的不足一秒的毫秒数
		boolean isChinese = language.equalsIgnoreCase("chinese");
		if (isChinese) {
			v += millonSecond + "毫秒";
		} else {
			v += millonSecond + "ms.";
		}
		if (second > 0)// 如果还有秒
		{
			long minutes = second / 60;// 分钟取整
			second = second - minutes * 60;// 不足一分钟的秒数
			if (isChinese) {
				v = second + "秒" + v;
			} else {
				v = second + "s" + v;
			}
			if (minutes > 0)// 如果还有分钟
			{
				long hours = minutes / 60;// 小时取整
				minutes = minutes - hours * 60;// 不足一小时的分钟数
				if (isChinese) {
					v = minutes + "分" + v;
				} else {
					v = minutes + "minutes " + v;
				}
				if (hours > 0) {
					long days = hours / 24;// 天取整
					hours = hours - days * 24;// 不足一天的小时数
					if (isChinese) {
						v = hours + "小时" + v;
					} else {
						v = hours + "hours " + v;
					}
					if (days > 0) {
						if (isChinese) {
							v += days + "天" + v;
						} else {
							v += days + " days " + v;
						}
					}
				}
			}
		}
		return v;
	}

	/**
	 * 时间格式化
	 * 
	 * @param millonSeconds 毫秒
	 * @return time
	 */
	public static String formatMilliseconds(long millonSeconds) {
		return formatMilliseconds(millonSeconds, "CHINESE");
	}
}
