package donm.sky.hotel.t;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dmonline.R;
import com.mytables.AppT;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;
import com.myviews.MyListView;

import java.util.ArrayList;
import java.util.List;

import donm.sky.hotel.adapter.HorderGuestAdapter;
import donm.sky.hotel.htservice.QuerOrderList;
import donm.sky.hotel.model.HotelDataList;
import donm.sky.hotel.model.QueryOrderHotelResult;
import donm.sky.hotel.until.HotelUntils;
import donm.sky.hotel.until.TimeUntil;
import f.sky.flight.core.Constants;

import static donm.sky.hotel.until.HotelUntils.callPhone;
import static donm.sky.hotel.until.HotelUntils.getBreakHasfast;
import static donm.sky.hotel.until.HotelUntils.getNet;
import static donm.sky.hotel.until.HotelUntils.getParseNetPrice;
import static donm.sky.hotel.until.HotelUntils.getPayMent;

/**
 * Created by Administrator on 2017/5/9/009.
 * 酒店预订成功后的界面
 */

public class OrderSucessT extends AppT  implements SwipeRefreshLayout.OnRefreshListener{


    private NestedScrollView mScrollView;
    private SwipeRefreshLayout main_pull_refresh_view;
    private MyListView H_order_hotel_persons;
    protected String  whichPager;
    protected HotelDataList orderData;
    private ImageView app_back;
    private TextView app_title;
    private TextView H_order_status,H_order_status_dt;//订单状态
    private TextView H_order_payType,H_order_price,H_order_hotelname,H_order_address,H_order_call_hotel,H_order_location;
    private TextView H_order_roomType,H_order_enter_time,H_order_night_num_time,H_order_breakfast_wf;
    private TextView H_order_contact_name,H_order_phone_number,H_order_emil_address,H_order_userCenter_name,H_order_project_number;
    protected final  int  FLASH_HOTEL_STATUS = 1;
    protected final  int  DETAIL_PAGER_FLSH = 2;
    private int ApplyId = 0;
    private int OrderId = 0;
    private PopupWindow popupWindow;
    private View popView;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.order_sucess_lay);
        Intent intent = getIntent();
        whichPager = intent.getStringExtra("H_ORDER");
        mScrollView =(NestedScrollView) this.findViewById(R.id.my_sc);
        main_pull_refresh_view = (SwipeRefreshLayout)findViewById(R.id.main_pull_refresh_view);
        main_pull_refresh_view.setOnRefreshListener(this);
        main_pull_refresh_view.setColorSchemeResources(R.color.green,R.color.chengse);
        initView();
    }
/*初始化所有的UI*/
    private void initView() {
        app_back =(ImageView)findViewById(R.id.app_back_icon);
        app_back.setOnClickListener(this);
        app_title = (TextView)findViewById(R.id.app_title_txt);
        app_title.setText("订单详情");
        H_order_status = (TextView)findViewById(R.id.H_order_status);
        H_order_status_dt = (TextView)findViewById(R.id.H_order_status_dt);
        H_order_payType = (TextView)findViewById(R.id.H_order_payType);
        H_order_price = (TextView)findViewById(R.id.H_order_price);
        H_order_hotelname = (TextView)findViewById(R.id.H_order_hotelname);
        H_order_address = (TextView)findViewById(R.id.H_order_address);

        H_order_call_hotel = (TextView)findViewById(R.id.H_order_call_hotel);
        H_order_call_hotel.setOnClickListener(this);
        H_order_location = (TextView)findViewById(R.id.H_order_location);
        H_order_location.setOnClickListener(this);

        H_order_roomType = (TextView)findViewById(R.id.H_order_roomType);
        H_order_enter_time = (TextView)findViewById(R.id.H_order_enter_time);
        H_order_night_num_time = (TextView)findViewById(R.id.H_order_night_num_time);
        H_order_breakfast_wf = (TextView)findViewById(R.id.H_order_breakfast_wf);
        H_order_contact_name = (TextView)findViewById(R.id.H_order_contact_name);
        H_order_phone_number = (TextView)findViewById(R.id.H_order_phone_number);
        H_order_emil_address = (TextView)findViewById(R.id.H_order_emil_address);
        H_order_userCenter_name = (TextView)findViewById(R.id.H_order_userCenter_name);
        H_order_project_number = (TextView)findViewById(R.id.H_order_project_number);
        H_order_hotel_persons = (MyListView)findViewById(R.id.H_order_hotel_persons);
       if (whichPager.equals("MyOrderT")){
           orderData = MyApp.hotelDataList;
           OrderId =Integer.parseInt(orderData.getOrderid());
           setStaticData();
       }else{
           ApplyId = Integer.parseInt(whichPager);
           executeWeb(FLASH_HOTEL_STATUS,"");
       }

    }
    /*设置listView传送的数据*/
    private void setStaticData() {
        H_order_payType.setText(getPayMent(orderData.getPayment()));
        H_order_price.setText(getParseNetPrice(orderData.getFkHorderfuReferenceHorder().getNetPrice()));
        H_order_hotelname.setText(orderData.getHotelName());
        H_order_address.setText(orderData.getHotelAddress());
        H_order_roomType.setText(orderData.getRoomTypeName());
        //入住时间
        H_order_enter_time.setText(TimeUntil.getDayStr(orderData.getCheckInDate())+" - "+TimeUntil.getDayStr(orderData.getCheckOutDate()));
        String night_num = orderData.getNumberOfDate()+"晚"+"\t"+orderData.getNumberOfUnits()+"间";
        String lastArrive = orderData.getLaterArrivalTime().substring(11,16);
        H_order_night_num_time.setText(night_num+"最晚到店"+lastArrive);
        H_order_breakfast_wf.setText(getBreakHasfast(orderData.getBreakfast())+"\t|\t"+getNet(orderData.getNet()));
        H_order_contact_name.setText(orderData.getHorderContact().getContactPersonName());
        H_order_phone_number.setText(orderData.getHorderContact().getPhoneNumber());
        H_order_emil_address.setText(orderData.getHorderContact().getEmail());
        H_order_userCenter_name.setText(orderData.getCostCenter());
        H_order_project_number.setText(orderData.getCostNumber());
        H_order_hotel_persons.setAdapter(new HorderGuestAdapter(this,orderData.getHorderGuest()) );

        H_order_status_dt.setText(HotelUntils.getOrderDetailStatus(orderData.getStatus()));
        H_order_status.setText(HotelUntils.orderStatus_1(orderData.getStatus()));

    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag==FLASH_HOTEL_STATUS){//行程页面刷新
            int bookId =  MyApp.loginRegResult.getUserServerObject().getId();
            int clientId = MyApp.loginRegResult.getUserServerObject().getUserOrgObj().getClientID();
            return QuerOrderList.queryOrderHotel(bookId,clientId, ApplyId,OrderId);

        }else if (flag==DETAIL_PAGER_FLSH){//详情页面刷新
            int bookId =  MyApp.loginRegResult.getUserServerObject().getId();
            int clientId = MyApp.loginRegResult.getUserServerObject().getUserOrgObj().getClientID();
            return QuerOrderList.queryOrderHotel(bookId,clientId, ApplyId,OrderId);
        }
        return super.doTask(flag, params);

    }

    @Override
    public void taskDone(int flag, Object result) {
        super.taskDone(flag, result);
        if (flag==FLASH_HOTEL_STATUS){//从预订页面跳转时查询数据
            if (null==result){
                AndroidUtil.shortToast(this,"订单刷新失败,请返回订单列表查看");
            }else{
                QueryOrderHotelResult queryOrderHotelResult = (QueryOrderHotelResult) result;
                orderData  = queryOrderHotelResult.getHotelDataLists().get(0);
                setStaticData();
            }

        }else if (flag==DETAIL_PAGER_FLSH){//con订单列表跳转是刷新
            if (null==result){
                AndroidUtil.shortToast(this,"订单查询失败");
            }else{
                QueryOrderHotelResult queryOrderHotelResult = (QueryOrderHotelResult) result;
                orderData  = queryOrderHotelResult.getHotelDataLists().get(0);
                setStaticData();
            }
        }
        main_pull_refresh_view.setRefreshing(false);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.app_back_icon:
                finish();
                break;
            case R.id.H_order_call_hotel://联系酒店
                callPhone(this,orderData.getHotelPhone());
                break;
            case R.id.H_order_location://地图导航
                daohang();
                popupWindow.showAtLocation(H_order_location, Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    @Override
    public void onRefresh() {
        main_pull_refresh_view.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (whichPager.equals("MyOrderT")){
                    Log.e("行程---", "run--01---: " );
                     executeWeb(FLASH_HOTEL_STATUS,"");
                }else{
                    Log.e("详情 ---", "run--02---: " );
                    executeWeb(DETAIL_PAGER_FLSH,"");
                 }
            }
        }, 2000);
    }




    //吊起地图导航
    private void daohang() {
        popView = LayoutInflater.from(this).inflate(R.layout.baidu_gaode_popo,null);
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.setFocusable(true);
        LinearLayout baidu_layout = (LinearLayout) popView.findViewById(R.id.baidu_layout);
        LinearLayout gaode_layout = (LinearLayout) popView.findViewById(R.id.gaode_layout);
        LinearLayout pop_item_lay = (LinearLayout)popView.findViewById(R.id.pop_item_lay);

        TextView baidu_txt = (TextView)popView.findViewById(R.id.baidu_txt);
        TextView gaode_txt = (TextView)popView.findViewById(R.id.gaode_txt);
        if (isAvilible(OrderSucessT.this,"com.baidu.BaiduMap")){
            baidu_txt.setText("百度地图导航(已安装)");
        }else {
            baidu_txt.setText("百度地图导航(未安装)");
        }
        if (isAvilible(OrderSucessT.this,"com.autonavi.minimap")){
            gaode_txt.setText("高德地图导航(已安装)");
        }else {
            gaode_txt.setText("高德地图导航(未安装)");
        }
        pop_item_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        baidu_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAvilible(OrderSucessT.this,"com.baidu.BaiduMap")){
                    startNative_Baidu();

                }
                popupWindow.dismiss();
            }
        });
        gaode_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAvilible(OrderSucessT.this,"com.autonavi.minimap")){
                    startNative_Gaode();
                }
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 开发 > URI API > Android
     * 跳转到百度地图
     *
     * @param
     * @param
     * @param
     */
    public  void startNative_Baidu() {
        try {
            Intent intent = Intent.getIntent("intent://map/direction?destination=" + orderData.getHotelAddress() +
                    "&mode=transit&src=#Intent;" + "scheme=bdapp;package=com.baidu.BaiduMap;end");
            this.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "地址解析错误", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 开发 > URI API > Android
     * 调起高德地图
     *     * @param context
     * @param
     */
    public  void startNative_Gaode() {
        try {
            //地理编码
            Intent inten1 = new Intent("android.intent.action.VIEW"
                    , android.net.Uri.parse("androidamap://viewGeo?sourceApplication=&addr=" +orderData.getHotelAddress()));
            inten1.setPackage("com.autonavi.minimap");//
            inten1.addCategory("android.intent.category.DEFAULT");
            this.startActivity(inten1);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "地址解析错误", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }

}
