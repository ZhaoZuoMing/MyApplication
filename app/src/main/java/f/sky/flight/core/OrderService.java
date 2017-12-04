package f.sky.flight.core;

import android.util.Log;

import com.myuntils.DateUtil;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import f.sky.flight.model.ApplyObj;
import f.sky.flight.model.ApplyQueryByIdResult;
import f.sky.flight.model.B_OrderDOObj;
import f.sky.flight.model.B_TouristDOObj;
import f.sky.flight.model.CheckRequestNoteResObj;
import f.sky.flight.model.CostCenterObj;
import f.sky.flight.model.GetClientPriceResult;
import f.sky.flight.model.OrderCanPayResObj;
import f.sky.flight.model.OrderOrApplyStatusOperaResult;
import f.sky.flight.model.OrderQueryByIdResult;
import f.sky.flight.model.QueryCostCenterResult;
import f.sky.flight.model.QueryOrderOrApplyResult;
import f.sky.flight.model.ServerResObj;
import f.sky.flight.model.SkyWayObj;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class OrderService extends WebServUtil{

    public static ApplyQueryByIdResult apply_QueryByID(String userID, String password, String UserName, int ApplyID)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildOrderCommRequestData(WebServUtil.APPLY_QUERYBYID, userID, password, UserName, ApplyID).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == WebServUtil.HTTP_STATUS_OK){
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<Apply_QueryByIDResult>");
            int endIndex = response.lastIndexOf("</Apply_QueryByIDResult>");
            response = response.substring(index, endIndex + "</Apply_QueryByIDResult>".length());
            return getApply_QueryByIDObject(response);
        }
        return null;
    }
    /**
     * @param userID
     * @param password
     * @param OrderID
     * @param  :保存   5:废除   10:未审  15:在线支付未支付  20:已出票
     * @return
     * @throws Exception
     */
    public static OrderOrApplyStatusOperaResult orderStatusChange(String userID, String password, String userName, int OrderID, int OrderStatus)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildApplyOrderStatusCommRequestData(WebServUtil.ORDER_STATUS_CHANGE, userID, password, userName, OrderID, OrderStatus).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == WebServUtil.HTTP_STATUS_OK){
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<OrderStatusChangeResult>");
            int endIndex = response.lastIndexOf("</OrderStatusChangeResult>");
            response = response.substring(index, endIndex + "</OrderStatusChangeResult>".length());
            return getOrderStatusOperaObject(response, true);
        }
        return null;
    }

    private static String buildApplyOrderStatusCommRequestData(
            String methodName, String userID, String password, String Patameter,
            int patameter1, int patameter2) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + methodName + " xmlns=\"" + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>"+userID+"</UserID>");
        soapRequestData.append("<Password>"+password+"</Password>");
        soapRequestData.append("<Patameter>"+Patameter+"</Patameter>");
        soapRequestData.append("<Patameter1>"+patameter1+"</Patameter1>");
        soapRequestData.append("<Patameter2>"+patameter2+"</Patameter2>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + methodName + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
    private static OrderOrApplyStatusOperaResult getOrderStatusOperaObject(String response, boolean fromOrder) {
        try {
            OrderOrApplyStatusOperaResult orderStatusOperaResult = new OrderOrApplyStatusOperaResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                orderStatusOperaResult.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                orderStatusOperaResult.setMessage(doc.getRootElement().elementTextTrim("Message"));
                orderStatusOperaResult.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
            }
            if(fromOrder){
                B_OrderDOObj orderDOObj = parseOrderElement((Element)doc.selectSingleNode("//ResultObject"));
                if(null != orderDOObj && orderDOObj.getID() != 0){
                    orderStatusOperaResult.setResultObject(true);
                    orderStatusOperaResult.setOrderDOObj(orderDOObj);
                }
            } else {
                orderStatusOperaResult.setResultObject(parseBoolean(doc.getRootElement().elementTextTrim("ResultObject")));
            }
            return orderStatusOperaResult;
        } catch (Exception e) {
            Log.e(TAG, "getOrderStatusOperaObject: "+e.getMessage() );
            return null;
        }
    }


    /**
     * @param userID
     * @param password
     * @param UserName
     * @param ApplyID
     * @param :保存   1:通过  99:拒绝
     * @return
     * @throws Exception
     */
    public static OrderOrApplyStatusOperaResult applyStatusChange(String userID, String password, String UserName, int ApplyID, int ApplyStatus)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildApplyOrderStatusCommRequestData(WebServUtil.APPLY_STATUS_CHANGE, userID, password, UserName, ApplyID, ApplyStatus).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == WebServUtil.HTTP_STATUS_OK){
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<ApplyStatusChangeResult>");
            int endIndex = response.lastIndexOf("</ApplyStatusChangeResult>");
            response = response.substring(index, endIndex + "</ApplyStatusChangeResult>".length());
            return getOrderStatusOperaObject(response, false);
        }
        return null;
    }

    public static OrderCanPayResObj orderCanPay(String userID, String password, String userName, int orderId)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildOrderCanPayRequestData(WebServUtil.ORDER_CAN_PAY, userID, password, userName, orderId).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == WebServUtil.HTTP_STATUS_OK){
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<OrderCanPayResult>");
            int endIndex = response.lastIndexOf("</OrderCanPayResult>");
            response = response.substring(index, endIndex + "</OrderCanPayResult>".length());
            System.out.println("response---" + response);
            return getOrderCanPayObject(response);
        }
        return null;
    }
    private static OrderCanPayResObj getOrderCanPayObject(String response) {
        try {
            OrderCanPayResObj orderCanPayResObj = new OrderCanPayResObj();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                orderCanPayResObj.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                orderCanPayResObj.setMessage(doc.getRootElement().elementTextTrim("Message"));
                orderCanPayResObj.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
                orderCanPayResObj.setOrderCanPayResult(parseBoolean(doc.getRootElement().elementTextTrim("ResultObject")));
            }
            return orderCanPayResObj;
        } catch (Exception e) {
            Log.e(TAG, "getOrderCanPayObject: "+e.getMessage() );
            return null;
        }
    }
    private static String buildOrderCanPayRequestData(
            String methodName, String userID, String password, String Patameter,
            int patameter1) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + methodName + " xmlns=\"" + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>"+userID+"</UserID>");
        soapRequestData.append("<Password>"+password+"</Password>");
        soapRequestData.append("<Patameter>"+Patameter+"</Patameter>");
        soapRequestData.append("<Patameter1>"+patameter1+"</Patameter1>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + methodName + ">");
        return buildSoapFooter(soapRequestData).toString();
    }


    /**查询机票订单**/
    public static QueryOrderOrApplyResult order_Query(String userID, String password, String username, int pageindex, int pagesize)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildOrderOrApplyQueryRequestData(WebServUtil.ORDER_QUERY, userID, password, username, pageindex, pagesize).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == 200){
            String response = postMethod.getResponseBodyAsString();
            Log.e(TAG, "查询到的机票订单："+response);
            int index = response.indexOf("<Order_QueryResult>");
            int endIndex = response.lastIndexOf("</Order_QueryResult>");
            response = response.substring(index, endIndex + "</Order_QueryResult>".length());
            return getOrderOrApplyQueryObject(response);
        }
        return null;
    }
     /**构建参数**/
    private static String buildOrderOrApplyQueryRequestData(String methodName, String userID, String password,
                                                            String patameter, int patameter1, int patameter2) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + methodName + " xmlns=\"" + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>"+userID+"</UserID>");
        soapRequestData.append("<Password>"+password+"</Password>");
        soapRequestData.append("<Patameter>"+patameter+"</Patameter>");
        soapRequestData.append("<Patameter1>"+patameter1+"</Patameter1>");
        soapRequestData.append("<Patameter2>"+patameter2+"</Patameter2>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + methodName + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
    /**审核邮箱**/
    public static ServerResObj resendAuditMail(String userID, String password, String UserName, String AuditMail, int ApplyID)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildOperaResCommRequestData(WebServUtil.RESEND_AUDIT_MAIL, userID, password, UserName, AuditMail, ApplyID).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == WebServUtil.HTTP_STATUS_OK){
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<ReSendAuditMailResult>");
            int endIndex = response.lastIndexOf("</ReSendAuditMailResult>");
            response = response.substring(index, endIndex + "</ReSendAuditMailResult>".length());
            return getOperaResObject(response);
        }
        return null;
    }

    private static String buildOperaResCommRequestData(
            String methodName, String userID, String password, String Patameter,
            String patameter1, int patameter2) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + methodName + " xmlns=\"" + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>"+userID+"</UserID>");
        soapRequestData.append("<Password>"+password+"</Password>");
        soapRequestData.append("<Patameter>"+Patameter+"</Patameter>");
        soapRequestData.append("<Patameter1>"+patameter1+"</Patameter1>");
        soapRequestData.append("<Patameter2>"+patameter2+"</Patameter2>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + methodName + ">");
        return buildSoapFooter(soapRequestData).toString();
    }

    private static ServerResObj getOperaResObject(String response) {
        try {
            ServerResObj operaRes = new ServerResObj();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                operaRes.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                operaRes.setMessage(doc.getRootElement().elementTextTrim("Message"));
                operaRes.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
            }
            return operaRes;
        } catch (Exception e) {
            Log.e(TAG, "getOperaResObject: "+e.getMessage() );
            return null;
        }
    }


    /** **/

    public static ServerResObj urgentTicket(String userID, String password, String UserName, String Reason, int ApplyID)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildOperaResCommRequestData(WebServUtil.TICKET_URGENT, userID, password, UserName, Reason, ApplyID).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == WebServUtil.HTTP_STATUS_OK){
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<TicketUrgentResult>");
            int endIndex = response.lastIndexOf("</TicketUrgentResult>");
            response = response.substring(index, endIndex + "</TicketUrgentResult>".length());
            return getOperaResObject(response);
        }
        return null;
    }
     /**通过订单号来查询订单**/
    public static OrderQueryByIdResult order_QueryByID(String userID, String password, String UserName, int OrderID)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildOrderCommRequestData(WebServUtil.ORDER_QUERYBYID, userID, password, UserName, OrderID).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == WebServUtil.HTTP_STATUS_OK){
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<Order_QueryByIDResult>");
            int endIndex = response.lastIndexOf("</Order_QueryByIDResult>");
            response = response.substring(index, endIndex + "</Order_QueryByIDResult>".length());
            return getOrderQueryByIdObject(response);
        }
        return null;
    }

    private static String buildOrderCommRequestData(
            String methodName, String userID, String password,
            Object patameter, int patameter1) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + methodName + " xmlns=\"" + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>"+userID+"</UserID>");
        soapRequestData.append("<Password>"+password+"</Password>");
        soapRequestData.append("<Patameter>"+patameter+"</Patameter>");
        soapRequestData.append("<Patameter1>"+patameter1+"</Patameter1>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + methodName + ">");
        return buildSoapFooter(soapRequestData).toString();
    }

    private static OrderQueryByIdResult getOrderQueryByIdObject(String response) {
        try {
            OrderQueryByIdResult orderQueryByIdResult = new OrderQueryByIdResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                orderQueryByIdResult.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                orderQueryByIdResult.setMessage(doc.getRootElement().elementTextTrim("Message"));
                orderQueryByIdResult.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
            }
            Element resultObjectEle = (Element)doc.selectSingleNode("//ResultObject");
            orderQueryByIdResult.setOrderDo(parseOrderElement(resultObjectEle));
            return orderQueryByIdResult;
        } catch (Exception e) {
            Log.e(TAG, "getOrderQueryByIdObject: "+e.getMessage() );
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static QueryOrderOrApplyResult getOrderOrApplyQueryObject(String response) {
        try {
            QueryOrderOrApplyResult orderQueryResult = new QueryOrderOrApplyResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                orderQueryResult.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                orderQueryResult.setMessage(doc.getRootElement().elementTextTrim("Message"));
                orderQueryResult.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
            }
            List<Element> resultObjectEles = (List<Element>)doc.selectNodes("//B_OrderDO");
            for(Element e: resultObjectEles){
                orderQueryResult.addOneOrder(parseOrderElement(e));
            }
            return orderQueryResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ApplyQueryByIdResult bizSealSeat(String userID, String password, ApplyObj applyObj, B_OrderDOObj order, String patameter1)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);
        byte[] b = buildBizSealSeatRequestData(userID, password, applyObj, order, patameter1).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == WebServUtil.HTTP_STATUS_OK){
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<BizSealSeatResult>");
            int endIndex = response.lastIndexOf("</BizSealSeatResult>");
            response = response.substring(index, endIndex + "</BizSealSeatResult>".length());
            return getApply_QueryByIDObject(response);
        }
        return null;
    }
    private static ApplyQueryByIdResult getApply_QueryByIDObject(String response) {
        try {
            ApplyQueryByIdResult applyQueryByIdResult = new ApplyQueryByIdResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                applyQueryByIdResult.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                applyQueryByIdResult.setMessage(doc.getRootElement().elementTextTrim("Message"));
                applyQueryByIdResult.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
            }
            Element applyEle = (Element)doc.selectSingleNode("//Apply");
            Element orderEle = (Element)doc.selectSingleNode("//Order");
            applyQueryByIdResult.setOrderDo(parseOrderElement(orderEle));
            applyQueryByIdResult.setApplyObj(parseApplyElement(applyEle));
            return applyQueryByIdResult;
        } catch (Exception e) {
            Log.e(TAG, "getApply_QueryByIDObject: "+e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static B_OrderDOObj parseOrderElement(Element orderEle){
        B_OrderDOObj orderDOObj = new B_OrderDOObj();
        orderDOObj.setAirlinePolicyID(parseInt(orderEle.elementTextTrim("AirlinePolicyID")));
        orderDOObj.setTotalCount(parseInt(orderEle.elementTextTrim("TotalCount")));
        orderDOObj.setApplyID(parseInt(orderEle.elementTextTrim("ApplyID")));
        orderDOObj.setApplyStatus(parseInt(orderEle.elementTextTrim("ApplyStatus")));
        orderDOObj.setBookID(parseInt(orderEle.elementTextTrim("BookID")));
        orderDOObj.setCashStatus(parseInt(orderEle.elementTextTrim("CashStatus")));
        orderDOObj.setCategoryID(parseInt(orderEle.elementTextTrim("CategoryID")));
        orderDOObj.setClientID(parseInt(orderEle.elementTextTrim("ClientID")));
        orderDOObj.setClientPolicyID(parseInt(orderEle.elementTextTrim("ClientPolicyID")));
        orderDOObj.setCorpID(parseInt(orderEle.elementTextTrim("CorpID")));
        orderDOObj.setDeptID(parseInt(orderEle.elementTextTrim("DeptID")));
        orderDOObj.setEtdzID(parseInt(orderEle.elementTextTrim("EtdzID")));
        orderDOObj.setGroupFlag(parseInt(orderEle.elementTextTrim("GroupFlag")));
        orderDOObj.setID(parseInt(orderEle.elementTextTrim("ID")));
        orderDOObj.setObeyRole(parseInt(orderEle.elementTextTrim("ObeyRole")));
        orderDOObj.setOrderID(parseInt(orderEle.elementTextTrim("OrderID")));
        orderDOObj.setOrderSource(parseInt(orderEle.elementTextTrim("OrderSource")));
        orderDOObj.setOrderStatus(parseInt(orderEle.elementTextTrim("OrderStatus")));
        orderDOObj.setOrderType(parseInt(orderEle.elementTextTrim("OrderType")));
        orderDOObj.setPassengerCount(parseInt(orderEle.elementTextTrim("PassengerCount")));
        orderDOObj.setPayID(parseInt(orderEle.elementTextTrim("PayID")));
        orderDOObj.setPayType(parseInt(orderEle.elementTextTrim("PayType")));
        orderDOObj.setReasonCodeID(parseInt(orderEle.elementTextTrim("ReasonCodeID")));
        orderDOObj.setReasonCode(orderEle.elementTextTrim("ReasonCode"));
        orderDOObj.setReasonCodeDesc(orderEle.elementTextTrim("ReasonCodeDesc"));
        orderDOObj.setSelfTake(parseInt(orderEle.elementTextTrim("SelfTake")));
        orderDOObj.setSmsStatus(parseInt(orderEle.elementTextTrim("SmsStatus")));
        orderDOObj.setTripStatus(parseInt(orderEle.elementTextTrim("TripStatus")));

        orderDOObj.setFuelTax(parseDouble(orderEle.elementTextTrim("FuelTax")));
        orderDOObj.setOrgTotalPrice(parseDouble(orderEle.elementTextTrim("OrgTotalPrice")));
        orderDOObj.setOtherPrice(parseDouble(orderEle.elementTextTrim("OtherPrice")));
        orderDOObj.setTax(parseDouble(orderEle.elementTextTrim("Tax")));
        orderDOObj.setTotalPrice(parseDouble(orderEle.elementTextTrim("TotalPrice")));

        orderDOObj.setBookDate(orderEle.elementTextTrim("BookDate"));
        orderDOObj.setCity(orderEle.elementTextTrim("City"));
        orderDOObj.setCostCenter(orderEle.elementTextTrim("CostCenter"));
        orderDOObj.setCostID(orderEle.elementTextTrim("CostID"));
        orderDOObj.setCostNumber(orderEle.elementTextTrim("CostNumber"));
        orderDOObj.setDeliverAddr(orderEle.elementTextTrim("DeliverAddr"));
        orderDOObj.setExternalNo(orderEle.elementTextTrim("ExternalNo"));
        orderDOObj.setField1(orderEle.elementTextTrim("Field1"));
        orderDOObj.setField2(orderEle.elementTextTrim("Field2"));
        orderDOObj.setField3(orderEle.elementTextTrim("Field3"));
        orderDOObj.setField4(orderEle.elementTextTrim("Field4"));
        orderDOObj.setField5(orderEle.elementTextTrim("Field5"));
        orderDOObj.setField6(orderEle.elementTextTrim("Field6"));
        orderDOObj.setField7(orderEle.elementTextTrim("Field7"));
        orderDOObj.setField8(orderEle.elementTextTrim("Field8"));
        orderDOObj.setField9(orderEle.elementTextTrim("Field9"));
        orderDOObj.setField10(orderEle.elementTextTrim("Field10"));
        orderDOObj.setFlightNos(orderEle.elementTextTrim("FlightNos"));
        orderDOObj.setLinkEmail(orderEle.elementTextTrim("LinkEmail"));
        orderDOObj.setLinkMan(orderEle.elementTextTrim("LinkMan"));
        orderDOObj.setLinkTel(orderEle.elementTextTrim("LinkTel"));
        orderDOObj.setLowestHint(orderEle.elementTextTrim("LowestHint"));
        orderDOObj.setOfficeCode(orderEle.elementTextTrim("OfficeCode"));
        orderDOObj.setPnrNo(orderEle.elementTextTrim("PnrNo"));
        orderDOObj.setSchemeID(orderEle.elementTextTrim("SchemeID"));
        orderDOObj.setSkyways(orderEle.elementTextTrim("Skyways"));
        orderDOObj.setSkywayTakeOffDates(orderEle.elementTextTrim("SkywayTakeOffDates"));
        orderDOObj.setTktNos(orderEle.elementTextTrim("TktNos"));
        orderDOObj.setTourists(orderEle.elementTextTrim("Tourists"));
        orderDOObj.setAuditor(orderEle.elementTextTrim("Auditor"));

        List<Element> touristEles = (List<Element>) orderEle.selectNodes("//B_TouristDO");
        for(Element e: touristEles){
            orderDOObj.getTouristDOObjL().add(parseTouristElement(e));
        }

        List<Element> skywayEles = (List<Element>) orderEle.selectNodes("//B_SkyWayDO");
        for(Element e: skywayEles){
            orderDOObj.getSkyWayObjL().add(parseSkywayElement(e));
        }
        return orderDOObj;
    }
    private static SkyWayObj parseSkywayElement(Element skywayEle){
        SkyWayObj skyWayObj = new SkyWayObj();
        skyWayObj.setSkyWayID(parseInt(skywayEle.elementTextTrim("SkyWayID")));
        skyWayObj.setStatus(parseInt(skywayEle.elementTextTrim("Status")));
        skyWayObj.setSkyWayGroupID(parseInt(skywayEle.elementTextTrim("SkyWayGroupID")));
        skyWayObj.setOrderID(parseInt(skywayEle.elementTextTrim("OrderID")));
        skyWayObj.setGroupSerial(parseInt(skywayEle.elementTextTrim("GroupSerial")));
        skyWayObj.setSkyWayType(parseInt(skywayEle.elementTextTrim("SkyWayType")));
        skyWayObj.setSkyWayStatus(parseInt(skywayEle.elementTextTrim("SkyWayStatus")));
        skyWayObj.setPricePolicyID(parseInt(skywayEle.elementTextTrim("PricePolicyID")));
        skyWayObj.setReasonCodeID(parseInt(skywayEle.elementTextTrim("ReasonCodeID")));

        skyWayObj.setStandardPrice(parseDouble(skywayEle.elementTextTrim("StandardPrice")));
        skyWayObj.setYPrice(parseDouble(skywayEle.elementTextTrim("YPrice")));
        skyWayObj.setPrice(parseDouble(skywayEle.elementTextTrim("Price")));
        skyWayObj.setFuelTax(parseDouble(skywayEle.elementTextTrim("FuelTax")));
        skyWayObj.setTax(parseDouble(skywayEle.elementTextTrim("Tax")));
        skyWayObj.setOtherPrice(parseDouble(skywayEle.elementTextTrim("OtherPrice")));
        skyWayObj.setLowestPrice(parseDouble(skywayEle.elementTextTrim("LowestPrice")));

        skyWayObj.setField1(skywayEle.elementTextTrim("Field1"));
        skyWayObj.setField2(skywayEle.elementTextTrim("Field2"));
        skyWayObj.setField3(skywayEle.elementTextTrim("Field3"));
        skyWayObj.setNonRer(skywayEle.elementTextTrim("NonRer"));
        skyWayObj.setNonRef(skywayEle.elementTextTrim("NonRef"));
        skyWayObj.setNonEnd(skywayEle.elementTextTrim("NonEnd"));
        skyWayObj.setSameDayHint(skywayEle.elementTextTrim("SameDayHint"));
        skyWayObj.setLowestHint(skywayEle.elementTextTrim("LowestHint"));
        skyWayObj.setBrokeRoles(skywayEle.elementTextTrim("BrokeRoles"));
        skyWayObj.setBrokeRolesReason(skywayEle.elementTextTrim("BrokeRolesReason"));
        skyWayObj.setStandardCabin(skywayEle.elementTextTrim("StandardCabin"));
        skyWayObj.setMainPnrNo(skywayEle.elementTextTrim("MainPnrNo"));
        skyWayObj.setOrinPnrNo(skywayEle.elementTextTrim("OrinPnrNo"));
        skyWayObj.setFlightNo(skywayEle.elementTextTrim("FlightNo"));
        skyWayObj.setCarrier(skywayEle.elementTextTrim("Carrier"));
        skyWayObj.setOrgCity(skywayEle.elementTextTrim("OrgCity"));
        skyWayObj.setDstCity(skywayEle.elementTextTrim("DstCity"));
        skyWayObj.setCabin(skywayEle.elementTextTrim("Cabin"));
        skyWayObj.setCabinDesc(skywayEle.elementTextTrim("CabinDesc"));
        skyWayObj.setTakeOffDate(skywayEle.elementTextTrim("TakeOffDate"));
        skyWayObj.setTakeOffTime(skywayEle.elementTextTrim("TakeOffTime"));
        skyWayObj.setLandDate(skywayEle.elementTextTrim("LandDate"));
        skyWayObj.setLandTime(skywayEle.elementTextTrim("LandTime"));
        skyWayObj.setRemark(skywayEle.elementTextTrim("Remark"));
        skyWayObj.setOrgTeminal(skywayEle.elementTextTrim("OrgTeminal"));
        skyWayObj.setDstTeminal(skywayEle.elementTextTrim("DstTeminal"));
        skyWayObj.setReasonCode(skywayEle.elementTextTrim("ReasonCode"));
        skyWayObj.setReasonCodeDesc(skywayEle.elementTextTrim("ReasonCodeDesc"));
        skyWayObj.setLowestCabin(skywayEle.elementTextTrim("LowestCabin"));
        skyWayObj.setLowestCabinDesc(skywayEle.elementTextTrim("LowestCabinDesc"));
        skyWayObj.setRerNotes(skywayEle.elementTextTrim("RerNotes"));
        skyWayObj.setRefNotes(skywayEle.elementTextTrim("RefNotes"));
        skyWayObj.setEndNotes(skywayEle.elementTextTrim("EndNotes"));
        skyWayObj.setAgreementCode(skywayEle.elementTextTrim("AgreementCode"));
        skyWayObj.setAgreementID(skywayEle.elementTextTrim("AgreementID"));
        skyWayObj.setDisCountCode(skywayEle.elementTextTrim("DisCountCode"));
        skyWayObj.setCarrierFlight(skywayEle.elementTextTrim("CarrierFlight"));
        skyWayObj.setIsCodeShare(parseBoolean(skywayEle.elementTextTrim("IsCodeShare")));
        skyWayObj.setPlaneStyle(skywayEle.elementTextTrim("PlaneStyle"));
        return skyWayObj;
    }
    private static ApplyObj parseApplyElement(Element applyEle){
        ApplyObj applyObj = new ApplyObj();
        applyObj.setAuditDate(parseDate(applyEle.elementTextTrim("AuditDate")));
        applyObj.setAuditID(parseInt(applyEle.elementTextTrim("AuditID")));
        applyObj.setAuditMail(applyEle.elementTextTrim("AuditMail"));
        applyObj.setReason(applyEle.elementTextTrim("Reason"));
        applyObj.setBookID(parseInt(applyEle.elementTextTrim("BookID")));
        applyObj.setDepID(parseInt(applyEle.elementTextTrim("DepID")));
        applyObj.setID(parseInt(applyEle.elementTextTrim("ID")));
        applyObj.setOrderID(parseInt(applyEle.elementTextTrim("OrderID")));
        applyObj.setStatus(parseInt(applyEle.elementTextTrim("Status")));
        applyObj.setInsertDate(parseDate(applyEle.elementTextTrim("InsertDate")));
        return applyObj;
    }
    private static B_TouristDOObj parseTouristElement(Element touristEle){
        B_TouristDOObj touristDOObj = new B_TouristDOObj();
        touristDOObj.setTouristID(parseInt(touristEle.elementTextTrim("TouristID")));
        touristDOObj.setOrderID(parseInt(touristEle.elementTextTrim("OrderID")));
        touristDOObj.setUserID(parseInt(touristEle.elementTextTrim("UserID")));
        touristDOObj.setTouristType(parseInt(touristEle.elementTextTrim("TouristType")));
        touristDOObj.setPassengerID(parseInt(touristEle.elementTextTrim("PassengerID")));
        touristDOObj.setIDType(parseInt(touristEle.elementTextTrim("IDType")));
        touristDOObj.setStatus(parseInt(touristEle.elementTextTrim("Status")));
        touristDOObj.setTouristName(touristEle.elementTextTrim("TouristName"));
        touristDOObj.setMobileNo(touristEle.elementTextTrim("MobileNo"));
        touristDOObj.setNationalCode(touristEle.elementTextTrim("NationalCode"));
        touristDOObj.setNationalityName(touristEle.elementTextTrim("NationalityName"));
        touristDOObj.setField1(touristEle.elementTextTrim("Field1"));
        touristDOObj.setField2(touristEle.elementTextTrim("Field2"));
        touristDOObj.setIDNumber(touristEle.elementTextTrim("IDNumber"));
        return touristDOObj;
    }
    private static String buildBizSealSeatRequestData(String userID,
                                                      String password, ApplyObj applyObj, B_OrderDOObj orderObj, String patameter1) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.BIZ_SEAL_SEAT + " xmlns=\"" + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>"+userID+"</UserID>");
        soapRequestData.append("<Password>"+password+"</Password>");
        soapRequestData.append("<Patameter>");
        soapRequestData.append("<Apply>");
        appendApply(soapRequestData, applyObj);
        soapRequestData.append("</Apply>");
        soapRequestData.append("<Order>");
        appendOrder(soapRequestData, orderObj);
        soapRequestData.append("</Order>");
        soapRequestData.append("</Patameter>");
        soapRequestData.append("<Patameter1>"+patameter1+"</Patameter1>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.BIZ_SEAL_SEAT + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
    public static CheckRequestNoteResObj CheckRequestNote(String userID, String password, int clientID, String costNumber)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildCheckRequestNoteRequestData(WebServUtil.CHECK_REQUEST_NOTE, userID, password, clientID, costNumber).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == WebServUtil.HTTP_STATUS_OK){
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<CheckRequestNoteResult>");
            int endIndex = response.lastIndexOf("</CheckRequestNoteResult>");
            response = response.substring(index, endIndex + "</CheckRequestNoteResult>".length());
            System.out.println("response---" + response);
            return getCheckRequestNoteObject(response);
        }
        return null;
    }
    private static String buildCheckRequestNoteRequestData(
            String methodName, String userID, String password, int Patameter,
            String patameter1) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + methodName + " xmlns=\"" + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>"+userID+"</UserID>");
        soapRequestData.append("<Password>"+password+"</Password>");
        soapRequestData.append("<Patameter>"+Patameter+"</Patameter>");
        soapRequestData.append("<Patameter1>"+patameter1+"</Patameter1>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + methodName + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
    private static CheckRequestNoteResObj getCheckRequestNoteObject(String response) {
        try {
            CheckRequestNoteResObj checkRequestNoteResObj = new CheckRequestNoteResObj();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                checkRequestNoteResObj.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                checkRequestNoteResObj.setMessage(doc.getRootElement().elementTextTrim("Message"));
                checkRequestNoteResObj.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
                checkRequestNoteResObj.setCheckRequestNoteResult(parseBoolean(doc.getRootElement().elementTextTrim("ResultObject")));
            }
            return checkRequestNoteResObj;
        } catch (Exception e) {
            Log.e(TAG, "getCheckRequestNoteObject: "+e.getMessage() );
            return null;
        }
    }

    public static QueryCostCenterResult costCenterQuery(String userID, String password, String UserName)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);
        byte[] b = buildCostCenterQueryRequestData(userID, password, UserName).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == WebServUtil.HTTP_STATUS_OK){
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<CostCenterQueryResult>");
            int endIndex = response.lastIndexOf("</CostCenterQueryResult>");
            response = response.substring(index, endIndex + "</CostCenterQueryResult>".length());
            return getCostCenterQueryObject(response);
        }
        return null;
    }
    private static String buildCostCenterQueryRequestData(String userID,
                                                          String password, String patameter) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.COST_CENTER_QUERY + " xmlns=\"" + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>"+userID+"</UserID>");
        soapRequestData.append("<Password>"+password+"</Password>");
        soapRequestData.append("<Patameter>"+patameter+"</Patameter>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.COST_CENTER_QUERY + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
    @SuppressWarnings("unchecked")
    private static QueryCostCenterResult getCostCenterQueryObject(String response) {
        try {
            QueryCostCenterResult costCenterQueryResult = new QueryCostCenterResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                costCenterQueryResult.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                costCenterQueryResult.setMessage(doc.getRootElement().elementTextTrim("Message"));
                costCenterQueryResult.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
            }
            List<Element> costCenterEles = (List<Element>)doc.selectNodes("//CostCenter");
            for(Element e: costCenterEles){
                costCenterQueryResult.addOneCostCenter((parseCostCenterElement(e)));
            }
            return costCenterQueryResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static CostCenterObj parseCostCenterElement(Element costCenterEle){
        CostCenterObj costCenterObj = new CostCenterObj();
        costCenterObj.setCostCenterCode(costCenterEle.elementTextTrim("CostCenterCode"));
        costCenterObj.setCostCenterName(costCenterEle.elementTextTrim("CostCenterName"));
        costCenterObj.setProjectNumNeeded(parseInt(costCenterEle.elementTextTrim("ProjectNumNeeded")));
        costCenterObj.setDepID(parseInt(costCenterEle.elementTextTrim("DepID")));
        costCenterObj.setID(parseInt(costCenterEle.elementTextTrim("ID")));
        costCenterObj.setSortIndex(parseInt(costCenterEle.elementTextTrim("SortIndex")));
        costCenterObj.setStatus(parseInt(costCenterEle.elementTextTrim("Status")));
        costCenterObj.setRemark(costCenterEle.elementTextTrim("Remark"));
        return costCenterObj;
    }

    public static GetClientPriceResult getClientPrice(String userID, String password, int ClientID, ApplyObj applyObj, B_OrderDOObj order)throws Exception{
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);
        byte[] b = buildGetClientPriceRequestData(userID, password, ClientID, applyObj, order).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b,0,b.length);
        RequestEntity re = new InputStreamRequestEntity(is,b.length, CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if(statusCode == WebServUtil.HTTP_STATUS_OK){
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<GetClientPriceResult>");
            int endIndex = response.lastIndexOf("</GetClientPriceResult>");
            response = response.substring(index, endIndex + "</GetClientPriceResult>".length());
            return getClientPriceObject(response);
        }
        return null;
    }

    private static GetClientPriceResult getClientPriceObject(String response) {
        try {
            GetClientPriceResult getClientPriceResult = new GetClientPriceResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                getClientPriceResult.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                getClientPriceResult.setMessage(doc.getRootElement().elementTextTrim("Message"));
                getClientPriceResult.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
                getClientPriceResult.setResultObject(parseDouble(doc.getRootElement().elementTextTrim("ResultObject")));
            }
            return getClientPriceResult;
        } catch (Exception e) {
            Log.e("-----提交订单价格异常---",e.getMessage());
            return null;
        }
    }
    private static String buildGetClientPriceRequestData(String userID,
                                                         String password, int patameter, ApplyObj applyObj, B_OrderDOObj orderObj) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.GET_CLIENT_PRICE + " xmlns=\"" + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>"+userID+"</UserID>");
        soapRequestData.append("<Password>"+password+"</Password>");
        soapRequestData.append("<Patameter>"+patameter+"</Patameter>");
        soapRequestData.append("<Patameter1>");
        soapRequestData.append("<Apply>");
        appendApply(soapRequestData, applyObj);
        soapRequestData.append("</Apply>");
        soapRequestData.append("<Order>");
        appendOrder(soapRequestData, orderObj);
        soapRequestData.append("</Order>");
        soapRequestData.append("</Patameter1>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.GET_CLIENT_PRICE + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
    private static void appendApply(StringBuilder soapRequestData, ApplyObj apply){
//		soapRequestData.append("<ID>"+apply.getID()+"</ID>");
        soapRequestData.append("<BookID>"+apply.getBookID()+"</BookID>");
//		soapRequestData.append("<OrderID>"+apply.getOrderID()+"</OrderID>");
        soapRequestData.append("<DepID>"+apply.getDepID()+"</DepID>");
//		soapRequestData.append("<AuditID>"+apply.getAuditID()+"</AuditID>");
        soapRequestData.append("<Status>"+apply.getStatus()+"</Status>");
        soapRequestData.append("<Reason>"+apply.getReason()+"</Reason>");
        soapRequestData.append("<AuditMail>"+apply.getAuditMail()+"</AuditMail>");
        if(null != apply.getInsertDate()){
            soapRequestData.append("<insertDate>"+ DateUtil.yyyy_MM_dd.format(apply.getInsertDate())+"</insertDate>");
        }
        if(null != apply.getAuditDate()){
            soapRequestData.append("<AuditDate>"+DateUtil.yyyy_MM_dd.format(apply.getAuditDate())+"</AuditDate>");
        }
    }

    /*****添加订单***/
    private static void appendOrder(StringBuilder soapRequestData, B_OrderDOObj order){
        soapRequestData.append("<ClientPayType>"+order.getClientPayType()+"</ClientPayType>");
        soapRequestData.append("<ID>"+order.getID()+"</ID>");
        soapRequestData.append("<ApplyID>"+order.getApplyID()+"</ApplyID>");
        soapRequestData.append("<ApplyStatus>"+order.getApplyStatus()+"</ApplyStatus>");
        soapRequestData.append("<OrderID>"+order.getOrderID()+"</OrderID>");
        soapRequestData.append("<OrderStatus>"+order.getOrderStatus()+"</OrderStatus>");
        soapRequestData.append("<ClientID>"+order.getClientID()+"</ClientID>");
        soapRequestData.append("<PnrNo>"+order.getPnrNo()+"</PnrNo>");
        soapRequestData.append("<EtdzID>"+order.getEtdzID()+"</EtdzID>");
        soapRequestData.append("<OfficeCode>"+order.getOfficeCode()+"</OfficeCode>");
        soapRequestData.append("<ClientPolicyID>"+order.getClientPolicyID()+"</ClientPolicyID>");
        soapRequestData.append("<AirlinePolicyID>"+order.getAirlinePolicyID()+"</AirlinePolicyID>");
        soapRequestData.append("<LinkMan>"+order.getLinkMan()+"</LinkMan>");
        soapRequestData.append("<LinkTel>"+order.getLinkTel()+"</LinkTel>");
        soapRequestData.append("<CostCenter>"+order.getCostCenter()+"</CostCenter>");
        soapRequestData.append("<CostNumber>"+order.getCostNumber()+"</CostNumber>");
        soapRequestData.append("<CorpID>"+order.getCorpID()+"</CorpID>");
        soapRequestData.append("<DeptID>"+order.getDeptID()+"</DeptID>");
        soapRequestData.append("<BookID>"+order.getBookID()+"</BookID>");
        soapRequestData.append("<BookDate>"+order.getBookDate()+"</BookDate>");
        soapRequestData.append("<OrderType>"+order.getOrderType()+"</OrderType>");
        soapRequestData.append("<OrderSource>"+order.getOrderSource()+"</OrderSource>");
        soapRequestData.append("<SelfTake>"+order.getSelfTake()+"</SelfTake>");
        soapRequestData.append("<DeliverAddr>"+order.getDeliverAddr()+"</DeliverAddr>");
        soapRequestData.append("<GroupFlag>"+order.getGroupFlag()+"</GroupFlag>");
        soapRequestData.append("<PassengerCount>"+order.getPassengerCount()+"</PassengerCount>");
        soapRequestData.append("<PayType>"+order.getPayType()+"</PayType>");
        soapRequestData.append("<CashStatus>"+order.getCashStatus()+"</CashStatus>");
        soapRequestData.append("<TotalPrice>"+order.getTotalPrice()+"</TotalPrice>");
        soapRequestData.append("<FuelTax>"+order.getFuelTax()+"</FuelTax>");
        soapRequestData.append("<OrgTotalPrice>"+order.getOrgTotalPrice()+"</OrgTotalPrice>");
        soapRequestData.append("<Tax>"+order.getTax()+"</Tax>");
        soapRequestData.append("<OtherPrice>"+order.getOtherPrice()+"</OtherPrice>");
        soapRequestData.append("<TktNos>"+order.getTktNos()+"</TktNos>");
        soapRequestData.append("<ExternalNo>"+order.getExternalNo()+"</ExternalNo>");
        soapRequestData.append("<Remark>"+order.getRemark()+"</Remark>");
        soapRequestData.append("<Field1>"+order.getField1()+"</Field1>");
        soapRequestData.append("<Field2>"+order.getField2()+"</Field2>");
        soapRequestData.append("<Field3>"+order.getField3()+"</Field3>");
        soapRequestData.append("<Field4>"+order.getField4()+"</Field4>");
        soapRequestData.append("<Field5>"+order.getField5()+"</Field5>");
        soapRequestData.append("<Field6>"+order.getField6()+"</Field6>");
        soapRequestData.append("<Field7>"+order.getField7()+"</Field7>");
        soapRequestData.append("<Field8>"+order.getField8()+"</Field8>");
        soapRequestData.append("<Field9>"+order.getField9()+"</Field9>");
        soapRequestData.append("<Field10>"+order.getField10()+"</Field10>");

        soapRequestData.append("<ReasonCodeID>"+order.getReasonCodeID()+"</ReasonCodeID>");
        soapRequestData.append("<ReasonCode>"+order.getReasonCode()+"</ReasonCode>");
        soapRequestData.append("<ReasonCodeDesc>"+order.getReasonCodeDesc()+"</ReasonCodeDesc>");
        soapRequestData.append("<CategoryID>"+order.getCategoryID()+"</CategoryID>");
        soapRequestData.append("<CostID>"+order.getCostID()+"</CostID>");
        soapRequestData.append("<SchemeID>"+order.getSchemeID()+"</SchemeID>");
        soapRequestData.append("<SmsStatus>"+order.getSmsStatus()+"</SmsStatus>");
        soapRequestData.append("<LinkEmail>"+order.getLinkEmail()+"</LinkEmail>");
        soapRequestData.append("<City>"+order.getCity()+"</City>");
        soapRequestData.append("<PayID>"+order.getPayID()+"</PayID>");
        soapRequestData.append("<TripStatus>"+order.getTripStatus()+"</TripStatus>");

        {
            soapRequestData.append("<Tourist>");
            for(B_TouristDOObj tourist: order.getTouristDOObjL()){
                soapRequestData.append("<B_TouristDO>");
                soapRequestData.append("<TouristID>"+tourist.getTouristID()+"</TouristID>");
                soapRequestData.append("<OrderID>"+tourist.getOrderID()+"</OrderID>");
                soapRequestData.append("<UserID>"+tourist.getUserID()+"</UserID>");
                soapRequestData.append("<TouristName>"+tourist.getTouristName()+"</TouristName>");
                soapRequestData.append("<TouristType>"+tourist.getTouristType()+"</TouristType>");
                soapRequestData.append("<MobileNo>"+tourist.getMobileNo()+"</MobileNo>");
                soapRequestData.append("<NationalCode>"+tourist.getNationalCode()+"</NationalCode>");
                soapRequestData.append("<NationalityName>"+tourist.getNationalityName()+"</NationalityName>");
                soapRequestData.append("<PassengerID>"+tourist.getPassengerID()+"</PassengerID>");
                soapRequestData.append("<IDType>"+tourist.getIDType()+"</IDType>");
                soapRequestData.append("<IDNumber>"+tourist.getIDNumber()+"</IDNumber>");
                soapRequestData.append("<Status>"+tourist.getStatus()+"</Status>");
                soapRequestData.append("<Field1>"+tourist.getField1()+"</Field1>");
                soapRequestData.append("<Field2>"+tourist.getField2()+"</Field2>");
                soapRequestData.append("</B_TouristDO>");
            }
            soapRequestData.append("</Tourist>");
        }
        {
            soapRequestData.append("<SkyWay>");
            for(SkyWayObj skywayObj: order.getSkyWayObjL()){
                soapRequestData.append("<B_SkyWayDO>");

                soapRequestData.append("<Distance>"+skywayObj.getDistance()+"</Distance>");
                soapRequestData.append("<SkyWayID>"+skywayObj.getSkyWayID()+"</SkyWayID>");
                soapRequestData.append("<Status>"+skywayObj.getStatus()+"</Status>");
                soapRequestData.append("<SkyWayGroupID>"+skywayObj.getSkyWayGroupID()+"</SkyWayGroupID>");
                soapRequestData.append("<OrderID>"+skywayObj.getOrderID()+"</OrderID>");
                soapRequestData.append("<GroupSerial>"+skywayObj.getGroupSerial()+"</GroupSerial>");
                soapRequestData.append("<SkyWayType>"+skywayObj.getSkyWayType()+"</SkyWayType>");
                soapRequestData.append("<SkyWayStatus>"+skywayObj.getSkyWayStatus()+"</SkyWayStatus>");
                soapRequestData.append("<PricePolicyID>"+skywayObj.getPricePolicyID()+"</PricePolicyID>");
                soapRequestData.append("<Field1>"+skywayObj.getField1()+"</Field1>");
                soapRequestData.append("<Field2>"+skywayObj.getField2()+"</Field2>");
                soapRequestData.append("<Field3>"+skywayObj.getStatus()+"</Field3>");
                soapRequestData.append("<NonRer>"+skywayObj.getNonRer()+"</NonRer>");
                soapRequestData.append("<NonRef>"+skywayObj.getNonRef()+"</NonRef>");
                soapRequestData.append("<NonEnd>"+skywayObj.getNonEnd()+"</NonEnd>");
                soapRequestData.append("<SameDayHint>"+skywayObj.getSameDayHint()+"</SameDayHint>");
                soapRequestData.append("<LowestHint>"+skywayObj.getLowestHint()+"</LowestHint>");
                soapRequestData.append("<BrokeRoles>"+skywayObj.getBrokeRoles()+"</BrokeRoles>");
                soapRequestData.append("<BrokeRolesReason>"+skywayObj.getBrokeRolesReason()+"</BrokeRolesReason>");
                soapRequestData.append("<StandardPrice>"+skywayObj.getStandardPrice()+"</StandardPrice>");
                soapRequestData.append("<StandardCabin>"+skywayObj.getStandardCabin()+"</StandardCabin>");
                soapRequestData.append("<MainPnrNo>"+skywayObj.getMainPnrNo()+"</MainPnrNo>");
                soapRequestData.append("<OrinPnrNo>"+skywayObj.getOrinPnrNo()+"</OrinPnrNo>");
                soapRequestData.append("<FlightNo>"+skywayObj.getFlightNo()+"</FlightNo>");
                soapRequestData.append("<Carrier>"+skywayObj.getCarrier()+"</Carrier>");
                soapRequestData.append("<OrgCity>"+skywayObj.getOrgCity()+"</OrgCity>");
                soapRequestData.append("<DstCity>"+skywayObj.getDstCity()+"</DstCity>");
                soapRequestData.append("<Cabin>"+skywayObj.getCabin()+"</Cabin>");
                soapRequestData.append("<CabinDesc>"+skywayObj.getCabinDesc()+"</CabinDesc>");
                soapRequestData.append("<TakeOffDate>"+skywayObj.getTakeOffDate()+"</TakeOffDate>");
                soapRequestData.append("<TakeOffTime>"+skywayObj.getTakeOffTime()+"</TakeOffTime>");
                soapRequestData.append("<LandDate>"+skywayObj.getLandDate()+"</LandDate>");
                soapRequestData.append("<LandTime>"+skywayObj.getLandTime()+"</LandTime>");
                soapRequestData.append("<YPrice>"+skywayObj.getYPrice()+"</YPrice>");
                soapRequestData.append("<Price>"+skywayObj.getPrice()+"</Price>");
                soapRequestData.append("<FuelTax>"+skywayObj.getFuelTax()+"</FuelTax>");
                soapRequestData.append("<Tax>"+skywayObj.getTax()+"</Tax>");
                soapRequestData.append("<OtherPrice>"+skywayObj.getOtherPrice()+"</OtherPrice>");
                soapRequestData.append("<Remark>"+skywayObj.getRemark()+"</Remark>");
                soapRequestData.append("<OrgTeminal>"+skywayObj.getOrgTeminal()+"</OrgTeminal>");
                soapRequestData.append("<DstTeminal>"+skywayObj.getDstTeminal()+"</DstTeminal>");
                soapRequestData.append("<ReasonCodeID>"+skywayObj.getReasonCodeID()+"</ReasonCodeID>");
                soapRequestData.append("<ReasonCode>"+skywayObj.getReasonCode()+"</ReasonCode>");
                soapRequestData.append("<ReasonCodeDesc>"+skywayObj.getReasonCodeDesc()+"</ReasonCodeDesc>");
                soapRequestData.append("<LowestPrice>"+skywayObj.getLowestPrice()+"</LowestPrice>");
                soapRequestData.append("<LowestCabin>"+skywayObj.getLowestCabin()+"</LowestCabin>");
                soapRequestData.append("<LowestCabinDesc>"+skywayObj.getLowestCabinDesc()+"</LowestCabinDesc>");
                soapRequestData.append("<RerNotes>"+skywayObj.getRerNotes()+"</RerNotes>");
                soapRequestData.append("<RefNotes>"+skywayObj.getRefNotes()+"</RefNotes>");
                soapRequestData.append("<ERerNotes>"+skywayObj.getEnRerNotes()+"</ERerNotes>");
                soapRequestData.append("<ERefNotes>"+skywayObj.getEnRefNotes()+"</ERefNotes>");
                soapRequestData.append("<EndNotes>"+skywayObj.getEndNotes()+"</EndNotes>");

                soapRequestData.append("<AgreementCode>"+skywayObj.getAgreementCode()+"</AgreementCode>");
                soapRequestData.append("<AgreementID>"+skywayObj.getAgreementID()+"</AgreementID>");
                soapRequestData.append("<DisCountCode>"+skywayObj.getDisCountCode()+"</DisCountCode>");

                soapRequestData.append("<CarrierFlight>"+skywayObj.getCarrierFlight()+"</CarrierFlight>");
                soapRequestData.append("<IsCodeShare>"+skywayObj.isIsCodeShare()+"</IsCodeShare>");
                soapRequestData.append("<PlaneStyle>"+skywayObj.getPlaneStyle()+"</PlaneStyle>");

                soapRequestData.append("</B_SkyWayDO>");
            }
            soapRequestData.append("</SkyWay>");
        }
        soapRequestData.append("<DeletedSkyWay />");

        soapRequestData.append("<LowestHint>"+order.getLowestHint()+"</LowestHint>");
        soapRequestData.append("<ObeyRole>"+order.getObeyRole()+"</ObeyRole>");
        soapRequestData.append("<Tourists>"+order.getTourists()+"</Tourists>");
        soapRequestData.append("<FlightNos>"+order.getFlightNos()+"</FlightNos>");
        soapRequestData.append("<Skyways>"+order.getSkyways()+"</Skyways>");
    }
}
