package com.mytables;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mymodels.MyLocation;
import com.myuntils.LocationUntil;

import cn.skytrip.train.t.SearchTrainActivity;
import donm.sky.hotel.t.HotelSearchActivity;
import f.sky.flight.t.SearchFlightActivity;

/**
 * Created by Administrator on 2016/11/10/010.
 */

public class HomeActivity extends LocationUntil {

    private TextView city_location_txt;
    private MyLocation location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
    }


    /**
     *机票查询
     * @param v
     */
     public  void searchAirTicket(View v){
         Intent intent = new Intent(HomeActivity.this, SearchFlightActivity.class);
         Bundle bundle = new Bundle();
         bundle.putInt("time",0);
         intent.putExtras(bundle);
         startActivity(intent);
     }

    /**
     * 火车票查询
     * @param v
     */
     public  void searchTrainTicket(View v){
         Intent intent = new Intent(HomeActivity.this, SearchTrainActivity.class);
         startActivity(intent);
    }

    /**
     * 酒店查询
     * @param v
     */
     public  void searchHotelTicket(View v){
        Intent intent =new Intent(HomeActivity.this, HotelSearchActivity.class);
         startActivity(intent);
    }

}
