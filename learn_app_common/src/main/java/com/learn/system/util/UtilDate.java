package com.learn.system.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * 类名：UtilDate
 * 功能：自定义订单类
 */
public class UtilDate {

    /**
     * 年月日时分秒(无下划线) yyyyMMddHHmmss
     */
    public static final String dtLong = "yyyyMMddHHmmss";

    /**
     * 完整时间 yyyy-MM-dd HH:mm:ss
     */
    public static final String simple = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日(无下划线) yyyyMMdd
     */
    public static final String dtShort = "yyyyMMdd";

    /**
     * yyyy-MM-dd
     */
    public static final String dtSimpleShort = "yyyy-MM-dd";

    /**
     * MM-dd
     */
    public static final String monthAndDay = "MM-dd";

    /**
     * MM月dd日 EEEE
     */
    public static final String monthDayWeek_cn = "MM月dd日 EEEE";

    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String yearMonthDayHM = "yyyy-MM-dd HH:mm";

    /**
     * HH
     */
    public static final String HOUR = "HH";

    /**
     * HH:mm
     */
    public static final String hourAndMinute = "HH:mm";


    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return 以yyyyMMddHHmmss为格式的当前系统时间
     */
    public static String getOrderNum() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtLong);
        return df.format(date);
    }

    /**
     * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getDateFormatter() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(simple);
        return df.format(date);
    }

    /**
     * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
     */
    public static String getDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dtShort);
        return df.format(date);
    }

    /**
     * 产生随机的三位数
     */
    public static String getThree() {
        Random rad = new Random();
        return rad.nextInt(1000) + "";
    }

    /**
     * 计算距离当前时间的日期
     * @param days 距离系统日期的天数
     */
    public static Date getFutureDate(int days) {
        return UtilDate.getFutureDate(UtilDate.getTodayBegin(), days);
    }

    public static Date getFutureDate(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
        return calendar.getTime();
    }

    public static Date getFutureDate(Date date, int days, int hours, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
        calendar.add(Calendar.HOUR, hours);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * Description 判断时间是否上午，true：上午 false：下午
     * @Author WindPursuer
     * @Date 2018/4/11 下午5:17
     */
    public static boolean isAM(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int result = calendar.get(Calendar.AM_PM);
        return (0 == result);
    }

    /**
     * 格式化日期
     * @param date
     * @param formatStr 格式化样式
     */
    public static String dateFormat(Date date, String formatStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr, Locale.CHINESE);
        return simpleDateFormat.format(date);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param start
     * @param end
     */
    public static int differentDaysIgnoreHour(Date start, Date end) {
        Date _start = UtilDate.resetDateTime(start, 0, 0, 0);
        Date _end = UtilDate.resetDateTime(end, 0, 0, 0);

        int days = (int) ((_end.getTime() - _start.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    public static List<Date> getOrderDateList(Date start, Date end) {
        List<Date> l = new ArrayList<Date>();
        Date tempDate = start;
        l.add(start);
        int j = UtilDate.differentDaysIgnoreHour(start, end);
        for (int i = 0; i < j; i++) {
            tempDate = UtilDate.getFutureDate(tempDate, 1);
            l.add(tempDate);
        }
        return l;
    }

    public static Date resetDateTime(Date date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getTodayBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * Description 获取小时
     * @Author WindPursuer
     * @Date 2018/5/24 上午9:53
     * @param date 时间
     * @param isIncrease 分钟  》 0 小时是否加一
     */
    public static int getHour(Date date, boolean isIncrease) {
        Calendar calendar = Calendar.getInstance();//可以对每个时间域单独修改
        calendar.setTime(date);
        if (calendar.get(Calendar.MINUTE) > 0 && isIncrease) {
            return calendar.get(Calendar.HOUR_OF_DAY) + 1;
        }
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Description 获取分钟
     * @Author WindPursuer
     * @Date 2018/6/25 下午5:01
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();//可以对每个时间域单独修改
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static Date getDateFromStr(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Long calIntervalAsMinutes(Date startDate, Date endDate) {
        long start = startDate.getTime();
        long end = endDate.getTime();
        long minute = (end - start) / (1000 * 60);
        return minute;
    }

//	public static void main(String[] args) {
//		Date start=new Date(System.currentTimeMillis()+60*60*1000*24);
//		Date end=new Date(System.currentTimeMillis()+901*1000);
//		System.out.println(UtilDate.calIntervalAsMinutes(new Date(),end));
//		//System.out.println(UtilDate.getOrderDateList(start,end).size());
//		//System.out.println(UtilDate.dateFormat(UtilDate.getToday(), "yyyy-MM-dd HHmmss SSS EEEE"));
//	}

    /**
     * 获取指定月的上个月第一天 yyyy-MM-dd 00:00:00
     * @param sourceDate
     * @return
     */
    public static Date getLastMonthBegin(Date sourceDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定月的上个月的最后一天   yyyy-MM-dd 23:59:59
     * @param sourceDate
     * @return
     */
    public static Date getLastMonthEnd(Date sourceDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.add(Calendar.SECOND, -1);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定月的开始时间
     * @param sourceDate
     * @return
     */
    public static Date getTargetMonthBegin(Date sourceDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定月的开始时间
     * @param sourceDate
     * @return
     */
    public static Date getTargetMonthEnd(Date sourceDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sourceDate);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MILLISECOND, -1);

        return calendar.getTime();
    }

}
