package com.mytables;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mymodels.TravelData;
import com.myuntils.MyAllUntils;
import com.myviews.MyListView;
import com.myviews.TravelAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import donm.sky.hotel.htservice.QuerOrderList;
import donm.sky.hotel.model.HotelDataList;
import donm.sky.hotel.model.QueryOrderHotelResult;
import donm.sky.hotel.until.TimeUntil;
import f.sky.flight.core.Constants;
import f.sky.flight.core.OrderService;
import f.sky.flight.core.WebServUtil;
import f.sky.flight.model.B_OrderDOObj;
import f.sky.flight.model.QueryOrderOrApplyResult;

/**
 * Created by Administrator on 2016/12/13/013.
 * 订单列表界面
 */

public class TravelT extends  AppT implements SwipeRefreshLayout.OnRefreshListener{
    private  final static int GET_DATA = 0;
    private int page = 1;
    private MyListView travel_listview;
    private TextView travel_fistCity_name;
    private TextView travel_fistStart_date;
    private  List<TravelData> travelDatas = new ArrayList<>();
    private SwipeRefreshLayout travel_pull_refresh_view;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.travel_layout);
        initView();
//        executeWeb(GET_DATA,"正在规划行程...");
    }

    private void initView() {
        travel_pull_refresh_view = (SwipeRefreshLayout) findViewById(R.id.travel_pull_refresh_view);
        travel_pull_refresh_view.setOnRefreshListener(this);
        travel_pull_refresh_view.setColorSchemeResources(R.color.green,R.color.chengse);
        travel_listview = (MyListView) findViewById(R.id.travel_listview);
        travel_listview.setFocusable(false);
        travel_fistCity_name = (TextView)findViewById(R.id.travel_fistCity_name);
        travel_fistStart_date = (TextView)findViewById(R.id.travel_fistStart_date);
    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag==GET_DATA){
             QueryOrderOrApplyResult queryOrderOrApplyResult = OrderService.order_Query(WebServUtil.USER_ID,WebServUtil.PASSWORD,MyApp.loginRegResult  .getUserServerObject().getUserName(),page,MyApp.getPageSize());
             List<B_OrderDOObj> doObjs = queryOrderOrApplyResult.getOrderDoS();
            for (int i = 0; i <doObjs.size() ; i++) {
                 B_OrderDOObj orderObj = doObjs.get(i);

                String[] skywayS = orderObj.getSkyways().split(" ");
                String[] SkywayTakeOffDates = orderObj.getSkywayTakeOffDates().split(" ");
                String [] flight_NOs =orderObj.getFlightNos().split(" ");
                 if (SkywayTakeOffDates.length>1){
                     TravelData TtravelData = new TravelData();
                     if (TimeUntil.daysBetween(MyAllUntils.getBejiT(),SkywayTakeOffDates[0])>=0){
//                         TtravelData.setStartDate("2017-06-20");
                         TtravelData.setStartDate(SkywayTakeOffDates[0]);
                         TtravelData.setStartStation(skywayS[0].split("-")[0]);
                         TtravelData.setEndStation(skywayS[0].split("-")[1]);
                         TtravelData.setName(flight_NOs[0]);
                         TtravelData.setType(1);
                         travelDatas.add(TtravelData);
                      }
                     TravelData travelData = new TravelData();
                     if (TimeUntil.daysBetween(MyAllUntils.getBejiT(),SkywayTakeOffDates[1])>=0) {
                         travelData.setStartDate(SkywayTakeOffDates[1]);
                         travelData.setStartStation(skywayS[1].split("-")[0]);
                         travelData.setEndStation(skywayS[1].split("-")[1]);
                         travelData.setName(flight_NOs[1]);
                         travelData.setType(1);
                         travelDatas.add(travelData);
                     }
                   }else{
                     TravelData singlData = new TravelData();
                     if (TimeUntil.daysBetween(MyAllUntils.getBejiT(),SkywayTakeOffDates[0])>=0) {
                         singlData.setType(1);
//                         singlData.setStartDate("2017-06-19");
                         singlData.setStartDate(SkywayTakeOffDates[0]);
                         singlData.setStartStation(skywayS[0].split("-")[0]);
                         singlData.setEndStation(skywayS[0].split("-")[1]);
                         singlData.setName(flight_NOs[0]);
                         travelDatas.add(singlData);
                     }
                 }
             }
             int bookId =  MyApp.loginRegResult.getUserServerObject().getId();
             int clientId = MyApp.loginRegResult.getUserServerObject().getUserOrgObj().getClientID();
             QueryOrderHotelResult queryOrderHotelResult =  QuerOrderList.queryOrderHotel(bookId,clientId, Constants.APPLY_ID,Constants.FUONE);
             List<HotelDataList> dataLists = queryOrderHotelResult.getHotelDataLists();
              for (int i = 0; i <dataLists.size() ; i++) {
                  HotelDataList hotelData = dataLists.get(i);
                  TravelData travelData = new TravelData();
                  if (TimeUntil.daysBetween(MyAllUntils.getBejiT(), hotelData.getCheckInDate()) >= 0) {
                      travelData.setType(2);
                      travelData.setStartDate(hotelData.getCheckInDate());
                      travelData.setEndDate(hotelData.getCheckOutDate());
                      travelData.setOrderId(hotelData.getOrderid());
                      travelData.setName(hotelData.getHotelName());
                      travelData.setStartTime(getHotelLaterArraiveT(hotelData.getLaterArrivalTime()));//2017-05-20T17:00:00
                      travelData.setEnter_days(TimeUntil.daysBetween(hotelData.getCheckInDate(), hotelData.getCheckOutDate()) + "");
                      travelDatas.add(travelData);
                  }
              }
               listSort(travelDatas);
              return  travelDatas;
         }
        return super.doTask(flag, params);
    }

    private String getHotelLaterArraiveT(String t){
         return t.substring(11,16);
    }
    @Override
    public void taskDone(int flag, Object result) {
        super.taskDone(flag, result);
        if (flag==GET_DATA){
            List<TravelData> datas = (List<TravelData>) result;
             //设置显示的第一个行程
             TravelData d1 = datas.get(0);
             if (d1.getType()==1){
                 travel_fistCity_name.setText("飞往"+d1.getStartStation().substring(0,2));
                 travel_fistStart_date.setText(TimeUntil.dateChangeToString(d1.getStartDate()));
             }else if (d1.getType()==2){
                 travel_fistCity_name.setText("入住"+d1.getName());
                 travel_fistStart_date.setText(TimeUntil.dateChangeToString(d1.getStartDate())+"\t\t/"+d1.getStartTime());
             }
            travel_listview.setAdapter(new TravelAdapter(datas,this));
        }
        travel_pull_refresh_view.setRefreshing(false);

    }

    private void listSort(List<TravelData> list){
        Collections.sort(list, new Comparator<TravelData>() {
            @Override
            public int compare(TravelData o1, TravelData o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try{
                    Date d1 = format.parse(o1.getStartDate());
                    Date d2 = format.parse(o2.getStartDate());
                    if (d1.getTime()>d2.getTime()){
                        return 1;
                    }else if (d1.getTime()<d2.getTime()){
                        return -1;
                    }else{
                        return 0;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        travel_pull_refresh_view.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (travelDatas.size()>0){
                    travelDatas.clear();
                   }
              executeWeb(GET_DATA,"");
            }
        },2000);
    }
}
