package com.myuntils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/11/21/021.
 */

public final class MyAllUntils {

    public  static  String getNowDate(){
        /**
         * 获取系统当前时间
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date nowDate = new Date(System.currentTimeMillis());
        String strDate = format.format(nowDate);

        return  strDate;
    }



    public static String getNextDate() {
            Date nowDate = new Date(System.currentTimeMillis());
            Calendar out = Calendar.getInstance();
            out.setTime(nowDate);
            out.setTimeInMillis(out.getTimeInMillis() + (86400000 * 1));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            String endDate = sdf.format(out.getTime());

        return endDate;
    }

    public static String getNextDateF() {
        Date nowDate = new Date(System.currentTimeMillis());
        Calendar out = Calendar.getInstance();
        out.setTime(nowDate);
        out.setTimeInMillis(out.getTimeInMillis() + (86400000 * 1));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String endDate = sdf.format(out.getTime());

        return endDate;
    }
    public  static  String getNowDateF(){
        /**
         * 获取系统当前时间
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = new Date(System.currentTimeMillis());
        String strDate = format.format(nowDate);

        return  strDate;
    }


    public  static  String getNowDateYMDS(){
        /**
         * 获取系统当前时间
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date(System.currentTimeMillis());
        String strDate = format.format(nowDate);

        return  strDate;
    }

    /**
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    public  static  String getNowDateSS(){
        /**
         * 获取系统当前时间
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date nowDate = new Date(System.currentTimeMillis());
        String strDate = format.format(nowDate);

        return  strDate;
    }

    /**
     * MD5加密
     * @param str
     * @return
     */
    public static  String getMD5(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(32);
        } catch (Exception e) {
            Log.e("----加密--", "MD5加密出现错误");
        }
        return  null;

    }
    public static void open(Activity a, Class<? extends Activity> b) {
        Intent intent = new Intent();
        intent.setClass(a, b);
        a.startActivity(intent);
    }

    public static String getBejiT(){
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String reStr = dff.format(new Date());
        return  reStr;
    }
    /**
     * 返回当前时间是上午还是下午
     * @return
     */
   public static String getNowTime(){
       DateFormat df = new SimpleDateFormat("HH:mm");
       String time= df.format(new Date());
       if(time.length()==5){
           String str =  time.substring(0,2);
           int t = Integer.parseInt(str);
           if(t>=10 && t<12){
               return "上午"+time;
           }else if(t==12){
              return  "中午"+time;
           }else{
               return  "下午"+time;
           }

        }else{
            return "上午"+time;
       }

   }
}
