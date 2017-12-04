package f.sky.flight.core;

import android.util.Log;

import com.changJson.API;
import com.mytables.MyApp;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import f.sky.flight.model.Cabin;
import f.sky.flight.model.ChangeDetail;
import f.sky.flight.model.EndorsementDetail;
import f.sky.flight.model.FlightResModel;
import f.sky.flight.model.FlightSegment;
import f.sky.flight.model.RefundChange;
import f.sky.flight.model.RefundDetail;
import f.sky.flight.model.Rules;
import f.sky.flight.model.Variables;

/**
 * Created by zhaody on 2017/11/15.
 * 新机票查询类
 */

public class QuerNewFlightService extends WebServUtil {
    static String TAG = "---NewFlightService----";
    public  static FlightResModel querNewFlightList(String date,String fromCode,String toCode,String ticket){
          long passenger = Long.parseLong(MyApp.getSp().getString(API.ID,""));
          String url = API.SEARCH_FLIGHT_LIST+date+"&fromCode="+fromCode+"&toCode="+toCode+"&passenger="+passenger+"&ticket="+ticket;
        Log.e("--机票查询接口---", "---URL:"+url );
        PostMethod postMethod = new PostMethod(url);
        HttpClient httpClient = new HttpClient();
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode== 200){
                String response = postMethod.getResponseBodyAsString();
                return  jsonFlightListObj(response);
            }
        }catch (Exception e){
            Log.e(TAG, "trainQuery: "+e.getMessage() );
        }
        return null;
    }

    /**
     * 解析列表数据
     * @param response
     * @return
     */
    private static FlightResModel jsonFlightListObj(String response) {
        FlightResModel resModel = new FlightResModel();
        try {
            JSONObject resObj = new JSONObject(response);
            resModel.setStatus(resObj.getBoolean("Status"));
            resModel.setCode(resObj.getString("Code"));
            resModel.setMessage(resObj.getString("Message"));
            resModel.setTimestamp(resObj.getInt("Timestamp"));
            //请求数据成功就进入解析
            if (resModel.isStatus()&&resModel.getCode().equals("Success")){

                JSONArray data = resObj.getJSONArray("Data");
                JSONObject dataObj = data.getJSONObject(0);

                JSONArray FlightRoutes = dataObj.getJSONArray("FlightRoutes");
                List<FlightSegment> segmentList = new ArrayList<>();
                for (int i = 0; i <FlightRoutes.length() ; i++) {
                    JSONObject ftRoutObj = FlightRoutes.getJSONObject(i);
                    JSONArray flightsArr = ftRoutObj.getJSONArray("FlightSegments");
                    for (int j = 0; j <flightsArr.length() ; j++) {
                        JSONObject segment = flightsArr.getJSONObject(j);
                        segmentList.add(parseSegs(segment,dataObj));
                    }
                }
                resModel.setFlightSegmentList(segmentList);
//                Log.e(TAG, "解析的航班数量---: "+parseSegs(flightsArr).size()+"个--卧槽" );
                return resModel;
            }
           }catch (JSONException e){
            Log.e(TAG, "机票查询数据解析异常---： "+e);
        }

        return null;
    }

    /**
     *  解析航班数据
     * @param segObj
     */
    private static FlightSegment parseSegs(JSONObject segObj,JSONObject dataObj)throws JSONException {
            FlightSegment seg = new FlightSegment();
            //出发和到达城市代码
            seg.setFromCity(dataObj.getString("FromCity"));
            seg.setToCity(dataObj.getString("ToCity"));
            //-----
            seg.setLowestFare(new BigDecimal(segObj.getString("LowestFare")));
            seg.setLowestDiscount(new BigDecimal(segObj.getString("LowestDiscount")));
            seg.setTax(new BigDecimal(segObj.getString("Tax")));
            seg.setYFare(new BigDecimal(segObj.getString("YFare")));
            seg.setCFare(new BigDecimal(segObj.getString("CFare")));
            seg.setFFare(new BigDecimal(segObj.getString("FFare")));
            seg.setLowestCabinCode(segObj.getString("LowestCabinCode"));
            seg.setNumber(segObj.getString("Number"));
            seg.setAirline(segObj.getString("Airline"));
            seg.setAirlineName(segObj.getString("AirlineName"));
            seg.setPlaneType(segObj.getString("PlaneType"));
            seg.setPlaneTypeDescribe(segObj.getString("PlaneTypeDescribe"));
            String codeNumber = segObj.getString("CodeShareNumber");
            if (codeNumber.equals("null")){
                seg.setCodeShareNumber("");
            }else {
                seg.setCodeShareNumber(segObj.getString("CodeShareNumber"));
            }
            seg.setCarrier(segObj.getString("Carrier"));
            seg.setCarrierName(segObj.getString("CarrierName"));
            seg.setToAirport(segObj.getString("ToAirport"));
            seg.setFromAirport(segObj.getString("FromAirport"));
            seg.setFromAirportName(segObj.getString("FromAirportName"));
            seg.setFromCityName(segObj.getString("FromCityName"));
            seg.setToAirportName(segObj.getString("ToAirportName"));
            seg.setToCityName(segObj.getString("ToCityName"));
            seg.setTakeoffTime(segObj.getString("TakeoffTime"));
            seg.setArrivalTime(segObj.getString("ArrivalTime"));
            seg.setFromTerminal(segObj.getString("FromTerminal"));
            seg.setToTerminal(segObj.getString("ToTerminal"));
//            seg.setStopCities(segObj.getString("StopCities"));
            seg.setDistance(WebServUtil.parseInt(segObj.getString("ArrivalTime")));
            seg.setFlyTime(WebServUtil.parseInt(segObj.getString("FlyTime")));
            seg.setFlyTimeName(segObj.getString("FlyTimeName"));
//            //---解析Variables
            seg.setVariables(parseVariables(segObj));
            //---解析仓位数据
            seg.setCabins(paseCabins(segObj));
            /**----添加到List中**/


        return seg;
    }

    /**
     * 解析仓位
     * @param segObj
     * @return
     * @throws JSONException
     */
    private static List<Cabin> paseCabins(JSONObject segObj) throws  JSONException{
        JSONArray cabinsArr = segObj.optJSONArray("Cabins");
        List<Cabin> cabinList = new ArrayList<>();
        for (int j = 0; j <cabinsArr.length() ; j++) {
            JSONObject cabObj = (JSONObject)cabinsArr.get(j);
            Cabin cabin = new Cabin();
            cabin.setAllowOrder(cabObj.getBoolean("IsAllowOrder"));
            cabin.setFlightNumber(cabObj.getString("FlightNumber"));
//                         //未启用字段
//                        cabin.setPolicyId("");//cabObj.getString("PolicyId")
//                        cabin.setFlightPolicy("");//cabObj.getString("FlightPolicy")
//                        cabin.setLowerSegment("");//cabObj.getString("LowerSegment")
                        cabin.setSupplierType(cabObj.getString("SupplierType"));
                        cabin.setFareType(WebServUtil.parseInt(cabObj.getString("FareType")));
                        cabin.setName(cabObj.getString("Name"));
                        cabin.setType(WebServUtil.parseInt(cabObj.getString("Type")));
                        cabin.setCode(cabObj.getString("Code"));
                        cabin.setTicketPrice(Float.parseFloat(cabObj.getString("TicketPrice")));
                        cabin.setSettlePrice(Float.parseFloat(cabObj.getString("SettlePrice")));
                        cabin.setSalesPrice(Float.parseFloat(cabObj.getString("SalesPrice")));
                        cabin.setTax(Float.parseFloat(cabObj.getString("Tax")));
                        cabin.setReward(Float.parseFloat(cabObj.getString("Reward")));
                        cabin.setDiscount(Float.parseFloat(cabObj.getString("Discount")));
                        cabin.setCount(WebServUtil.parseInt(cabObj.getString("Count")));

                        cabin.setTypeName(cabObj.getString("TypeName"));
                        cabin.setFareTypeName(cabObj.getString("FareTypeName"));
                        cabin.setSupplierTypeName(cabObj.getString("SupplierTypeName"));
                        //---解析cabin中的Variable
                         cabin.setVariables(parseCabinVariable(cabObj));
                         //---解析RefundChange
                         cabin.setRefundChange(parseRefundChange(cabObj));
                         //---解析违规政策
                         cabin.setRules(parseRules(cabObj));
            /***---添加Cabin至lis***/
            cabinList.add(cabin);
        }
         return cabinList;
    }

    /**
     * 退改签规则
     * @param cabObj
     * @return
     * @throws JSONException
     */
    private static Rules parseRules(JSONObject cabObj) throws JSONException {
        Log.e(TAG, "parseRules: "+cabObj.getString("Rules") );
      if (cabObj.getString("Rules").equals("null")){
          return null;
      }else {
          JSONObject ruleObj = cabObj.getJSONObject("Rules");
          Rules rules =new Rules();
          if (ruleObj.has("FlightIsMustBookLowestPrice")){
              rules.setFlightIsMustBookLowestPrice(ruleObj.getString("FlightIsMustBookLowestPrice"));
          }
          if (ruleObj.has("FlightLowerFare")){
              rules.setFlightLowerFare(ruleObj.getString("FlightLowerFare"));
          }
          if (ruleObj.has("FlightDiscountLimit")){
                rules.setFlightDiscountLimit(ruleObj.getString("FlightDiscountLimit"));
          }
          return rules;
      }
    }

    /**
     * 解析RefundChange
     * @param cabObj
     * @return
     */
    private static RefundChange parseRefundChange(JSONObject cabObj)throws JSONException {
        //变更信息解析RefundChange
                        RefundChange refundChange = new RefundChange();
                        JSONObject refObj = cabObj.optJSONObject("RefundChange");
                        refundChange.setAirline(refObj.getString("Airline"));
                        refundChange.setCabin(refObj.getString("Cabin"));
                        String remark = refObj.getString("Remark");
                        if (remark.equals("null")){
                            refundChange.setRemark("");
                        }else {
                            refundChange.setRemark(remark);
                        }
                        refundChange.setBaggageAllowance(refObj.getString("BaggageAllowance"));
                        //---解析RefundDetail--
                       refundChange.setRefundDetail(parseRefundDetail(refObj));
                       //--解析change
                     refundChange.setChangeDetail(paseChangeDetail(refObj));
                       //EndorsementDetailObj
                     refundChange.setEndorsementDetail(parseEndorsementDetailObj(refObj));
        return refundChange;
    }

    /**
     * refundChange --中的EndorsementDetailObj
     * @param refObj
     * @return
     */
    private static EndorsementDetail parseEndorsementDetailObj(JSONObject refObj) throws  JSONException{
        JSONObject EndorsementDetailObj = refObj.optJSONObject("EndorsementDetail");
                        EndorsementDetail endorsementDetail = new EndorsementDetail();
                        JSONArray Endorsements = EndorsementDetailObj.optJSONArray("Endorsements");
                        String en_Arr[] = new String[Endorsements.length()];
                        for (int n = 0; n <Endorsements.length() ; n++) {
                            en_Arr[n] = Endorsements.getString(n);
                        }
                        endorsementDetail.setEndorsements(en_Arr);
        return endorsementDetail;
    }

    /**
     * refundChange中的changeDetail
     * @param refObj
     * @return
     */
    private static ChangeDetail paseChangeDetail(JSONObject refObj) throws  JSONException{
        //RefundChange--中的 --ChangeDetail
                        JSONObject cDetail = refObj.optJSONObject("ChangeDetail");
                        ChangeDetail changeDetail = new ChangeDetail();
                        JSONArray changeBefors = cDetail.optJSONArray("Befores");
                        String c_beforArr[] = new String[changeBefors.length()];
                        for (int a = 0; a < changeBefors.length(); a++) {
                            c_beforArr[a] = changeBefors.getString(a);
                        }
                        changeDetail.setBefores(c_beforArr);
                        //--change_BeforeEns
                        JSONArray changeBeforeEns = cDetail.optJSONArray("BeforeEns");
                        String c_BeforeEnsArr[] = new String[changeBeforeEns.length()];
                        for (int b = 0; b < changeBeforeEns.length(); b++) {
                            c_BeforeEnsArr[b] = changeBeforeEns.getString(b);
                        }
                        changeDetail.setAfterEns(c_BeforeEnsArr);
                        //--change-Afters
                        JSONArray changeAfters =cDetail.optJSONArray("Afters");
                        String c_Afters[] = new String[changeAfters.length()];
                        for (int c = 0; c <changeAfters.length() ; c++) {
                            c_Afters[c] = changeAfters.getString(c);
                        }
                        changeDetail.setAfters(c_Afters);
                        //--change-AfterEns
                        JSONArray changeAfterEns = cDetail.optJSONArray("AfterEns");
                        String c_AfterEns[] =new String[changeAfterEns.length()] ;
                        for (int d = 0; d <changeAfterEns.length() ; d++) {
                            c_AfterEns[d] = changeAfterEns.getString(d);
                        }
                        changeDetail.setAfterEns(c_AfterEns);
                        return changeDetail;
    }

    /**
     * RefundDetail json
     * @param refObj
     * @return
     */
    private static RefundDetail parseRefundDetail(JSONObject refObj) throws  JSONException{
        RefundDetail refundDetail = new RefundDetail();
                        JSONObject redObj = refObj.optJSONObject("RefundDetail");
                        //---Befores解析
                        JSONArray befores = redObj.getJSONArray("Befores");
                        String bArr[] =new String[befores.length()];
                        for (int k = 0; k <befores.length() ; k++) {
                            bArr[k]=befores.getString(k);
                        }
                        refundDetail.setBefores(bArr);
                        //---BeforeEns
                        JSONArray BeforeEns = redObj.getJSONArray("BeforeEns");
                        String endArr[] = new String[BeforeEns.length()];
                        for (int h = 0; h <BeforeEns.length() ; h++) {
                            endArr[h] = BeforeEns.getString(h);
                        }
                        refundDetail.setBeforeEns(endArr);
                        //---Afters
                        JSONArray Afters = redObj.getJSONArray("Afters");
                        String afArr[] = new String[Afters.length()];
                        for (int f = 0; f <Afters.length() ; f++) {
                            afArr[f] = Afters.getString(f);
                        }
                        refundDetail.setAfters(afArr);
                        //---AfterEns
                        JSONArray AfterEns = redObj.getJSONArray("AfterEns");
                        String afenArr[] = new String[AfterEns.length()];
                        for (int e = 0; e < AfterEns.length(); e++) {
                            afenArr[e] = AfterEns.getString(e);
                        }
                        refundDetail.setAfterEns(afenArr);

                   return refundDetail;
    }

    private static Variables parseCabinVariable(JSONObject cabObj) throws JSONException{
        //Variables解析
                        Variables cv = new Variables();
                        JSONObject cvObj = cabObj.optJSONObject("Variables");
                        cv.setFareBasis(cvObj.getString("FareBasis"));
                        cv.setEi(cvObj.getString("Ei"));
                        if (cvObj.has("AccountCode")){
                            cv.setAccountCode(cvObj.getString("AccountCode"));
                        }
                        cv.setCabinKey(cvObj.getString("CabinKey"));
                        return cv;
    }

    /**
     * 解析解析Variables
     * @param segObj
     */
    private static Variables parseVariables(JSONObject segObj) throws JSONException{
        Variables variables = new Variables();
                    JSONObject vabObj = segObj.getJSONObject("Variables");
                    variables.setFlightRph(vabObj.getString("FlightRph"));
                    variables.setFlightKey(vabObj.getString("FlightKey"));
        return variables;
    }
}
