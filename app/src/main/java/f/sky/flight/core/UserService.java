package f.sky.flight.core;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import f.sky.flight.model.B_TouristDOObj;
import f.sky.flight.model.LoginRegResult;
import f.sky.flight.model.PsgOperaResult;
import f.sky.flight.model.QueryPsgResult;
import f.sky.flight.model.UserDeptObj;
import f.sky.flight.model.UserRoleObj;
import f.sky.flight.model.UserServerObj;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class UserService extends WebServUtil {

    /**
     * 常旅客信息修改
     * @param UserID
     * @param Password
     * @param parameter
     * @param TouristID
     * @param userID
     * @param TouristName
     * @param MobileNo
     * @param Address
     * @param IDType
     * @param IDNumber
     * @param ClientID
     * @param nickname
     * @return
     * @throws Exception
     */
    public static PsgOperaResult psgUpdate(String UserID, String Password,
                                           String parameter, int TouristID, int userID, String TouristName, String MobileNo, String Address, int IDType, String IDNumber, int ClientID, String nickname)
            throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("TouristID", TouristID);//add:0 modify:值
        paramMap.put("UserID", userID);//loginRegResult.serverUserObj.getId();
        paramMap.put("TouristName", TouristName);
        paramMap.put("MobileNo", MobileNo);
        paramMap.put("Address", Address);
        paramMap.put("IDType", IDType);
        paramMap.put("IDNumber", IDNumber);

        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildPsgUpdateRequestData(UserID, Password, parameter,
                paramMap, ClientID, nickname).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<PsgUpdateResult>");
            int endIndex = response.lastIndexOf("</PsgUpdateResult>");
            response = response.substring(index, endIndex
                    + "</PsgUpdateResult>".length());
            return getPsgOperaObject(response, false);
        }
        return null;
    }

    private static String buildPsgUpdateRequestData(String userId,
                                                    String password, String patameter, Map<String, Object> patameterMap, int ClientID, String nickname) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.PSG_UPDATE + " xmlns=\""
                + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + userId + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("<Patameter>" + patameter + "</Patameter>");
        Set<Map.Entry<String, Object>> entrySet = patameterMap.entrySet();
        soapRequestData.append("<Patameter1>");
        for (Map.Entry<String, Object> entry : entrySet) {
            soapRequestData.append("<" + entry.getKey() + ">"
                    + entry.getValue() + "</" + entry.getKey() + ">");
        }
        {
            soapRequestData.append("<OrderID>").append(Constants.ZERO).append("</OrderID>");
            soapRequestData.append("<Hits>").append(Constants.ZERO).append("</Hits>");
            soapRequestData.append("<TouristType>").append(WebServUtil.TOURIST_TYPE).append("</TouristType>");
            soapRequestData.append("<NationalCode>").append(Constants.EMPTY_STRING).append("</NationalCode>");
            soapRequestData.append("<PassengerID>").append(Constants.ZERO).append("</PassengerID>");
            soapRequestData.append("<FreeFlag>").append(Constants.ZERO).append("</FreeFlag>");
            soapRequestData.append("<Price>").append(Constants.ZERO).append("</Price>");
            soapRequestData.append("<FuelTax>").append(Constants.ZERO).append("</FuelTax>");
            soapRequestData.append("<Tax>").append(Constants.ZERO).append("</Tax>");
            soapRequestData.append("<OtherPrice>").append(Constants.ZERO).append("</OtherPrice>");
            soapRequestData.append("<TktNos>").append(Constants.EMPTY_STRING).append("</TktNos>");
            soapRequestData.append("<Remark>").append(Constants.EMPTY_STRING).append("</Remark>");
            soapRequestData.append("<Field1>").append(ClientID).append("</Field1>");
            soapRequestData.append("<Field2>").append(nickname).append("</Field2>");
            soapRequestData.append("<Field3>").append(Constants.EMPTY_STRING).append("</Field3>");
            soapRequestData.append("<Birthday>").append(Constants.EMPTY_STRING).append("</Birthday>");
            soapRequestData.append("<Gender>").append(Constants.ZERO).append("</Gender>");
            soapRequestData.append("<NationalityName>").append(Constants.EMPTY_STRING).append("</NationalityName>");
            soapRequestData.append("<CardValid>").append(Constants.EMPTY_STRING).append("</CardValid>");
            soapRequestData.append("<InsuranceAmount>").append(Constants.ZERO).append("</InsuranceAmount>");
            soapRequestData.append("<Status>").append(WebServUtil.TOURIST_STATUS).append("</Status>");
        }
        soapRequestData.append("</Patameter1>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.PSG_UPDATE + ">");
        return buildSoapFooter(soapRequestData).toString();
    }

    /**
     * 用户详细信息修改
     * @param UserID
     * @param Password
     * @param UserName
     * @param RealName
     * @param MobileNo
     * @param MailAddress
     * @param IDType
     * @param IDNumber
     * @return
     * @throws Exception
     */
    public static LoginRegResult UserUpdateInfo(String UserID, String Password,
                                                String UserName, String RealName, String MobileNo, String MailAddress, int IDType, String IDNumber) throws Exception {
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);
        byte[] b = buildUserUpdateInfoRequestData(UserID, Password, UserName, RealName, MobileNo, MailAddress, IDType, IDNumber)
                .getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<UserUpdateResult>");
            int endIndex = response.lastIndexOf("</UserUpdateResult>");
            response = response.substring(index, endIndex
                    + "</UserUpdateResult>".length());
            return getLoginRegObject(response);
        }
        return null;
    }

    private static String buildUserUpdateInfoRequestData(String userId,
                                                         String password, String Patameter, String Patameter1, String Patameter2, String Patameter3, int Patameter4, String Patameter5) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.USER_UPDATE + " xmlns=\""
                + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + userId + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("<Patameter>" + Patameter + "</Patameter>");
        soapRequestData.append("<Patameter1>" + Patameter1 + "</Patameter1>");
        soapRequestData.append("<Patameter2>" + Patameter2 + "</Patameter2>");
        soapRequestData.append("<Patameter3>" + Patameter3 + "</Patameter3>");
        soapRequestData.append("<Patameter4>" + Patameter4 + "</Patameter4>");
        soapRequestData.append("<Patameter5>" + Patameter5 + "</Patameter5>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.USER_UPDATE + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
    /**
     * 用户密码修改
     * @param UserID
     * @param Password
     * @param UserName
     * @param userPwd
     * @return
     * @throws Exception
     */
    public static LoginRegResult UserChangePwd(String UserID, String Password,
                                               String UserName, String userPwd) throws Exception {
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);
        byte[] b = buildUserChangePwdRequestData(UserID, Password, UserName, userPwd)
                .getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<UserChangePasswordResult>");
            int endIndex = response.lastIndexOf("</UserChangePasswordResult>");
            response = response.substring(index, endIndex
                    + "</UserChangePasswordResult>".length());
            return getLoginRegObject(response);
        }
        return null;
    }
    private static String buildUserChangePwdRequestData(String userId,
                                                        String password, String patameter, String patameter1) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.USER_CHANGE_PASSWORD + " xmlns=\""
                + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + userId + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("<Patameter>" + patameter + "</Patameter>");
        soapRequestData.append("<Patameter1>" + patameter1 + "</Patameter1>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.USER_CHANGE_PASSWORD + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
    /**
     * 用户登录请求
     * @param UserID
     * @param Password
     * @param UserName
     * @param loginPwd
     * @return
     * @throws Exception
     *    public static final String ONLINE_WSDL = "http://tmc.sky-trip.com/DMBSService/ServiceForWeb/BizOutService.asmx?wsdl";
     */
//    public static final String ONLINE_WSDL = "http://tmc.sky-trip.com/DMBSService/ServiceForWeb/BizOutService.asmx?wsdl";
    public static LoginRegResult userLogin(String UserID, String Password,
                                           String UserName, String loginPwd) throws Exception {

        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);
        byte[] b = buildUserLoginRequestData(UserID, Password, UserName, loginPwd)
                .getBytes(ENCODE);
        Log.e("---登录请求参数----", "userLogin: "+ buildUserLoginRequestData(UserID, Password, UserName, loginPwd));
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        //设置请求内容
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            Log.e("登录后截取的数据-----", "userLogin: "+response );
            int index = response.indexOf("<" + WebServUtil.USER_LOGIN + "Result>");
            int endIndex = response.lastIndexOf("</" + WebServUtil.USER_LOGIN + "Result>");
            response = response.substring(index, endIndex+ ("</" + WebServUtil.USER_LOGIN + "Result>").length());
            return getLoginRegObject(response);
        }
        return null;
    }

    /**
     * 创建请求需要的参数
     * @param userId
     * @param password
     * @param patameter
     * @param loginPwd
     * @return
     */

    private static String buildUserLoginRequestData(String userId,
                                                    String password, String patameter, String loginPwd) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.USER_LOGIN + " xmlns=\""
                + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + userId + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("<Patameter>" + patameter + "</Patameter>");
        soapRequestData.append("<Patameter1>" + loginPwd + "</Patameter1>");
        soapRequestData.append("<Patameter2>" + WebServUtil.USER_LOGIN_VERSION + "</Patameter2>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.USER_LOGIN + ">");

        return buildSoapFooter(soapRequestData).toString();
    }

    /**
     * 用户登录时返回的数据并将其转化为Javabean
     * @param response
     * @return
     */
    private static LoginRegResult getLoginRegObject(String response) {
        try {
            LoginRegResult loginRegResult = new LoginRegResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                loginRegResult.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                loginRegResult.setMessage(doc.getRootElement().elementTextTrim("Message"));
                loginRegResult.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
            }
            Element resultObjectEle = (Element)doc.selectSingleNode("//ResultObject");

            if(null != resultObjectEle){
                UserServerObj userServerObj = new UserServerObj();
                {
                    userServerObj.setAddress(resultObjectEle.elementTextTrim("Address"));
                    userServerObj.setAllowMailMessage(parseInt(resultObjectEle.elementTextTrim("AllowMailMessage")));
                    userServerObj.setAllowMobileMessage(parseInt(resultObjectEle.elementTextTrim("allowMobileMessage")));
                    userServerObj.setDMBSUserID(parseInt(resultObjectEle.elementTextTrim("DMBSUserID")));//业务系统ID
                    userServerObj.setEffectDate(parseDate(resultObjectEle.elementTextTrim("EffectDate")));
                    userServerObj.setId(parseInt(resultObjectEle.elementTextTrim("ID")));//??
                    userServerObj.setIDNumber(resultObjectEle.elementTextTrim("IDNumber"));
                    userServerObj.setIDType(parseInt(resultObjectEle.elementTextTrim("IDType")));
                    userServerObj.setMail(resultObjectEle.elementTextTrim("Mail"));
                    userServerObj.setMobile(resultObjectEle.elementTextTrim("Mobile"));
                    userServerObj.setPassword(resultObjectEle.elementTextTrim("Password"));
                    userServerObj.setPhone(resultObjectEle.elementTextTrim("Phone"));
                    userServerObj.setProject(resultObjectEle.elementTextTrim("Project"));
                    userServerObj.setRealName(resultObjectEle.elementTextTrim("RealName"));
                    userServerObj.setThirdDiscount(resultObjectEle.elementTextTrim("ThirdDiscount"));
                    userServerObj.setUserCode(resultObjectEle.elementTextTrim("UserCode"));//工号
                    userServerObj.setUserMinDiscount(parseInt(resultObjectEle.elementTextTrim("UserMinDiscount")));
                    userServerObj.setUserName(resultObjectEle.elementTextTrim("UserName"));
                    userServerObj.setZipCode(resultObjectEle.elementTextTrim("ZipCode"));//邮编
                    userServerObj.setIntro(resultObjectEle.elementTextTrim("Intro"));
                    userServerObj.setRegEncode(resultObjectEle.elementTextTrim("RegEncode"));
                    userServerObj.setUserDepID(parseInt(resultObjectEle.elementTextTrim("UserDepID")));//用户部门ID
                    userServerObj.setStatus(parseInt(resultObjectEle.elementTextTrim("Status")));
                    userServerObj.setLoginTimes(parseInt(resultObjectEle.elementTextTrim("LoginTimes")));
                    userServerObj.setRegType(parseInt(resultObjectEle.elementTextTrim("RegType")));
                    userServerObj.setAuditUser(parseInt(resultObjectEle.elementTextTrim("AuditUser")));
                    userServerObj.setSortIndex(parseInt(resultObjectEle.elementTextTrim("SortIndex")));
                    userServerObj.setPosition(parseInt(resultObjectEle.elementTextTrim("Position")));
                    userServerObj.setCreateDate(parseDate(resultObjectEle.elementTextTrim("CreateDate")));
                    userServerObj.setAuditDate(parseDate(resultObjectEle.elementTextTrim("AuditDate")));
                    userServerObj.setNick(resultObjectEle.elementTextTrim("Nick"));

                    Element ele = (Element)resultObjectEle.selectSingleNode("//Dep");//部门
                    if(null != ele){
                        userServerObj.setUserDeptObj(parseUserDeptObj(ele));
                    }
                    ele = (Element)resultObjectEle.selectSingleNode("//Org");//公司
                    if(null != ele){
                        userServerObj.setUserOrgObj(parseUserDeptObj(ele));
                    }
                    ele = (Element)resultObjectEle.selectSingleNode("//Role");//公司的协议信息
                    if(null != ele){
                        userServerObj.setUserRoleObj(parseUserRoleObj(ele));
                    }
                }
                loginRegResult.setUserServerObject(userServerObj);
            }
            return loginRegResult;
        } catch (Exception e) {
            System.out.println("-------数据解析异常------"+e.getMessage());
            return null;
        }
    }

    /**
     * 这里解析的是部门和公司信息，字段一样但是值不同
     * @param userDeptObjEle
     * @return
     */
    private static UserDeptObj parseUserDeptObj(Element userDeptObjEle){
        UserDeptObj userDeptObj = new UserDeptObj();
        /********部门信息********/
        userDeptObj.setCostCenter(userDeptObjEle.elementTextTrim("CostCenMailAddresster"));//默认成本中心
        userDeptObj.setMailAddress(userDeptObjEle.elementTextTrim(""));
        userDeptObj.setClientCode(userDeptObjEle.elementTextTrim("ClientCode"));
        userDeptObj.setDepEName(userDeptObjEle.elementTextTrim("DepEName"));
        userDeptObj.setServiceInfo(userDeptObjEle.elementTextTrim("ServiceInfo"));
        userDeptObj.setServiceEInfo(userDeptObjEle.elementTextTrim("ServiceEInfo"));
        userDeptObj.setDepName(userDeptObjEle.elementTextTrim("DepName"));
        userDeptObj.setDepFullName(userDeptObjEle.elementTextTrim("DepFullName"));
        userDeptObj.setAirLineDiscount(userDeptObjEle.elementTextTrim("AirLineDiscount"));
        userDeptObj.setIntro(userDeptObjEle.elementTextTrim("intro"));
        userDeptObj.setLinkAddress(userDeptObjEle.elementTextTrim("LinkAddress"));
        userDeptObj.setLinkPhone(userDeptObjEle.elementTextTrim("LinkPhone"));
        userDeptObj.setLinkMan(userDeptObjEle.elementTextTrim("LinkMan"));
        userDeptObj.setDepType(userDeptObjEle.elementTextTrim("DepType"));
        userDeptObj.setID(parseInt(userDeptObjEle.elementTextTrim("ID")));//？？
        userDeptObj.setAllowTicketDirect(parseInt(userDeptObjEle.elementTextTrim("AllowTicketDirect")));//允许直接出票
        userDeptObj.setForeDay(parseInt(userDeptObjEle.elementTextTrim("ForeDay")));//提前预定天数
        userDeptObj.setAllowPayOnline(parseInt(userDeptObjEle.elementTextTrim("AllowPayOnline")));
        userDeptObj.setLowestPriceDuration(parseInt(userDeptObjEle.elementTextTrim("LowestPriceDuration")));//显示最低票价
        userDeptObj.setClientID(parseInt(userDeptObjEle.elementTextTrim("ClientID")));
        userDeptObj.setStatus(parseInt(userDeptObjEle.elementTextTrim("Status")));
        userDeptObj.setParent(parseInt(userDeptObjEle.elementTextTrim("Parent")));
        userDeptObj.setSortIndex(parseInt(userDeptObjEle.elementTextTrim("SortIndex")));
        userDeptObj.setCorpID(parseInt(userDeptObjEle.elementTextTrim("CorpID")));//公司ID
        userDeptObj.setTmpUserID(parseInt(userDeptObjEle.elementTextTrim("tmpUserID")));

        Element orgRight = userDeptObjEle.element("OrgRights");
        Element orgRightName = userDeptObjEle.element("OrgRightsName");
        Element deliverAddressEle = userDeptObjEle.element("DeliverAddress");
        Element eDeliverAddressEle = userDeptObjEle.element("DeliverAddressE");
        List<String> deliverAddr = parseDeliverAddrs(deliverAddressEle);
        if(null != deliverAddr){
            userDeptObj.getDeliverAddress().addAll(deliverAddr);
        }
        List<String> deliverAddrE = parseDeliverAddrs(eDeliverAddressEle);
        if(null != deliverAddr){
            userDeptObj.getDeliverAddressE().addAll(deliverAddrE);
        }
        Map<String, String> configs = parseConfigMap(orgRight, orgRightName);
        if(null != configs){
            userDeptObj.getConfigsMap().putAll(configs);
        }
        return userDeptObj;
    }

    private static UserRoleObj parseUserRoleObj(Element userRoleObjEle){
        UserRoleObj userRoleObj = new UserRoleObj();
        userRoleObj.setID(parseInt(userRoleObjEle.elementTextTrim("ID")));
        userRoleObj.setRightCodes(userRoleObjEle.elementTextTrim("RightCodes"));//权限集合
        userRoleObj.setType(userRoleObjEle.elementTextTrim("Type"));//权限类型
        userRoleObj.setTitle(userRoleObjEle.elementTextTrim("Title"));//名称
        userRoleObj.setIntro(userRoleObjEle.elementTextTrim("Intro"));
        userRoleObj.setIDs(userRoleObjEle.elementTextTrim("IDs"));
        userRoleObj.setRights(userRoleObjEle.elementTextTrim("Rights"));
        userRoleObj.setSortIndex(parseInt(userRoleObjEle.elementTextTrim("SortIndex")));
        userRoleObj.setStatus(parseInt(userRoleObjEle.elementTextTrim("Status")));
        return userRoleObj;
    }

    @SuppressWarnings("unchecked")
    private static List<String> parseDeliverAddrs(
            Element deliverAddressEle) {
        List<Element> values = deliverAddressEle.elements();
        if(null == values || values.size() == 0){
            return null;
        }

        List<String> addrList = new ArrayList<String>();
        for(Element addrEach: values){
            addrList.add(addrEach.getText());
        }
        return addrList;
    }
    @SuppressWarnings("unchecked")
    private static Map<String, String> parseConfigMap(
            Element orgRight, Element orgRightName) {
        List<Element> values = orgRight.elements();
        List<Element> keys = orgRightName.elements();
        if(null == values || values.size() == 0){
            return null;
        }
        Map<String, String> configM = new HashMap<String, String>();
        int configCount = values.size();
        for(int i=0; i<configCount; i++){
            configM.put(keys.get(i).getText(), values.get(i).getText());
        }
        return configM;
    }


    public static QueryPsgResult psgQuery(String userID, String password,
                                          String UserName, String KeyWord) throws Exception {
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildPsgQueryRequestData(userID, password, UserName,
                KeyWord).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<PsgQueryResult>");
            int endIndex = response.lastIndexOf("</PsgQueryResult>");
            response = response.substring(index,
                    endIndex + "</PsgQueryResult>".length());
            return getPsgQueryObject(response);
        }
        return null;
    }
    @SuppressWarnings("unchecked")
    private static QueryPsgResult getPsgQueryObject(String response) {
        try {
            QueryPsgResult psgQueryResult = new QueryPsgResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                psgQueryResult.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                psgQueryResult.setMessage(doc.getRootElement().elementTextTrim("Message"));
                psgQueryResult.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
            }
            List<Element> resultObjectEles = (List<Element>)doc.selectNodes("//B_TouristDO");
//			B_TouristDOObj b_TouristDO = null;
            for(Element e: resultObjectEles){
//				b_TouristDO = new B_TouristDOObj();
//				b_TouristDO.setTouristID(parseInt(e.elementTextTrim("TouristID")));
//				b_TouristDO.setOrderID(parseInt(e.elementTextTrim("OrderID")));
//				b_TouristDO.setHits(parseInt(e.elementTextTrim("Hits")));
//				b_TouristDO.setUserID(parseInt(e.elementTextTrim("UserID")));
//				b_TouristDO.setTouristName(e.elementTextTrim("TouristName"));
//				b_TouristDO.setTouristType(parseInt(e.elementTextTrim("TouristType")));
//				b_TouristDO.setPassengerID(parseInt(e.elementTextTrim("PassengerID")));
//				b_TouristDO.setIDType(parseInt(e.elementTextTrim("IDType")));
//				b_TouristDO.setFreeFlag(parseInt(e.elementTextTrim("FreeFlag")));
//				b_TouristDO.setGender(parseInt(e.elementTextTrim("Gender")));
//				b_TouristDO.setStatus(parseInt(e.elementTextTrim("Status")));
//				b_TouristDO.setMobileNo(e.elementTextTrim("MobileNo"));
//				b_TouristDO.setAddress(e.elementTextTrim("Address"));
//				b_TouristDO.setNationalCode(e.elementTextTrim("NationalCode"));
//				b_TouristDO.setIDNumber(e.elementTextTrim("IDNumber"));
//				b_TouristDO.setPrice(parseDouble(e.elementTextTrim("Price")));
//				b_TouristDO.setFuelTax(parseDouble(e.elementTextTrim("FuelTax")));
//				b_TouristDO.setTax(parseDouble(e.elementTextTrim("Tax")));
//				b_TouristDO.setOtherPrice(parseDouble(e.elementTextTrim("OtherPrice")));
//				b_TouristDO.setInsuranceAmount(parseDouble(e.elementTextTrim("InsuranceAmount")));
//				b_TouristDO.setTktNos(e.elementTextTrim("TktNos"));
//				b_TouristDO.setRemark(e.elementTextTrim("Remark"));
//				b_TouristDO.setField1(e.elementTextTrim("Field1"));
//				b_TouristDO.setField2(e.elementTextTrim("Field2"));
//				b_TouristDO.setField3(e.elementTextTrim("Field3"));
//				b_TouristDO.setBirthday(e.elementTextTrim("Birthday"));
//				b_TouristDO.setNationalityName(e.elementTextTrim("NationalityName"));
//				b_TouristDO.setCardValid(e.elementTextTrim("CardValid"));
                psgQueryResult.addOne(parseTouristEle(e));
            }
            return psgQueryResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static PsgOperaResult psgDelete(String userID, String password,
                                           String UserName, int TouristId) throws Exception {
        PostMethod postMethod = new PostMethod(WebServUtil.WSDL);

        byte[] b = buildPsgDeleteRequestData(userID, password, UserName,
                TouristId).getBytes(ENCODE);
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity re = new InputStreamRequestEntity(is, b.length,
                CONTENT_TYPE);
        postMethod.setRequestEntity(re);
        HttpClient httpClient = new HttpClient();
        int statusCode = httpClient.executeMethod(postMethod);
        if (statusCode == WebServUtil.HTTP_STATUS_OK) {
            String response = postMethod.getResponseBodyAsString();
            int index = response.indexOf("<PsgDeleteResult>");
            int endIndex = response.lastIndexOf("</PsgDeleteResult>");
            response = response.substring(index, endIndex
                    + "</PsgDeleteResult>".length());
            return getPsgOperaObject(response, true);
        }
        return null;
    }

    private static PsgOperaResult getPsgOperaObject(String response, boolean isDelete) {
        try {
            PsgOperaResult psgOperaResult = new PsgOperaResult();
            Document doc = new SAXReader().read(new ByteArrayInputStream(response.getBytes(ENCODE)));
            {
                psgOperaResult.setSuccess(parseBoolean(doc.getRootElement().elementTextTrim("Success")));
                psgOperaResult.setMessage(doc.getRootElement().elementTextTrim("Message"));
                psgOperaResult.setElapsedMilliseconds(parseLong(doc.getRootElement().elementTextTrim("ElapsedMilliseconds")));
            }
            if(isDelete){
                psgOperaResult.setResultObject(parseBoolean(doc.getRootElement().elementTextTrim("ResultObject")));
            } else {
                Element touristEle = (Element)doc.selectSingleNode("//ResultObject");
                psgOperaResult.setTouristDOObj(parseTouristEle(touristEle));
            }
            return psgOperaResult;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static B_TouristDOObj parseTouristEle(Element e) {
        B_TouristDOObj b_TouristDO = new B_TouristDOObj();
        b_TouristDO.setTouristID(parseInt(e.elementTextTrim("TouristID")));
        b_TouristDO.setOrderID(parseInt(e.elementTextTrim("OrderID")));
        b_TouristDO.setHits(parseInt(e.elementTextTrim("Hits")));
        b_TouristDO.setUserID(parseInt(e.elementTextTrim("UserID")));
        b_TouristDO.setTouristName(e.elementTextTrim("TouristName"));
        b_TouristDO.setTouristType(parseInt(e.elementTextTrim("TouristType")));
        b_TouristDO.setPassengerID(parseInt(e.elementTextTrim("PassengerID")));
        b_TouristDO.setIDType(parseInt(e.elementTextTrim("IDType")));
        b_TouristDO.setFreeFlag(parseInt(e.elementTextTrim("FreeFlag")));
        b_TouristDO.setGender(parseInt(e.elementTextTrim("Gender")));
        b_TouristDO.setStatus(parseInt(e.elementTextTrim("Status")));
        b_TouristDO.setMobileNo(e.elementTextTrim("MobileNo"));
        b_TouristDO.setAddress(e.elementTextTrim("Address"));
        b_TouristDO.setNationalCode(e.elementTextTrim("NationalCode"));
        b_TouristDO.setIDNumber(e.elementTextTrim("IDNumber"));
        b_TouristDO.setPrice(parseDouble(e.elementTextTrim("Price")));
        b_TouristDO.setFuelTax(parseDouble(e.elementTextTrim("FuelTax")));
        b_TouristDO.setTax(parseDouble(e.elementTextTrim("Tax")));
        b_TouristDO.setOtherPrice(parseDouble(e.elementTextTrim("OtherPrice")));
        b_TouristDO.setInsuranceAmount(parseDouble(e.elementTextTrim("InsuranceAmount")));
        b_TouristDO.setTktNos(e.elementTextTrim("TktNos"));
        b_TouristDO.setRemark(e.elementTextTrim("Remark"));
        b_TouristDO.setField1(e.elementTextTrim("Field1"));
        b_TouristDO.setField2(e.elementTextTrim("Field2"));
        b_TouristDO.setField3(e.elementTextTrim("Field3"));
        b_TouristDO.setBirthday(e.elementTextTrim("Birthday"));
        b_TouristDO.setNationalityName(e.elementTextTrim("NationalityName"));
        b_TouristDO.setCardValid(e.elementTextTrim("CardValid"));
        return b_TouristDO;
    }
    private static String buildPsgQueryRequestData(String userId,
                                                   String password, String patameter, String patameter1) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.PSG_QUERY + " xmlns=\""
                + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + userId + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("<Patameter>" + patameter + "</Patameter>");
        soapRequestData.append("<Patameter1>" + patameter1 + "</Patameter1>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.PSG_QUERY + ">");
        return buildSoapFooter(soapRequestData).toString();
    }

    private static String buildPsgDeleteRequestData(String userId,
                                                    String password, String patameter, int patameter1) {
        StringBuilder soapRequestData = buildSoapHeader();
        soapRequestData.append("<" + WebServUtil.PSG_DELETE + " xmlns=\""
                + WebServUtil.TARGET_NAME_SPACE + "\">");
        soapRequestData.append("<sap>");
        soapRequestData.append("<UserID>" + userId + "</UserID>");
        soapRequestData.append("<Password>" + password + "</Password>");
        soapRequestData.append("<Patameter>" + patameter + "</Patameter>");
        soapRequestData.append("<Patameter1>" + patameter1 + "</Patameter1>");
        soapRequestData.append("</sap>");
        soapRequestData.append("</" + WebServUtil.PSG_DELETE + ">");
        return buildSoapFooter(soapRequestData).toString();
    }
}
