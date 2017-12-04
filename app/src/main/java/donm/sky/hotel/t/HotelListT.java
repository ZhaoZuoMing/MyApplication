package donm.sky.hotel.t;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.AppT;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;
import com.myviews.CustomListView;

import java.util.ArrayList;
import java.util.List;

import donm.sky.hotel.adapter.HotelListAdapter;
import donm.sky.hotel.htservice.HotelListSearch;
import donm.sky.hotel.htservice.InitHotelData;
import donm.sky.hotel.model.HotelDetail;
import donm.sky.hotel.model.HotelInfoSearchResult;
import donm.sky.hotel.model.HotelListSearchResult;


/**
 * Created by Administrator on 2016/12/26/026.
 * 酒店列表
 */

public class HotelListT extends AppT {
    private static  final  int SEARCH_FLAG=1;
    private static final  int SEARCH_INFO_LIST=2;
    private List<Integer> hotelIds;
    private HotelListAdapter adapter;
    private CustomListView hotel_listview;
    private TextView app_title;
    private ImageView app_back;
    private HotelListSearchResult searchResult = null;
    private  HotelInfoSearchResult hotelInfoSearchResult=null;
    private List<HotelInfoSearchResult> lisInfos;
    private List<HotelListSearchResult> hotelListSearchResults;
    private int currentPager = 1;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.hotel_list_lay);
        hotel_listview = (CustomListView)findViewById(R.id.hotel_listview);
        app_title = (TextView)findViewById(R.id.app_title_txt);
        app_title.setText("酒店查询");
        app_back = (ImageView)findViewById(R.id.app_back_icon);
        lisInfos = new ArrayList<>();
        hotelListSearchResults = new ArrayList<>();
        app_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        executeWeb(SEARCH_FLAG,"查询酒店数据...");
        /**上拉加载更多--分页**/
        hotel_listview.setOnLoadListener(new CustomListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                currentPager++;
                executeWeb(SEARCH_FLAG,"");
            }
        });

    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag == SEARCH_FLAG){
//             logTest();
             return InitHotelData.hotelListSearCh(MyApp.gethEnterT(),MyApp.gethOutT(),MyApp.getH_city().getCode(),MyApp.QueryText,
                    MyApp.loginRegResult.getUserServerObject().getUserOrgObj().getClientID(),currentPager,MyApp.strLevel);
        }else if (flag==SEARCH_INFO_LIST){
            return  HotelListSearch.getHotelListData(MyApp.loginRegResult.getUserServerObject().getUserOrgObj().getClientID(),
                    MyApp.getH_city().getCode(),hotelIds);
        }
        return super.doTask(flag, params);
    }

    private void logTest() {
        Log.e("----------","查询酒店需要的参数：------："+
                MyApp.gethEnterT()+"\t"+MyApp.gethOutT()+"\t"+MyApp .getH_city().getCode()+ "\t"+MyApp.loginRegResult.getUserServerObject()
                .getUserOrgObj().getClientID());
    }

    @Override
    public void taskDone(int flag, Object result) {
        if (flag == SEARCH_FLAG){
            if (result==null){
                AndroidUtil.alert(HotelListT.this,"查询失败！请重新查询");
                hotel_listview.onLoadMoreComplete();
                return;
            }
            searchResult = (HotelListSearchResult)result;//------
            hotelListSearchResults.add(searchResult);
            if(searchResult.isSuccess()==true){
                hotelIds = new ArrayList<>();
                int hLong = searchResult.getHotels().size();
                for (int i = 0; i < hLong; i++) {
                    int id = searchResult.getHotels().get(i).getHotelId();
                    hotelIds.add(id);
                }
                MyApp.setHotelListSearchResult(hotelListSearchResults);
                if (currentPager==1){
                    executeWeb(SEARCH_INFO_LIST,"查询酒店...");
                }else{
                    executeWeb(SEARCH_INFO_LIST,"");
                }


            }

        }else if (flag==SEARCH_INFO_LIST){
            if (result==null){
                AndroidUtil.alert(HotelListT.this,"查询失败！请重新查询");
                hotel_listview.onLoadMoreComplete();
                return;
            }

            hotelInfoSearchResult = (HotelInfoSearchResult) result;
            lisInfos.add(hotelInfoSearchResult);
            MyApp.setHotelInfoSearchResult(lisInfos);
            if (hotelInfoSearchResult.isSuccess()==true){
                hotel_listview.onLoadMoreComplete();
                adapter = new HotelListAdapter(this,lisInfos);
                hotel_listview.setAdapter(adapter);
                if (currentPager!=1){
                    adapter.notifyDataSetChanged();
                    hotel_listview.setSelection(adapter.getCount()-10);
                }
                //----ListView点击事件
                hotel_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(HotelListT.this,HDetailT.class);
                        HotelDetail detail = ((HotelListAdapter.DetailHodler)view.getTag()).detail;
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("detail",detail);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }else {
                AndroidUtil.shortToast(HotelListT.this,hotelInfoSearchResult.getMessage());
            }
        }
        super.taskDone(flag, result);
    }

}


