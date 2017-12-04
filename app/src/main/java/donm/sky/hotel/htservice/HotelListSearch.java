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

import donm.sky.hotel.model.HotelDetail;
import donm.sky.hotel.model.HotelFacilities;
import donm.sky.hotel.model.HotelImage;
import donm.sky.hotel.model.HotelImageLocation;
import donm.sky.hotel.model.HotelInfoSearchResult;
import donm.sky.hotel.model.HotelRoom;
import donm.sky.hotel.model.HotelServiceRank;
import f.sky.flight.core.WebServUtil;

/**
 * Created by Administrator on 2017/1/12/012.
 * 酒店列表信息查询
 */

public class HotelListSearch extends WebServUtil {
    private static  final  String TAG = "HotelListSearch";
    /**
     * 酒店详细信息查询
     * @param
     * @param
     * @param clientId
     * @param cityId
     * @param hotelIds
     * @return
     */
    public static  HotelInfoSearchResult getHotelListData( int clientId,String cityId, List<Integer> hotelIds)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.HOTEL_RELAY_API);
        byte b[]=builderHotelListData(clientId,cityId,hotelIds).getBytes(ENCODE);
        Log.e(TAG, "getHotelListData:=====参数数据： "+builderHotelListData(clientId,cityId,hotelIds) );
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        Log.e(TAG, "hotelListSearCh:-----Code数据： "+statusCode);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            Log.e(TAG, "hotelListSearCh:-----得到的Json数据： "+response);
            return  parseHotelDetail(response);
        }

        return null;
    }

    /**
     * 解析酒店数据
     * @param response
     * @return
     */
    private static HotelInfoSearchResult parseHotelDetail(String response) {
        Element root;
        Document doc;
        SAXReader reader = new SAXReader();
        HotelInfoSearchResult infoResult = new HotelInfoSearchResult();
        try {
            doc = reader.read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            root = doc.getRootElement();
            Element obj = root.element("Body")
                    .element("HotelInfoSearchResponse")
                    .element("HotelInfoSearchResult");
            infoResult.setSuccess(parseBoolean(obj.elementTextTrim("Success")));
            infoResult.setMessage(obj.elementTextTrim("Message"));
            infoResult.setElapsedMilliseconds(parseInt(obj.elementTextTrim("ElapsedMilliseconds")));
            //--------到处需要使用的值
            Element resultObject = obj.element("ResultObject");
            //解析酒店详情数据并返回list存储
            infoResult.setDetails(parseHoDetails(resultObject));
            //解析酒店所有房间数据
            infoResult.setRooms(parseHotelRooms(resultObject));
            //HotelServiceRank
            infoResult.setRanks(parseServiceRank(resultObject));
           //HotelFacilities
            infoResult.setFacilities( parseFacilities(resultObject));
            //<HotelImage>
            infoResult.setImages(parseHotelImages(resultObject));
            //HotelImageLocation
            infoResult.setImgLocations( parseHotelImageLocation(resultObject));
            return  infoResult;

        }catch (Exception e){
            e.printStackTrace();
            Log.e(TAG, "parseHotelDetail:---酒店数据解析err--- ");
        }
        return null;

    }

    /**
     * 解析
     * @param resultObject
     * @return
     */
    private static List<HotelImageLocation> parseHotelImageLocation(Element resultObject) {
        List<HotelImageLocation> imgLocations = new ArrayList<>();
        Element hotelR = resultObject.element("HotelImageLocation");
        List<Element> types =hotelR.elements("anyType");
        for(Element e:types){
            HotelImageLocation imgL = new HotelImageLocation();
            imgL.setImageID(parseInt(e.elementTextTrim("ImageID")));
            imgL.setHotelID(parseInt(e.elementTextTrim("HotelID")));
            imgL.setHotelCode(parseInt(e.elementTextTrim("HotelCode")));
            imgL.setSize(parseInt(e.elementTextTrim("Size")));
            imgL.setURL(e.elementTextTrim("URL"));
            imgL.setWaterMark(parseInt(e.elementTextTrim("WaterMark")));
            imgL.setID(parseInt(e.elementTextTrim("ID")));
            imgLocations.add(imgL);

        }
        return  imgLocations;
    }

    /**
     * 解析图片集合
     * @param resultObject
     * @return
     */
    private static List<HotelImage> parseHotelImages(Element resultObject) {
        List<HotelImage> imgs = new ArrayList<>();
        Element hotelR = resultObject.element("HotelImage");
        List<Element> types =hotelR.elements("anyType");
        for(Element e:types){
            HotelImage img = new HotelImage();
            img.setHotelID(parseInt(e.elementTextTrim("HotelID")));
            img.setImageID(parseInt(e.elementTextTrim("ImageID")));
            img.setHotelCode(parseInt(e.elementTextTrim("HotelCode")));
            img.setRoomId(e.elementTextTrim("RoomId"));
//            Log.e(TAG, "HotelImage: ---roomId"+e.elementTextTrim("RoomId") );
            img.setIsCoverImage(parseInt(e.elementTextTrim("IsCoverImage")));
            img.setAuthorType(e.elementTextTrim("AuthorType"));
            img.setType(parseInt(e.elementTextTrim("Type")));
            imgs.add(img);
        }

        return imgs;
    }

    /**
     *
     * @param resultObject
     * @return
     */
    private static List<HotelFacilities> parseFacilities(Element resultObject) {
        List<HotelFacilities> facilities = new ArrayList<>();
        Element hotelR = resultObject.element("HotelFacilities");
        List<Element> types =hotelR.elements("anyType");
        for(Element e:types){
            HotelFacilities fc = new HotelFacilities();
            fc.setID(parseInt(e.elementTextTrim("ID")));
            fc.setHotelID(parseInt(e.elementTextTrim("HotelID")));
            fc.setHotelCode(parseInt(e.elementTextTrim("HotelCode")));
            fc.setGeneralAmenities(e.elementTextTrim("GeneralAmenities"));
            fc.setRecreationAmenities(e.elementTextTrim("RecreationAmenities"));
            fc.setServiceAmenities(e.elementTextTrim("ServiceAmenities"));
            facilities.add(fc);
        }
        return  facilities;
    }

    /**
     * 解析酒店排行
     * @param resultObject
     * @return
     */
    private static List<HotelServiceRank> parseServiceRank(Element resultObject) {
        List<HotelServiceRank> ranks = new ArrayList<>();
        Element hotelR = resultObject.element("HotelServiceRank");
        List<Element> types =hotelR.elements("anyType");
        for(Element Rk:types){
            HotelServiceRank r = new HotelServiceRank();
            r.setId(parseInt(Rk.elementTextTrim("ID")));
            r.setHotelID(parseInt(Rk.elementTextTrim("HotelID")));
            r.setHotelCode(parseInt(Rk.elementTextTrim("HotelCode")));
            r.setSummaryScore(parseDouble(Rk.elementTextTrim("SummaryScore")));
            r.setSummaryRate(Rk.elementTextTrim("SummaryRate"));
            r.setInstantConfirmScore(Rk.elementTextTrim("InstantConfirmScore"));
            r.setInstantConfirmRate(Rk.elementTextTrim("InstantConfirmRate"));
            r.setBookingSuccessScore(Rk.elementTextTrim("BookingSuccessScore"));
            r.setBookingSuccessRate(Rk.elementTextTrim("BookingSuccessRate"));
            r.setComplaintScore(Rk.elementTextTrim("ComplaintScore"));
            r.setComplaintRate(Rk.elementTextTrim("ComplaintRate"));
            ranks.add(r);
        }
        return  ranks;
    }

    /**
     * 解析酒店房间
     * @param resultObject
     * @return
     */
    private static List<HotelRoom>  parseHotelRooms(Element resultObject) {
        List<HotelRoom> rooms = new ArrayList<>();
        Element hotelR = resultObject.element("HotelRoom");
        List<Element> types =hotelR.elements("anyType");
        for(Element hR:types){
            HotelRoom ros = new HotelRoom();
            ros.setId(parseInt(hR.elementTextTrim("ID")));
            ros.setAmount(parseInt(hR.elementTextTrim("Amount")));
            ros.setArea(parseInt(hR.elementTextTrim("Area")));
            ros.setHotelID(parseInt(hR.elementTextTrim("HotelID")));
            ros.setRoomId(hR.elementTextTrim("RoomId"));
//            Log.e(TAG, "HotelRoom: ---roomId-------:"+hR.elementTextTrim("RoomId") );

            ros.setHotelCode(parseInt(hR.elementTextTrim("HotelCode")));
            ros.setName(hR.elementTextTrim("Name"));
            ros.setEName(hR.elementTextTrim("EName"));
            ros.setFloor(hR.elementTextTrim("Floor"));
            ros.setBroadnetAccess(parseInt(hR.elementTextTrim("BroadnetAccess")));
            ros.setBroadnetFee(parseInt(hR.elementTextTrim("BroadnetFee")));
            ros.setBedType(hR.elementTextTrim("BedType"));
            ros.setEBedType(hR.elementTextTrim("EBedType"));
            ros.setDescription(hR.elementTextTrim("Description"));
            ros.setEDescription(hR.elementTextTrim("EDescription"));
            ros.setComments(hR.elementTextTrim("Comments"));
            ros.setEComments(hR.elementTextTrim("EComments"));
            ros.setCapacity(parseInt(hR.elementTextTrim("Capacity")));
            ros.setFacilities(hR.elementTextTrim("Facilities"));
            ros.setAmount(parseInt(hR.elementTextTrim("Amount")));
            rooms.add(ros);
        }
        return rooms;
    }

    /**
     * 解析酒店
     * @param resultObject
     * @return
     */
    private static List<HotelDetail> parseHoDetails(Element resultObject) {
        List<HotelDetail> details = new ArrayList<>();
        Element hotelE = resultObject.element("HotelDetail");
        List<Element> types = hotelE.elements("anyType");
        for(Element hE:types){
            HotelDetail dt = new HotelDetail();
            dt.setName(hE.elementTextTrim("Name"));//hE.elementTextTrim("Name")
            dt.setEName(hE.elementTextTrim("EName"));
            dt.setHotelID(parseInt(hE.elementTextTrim("HotelID")));
            dt.setCode(parseInt(hE.elementTextTrim("Code")));
            dt.setAddress(hE.elementTextTrim("Address"));
            dt.setEAddress(hE.elementTextTrim("EAddress"));
            dt.setPhone(hE.elementTextTrim("Phone"));
            dt.setFax(hE.elementTextTrim("Fax"));
            dt.setCategory(parseInt(hE.elementTextTrim("Category")));
            dt.setStarRate(parseInt(hE.elementTextTrim("StarRate")));
            dt.setEstablishmentDate(hE.elementTextTrim("EstablishmentDate"));
            dt.setRenovationDate(hE.elementTextTrim("RenovationDate"));
            dt.setGroupId(parseInt(hE.elementTextTrim("GroupId")));
            dt.setBrandId(parseInt(hE.elementTextTrim("BrandId")));
            dt.setIsEconomic(parseInt(hE.elementTextTrim("IsEconomic")));
            dt.setIsApartment(parseInt(hE.elementTextTrim("IsApartment")));
            dt.setGoogleLat(parseDouble(hE.elementTextTrim("GoogleLat")));
            dt.setGoogleLon(parseDouble(hE.elementTextTrim("GoogleLon")));
            dt.setBaiduLat(parseDouble(hE.elementTextTrim("BaiduLat")));
            dt.setBaiduLon(parseDouble(hE.elementTextTrim("BaiduLon")));
            dt.setRank(parseInt(hE.elementTextTrim("Rank")));
            dt.setCityId(hE.elementTextTrim("CityId"));
            dt.setDistrict(hE.elementTextTrim("District"));
            dt.setBusinessZone(hE.elementTextTrim("BusinessZone"));
            dt.setBusinessZone2(hE.elementTextTrim("BusinessZone2"));
            dt.setCreditCards(hE.elementTextTrim("CreditCards"));
            dt.setECreditCards(hE.elementTextTrim("ECreditCards"));
            dt.setDescription(hE.elementTextTrim("Description"));
            dt.setTraffic(hE.elementTextTrim("Traffic"));
            dt.setETraffic(hE.elementTextTrim("ETraffic"));
            dt.setFeatures(hE.elementTextTrim("Features"));
            dt.setEFeatures(hE.elementTextTrim("EFeatures"));
            dt.setFacilities(hE.elementTextTrim("Facilities"));
            dt.setHasCoupon(parseInt(hE.elementTextTrim("HasCoupon")));
            dt.setThemes(hE.elementTextTrim("Themes"));
            dt.setRoomTotalAmount(parseInt(hE.elementTextTrim("RoomTotalAmount")));
            dt.setStatus(hE.elementTextTrim("Status"));
            dt.setReviewPoor(parseInt(hE.elementTextTrim("ReviewCount")));
            dt.setReviewGood(parseInt(hE.elementTextTrim("ReviewGood")));
            dt.setReviewCount(parseInt(hE.elementTextTrim("ReviewCount")));

            details.add(dt);

        }
        return  details;
    }

    /**
     * 构建酒店数据查询参数
     *
     *
     * @param ClientId
     * @param cityId
     * @param hotelIds
     * @return
     */
    private static String builderHotelListData( int ClientId, String cityId, List<Integer> hotelIds) {
        StringBuilder  soapRequestData = buildSoapHeader();
        soapRequestData.append("<"+WebServUtil.HOTEL_LIST_INFOS+" xmlns=\""+ WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + WebServUtil.HBE_USER_NAME + "</UserID>");
        soapRequestData.append("<Password>" +WebServUtil.HBE_USER_PAW+ "</Password>");
        soapRequestData.append("<Patameter>");
        soapRequestData.append("<Local>"+"zh_CN"+"</Local>");
        soapRequestData.append("<Action>"+"StaticData"+"</Action>");
        soapRequestData.append("<ClientId>"+ClientId+"</ClientId>");
        soapRequestData.append("<Switch>"+"Local"+"</Switch>");
        soapRequestData.append("<HotelCodeList>");
        for (int i = 0; i <hotelIds.size() ; i++) {
            soapRequestData.append("<string>"+hotelIds.get(i)+"</string>");
        }
         soapRequestData.append("</HotelCodeList>");

        soapRequestData.append("</Patameter>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.HOTEL_LIST_INFOS + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
}
