package com.myuntils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.mymodels.MyLocation;
import com.mytables.AppT;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import f.sky.flight.core.WebServUtil;

/**
 * Created by Administrator on 2017/4/12/012.
 * 地理位置提供器
 */

public class LocationUntil extends AppT{

    private LocationManager locationManager;//位置服务
    private Location location;
    private String provider;//位置提供器
    private  String ak = "dYbb06gbPmDOjGMvmfarnlHGuAoOQGtX";

    public String initLocation(){
        locationManager  = (LocationManager) getSystemService(LOCATION_SERVICE);
        provider  = judgeProvider(locationManager);
        if (provider != null) {//有位置提供器的情况
            //为了压制getLastKnownLocation方法的警告
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return "";
            }
            location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
               return getLocation(location);//得到当前经纬度并开启线程去反向地理编码
            } else {
                AndroidUtil.shortToast(this,"暂时无法获得当前位置");
            }
            }else{//不存在位置提供器的情况
              return  "";
           }
          return "";
        }

    private String  getLocation(Location location) {
        String latitude = location.getLatitude()+"";
        String longitude = location.getLongitude()+"";
        return  "http://api.map.baidu.com/geocoder/v2/?ak=" +
                 ak +"&callback=renderReverse&location="+latitude+","+longitude+"&output=json&pois=0";
    }

    /**
     * 解析得到的城市及其他数据
     * @param url
     * @return
     */
    public MyLocation getLocationData(String url){
        PostMethod postMethod = new PostMethod(url);
        HttpClient httpClient = new HttpClient();
        try{
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode== 200){
                String response = postMethod.getResponseBodyAsString();
                return jsonData(response);
            }
        }catch (Exception e){

        }
        return null;
    }

    private MyLocation jsonData(String response) {
        MyLocation myLocation = new MyLocation();
        response = response.replace("renderReverse&&renderReverse","");
        response = response.replace("(","");
        response = response.replace(")","");
        try {
            JSONObject jObject  = new JSONObject(response);
             myLocation.setStatus(WebServUtil.parseInt(jObject.getString("status")));
             JSONObject obj = jObject.getJSONObject("result");
             myLocation.setFormatted_address(obj.getString("formatted_address"));
             myLocation.setBusiness(obj.getString("business"));
             myLocation.setSematic_description(obj.getString("sematic_description"));
             myLocation.setCityCode(obj.getString("cityCode"));

            JSONObject objRes = obj.getJSONObject("location");
             myLocation.setLat(WebServUtil.parseDouble(objRes.getString("lat")));
             myLocation.setLng(WebServUtil.parseDouble(objRes.getString("lng")));


             JSONObject objAddress = obj.getJSONObject("addressComponent");
             myLocation.setCountry(objAddress.getString("country"));
             myLocation.setCountry_code(objAddress.getString("country_code"));
             myLocation.setProvince(objAddress.getString("province"));
             myLocation.setCity(objAddress.getString("city"));
             myLocation.setDirection(objAddress.getString("direction"));
             myLocation.setStreet(objAddress.getString("street"));
             myLocation.setStreet_number(objAddress.getString("street_number"));
             myLocation.setDistrict(objAddress.getString("district"));
             myLocation.setAdcode(objAddress.getString("adcode"));
             myLocation.setDistance(objAddress.getString("distance"));


        }catch (JSONException e){
             e.printStackTrace();
        }
        return myLocation;
    }

    /**
     * 判断是否有可用的内容提供器
     * @return 不存在返回null
     */
    private String judgeProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        if(prodiverlist.contains(LocationManager.NETWORK_PROVIDER)){
            return LocationManager.NETWORK_PROVIDER;
        }else if(prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;
        }else{
            Toast.makeText(LocationUntil.this,"没有可用的位置提供器",Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
