package com.mytables;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.myuntils.MyAllUntils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.skytrip.train.model.TrainCity;
import cn.skytrip.train.model.TrainObj;
import cn.skytrip.train.model.TraintStation;
import donm.sky.hotel.model.CardData;
import donm.sky.hotel.model.CommitHotel;
import donm.sky.hotel.model.HotelDataList;
import donm.sky.hotel.model.HotelDetail;
import donm.sky.hotel.model.HotelDetailSearchResult;
import donm.sky.hotel.model.HotelInfoSearchResult;
import donm.sky.hotel.model.HotelListSearchResult;
import donm.sky.hotel.model.HotelRoom;
import donm.sky.hotel.model.RatePlans;
import f.sky.flight.core.Constants;
import f.sky.flight.core.WebServUtil;
import f.sky.flight.model.AirportObj;
import f.sky.flight.model.ApplyObj;
import f.sky.flight.model.B_OrderDOObj;
import f.sky.flight.model.B_TouristDOObj;
import f.sky.flight.model.Cabin;
import f.sky.flight.model.FlightRoleObj;
import f.sky.flight.model.FlightSegment;
import f.sky.flight.model.LoginRegResult;
import f.sky.flight.model.QueryAirlineResult;
import f.sky.flight.model.QueryAirportResult;
import f.sky.flight.model.QueryAuditMailResult;
import f.sky.flight.model.QueryCostCenterResult;
import f.sky.flight.model.QueryFlightRoleResult;
import f.sky.flight.model.QueryPlaneStyleResult;
import f.sky.flight.model.QueryPsgResult;
import f.sky.flight.model.QueryReasonCodeResult;
import f.sky.flight.model.SkyWayObj;
import f.sky.flight.model.SortModel;

/**
 * Created by Administrator on 2016/11/18/018.
 */

public class MyApp extends Application {
    //测试账号登录
    public static boolean debug = false;
    public static boolean hotelDebug = true;
    public static  MyApp context;
    private static MyApp  instence= null;
    public static int index;

    public static LoginRegResult loginRegResult;// 用户信息,登录时初始化
    public static B_OrderDOObj orderObj;
    private B_TouristDOObj delTouristDOObj;
    public static QueryAirportResult queryAirportResult;// 机场信息,加载初始化
    public static QueryAirlineResult queryAirlineResult;// 航空公司信息,加载初始化
    public static QueryPlaneStyleResult queryPlaneStyleResult;// 机型信息,加载初始化
    public static QueryReasonCodeResult queryReasonCodeResult;// 未选最低价信息,登录时初始化
    public static QueryCostCenterResult queryCostCenterResult;// 成本中心信息,登录时初始化
    public static QueryPsgResult queryPsgResult;// 旅客信息
    public static QueryAuditMailResult queryAuditMailResult;// 最近一次订单审核人邮箱信息,下订单时读取
    public static QueryFlightRoleResult queryFlightRoleResult;
    private static AirportObj orgCity;
    private static AirportObj dstCity;
    public static ApplyObj applyObj = new ApplyObj();
    private static Calendar deptDate = Calendar.getInstance();
    private static Calendar backDate = Calendar.getInstance();
    private static boolean isRoundFlight;
    public static B_OrderDOObj tempOrderObj = new B_OrderDOObj();
    private static String startDate;
    private static String endDate;
    private static String trainStartDate;
    public static  String Airline;
    public static  String AirStartT;
    public static boolean airLineRole;
    public static int net;
    public static Map<String, Object> args = new HashMap<String, Object>();

    public static  HotelDataList cancelData;
    public  static  TraintStation startStation;
    public  static  TraintStation endStation;
    private String train_toDate;
    public static  TrainObj trainObj;
    public static SortModel h_city;
    private static String hEnterT;
    private static String hOutT;
    public static List<HotelListSearchResult>HotelListSearchResult ;
    public static List<HotelInfoSearchResult> hotelInfoSearchResult;
    public static HotelDetailSearchResult hotelDetailSearchResult;
    public static HotelDetail hotelDetail;
    public static HotelRoom hotelRoom;
    public static RatePlans plan;
    public static CommitHotel commitHotel;
    public static String strLevel;
    public static String QueryText;
    public static int LowRate;
    public static int HighRate;
    public static CardData cardData;
    public static FlightSegment flightSegment;
//    public static Cabin cabinSeg;
    public static HotelDataList hotelDataList;
    public static  String gethEnterT() {
        if (null==hEnterT){
            return  MyAllUntils.getNowDateF();
        }else {
            return hEnterT;
        }

    }
   public static void setFlightSegment(FlightSegment flightSegment){
        MyApp.flightSegment  =flightSegment;
   }
    public static FlightSegment getFlightSegment() {
        return flightSegment;
    }

    public static RatePlans getPlan() {
        return plan;
    }

    public static void setPlan(RatePlans plan) {
        MyApp.plan = plan;
    }

    public static HotelRoom getHotelRoom() {
        return hotelRoom;
    }

    public static void setHotelRoom(HotelRoom hotelRoom) {
        MyApp.hotelRoom = hotelRoom;
    }

    public static HotelDetail getHotelDetail() {
        return hotelDetail;
    }

    public static void setHotelDetail(HotelDetail hotelDetail) {
        MyApp.hotelDetail = hotelDetail;
    }

    public static List<HotelListSearchResult> getHotelListSearchResult() {
        return HotelListSearchResult;
    }

    public static void setHotelListSearchResult(List<HotelListSearchResult> hotelListSearchResult) {
        HotelListSearchResult = hotelListSearchResult;
    }

    public static List<HotelInfoSearchResult> getHotelInfoSearchResult() {
        return hotelInfoSearchResult;
    }

    public static void setHotelInfoSearchResult(List<HotelInfoSearchResult> hotelInfoSearchResult) {
        MyApp.hotelInfoSearchResult = hotelInfoSearchResult;
    }

    public static void sethEnterT(String hEnterT) {
        MyApp.hEnterT = hEnterT;
    }

    public static String gethOutT() {
        if (null==hOutT){
            return MyAllUntils.getNextDateF();
        }{
            return hOutT;
        }

    }

    public static void sethOutT(String hOutT) {
        MyApp.hOutT = hOutT;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        instence = this;
        ImageDownLoad();
    }
    public static MyApp getInstance() {
        return instence;
    }
    private void ImageDownLoad() {

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory().cacheOnDisc()
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new WeakMemoryCache())

                .discCacheExtraOptions(240, 400, Bitmap.CompressFormat.PNG, 40, null)
                .memoryCacheExtraOptions(240, 400).discCacheFileCount(1000)
                .writeDebugLogs().defaultDisplayImageOptions(defaultOptions)
                .build();

        ImageLoader.getInstance().init(config);
    }
    public static int getPageSize(){
        return getSp().getInt(Constants.PAGE_SIZE_S, Constants.PAGE_SIZE_I);
    }
    public static void setH_city(SortModel city){
       MyApp.h_city = city;
    }

    public  static SortModel getH_city(){
        if (null==h_city){
            SortModel h_city = new SortModel();
            h_city.setCountry(1);
            h_city.seteName("Shanghai");
            h_city.setName("上海");
            h_city.setProvince(2);
            h_city.setStatus(1);
            h_city.setCode("0201");
            h_city.setId(2);
            return  h_city;
        }else {
            return MyApp.h_city;
        }
    }
    public static TrainObj getTrainObj(){
        if (trainObj==null){
            return null;
        }else{
            return trainObj;
        }
    }
    public static void setTrainObj(TrainObj mObj){
        MyApp.trainObj = mObj;

    }

    public static final int ORDER_COMMIT_1_PAY = 1;
    public static final int ORDER_COMMIT_2_AUDIT = 2;
    public static final int ORDER_COMMIT_3_NOTICETODIRECT = 3;
    public static final int ORDER_COMMIT_4_TICKETDIRECT = 4;
    public static final String[] SELECT_COMMIT_ORDER_ITEMS = {"支付宝支付", "审批", "通知代理人出票", "直接出票"};
    public static final String COMMIT_ORDER_TYPE = "COMMIT_ORDER_TYPE";
    public static int parseCommitOrderType(String commitTypeDesc){
        int i = 0;
        for(String each: SELECT_COMMIT_ORDER_ITEMS){
            i++;
            if(StringUtils.equals(commitTypeDesc, each)){
                return i;
            }
        }
        return i;
    }

    public static boolean hideDeliverAddress(){
        List<String> deliverAddrs = loginRegResult.getUserServerObject().getUserOrgObj().getDeliverAddress();
        return deliverAddrs == null || deliverAddrs.size() == 0;
    }

    public static String getLatestAuditMailPre() {
        if (null != MyApp.queryAuditMailResult) {
            return MyApp.queryAuditMailResult.getMail().split(
                    Constants.EMAIL_MIDDLE_FIX)[0];
        }
        return Constants.EMPTY_STRING;
    }
    //用于判断是否是往返机票
    public static boolean isRoundFlight() {
        return isRoundFlight;
    }

    public static void setRoundFlight(boolean isRoundFlight) {
        MyApp.isRoundFlight = isRoundFlight;
    }
    /********  **********/
    public static String getDefaltStartDate() {
        if (startDate==null){
            startDate = MyAllUntils.getNowDateF();
          }
        return startDate;
    }

    public static  String getTrainStartDate(){
        if (trainStartDate==null){
            trainStartDate = MyAllUntils.getNowDateF();
        }
        return  trainStartDate;
    }
    public static  void setTrainStartDate(String trainStartDate){
        MyApp.trainStartDate = trainStartDate;

    }
    public static void setStartDate(String startDate) {
        MyApp.startDate = startDate;
    }

    public static String getDefaltEndDate() {
         if (endDate==null){
             Date nowDate = new Date(System.currentTimeMillis());
             Calendar out = Calendar.getInstance();
             out.setTime(nowDate);
             out.setTimeInMillis(out.getTimeInMillis() + (86400000 * 1));
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             endDate = sdf.format(out.getTime());
         }
        return endDate;
    }

    public static String getTrainEndDate(String date,int days) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Date dt;
        try {
            dt = sdf.parse(date);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.DAY_OF_YEAR,days);//日期加1天
            Date dt1=rightNow.getTime();
            String reStr = sdf.format(dt1);
            String sub = reStr.substring(4, 6)+"月";
            String sub1 = reStr.substring(6,8)+"日";
            return (sub+sub1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }


    public static  void setEndDate(String endDate) {
        MyApp.endDate = endDate;
    }

    /**
     * 转化为需要返回的字符串
     * @param date
     */
    public static String dateChangeToString(String date) {
        String str1 = date.substring(0,4);//2017-18-18
        String str2 = date.substring(5,7);
        String str3 = date.substring(8,10);
        String newStr  = str1+"年"+str2+"月"+str3+"日";
        return  newStr;
    }
    /*********************/
    /**
     * 出发城市
     * @param orgCity
     */
    public static void setOrgCity(AirportObj orgCity) {
        MyApp.orgCity = orgCity;
    }

    /**
     * 到达城市
     * @param dstCity
     */
    public static void setDstCity(AirportObj dstCity) {
        MyApp.dstCity = dstCity;
    }

    /**
     * 显示协议价格
     * @param showPrice
     * @return
     */
    public static String showNumber(double showPrice){
        int intPrice = (int)showPrice;
        double distance = showPrice*100 - intPrice*100;
        boolean showDouble = ((int)distance != 0);
        if(showDouble){
            return "￥"+ showPrice;
        } else {
            return "￥" + (int)showPrice;
        }
    }
   public static TraintStation getTrainOrgCity(){
       if (null==startStation){
           startStation = new TraintStation();
           startStation.setName("上海虹桥");
           startStation.setCode("AOH");
       }
       return  startStation;
   }
    public  static  void setTrainOrgCity(TraintStation s){
        MyApp.startStation = s;

    }
    public static  TraintStation getTrainDeptCity(){
        if (null == endStation){
            endStation = new TraintStation();
            endStation.setName("北京南");
            endStation.setCode("VNP");
        }
        return endStation;
    }

    public static  void setTrainDeptCity(TraintStation s){
        MyApp.endStation = s;

    }

    public static AirportObj getDefaultOrgCity() {
        if (null == orgCity) {
            orgCity = new AirportObj();
            orgCity.setAirPortCode("SHA");
            orgCity.setAirPortName("上海虹桥");
            orgCity.setFirstPy("S");
        }
        return orgCity;
    }

    public static String showStatusDesc(int orderStatus, int applyStatus, int payStatus){
        if(payStatus == 1 && orderStatus != 20){
            return "已支付";
        }
        if (orderStatus == WebServUtil.ORDER_SAVED_STATUS) {
            return WebServUtil.ORDER_STATUS_DESC[0];
        } else if (orderStatus == WebServUtil.ORDER_DELETED_STATUS) {
            if(applyStatus == WebServUtil.APPLY_OUTDATE_STATUS){
                return WebServUtil.ORDER_DELETE_STATUS_DESC[0];
            } else if(applyStatus == WebServUtil.APPLY_CANCEL_STATUS){
                return WebServUtil.ORDER_DELETE_STATUS_DESC[1];
            } else if(applyStatus == WebServUtil.APPLY_REJECT_STATUS){
                return WebServUtil.ORDER_DELETE_STATUS_DESC[2];
            }
        } else if (orderStatus == WebServUtil.ORDER_APPLYED_STATUS) {
            if(applyStatus == WebServUtil.APPLY_UNAUDIT_STATUS){
                return WebServUtil.ORDER_APPLYED_STATUS_DESC[0];
            } else if(applyStatus == WebServUtil.APPLY_AUDITED_STATUS){
                return WebServUtil.ORDER_APPLYED_STATUS_DESC[1];
            } else if(applyStatus == WebServUtil.APPLY_NOT_NEED_AUDIT_STATUS){
                return WebServUtil.ORDER_APPLYED_STATUS_DESC[2];
            } else if(applyStatus == WebServUtil.APPLY_EMGENCY_TICKET_STATUS){
                return WebServUtil.ORDER_APPLYED_STATUS_DESC[3];
            }
        } else if (orderStatus == WebServUtil.ORDER_UNPAYED_STATUS) {
            return WebServUtil.ORDER_STATUS_DESC[3];
        } else if (orderStatus == WebServUtil.ORDER_PAYED_STATUS) {
            return WebServUtil.ORDER_STATUS_DESC[4];
        }
        return "其他";
    }
    public static String getDiscountTxt(SkyWayObj skyWayObj) {
        String cabinDesc = skyWayObj.getCabinDesc();
        String cabin = cabinDesc.substring(0, 1);
        if (cabinDesc.indexOf(cabin) != -1) {
            if (StringUtils.equals("F", cabin)) {
                return "头等舱"
                        + (cabinDesc.substring(1) + "折").replace("100折", "全价");
            } else if (StringUtils.equals("C", cabin)) {
                return "公务舱"
                        + (cabinDesc.substring(1) + "折").replace("100折", "全价");
            } else if (StringUtils.equals("Y", cabin)) {
                return "经济舱"
                        + (cabinDesc.substring(1) + "折").replace("100折", "全价");
            } else {
                return (cabinDesc.substring(1) + "折").replace("100折", "全价");
            }
        } else {
            return "";
        }
    }

    public static String parseBrokeReasonStr(String no){
        if(StringUtils.isBlank(no)){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if(null != queryFlightRoleResult){
            for(FlightRoleObj fro: queryFlightRoleResult.getFlightRoleObjL()){
                if(fro.getCode().indexOf("{A[") != -1 && no.indexOf("{A[")!=-1){
                    sb.append(fro.getDetail().replace("[a]", parseABrokeMinuteS(no))).append("\n");
                } else if (fro.getCode().indexOf("{B[") != -1 && no.indexOf("{B[")!=-1){
                    sb.append(fro.getDetail().replace("[a]", parseBBrokeDays(no))).append("\n");
                } else if (fro.getCode().indexOf("{C}") != -1 && no.indexOf("{C}")!=-1){
                    sb.append(fro.getDetail()).append("\n");
                }
            }
            return StringUtils.removeEnd(sb.toString(), "\n");
        }
        return "";
    }
    private static String parseABrokeMinuteS(String no){
        Pattern p = Pattern.compile("\\{A\\[\\d{1,9}\\]\\}");
        Matcher m = p.matcher(no);
        if(m.find()){
            return StringUtils.removeEnd(StringUtils.removeStart(m.group(), "{A["), "]}");
        }
        return null;
    }
    private static String parseBBrokeDays(String no){
        Pattern p = Pattern.compile("\\{B\\[\\d{1,9}\\]\\}");
        Matcher m = p.matcher(no);
        if(m.find()){
            return StringUtils.removeEnd(StringUtils.removeStart(m.group(), "{B["), "]}");
        }
        return null;
    }

    public static AirportObj getDefaultDstCity() {
        if (null == dstCity) {
            dstCity = new AirportObj();
            dstCity.setAirPortCode("PEK");
            dstCity.setAirPortName("北京首都");
            dstCity.setFirstPy("B");
        }
        return dstCity;
    }

    /**
     * 调用SharedPreferences对象来保存数据
     * @return
     */
    public static SharedPreferences getSp() {
        return context.getSharedPreferences(Constants.SHARE_PREFER,
                Activity.MODE_PRIVATE);
    }



    public static boolean parseUserNeedAudit(){
        return StringUtils.equals(loginRegResult.getUserServerObject().getUserOrgObj().getConfigsMap().get("DEPTYPE"), "NEEDAUDIT");
    }
    public static boolean parseUserAuditSelf(){
        return Boolean.valueOf(loginRegResult.getUserServerObject().getUserOrgObj().getConfigsMap().get("AUDITSELF"));
    }
    public static boolean needAudit() {
        boolean notLowestPrice = false;
        for(SkyWayObj skyWayObj: MyApp.orderObj.getSkyWayObjL()){
            if(skyWayObj.getLowestPrice() != skyWayObj.getPrice()){
                notLowestPrice = true;
                break;
            }
        }
        boolean needAudit = notLowestPrice && MyApp.loginRegResult.getUserServerObject().getUserRoleObj().getRightCodes().indexOf("|TICKET_APPLY_NEED_AUDIT_NOT_LOWEST|") != -1;
        return parseUserNeedAudit()
                || MyApp.loginRegResult.getUserServerObject().getUserRoleObj()
                .getRightCodes().indexOf("|TICKET_APPLY_NEED_AUDIT|") != -1
                || needAudit;
    }
    public static boolean haveSuffix(){
        String mailAddrs = MyApp.loginRegResult.getUserServerObject().getUserOrgObj().getMailAddress();
        if(StringUtils.isBlank(mailAddrs)){
            return false;
        }
        return mailAddrs.split(",").length > 0;
    }

    public static boolean showEmergencyTicketBtn(){
        return StringUtils.equals(loginRegResult.getUserServerObject().getUserOrgObj().getConfigsMap().get("ISSUEURGENT"), "1");
    }

    public static boolean needProjectNumber(){
        return StringUtils.equals(loginRegResult.getUserServerObject().getUserOrgObj().getConfigsMap().get("NEEDPROJECTNUMBER"), "1");
    }

    public static boolean allowPayOnline(){
        return StringUtils.equals(loginRegResult.getUserServerObject().getUserOrgObj().getConfigsMap().get("ALLOWPAYONLINE"), "1");
    }

    public static boolean allowCorpPay(){
        return StringUtils.equals(loginRegResult.getUserServerObject().getUserOrgObj().getConfigsMap().get("ALLOWCORPPAY"), "1");
    }

    public static boolean allowNoticeToDirect(){
        return StringUtils.equals(loginRegResult.getUserServerObject().getUserOrgObj().getConfigsMap().get("ALLOWNOTICETODIRECT"), "1");
    }

    public static boolean allowTicketDirect(){
        return StringUtils.equals(loginRegResult.getUserServerObject().getUserOrgObj().getConfigsMap().get("ALLOWTICKETDIRECT"), "1");
    }

    public static boolean showReasonCode(){
        return StringUtils.equals(loginRegResult.getUserServerObject().getUserOrgObj().getConfigsMap().get("SHOWREASONCODE"), "1");
    }

    public static boolean showCorporatePrice(){
        return StringUtils.equals(loginRegResult.getUserServerObject().getUserOrgObj().getConfigsMap().get("SHOWCORPORATEPRICE"), "1");
    }

    public static int bookForeDay(){
        return Integer.valueOf(loginRegResult.getUserServerObject().getUserOrgObj().getConfigsMap().get("BOOKFOREDAY"));
    }

    public static boolean isOnlyRule(){
        if(null == loginRegResult || null == loginRegResult.getUserServerObject()
                || null == loginRegResult.getUserServerObject().getUserRoleObj()
                || null == loginRegResult.getUserServerObject().getUserRoleObj().getRightCodes()){
            return false;
        }
        return -1 != loginRegResult.getUserServerObject().getUserRoleObj().getRightCodes().indexOf("|TICKET_MENU_ONLYROLEYES|");
    }


    public static String getCurrentAirlineVersion() {
        return getSp().getString(Constants.AIRLINE_DATA_VERSION,
                Constants.DEFAULT_AIRLINE_DATA_VERSION);
    }

    public static String getCurrentAirportVersion() {
        return getSp().getString(Constants.AIRPORT_DATA_VERSION,
                Constants.DEFAULT_AIRPORT_DATA_VERSION);
    }

    public static String getCurrentPlaneStyleVersion() {
        return getSp().getString(Constants.AIRPORT_DATA_VERSION,
                Constants.DEFAULT_AIRPORT_DATA_VERSION);
    }
    //机票地点集合
    private static List<AirportObj> hotCities = new ArrayList<>();
    private static List<TrainCity> hotTraincity = new ArrayList<>();
    //热门城市
    public static List<AirportObj> getHotCity() {
        if (null != hotCities && hotCities.size() > 0) {
            return hotCities;
        }
        List<AirportObj> otherHotCities = new ArrayList<AirportObj>();
        List<AirportObj> shaCities = new ArrayList<AirportObj>();
        List<AirportObj> bjsCities = new ArrayList<AirportObj>();
        String[] hotCitiesCode = WebServUtil.HOT_CITY_CODE.split(" ");
        for (String eachHot : hotCitiesCode) {
            for (AirportObj city : queryAirportResult.getAirprots()) {
                if (StringUtils
                        .equalsIgnoreCase(city.getAirPortCode(), eachHot)) {
                    if (StringUtils.equals("PEK", city.getAirPortCode())) {
                        bjsCities.add(city);
                        break;
                    } else if (StringUtils.equals("SHA", city.getAirPortCode())
                            || StringUtils.equals("PVG", city.getAirPortCode())) {
                        shaCities.add(city);
                        break;
                    } else {
                        otherHotCities.add(city);
                        break;
                    }
                }
            }
        }
        hotCities.addAll(shaCities);
        hotCities.addAll(bjsCities);
        hotCities.addAll(otherHotCities);
        return hotCities;
    }
}
