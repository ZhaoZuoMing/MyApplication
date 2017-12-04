package com.myuntils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class DateUtil {



    public static  String getWeek(String pTime) {

        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(pTime));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            Week += "天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            Week += "六";
        }

        return Week;
    }
































    public final static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyyMMddHHmmss");
    public final static SimpleDateFormat serverSideFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    public final static SimpleDateFormat webServerSideFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat weekFormat_CH = new SimpleDateFormat("yyyy年M月d日 EEE");
    public final static SimpleDateFormat weekFormat_EN = new SimpleDateFormat("yyyy-MM-dd EEE");
    public final static SimpleDateFormat showSDF = new SimpleDateFormat("yyyy年M月d日");
    public final static SimpleDateFormat SHOW_MONTH_DAY = new SimpleDateFormat("M月d日 EEE");
    public final static SimpleDateFormat currentSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public final static SimpleDateFormat chatFormatShort = new SimpleDateFormat("HH:mm");
    public static String formatCurrentTime(Date d){
        return currentSDF.format(d);
    }
    public static String formatShowDate(Date d){
        return showSDF.format(d);
    }

    public static String formatUserBirthDay(Date d){
        return serverSideFormat.format(d);
    }

    public static Date parseServerSide(String s){
        try {
            return serverSideFormat.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatLong2(long ts){
        Date date = new Date(ts);
        return currentSDF.format(date);
    }

    public static String formatLongAs(long ts,SimpleDateFormat format){
        Date date = new Date(ts);
        return format.format(date);
    }

    public static int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) {
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR)
                - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    public static String getChineseWeek(Calendar date) {
        final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
                "星期六" };
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        return dayNames[dayOfWeek - 1];
    }

    public static int getIntWeek(Calendar date) {
        return date.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static boolean dateBefore(Calendar date, Calendar compareDate){
        return date.before(compareDate)&&(getDaysBetween(date, compareDate)!=0);
    }

    public static boolean dateBeforeTomorrow(Calendar date){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return date.before(c)&&(getDaysBetween(date, c)!=0);
    }

    public static boolean dateBeforeToday(Calendar date){
        Calendar c = Calendar.getInstance();
        return date.before(c)&&(getDaysBetween(date, c)!=0);
    }

    public static boolean dateAfter(Calendar date, Calendar compareDate){
        return date.after(compareDate)&&(getDaysBetween(date, compareDate)!=0);
    }

    public static String getAfterHalfHour(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 30);
        return DateUtil.webServerSideFormat.format(c.getTime());
    }

}
