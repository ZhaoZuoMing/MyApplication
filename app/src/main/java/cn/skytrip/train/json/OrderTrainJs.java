package cn.skytrip.train.json;

import android.util.Log;

import com.mytables.MyApp;
import com.myuntils.MyAllUntils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.skytrip.train.model.Person;
import f.sky.flight.core.WebServUtil;

/**
 * Created by Administrator on 2016/12/21/021.
 */

public class OrderTrainJs extends  WebServUtil{
    static String  TAG = "OrderTrainJs";

    public  static String trainOrder(String checi,boolean is_accept_standing, String from_name, String from_code, String to_name, String to_code, List<Person>persons){
        String url = WebServUtil.TRAIN_ORDER_URL+"?jsonStr="+String.valueOf(getPara(checi,is_accept_standing,from_name,from_code,to_name,to_code,persons));
        PostMethod postMethod = new PostMethod(url);
        HttpClient httpClient = new HttpClient();
        try{
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode==WebServUtil.HTTP_STATUS_OK){
                String response = postMethod.getResponseBodyAsString();
                Log.e(TAG, "----提交订单返回的数据---： "+response);
                return response;
            }
        }catch (Exception e){

        }

        return null;
    }

    /**
     * 提交需要的参数
     * @return
     * String str = String.valueOf(getPara("100011","上海","111","北京","456"));
     */

    private static JSONObject getPara(String checi, boolean is_accept_standing,String from_station_name, String from_station_code, String to_station_name, String to_station_code, List<Person>persons){

        String para = TrainQuerJs.getMD5(WebServUtil.DOMEI_UERNAME+WebServUtil.TRAIN_ORDER_METHED+
                MyAllUntils.getNowDateSS()+TrainQuerJs.getMD5(WebServUtil.TRAIN_KEY));
       try {
           JSONObject js = new JSONObject();
           js.put("partnerid", WebServUtil.DOMEI_UERNAME);
           js.put("method",WebServUtil.TRAIN_ORDER_METHED);
           js.put("reqtime", MyAllUntils.getNowDateSS());
           js.put("sign",para);
           js.put("orderid","T1612221358562379549");
           js.put("train_date",MyApp.getTrainStartDate());
           js.put("from_station_name",from_station_name);
           js.put("from_station_code",from_station_code);
           js.put("to_station_name",to_station_name);
           js.put("to_station_code",to_station_code);
           js.put("checi",checi);
           js.put("is_accept_standing",is_accept_standing);//是否需要无座

           JSONArray array = new JSONArray();
           for (int i=0;i<persons.size();i++){
               JSONObject obj = new JSONObject();
               obj.put("passengerid",i+200);
               obj.put("passengersename",persons.get(i).getPassengersename());
               obj.put("passportseno",persons.get(i).getPassportseno());
               obj.put("passporttypeseid",persons.get(i).getPassengerid());
               obj.put("passporttypeseidname",persons.get(i).getPassporttypeseidname());
               obj.put("piaotype",persons.get(i).getPiaotype());
               obj.put("piaotypename",persons.get(i).getPiaotypename());
               obj.put("zwcode",persons.get(i).getZwcode());
               obj.put("zwname",persons.get(i).getZwname());
               obj.put("cxin",persons.get(i).getCxin());
               obj.put("price",persons.get(i).getPrice());
               obj.put("reason",persons.get(i).getReason());

               obj.put("province_name",persons.get(i).getProvince_name());
               obj.put("province_code",persons.get(i).getProvince_code());
               obj.put("school_code",persons.get(i).getSchool_code());
               obj.put("school_name",persons.get(i).getSchool_name());
               obj.put("student_no",persons.get(i).getStudent_no());
               obj.put("school_system",persons.get(i).getSchool_system());
               obj.put("enter_year",persons.get(i).getEnter_year());
               obj.put("preference_from_station_name",persons.get(i).getPreference_from_station_name());
               obj.put("preference_from_station_code",persons.get(i).getPreference_from_station_code());
               obj.put("preference_to_station_name",persons.get(i).getPreference_to_station_name());
               obj.put("preference_to_station_code",persons.get(i).getPreference_to_station_code());
               array.put(obj);
           }
           js.putOpt("passengers",array);
              return  js;
       }catch (JSONException e){
           Log.e("--OrderTrainJs==", "getPara: "+e.getMessage());
       }

      return null;
    }


}
