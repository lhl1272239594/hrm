/**
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">JeeSite</a> All rights reserved.
 */
package com.jims.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author zhangyao
 * @version 2016-4-23
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM","yyyy年MM月dd日 HH:mm","yyyy年MM月dd日","yyyy年MM月dd日 HH:mm:ss"};


	/**
     *
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
        float  num=(float)(afterTime - beforeTime) / (1000 * 60 * 60 * 24);
        float nun1= (float) ((int)num+0.5);
        if(num<nun1){
            return num;
        }else{
            return num+1;
        }
	}
	//获取 当前日期的后一天
	public static String getTomorrow(){
		Calendar c=Calendar.getInstance();
		//当前的day_of_month加一就是明天时间
		c.add(Calendar.DAY_OF_MONTH,1);
		String tomorrow=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		// System.out.print("明天的日期"+tomorrow);
		return tomorrow;
	}

    /**
     * 获取几天后的这个时间
     * @param beginDate
     * @param daysAfter
     * @return
     */
    public static Date getDaysAfter(Date beginDate,int daysAfter){
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(beginDate);
        calendar.add(Calendar.DAY_OF_MONTH,daysAfter);
        return calendar.getTime();
    }

	/**
	 * @param args
	 * @throws java.text.ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
//        Date date = new Date("2016-06-30 23:59:59") ;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        Date date = dateFormat.parse("2016-06-30 23:59:59") ;
        Date date1  = DateUtils.getDaysAfter(date,50);
        System.out.println(dateFormat.format(date));
        System.out.println(dateFormat.format(date1));

	}
	public static String getBirthDate(Date birthDate) throws ParseException {
		if (birthDate == null) {
			return "";
		}
		int age = 0;

		Date now = new Date();

		SimpleDateFormat format_y = new
				SimpleDateFormat("yyyy");
		SimpleDateFormat format_M = new
				SimpleDateFormat("MM");

		String birth_year =
				format_y.format(birthDate);
		String this_year =
				format_y.format(now);

		String birth_month =
				format_M.format(birthDate);
		String this_month =
				format_M.format(now);

// 初步，估算
		age =
				Integer.parseInt(this_year) - Integer.parseInt(birth_year);

// 如果未到出生月份，则age - 1
		if
				(this_month.compareTo(birth_month) < 0)
			age -=
					1;
		if (age <
				0)
			age =
					0;
		return Integer.toString(age);
	}
    //计算年龄
    public static long getAge(Date dt1) {
        Date dt2= new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            dt1=sdf.parse(sdf.format(dt1));
            dt2=sdf.parse(sdf.format(dt2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(dt2);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        between_days=between_days/365;
        return between_days;
    }
}
