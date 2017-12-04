package donm.sky.hotel.t;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.AppT;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;
import com.myuntils.ListViewSetup;
import com.myviews.MyGridView;
import com.myviews.MyListView;

import java.io.InputStream;
import java.util.List;

import cn.skytrip.train.adapter.RoomNumAdapter;
import donm.sky.hotel.adapter.ArrvieTimeAdapter;
import donm.sky.hotel.adapter.CostCenterAdapter;
import donm.sky.hotel.json.JsonTime;
import donm.sky.hotel.model.ArriveTime;
import donm.sky.hotel.model.BookingRules;
import donm.sky.hotel.model.CommitHotel;
import donm.sky.hotel.model.DHotel;
import donm.sky.hotel.model.GuaranteeRule;
import donm.sky.hotel.model.HotelDetail;
import donm.sky.hotel.model.HotelDetailSearchResult;
import donm.sky.hotel.model.HotelRoom;
import donm.sky.hotel.model.NightlyRates;
import donm.sky.hotel.model.PrepayRule;
import donm.sky.hotel.model.RatePlans;
import donm.sky.hotel.model.ValueAdds;
import donm.sky.hotel.until.TimeUntil;
import f.sky.flight.adapter.SelectPsgAdapter;
import f.sky.flight.core.Constants;
import f.sky.flight.core.WebServUtil;
import f.sky.flight.model.B_TouristDOObj;


/**
 * Created by Administrator on 2017/2/13/013.
 * 酒店预订订单详情页面
 */

public class HotelOrderDetailT extends AppT {
     //选需要HotelDetail  Rateplans
     public final static int REQUEST_PSG_SELECT = 10;
    private TextView hotelname,order_room_name,order_room_date_num,order_room_content_isfood,g_line1,order_room_num_txt,user_chenben_name,r_line_1;
    private TextView order_room_all_price,order_room_arrivetime_txt,order_room_change_rule,order_room_payment,order_room_isfood,order_must_know;
     private ImageView app_back,order_room_num_img,order_room_need_img;
     private HotelDetailSearchResult searchResult;
     private HotelDetail detail;
     private RatePlans ratePlan;
     private HotelRoom hotelRoom;
     private MyGridView order_room_gridview;
     private MyListView order_room_listview;
     private RelativeLayout order_room_num_rety,order_room_addperson,order_room_arrive_rety,order_room_need_rety;
     private  boolean isCheck = true;
     private  boolean isRule = true;
     private int check_room_num=1;
     private SelectPsgAdapter selectPsgAdapter;
    private Button order_room_btn;
    private PopupWindow popupWindow;
    private View popView;
    private LinearLayout order_rule_lay,room_order_rule_ly,room_orderdanbao_rules;
    private EditText user_contact_name,user_ponenumber_name,user_emil_address,user_pjname_name;//联系人信息
    private TextView order_room_rules,order_room_danbao_txt;
    private RelativeLayout chengben_rety;//成本中心及项目
    //当前酒店的预定规则
    private List<BookingRules> bookingRules;
    //当前酒店的担保规则
    private List<GuaranteeRule> guaranteeRules;
    //当前酒店的预付规则
    private List<PrepayRule> prepayRules;
    //附加服务
    private List<ValueAdds> valueAddses;
    private String TypeCode;
    private int persions=0;
    private BookingRules bookingRule;
    private PrepayRule prepayRule;
    protected GuaranteeRule guaranteeRule;
    protected CommitHotel commitH;
    private List<ArriveTime> arriveTimes;
    private double allPrice;//总价

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        /*获得所需要的数据*/
        searchResult = MyApp.hotelDetailSearchResult;
        ratePlan =     MyApp.getPlan();
        detail =       MyApp.getHotelDetail();
        hotelRoom =    MyApp.getHotelRoom();
        getAllPrice();
        commitH = new CommitHotel();
        String str = readStream(getResources().openRawResource(R.raw.time));
        arriveTimes = JsonTime.getArrTime(str);
        getNeedData();
        setContentView(R.layout.hote_order_lay);
        initView();
        setRulesData();

    }

    private void getAllPrice() {
                /*获取所有的价格*/
        //如果现付  用Member 价格 如果是 预付 则用cost
        for (NightlyRates rates: ratePlan.getNightlyRates()){
            if (ratePlan.getPaymentType().equals("Prepay")){//预付
//                Log.e(TAG, "Prepay 普通价： "+rates.getCost());
                 allPrice = WebServUtil.add(allPrice,WebServUtil.parseDouble(rates.getCost()));
            }else if (ratePlan.getPaymentType().equals("SelfPay")){//支付方式为selfpay 但是cost 为 -1.0
//                Log.e(TAG, "SelfPay 会员价： "+rates.getMember());
                allPrice = WebServUtil.add(allPrice, WebServUtil.parseDouble(rates.getMember()));
             }else if (ratePlan.getPaymentType().equals("")){
                // Log.e(TAG, "其他散客价： "+rates.getCost());
                allPrice = WebServUtil.add(allPrice, WebServUtil.parseDouble(rates.getCost()));
             }
        }

    }

    private String readStream(InputStream is){
        String res;
        try
        {
            byte[] buf = new byte[is.available()];
            is.read(buf);
            res = new String(buf,"utf-8");
            is.close();
        } catch (Exception e)
        {
            res="";
        }
        return(res);
    }
    /*获取需要的常用数据*/
    private void getNeedData(){
        DHotel dHotel = searchResult.getdHotel();
        bookingRules = dHotel.getBookingRules();
        guaranteeRules =dHotel.getGuaranteeRules();
        prepayRules = dHotel.getPrepayRules();
        valueAddses = dHotel.getValueAddses();
    }

    /*设置规则数据*/
    private void setRulesData(){
        /*支付方式*/
        order_room_payment.setText(getPayStr());
        commitH.setPayType(getPayStr());
        /*附加服务*/
        order_room_isfood.setText(getFoodData(ratePlan.getValueAddIds()));
        commitH.setFood(getFoodData(ratePlan.getValueAddIds()));
        /*设置默认成本中心*/
        user_chenben_name.setText(MyApp.queryCostCenterResult.getCostCenterObjs().get(0).getCostCenterName());
        commitH.setCostCenter(MyApp.queryCostCenterResult.getCostCenterObjs().get(0).getCostCenterName());
       /*变更规则*/
        order_room_change_rule.setText(getChangeRule(ratePlan.getPrepayRuleIds()));
        commitH.setChange_rule(getChangeRule(ratePlan.getPrepayRuleIds()));
        /*设置默认时间*/
       commitH.setEnterTime(arriveTimes.get(1));
        /*预定规则条件*/
      String bookRuleId = ratePlan.getBookingRuleIds();
        if (null==bookRuleId||"".equals(bookRuleId)){
            room_order_rule_ly.setVisibility(View.GONE);
            r_line_1.setVisibility(View.GONE);
        }else {
            for (BookingRules rule:bookingRules){
                if (rule.getBookingRuleId().equals(bookRuleId)){
                    order_room_rules.setText(rule.getDescription());
                    TypeCode = rule.getTypeCode();
                    bookingRule = rule;
                    commitH.setBookingRule(bookingRule);
                }
            }
        }

          /*设置担保规则*/
        String guaranteeRuleId = ratePlan.getGuaranteeRuleIds();
        order_room_danbao_txt.setText(getDanBaoRule(guaranteeRuleId));
        commitH.setDanBaoRule(getDanBaoRule(guaranteeRuleId));




    }//----end---
/*担保规则*/
    private String getDanBaoRule(String guaranteeRuleId) {

        if (null==guaranteeRuleId||"".equals(guaranteeRuleId)){
            return ("不需要担保");
        }else{
            for (GuaranteeRule rule:guaranteeRules){//得到相应的的担保规则
                if (rule.getGuranteeRuleId().equals(guaranteeRuleId)){
                    //order_room_danbao_txt.setText(rule.getDescription());
                    guaranteeRule = rule;
                    commitH.setGuaranteeRule(guaranteeRule);
                    //强制担保
                    if (rule.isAmountGuarantee()==false&&rule.isTimeGuarantee()==false){
                        return("必须担保,"+getGuaranteeTypes(rule.getGuaranteeType()));
                        //房量担保，检查Amount
                    }else if (rule.isAmountGuarantee()==true&&rule.isTimeGuarantee()==false){
                        return ("预订"+rule.getAmount()+"间房以上需要担保"+getGuaranteeTypes(rule.getGuaranteeType()));

                        //到店时间担保，检查StartTime 和EndTime
                    }else if (rule.isAmountGuarantee()==false&&rule.isTimeGuarantee()==true){
                        if (TimeUntil.timeAsTime(rule.getStartTime(),rule.getEndTime())){
                            return (rule.getStartTime()+"至次日6点都需要担保"+getGuaranteeTypes(rule.getGuaranteeType()));
                        }
                    }else if (rule.isAmountGuarantee()==true&&rule.isTimeGuarantee()==true){
//                        if (rule.isAmountGuarantee()==true){
//                            return("预订"+rule.getAmount()+"间房以上需要担保,"+getGuaranteeTypes(rule.getGuaranteeType()));
//                        }else if (rule.isTimeGuarantee()==true){
//                            if (TimeUntil.timeAsTime(rule.getStartTime(),rule.getEndTime())){
//                                return (rule.getStartTime()+"至次日6点都需要担保");
//                            }
//                        }else if(rule.isAmountGuarantee()==true&&rule.isTimeGuarantee()==true){
                            String timeRule="" ;
                            if (TimeUntil.timeAsTime(rule.getStartTime(),rule.getEndTime())){
                                timeRule= rule.getStartTime()+"至次日6点都需要担保";
                            }
                            return ("需要担保,"+"预订"+rule.getAmount()+"间房以上需要担保;或"+timeRule+getGuaranteeTypes(rule.getGuaranteeType()));
//                        }


                    }


                }
            }
        }
        return "";
    }

    /*变更规则*/
    private String getChangeRule(String prepayRuleId) {
        if (null==prepayRuleId||"".equals(prepayRuleId)){
            return("允许变更取消");
        }else{
            for (PrepayRule rule: prepayRules){
                if (rule.getPrepayRuleId().equals(prepayRuleId)){
                     prepayRule = rule;
                    if (rule.getChangeRule().equals(TimeUntil.PREPAY_NOCHANGE)){
                       return ("不可取消");
                    }else if (rule.getChangeRule().equals(TimeUntil.PREPAY_NEEDSOME_DAY)){
                        return("限时变更取消,需在"+MyApp.gethEnterT()+"日"+"24点之前"+prepayRule.getHour()+"小时通知");
                    }else if (rule.getChangeRule().equals(TimeUntil.PREPAY_NEED_ONETIME)){
                        return ("限时变更取消,需在"+MyApp.gethEnterT()+"日"+rule.getTime()+"点前通知");
                    }
                }
            }

        }
        return "";
    }

    /*支付方式*/
    private String getPayStr() {
        if (ratePlan.getPaymentType().equals("Prepay")){
            return "预付";
        }else {
            return"前台现付";
        }
    }

    /*早餐*/
    private String getFoodData(String valueAddId) {
        if (null==valueAddId||"".equals(valueAddId)){
             return "(无早)";
        }else{
            for (ValueAdds value:valueAddses){
                if (value.getValueAddId().equals(valueAddId)){
                    if (value.getTypeCode().equals("01")) {//代表早餐
                        switch (value.getAmount()){
                            case 0:
                                return "(无早)";
                            case 1:
                                return "(单早)";
                            case 2:
                                return "(双早)";
                            case 3:
                                return "(三份以上早餐)";
                        }
                    }
                }
            }
        }
        return "";
    }


    private String getGuaranteeTypes(String type){
        if (type.equals(TimeUntil.FIRST_NIGHT_COST)){
            return "担保额度首晚房费";
        }else if (type.equals(TimeUntil.FULL_NIGHT_COST)){
            return "担保额度全额房费";
        }
        return  "";
    }
    private void initView() {
        room_order_rule_ly = (LinearLayout) findViewById(R.id.room_order_rule_ly);
        room_orderdanbao_rules = (LinearLayout) findViewById(R.id.room_orderdanbao_rules);
        order_room_rules = (TextView)findViewById(R.id.order_room_rules);
        order_must_know = (TextView)findViewById(R.id.order_must_know);
        order_room_payment = (TextView)findViewById(R.id.order_room_payment);
        order_room_isfood = (TextView)findViewById(R.id.order_room_isfood);
        order_room_danbao_txt = (TextView)findViewById(R.id.order_room_danbao_txt);
        r_line_1 = (TextView)findViewById(R.id.r_line_1);
        order_room_change_rule = (TextView)findViewById(R.id.order_room_change_rule);

        order_rule_lay = (LinearLayout) findViewById(R.id.order_rule_lay);
        chengben_rety = (RelativeLayout)findViewById(R.id.chengben_rety);
        order_room_need_rety = (RelativeLayout)findViewById(R.id.order_room_need_rety);
        chengben_rety.setOnClickListener(this);
        order_room_need_rety.setOnClickListener(this);

        user_contact_name = (EditText)findViewById(R.id.user_contact_name);
        user_contact_name.setText(MyApp.loginRegResult.getUserServerObject().getRealName());
        user_ponenumber_name = (EditText)findViewById(R.id.user_ponenumber_name);
        user_ponenumber_name.setText(MyApp.loginRegResult.getUserServerObject().getMobile());
        user_emil_address = (EditText)findViewById(R.id.user_emil_address);
        user_emil_address.setText(MyApp.loginRegResult.getUserServerObject().getMail());


        user_chenben_name = (TextView)findViewById(R.id.user_chenben_name);
        user_pjname_name = (EditText) findViewById(R.id.user_pjname_name);

        order_room_btn = (Button)findViewById(R.id.order_room_btn);
        order_room_btn.setOnClickListener(this);
        order_room_all_price = (TextView)findViewById(R.id.order_room_all_price);
        double allP = WebServUtil.convert(WebServUtil.parseDouble(ratePlan.getAverageRate())*check_room_num*TimeUntil.daysBetween(MyApp.gethEnterT(),MyApp.gethOutT()));
        order_room_all_price.setText(allPrice+"");
        order_room_arrivetime_txt = (TextView)findViewById(R.id.order_room_arrivetime_txt);
        app_back = (ImageView)findViewById(R.id.app_back_icon);
        order_room_need_img = (ImageView)findViewById(R.id.order_room_need_img);
        order_room_num_img = (ImageView)findViewById(R.id.order_room_num_img);
        order_room_num_txt = (TextView)findViewById(R.id.order_room_num_txt);
        order_room_listview = (MyListView)findViewById(R.id.order_room_listview);
        app_back.setOnClickListener(this);
        order_room_addperson = (RelativeLayout)findViewById(R.id.order_room_addperson);
        order_room_arrive_rety = (RelativeLayout)findViewById(R.id.order_room_arrive_rety);
        order_room_arrive_rety.setOnClickListener(this);
        order_room_addperson.setOnClickListener(this);
        g_line1 = (TextView)findViewById(R.id.g_line1);
        hotelname  = (TextView)findViewById(R.id.app_title_txt);
        order_room_name = (TextView)findViewById(R.id.order_room_name);
        order_room_date_num = (TextView)findViewById(R.id.order_room_date_num);
        order_room_content_isfood = (TextView)findViewById(R.id.order_room_content_isfood);
        order_room_gridview= (MyGridView)findViewById(R.id.order_room_gridview);
        order_room_gridview.setAdapter(new RoomNumAdapter(this,getResources().getStringArray(R.array.room_num_array)));
        order_room_num_rety = (RelativeLayout)findViewById(R.id.order_room_num_rety);
        order_room_num_rety.setOnClickListener(this);
        String content = "入住:"+ TimeUntil.getDayStr(MyApp.gethEnterT())
                +"\t"+"离店:"+TimeUntil.getDayStr(MyApp.gethOutT())+"\t"+"共"+
                TimeUntil.daysBetween(MyApp.gethEnterT(),MyApp.gethOutT())+"晚";
        commitH.setNumNight(TimeUntil.daysBetween(MyApp.gethEnterT(),MyApp.gethOutT()));
        order_room_date_num.setText(content);
        hotelname.setText(detail.getName());
        order_room_name.setText(hotelRoom.getName());
        order_room_content_isfood.setText(hotelRoom.getDescription());
        commitH.setRooms(check_room_num);
        /*房间数量选择*/
        order_room_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                check_room_num = WebServUtil.parseInt(getResources().getStringArray(R.array.room_num_array)[position]);
                order_room_num_txt.setText(check_room_num+"间");
                commitH.setRooms(check_room_num);

//                double allP = WebServUtil.convert(WebServUtil.parseDouble(ratePlan.getAverageRate())*check_room_num*TimeUntil.daysBetween(MyApp.gethEnterT(),MyApp.gethOutT()));
                  order_room_all_price.setText((allPrice*check_room_num)+"");
            }
        });

        setPersonAdapter();
    }

    private void setPersonAdapter() {
        selectPsgAdapter = new SelectPsgAdapter(this);
        selectPsgAdapter.setEdit(true);
        order_room_listview.setAdapter(selectPsgAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQUEST_PSG_SELECT && resultCode == SELECT_PSG_RESULT_CODE){
//            selectPsgAdapter.setDatas(MyApp.tempOrderObj.getTouristDOObjL());
//            ListViewSetup.setListViewHeightBasedOnSameRowHeightChildren(order_room_listview);
//            persions = selectPsgAdapter.getTouristL().size();
//            commitH.setB_touristDOObjs(selectPsgAdapter.getTouristL());
//        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.app_back_icon:
                finish();
                break;
            case R.id.order_room_num_rety:
              setRoomNum();
                break;
            case R.id.order_room_addperson://添加入住人
//                Intent intent = new Intent();
//                intent.setClass(this, PsgManagerT.class);
//                intent.putExtra(Constants.PSG_IS_SELECT, true);
//                startActivityForResult(intent, REQUEST_PSG_SELECT);
                break;
            case R.id.del_psg_btn://删除乘车人
                deletePso(v);
                break;
            case R.id.order_room_arrive_rety://到店时间
                shouPoPoTime();
                popupWindow.showAtLocation(order_room_btn, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.chengben_rety://成本中心
                showCostCenter();
                popupWindow.showAtLocation(order_room_btn, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.pj_rety://
                break;
            case R.id.order_room_need_rety://点击显示或者隐藏预订须知
                setRuleIsV();
                break;
            case R.id.order_room_btn://点击下一步
                if (orderCommit()){
                    commitH.setProjectname(user_pjname_name.getText().toString());
                    commitH.setOrderP(order_room_all_price.getText().toString());
                    commitH.setUser_name(user_contact_name.getText().toString());
                    commitH.setPhone_number(user_ponenumber_name.getText().toString());
                    commitH.setEmail(user_emil_address.getText().toString());
                    Intent in = new Intent(HotelOrderDetailT.this,CommitHotelT.class);
                    MyApp.commitHotel = commitH;
                    startActivity(in);

                }
                break;

        }
        super.onClick(v);
    }
    /*提交订单验证*/
    private boolean orderCommit() {
       return isBookRule();
    }
    /*预定规则判断*/
    private boolean isBookRule(){
        String phone = user_ponenumber_name.getText().toString();
        //过滤预定规则
        if (persions < check_room_num){
            AndroidUtil.shortToast(this, "还需添加"+(check_room_num-persions)+"人");
            return false;
        }else if (("".equals(phone)) || (phone.length() < 11)){
            AndroidUtil.shortToast(this, "请输入正确的手机号");
            return false;
        }else{
           if (null==TypeCode||"".equals(TypeCode)) {
               return true;
           }else{
               switch (TypeCode) {
                   case TimeUntil.NEED_CONTRY:
                       break;
                   case TimeUntil.PERSON_NAME:
                       if (persions < check_room_num) {
                           AndroidUtil.shortToast(this, "还需添加" + (check_room_num - persions) + "人");
                           return false;
                         }
                       break;
                   case TimeUntil.PERSON_ENNAME:
                       break;
                   case TimeUntil.CHECKIN_TIEM:
                       break;
                   case TimeUntil.CONT_ORDER_HOTEL://当TypeCode 为4时表示StartHour到EndHour 酒店不接受预订
                       if (TypeCode.equals(TimeUntil.CONT_ORDER_HOTEL)){
                           AndroidUtil.shortToast(this,"酒店"+bookingRule.getStartHour()+"至"+bookingRule.getEndHour()+"不接受预订");
                           return false;
                       }
                       break;
               }
           }
        }
        return true;
    }



    /*预订须知显示与否*/
    private void setRuleIsV() {
        if (isRule){
            order_rule_lay.setVisibility(View.GONE);
            order_room_need_img.setBackgroundDrawable(getResources().getDrawable(R.mipmap.room_fangxia));
            order_must_know.setText("展开预订须知");
            isRule=false;
        }else {
            order_rule_lay.setVisibility(View.VISIBLE);
            order_room_need_img.setBackgroundDrawable(getResources().getDrawable(R.mipmap.room_shouqi));
            order_must_know.setText("收起预订须知");
            isRule=true;
        }
    }

    /*房间num显示*/
    private void setRoomNum() {
        if(isCheck){
            order_room_gridview.setVisibility(View.VISIBLE);
            g_line1.setVisibility(View.GONE);
            order_room_num_img.setBackgroundDrawable(getResources().getDrawable(R.mipmap.room_shouqi));
            isCheck=false;
        }else{
            order_room_gridview.setVisibility(View.GONE);
            g_line1.setVisibility(View.VISIBLE);
            order_room_num_img.setBackgroundDrawable(getResources().getDrawable(R.mipmap.room_fangxia));
            isCheck=true;
        }
    }

    /*显示成本中心*/
    private void showCostCenter(){
        popView = LayoutInflater.from(this).inflate(R.layout.cost_center_popo,null);
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(this.getResources()));
        popupWindow.setOutsideTouchable(true);
        ListView pop_listview = (ListView) popView.findViewById(R.id.cost_center_listview);
        pop_listview.setAdapter(new CostCenterAdapter(this, MyApp.queryCostCenterResult.getCostCenterObjs()));
        pop_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                user_chenben_name.setText(MyApp.queryCostCenterResult.getCostCenterObjs().get(position).getCostCenterName());
                commitH.setCostCenter(MyApp.queryCostCenterResult.getCostCenterObjs().get(position).getCostCenterName());
                popupWindow.dismiss();
            }
        });
        TextView room_arrive_dismiss =(TextView) popView.findViewById(R.id.room_arrive_dismiss);
        room_arrive_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }
    /*显示时间的popo*/
    private void shouPoPoTime(){
        popView = LayoutInflater.from(this).inflate(R.layout.arrive_hotel_popo,null);
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(this.getResources()));
        popupWindow.setOutsideTouchable(true);
        GridView gridView = (GridView) popView.findViewById(R.id.room_arrive_gridview);

        gridView.setAdapter(new ArrvieTimeAdapter(this,arriveTimes));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                order_room_arrivetime_txt.setText(arriveTimes.get(position).getTime());
                commitH.setEnterTime(arriveTimes.get(position));
                popupWindow.dismiss();
            }
        });
        TextView room_arrive_dismiss =(TextView) popView.findViewById(R.id.room_arrive_dismiss);
        room_arrive_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

     }

    /*删除已选的住店人*/
    private void deletePso(View v) {
        final B_TouristDOObj touristDOObj = (B_TouristDOObj) v.getTag();
         AndroidUtil.confrim(this, null, "你确认要删除旅客" + touristDOObj.getTouristName() +"?", new Runnable() {
            @Override
            public void run() {
                for(B_TouristDOObj each: selectPsgAdapter.getTouristL()){
                    if(each.getTouristID() == touristDOObj.getTouristID()){
                        selectPsgAdapter.getTouristL().remove(each);
                        break;
                    }
                }
                selectPsgAdapter.notifyDataSetChanged();
                ListViewSetup.setListViewHeightBasedOnSameRowHeightChildren(order_room_listview);
                persions = selectPsgAdapter.getTouristL().size();
                //更新联系人
                commitH.setB_touristDOObjs(selectPsgAdapter.getTouristL());
            }

        });

    }


}
