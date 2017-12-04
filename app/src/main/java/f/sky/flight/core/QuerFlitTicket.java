package f.sky.flight.core;

import f.sky.flight.model.CabinPriceExObj;
import f.sky.flight.model.QueryTktResult;
import f.sky.flight.model.SegmentObj;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/11/25/025.
 */

public class QuerFlitTicket extends WebServUtil {

    /**
     * c查询航班
     * @param UserId
     * @param Password
     * @param orgCity
     * @param dstCity
     * @param deptDate
     * @param airline
     * @param QueryRole
     * @param DstCitySecondTrip
     * @param OrgCitySecondTrip
     * @param QuerySort
     * @param DepDateSecondTrip
     * @param TripType
     * @param Direct
     * @param ETkt
     * @param Patameter1
     * @return
     */
    public static QueryTktResult queryTktResult(String UserId, String Password,
                                                String orgCity, String dstCity, String deptDate, String airline,
                                                String QueryRole, String DstCitySecondTrip,
                                                String OrgCitySecondTrip, String QuerySort,
                                                String DepDateSecondTrip, int TripType, boolean Direct,
                                                boolean ETkt, String Patameter1) {
        //参数传递
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("OrgCity", orgCity);
        paramMap.put("DstCity", dstCity);
        paramMap.put("DepDate", deptDate);
        paramMap.put("AirLine", airline);
        paramMap.put("QueryRole", QueryRole);// YES NO
        //是否查询往返的判断
        if (StringUtils.isNotBlank(DstCitySecondTrip)) {
            paramMap.put("DstCitySecondTrip", DstCitySecondTrip);
        }
        if (StringUtils.isNotBlank(OrgCitySecondTrip)) {
            paramMap.put("OrgCitySecondTrip", OrgCitySecondTrip);
        }
        if (StringUtils.isNotBlank(DepDateSecondTrip)) {
            paramMap.put("DepDateSecondTrip", DepDateSecondTrip);
        }
        paramMap.put("QuerySort", QuerySort);// DepDate, FlightTime, MinPrice
        paramMap.put("TripType", TripType);// 0 单航段  1 来回程  2 联程
        paramMap.put("Direct", Direct);
        paramMap.put("ETkt", ETkt);

        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);
        try {
            byte[] b = buildQueryTktRequestData(UserId, Password, paramMap,
                    Patameter1).getBytes(ENCODE);
            InputStream is = new ByteArrayInputStream(b, 0, b.length);
            RequestEntity re = new InputStreamRequestEntity(is, b.length,
                    CONTENT_TYPE);
            postMethod.setRequestEntity(re);

            // 最后生成一个HttpClient对象，并发出postMethod请求

            HttpClient httpClient = new HttpClient();
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == WebServUtil.HTTP_STATUS_OK) {
                String response = postMethod.getResponseBodyAsString();
                int index = response.indexOf("<QueryTktResult>");
                int endIndex = response.lastIndexOf("</QueryTktResult>");
                response = response.substring(index, endIndex
                        + "</QueryTktResult>".length());
                System.out.println("--------机票查询到的数据------"+response);

                return getQueryTktObject(response);

            }

            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**组合需要提交的参数**/
    private static String buildQueryTktRequestData(String userId,
                                                   String password, Map<String, Object> patameterMap, String Patameter1) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.QUERY_TKT + " xmlns=\""
                + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + userId + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("<Patameter>");
        Set<Map.Entry<String, Object>> entrySet = patameterMap.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            soapRequestData.append("<" + entry.getKey() + ">"
                    + entry.getValue() + "</" + entry.getKey() + ">");
        }
        soapRequestData.append("</Patameter>");
        soapRequestData.append("<Patameter1>" + Patameter1 + "</Patameter1>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.QUERY_TKT + ">");
        return buildSoapFooter(soapRequestData).toString();
    }


    /**
     * 解析航班信息数据
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    private static QueryTktResult getQueryTktObject(String response) {
        try {
            QueryTktResult queryTktResult = new QueryTktResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(
                    response.getBytes(ENCODE)));
            {
                queryTktResult.setSuccess(parseBoolean(doc.getRootElement()
                        .elementTextTrim("Success")));
                queryTktResult.setMessage(doc.getRootElement().elementTextTrim(
                        "Message"));
                queryTktResult.setElapsedMilliseconds(parseLong(doc
                        .getRootElement()
                        .elementTextTrim("ElapsedMilliseconds")));
            }
            {
                Element resultObject = (Element) doc
                        .selectSingleNode("//ResultObject");
                queryTktResult.setDistance(parseInt(resultObject
                        .elementTextTrim("Distance")));
                queryTktResult.setDstCity(resultObject
                        .elementTextTrim("DstCity"));
                queryTktResult.setLowestPrice(parseInt(resultObject
                        .elementTextTrim("LowestPrice")));
                queryTktResult.setOrgCity(resultObject
                        .elementTextTrim("OrgCity"));
                queryTktResult
                        .setSysMsg(resultObject.elementTextTrim("SysMsg"));
            }
            List<Element> flightEles = (List<Element>) doc
                    .selectNodes("//Flights/Segment");
            List<Element> roundFlightEles = (List<Element>) doc
                    .selectNodes("//RoundFlights/Segment");
            for (Element e : flightEles) {
                queryTktResult.addOneFlight(parseSegmentElement(e));
            }

            for (Element e : roundFlightEles) {
                queryTktResult.addOneRoundFlight(parseSegmentElement(e));
            }

            return queryTktResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    @SuppressWarnings("unchecked")
    private static SegmentObj parseSegmentElement(Element e) {
        SegmentObj segmentObj = new SegmentObj();
        segmentObj.setAirLine(e.elementTextTrim("AirLine"));
        segmentObj.setAirLineName(e.elementTextTrim("AirLineName"));
        segmentObj.setAirLineEName(e.elementTextTrim("AirLineEName"));
        segmentObj.setFlightNo(e.elementTextTrim("FlightNo"));
        segmentObj.setOrgCity(e.elementTextTrim("OrgCity"));
        segmentObj.setOrgCityName(e.elementTextTrim("OrgCityName"));
        segmentObj.setOrgCityEName(e.elementTextTrim("OrgCityEName"));
        segmentObj.setOrgT(e.elementTextTrim("OrgT"));
        segmentObj.setDstCity(e.elementTextTrim("DstCity"));
        segmentObj.setDstCityName(e.elementTextTrim("DstCityName"));
        segmentObj.setDstCityEName(e.elementTextTrim("DstCityEName"));
        segmentObj.setDstT(e.elementTextTrim("DstT"));
        segmentObj.setDepDate(e.elementTextTrim("DepDate"));
        segmentObj.setDepTime(e.elementTextTrim("DepTime"));
        segmentObj.setIsDepTimeModify(parseBoolean(e
                .elementTextTrim("IsDepTimeModify")));
        segmentObj.setArrDate(e.elementTextTrim("ArrDate"));
        segmentObj.setArrTime(e.elementTextTrim("ArrTime"));
        segmentObj.setIsArrTimeModify(parseBoolean(e
                .elementTextTrim("IsArrTimeModify")));
        segmentObj.setFlyTime(parseInt(e.elementTextTrim("FlyTime")));
        segmentObj.setAsr(parseBoolean(e.elementTextTrim("Asr")));
        segmentObj.setIsEtkt(parseBoolean(e.elementTextTrim("IsEtkt")));
        segmentObj.setIsRule(parseBoolean(e.elementTextTrim("IsRule")));
        segmentObj.setMeal(parseBoolean(e.elementTextTrim("Meal")));
        segmentObj
                .setIsCodeShare(parseBoolean(e.elementTextTrim("IsCodeShare")));
        segmentObj.setLink(e.elementTextTrim("Link"));
        segmentObj.setMealCode(e.elementTextTrim("MealCode"));
        segmentObj.setPlaneStyle(e.elementTextTrim("PlaneStyle"));
        segmentObj.setCarrier(e.elementTextTrim("Carrier"));
        segmentObj
                .setLowestPrice(parseDouble(e.elementTextTrim("LowestPrice")));
        segmentObj.setAirportTax(parseDouble(e.elementTextTrim("AirportTax")));
        segmentObj.setFuelTax(parseDouble(e.elementTextTrim("FuelTax")));
        segmentObj.setOtherPrice(parseDouble(e.elementTextTrim("OtherPrice")));
        segmentObj.setCabinCodeInfo(e.elementTextTrim("CabinCodeInfo"));
        segmentObj.setRemark(e.elementTextTrim("Remark"));
        segmentObj.setStopCities(e.elementTextTrim("StopCities"));

        List<Element> cabinPriceEles = e.element("Cabins").elements();
        for (Element priceE : cabinPriceEles) {
            segmentObj.getCabins().add(parseCabinElement(priceE));
        }

        return segmentObj;
    }


    /**
     * 协议价数据
     * @param priceE
     * @return
     */
    private static CabinPriceExObj parseCabinElement(Element priceE) {
        CabinPriceExObj fPrice = new CabinPriceExObj();
        fPrice.setIndex(parseInt(priceE.elementTextTrim("Index")));
        fPrice.setSuperCabin(parseBoolean(priceE.elementTextTrim("SuperCabin")));
        fPrice.setAgreementCabin(parseBoolean(priceE
                .elementTextTrim("AgreementCabin")));
        fPrice.setSpecialCabin(parseBoolean(priceE
                .elementTextTrim("SpecialCabin")));
        fPrice.setIsRule(parseBoolean(priceE.elementTextTrim("IsRule")));
        fPrice.setClientPrice(parseDouble(priceE.elementTextTrim("ClientPrice")));
        fPrice.setPrice(parseDouble(priceE.elementTextTrim("Price")));
        fPrice.setRoundPrice(parseDouble(priceE.elementTextTrim("RoundPrice")));
        fPrice.setStandardPrice(parseDouble(priceE
                .elementTextTrim("StandardPrice")));
        fPrice.setYPrice(parseDouble(priceE.elementTextTrim("YPrice")));
        fPrice.setDiscountRate(parseDouble(priceE
                .elementTextTrim("DiscountRate")));
        fPrice.setAgreementCode(priceE.elementTextTrim("AgreementCode"));
        fPrice.setAgreementID(priceE.elementTextTrim("AgreementID"));
        fPrice.setTourCode(priceE.elementTextTrim("TourCode"));
        fPrice.setDisCountCode(priceE.elementTextTrim("DisCountCode"));
        fPrice.setCabin(priceE.elementTextTrim("Cabin"));
        fPrice.setCabinDesc(priceE.elementTextTrim("CabinDesc"));
        fPrice.setCabinEDesc(priceE.elementTextTrim("CabinEDesc"));
        fPrice.setCabinCount(priceE.elementTextTrim("CabinCount"));
        fPrice.setBasicCabin(priceE.elementTextTrim("BasicCabin"));
        fPrice.setPriceType(priceE.elementTextTrim("PriceType"));
        fPrice.setPriceEType(priceE.elementTextTrim("PriceEType"));
        fPrice.setRefundInfo(priceE.elementTextTrim("RefundInfo"));
        fPrice.setRefundEInfo(priceE.elementTextTrim("RefundEInfo"));
        fPrice.setChangeInfo(priceE.elementTextTrim("ChangeInfo"));
        fPrice.setChangeEInfo(priceE.elementTextTrim("ChangeEInfo"));
        fPrice.setEndInfo(priceE.elementTextTrim("EndInfo"));
        fPrice.setLimit(priceE.elementTextTrim("Limit"));
        fPrice.setDesc(priceE.elementTextTrim("Desc"));
        fPrice.setRemark(priceE.elementTextTrim("Remark"));
        return fPrice;
    }
}
