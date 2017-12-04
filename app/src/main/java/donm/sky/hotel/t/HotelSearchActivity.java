package donm.sky.hotel.t;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.AppT;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;
import com.myuntils.MyAllUntils;
import com.myviews.CaldroidActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import donm.sky.hotel.adapter.HotelLevelAdapter;
import donm.sky.hotel.model.Level;
import donm.sky.hotel.until.TimeUntil;
import f.sky.flight.core.Constants;

/**
 * Created by Administrator on 2016/11/21/021.
 * 酒店查询界面
 */

public class HotelSearchActivity extends AppT implements CompoundButton.OnCheckedChangeListener{

    private TextView hotel_destination_txt;//目的地城市
    private TextView hotel_checkin_date;//入住日期
    private TextView hotel_checkout_date;//离店日期
    private EditText hotel_edit_name;//按酒店名进行查询
    private TextView hotel_edit_price;//价格星级查询
    private ImageView app_back;
    private TextView app_title_txt;

    private PopupWindow popupWindow;
    private View popView,parent;
    private List<Level> level;
    private List<String> prices;
    private HotelLevelAdapter htLadapter;
    private  CheckBox buxian,kjiels,sanxJi,sanxinShs,sixinGd,wxinHh;
    private RadioButton rd1,rd2,rd3,rd4,rd5,rd6,rd7;
    private String check_hotel_price;
    private String strBuxin,strKj,strSanj,strSanS,strSig,strWxh;
    private String strLevel;
    private String builderStar = "";
    private long seleteTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.hotel_search_layout);
       initView();
        level = new ArrayList<>();
        htLadapter = new HotelLevelAdapter(this,level);
        prices = new ArrayList<>();
        Collections.addAll(prices,getResources().getStringArray(R.array.hotel_array_price));

    }

    /**
     * 组件初始化
     */
    private void initView() {
        hotel_destination_txt =(TextView) this.findViewById(R.id.hotel_destination_txt);
        hotel_destination_txt.setText(MyApp.getH_city().getName());
        hotel_checkin_date =(TextView) this.findViewById(R.id.hotel_checkin_date);
        hotel_checkin_date.setText(MyAllUntils.getNowDate());
        //设置入住日期
        MyApp.sethEnterT(MyAllUntils.getNowDateF());
        hotel_checkout_date =(TextView) this.findViewById(R.id.hotel_checkout_date);
        hotel_checkout_date.setText(MyAllUntils.getNextDate());
        MyApp.sethOutT(MyAllUntils.getNextDateF());
        hotel_edit_name = (EditText) this.findViewById(R.id.hotel_edit_name);
        hotel_edit_price = (TextView)this.findViewById(R.id.hotel_edit_price);
        app_back =(ImageView) this.findViewById(R.id.app_back_icon);
        app_title_txt = (TextView) this.findViewById(R.id.app_title_txt);
        app_title_txt.setText("酒店查询");
        app_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化价格筛选popo
     */
    private void initPricePopo(){
        popView = getLayoutInflater().inflate(R.layout.hotel_price_other_layout,null);
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources()));
        popupWindow.setOutsideTouchable(true);
        parent = this.findViewById(R.id.hotel_layout);
        Button hotel_pop_btn = (Button) popView.findViewById(R.id.hotel_pop_btn);
        buxian= (CheckBox)popView.findViewById(R.id.box_buxian);
        kjiels = (CheckBox)popView.findViewById(R.id.box_kuaijie_hotel);
         sanxJi = (CheckBox)popView.findViewById(R.id.box_jinji_hotel);
        sanxinShs = (CheckBox)popView.findViewById(R.id.box_sanxin_hotel);
         sixinGd = (CheckBox)popView.findViewById(R.id.box_sixing_hotel);
        wxinHh = (CheckBox)popView.findViewById(R.id.box_wuxing_hotel);

        buxian.setOnCheckedChangeListener(this);
        kjiels.setOnCheckedChangeListener(this);
        sanxJi.setOnCheckedChangeListener(this);
        sanxinShs.setOnCheckedChangeListener(this);
        sixinGd.setOnCheckedChangeListener(this);
        wxinHh.setOnCheckedChangeListener(this);

        rd1 = (RadioButton)popView.findViewById(R.id.radio_buxian) ;
        rd1.setOnCheckedChangeListener(this);
        rd2 = (RadioButton)popView.findViewById(R.id.radio_150_end) ;
        rd2.setOnCheckedChangeListener(this);
        rd3 = (RadioButton)popView.findViewById(R.id.radio_300_end) ;
        rd3.setOnCheckedChangeListener(this);
        rd4 = (RadioButton)popView.findViewById(R.id.radio_450_end) ;
        rd4.setOnCheckedChangeListener(this);

        rd5 = (RadioButton)popView.findViewById(R.id.radio_600_end) ;
        rd5.setOnCheckedChangeListener(this);
        rd6 = (RadioButton)popView.findViewById(R.id.radio_1000_end) ;
        rd6.setOnCheckedChangeListener(this);
        rd7 = (RadioButton)popView.findViewById(R.id.radio_10001_end) ;
        rd7.setOnCheckedChangeListener(this);
        check_hotel_price = "不限";
        strBuxin = "不限";
        strLevel = "";
        strKj ="";
        strSanj ="";
        strSanS = "";
        strSig = "";
        strWxh = "";
        hotel_pop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strLevel= strBuxin+strKj+strSanj+strSanS+strSig+strWxh;
                if (!strBuxin.equals("")){
                    builderStar = "";
                }else{
                    if (!strKj.equals("")){
                        builderStar = builderStar+"A,";
                    }
                    if (!strSanj.equals("")) {
                        builderStar = builderStar + "0,1,2,";
                    }
                    if (!strSanS.equals("")){
                        builderStar = builderStar +"3,";
                    }
                    if (!strSig.equals("")){
                        builderStar = builderStar +"4,";
                    }
                    if (!strWxh.equals("")){
                        builderStar = builderStar +"5,";
                    }
                }

                switch (check_hotel_price){
                    case "不限":
                        MyApp.LowRate = 0;
                        MyApp.HighRate= 0;
                        break;
                    case "￥150以下":
                        MyApp.LowRate = 0;
                        MyApp.HighRate= 150;
                        break;
                    case "￥150-300":
                        MyApp.LowRate = 150;
                        MyApp.HighRate= 300;
                        break;
                    case "￥301-500":
                        MyApp.LowRate = 300;
                        MyApp.HighRate= 500;
                        break;
                    case "￥501-800":
                        MyApp.LowRate = 500;
                        MyApp.HighRate= 800;
                        break;
                    case "￥801-1000":
                        MyApp.LowRate = 800;
                        MyApp.HighRate= 1000;
                        break;
                    case "￥1000以上":
                        MyApp.LowRate = 1000;
                        MyApp.HighRate= 10000;
                        break;
                }
                 MyApp.strLevel = builderStar;
                hotel_edit_price.setText(check_hotel_price+"/"+strLevel);
                popupWindow.dismiss();
                strLevel="";

            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.box_buxian:
                setOther(isChecked);
                if (isChecked){
                    strBuxin = "不限";
                }else {
                    strBuxin = "";
                }

                break;
            case R.id.box_kuaijie_hotel://快捷连锁
                if (isChecked){
                    buxian.setChecked(false);
                    strKj = "公寓/其他";
                }else {
                     strKj = "";
                }
                break;
            case R.id.box_jinji_hotel://三星、经济
                if (isChecked){
                    buxian.setChecked(false);
                    strSanj = "经济客栈";
                }else {
                     strSanj = "";
                }
                break;
            case R.id.box_sanxin_hotel://三星舒适
                if (isChecked){
                    buxian.setChecked(false);
                    strSanS = "三星舒适";
                }else {

                    strSanS = "";
                }
                break;
            case R.id.box_sixing_hotel://四星高档
                if (isChecked){
                    buxian.setChecked(false);
                    strSig = "四星高档";
                }else {
                    strSig = "";
                }
                break;
            case R.id.box_wuxing_hotel://五星豪华
                if (isChecked){
                    buxian.setChecked(false);
                    strWxh = "五星豪华";
                }else {
                    strWxh = "";
                }
                break;
            //以下是radiobutton
            case R.id.radio_buxian:
                setF(isChecked,1);
                check_hotel_price = rd1.getText().toString();
                break;
            case R.id.radio_150_end://150以下
                setF(isChecked,2);
                check_hotel_price = rd2.getText().toString();
                break;
            case R.id.radio_300_end://150-300
                setF(isChecked,3);
                check_hotel_price = rd3.getText().toString();
                break;
            case R.id.radio_450_end://301-500
                setF(isChecked,4);
                check_hotel_price = rd4.getText().toString();
                break;
            //-------------------
            case R.id.radio_600_end://501-800
                setF(isChecked,5);
                check_hotel_price = rd5.getText().toString();
                break;
            case R.id.radio_1000_end://￥801-1000
                setF(isChecked,6);
                check_hotel_price = rd6.getText().toString();
                break;
            case R.id.radio_10001_end://1000以上
                setF(isChecked,7);
                check_hotel_price = rd7.getText().toString();
                break;

        }
    }
    private void setOther(boolean isChecked){
        if (isChecked){
            kjiels.setChecked(false);
            sanxJi.setChecked(false);
            sanxinShs.setChecked(false);
            sixinGd.setChecked(false);
            wxinHh.setChecked(false);
        }
    }
    private void setF(boolean isC,int i){
        if (isC){
            if (i==1){
                rd1.setChecked(true);
                rd2.setChecked(false);
                rd3.setChecked(false);
                rd4.setChecked(false);
                rd5.setChecked(false);
                rd6.setChecked(false);
                rd7.setChecked(false);
            } else if (i==2){
                rd2.setChecked(true);
                rd1.setChecked(false);
                rd3.setChecked(false);
                rd4.setChecked(false);
                rd5.setChecked(false);
                rd6.setChecked(false);
                rd7.setChecked(false);
            }else if (i==3){
                rd3.setChecked(true);
                rd1.setChecked(false);
                rd2.setChecked(false);
                rd4.setChecked(false);
                rd5.setChecked(false);
                rd6.setChecked(false);
                rd7.setChecked(false);
            }else if (i==4){
                rd4.setChecked(true);
                rd1.setChecked(false);
                rd2.setChecked(false);
                rd3.setChecked(false);
                rd5.setChecked(false);
                rd6.setChecked(false);
                rd7.setChecked(false);
            }else if (i==5){
                rd5.setChecked(true);
                rd1.setChecked(false);
                rd2.setChecked(false);
                rd3.setChecked(false);
                rd4.setChecked(false);
                rd6.setChecked(false);
                rd7.setChecked(false);
            }else if (i==6){
                rd6.setChecked(true);
                rd1.setChecked(false);
                rd2.setChecked(false);
                rd3.setChecked(false);
                rd4.setChecked(false);
                rd5.setChecked(false);
                rd7.setChecked(false);
            }else if (i==7){
                rd7.setChecked(true);
                rd1.setChecked(false);
                rd2.setChecked(false);
                rd3.setChecked(false);
                rd4.setChecked(false);
                rd5.setChecked(false);
                rd6.setChecked(false);
            }
        }

    }

    /**
     * popo动画触发
     */
    private void popoAnim(){
        //为popWindow添加动画效果
         popupWindow.setAnimationStyle(R.style.popWindow_animation);
        // 点击弹出泡泡窗口
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 酒店查询
     * @param v
     */
    public  void hotelSearch(View v){
        MyApp.QueryText=hotel_edit_name.getText().toString();
        MyAllUntils.open(HotelSearchActivity.this,HotelListT.class);
    }

    /**
     * 目的地
     * @param v
     */
    public  void wanttoCitiyClick(View v){
        MyAllUntils.open(HotelSearchActivity.this,HotelCityT.class);
        finish();
    }
    /**
     * 入住日期
     * @param v
     */
    public  void checkInDateClick(View v){

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.SELECT_TIME, seleteTime);
        intent.putExtras(bundle);
        intent.setClass(HotelSearchActivity.this, CaldroidActivity.class);
        startActivityForResult(intent,10);
    }
    /**
     * 离店日期
     * @param v
     */
    public  void checkOutDateClick(View v){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.SELECT_TIME, seleteTime);
        intent.putExtras(bundle);
        intent.setClass(HotelSearchActivity.this, CaldroidActivity.class);
        startActivityForResult(intent,20);
    }

    /**
     * 就点名筛选
     * @param v
     */
    public  void hotelEditNameClick(View v){

    }
    /**
     * 星级价格筛选
     * @param v
     */
    public  void hotelPriceOnclick(View v){
        initPricePopo();
        popoAnim();
        builderStar = "";
        MyApp.strLevel = "";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10){//入住日期
            if (resultCode==Constants.CHOSE_T){
                {
                    seleteTime = data.getLongExtra(Constants.SELECT_DATA_TIME, 0);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = new Date(seleteTime);
                    String t1 = format.format(d1);
                    if (seleteTime > 0) {
                        reflshDate(t1,1);
                    } else {
                        return;
                    }
                }
            }else if (requestCode == Constants.NO_SELECT_T){
                return;
            }
        }else if (requestCode==20){//离店日期
            if (resultCode==Constants.CHOSE_T){
                {
                    seleteTime = data.getLongExtra(Constants.SELECT_DATA_TIME, 0);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = new Date(seleteTime);
                    String t1 = format.format(d1);
                    if (seleteTime > 0) {
                        reflshDate(t1,-1);
                    } else {
                        return;
                    }
                }
            }else if (requestCode == Constants.NO_SELECT_T){
                return;
            }
        }



    }

    /**
     * 计算日期是否正确的函数
     * @param str
     * @param addOrloer
     */
    private void reflshDate(String str,int addOrloer) {
          if (addOrloer==1){//选择入住日期
               if (TimeUntil.daysBetween(str,MyApp.gethOutT())>0){
                   MyApp.sethEnterT(str);
                   hotel_checkin_date.setText(TimeUntil.dateChangeToString(str));
                   return;
                }
              MyApp.sethEnterT(str);
              MyApp.sethOutT(TimeUntil.addOrLoer(str,addOrloer));
              hotel_checkin_date.setText(TimeUntil.dateChangeToString(str));
              hotel_checkout_date.setText(TimeUntil.dateChangeToString(TimeUntil.addOrLoer(str,1)));
           }else if (addOrloer==-1){
                if (TimeUntil.daysBetween(MyApp.gethEnterT(),str)<=0){
                    AndroidUtil.shortToast(this,"离店日期有误");
                     MyApp.sethOutT(MyApp.gethOutT());
//                    Log.e("选择的离店日期：", "+ "+str +"\t"+MyApp.gethOutT() );
                     hotel_checkout_date.setText((TimeUntil.dateChangeToString(MyApp.gethOutT())));
                 }else {
                    hotel_checkout_date.setText(TimeUntil.dateChangeToString(str));
                    MyApp.sethOutT(str);
                }

          }
    }


}
