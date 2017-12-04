package f.sky.flight.core;

import com.mytables.MyApp;
import com.myuntils.DateUtil;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/23/023.
 */

public class WebServUtil {


    /****
     * 火车票查询接口
     */
    public static String TRAIN_CITY_LIST = "http://115.29.34.205/api/train/stations";
    public static String TRAIN_KEY ="KXQnnJh7LcKlXX5tIqRr1WAMAA8Zruk9";
    public static String TRAIN_SEARCH_METHED ="train_query";
    public static String DOMEI_UERNAME="dongmei_test";
    public static String TRAIN_SEARCH_PATH = "http://searchtrain.hangtian123.net/trainSearch";

    /**
     * 酒店查询接口
     */
    public static String HOTEL_TEST_URL = "http://test.sky-trip.com:8999/hbeservice/hbeservice.asmx";
    public static String HOTEL_RELAY_API = "http://180.166.153.150/hbeservice/hbeservice.asmx";
    public static  String HOTEL_PAY_NOTIFY = "http://biz.sky-trip.com/EHotel/HAlipayBGRtn.aspx";
    public static final String HOTEL_SERVICE = MyApp.hotelDebug ? HOTEL_TEST_URL : HOTEL_RELAY_API;


    public static String HOTEL_RELAY_API_1 = "http://172.18.24.247/hbeservice/hbeservice.asmx";

    public static final String INIT_HOTEL_DATA = "InitalData";
    public static final String SEARCH_HOTEL_LIST = "HotelListSearch";
    public static final  String HOTEL_DETAIL_SEARCH = "HotelDetailSearch";
    public static final String HOTEL_LIST_INFOS = "HotelInfoSearch";
    public static final  String COMMIT_HOTEL_DATA = "CommitOrder";
    public static final String HOTEL_DATA_VALIDATA= "HotelDataValidate";
    public static final String QUER_HOTEL_ORDER = "QueryOrder";
    public static  final  String HOTEL_ORDER_CANCEL = "HotelOrderCancel";

    public static final String HBE_USER_NAME = "bizuser";
    public static final String HBE_USER_PAW = "bizuser";

    public static String TRAIN_ORDER_URL = "http://trainorder.test.hangtian123.net/cn_interface/tcTrain";
    public  static  String TRAIN_ORDER_METHED ="train_order";

    private static String XML_HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    private static String SOAP_ENVELOPE_BEGIN = "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
    private static String SOAP_BODY_BEGIN = "<soap12:Body>";
    private static String SOAP_BODY_END = "</soap12:Body>";
    private static String SOAP_ENVELOPE_END = "</soap12:Envelope>";

    protected static final int HTTP_STATUS_OK = 200;
    protected static String CONTENT_TYPE = "application/soap+xml; charset=utf-8";
    protected static String ENCODE = "utf-8";

    /**
     * 酒店头部xml
     * @return
     */
    private static String MY_SOAP_ENVELOPE_BEGIN = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope\">";
    private static String MY_SOAP_BODY_BEGIN = "<soap:Body>";
    private static String MY_SOAP_BODY_END = "</soap:Body>";
    private static String MY_SOAP_ENVELOPE_END = "</soap:Envelope>";

    public  static StringBuilder builderMyHead(){
        StringBuilder sb = new StringBuilder();
        return  sb.append(XML_HEADER).append(MY_SOAP_ENVELOPE_BEGIN).append(MY_SOAP_BODY_BEGIN);
    }
   public  static  String builderMyEnd(StringBuilder sb){
       return sb.append(MY_SOAP_BODY_END).append(MY_SOAP_ENVELOPE_END).toString();
   }
    /**
     * 构建xml数据头部的内容
     * @return
     */
    public static StringBuilder buildSoapHeader(){
        StringBuilder sb = new StringBuilder();
        return sb.append(XML_HEADER).append(SOAP_ENVELOPE_BEGIN).append(SOAP_BODY_BEGIN);
    }
    /**
     * 构建相关XML数据的底部内容
     * @param sb
     * @return
     */
    public static String buildSoapFooter(StringBuilder sb){
        return sb.append(SOAP_BODY_END).append(SOAP_ENVELOPE_END).toString();
    }

    public static final String USER_ID = "appuser";
    public static final String PASSWORD = "app";
    public static final String USER_NAME = "test90";
    public static final String QUERY_ALL_AIRLINE = "ALL";
    public static final String TARGET_NAME_SPACE = "http://tempuri.org/";

    public static final String TEST_ALIPAY_NOTIFY_URL = "http://211.144.105.107:9001/maplipay/Notify.aspx";
    public static final String ONLINE_ALIPAY_NOTIFY_URL = "http://biz.sky-trip.com/MAliPay/Notify.aspx";

    public static final String ALIPAY_NOTIFY_URL = MyApp.debug ? TEST_ALIPAY_NOTIFY_URL : ONLINE_ALIPAY_NOTIFY_URL;
    //测试接口
    public static final String TEST_WSDL = "http://test.sky-trip.com:8999/DMBSService/ServiceForWeb/BizOutService.asmx?wsdl";
    //测试账号：ADmin zhangjun

    //机票查询接口
    public static final String ONLINE_WSDL = "http://tmc.sky-trip.com/DMBSService/ServiceForWeb/BizOutService.asmx?wsdl";
    //判断是否是测试还是正式接口
    public static final String WSDL = MyApp.debug ? TEST_WSDL : ONLINE_WSDL;
    //用户登录的系统版本号
    public static final String USER_LOGIN_VERSION = "2.6";

    public static final String USER_LOGIN  = "UserLogin_Android";//登录
    public static final String USER_LOGIN_AND_REG  = "UserLoginAndReg";//注册登录
    public static final String USER_UPDATE   = "UserUpdate";
    public static final String DATA_VERSION_QUERY  = "DataVersionQuery";
    public static final String GET_REFUND_CHANGE_INFO_ALL   = "GetRefundChangeInfoAll";
    public static final String USER_CHANGE_PASSWORD  = "UserChangePassword";
    public static final String GET_FLIGHT_ROLE   = "GetFlightRole";

    public static final String QUERY_TKT = "QueryTkt";//机票查询接口
    public static final String GET_REFUND_CHANGE_INFO = "GetRefundChangeInfo";//退改签信息查询
    public static final String QUERY_AIRLINES  = "QueryAirlines ";//航空公司信息
    public static final String QUERY_AIRPORT = "QueryAirport";//机场信息
    public static final String QUERY_AUDIT_MAIL  = "QueryAuditMail";//最近一次审核邮箱
    public static final String QUERY_PLANE_STYLE = "QueryPlaneStyle";//机型信息
    public static final String REASON_CODE_QUERY = "ReasonCodeQuery";//ReasonCode

    public static final String APPLY_STATUS_CHANGE = "ApplyStatusChange";//申请单状态修改
    public static final String ORDER_CASH_STATUS_CHANGE = "OrderCashStatusChange";//订单支付状态更改 1已支付 0 未支付
    public static final String ORDER_STATUS_CHANGE = "OrderStatusChange";//订单状态更改 0:保存 5:废除 10:未审 15:在线支付未支付 20:已出票
    public static final String ORDER_QUERY = "Order_Query";//查询订单列表
    public static final String ORDER_QUERYBYID = "Order_QueryByID";//查询订单详情
    public static final String ORDER_CAN_PAY = "OrderCanPay";
    public static final String CHECK_REQUEST_NOTE = "CheckRequestNote";

    public static final String PSG_DELETE = "PsgDelete";//删除常旅客
    public static final String PSG_QUERY = "PsgQuery";//常旅客
    public static final String PSG_UPDATE = "PsgUpdate";//修改/新增常旅客

    public static final String APPLY_QUERY = "Apply_Query";//我要审核的申请单
    public static final String APPLY_QUERYBYID = "Apply_QueryByID";//我要审核的申请单详情
    public static final String BIZ_BOOK_TKT = "BizBookTkt";//出票接口
    public static final String BIZ_SEAL_SEAT = "BizSealSeat";//机票预订接口
    public static final String COST_CENTER_QUERY = "CostCenterQuery";//成本中心
    public static final String GET_CLIENT_PRICE = "GetClientPrice";//算两方协议价

    public static final String TICKET_URGENT = "TicketUrgent ";//紧急出票
    public static final String RESEND_AUDIT_MAIL = "ReSendAuditMail";//重发审核邮件

    public static final String MAIL_SEND_APPLY_SUCC = "MailSendApplySucc";//发订单生成通知到申请人信箱
    public static final String MAIL_SEND_APPLY_SUCCE = "MailSendApplySuccE";//发订单生成通知到申请人信箱E
    public static final String MAIL_SEND_AUDIT_MAIL = "MailSendAuditMail";//发通知到审核信箱
    public static final String MAIL_SEND_AUDIT_MAILE = "MailSendAuditMailE";//发通知到审核信箱E
    public static final String MAIL_SEND_DENY_MAIL = "MailSendDenyMail";//发未通过审核通知到申请人信箱
    public static final String MAIL_SEND_DENY_MAILE = "MailSendDenyMailE";//发未通过审核通知到申请人信箱E
    public static final String MAIL_SEND_SUCC_INFO = "MailSendSuccInfo";//发已通过审核通知到申请人信箱
    public static final String MAIL_SEND_SUCC_INFOE = "MailSendSuccInfoE";//发已通过审核通知到申请人信箱E
    public static final String MAIL_SEND_SYSTEM = "MailSendSystem";//发出票通知到系统邮箱E

    //申请单状态
    public static final int APPLY_UNAUDIT_STATUS = 0;//未审
    public static final int APPLY_AUDITED_STATUS = 1;//已审
    public static final int APPLY_NOT_NEED_AUDIT_STATUS = 2;//不需要审
    public static final int APPLY_EMGENCY_TICKET_STATUS = 3;//紧急出票
    public static final int APPLY_OUTDATE_STATUS = 97;//审核拒绝
    public static final int APPLY_CANCEL_STATUS = 98;//审核拒绝
    public static final int APPLY_REJECT_STATUS = 99;//取消
    public static final String[] APPLY_STATUS_DESC = {"未审", "已审", "不需要审", "拒绝"};

    //0 已保存  5 已废  10 [申请单 ]  15 订单未支付  20 已出票(已支付)
    public static final int ORDER_SAVED_STATUS = 0;
    public static final int ORDER_DELETED_STATUS = 5;
    public static final int ORDER_APPLYED_STATUS = 10;
    public static final int ORDER_UNPAYED_STATUS = 15;
    public static final int ORDER_PAYED_STATUS = 20;
    public static final String[] ORDER_STATUS_DESC = {"未审核", "已废", "申请单", "未支付", "已出票"};
    public static final String[] ORDER_DELETE_STATUS_DESC = {"超期取消", "审核拒绝", "取消"};
    public static final String[] ORDER_APPLYED_STATUS_DESC = {"未审核", "已审核", "未出票", "紧急出票"};

    //下订单的一些固定参数
    public static final String OFFICE_CODE = "SHA396";
    public static final int ORDER_SOURCE = 3;
    public static final int SELF_TAKE = 1;
    public static final int CATEGORY_ID = 1;
    public static final int TRIP_STATUS = 9;
    public static final int SKYWAY_STATUS = 1;
    public static final int SKYWAY_TYPE = 3;

    //关于旅客的固定信息
    public static final int TOURIST_STATUS = 0;
    public static final int TOURIST_TYPE = 1;
    public static final String NATIONAL_CODE = "CN";
    public static final String NATIONALITY_NAME = "中国";

    //0 申请 1 在线支票  2通知代理人出票 3 直接出票
    public static final int PAY_TYPE_APPLY = 0;
    public static final int PAY_TYPE_ONLINE = 1;
    public static final int PAY_TYPE_NOTICE_PROXY = 2;
    public static final int PAY_TYPE_NOTICE_DIRECT = 3;

    //0 未支付  1 已支付
    public static final int PAY_UNPAYED_STATUS = 0;
    public static final int PAY_PAYED_STATUS = 1;

    //0 身份证 1护照 2 军官证 3 其它
    public static final int IDENTITY_TYPE = 0;
    public static final int IDENTITY_TYPE_PASSPORT = 1;
    public static final int IIDENTITY_TYPE_OFFICER = 2;
    public static final int IDENTITY_TYPE_OTHER = 3;
    public static final String[] ID_TYPES = {"身份证", "护照", "军官证", "其它"};

    public static final String[] IS_RULES = {"政策无关", "符合政策"};
    //iOS,Android
    public static final String SYSTEM_IOS = "iOS";
    public static final String SYSTEM_ANDROID = "Android";

    public static final String HOT_CITY_CODE = "PVG SHA PEK CAN SZX CTU HGH WUH XIY CKG TAO CSX NKG XMN KMG DLC TSN CGO SYX TNA FOC";

    public static int parseInt(String s){
        if(StringUtils.isBlank(s) || StringUtils.equalsIgnoreCase("null", s)){
            return 0;
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public static double convert(double  value){
        long l1 = Math.round(value*100);   //四舍五入
        double  ret   =   l1/100.0;
        return ret;
    }
    public static double parseDouble(String s){
        if(StringUtils.isBlank(s) || StringUtils.equalsIgnoreCase("null", s)){
            return 0;
        }
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public static double add(double d1, double d2){
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();
    }
    public static long parseLong(String s){
        if(StringUtils.isBlank(s) || StringUtils.equalsIgnoreCase("null", s)){
            return 0;
        }
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static boolean parseBoolean(String s){
        if(StringUtils.isBlank(s) || StringUtils.equalsIgnoreCase("null", s)){
            return false;
        }
        try {
            return Boolean.parseBoolean(s);
        } catch (Exception e) {
            return false;
        }
    }
    public static Date parseDate(String s){
        if(StringUtils.isBlank(s) || StringUtils.equalsIgnoreCase("null", s)){
            return null;
        }
        try {
            return DateUtil.yyyy_MM_dd.parse(s.substring(0, 10));
        } catch (Exception e) {
            return null;
        }
    }



}
