package cn.teleus.common.lang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Times {
	
	/**
	 * 某时间的日期（年月日，时分秒是传入参数）：2016-08-01 14:14:00
	 * @param hhmmss
	 * @return
	 */
	public static Date getNowDateByHMS(String hhmmss){
		if(Strings.isBlank(hhmmss)){
			return Times.now();
		}else{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return df.parse(nowDay()+" "+hhmmss);
			} catch (ParseException e) {
				e.printStackTrace();
				return Times.now();
			}
		}
	}
	
	/**
	 * 当天日期（只包括年月日）：2016-08-01
	 * @return
	 */
	public static String nowDay(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(Times.now());
	}
	
	/**
	 * 获取当天的起始时间：即00:00:00
	 */
	public static Date dayStart(Date date){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(date);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	};
	
	/**
	 * @param date
	 * @return
	 */
	public static Date dayEnd(Date date){
		return new Date(dayStart(date).getTime() + 24*3600*1000);
	};
	
	/**
	 * @param date
	 * @return
	 */
	public static Date nextDay(Date date){
		return new Date(date.getTime() + 24*3600*1000);
	};
	
	 /**
     * 判断一年是否为闰年，如果给定年份小于1全部为 false
     * 
     * @param year
     *            年份，比如 2012 就是二零一二年
     * @return 给定年份是否是闰年
     */
    public static boolean leapYear(int year) {
        if (year < 4) {
            return false;
        }
        return (year % 400 == 0) || (year % 100 != 0 && year % 4 == 0);
    }

    /**
     * 判断某年（不包括自己）之前有多少个闰年
     * 
     * @param year
     *            年份，比如 2012 就是二零一二年
     * @return 闰年的个数
     */
    public static int countLeapYear(int year) {
        // 因为要计算年份到公元元年（0001年）的年份跨度，所以减去1
        int span = year - 1;
        return (span / 4) - (span / 100) + (span / 400);
    }

    /**
     * 将一个秒数（天中），转换成一个如下格式的数组:
     * 
     * <pre>
     * [0-23][0-59[-059]
     * </pre>
     * 
     * @param sec
     *            秒数
     * @return 时分秒的数组
     */
    public static int[] t(int sec) {
        int[] re = new int[3];
        re[0] = Math.min(23, sec / 3600);
        re[1] = Math.min(59, (sec - (re[0] * 3600)) / 60);
        re[2] = Math.min(59, sec - (re[0] * 3600) - (re[1] * 60));
        return re;
    }

    /**
     * 将一个时间字符串，转换成一个一天中的绝对秒数
     * 
     * @param ts
     *            时间字符串，符合格式 "HH:mm:ss"
     * @return 一天中的绝对秒数
     */
    public static int t(String ts) {
        String[] tss = Strings.splitIgnoreBlank(ts, ":");
        if (null != tss && tss.length == 3) {
            int hh = Integer.parseInt(tss[0]);
            int mm = Integer.parseInt(tss[1]);
            int ss = Integer.parseInt(tss[2]);
            return hh * 3600 + mm * 60 + ss;
        }
        throw Lang.makeThrow("Wrong format of time string '%s'", ts);
    }

    /**
     * 返回服务器当前时间
     * 
     * @return 服务器当前时间
     */
    public static Date now() {
        return new Date(System.currentTimeMillis());
    }

    private static Pattern P_TIME = Pattern.compile("^((\\d{2,4})([/\\\\-])(\\d{1,2})([/\\\\-])(\\d{1,2}))?"
                                                     + "(([ T])?"
                                                     + "(\\d{1,2})(:)(\\d{1,2})(:)(\\d{1,2})"
                                                     + "(([.])"
                                                     + "(\\d{1,}))?)?"
                                                     + "(([+-])(\\d{1,2})(:\\d{1,2})?)?"
                                                     + "$");
    /**
     * 返回时间对象在一天中的毫秒数
     * 
     * @param d
     *            时间对象
     * 
     * @return 时间对象在一天中的毫秒数
     */
    public static long ms(Date d) {
        return ms(calendar(d));
    }

    /**
     * 返回时间对象在一天中的毫秒数
     * 
     * @param c
     *            时间对象
     * 
     * @return 时间对象在一天中的毫秒数
     */
    public static int ms(Calendar c) {
        int ms = c.get(Calendar.HOUR_OF_DAY) * 3600000;
        ms += c.get(Calendar.MINUTE) * 60000;
        ms += c.get(Calendar.SECOND) * 1000;
        ms += c.get(Calendar.MILLISECOND);
        return ms;
    }

    /**
     * 返回当前时间在一天中的毫秒数
     * 
     * @return 当前时间在一天中的毫秒数
     */
    public static int ms() {
        return ms(Calendar.getInstance());
    }

    /**
     * 根据一个当天的绝对毫秒数，得到一个时间字符串，格式为 "HH:mm:ss.EEE"
     * 
     * @param ms
     *            当天的绝对毫秒数
     * @return 时间字符串
     */
    public static String mss(int ms) {
        int sec = ms / 1000;
        ms = ms - sec * 1000;
        return secs((int) sec) + "." + Strings.alignRight(ms, 3, '0');
    }

    /**
     * 根据一个当天的绝对秒数，得到一个时间字符串，格式为 "HH:mm:ss"
     * 
     * @param sec
     *            当天的绝对秒数
     * @return 时间字符串
     */
    public static String secs(int sec) {
        int hh = sec / 3600;
        sec -= hh * 3600;
        int mm = sec / 60;
        sec -= mm * 60;
        return Strings.alignRight(hh, 2, '0')
               + ":"
               + Strings.alignRight(mm, 2, '0')
               + ":"
               + Strings.alignRight(sec, 2, '0');

    }

    /**
     * 返回时间对象在一天中的秒数
     * 
     * @param d
     *            时间对象
     * 
     * @return 时间对象在一天中的秒数
     */
    public static int sec(Date d) {
        Calendar c = calendar(d);
        int sec = c.get(Calendar.HOUR_OF_DAY) * 3600;
        sec += c.get(Calendar.MINUTE) * 60;
        sec += c.get(Calendar.SECOND);
        return sec;
    }

    /**
     * 返回当前时间在一天中的秒数
     * 
     * @return 当前时间在一天中的秒数
     */
    public static int sec() {
        return sec(now());
    }

    /**
     * 根据毫秒数得到时间
     * 
     * @param ms
     *            时间的毫秒数
     * @return 时间
     */
    public static Date d(long ms) {
        return new Date(ms);
    }

    /**
     * 根据日期对象得到时间
     * 
     * @param d
     *            时间对象
     * @return 时间
     */
    public static Calendar calendar(Date d) {
        return calendar(d.getTime());
    }

    /**
     * 根据毫秒数得到时间
     * 
     * @param ms
     *            时间的毫秒数
     * @return 时间
     */
    public static Calendar calendar(long ms) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(ms);
        return c;
    }

    /**
     * 将一个秒数（天中），转换成一个格式为 HH:mm:ss 的字符串
     * 
     * @param sec
     *            秒数
     * @return 格式为 HH:mm:ss 的字符串
     */
    public static String sT(int sec) {
        int[] ss = t(sec);
        return Strings.alignRight(ss[0], 2, '0')
               + ":"
               + Strings.alignRight(ss[1], 2, '0')
               + ":"
               + Strings.alignRight(ss[2], 2, '0');
    }

    /**
     * 以本周为基础获得某一周的时间范围
     * 
     * @param off
     *            从本周偏移几周， 0 表示本周，-1 表示上一周，1 表示下一周
     * 
     * @return 时间范围(毫秒级别)
     *
     */
    public static Date[] week(int off) {
        return week(System.currentTimeMillis(), off);
    }

    /**
     * 以某周为基础获得某一周的时间范围
     * 
     * @param base
     *            基础时间，毫秒
     * @param off
     *            从本周偏移几周， 0 表示本周，-1 表示上一周，1 表示下一周
     * 
     * @return 时间范围(毫秒级别)
     */
    public static Date[] week(long base, int off) {
        return weeks(base, off, off);
    }

    /**
     * 以本周为基础获得时间范围
     * 
     * @param offL
     *            从本周偏移几周， 0 表示本周，-1 表示上一周，1 表示下一周
     * @param offR
     *            从本周偏移几周， 0 表示本周，-1 表示上一周，1 表示下一周
     * 
     * @return 时间范围(毫秒级别)
     */
    public static Date[] weeks(int offL, int offR) {
        return weeks(System.currentTimeMillis(), offL, offR);
    }

    /**
     * 按周获得某几周周一 00:00:00 到周六 的时间范围
     * <p>
     * 它会根据给定的 offL 和 offR 得到一个时间范围
     * <p>
     * 对本函数来说 week(-3,-5) 和 week(-5,-3) 是一个意思
     * 
     * @param base
     *            基础时间，毫秒
     * @param offL
     *            从本周偏移几周， 0 表示本周，-1 表示上一周，1 表示下一周
     * @param offR
     *            从本周偏移几周， 0 表示本周，-1 表示上一周，1 表示下一周
     * 
     * @return 时间范围(毫秒级别)
     */
    public static Date[] weeks(long base, int offL, int offR) {
        int from = Math.min(offL, offR);
        int len = Math.abs(offL - offR);
        // 现在
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(base);

        Date[] re = new Date[2];

        // 计算开始
        c.add(Calendar.DAY_OF_YEAR, 7*from);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        re[0] = c.getTime();

        // 计算结束
        c.add(Calendar.DAY_OF_YEAR, 7*(len+1));
        c.add(Calendar.MILLISECOND, -1);
        re[1] = c.getTime();

        // 返回
        return re;
    }

    private static final String[] MMM = new String[]{"Jan",
                                                      "Feb",
                                                      "Mar",
                                                      "Apr",
                                                      "May",
                                                      "Jun",
                                                      "Jul",
                                                      "Aug",
                                                      "Sep",
                                                      "Oct",
                                                      "Nov",
                                                      "Dec"};

    /**
     * 将一个时间格式化成容易被人类阅读的格式
     * 
     * <pre>
     * 如果 1 分钟内，打印 Just Now
     * 如果 1 小时内，打印多少分钟
     * 如果 1 天之内，打印多少小时之前
     * 如果是今年之内，打印月份和日期
     * 否则打印月份和年
     * </pre>
     * 
     * @param d
     * @return
     */
    public static String formatForRead(Date d) {
        long ms = System.currentTimeMillis() - d.getTime();
        // 如果 1 分钟内，打印 Just Now
        if (ms < (60000)) {
            return "Just Now";
        }
        // 如果 1 小时内，打印多少分钟
        if (ms < (60 * 60000)) {
            return "" + (ms / 60000) + "Min.";
        }

        // 如果 1 天之内，打印多少小时之前
        if (ms < (24 * 3600 * 1000)) {
            return "" + (ms / 3600000) + "hr.";
        }

        // 如果一周之内，打印多少天之前
        if (ms < (7 * 24 * 3600 * 1000)) {
            return "" + (ms / (24 * 3600000)) + "Day";
        }

        // 如果是今年之内，打印月份和日期
        Calendar c = Calendar.getInstance();
        int thisYear = c.get(Calendar.YEAR);

        c.setTime(d);
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        if (thisYear == yy) {
            int dd = c.get(Calendar.DAY_OF_MONTH);
            return String.format("%s %d", MMM[mm], dd);
        }

        // 否则打印月份和年
        return String.format("%s %d", MMM[mm], yy);
    }

    /**
     * 以给定的时间格式来安全的对时间进行格式化，并返回格式化后所对应的字符串
     * 
     * @param fmt
     *            时间格式
     * @param d
     *            时间对象
     * @return 格式化后的字符串
     */
    public static String format(DateFormat fmt, Date d) {
        return ((DateFormat) fmt.clone()).format(d);
    }

    /**
     * 以给定的时间格式来安全的对时间进行格式化，并返回格式化后所对应的字符串
     * 
     * @param fmt
     *            时间格式
     * @param d
     *            时间对象
     * @return 格式化后的字符串
     */
    public static String format(String fmt, Date d) {
        return new SimpleDateFormat(fmt).format(d);
    }

    /**
     * 以给定的时间格式来安全的解析时间字符串，并返回解析后所对应的时间对象（包裹RuntimeException）
     * 
     * @param fmt
     *            时间格式
     * @param s
     *            时间字符串
     * @return 该时间字符串对应的时间对象
     */
    public static Date parseq(DateFormat fmt, String s) {
        try {
            return parse(fmt, s);
        }
        catch (ParseException e) {
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 以给定的时间格式来安全的解析时间字符串，并返回解析后所对应的时间对象（包裹RuntimeException）
     * 
     * @param fmt
     *            时间格式
     * @param s
     *            时间字符串
     * @return 该时间字符串对应的时间对象
     */
    public static Date parseq(String fmt, String s) {
        try {
            return parse(fmt, s);
        }
        catch (ParseException e) {
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 以给定的时间格式来安全的解析时间字符串，并返回解析后所对应的时间对象
     * 
     * @param fmt
     *            时间格式
     * @param s
     *            日期时间字符串
     * @return 该时间字符串对应的时间对象
     */
    public static Date parse(DateFormat fmt, String s) throws ParseException {
        return ((DateFormat) fmt.clone()).parse(s);
    }

    /**
     * 以给定的时间格式来安全的解析时间字符串，并返回解析后所对应的时间对象
     * 
     * @param fmt
     *            时间格式
     * @param s
     *            日期时间字符串
     * @return 该时间字符串对应的时间对象
     */
    public static Date parse(String fmt, String s) throws ParseException {
        return new SimpleDateFormat(fmt).parse(s);
    }

    public static final long T_1S = 1000;
    public static final long T_1M = 60 * 1000;
    public static final long T_1H = 60 * 60 * 1000;
    public static final long T_1D = 24 * 60 * 60 * 1000;

    /**
     * 方便的把时间换算成毫秒数
     * 
     * 支持几个单位, s(秒), m(分钟), h(小时), d(天)
     * 
     * 比如:
     * 
     * 100s -> 100000 <br>
     * 2m -> 120000 <br>
     * 3h -> 10800000 <br>
     * 
     * @param tstr
     * @return
     */
    public static long toMillis(String tstr) {
        if (Strings.isBlank(tstr)) {
            return 0;
        }
        tstr = tstr.toLowerCase();
        // FIXME 稍后改成正则判断
        String tl = tstr.substring(0, tstr.length() - 1);
        String tu = tstr.substring(tstr.length() - 1);
        if (TIME_S_EN.equals(tu)) {
            return T_1S * Long.valueOf(tl);
        }
        if (TIME_M_EN.equals(tu)) {
            return T_1M * Long.valueOf(tl);
        }
        if (TIME_H_EN.equals(tu)) {
            return T_1H * Long.valueOf(tl);
        }
        if (TIME_D_EN.equals(tu)) {
            return T_1D * Long.valueOf(tl);
        }
        return Long.valueOf(tstr);
    }

    private static String TIME_S_EN = "s";
    private static String TIME_M_EN = "m";
    private static String TIME_H_EN = "h";
    private static String TIME_D_EN = "d";

    private static String TIME_S_CN = "秒";
    private static String TIME_M_CN = "分";
    private static String TIME_H_CN = "时";
    private static String TIME_D_CN = "天";

    /**
     * 一段时间长度的毫秒数转换为一个时间长度的字符串
     * 
     * 1000 -> 1S
     * 
     * 120000 - 2M
     * 
     * @param mi
     *            毫秒数
     * @return 可读的文字
     */
    public static String fromMillis(long mi) {
        return fromMillis(mi, true);
    }

    /**
     * fromMillis的中文版本
     * 
     * 1000 -> 1秒
     * 
     * 120000 - 2分
     * 
     * @param mi
     *            毫秒数
     * @return 可读的文字
     */
    public static String fromMillisCN(long mi) {
        return fromMillis(mi, false);
    }

    private static String fromMillis(long mi, boolean useEnglish) {
        if (mi <= T_1S) {
            return "1" + (useEnglish ? TIME_S_EN : TIME_S_CN);
        }
        if (mi < T_1M && mi > T_1S) {
            return (int) (mi / T_1S) + (useEnglish ? TIME_S_EN : TIME_S_CN);
        }
        if (mi >= T_1M && mi < T_1H) {
            int m = (int) (mi / T_1M);
            return m
                   + (useEnglish ? TIME_M_EN : TIME_M_CN)
                   + fromMillis(mi - m * T_1M, useEnglish);
        }
        if (mi >= T_1H && mi < T_1D) {
            int h = (int) (mi / T_1H);
            return h
                   + (useEnglish ? TIME_H_EN : TIME_H_CN)
                   + fromMillis(mi - h * T_1H, useEnglish);
        }
        if (mi >= T_1D) {
            int d = (int) (mi / T_1D);
            return d
                   + (useEnglish ? TIME_D_EN : TIME_D_CN)
                   + fromMillis(mi - d * T_1D, useEnglish);
        }
        // WTF ?
        throw Lang.impossible();
    }
}