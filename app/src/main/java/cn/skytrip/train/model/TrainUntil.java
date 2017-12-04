package cn.skytrip.train.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.myuntils.MyAllUntils;

/**
 * Created by Administrator on 2016/12/29/029.
 */

public class TrainUntil {

    public static String addDay(String data,int days){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date dt;
        try {
            dt = sdf.parse(data);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_YEAR,days);//日期加1天
            Date dt1=rightNow.getTime();
            String reStr = sdf.format(dt1);
            return reStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String lowDay(String data,int num){
        if (data.equals(MyAllUntils.getNowDateF())){
            return MyAllUntils.getNowDateF();
        }else{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date nowDate = null;
            try {
                nowDate = df.parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //如果需要向后计算日期 -改为+
            Date newDate2 = new Date(nowDate.getTime() - (long)num * 24 * 60 * 60 * 1000);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateOk = simpleDateFormat.format(newDate2);
            return dateOk;
        }

    }


}
