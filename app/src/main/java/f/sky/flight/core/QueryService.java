package f.sky.flight.core;

import android.content.Context;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import f.sky.flight.model.AirlineObj;
import f.sky.flight.model.AirportObj;
import f.sky.flight.model.F_ClientReasonCodeObj;
import f.sky.flight.model.F_VersionObj;
import f.sky.flight.model.FlightRoleObj;
import f.sky.flight.model.PlaneStyleObj;
import f.sky.flight.model.QueryAirlineResult;
import f.sky.flight.model.QueryAirportResult;
import f.sky.flight.model.QueryAuditMailResult;
import f.sky.flight.model.QueryDataVersionResult;
import f.sky.flight.model.QueryFlightRoleResult;
import f.sky.flight.model.QueryPlaneStyleResult;
import f.sky.flight.model.QueryReasonCodeResult;

/**
 * Created by Administrator on 2016/11/23/023.
 */

public class QueryService  extends WebServUtil{

    public static QueryFlightRoleResult GetFlightRole(
            String UserID, String Password, String UserName) throws Exception {
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildGetFlightRoleRequestData(UserID, Password, UserName).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            System.out.println(response);
            int index = response.indexOf("<GetFlightRoleResult>");
            int endIndex = response.lastIndexOf("</GetFlightRoleResult>");
            response = response.substring(index, endIndex
                    + "</GetFlightRoleResult>".length());
            return queryFlightRoleResult(response);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static QueryFlightRoleResult queryFlightRoleResult(String response) {
        try {
            QueryFlightRoleResult queryFlightRoleResult = new QueryFlightRoleResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(
                    response.getBytes(ENCODE)));
            {
                queryFlightRoleResult.setSuccess(parseBoolean(doc
                        .getRootElement().elementTextTrim("Success")));
                queryFlightRoleResult.setMessage(doc.getRootElement()
                        .elementTextTrim("Message"));
                queryFlightRoleResult.setElapsedMilliseconds(parseLong(doc
                        .getRootElement()
                        .elementTextTrim("ElapsedMilliseconds")));
                List<Element> versionEles = (List<Element>) doc
                        .selectNodes("//FlightRoleDO");
                FlightRoleObj flightRoleObj = null;
                for (Element each : versionEles) {
                    flightRoleObj = new FlightRoleObj();
                    flightRoleObj.setID(parseInt(each.elementTextTrim("ID")));
                    flightRoleObj.setCode(each.elementTextTrim("Code"));
                    flightRoleObj.setDetail(each.elementTextTrim("Detail"));
                    flightRoleObj.setEDetail(each.elementTextTrim("EDetail"));
                    queryFlightRoleResult.getFlightRoleObjL().add(flightRoleObj);
                }
            }
            return queryFlightRoleResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String buildGetFlightRoleRequestData(String userID, String password, String UserName){
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.GET_FLIGHT_ROLE
                + " xmlns=\"" + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + userID + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("<Patameter>" + UserName + "</Patameter>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + GET_FLIGHT_ROLE + ">");
        return buildSoapFooter(soapRequestData).toString();
    }

    /**
     * 检查版本号
     * @param UserID
     * @param Password
     * @param SystemType
     * @return
     * @throws Exception
     */
    public static QueryDataVersionResult QueryDataVersion(String UserID,
                                                          String Password, String SystemType) throws Exception {

        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);
        byte[] b = buildQueryDataVersionRequestData(UserID, Password,
                SystemType).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);

        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);

        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<DataVersionQueryResult>");
            int endIndex = response.lastIndexOf("</DataVersionQueryResult>");

            response = response.substring(index, endIndex
                    + "</DataVersionQueryResult>".length());

            return getQueryDataVersionObject(response);
        }
        return null;
    }

    public static QueryAuditMailResult queryAuditMail(String UserID,
                                                      String Password, String UserName) throws Exception {
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildQueryAuditMailOrReasonCodeRequestData(
                WebServUtil.QUERY_AUDIT_MAIL, UserID, Password, UserName)
                .getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<QueryAuditMailResult>");
            int endIndex = response.lastIndexOf("</QueryAuditMailResult>");
            response = response.substring(index, endIndex
                    + "</QueryAuditMailResult>".length());
            return getQueryAuditMailObject(response);
        }
        return null;
    }

    private static QueryAuditMailResult getQueryAuditMailObject(String response) {
        try {
            QueryAuditMailResult queryAuditMailResult = new QueryAuditMailResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(
                    response.getBytes(ENCODE)));
            {
                queryAuditMailResult.setSuccess(parseBoolean(doc
                        .getRootElement().elementTextTrim("Success")));
                queryAuditMailResult.setMessage(doc.getRootElement()
                        .elementTextTrim("Message"));
                queryAuditMailResult.setElapsedMilliseconds(parseLong(doc
                        .getRootElement()
                        .elementTextTrim("ElapsedMilliseconds")));
                queryAuditMailResult.setMail(doc.getRootElement()
                        .elementTextTrim("ResultObject"));
            }
            return queryAuditMailResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 构建请求版本的参数
     * @param userID
     * @param password
     * @param systemType
     * @return
     */
    private static String buildQueryDataVersionRequestData(String userID,
                                                           String password, String systemType) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.DATA_VERSION_QUERY
                + " xmlns=\"" + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + userID + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("<Patameter>" + systemType + "</Patameter>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + DATA_VERSION_QUERY + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
    /**
     * 使用document进行数据解析
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    private static QueryDataVersionResult getQueryDataVersionObject(
            String response) {
        try {
            QueryDataVersionResult queryDataVersionResult = new QueryDataVersionResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(
                    response.getBytes(ENCODE)));
            {
                queryDataVersionResult.setSuccess(parseBoolean(doc
                        .getRootElement().elementTextTrim("Success")));
                queryDataVersionResult.setMessage(doc.getRootElement()
                        .elementTextTrim("Message"));
                queryDataVersionResult.setElapsedMilliseconds(parseLong(doc
                        .getRootElement()
                        .elementTextTrim("ElapsedMilliseconds")));

                List<Element> versionEles = (List<Element>) doc	.selectNodes("//F_Version");

                F_VersionObj versionObj = null;
                for (Element each : versionEles) {
                    versionObj = new F_VersionObj();
                    versionObj.setID(parseInt(each.elementTextTrim("ID")));
                    versionObj.setStatus(parseInt(each
                            .elementTextTrim("status")));
                    versionObj.setOS(each.elementTextTrim("OS"));
                    versionObj.setVersion(each.elementTextTrim("Version"));
                    versionObj.setDataType(each.elementTextTrim("DataType"));
                    queryDataVersionResult.getVersionObjL().add(versionObj);
                }
            }
            return queryDataVersionResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static QueryAirportResult queryAirport(Context ctx,String UserID, String Password)
            throws Exception {

        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildQueryCommRequestData(WebServUtil.QUERY_AIRPORT, UserID,
                Password).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            //TODO 保存文件
//            HotelUntils.saveAirport(ctx,response);
//            Log.e("---   save---", "queryAirport: "+response );
//            FileUtils.writeStringToFile(FileUtil.getAirportFile(), response, WebServUtil.ENCODE);
            int index = response.indexOf("<QueryAirportResult>");
            int endIndex = response.lastIndexOf("</QueryAirportResult>");
            response = response.substring(index, endIndex
                    + "</QueryAirportResult>".length());
            return getQueryAirportObject(response);
        }
        return null;
    }

    private static String buildQueryCommRequestData(String methodName,
                                                    String user, String password) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + methodName + " xmlns=\""
                + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + user + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + methodName + ">");
        return buildSoapFooter(soapRequestData).toString();
    }

    @SuppressWarnings("unchecked")
    public static QueryAirportResult getQueryAirportObject(String response) {
        try {
            QueryAirportResult queryAirportResult = new QueryAirportResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(
                    response.getBytes(ENCODE)));
            {
                queryAirportResult.setSuccess(parseBoolean(doc.getRootElement()
                        .elementTextTrim("Success")));
                queryAirportResult.setMessage(doc.getRootElement()
                        .elementTextTrim("Message"));
                queryAirportResult.setElapsedMilliseconds(parseLong(doc
                        .getRootElement()
                        .elementTextTrim("ElapsedMilliseconds")));
            }
            List<Element> airLineEles = (List<Element>) doc
                    .selectNodes("//Airport");
            AirportObj airportObj = null;
            for (Element each : airLineEles) {
                airportObj = new AirportObj();
                airportObj.setAirPortCode(each.elementTextTrim("AirPortCode"));
                airportObj.setCityCode(each.elementTextTrim("CityCode"));
                airportObj.setAirPortName(each.elementTextTrim("AirPortName"));
                airportObj.setField1(each.elementTextTrim("Field1"));
                airportObj.setField2(each.elementTextTrim("Field2"));
                airportObj.setField3(each.elementTextTrim("Field3"));
                airportObj.setEngName(each.elementTextTrim("EngName"));
                airportObj.setRemark(each.elementTextTrim("Remark"));
                queryAirportResult.addOne(airportObj);
            }
            //排序
            Collections.sort(queryAirportResult.getAirprots(), new Comparator<AirportObj>() {
                @Override
                public int compare(AirportObj o1, AirportObj o2) {
                    return o1.getFirstPy().compareTo(o2.getFirstPy());
                }
            });
            return queryAirportResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static QueryAirlineResult queryAirlines(String UserID,
                                                   String Password) throws Exception {
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildQueryCommRequestData(WebServUtil.QUERY_AIRLINES,
                UserID, Password).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            //TODO 保存文件
//            FileUtils.writeStringToFile(FileUtil.getAirlineFile(), response, WebServUtil.ENCODE);
            int index = response.indexOf("<QueryAirlinesResult>");
            int endIndex = response.lastIndexOf("</QueryAirlinesResult>");
            response = response.substring(index, endIndex
                    + "</QueryAirlinesResult>".length());
            return getQueryAirlineObject(response);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static QueryAirlineResult getQueryAirlineObject(String response) {
        try {
            QueryAirlineResult queryAirlineResult = new QueryAirlineResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(
                    response.getBytes(ENCODE)));
            {
                queryAirlineResult.setSuccess(parseBoolean(doc.getRootElement()
                        .elementTextTrim("Success")));
                queryAirlineResult.setMessage(doc.getRootElement()
                        .elementTextTrim("Message"));
                queryAirlineResult.setElapsedMilliseconds(parseLong(doc
                        .getRootElement()
                        .elementTextTrim("ElapsedMilliseconds")));
            }
            List<Element> airLineEles = (List<Element>) doc
                    .selectNodes("//AirLine");
            AirlineObj airlineObj = null;
            for (Element each : airLineEles) {
                airlineObj = new AirlineObj();
                airlineObj.setAirlineName(each.elementTextTrim("AirlineName"));
                airlineObj.setCarrierCode(each.elementTextTrim("CarrierCode"));
                airlineObj.setEnglishName(each.elementTextTrim("EnglishName"));
                airlineObj.setField1(each.elementTextTrim("Field1"));
                airlineObj.setField2(each.elementTextTrim("Field2"));
                airlineObj.setField3(each.elementTextTrim("Field3"));
                airlineObj.setID(parseInt(each.elementTextTrim("ID")));
                airlineObj
                        .setNationalCode(each.elementTextTrim("NationalCode"));
                airlineObj.setRemark(each.elementTextTrim("Remark"));
                airlineObj.setSettleCode(each.elementTextTrim("SettleCode"));
                airlineObj.setShortName(each.elementTextTrim("ShortName"));
                queryAirlineResult.addOne(airlineObj);
            }

            return queryAirlineResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static QueryPlaneStyleResult queryPlaneStyle(String UserID,
                                                        String Password) throws Exception {
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildQueryCommRequestData(WebServUtil.QUERY_PLANE_STYLE,
                UserID, Password).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            //TODO 保存文件
//            FileUtils.writeStringToFile(FileUtil.getPlaneStyleFile(), response, WebServUtil.ENCODE);
            int index = response.indexOf("<QueryPlaneStyleResult>");
            int endIndex = response.lastIndexOf("</QueryPlaneStyleResult>");
            response = response.substring(index, endIndex
                    + "</QueryPlaneStyleResult>".length());
            return getQueryPlaneStyleObject(response);
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    public static QueryPlaneStyleResult getQueryPlaneStyleObject(
            String response) {
        try {
            QueryPlaneStyleResult queryPlaneStyleResult = new QueryPlaneStyleResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(
                    response.getBytes(ENCODE)));
            {
                queryPlaneStyleResult.setSuccess(parseBoolean(doc
                        .getRootElement().elementTextTrim("Success")));
                queryPlaneStyleResult.setMessage(doc.getRootElement()
                        .elementTextTrim("Message"));
                queryPlaneStyleResult.setElapsedMilliseconds(parseLong(doc
                        .getRootElement()
                        .elementTextTrim("ElapsedMilliseconds")));
            }
            List<Element> airLineEles = (List<Element>) doc
                    .selectNodes("//PlaneStyle");
            PlaneStyleObj planeStyleObj = null;
            for (Element each : airLineEles) {
                planeStyleObj = new PlaneStyleObj();
                planeStyleObj.setID(parseInt(each.elementTextTrim("ID")));
                planeStyleObj.setName(each.elementTextTrim("Name"));
                planeStyleObj.setCode(each.elementTextTrim("Code"));
                planeStyleObj.setField1(each.elementTextTrim("Field1"));
                planeStyleObj.setField2(each.elementTextTrim("Field2"));
                planeStyleObj.setField3(each.elementTextTrim("Field3"));
                planeStyleObj.setRemark(each.elementTextTrim("Remark"));
                queryPlaneStyleResult.addOne(planeStyleObj);
            }

            return queryPlaneStyleResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static QueryReasonCodeResult queryReasonCode(String UserID,
                                                        String Password, String UserName) throws Exception {
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildQueryAuditMailOrReasonCodeRequestData(
                WebServUtil.REASON_CODE_QUERY, UserID, Password, UserName)
                .getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);

        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<ReasonCodeQueryResult>");
            int endIndex = response.lastIndexOf("</ReasonCodeQueryResult>");
            response = response.substring(index, endIndex
                    + "</ReasonCodeQueryResult>".length());
            return getQueryReasonCodeObject(response);
        }
        return null;
    }
    private static String buildQueryAuditMailOrReasonCodeRequestData(
            String methodName, String userID, String password, String patameter) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + methodName + " xmlns=\""
                + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + userID + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("<Patameter>" + patameter + "</Patameter>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + methodName + ">");
        return buildSoapFooter(soapRequestData).toString();
    }

    @SuppressWarnings("unchecked")
    private static QueryReasonCodeResult getQueryReasonCodeObject(
            String response) {
        try {
            QueryReasonCodeResult queryReasonCodeResult = new QueryReasonCodeResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(
                    response.getBytes(ENCODE)));
            {
                queryReasonCodeResult.setSuccess(parseBoolean(doc
                        .getRootElement().elementTextTrim("Success")));
                queryReasonCodeResult.setMessage(doc.getRootElement()
                        .elementTextTrim("Message"));
                queryReasonCodeResult.setElapsedMilliseconds(parseLong(doc
                        .getRootElement()
                        .elementTextTrim("ElapsedMilliseconds")));
            }
            List<Element> airLineEles = (List<Element>) doc
                    .selectNodes("//F_ClientReasonCode");
            F_ClientReasonCodeObj clientReasonCodeObj = null;
            for (Element each : airLineEles) {
                clientReasonCodeObj = new F_ClientReasonCodeObj();
                clientReasonCodeObj.setID(parseInt(each.elementTextTrim("ID")));
                clientReasonCodeObj.setClientID(parseInt(each
                        .elementTextTrim("ClientID")));
                clientReasonCodeObj.setType(parseInt(each
                        .elementTextTrim("Type")));
                clientReasonCodeObj.setCode(each.elementTextTrim("Code"));
                clientReasonCodeObj.setDescC(each.elementTextTrim("DescC"));
                clientReasonCodeObj.setDescE(each.elementTextTrim("DescE"));
                clientReasonCodeObj.setRemark(each.elementTextTrim("Remark"));
                queryReasonCodeResult.addOne(clientReasonCodeObj);
            }

            return queryReasonCodeResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
