package com.changJson;

import android.util.Log;

import com.mymodels.UserMsg;
import com.myuntils.AndroidUtil;
import com.myuntils.MyAllUntils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import f.sky.flight.core.WebServUtil;
import f.sky.flight.model.Credential;

/**
 * Created by zhaody on 2017/11/1.
 */

public class QuerUserSyc {
    static String TAG = "---QuerUserSyc----";
    /**
     * 登录请求
     * @return
     * http://test.api.sky-trip.com/account/login?name=T2G00210&password=T2G00210&token=D42E7EDE1B7540AAA23E0599E234C406
     */
    public static UserMsg querLoginUser(String name,String paw){
        //JsonObject转Str
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Name",name);
            jsonObject.put("Password",paw);
            String data = String.valueOf(jsonObject);
            //获取系统当前的时间
            String timeStamp =   MyAllUntils.dateToStamp(MyAllUntils.getNowDateYMDS());
            String sign = MyAllUntils.getMD5(data+timeStamp+API.TOKEN);
            String url = API.LOGIN_URL+"data="+data+"&timestamp="+timeStamp+"&sign="+sign+"&token="+API.TOKEN;
            Log.e(TAG, "---登录接口--------: "+url );
            PostMethod postMethod = new PostMethod(url);
            HttpClient httpClient = new HttpClient();
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode== 200){
                String response = postMethod.getResponseBodyAsString();
                Log.e(TAG, "----登录返回的数据---： "+response);
                return  jsonTObj(response);
            }
        }catch (Exception e){
            Log.e(TAG, "trainQuery: "+e.getMessage() );
        }

        return null;
    }

    /**
     * 获取登记人信息
     * @return
     */
     public  static String querCredential(String ticket){
         String url = API.GETUER_MESSAGE+ticket;
         Log.e(TAG, "----查询乘机人信息接口---： "+url);
         PostMethod postMethod = new PostMethod(url);
         HttpClient httpClient = new HttpClient();
         try {
         int statusCode = httpClient.executeMethod(postMethod);
         if (statusCode== 200){
             String response = postMethod.getResponseBodyAsString();
             Log.e(TAG, "----查询乘机人信息---： "+response);
             return  response;
         }
     }catch (Exception e){
        Log.e(TAG, "trainQuery: "+e.getMessage() );
    }
        return null;
     }

    public static Credential jsonCredential(String response) {
        Credential credential = new Credential();
        try {
            JSONObject object = new JSONObject(response);
            JSONObject Data = object.getJSONObject("Data");
            JSONArray credentialArr = Data.getJSONArray("Credentials");
            for (int i = 0; i <credentialArr.length() ; i++) {
                JSONObject cb = credentialArr.getJSONObject(i);
                credential.setId(cb.getString("Id"));
                credential.setAccountId(cb.getString("AccountId"));
                credential.setType(cb.getString("Type"));
                if (cb.has("TypeName")){
                    credential.setTypeName(cb.getString("TypeName"));
                }
                credential.setNumber(cb.getString("Number"));
                credential.setFirstName(cb.getString("FirstName"));
                credential.setLastName(cb.getString("LastName"));
                credential.setCheckName(cb.getString("CheckName"));
                credential.setCheckFirstName(cb.getString("CheckFirstName"));
                credential.setCheckLastName(cb.getString("CheckLastName"));
                credential.setExpirationDate(cb.getString("ExpirationDate"));
                credential.setCountry(cb.getString("Country"));
                credential.setIssueCountry(cb.getString("IssueCountry"));
                credential.setBirthday(cb.getString("Birthday"));
                credential.setGender(cb.getString("Gender"));
            }
        }catch (JSONException e){
            Log.e(TAG, "jsonCredential:数据解析异常："+e.getMessage() );
        }

         return credential;
    }

    /**
     * 解析返回数据
     * @param response
     * @return
     */
    private static UserMsg jsonTObj(String response) {
        UserMsg userMsg = new UserMsg();
        try {
            JSONObject jsonObject  = new JSONObject(response);
            userMsg.setMessage(jsonObject.getString("Message"));
            userMsg.setSuccess(jsonObject.getBoolean("Status"));
            Log.e(TAG, "jsonTObj----: "+jsonObject.getString("Message") );
            if (jsonObject.getBoolean("Status")){
                Log.e(TAG, "jsonTObj----: "+jsonObject.getString("Message") );
                userMsg.setTimestamp(jsonObject.getString("Timestamp"));
                JSONObject jsData = jsonObject.getJSONObject("Data");
                userMsg.setTicket(jsData.getString("Ticket"));
                JSONObject Identity = jsData.getJSONObject("Identity");
                userMsg.setId(Identity.getString("Id"));
                userMsg.setName(Identity.getString("Name"));
                JSONObject Numbers = Identity.getJSONObject("Numbers");
                userMsg.setHrId(Numbers.getString("HrId"));
                userMsg.setCmsId(Numbers.getString("CmsId"));
                userMsg.setTmcId(Numbers.getString("TmcId"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG,"-----用户登录数据解析异常-----");
        }

        return userMsg;
    }
}
