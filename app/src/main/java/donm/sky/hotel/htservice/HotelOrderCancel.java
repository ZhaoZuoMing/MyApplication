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

import donm.sky.hotel.model.HotelOrderCancelResult;
import f.sky.flight.core.WebServUtil;

/**
 * Created by Administrator on 2017/5/15/015.
 * 取消酒店订单
 * Elong接口中订单取消,需在调用UpdateOrder方法前调用此方法,若此方法调用不成功,则本地订单不可取消.
 */

public class HotelOrderCancel extends WebServUtil {

    public static HotelOrderCancelResult hotelOrderCancel(String OrderId, String CancelCode){
        PostMethod postMethod = new PostMethod(WebServUtil.HOTEL_TEST_URL);
        try{
             byte b[] = builderHotelOrderCancel(OrderId,CancelCode).getBytes(ENCODE);
            InputStream is = new ByteArrayInputStream(b, 0, b.length);
            RequestEntity re = new InputStreamRequestEntity(is, b.length,  CONTENT_TYPE);

            postMethod.setRequestEntity(re);
            HttpClient httpClient = new HttpClient();
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == WebServUtil.HTTP_STATUS_OK) {
                String response = postMethod.getResponseBodyAsString();

                Log.e("HotelOrderCancel:  ", "------酒店取消-： "+response);
                return parseHotelOrderCancelResult(response) ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
    /*解析取消酒店返回的数据*/
    private static HotelOrderCancelResult parseHotelOrderCancelResult(String response) {
        HotelOrderCancelResult orderCancelResult = new HotelOrderCancelResult();
        Element root;
        Document doc;
        SAXReader reader = new SAXReader();
        try {

            doc = reader.read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            root = doc.getRootElement();
            Element obj = root.element("Body")
                    .element("HotelOrderCancelResponse")
                    .element("HotelOrderCancelResult");
            orderCancelResult.setMessage(obj.elementTextTrim("Message"));
            orderCancelResult.setElapsedMilliseconds(parseInt(obj.elementTextTrim("ElapsedMilliseconds")));
            orderCancelResult.setSuccess(parseBoolean(obj.elementTextTrim("Success")));
            //----NO end

        }catch (Exception e){
            e.printStackTrace();
        }
        return orderCancelResult;
    }

    private static  String builderHotelOrderCancel(String OrderId,String CancelCode){
        StringBuilder  soapRequestData = buildSoapHeader();
        soapRequestData.append("<"+WebServUtil.HOTEL_ORDER_CANCEL+" xmlns=\""+ WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + WebServUtil.HBE_USER_NAME + "</UserID>");
        soapRequestData.append("<Password>" +WebServUtil.HBE_USER_PAW+ "</Password>");
        soapRequestData.append("<Patameter>");
        soapRequestData.append("<Action>" +"Cancel"+ "</Action>");
          soapRequestData.append("<CancelCondition>");
        soapRequestData.append("<OrderId>" +OrderId+ "</OrderId>");//订单号,HOrder中的ExternalNo
        soapRequestData.append("<CancelCode>" +CancelCode+ "</CancelCode>");//??
        soapRequestData.append("<Reason>" +"酒店订单取消"+ "</Reason>");
          soapRequestData.append("</CancelCondition>");
        soapRequestData.append("</Patameter>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.HOTEL_ORDER_CANCEL + ">");
        return buildSoapFooter(soapRequestData).toString();
    }


}
