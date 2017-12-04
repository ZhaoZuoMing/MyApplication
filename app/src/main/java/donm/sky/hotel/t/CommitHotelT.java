package donm.sky.hotel.t;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.AppT;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;
import com.myuntils.MyAllUntils;
import com.myviews.MyListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import donm.sky.hotel.adapter.CommitAdapter;
import donm.sky.hotel.htservice.CommitSer;
import donm.sky.hotel.model.ArriveTime;
import donm.sky.hotel.model.CardData;
import donm.sky.hotel.model.CommitHotel;
import donm.sky.hotel.model.CommitOrderResult;
import donm.sky.hotel.model.GuaranteeRule;
import donm.sky.hotel.model.HotelDataValidateResult;
import donm.sky.hotel.model.HotelDetail;
import donm.sky.hotel.model.HotelRoom;
import donm.sky.hotel.model.NightlyRates;
import donm.sky.hotel.model.RatePlans;
import donm.sky.hotel.until.MacAddressUtils;
import donm.sky.hotel.until.TimeUntil;
import f.sky.flight.core.WebServUtil;
import f.sky.flight.model.B_TouristDOObj;
import f.sky.flight.model.UserServerObj;

/**
 * Created by Administrator on 2017/2/16/016.
 */

public class CommitHotelT extends AppT {
    protected CommitHotel commitH;
    private static  final String TAG  = "CommitHotelActivity:";
    private TextView commit_hotel_name,commit_hotel_room,commit_hotel_enterdate,commit_hotel_arrivedate,commit_hotel_paytype,commit_hotel_allprice;
    private TextView order_person_cname,order_person_phone,order_chenben_txt,order_project_name_txt,order_hotel_changerule,order_hotel_danbaorule;
    private TextView danbao_type_price,danbao_type_txt,app_title_txt;
    private ImageView app_back_icon;
    private Spinner spinner_year,spinner_month,spinner_card_type;
    private EditText person_card_number,person_card_cvv,person_card_username,person_shengfen_number;
    private CheckBox isknow_order_rule;
    private LinearLayout danbao_card_layout;
    private MyListView lisview;
    private HotelDetail detail;
    private RatePlans ratePlan;
    private HotelRoom hotelRoom;
    private GuaranteeRule guaranteeRule;
    private int rooms;//预订的房间数量
    private ArriveTime time;//具体到店时间
    private static  final int COMMIT_HOTEL_DATA = 0;//提交酒店数据
    private static  final int HOTEL_DATA_VALITA = 1;//验证酒店提交的数据
    private boolean isNeedRole = false;
    private String [] cardyearArrays;
    private String [] cardMonth ;
    private String [] IDTypes;
    private CardData cardData;
    private boolean isKnowRolue = false;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.commit_hotel_layout);
        commitH = MyApp.commitHotel;
        ratePlan =     MyApp.getPlan();
        detail =       MyApp.getHotelDetail();
        hotelRoom =    MyApp.getHotelRoom();
        rooms = commitH.getRooms();
        time = commitH.getEnterTime();
        cardyearArrays = getResources().getStringArray(R.array.years);
        cardMonth = getResources().getStringArray(R.array.int_months);
        IDTypes = getResources().getStringArray(R.array.cards_type);
        cardData =new CardData();
        initView();

    }

    /*快捷预订*/
    public void orderHotelFast(View v){
        if (isKnowRolue){
            cardData.setNeedRoule(isNeedRole);
            if (isNeedRole){
                //判断信用卡输入内容
                if (checkCard()){
                    executeWeb(HOTEL_DATA_VALITA,"正在验证数据...");
                }
            }else{
                executeWeb(HOTEL_DATA_VALITA,"正在验证数据...");
            }
        }else{
            AndroidUtil.shortToast(CommitHotelT.this,"请确认变更规则");
        }
    }
    /*检查信用卡输入完整*/
    private boolean checkCard() {
         String cardNumber  = person_card_number.getText().toString();
         String CVV = person_card_cvv.getText().toString();
         String cardName = person_card_username.getText().toString();
         String IDNmber = person_shengfen_number.getText().toString();
         if (cardNumber.equals("")){
             AndroidUtil.shortToast(this,"请输入卡号");
             return false;
         }else if (CVV.equals("")){
             AndroidUtil.shortToast(this,"请输入CVV");
             return false;
         }else if (cardName.equals("")){
             AndroidUtil.shortToast(this,"请输入持卡人姓名");
             return false;
         }else if (IDNmber.equals("")){
             AndroidUtil.shortToast(this,"请输入证件号");
             return false;
         }
         if (!"".equals(cardNumber)&&!"".equals(CVV)&&!"".equals(cardName)&&!"".equals(IDNmber)){
             cardData.setCardNumber(cardNumber);
             cardData.setCardCVV(CVV);
             cardData.setHorderName(cardName);
             cardData.setIDNumber(IDNmber);
             return true;
         }
         return  true;
    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag==COMMIT_HOTEL_DATA){//提交酒店数据
            UserServerObj serverObj = MyApp.loginRegResult.getUserServerObject();
             /*************************参数区***********************************/
            int clientId= serverObj.getUserOrgObj().getClientID();
            String cityName = MyApp.getH_city().getName();
            int BizDepID = serverObj.getUserDeptObj().getID();
            int corpId =  serverObj.getUserOrgObj().getCorpID();//公司Id
            int DepId =   0  ;//部门id loginRegResult.getUserServerObject().getUserDeptObj().getID()
            int Bookid =  serverObj.getId();//loginRegResult.getUserServerObject().getId()
            int rank = detail.getCategory();
            String status  = "15";
            String SouceType = "0";
            String SellType = "0";//协议酒店1 非 0
            String Payment= getPayMent();//0 预付 1现付  2 网上支付
            String Vendor = "ELong";//价格来源
            int GuestCount = commitH.getB_touristDOObjs().size();//入住人数
            String BookName =  commitH.getUser_name();
            int ConfigId = 0;
           /*需要的时间*/
            String ConfirmDate =TimeUntil.getBejiT();
            String checkInData  =  MyApp.gethEnterT();
            String checkOutData =  MyApp.gethOutT();
            //--落在本地的酒店数据格式为：2017-07-12 T 12:30:00
            String EarliestArrivalTime = TimeUntil.timeAdd(MyApp.gethEnterT()+time.getTime()+":00+08:00");//最早入住日期+"+08:00"
            String LatestArrivalTime =  MyApp.gethEnterT()+"T"+time.getTime()+":00";//最晚入住日期+08:00
            String createData = TimeUntil.getBejiT();//+8:00
            //----艺龙提交酒店的时间日期格式-----2017-07-12 13:20:00
            String ylEarliestArrivalTime =TimeUntil.yiLongT(MyApp.gethEnterT()+time.getTime()+":00");
            String ylLatestArrivalTime =  MyApp.gethEnterT()+" "+time.getTime()+":00";
            Log.e(TAG, "提交艺龙的时间: "+ylEarliestArrivalTime+"\t"+ylLatestArrivalTime);

            int Breakfast = getBreakFast(commitH.getFood());
            int net =  hotelRoom.getBroadnetAccess();
            String hotelPhone = detail.getPhone();
            // 如果为Elong 或者 Hotel 就直接赋值为他 或者为NoSense则为“”  “” 如果没有也为空
            String invoicemode = getinvoicemode(ratePlan.getInvoiceMode());//
            //所需要的联系人信息
            String ContactPersonName =  commitH.getUser_name();
            String ContactPhoneNumber = commitH.getPhone_number();
            String ContactEmail = commitH.getEmail();
            //入住人
            List<B_TouristDOObj> enterPersons = commitH.getB_touristDOObjs();
            //客人类型
            String CustomerType = ratePlan.getCustomerType();
            //每日价格
            List<NightlyRates> rates = ratePlan.getNightlyRates();
            String phoneIp = MacAddressUtils.getIpAddress(this);
            boolean IsGuaranteeOrCharged = getIsGuaranteeOrCharged(ratePlan.getPaymentType());
            String bookData =   MyAllUntils.getNowDateF();

            String ratePlanCode = ratePlan.getRatePlanId();
            String RoomId = hotelRoom.getRoomId();/**新增roomid**/
            String hotelID  = detail.getHotelID()+"";
            String hotelCode = ratePlan.getHotelCode()+"";
            String RoomTypeId = ratePlan.getRoomTypeId();
            String CurrencyCode = ratePlan.getCurrencyCode();
            String TotalPrice = commitH.getOrderP();
            double elTotalPrice = WebServUtil.parseDouble(ratePlan.getTotalRate())*commitH.getRooms();
            Log.e(TAG, "***两个价格***"+TotalPrice+"\t"+elTotalPrice);
            /********************************* end ****************************************/
            return CommitSer.commitHoteData(clientId,corpId,DepId, BizDepID,hotelID,hotelCode,detail.getName(),detail.getEName(),
                    detail.getBrandId()+"",detail.getAddress(),detail.getEAddress(),
                    hotelRoom.getName(),hotelRoom.getEName(),ratePlanCode,status,rank,detail.getCityId(),cityName,SouceType,SellType,Payment,commitH.getCostCenter(),
                    commitH.getProjectname(),Vendor,commitH.getRooms(),commitH.getNumNight(),GuestCount,
                    checkInData,checkOutData,
                    Bookid,
                    BookName,
                    bookData,
                    ConfigId, ConfirmDate,ratePlan.getPaymentType(),
                    EarliestArrivalTime,LatestArrivalTime,createData,ylEarliestArrivalTime,ylLatestArrivalTime,
                    Breakfast,net,hotelPhone,invoicemode,
                    ContactPersonName,ContactPhoneNumber,ContactEmail,enterPersons,CustomerType,rates,phoneIp,IsGuaranteeOrCharged,cardData,
                    RoomTypeId,CurrencyCode,TotalPrice,elTotalPrice,RoomId
                    );


        }else if (flag==HOTEL_DATA_VALITA){//验证酒店数据
                Log.e(TAG, "应支付价格: "+commitH.getOrderP());
                 /********这里还需改动*******/
                String EarliestArrivalTime = TimeUntil.timeCheck(MyApp.gethEnterT()+time.getTime()+":00")+"+08:00";//最早入住日期
                String LatestArrivalTime =  MyApp.gethEnterT()+"T"+time.getTime()+":00+08:00";//最晚入住日期

            return CommitSer.hotelDataValidata(MyApp.gethEnterT(),MyApp.gethOutT(),EarliestArrivalTime,LatestArrivalTime,detail.getCode()+"",ratePlan.getRoomTypeId(),
                    ratePlan.getRatePlanId(),WebServUtil.parseDouble(commitH.getOrderP()),commitH.getRooms());
        }
        return super.doTask(flag, params);

    }
   /*获取并返回支付方式*/
    private boolean getIsGuaranteeOrCharged(String paymentType) {
        if (paymentType.equals("Prepay")){
            return  true;
        }else{
            return false;
        }
    }

    /**
     * 发票提供方信息
     * @param invoiceMode
     * @return
     */
    private String getinvoicemode(String invoiceMode) {
        if (invoiceMode.equals("Elong")){
             return "Elong";
        }else if (invoiceMode.equals("Hotel")){
            return "Hotel";
        }else if(invoiceMode.equals("NoSense")){
            return "";
        }

        return "";
    }

    /**
     * 获取早餐类型
     * @param food
     * @return
     */
    private int getBreakFast(String food) {
        switch (food){
             case "(无早)":
                return  0;
            case "(单早)":
                return 1;
            case "(双早)":
                return 2;
            case "(三份以上早餐)":
                return 3;

            }
          return 0;
    }

    /**
     * 获取支付方式
     * @return
     */
    private String  getPayMent() {
        if (ratePlan.getPaymentType().equals("Prepay")){
            return  "0";
        }else if (ratePlan.getPaymentType().equals("SelfPay")){
            return  "1";
        }else {
            return  "2";
        }
    }

    @Override
    public void taskDone(int flag, Object result) {
        super.taskDone(flag, result);
        if (flag==COMMIT_HOTEL_DATA){
            if (null==result){
                AndroidUtil.shortToast(CommitHotelT.this,"提交数据失败");
                return;
            }else{
                CommitOrderResult orderResult = (CommitOrderResult) result;
                 if (orderResult.isSuccess()){
                     Intent intent = new Intent(this,OrderSucessT.class);
                     intent.putExtra("H_ORDER",orderResult.getID()+"");
                     startActivity(intent);
                     AndroidUtil.shortToast(this,"下单成功！");
                 }else{
                     AndroidUtil.shortToast(this,"下单失败,请重试！");
                 }

            }

        }else if (flag==HOTEL_DATA_VALITA){//验证数据
            if (null==result){
                return;
            }else{
                HotelDataValidateResult validateResult  = (HotelDataValidateResult) result;
                 if (validateResult.getResultObject().getCode().equals("0")){
                     executeWeb(COMMIT_HOTEL_DATA,"正在下单...");
                 } else{
                     AndroidUtil.shortToast(this,validateResult.getResultObject().getCode());
                 }
            }
        }
    }
/*组件初始化*/
    private void initView() {
        app_title_txt = (TextView)findViewById(R.id.app_title_txt);
        app_title_txt.setText("酒店订单确认");
        commit_hotel_name = (TextView)findViewById(R.id.commit_hotel_name);
        commit_hotel_room = (TextView)findViewById(R.id.commit_hotel_room);
        commit_hotel_enterdate = (TextView)findViewById(R.id.commit_hotel_enterdate);
        commit_hotel_arrivedate = (TextView)findViewById(R.id.commit_hotel_arrivedate);
        commit_hotel_paytype = (TextView)findViewById(R.id.commit_hotel_paytype);
        commit_hotel_allprice = (TextView)findViewById(R.id.commit_hotel_allprice);
        order_person_cname = (TextView)findViewById(R.id.order_person_cname);
        order_person_phone = (TextView)findViewById(R.id.order_person_phone);
        order_chenben_txt = (TextView)findViewById(R.id.order_chenben_txt);
        order_project_name_txt = (TextView)findViewById(R.id.order_project_name_txt);
        order_hotel_changerule = (TextView)findViewById(R.id.order_hotel_changerule);
        order_hotel_danbaorule = (TextView)findViewById(R.id.order_hotel_danbaorule);

        danbao_type_price = (TextView)findViewById(R.id.danbao_type_price);
        danbao_type_txt = (TextView)findViewById(R.id.danbao_type_txt);

        spinner_year = (Spinner)findViewById(R.id.spinner_year);
        cardData.setYear(Integer.parseInt(cardyearArrays[0].substring(0,4)));
        spinner_month = (Spinner)findViewById(R.id.spinner_month);
        cardData.setMonth(Integer.parseInt(cardMonth[0]));
        spinner_card_type = (Spinner)findViewById(R.id.spinner_card_type);
        cardData.setIDType(IDTypes[0]);

        person_card_number = (EditText)findViewById(R.id.person_card_number);
        person_card_cvv = (EditText)findViewById(R.id.person_card_cvv);
        person_card_username = (EditText)findViewById(R.id.person_card_username);
        person_shengfen_number = (EditText)findViewById(R.id.person_shengfen_number);

        isknow_order_rule = (CheckBox)findViewById(R.id.isknow_order_rule);
        app_back_icon = (ImageView)findViewById(R.id.app_back_icon);
        app_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        danbao_card_layout = (LinearLayout)findViewById(R.id.danbao_card_layout);
        spinner_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardData.setYear(Integer.parseInt(cardyearArrays[position].substring(0,4)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardData.setMonth(Integer.parseInt(cardMonth[position]));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_card_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardData.setIDType(IDTypes[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        isknow_order_rule.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isKnowRolue = isChecked;
            }
        });

       setAllMyData();
    }
/*设置数据*/
    private void setAllMyData() {
        commit_hotel_name.setText(detail.getName()+detail.getEName());
        commit_hotel_room.setText(hotelRoom.getName()+commitH.getFood());
        commit_hotel_enterdate.setText("入住日期："+MyApp.gethEnterT()+ TimeUntil.getWeek(MyApp.gethEnterT())+"至"+MyApp.gethOutT()+TimeUntil.getWeek(MyApp.gethOutT()));
        commit_hotel_arrivedate.setText("抵店日期："+MyApp.gethEnterT()+"\t\t"+time.getTime());
        commit_hotel_paytype.setText(commitH.getPayType());
        commit_hotel_allprice.setText(commitH.getOrderP());
        order_person_cname.setText(commitH.getUser_name());
        order_person_phone.setText(commitH.getPhone_number());
        order_project_name_txt.setText(commitH.getProjectname());
        order_chenben_txt.setText(commitH.getCostCenter());
        order_hotel_changerule.setText("变更规则："+commitH.getChange_rule());
        order_hotel_danbaorule.setText("担保规则："+commitH.getDanBaoRule());
        lisview = (MyListView)findViewById(R.id.user_name_listview);
        lisview.setAdapter(new CommitAdapter(commitH.getB_touristDOObjs(),this));

        setDanBaoRule();
    }
  /*设置担保内容*/
    private void setDanBaoRule() {
        //先判断是否需要担保--预付
        String guaranteeRuleIds = ratePlan.getGuaranteeRuleIds();
        if (null==guaranteeRuleIds||"".equals(guaranteeRuleIds)){
            danbao_card_layout.setVisibility(View.GONE);
        }else{//判断担保的情况\
             guaranteeRule = commitH.getGuaranteeRule();
             danbao_card_layout.setVisibility(View.VISIBLE);
             setDBContent();
        }
    }
   /*设置担保内容*/
    private void setDBContent() {
        //任何情况下都需要担保，只是担保首晚房费还是全额
        if (guaranteeRule.isAmountGuarantee()==false&&guaranteeRule.isTimeGuarantee()==false){
             setNightDBPI();
        }else if (guaranteeRule.isAmountGuarantee()==true&&guaranteeRule.isTimeGuarantee()==false){//房量担保
            setNightDBPI();
            setRoomNumDB();
        }else if (guaranteeRule.isAmountGuarantee()==false&&guaranteeRule.isTimeGuarantee()==true){//到店时间担保
               setNightDBPI();
               setDBisTime();
        }else if (guaranteeRule.isAmountGuarantee()==true&&guaranteeRule.isTimeGuarantee()==true){// 房量担保或到店时间担保
              //需要检查房量和到店时间
            setNightDBPI();
             if (setRoomNumDB()==true){//房量需要担保
                 return;
             }else {
                 setDBisTime();
             }

        }
    }//担保***end***

    /*房量担保*/
    private boolean setRoomNumDB() {
        if (rooms>=guaranteeRule.getAmount()){
            danbao_card_layout.setVisibility(View.VISIBLE);
            isNeedRole = true;
            return true;
        }else{
            danbao_card_layout.setVisibility(View.GONE);
            isNeedRole = false;
            return false;
        }
    }
   /*到店时间担保*/
    private boolean setDBisTime() {
        String startTime = guaranteeRule.getStartTime();
        if (time.getId()==0){//14点前
            danbao_card_layout.setVisibility(View.GONE);//不需要担保
            isNeedRole=false;
            return false;
        }else if (time.getId()==1){//14-23:59
            if (timeAsTime(time.getTime(),startTime)){
                danbao_card_layout.setVisibility(View.VISIBLE);//需要担保
                isNeedRole = true;
                return true;
            }else{
                danbao_card_layout.setVisibility(View.GONE);//No需要担保
                isNeedRole = false;
                return false;
            }
        }else if (time.getId()==-1){//24点以后全部需要担保
            danbao_card_layout.setVisibility(View.VISIBLE);//次日都需要担保需要担保
            isNeedRole = true;
            return true;
        }
        return true;
    }

    /*判断担保时间*/
    private static boolean timeAsTime(String nowTime,String startTime){
        DateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date end = df.parse(startTime);//将字符串转换为date类型
            Date start = df.parse(nowTime);
            if(end.getTime()<start.getTime())//比较时间大小,dt1小于dt2
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
   /*设置担保金额*/
    private void setNightDBPI() {
        //先判断担保的费用
        if (guaranteeRule.getGuaranteeType().equals(TimeUntil.FIRST_NIGHT_COST)){//首晚担保
            double oneNightP = WebServUtil.convert(WebServUtil.parseDouble(ratePlan.getAverageRate())*rooms);
            danbao_type_price.setText(oneNightP+"元");
            danbao_type_txt.setText("首晚房费担保");
            isNeedRole = true;
        }else if (guaranteeRule.getGuaranteeType().equals(TimeUntil.FULL_NIGHT_COST)){//全额房费担保
            double allP =  WebServUtil.convert(WebServUtil.parseDouble(ratePlan.getAverageRate())*rooms*commitH.getNumNight());
            danbao_type_price.setText(allP+"元");
            danbao_type_txt.setText("全额房费担保");
            isNeedRole = true;
        }
    }






}
