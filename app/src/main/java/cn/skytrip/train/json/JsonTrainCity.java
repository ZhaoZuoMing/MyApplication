package cn.skytrip.train.json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.skytrip.train.model.TrainCity;
import cn.skytrip.train.model.TrainHot;
import cn.skytrip.train.model.TraintStation;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2016/12/13/013.
 */

public class JsonTrainCity {
    private static  TrainCity jsonCityDate(String content){
        TrainCity city = null;
        try {
            List<TrainHot> hotList = new ArrayList<>();
            List<TraintStation> stationList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(content);
            JSONArray hotArr  = jsonObject.optJSONArray("hot");
            int hl=hotArr.length();
            for (int i=0;i<hl;i++){

                TrainHot hot = new TrainHot();
                JSONObject hotBj = hotArr.getJSONObject(i);
                hot.setCode(hotBj.getString("code"));
                hot.setName(hotBj.getString("name"));
                hot.setNo(hotBj.getString("no"));
                hot.setScode(hotBj.getString("scode"));
                hotList.add(hot);
            }

            //其他城市解析
            JSONArray stationArr = jsonObject.optJSONArray("stations");
            int stationL = stationArr.length();
            for (int j = 0; j <stationL ; j++) {
                TraintStation stations = new TraintStation();
                JSONObject sObj = stationArr.getJSONObject(j);
                stations.setCode(sObj.getString("code"));
                stations.setName(sObj.getString("name"));
                stations.setNo(sObj.getString("no"));
                stations.setScode(sObj.getString("scode"));
                stations.setPy(sObj.getString("py"));
                stations.setJp(sObj.getString("jp"));
                stationList.add(stations);
            }

             city= new TrainCity(hotList,stationList);


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "----jsonCityDate:------ "+e.getMessage() );
        }
        return  city;
    }

    public  TrainCity getCityData(String content){
        return  jsonCityDate(content);
    }
}
