package donm.sky.hotel.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import donm.sky.hotel.model.ArriveTime;
import f.sky.flight.core.WebServUtil;

/**
 * Created by Administrator on 2017/2/24/024.
 */

public class JsonTime {

    public static List<ArriveTime> getArrTime(String json){
        List<ArriveTime> list = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i <array.length() ; i++) {
                JSONObject obj = array.optJSONObject(i);
                ArriveTime t = new ArriveTime();
                t.setId(WebServUtil.parseInt(obj.getString("id")));
                t.setTime(obj.getString("time"));
                list.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
