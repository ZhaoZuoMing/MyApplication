package cn.skytrip.train.json;

import android.util.Log;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import cn.skytrip.train.model.TrainObj;
import cn.skytrip.train.model.TrainQuerObj;

/**
 * Created by Administrator on 2016/12/15/015.
 */

public class TrainQuerJs {
   public  static  String TAG = "TrainQuerJsTest";
    /**
     * 获取链接接口
     * @param path
     * @return
     */
    public static TrainQuerObj trainQuery(String path, String partnerid, String method, String traindate, String reqtime, String key, String from_station, String to_station){
         String url = path+"?jsonStr="+String.valueOf(getJsonDate( partnerid, method, traindate, reqtime, key,from_station,to_station));
        Log.e(TAG, "trainQuery--url: "+url );
         PostMethod postMethod = new PostMethod(url);
         HttpClient httpClient = new HttpClient();
        try {
            int statusCode = httpClient.executeMethod(postMethod);
             if (statusCode== 200){
                 String response = postMethod.getResponseBodyAsString();
                 Log.e(TAG, "----火车票返回数据---： "+response);
                 return  jsonTObj(response);
             }
        }catch (Exception e){
            Log.e(TAG, "trainQuery: "+e.getMessage() );
        }


        return  null;
    }

    private static  TrainQuerObj jsonTObj(String content) throws  Exception{
        TrainQuerObj trainData = new TrainQuerObj();
        JSONObject js = new JSONObject(content);
        trainData.setSuccess(js.getBoolean("success"));
        trainData.setCode(js.getInt("code"));
        trainData.setMsg(js.getString("msg"));
        trainData.setSearchKey(js.getString("searchkey"));
        JSONArray dataArr = js.optJSONArray("data");
        List<TrainObj> list = new ArrayList<>();
        for (int i = 0; i <dataArr.length() ; i++) {
            TrainObj t = new TrainObj();
            JSONObject jd = dataArr.getJSONObject(i);
            t.setAccess_byidcard(jd.getString("access_byidcard"));
            t.setArrive_days(jd.getString("arrive_days"));
            t.setArrive_time(jd.getString("arrive_time"));
            t.setStart_time(jd.getString("start_time"));
            t.setCan_buy_now(jd.getString("can_buy_now"));
            t.setEdz_num(jd.getString("edz_num"));
            t.setEdz_price(jd.getString("edz_price"));
            t.setYdz_num(jd.getString("ydz_num"));
            t.setYdz_price(jd.getString("ydz_price"));
            t.setSwz_num(jd.getString("swz_num"));
            t.setSwz_price(jd.getString("swz_price"));
            t.setGjrw_num(jd.getString("gjrw_num"));
            t.setGjrw_price(jd.getString("gjrw_price"));
            t.setRw_num(jd.getString("rw_num"));//软卧
            t.setRw_price(jd.getString("rw_price"));//代表软卧上
            t.setRwx_price(jd.getString("rwx_price"));
            t.setYz_num(jd.getString("yz_num"));
            t.setYz_price(jd.getString("yz_price"));
//            t.setRws_num(jd.getString("rws_num"));
//            t.setRws_price(jd.getString("rws_price"));
            t.setWz_num(jd.getString("wz_num"));
            t.setWz_price(jd.getString("wz_price"));
            t.setFrom_station_name(jd.getString("from_station_name"));
            t.setTo_station_name(jd.getString("to_station_name"));
            t.setRun_time(jd.getString("run_time"));
            t.setTrain_code(jd.getString("train_code"));
            t.setTrain_no(jd.getString("train_no"));
            t.setTrain_start_date(jd.getString("train_start_date"));
            t.setTrain_type(jd.getString("train_type"));
            t.setSale_date_time(jd.getString("sale_date_time"));
//            t.setYws_num(jd.getString("ywz_num"));
            t.setYws_price(jd.getString("ywz_price"));//硬卧上价格
            t.setYw_num(jd.getString("yw_num"));
            t.setYw_price(jd.getString("yw_price"));
//            t.setYwx_num(jd.getString("ywx_num"));
            t.setYwx_price(jd.getString("ywx_price"));//硬卧下价格
            t.setTdz_num(jd.getString("tdz_num"));
            t.setTdz_price(jd.getString("tdz_price"));
            t.setRz_num(jd.getString("rz_num"));
            t.setRz_price(jd.getString("rz_price"));
            t.setQtxb_num(jd.getString("qtxb_num"));
            t.setQtxb_price(jd.getString("qtxb_price"));
            list.add(t);
        }
        trainData.setTrain_list(list);
        return  trainData;

    }
   private  static  JSONObject getJsonDate(String partnerid,String method,String traindate,String reqtime,String key,String from_station,String to_station){
       JSONObject js = new JSONObject();
        String para = getMD5(partnerid+method+reqtime+getMD5(key));
       try{
           js.put("partnerid",partnerid);
           js.put("method",method);
           js.put("reqtime",reqtime);
           js.put("sign",para);
           js.put("train_date",traindate);
           js.put("from_station",from_station);
           js.put("to_station",to_station);
           js.put("purpose_codes","ADULT");
           js.put("needdistance","1");
       }catch (Exception e){
           Log.e("----json---", "getJsonDate: "+e.getMessage() );
       }
       return  js;
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
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            Log.e("----加密--", "MD5加密出现错误");
        }
        return  null;

    }

}
