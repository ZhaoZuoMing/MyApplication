package donm.sky.hotel.until;

import android.util.Log;

import com.mytables.MyApp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Administrator on 2017/2/13/013.
 */

public class TimeUntil {

    public  static final  String NEED_CONTRY = "NeedNationality";//需要提供客人国籍
    public  static final  String PERSON_NAME = "PerRoomPerName";//每个房间必须需要一人姓名
    public  static final  String PERSON_ENNAME = "ForeignerNeedEnName";//此酒店要求外宾务必留英文拼写
    public  static final  String CHECKIN_TIEM = "RejectCheckinTime";////几点到几点酒店不接受预订 , 此处校验的是下单时的预订时间
    public static final  String  CONT_ORDER_HOTEL = "4";
    public  static final  String NEED_PHONE = "NeedPhoneNo";//NeedPhoneNo

    public static final  String PREPAY_NOCHANGE = "PrepayNoChange";//不可取消
    //PrepayNeedSomeDay：在到店当日24点前Hour小时前按规则看是否可以免费变更取消（一般是不收罚金），在Hour和Hour2之间按规则存在罚金，Hour2之后不能变更取消；
    public static final  String PREPAY_NEEDSOME_DAY = "PrepayNeedSomeDay";
    //PrepayNeedOneTime：在约定日期时间点(DateNum + Time)前可以免费变更取消
    public static final String PREPAY_NEED_ONETIME = "PrepayNeedOneTime";
    //CheckInDay：表示当前订单的入住日期落在StartDate和EndDate之间，并且入住日期符合周设置时才需要判断其它条件是否担保，否则不需要担保
    public static final String  CHECK_IN_DAY = "CheckInDay";
    //StayDay：表示当前订单的客人只要有住在店里面的日期（[ArrivalDate,DepartureDate））落在StartDate和EndDate之间，
    // 并且入住日期符合周设置时才需要判断其它条件是否担保，否则不需要担保
    public static final String STAY_DAY = "StayDay";
    //担保类型
    public static final String FIRST_NIGHT_COST = "FirstNightCost";
    public static final String FULL_NIGHT_COST = "FullNightCost";

    public static String getDayStr(String data) {
        String s1 = data.substring(5, 7)+"月";
        String s2 = data.substring(8, 10)+"日";
        return s1+s2;
    }

    /**
     *
     * @param date 需要加减的日期
     * @param addOrL 传1代表加 传-1代表减
     * @return
     */
    public static String addOrLoer(String date,int addOrL){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt = sdf.parse(date);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
//            rightNow.add(Calendar.YEAR, -1);// 日期减1年
//            System.out.println(sdf.format(rightNow.getTime()));

//            rightNow.add(Calendar.MONTH, 3);// 日期加3个月
//            System.out.println(sdf.format(rightNow.getTime()));
            if (addOrL==1){
                rightNow.add(Calendar.DAY_OF_YEAR, 1);// 日期加10天
                return  sdf.format(rightNow.getTime());
            }else if (addOrL==-1){
                rightNow.add(Calendar.DAY_OF_YEAR, -1);// 日期加10天
                return  sdf.format(rightNow.getTime());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 计算两个日期之间相差的天数
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate)
    {
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            smdate=sdf.parse(sdf.format(smdate));
            bdate=sdf.parse(sdf.format(bdate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdate);
            long time2 = cal.getTimeInMillis();
            long between_days=(time2-time1)/(1000*3600*24);
            return Integer.parseInt(String.valueOf(between_days));
        }catch (ParseException e){
            e.printStackTrace();
         }


       return -100;
    }

    /**
     * 转化为需要返回的字符串
     * @param date
     */
    public static String dateChangeToString(String date) {
        String str1 = date.substring(0,4);//2017-18-18
        String str2 = date.substring(5,7);
        String str3 = date.substring(8,10);
        String newStr  = str1+"年"+str2+"月"+str3+"日";
        return  newStr;
    }

    public  static  String parseDataToStr(Date date){
        /**
         * 获取系统当前时间
         */
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        String strDate = format.format(date);
        return  strDate;
    }

    /**
     *字符串的日期格式的计算
     */
    public static int daysBetween(String smdate,String bdate) {
        try {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
        }catch (ParseException e){
            e.printStackTrace();
        }
       return -100;
    }

    public static String parseDate(String t){
        String[] strs=t.split("T");
        return  strs[0];
    }
    /*判断入住日期是否落在 startDate 和 endDate */
    public  static boolean checkInDayIsTrue(String startDate,String endDate,String weekSet){
       //酒店入住日期
        SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date sdate = localTime.parse(startDate);
            Date edate=  localTime.parse(endDate);
            Date ndate = localTime.parse(MyApp.gethEnterT());
            if(ndate.after(sdate)&& ndate.before(edate)&&checkWeekSet(weekSet)){
               return true;
            }
        }catch (ParseException e){
             e.printStackTrace();
        }
        return false;
    }

    /*判断日期是否符合周设置*/
    public static boolean checkWeekSet(String weekSet){//1,2,3,4,5,6,7
        String week []= weekSet.split(",");
        for (int i = 0; i < week.length; i++) {
            if(week[i].contains(getWeek(MyApp.gethEnterT()))){
                return true;
            }
        }
        return false;
    }

    /*通过日期得到星期几*/
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
            Week += "周天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            Week += "周一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            Week += "周二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            Week += "周三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            Week += "周四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            Week += "周五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            Week += "周六";
        }

        return Week;
    }

    /*判断时间大小*/
    public static boolean timeAsTime(String starT,String endT){
        DateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date end = df.parse(endT);//将字符串转换为date类型
            Date start = df.parse(starT);
            if(end.getTime()<start.getTime())//比较时间大小,dt1小于dt2
            {
                return true;
            }
            else
            {
               return false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean airTimeCheck(String starT,String endT){

        if (starT.equals("不限")){
            return false;
        }else{
            DateFormat df = new SimpleDateFormat("HH:mm");
            try {
                Date end = df.parse(endT);//将字符串转换为date类型
                Date start = df.parse(starT);
                if(end.getTime()<start.getTime())//比较时间大小,dt1小于dt2
                {
                    return true;
                }
                else
                {
                    return false;
                }
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        }

    }

    /**
     * 时间加法处理，用于入住离店最早和最晚时间的换算
     * 前后相差3个小时
     * @return
     */
    public  static  String timeAdd(String str){
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
//           String str="2017-12-23 14:22:00";
            Date dt=sdf.parse(str);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.HOUR, -3);
            Date dt1=rightNow.getTime();
            String reStr = sdf.format(dt1);
            String date= reStr.substring(0,10);
            String d = reStr.substring(10,18);
            return  date+"T"+d ;//date+"T"+d ;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public  static  String timeCheck(String str){
        try {
            Log.e("---", "timeAdd: "+str);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
//           String str="2017-12-23 14:22:00";
            Date dt=sdf.parse(str);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
//			rightNow.add(Calendar.YEAR,-1);//日期减1年
//			rightNow.add(Calendar.MONTH,3);//日期加3个月
//			rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
            rightNow.add(Calendar.HOUR, -3);
            Date dt1=rightNow.getTime();
            String reStr = sdf.format(dt1);
            String date= reStr.substring(0,10);
            String d = reStr.substring(10,18);
            return  date+"T"+d ;//;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public  static  String yiLongT(String str){
        try {
            Log.e("---", "timeAdd: "+str);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
//           String str="2017-12-23 14:22:00";
            Date dt=sdf.parse(str);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.HOUR, -3);
            Date dt1=rightNow.getTime();
            String reStr = sdf.format(dt1);
            String date= reStr.substring(0,10);
            String d = reStr.substring(10,18);
            return  date+" "+d ;//;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取北京时间
     * @return
     */
    public static String getBejiT(){
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String reStr = dff.format(new Date());
        String date= reStr.substring(0,10);
        String d = reStr.substring(10,18);
        return  date+"T"+d+"+08:00";
    }



}
