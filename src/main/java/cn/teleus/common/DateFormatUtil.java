package cn.teleus.common;

import cn.teleus.common.lang.Strings;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
	
	/**
	 * @param dateStr
	 * @return
	 */
	public static Timestamp stringToTimestamp(Object dateStr) {
		if(dateStr == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(dateStr.toString());
			date.getTime();
			cal.setTime(date);
			return new Timestamp(cal.getTimeInMillis());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Timestamp stringToTimestamp(Object dateStr,String format) {
		if(dateStr == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		Date date = null;
		try {
			date = sdf.parse(dateStr.toString());
			date.getTime();
			cal.setTime(date);
			return new Timestamp(cal.getTimeInMillis());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String getTime(String argBegin,String argEnd){
		
		String time = "";
		
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long between = 0;
        try {
        	//当前时间
            Date begin = dfs.parse(argBegin);
            //记录时间
            Date end = dfs.parse(argEnd);
            between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        if(day > 0){
        	//多少天
        	time = day + "天前";
        	if(day > 2){
        		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        		Date data;
				try {
					data = simpleDateFormat.parse(argBegin);
					time = simpleDateFormat.format(data);
				} catch (ParseException e) {
					e.printStackTrace();
				}
        	}
        }else if(hour > 0){
        	//多少小时
        	time = hour + "小时前";
        }else if(min > 0){
        	//多少分钟
        	time = min + "分钟前";
        }else {
        	time = s + "秒前";
        }
        return time;
	}
	
	
	public static String formatString(Timestamp da){
		String datastring = null;
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
		datastring = sdf.format(da);
		return datastring;
	}
	
	public static String formatString(Timestamp da,String format){
		String datastring = null;
		SimpleDateFormat   sdf   =   new   SimpleDateFormat(format); 
		datastring = sdf.format(da);
		return datastring;
	}
	
	/**
	 * 
	 * @param time  yyyy-MM-dd
	 * @return
	 */
	public static Date formatDate(Timestamp time,String format){
		String formatString = formatString(time,format);
		Timestamp stringToTimestamp = stringToTimestamp(formatString,format);
		return new Date(stringToTimestamp.getTime());
	}

	/**
	 * DateToStr 'yyyyMMdd'
	 * @param date
	 * @return
	 */
	public static String dateToStrM1(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMdd");
		return simpleDateFormat.format(date);
	}

	/**
	 * DateToStr 'yyyy-MM-dd HH:mm:ss'
	 * @param date
	 * @return
	 */
	public static String dateToStrM2(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}

	/**
	 * DateToStr 'yyyy-MM-dd'
	 * @param date
	 * @return
	 */
	public static String dateToStrM3(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd");
		return simpleDateFormat.format(date);
	}

	/**
	 * DateToStr 'yyyyMMddHHmmss'
	 * @param date
	 * @return
	 */
	public static String dateToStrM4(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		return simpleDateFormat.format(date);
	}

	/**
	 * DateToStr 'MM-dd HH:mm:ss'
	 * @param date
	 * @return
	 */
	public static String dateToStrM5(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}

	/**
	 * DateToStr 'HH:mm:ss'
	 * @param date
	 * @return
	 */
	public static String dateToStrM6(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"HH:mm:ss");
		return simpleDateFormat.format(date);
	}

	/**
	 * strToDate 'yyyy-MM-dd HH:mm:ss'
	 * @param strToDate 'yyyy-MM-dd HH:mm:ss'
	 * @return Date
	 */
	public static Date strToDateM1(String strToDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return simpleDateFormat.parse(strToDate);
		} catch (ParseException e) {}
		return null;
	}

	/**
	 * strToDate 'yyyy-MM-dd'
	 * @param strToDate 'yyyy-MM-dd'
	 * @return
	 */
	public static Date strToDateM2(String strToDate)
	{
		if (Strings.isEmpty(strToDate)) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return simpleDateFormat.parse(strToDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * strToDate 'yyyyMMdd'
	 * @param strToDate 'yyyyMMdd'
	 * @return
	 */
	public static Date strToDateM3(String strToDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		try {
			return simpleDateFormat.parse(strToDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * strToDate 'yyyy-MM-dd HH:mm'
	 * @param strToDate 'yyyy-MM-dd HH:mm'
	 * @return
	 */
	public static Date strToDateM4(String strToDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return simpleDateFormat.parse(strToDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * strToDate 'yyyy/MM/dd'
	 * @param strToDate 'yyyy/MM/dd'
	 * @return
	 */
	public static Date strToDateM5(String strToDate)
	{
		if (Strings.isEmpty(strToDate)) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			return simpleDateFormat.parse(strToDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * strToDate 'yyyy-MM-dd HH:mm:ss'
	 * @param strToDate 'yyyy-MM-dd HH:mm:ss'
	 * @return Date
	 */
	public static Date strToDateM6(String strToDate)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			return simpleDateFormat.parse(strToDate);
		} catch (ParseException e) {}
		return null;
	}

	public static Date nextDay(Date strToDateM2) {
		if (strToDateM2 == null) {
			return null;
		}
		return new Date(strToDateM2.getTime()+24*3600*1000);
	}
}
