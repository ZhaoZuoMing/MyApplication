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

import donm.sky.hotel.model.FkHorderfuReferenceHorder;
import donm.sky.hotel.model.HorderContact;
import donm.sky.hotel.model.HorderGuest;
import donm.sky.hotel.model.HotelDataList;
import donm.sky.hotel.model.QueryOrderHotelResult;
import f.sky.flight.core.Constants;
import f.sky.flight.core.WebServUtil;

/**
 * Created by Administrator on 2017/3/27/027.
 * 订单查询界面
 */

public class QuerOrderList extends WebServUtil {
    public static QueryOrderHotelResult queryOrderHotel(int bookId, int clientId,int applyId,int OrderId){
        PostMethod postMethod = new PostMethod(WebServUtil.HOTEL_SERVICE);
        try{
            byte b[] =builderQueryHotel(bookId,clientId,applyId,OrderId).getBytes(ENCODE);

            Log.e("----酒店订单查询：", "queryOrderHotel: "+ builderQueryHotel(bookId,clientId,applyId,OrderId));
            InputStream is = new ByteArrayInputStream(b, 0, b.length);
            RequestEntity re = new InputStreamRequestEntity(is, b.length,
                    CONTENT_TYPE);
            postMethod.setRequestEntity(re);
            HttpClient httpClient = new HttpClient();
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == WebServUtil.HTTP_STATUS_OK) {
                String response = postMethod.getResponseBodyAsString();
                Log.e("***", "hotelListSearCh:-----查询酒店订单： "+response);
                return parseHOederData(response);
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("-----异常信息---", "queryOrderHotel: "+e.getMessage() );
        }

        return null;
    }

    /**
     * 解析
     * @param response
     * @return
     */
    private static QueryOrderHotelResult parseHOederData(String response) {
        Element root;
        Document doc;
        SAXReader reader = new SAXReader();
        QueryOrderHotelResult hotelResult  = new QueryOrderHotelResult();
        try {
            doc = reader.read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            root = doc.getRootElement();
            Element obj = root.element("Body")
                    .element("QueryOrderResponse")
                    .element("QueryOrderResult");
            hotelResult.setMessage(obj.elementTextTrim("Message"));
            hotelResult.setElapsedMilliseconds(parseInt(obj.elementTextTrim("ElapsedMilliseconds")));
            hotelResult.setSuccess(parseBoolean(obj.elementTextTrim("Success")));
            Element resultObject = obj.element("ResultObject").element("DataList");
            List<Element> types = resultObject.elements("anyType");
            List<HotelDataList> hotelDataLists  = new ArrayList<>();
             for (Element e:types){
                 HotelDataList dataList  = new HotelDataList();
                 dataList.setOrderid(e.elementTextTrim("Orderid"));
                 dataList.setOrderType(parseInt(e.elementText("OrderType")));
                 dataList.setApplyID(e.elementTextTrim("ApplyID"));
                 dataList.setHotelid(e.elementTextTrim("Hotelid"));
                 dataList.setHotelCode(e.elementTextTrim("HotelCode"));
                 dataList.setHotelName(e.elementTextTrim("HotelName"));
                 dataList.setHotelEName(e.elementTextTrim("HotelEName"));
                 dataList.setInvoiceMode(e.elementTextTrim("InvoiceMode"));
                 dataList.setHotelAddress(e.elementTextTrim("HotelAddress"));
                 dataList.setHotelEAddress(e.elementTextTrim("HotelEAddress"));
                 dataList.setRoomTypeName(e.elementTextTrim("RoomTypeName"));
                 dataList.setRatePlanCode(e.elementTextTrim("RatePlanCode"));
                 dataList.setStatus(parseInt(e.elementTextTrim("Status")));
                 dataList.setRank(parseInt(e.elementTextTrim("Rank")));
                 dataList.setCityid(parseInt(e.elementTextTrim("Cityid")));
                 dataList.setCityName(e.elementTextTrim("CityName"));
                 dataList.setExternalNo(e.elementTextTrim("ExternalNo"));
                 dataList.setPayment(parseInt(e.elementTextTrim("Payment")));
                 dataList.setCostCenter(e.elementTextTrim("CostCenter"));
                 dataList.setVendor(e.elementTextTrim("Vendor"));
                 dataList.setCostNumber(e.elementTextTrim("CostNumber"));
                 dataList.setBizDeptid(e.elementTextTrim("BizDeptid"));
                 dataList.setDeptid(e.elementTextTrim("Deptid"));
                 dataList.setCorpid(e.elementTextTrim("Corpid"));
                 dataList.setCancelid(e.elementTextTrim("Cancelid"));
                 dataList.setSpecialRequest(e.elementTextTrim("SpecialRequest"));
                 dataList.setNumberOfUnits(parseInt(e.elementTextTrim("NumberOfUnits")));
                 dataList.setNumberOfDate(parseInt(e.elementTextTrim("NumberOfDate")));
                 dataList.setIsPerRoom(parseInt(e.elementTextTrim("IsPerRoom")));
                 dataList.setGuestCount(parseInt(e.elementTextTrim("GuestCount")));
                 dataList.setCheckInDate(e.elementTextTrim("CheckInDate"));
                 dataList.setCheckOutDate(e.elementTextTrim("CheckOutDate"));
                 dataList.setBookid(e.elementTextTrim("Bookid"));
                 dataList.setBookName(e.elementText("BookName"));
                 dataList.setArrivalTime(e.elementText("ArrivalTime"));
                 dataList.setLaterArrivalTime(e.elementTextTrim("LaterArrivalTime"));
                 dataList.setCreateTime(e.elementTextTrim("CreateTime"));
                 dataList.setBreakfast(e.elementText("Breakfast"));
                 dataList.setNet(e.elementTextTrim("Net"));
                 dataList.setSms(e.elementTextTrim("Sms"));
                 dataList.setEmail(e.elementTextTrim("Email"));
                 dataList.setHotelPhone(e.elementTextTrim("HotelPhone"));
                 dataList.setCityCode(e.elementTextTrim("CityCode"));
                 dataList.setVendorOrderStatus(e.elementTextTrim("VendorOrderStatus"));
                 dataList.setVendorOrderShowStatus(e.elementTextTrim("VendorOrderShowStatus"));
                 /**解析联系人信息**/
                 dataList.setHorderContact(parseHorderContact(e));
                 /**解析运价**/
                 dataList.setFkHorderfuReferenceHorder(parseFkHorderfuReferenceHorder(e));
                  /**解析入住人**/
                  dataList.setHorderGuest(parseHorderGuest(e));

                 hotelDataLists.add(dataList);
             }

            hotelResult.setHotelDataLists(hotelDataLists);
        }catch (Exception e){
            e.printStackTrace();
        }
        return hotelResult;
    }
     /*入住人*/
    private static List<HorderGuest> parseHorderGuest(Element e) {
        List<HorderGuest> guests = new ArrayList<>();
         Element ele = e.element("ColFkHOrdergReferenceHOrder2");
         List<Element> es = ele.elements("HorderGuest");
         for (Element element :es){
             HorderGuest guest = new HorderGuest();
             guest.setOrderid(element.elementTextTrim("Orderid"));
             guest.setId(parseInt(element.elementTextTrim("Id")));
             guest.setName(element.elementTextTrim("Name"));
             guest.setIdType(parseInt(element.elementTextTrim("IdType")));
             guest.setIdNumber(element.elementTextTrim("IdNumber"));
             guest.setMobile(element.elementText("Mobile"));
             guest.setDeleted(parseBoolean(element.elementTextTrim("IsDeleted")));
             guest.setChanged(parseBoolean(element.elementTextTrim("IsChanged")));
             guests.add(guest);
         }
        return guests;
    }

    private static FkHorderfuReferenceHorder parseFkHorderfuReferenceHorder(Element e) {
        FkHorderfuReferenceHorder horder  = new FkHorderfuReferenceHorder();
        Element ele = e.element("FkHorderfuReferenceHorder");
        horder.setOrderid(ele.elementTextTrim("Oederid"));
        horder.setNum(parseInt(ele.elementTextTrim("Num")));
        horder.setNetPrice(ele.elementTextTrim("NetPrice"));
        horder.setClientNetPrice(ele.elementTextTrim("ClientNetPrice"));
        horder.setSaleAmount(ele.elementTextTrim("SaleAmount"));
        horder.setCurrencyCode(ele.elementTextTrim("CurrencyCode"));
        return horder;
    }

    private static HorderContact parseHorderContact(Element e) {
        HorderContact contact  = new HorderContact();
         Element ele = e.element("ColFkHordercoReferenceHorder").element("HorderContact");
         contact.setOrderid(ele.elementTextTrim("Orderid"));
         contact.setId(ele.elementTextTrim("Id"));
         contact.setContactType(ele.elementTextTrim("ContactType"));
         contact.setContactPersonName(ele.elementTextTrim("ContactPersonName"));
         contact.setPhoneNumber(ele.elementTextTrim("PhoneNumber"));
         contact.setEmail(ele.elementTextTrim("Email"));
         contact.setConfirmType(ele.elementTextTrim("ConfirmType"));
         contact.setDeleted(parseBoolean(ele.elementTextTrim("IsDeleted")));
         contact.setChanged(parseBoolean(ele.elementTextTrim("IsChanged")));
        return  contact;
    }

    private static  String builderQueryHotel(int bookId,int clientId,int applyId,int OrderId){
        StringBuilder  soapRequestData = buildSoapHeader();
        soapRequestData.append("<"+WebServUtil.QUER_HOTEL_ORDER+" xmlns=\""+ WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + WebServUtil.HBE_USER_NAME + "</UserID>");
        soapRequestData.append("<Password>" +WebServUtil.HBE_USER_PAW+ "</Password>");
        soapRequestData.append("<Patameter>");
//        soapRequestData.append("<OrderID>"+"10738"+"</OrderID>"); Payment  -1 ApplyID
        soapRequestData.append("<Payment>"+-1+"</Payment>");
        soapRequestData.append("<ApplyID>"+applyId+"</ApplyID>");
        soapRequestData.append("<OrderID>").append(OrderId).append("</OrderID>");
        soapRequestData.append("<Status>").append(Constants.FUONE).append("</Status>");
        soapRequestData.append("<SouceType>").append(Constants.ZERO).append("</SouceType>");
        soapRequestData.append("<SellType>").append(Constants.ZERO).append("</SellType>");
//        soapRequestData.append("<Payment>").append(Constants.FROM_APPLY_LIST).append("</Payment>");;
        soapRequestData.append("<BookID>"+bookId+"</BookID>");
        soapRequestData.append("<ClientID>"+clientId+"</ClientID>");
        soapRequestData.append("<BookDateStart>"+""+"</BookDateStart>");
        soapRequestData.append("<BookDateEnd>"+""+"</BookDateEnd>");
        soapRequestData.append("<DisplayReq>").append(Constants.EMPTY_STRING).append("</DisplayReq>");//30 分页
        soapRequestData.append("<PageItems>").append(Constants.EMPTY_STRING).append("</PageItems>");//
        soapRequestData.append("<PageNo>").append(Constants.EMPTY_STRING).append("</PageNo>");
        soapRequestData.append("<CityID>"+"0201"+"</CityID>");
        soapRequestData.append("</Patameter>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.QUER_HOTEL_ORDER + ">");
        return buildSoapFooter(soapRequestData).toString();

    }

}