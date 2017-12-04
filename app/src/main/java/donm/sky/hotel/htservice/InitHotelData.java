package donm.sky.hotel.htservice;

import android.util.Log;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import donm.sky.hotel.model.BookingRules;
import donm.sky.hotel.model.Cities;
import donm.sky.hotel.model.HotelListSearchResult;
import donm.sky.hotel.model.Hotels;
import donm.sky.hotel.model.NightlyRates;
import donm.sky.hotel.model.RatePlans;
import donm.sky.hotel.model.Room;
import com.mytables.MyApp;
import f.sky.flight.core.WebServUtil;

/**
 * Created by Administrator on 2016/12/26/026.
 * 初始化酒店静态数据
 */

public class InitHotelData extends WebServUtil {
     private static  final  String TAG = "InitHotelData";
    /**
     * 酒店数据查询
     * @return
     */
    public static  HotelListSearchResult hotelListSearCh(String ArrivalDate,String DepartureDate,String CityId,String QueryText,int ClientId,int index,String starRate)throws Exception{
           PostMethod postMethod = new PostMethod(WebServUtil.HOTEL_RELAY_API);
           Log.e(TAG, "hotelListSearCh: ---提交数据接口---" +builderSearChData(ArrivalDate,DepartureDate,CityId,QueryText,ClientId, index,starRate));
            byte b [] = builderSearChData(ArrivalDate,DepartureDate,CityId,QueryText,ClientId, index,starRate).getBytes(ENCODE);
            InputStream is = new ByteArrayInputStream(b, 0, b.length);
            RequestEntity re = new InputStreamRequestEntity(is, b.length,
                    CONTENT_TYPE);
            postMethod.setRequestEntity(re);
            HttpClient httpClient = new HttpClient();
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == WebServUtil.HTTP_STATUS_OK) {
                String response = postMethod.getResponseBodyAsString();
//                Log.e(TAG, "hotelListSearCh:-----得到的Json数据： "+response);
                return  parseSearchData(response);
            }
        return null;
    }




    /**
     * 构建请求的参数
     * @return
     */
    private static  String builderSearChData(String ArrivalDate,String DepartureDate,String CityId,String QueryText,int ClientId,int index,String starRate){
        StringBuilder  soapRequestData = buildSoapHeader();
        soapRequestData.append("<"+WebServUtil.SEARCH_HOTEL_LIST+" xmlns=\""+ WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + WebServUtil.HBE_USER_NAME + "</UserID>");
        soapRequestData.append("<Password>" +WebServUtil.HBE_USER_PAW+ "</Password>");
        soapRequestData.append("<Patameter>");
        soapRequestData.append("<Local>"+"zh_CN"+"</Local>");
        soapRequestData.append("<Action>"+"List"+"</Action>");
        soapRequestData.append("<ClientId>"+ClientId+"</ClientId>");
        soapRequestData.append("<Switch>"+"Local"+"</Switch>");
        soapRequestData.append("<NeedAgreement>"+true+"</NeedAgreement>");
        soapRequestData.append("<ListCondition>");
        soapRequestData.append("<ArrivalDate>"+ ArrivalDate +"</ArrivalDate>");
        soapRequestData.append("<DepartureDate>"+ DepartureDate +"</DepartureDate>");
        soapRequestData.append("<CityId>"+ CityId +"</CityId>");//上海CityCode
        soapRequestData.append("<QueryText>"+ QueryText +"</QueryText>");
        soapRequestData.append("<QueryType>"+ "Intelligent" +"</QueryType>");
        soapRequestData.append("<PaymentType>"+ "All" +"</PaymentType>");
        soapRequestData.append("<PageIndex>"+index+"</PageIndex>");
//        soapRequestData.append("<ProductProperties>"+ "" +"</ProductProperties>"); //产品类型
//        soapRequestData.append("<Facilities>"+ "" +"</Facilities>");//设施
          soapRequestData.append("<StarRate>"+ starRate +"</StarRate>");////推荐星级
//        soapRequestData.append("<GroupId>"+ "" +"</GroupId>");//酒店集团编码
        soapRequestData.append("<LowRate>"+ MyApp.LowRate +"</LowRate>");//最小价格
        soapRequestData.append("<HighRate>"+ MyApp.HighRate +"</HighRate>");//最大价格
//        soapRequestData.append("<DistrictId>"+ "" +"</DistrictId>");//地区编码
//        soapRequestData.append("<BusinessZoneId>"+ "" +"</BusinessZoneId>");//商业区
//        soapRequestData.append("<LocationId>"+ "" +"</LocationId>");//地标编码 无效

//
        soapRequestData.append("<Sort>"+"Default"+"</Sort>");
        soapRequestData.append("<PageSize>"+10+"</PageSize>");
        soapRequestData.append("<CustomerType>"+"None"+"</CustomerType>");//宾客类型
        soapRequestData.append("</ListCondition>");
//        soapRequestData.append("<ThemeIds>"+0+"</ThemeIds>");//
//        soapRequestData.append("<CheckInPersonAmount>"+1+"</CheckInPersonAmount>");//房间入住人数
//          soapRequestData.append("<ResultType>"+"json"+"</ResultType>");//返回信息类型
        //-----------DetailCondition----酒店详细信息查询时需要提交--------
//        soapRequestData.append("<DetailCondition>");
//        soapRequestData.append("<ArrivalDate>"+ArrivalDate+"</ArrivalDate>");
//        soapRequestData.append("<DepartureDate>"+DepartureDate+"</DepartureDate>");
//        soapRequestData.append("<HotelIds>"+""+"</HotelIds>");
//        soapRequestData.append("<RoomTypeId>"+""+"</RoomTypeId>");
//        soapRequestData.append("<RatePlanId>"+""+"</RatePlanId>");
//        soapRequestData.append("<PaymentType>"+""+"</PaymentType>");
//        soapRequestData.append("<CheckInPersonAmount>"+""+"</CheckInPersonAmount>");
//        soapRequestData.append("<Options>"+""+"</Options>");
//        soapRequestData.append("</DetailCondition>");
        soapRequestData.append("</Patameter>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.SEARCH_HOTEL_LIST + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
    /**
     * 解析初次列表返回的数据
     * @param response
     * @return
     */
    private static HotelListSearchResult parseSearchData(String response) {
        List<Hotels> myHotels = new ArrayList<>();
        HotelListSearchResult result = new HotelListSearchResult();
        Element root;
        Document doc;
        SAXReader reader = new SAXReader();
        try {
            doc = reader.read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            root = doc.getRootElement();
            Element obj = root.element("Body")
                    .element("HotelListSearchResponse")
                    .element("HotelListSearchResult");
            result.setSuccess(parseBoolean(obj.elementTextTrim("Success")));
            result.setMessage(obj.elementTextTrim("Message"));
            Element resultObj =obj.element("ResultObject").element("Result").element("Hotels");
            List<Element> hotels = resultObj.elements("Hotel");
            for (Element e:hotels) {
                Hotels hotel = new Hotels();
                hotel.setLowRate(e.elementTextTrim("LowRate"));
                hotel.setHotelId(parseInt(e.elementTextTrim("HotelId")));
                hotel.setCurrencyCode(e.elementTextTrim("CurrencyCode"));
                hotel.setDistance(e.elementTextTrim("Distance"));
                hotel.setBookingRules(parseBookRules(e));
                hotel.setRooms(parseRooms(e));
                myHotels.add(hotel);
            }

            result.setHotels(myHotels);
            return  result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析酒店所有房间数据
     * @param e
     * @return
     */
    private static List<Room> parseRooms(Element e) {
        List<Room> rooms = new ArrayList<>();
        Element eleRoom = e.element("Rooms");
        List<Element> e_rooms = eleRoom.elements("Room");
        for (Element eRoom:e_rooms){
            Room r = new Room();
            r.setName(eRoom.elementTextTrim("Name"));
            r.setRoomId(eRoom.elementTextTrim("RoomId"));
             Log.e("-----解析的RoomId" , eRoom.elementTextTrim("RoomId")+"");
            r.setRatePlans(parseRatePlans(eRoom));

            rooms.add(r);
        }

        return  rooms;
    }

    /**
     * 解析RatePlans
     * @param
     * @return
     */
    private static List<RatePlans> parseRatePlans(Element e) {
        List<RatePlans> ratePlans = new ArrayList<>();
        Element eleRatePlans = e.element("RatePlans");
        List<Element> rates = eleRatePlans.elements("RatePlan");

        for (Element eRates:rates){
            RatePlans r =new RatePlans();
            r.setRatePlanId(eRates.elementTextTrim("RatePlanId"));
            r.setRatePlanName(eRates.elementTextTrim("RatePlanName"));
            r.setMinAmount(eRates.elementTextTrim("MinAmount"));
            r.setMinDays(parseInt(eRates.elementTextTrim("MinDays")));
            r.setMaxDays(parseInt(eRates.elementTextTrim("MaxDays")));
            r.setPaymentType(eRates.elementTextTrim("PaymentType"));
            r.setCurrentAlloment(parseInt(eRates.elementTextTrim("CurrentAlloment")));
            r.setInstantConfirmation(parseBoolean(eRates.elementTextTrim("InstantConfirmation")));
            r.setLastMinuteSale(parseBoolean(eRates.elementTextTrim("IsLastMinuteSale")));
            r.setStartTime(eRates.elementTextTrim("StartTime"));
            r.setEndTime(eRates.elementTextTrim("EndTime"));
            r.setTotalRate(eRates.elementTextTrim("TotalRate"));
            r.setAverageRate(eRates.elementTextTrim("AverageRate"));
            r.setCurrencyCode(eRates.elementTextTrim("CurrencyCode"));
            r.setCoupon(eRates.elementTextTrim("Coupon"));
            r.setNightlyRates(parseNightlyRates(eRates));
            r.setBookingRuleIds(eRates.elementTextTrim("BookingRuleIds"));
            r.setRoomTypeId(eRates.elementTextTrim("RoomTypeId"));
            r.setHotelCode(parseInt(eRates.elementTextTrim("HotelCode")));
            r.setInvoiceMode(eRates.elementTextTrim("InvoiceMode"));
            ratePlans.add(r);
        }
        return  ratePlans;

    }

    /**
     * 解析夜间协议内容
     * @param e
     * @return
     */
    private static List<NightlyRates> parseNightlyRates(Element e) {
        List<NightlyRates>  nightlyRates = new ArrayList<>();
        Element eleRatePlans = e.element("NightlyRates");
        List<Element> rates = eleRatePlans.elements("NightlyRate");
        for (Element n:rates){
            NightlyRates ni = new NightlyRates();
            ni.setAddBed(n.elementTextTrim("AddBed"));
            ni.setMember(n.elementTextTrim("Member"));
            ni.setCost(n.elementTextTrim("Cost"));
            ni.setStatus(parseBoolean(n.elementTextTrim("Status")));
            ni.setDate(n.elementTextTrim("Date"));
            nightlyRates.add(ni);
        }
        return  nightlyRates;
    }

    /**
     * 解析酒店预订协议
     * @param resultObj
     * @return
     */
    private static List<BookingRules> parseBookRules(Element resultObj) {
        List<BookingRules> list = new ArrayList<>();
        Element bookingRules = resultObj.element("BookingRules");
        List<Element> BookingRule = bookingRules.elements("BookingRule");
        for (Element eRules: BookingRule) {
            BookingRules b = new BookingRules();
            Log.e(TAG, "parseBookRules: =====:"+ eRules.elementTextTrim("BookingRuleId"));
            b.setBookingRuleId(eRules.elementTextTrim("BookingRuleId"));
            b.setDescription(eRules.elementTextTrim("Description"));
            b.setTypeCode(eRules.elementTextTrim("TypeCode"));
            b.setDateType(eRules.elementTextTrim("DateType"));
            b.setStartDate(eRules.elementTextTrim("StartDate"));
            b.setEndDate(eRules.elementTextTrim("EndDate"));
            b.setStartHour(eRules.elementTextTrim("StartHour"));
            b.setEndHour(eRules.elementTextTrim("EndHour"));
            list.add(b);
        }
       return  list;
    }

    public static  List<Cities> getHotelCities(String re){
        List<Cities> list = new ArrayList<>();
      try{
          JSONArray  array = new JSONArray(re);
          for (int i = 0; i <array.length() ; i++) {
              JSONObject obj = array.getJSONObject(i);
              Cities c = new Cities();
              c.setCode(obj.getString("CityCode"));
              c.setName(obj.getString("CityName"));
              if (obj.has("CityEName")){
                  c.seteName(obj.getString("CityEName"));
              }
              c.setProvinceId(obj.getString("ProvinceId"));
              c.setProvinceName(obj.getString("ProvinceName"));
              c.setStatus(parseInt(obj.getString("Status")));
              c.setSortIndex(parseInt(obj.getString("SortIndex")));
              c.setCountryName(obj.getString("Country"));
              c.setIsHot(parseInt(obj.getString("IsHot")));
              list.add(c);
          }
         return  list;
        }catch (JSONException e){
          Log.e(TAG, "getHotelCities: ----数据解析异常"+e.getMessage() );
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 酒店静态数据
     * @param userId
     * @param password
     * @param Patameter
     * @return
     */
    private static String builderInitData(String userId, String password,String Patameter) {
        StringBuilder  soapRequestData = buildSoapHeader();
        soapRequestData.append("<"+WebServUtil.INIT_HOTEL_DATA+" xmlns=\""+ WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + userId + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("<Patameter>" + Patameter + "</Patameter>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.INIT_HOTEL_DATA + ">");
        return buildSoapFooter(soapRequestData).toString();
    }



}
