package donm.sky.hotel.t;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.dmonline.R;
import com.mytables.AppT;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;
import com.myviews.MyListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import donm.sky.hotel.adapter.HotelRoomAdapter;
import donm.sky.hotel.adapter.HotelRoomPriceAdapter;
import donm.sky.hotel.adapter.PosterAdapter;
import donm.sky.hotel.htservice.HotelDetailSer;
import donm.sky.hotel.model.HotelDetail;
import donm.sky.hotel.model.HotelDetailSearchResult;
import donm.sky.hotel.model.HotelImageLocation;
import donm.sky.hotel.model.HotelInfoSearchResult;
import donm.sky.hotel.model.HotelRoom;
import donm.sky.hotel.model.PosterControl;
import donm.sky.hotel.model.RatePlans;
import donm.sky.hotel.model.Room;

import static com.mytables.MyApp.hotelDetailSearchResult;

/**
 * Created by Administrator on 2016/12/27/027.
 * 酒店详情界面
 */

public class HDetailT extends AppT {
    private TextView hotel_name_txt,hotel_pingfen_txt,hotel_kaiye_txt,hotel_address_txt;
    private TextView h_arr_date,h_out_date;
    private RelativeLayout h_map_location,app_back_rety;
    private LinearLayout linear_pointers,hotel_viewpager_container;
    private ViewPager viewPager;
    private List<String> imgs;
    private int hotelID ;
    private PopupWindow popupWindow;
    private View popView;
    private ScrollView detail_scrollview;
    private PosterControl posterControl;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private List<HotelInfoSearchResult> infos;
    private MyListView room_listview;
    private HotelRoomAdapter roomAdapter;
    private List<HotelImageLocation> locations;
    List<HotelDetail> details;
    List<HotelRoom> rooms;
    private static  final int DETAIL_SEARCH = 1;
    private HotelDetail detail;
    private int hotelCode;
    private List<Room> rateRooms;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        Intent intent = getIntent();
        detail = (HotelDetail) intent.getSerializableExtra("detail");
        MyApp.setHotelDetail(detail);
        hotelID  = detail.getHotelID();
        hotelCode= detail.getCode();
        infos = MyApp.getHotelInfoSearchResult();
        executeWeb(DETAIL_SEARCH,"数据查询中...");
        setContentView(R.layout.hotel_detail_layout);
        initView();
    }

    private void initView() {
        detail_scrollview = (ScrollView)findViewById(R.id.detail_scrollview);
        hotel_name_txt = (TextView)findViewById(R.id.hotel_name_txt);
        hotel_pingfen_txt = (TextView)findViewById(R.id.hotel_pingfen_txt);
        hotel_kaiye_txt = (TextView)findViewById(R.id.hotel_kaiye_txt);
        hotel_address_txt = (TextView)findViewById(R.id.hotel_address_txt);
        h_arr_date = (TextView)findViewById(R.id.h_arr_date);
        h_out_date = (TextView)findViewById(R.id.h_out_date);
        h_arr_date.setText(MyApp.gethEnterT());
        h_out_date.setText(MyApp.gethOutT());
        room_listview = (MyListView)findViewById(R.id.hotel_son_listview);
        room_listview.setFocusable(false);
        //地图导航
        h_map_location =(RelativeLayout)findViewById(R.id.h_map_location);
        app_back_rety = (RelativeLayout)findViewById(R.id.app_back_rety);
        app_back_rety.setOnClickListener(this);
        h_map_location.setOnClickListener(this);

        locations = new ArrayList<>();
        details= new ArrayList<>();
        rooms = new ArrayList<>();

    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag==DETAIL_SEARCH){
            int clientId= MyApp.loginRegResult.getUserServerObject().getUserOrgObj().getClientID();
            return HotelDetailSer.getHotelDetail(MyApp.gethEnterT(),MyApp.gethOutT(),hotelCode+"",clientId);
        }
        return super.doTask(flag, params);
    }

    @Override
    public void taskDone(int flag, Object result) {
        if (flag==DETAIL_SEARCH){
            if (result==null){
                AndroidUtil.alert(HDetailT.this,"查询失败！请重新查询");
                return;
            }
            hotelDetailSearchResult = (HotelDetailSearchResult) result;
            if ( hotelDetailSearchResult.isSuccess()){
                detail_scrollview.setVisibility(View.VISIBLE);
                rateRooms = MyApp.hotelDetailSearchResult.getdHotel().getRooms();
                for (int i = 0; i <infos.size() ; i++) {
                    details.addAll(infos.get(i).getDetails());
                    rooms.addAll(infos.get(i).getRooms());
                    locations.addAll(infos.get(i).getImgLocations());
                 }

                setPager();
                setMyDatas();
                /**ListView点击事件**/
                room_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        HotelRoom room = ((HotelRoomAdapter.RoomViewHolder)view.getTag()).room;
                        MyApp.setHotelRoom(room);
                        for (Room r: rateRooms) {
                            if (r.getRoomId().equals(room.getRoomId())){
                                MyApp.net= room.getBroadnetFee();//得到网络状态
                                List<RatePlans> plans = r.getRatePlans();
                                shoWPopList(room.getName(),plans);
                                popupWindow.showAtLocation(room_listview, Gravity.BOTTOM, 0, 0);
                            }
                        }
                    }
                });
             }else {
                return;
            }

        }
        super.taskDone(flag, result);
    }
    private  void shoWPopList(String name,List<RatePlans> plans){
        popView = LayoutInflater.from(this).inflate(R.layout.popo_for_room_layout,null);
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,true);
        popupWindow.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable(this.getResources()));
        popupWindow.setOutsideTouchable(true);
        TextView room_popo_dismiss = (TextView) popView.findViewById(R.id.room_popo_dismiss);
        TextView room_price_name = (TextView) popView.findViewById(R.id.room_price_name);
        room_price_name.setText(name);
        room_popo_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        ListView roo_price_listview = (ListView)popView.findViewById(R.id.roo_price_listview);
        HotelRoomPriceAdapter adapter = new HotelRoomPriceAdapter(plans,HDetailT.this);
        roo_price_listview.setAdapter(adapter);
    }


    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.app_back_rety:
                finish();
                break;
            case R.id.h_map_location:
                daohang();
                popupWindow.showAtLocation(room_listview, Gravity.BOTTOM, 0, 0);
                break;
        }
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
        if (isAvilible(HDetailT.this,"com.baidu.BaiduMap")){
            baidu_txt.setText("百度地图导航(已安装)");
        }else {
            baidu_txt.setText("百度地图导航(未安装)");
        }
        if (isAvilible(HDetailT.this,"com.autonavi.minimap")){
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
                if (isAvilible(HDetailT.this,"com.baidu.BaiduMap")){
                    startNative_Baidu();

                }
                popupWindow.dismiss();
            }
        });
        gaode_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isAvilible(HDetailT.this,"com.autonavi.minimap")){
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
             Intent intent = Intent.getIntent("intent://map/direction?destination=" + detail.getAddress() +
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
                    , android.net.Uri.parse("androidamap://viewGeo?sourceApplication=&addr=" +detail.getAddress()));
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

    /**设置需要显示的数据**/
    private void setMyDatas() {
        for (int i = 0; i <details.size() ; i++) {
            HotelDetail d = details.get(i);
             if (d.getHotelID()==hotelID){
                 hotel_name_txt.setText(d.getName());
                 hotel_address_txt.setText("地址："+d.getAddress());
                 if (d.getDescription().equals("")){
                     hotel_kaiye_txt.setText("不详！");
                 }else {
                     hotel_kaiye_txt.setText("简介："+d.getDescription());
                 }
                 if (d.getFeatures().equals("")){
                    hotel_pingfen_txt.setText("交通方便，性价比高");
                 }else {
                     hotel_pingfen_txt.setText(d.getFeatures());
                 }
             }
        }
        /**通过酒店ID找到酒店房间 rooms1 **/

        List<HotelRoom>  nowRooms = new ArrayList<>();//当前酒店的所有房间
        for (int i = 0; i <rooms.size() ; i++) {
            HotelRoom room = rooms.get(i);
            if (room.getHotelID()==hotelID){
                nowRooms.add(room);
            }
        }
        /*过滤一次房间数据*/
        List<HotelRoom> rightRoom = new ArrayList<>();
        for (int i = 0; i <rateRooms.size() ; i++) {
            for (int j = 0; j <nowRooms.size() ; j++) {
                if (rateRooms.get(i).getRoomId().equals(nowRooms.get(j).getRoomId())){
                    rightRoom.add(nowRooms.get(j));
                }
            }
        }

        /*得到所有的房间信息*/
        roomAdapter = new HotelRoomAdapter(this,rightRoom);
        room_listview.setAdapter(roomAdapter);


    }

    /**初始化酒店图片**/
    private void setPager(){
        imgs = new ArrayList<>();
        for (int i = 0; i <locations.size() ; i++) {
            if (locations.get(i).getHotelID()==hotelID&&locations.get(i).getSize()==7){
                imgs.add(locations.get(i).getURL());
            }
        }
        linear_pointers = (LinearLayout)findViewById(R.id.linearlayout_pointers);
        hotel_viewpager_container = (LinearLayout)findViewById(R.id.hotel_viewpager_container);
        linear_pointers.removeAllViews();
        viewPager = new ViewPager(this);
        //获取屏幕像素相关信息
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        viewPager.setLayoutParams(new ViewGroup.LayoutParams(dm.widthPixels, dm.heightPixels * 2 / 5));
        hotel_viewpager_container.addView(viewPager);
            final List<ImageView> list_poster = new ArrayList<>();
            // 广告内容
            RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, -1);
            for (int i = 0; i < 6; i++) {
                String itemBean = imgs.get(i);
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(params2);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ImageLoader.getInstance().displayImage(itemBean, imageView,
                        animateFirstListener);
                imageView.setTag(R.string.tag_id,  itemBean);
                list_poster.add(imageView);
            }
            // 广告的点
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                    12, 12);
            params3.leftMargin = 4;
            ImageView[] pointer = new ImageView[list_poster.size()];
            for (int i = 0; i < list_poster.size(); i++) {
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(params3);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                if (i == 0) {
                    imageView.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.shape_poster_pointer_selected));
                } else {
                    imageView.setBackgroundDrawable(getResources().getDrawable(
                            R.drawable.shape_poster_pointer_unselect));
                }
                pointer[i] = imageView;
                linear_pointers.addView(imageView);
            }

            final PosterAdapter posterAdapter = new PosterAdapter(this,
                    list_poster);
            viewPager.setAdapter(posterAdapter);
            if (posterControl != null) {
                posterControl.setThreadStop();
                posterControl = null;
            }
            posterControl = new PosterControl(this, viewPager, pointer);
            viewPager.setCurrentItem(0);


    }

    private static class AnimateFirstDisplayListener extends
            SimpleImageLoadingListener {
        static final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 300);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

}
