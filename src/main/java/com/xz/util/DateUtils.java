/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xz.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{

	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.S";
	public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	public static final String cn_YYYY_MM_DD = "yyyy年MM月dd日";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
	public static final String HH_MM_SS = "HH:mm:ss";
    private static String[] parsePatterns = { "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd",
            "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
            "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate()
    {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern)
    {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern)
    {
        String formatDate = null;
        if (pattern != null && pattern.length > 0)
        {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else
        {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date)
    {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime()
    {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime()
    {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear()
    {
        return formatDate(new Date(), "yyyy");
    }
    public static String getYear(Date date)
    {
        return formatDate(date, "yyyy");
    }
    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth()
    {
        return formatDate(new Date(), "MM");
    }
    
    public static String getMonth(Date date)
    {
        return formatDate(date, "MM");
    }
    
    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay()
    {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek()
    {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
     * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e)
        {
            return null;
        }
    }
    
    /**
	 * 解析
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parse(String date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

    /**
     * 获取过去的天数
     * 
     * @param date
     * @return
     */
    public static long pastDays(Date date)
    {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取过去的小时
     * 
     * @param date
     * @return
     */
    public static long pastHour(Date date)
    {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 60 * 1000);
    }

    /**
     * 获取过去的分钟
     * 
     * @param date
     * @return
     */
    public static long pastMinutes(Date date)
    {
        long t = new Date().getTime() - date.getTime();
        return t / (60 * 1000);
    }
    
    /**
	 * 比较两个 Date 对象表示的日期值（仅仅比较日期,忽略时分秒）。 -wuwm
	 * 
	 * @param d1
	 * @param d2
	 * @return 如果 d1 的日期等于 d2 表示的日期，则返回 0 值；
	 *         如果 d1 的日期在d2表示的日期之前，则返回小于 0 的值；
	 *         如果 d1 的日期在 d2 表示的日期之后，则返回大于 0 的值。
	 * 
	 */
	public static int compareDate(Date d1, Date d2) {
		d1 = DateUtils.parse(DateUtils.formatDate(d1, DateUtils.YYYY_MM_DD), DateUtils.YYYY_MM_DD);
		d2 = DateUtils.parse(DateUtils.formatDate(d2, DateUtils.YYYY_MM_DD), DateUtils.YYYY_MM_DD);
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		c1.setTime(d1);
		c2.setTime(d2);

		return c1.compareTo(c2);
	}

	/**
	 * 比较两个 Date 对象表示的日期值
	 *
	 * @param d1
	 * @param d2
	 * @return 如果 d1 的日期等于 d2 表示的日期，则返回 0 值；
	 *         如果 d1 的日期在d2表示的日期之前，则返回小于 0 的值；
	 *         如果 d1 的日期在 d2 表示的日期之后，则返回大于 0 的值。
	 *
	 */
	public static int compareDateNew(Date d1, Date d2) {
		d1 = DateUtils.parse(DateUtils.formatDate(d1, DateUtils.YYYY_MM_DD_HH_MM_SS), DateUtils.YYYY_MM_DD_HH_MM_SS);
		d2 = DateUtils.parse(DateUtils.formatDate(d2, DateUtils.YYYY_MM_DD_HH_MM_SS), DateUtils.YYYY_MM_DD_HH_MM_SS);
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		c1.setTime(d1);
		c2.setTime(d2);

		return c1.compareTo(c2);
	}

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * 
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis)
    {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60
                - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000
                - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "."
                + sss;
    }

    /**
     * 获取两个日期之间的天数
     * 
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after)
    {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    public static Date parseDate(long timeStamp)
    {	
    	if(timeStamp == 0l){
    		return null;
    	}
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        Long time = new Long(timeStamp);
        String d = format.format(time);
        Date date;
		try {
			date = format.parse(d);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    public static Date parseDateFormUnix(long timeStamp, String pattern){	
    	if(timeStamp == 0l){
    		return null;
    	}
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Long time = new Long(timeStamp);
        String d = format.format(time);
        Date date;
		try {
			date = format.parse(d);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    
	/**
	 * @Description: 时间戳格式化为日期字符串
	 * @param timestamp
	 * @return
	 */
	public static String formatDateFromUnix(long timestamp, String pattern){
		if(timestamp > 0){
			if(StringUtils.isBlank(pattern)){
				pattern = YYYY_MM_DD_HH_MM_SS;
			}
			return format(new Date(timestamp), pattern);
		}
		return null;
	}
	
	/**
	 * 格式化
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * 获取某年第一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}
	
	/**
	 * 获取某年最后一天日期
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearLast(int year){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		
		return currYearLast;
	}
	
	/**
	 * 获取当前时间并加一秒
	 * @return
	 */
	public static Date getNewDateAddFirst(){
		Calendar calendar = Calendar.getInstance ();
        calendar.add(Calendar.SECOND, 1);
        Date currDate = calendar.getTime();
        return currDate;
	}
	
	/**
	 * 获取前一天数据
	 * @return
	 */
	public static Date getYesterday(){
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, -1);    //得到前一天
        Date date = calendar.getTime();
        return date;
	}
	
	/**
	 * 
	 * @param days 获取之前几天
	 * @return
	 */
	public static Date getBeforeDay(int days){
		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
        calendar.add(Calendar.DATE, -days);    //得到之前的天数
        Date date = calendar.getTime();
        return date;
	}
	
	 public static String formatDuring(long mss) {
	        long days = mss / (1000 * 60 * 60 * 24);
	        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
	        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
	        long seconds = (mss % (1000 * 60)) / 1000;
	        return days + "天" + hours + "时" + minutes + "分"
	                + seconds + "秒";
	 }
	 
	 /**
	  * 获取两个日期之间的月份
	  * @param d1
	  * @param d2
	  * @return
	  * @throws ParseException
	  */
	 public static int getMonthDiff(Date d1, Date d2) {
	        Calendar c1 = Calendar.getInstance();
	        Calendar c2 = Calendar.getInstance();
	        c1.setTime(d1);
	        c2.setTime(d2);
	        if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
	        int year1 = c1.get(Calendar.YEAR);
	        int year2 = c2.get(Calendar.YEAR);
	        int month1 = c1.get(Calendar.MONTH);
	        int month2 = c2.get(Calendar.MONTH);
	        int day1 = c1.get(Calendar.DAY_OF_MONTH);
	        int day2 = c2.get(Calendar.DAY_OF_MONTH);
	        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
	        int yearInterval = year1 - year2;
	        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
	        if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;
	        // 获取月数差值
	        int monthInterval =  (month1 + 12) - month2  ;
	        if(day1 < day2) monthInterval --;
	        monthInterval %= 12;
	        return yearInterval * 12 + monthInterval;
	 }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException
    {
    	System.out.println(DateUtils.getMonthDiff(DateUtils.parseDate(Long.parseLong("1546272000000")),DateUtils.parseDate(Long.parseLong("1543593600000"))));
    }
}
