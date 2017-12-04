package donm.sky.hotel.htservice;

import android.util.Log;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import donm.sky.hotel.model.BookingRules;
import donm.sky.hotel.model.DHotel;
import donm.sky.hotel.model.GuaranteeRule;
import donm.sky.hotel.model.HotelDetailSearchResult;
import donm.sky.hotel.model.NightlyRates;
import donm.sky.hotel.model.PrepayRule;
import donm.sky.hotel.model.RatePlans;
import donm.sky.hotel.model.Room;
import donm.sky.hotel.model.ValueAdds;
import f.sky.flight.core.WebServUtil;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/2/7/007.
 */

public class HotelDetailSer extends WebServUtil {

    public static HotelDetailSearchResult getHotelDetail(String ArrivalDate,String DepartureDate,String HotelIds,int ClientId){
        PostMethod postMethod = new PostMethod(WebServUtil.HOTEL_RELAY_API);
        try {
            byte [] b = builderSearChData(ArrivalDate,DepartureDate,HotelIds,ClientId).getBytes(ENCODE);
            Log.e(TAG, "参数提交： "+ builderSearChData(ArrivalDate,DepartureDate,HotelIds,ClientId));
            InputStream is = new ByteArrayInputStream(b, 0, b.length);
            RequestEntity re = new InputStreamRequestEntity(is, b.length,
                    CONTENT_TYPE);
            postMethod.setRequestEntity(re);
            HttpClient httpClient = new HttpClient();
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == WebServUtil.HTTP_STATUS_OK) {
                String response = postMethod.getResponseBodyAsString();
                Log.e("HotelDetailSer:  ", "HotelDetailSer:-----得到的XML数据： "+response);
                return parseDetail(response);
            }
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;

    }
    /*构建提交请求参数*/
    private static  String builderSearChData(String ArrivalDate,String DepartureDate,String HotelIds,int ClientId){
        StringBuilder  soapRequestData = buildSoapHeader();
        soapRequestData.append("<"+WebServUtil.HOTEL_DETAIL_SEARCH+" xmlns=\""+ WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + WebServUtil.HBE_USER_NAME + "</UserID>");
        soapRequestData.append("<Password>" +WebServUtil.HBE_USER_PAW+ "</Password>");
        soapRequestData.append("<Patameter>");
        soapRequestData.append("<Local>"+"zh_CN"+"</Local>");
        soapRequestData.append("<Action>"+"Detail"+"</Action>");
        soapRequestData.append("<ClientId>"+ClientId+"</ClientId>");
        soapRequestData.append("<Switch>"+"Local"+"</Switch>");
        soapRequestData.append("<NeedAgreement>"+true+"</NeedAgreement>");

        soapRequestData.append("<DetailCondition>");
        soapRequestData.append("<ArrivalDate>"+ArrivalDate+"</ArrivalDate>");
        soapRequestData.append("<DepartureDate>"+DepartureDate+"</DepartureDate>");
        soapRequestData.append("<HotelIds>"+HotelIds+"</HotelIds>");
        soapRequestData.append("<RoomTypeId>"+""+"</RoomTypeId>");
        soapRequestData.append("<RatePlanId>"+0+"</RatePlanId>");
        soapRequestData.append("<PaymentType>"+"All"+"</PaymentType>");
        soapRequestData.append("<CheckInPersonAmount>"+1+"</CheckInPersonAmount>");
        soapRequestData.append("<Options>"+"1"+"</Options>");
        soapRequestData.append("</DetailCondition>");

        soapRequestData.append("</Patameter>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.HOTEL_DETAIL_SEARCH + ">");
        return buildSoapFooter(soapRequestData).toString();
    }

    /*解析数据*/
    private static HotelDetailSearchResult parseDetail(String response) {
        HotelDetailSearchResult detail = new HotelDetailSearchResult();
        Element root;
        Document doc;
        SAXReader reader = new SAXReader();
        try {
            doc = reader.read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            root = doc.getRootElement();
            Element obj = root.element("Body")
                    .element("HotelDetailSearchResponse")
                    .element("HotelDetailSearchResult");
            detail.setSuccess(parseBoolean(obj.elementTextTrim("Success")));
            detail.setMessage(obj.elementTextTrim("Message"));
            detail.setElapsedMilliseconds(parseLong(obj.elementTextTrim("ElapsedMilliseconds")));

            Element hotelResult =obj.element("ResultObject").element("Result")
                    .element("Hotels").element("Hotel");

            DHotel dHotel  = new DHotel();//解析酒店数据
            dHotel.setHotelId(hotelResult.elementTextTrim("HotelId"));
            dHotel.setCurrencyCode(hotelResult.elementTextTrim("CurrencyCode"));
            dHotel.setLowRate(hotelResult.elementTextTrim("LowRate"));
            dHotel.setFacilities(hotelResult.elementTextTrim("Facilities"));
            dHotel.setDistance(hotelResult.elementTextTrim("Distance"));
            /*设置解析预订规则*/
            dHotel.setBookingRules(parseBookingRule(hotelResult));
            /*设置解析担保条件*/
            dHotel.setGuaranteeRules(parseGuaranteeRule(hotelResult));
            /*设置预订、预付酒店时的规则*/
            dHotel.setPrepayRules(parsePrepayRule(hotelResult));
            /*设置酒店附加服务费*/
            dHotel.setValueAddses(parseValueAdds(hotelResult));
           /*设置当前酒店房型数据*/
            dHotel.setRooms(parseRooms(hotelResult));

            detail.setdHotel(dHotel);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("数据解析异常",e.getMessage());
        }
        return detail;
    }
   /*解析房间数据*/
    private static List<Room> parseRooms(Element hotelResult) {
        List<Room> listRoom = new ArrayList<>();
        Element bookingRules = hotelResult.element("Rooms");
        List<Element> rooms= bookingRules.elements("Room");
        for (Element ele: rooms) {
            Room r = new Room();
            r.setName(ele.elementTextTrim("Name"));
            r.setRoomId(ele.elementTextTrim("RoomId"));
            r.setRatePlans(parseRoomPlans(ele));
            listRoom.add(r);
        }
        return  listRoom;
    }
    /*解析房型价格数据*/
    private static List<RatePlans> parseRoomPlans(Element ele) {
        List<RatePlans> listPlans = new ArrayList<>();
        Element bookingRules = ele.element("RatePlans");
        List<Element> plans= bookingRules.elements("RatePlan");
        for (Element p: plans) {
            RatePlans plan = new RatePlans();
            plan.setRatePlanId(p.elementTextTrim("RatePlanId"));
            plan.setRatePlanName(p.elementTextTrim("RatePlanName"));
            plan.setMinAmount(p.elementTextTrim("MinAmount"));
            plan.setMinDays(parseInt(p.elementTextTrim("MinDays")));
            plan.setMaxDays(parseInt(p.elementTextTrim("MaxDays")));
            plan.setPaymentType(p.elementTextTrim("PaymentType"));
            plan.setStatus(parseBoolean(p.elementTextTrim("Status")));
            plan.setCustomerType(p.elementTextTrim("CustomerType"));
            plan.setCurrentAlloment(parseInt(p.elementTextTrim("CurrentAlloment")));
            plan.setInstantConfirmation(parseBoolean(p.elementTextTrim("InstantConfirmation")));
            plan.setLastMinuteSale(parseBoolean(p.elementTextTrim("IsLastMinuteSale")));
            plan.setStartTime(p.elementTextTrim("StartTime"));
            plan.setEndTime(p.elementTextTrim("EndTime"));
            plan.setTotalRate(p.elementTextTrim("TotalRate"));
            plan.setAverageRate(p.elementTextTrim("AverageRate"));
            plan.setCurrencyCode(p.elementTextTrim("CurrencyCode"));
            plan.setCoupon(p.elementTextTrim("Coupon"));
            plan.setBookingRuleIds(p.elementTextTrim("BookingRuleIds"));
            plan.setPrepayRuleIds(p.elementTextTrim("PrepayRuleIds"));
            plan.setValueAddIds(p.elementTextTrim("ValueAddIds"));
            //----
            plan.setGuaranteeRuleIds(p.elementTextTrim("GuaranteeRuleIds"));
            plan.setRoomTypeId(p.elementTextTrim("RoomTypeId"));
            plan.setHotelCode(parseInt(p.elementTextTrim("HotelCode")));
            plan.setInvoiceMode(p.elementTextTrim("InvoiceMode"));
            /*设置会员价格*/
            plan.setNightlyRates(parseMember(p));
            listPlans.add(plan);
        }
        return  listPlans;
    }
    /*设置会员价*/
    private static List<NightlyRates> parseMember(Element p) {
        List<NightlyRates> nightlyRates  = new ArrayList<>();
        Element bookingRules = p.element("NightlyRates");
        List<Element> nights= bookingRules.elements("NightlyRate");
        for (Element n: nights) {
            NightlyRates rate = new NightlyRates();
            rate.setMember(n.elementTextTrim("Member"));
            rate.setDate(n.elementTextTrim("Date"));
            rate.setCost(n.elementTextTrim("Cost"));
            rate.setStatus(parseBoolean(n.elementTextTrim("Status")));
            rate.setAddBed(n.elementTextTrim("AddBed"));
            nightlyRates.add(rate);
        }
        return  nightlyRates;
    }

    /*附加服务*/
    private static  List<ValueAdds> parseValueAdds(Element hotelResult) {
        List<ValueAdds> list = new ArrayList<>();
        Element bookingRules = hotelResult.element("ValueAdds");
        List<Element> valueAdds= bookingRules.elements("ValueAdd");
        for (Element rule: valueAdds) {
            ValueAdds adds = new ValueAdds();
            adds.setDescription(rule.elementTextTrim("Description"));
            adds.setTypeCode(rule.elementTextTrim("TypeCode"));
            adds.setIsInclude(parseBoolean(rule.elementTextTrim("IsInclude")));
            adds.setAmount(parseInt(rule.elementTextTrim("Amount")));
            adds.setCurrencyCode(rule.elementTextTrim("CurrencyCode"));
            adds.setPriceOption(rule.elementTextTrim("PriceOption"));
            adds.setPrice(rule.elementTextTrim("Price"));
            adds.setIsExtAdd(parseBoolean(rule.elementTextTrim("IsExtAdd")));
            adds.setExtOption(rule.elementTextTrim("ExtOption"));
            adds.setExtPrice(rule.elementTextTrim("ExtPrice"));
            adds.setStartDate(rule.elementTextTrim("StartDate"));
            adds.setEndDate(rule.elementTextTrim("EndDate"));
            adds.setValueAddId(rule.elementTextTrim("ValueAddId"));
            list.add(adds);
        }
        return  list;
    }

    /*酒店预订预付规则*/
    private static List<PrepayRule> parsePrepayRule(Element hotelResult) {
        List<PrepayRule> list = new ArrayList<>();
        Element bookingRules = hotelResult.element("PrepayRules");
        List<Element> prepayRule = bookingRules.elements("PrepayRule");
        for (Element rule: prepayRule) {
            PrepayRule pre  = new PrepayRule();
            pre.setDescription(rule.elementTextTrim("Description"));
            pre.setDateType(rule.elementTextTrim("DateType"));
            pre.setStartDate(rule.elementTextTrim("StartDate"));
            pre.setEndDate(rule.elementTextTrim("EndDate"));
            pre.setWeekSet(rule.elementTextTrim("WeekSet"));
            pre.setChangeRule(rule.elementTextTrim("ChangeRule"));
            pre.setCashScaleFirstAfter(rule.elementTextTrim("CashScaleFirstAfter"));
            pre.setCashScaleFirstBefore(rule.elementTextTrim("CashScaleFirstBefore"));
            pre.setDateNum(rule.elementTextTrim("DateNum"));
            pre.setTime(rule.elementTextTrim("Time"));
            pre.setDeductFeesAfter(parseInt(rule.elementTextTrim("DeductFeesAfter")));
            pre.setDeductFeesBefore(parseInt(rule.elementTextTrim("DeductFeesBefore")));
            pre.setDeductNumAfter(rule.elementTextTrim("DeductNumAfter"));
            pre.setDeductNumBefore(rule.elementTextTrim("DeductNumBefore"));
            pre.setHour(rule.elementTextTrim("Hour"));
            pre.setHour2(rule.elementTextTrim("Hour2"));
            pre.setPrepayRuleId(rule.elementTextTrim("PrepayRuleId"));
            list.add(pre);
        }
        return list;
    }

    /*解析担保规则*/
    private static List<GuaranteeRule> parseGuaranteeRule(Element hotelResult) {
        List<GuaranteeRule> list = new ArrayList<>();
        Element bookingRules = hotelResult.element("GuaranteeRules");
        List<Element> guaranteeRule = bookingRules.elements("GuaranteeRule");
        for (Element rule: guaranteeRule) {
            GuaranteeRule guarant = new GuaranteeRule();
            guarant.setAmount(parseInt(rule.elementTextTrim("Amount")));
            guarant.setDescription(rule.elementTextTrim("Description"));
            guarant.setDateType(rule.elementTextTrim("DateType"));
            guarant.setStartDate(rule.elementTextTrim("StartDate"));
            guarant.setEndDate(rule.elementTextTrim("EndDate"));
            guarant.setWeekSet(rule.elementTextTrim("WeekSet"));
            guarant.setTimeGuarantee(parseBoolean(rule.elementTextTrim("IsTimeGuarantee")));
            guarant.setStartTime(rule.elementTextTrim("StartTime"));
            guarant.setEndTime(rule.elementTextTrim("EndTime"));
            guarant.setTomorrow(parseBoolean(rule.elementTextTrim("IsTomorrow")));
            guarant.setAmountGuarantee(parseBoolean(rule.elementTextTrim("IsAmountGuarantee")));
            guarant.setAmount(parseInt(rule.elementTextTrim("Amount")));
            guarant.setGuaranteeType(rule.elementTextTrim("GuaranteeType"));
            guarant.setChangeRule(rule.elementTextTrim("ChangeRule"));
            guarant.setDay(rule.elementTextTrim("Day"));
            guarant.setTime(rule.elementTextTrim("Time"));
            guarant.setHour(rule.elementTextTrim("Hour"));
            guarant.setGuranteeRuleId(rule.elementTextTrim("GuranteeRuleId"));
            list.add(guarant);
        }
        return list;
    }

    /*解析酒店预订规则*/
    private static List<BookingRules> parseBookingRule(Element hotelResult) {
        List<BookingRules> list = new ArrayList<>();
        Element bookingRules = hotelResult.element("BookingRules");
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


}
