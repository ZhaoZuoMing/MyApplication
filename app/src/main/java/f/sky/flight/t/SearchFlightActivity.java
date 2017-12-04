package f.sky.flight.t;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.AppT;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;
import com.myviews.CaldroidActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import donm.sky.hotel.until.TimeUntil;
import f.sky.flight.core.Constants;

/**
 * Created by zhaody on 2017/11/8.
 * 查询机票
 */

public class SearchFlightActivity extends AppT {
    private ImageView app_back;
    private RadioButton oneway_radiobtn, backway_radio;
    private TextView flight_start_airport, flight_to_airport;
    private TextView flight_start_date, flight_start_week, flight_back_date, flight_back_week;
    private Button goto_search_flight;
    private long seleteTime = 0;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.search_flight_layout);
        TextView title = this.findViewById(R.id.app_title_txt);
        title.setText("机票查询");
        app_back = findViewById(R.id.app_back_icon);
        app_back.setOnClickListener(this);
        oneway_radiobtn = findViewById(R.id.oneway_radiobtn);
        backway_radio = findViewById(R.id.backway_radio);
        flight_start_airport = findViewById(R.id.flight_start_airport);
        flight_start_airport.setOnClickListener(this);
        flight_to_airport = findViewById(R.id.flight_to_airport);
        flight_to_airport.setOnClickListener(this);
        flight_start_date = findViewById(R.id.flight_start_date);
        flight_start_date.setOnClickListener(this);
        flight_start_week = findViewById(R.id.flight_start_week);
        flight_back_date = findViewById(R.id.flight_back_date);
        flight_back_date.setOnClickListener(this);
        flight_back_week = findViewById(R.id.flight_back_week);
        goto_search_flight = findViewById(R.id.goto_search_flight);
        goto_search_flight.setOnClickListener(this);
        //设置初始化的日期
        flight_start_date.setText(TimeUntil.getDayStr(MyApp.getDefaltStartDate()));
        flight_start_week.setText(TimeUntil.getWeek(MyApp.getDefaltStartDate()));
        flight_back_date.setText(TimeUntil.getDayStr(MyApp.getDefaltEndDate()));
        flight_back_week.setText(TimeUntil.getWeek(MyApp.getDefaltEndDate()));
        //单程选择
         oneway_radiobtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
               if (isChecked){
                   MyApp.setRoundFlight(false);
                   flight_back_date.setVisibility(View.GONE);
                   flight_back_week.setVisibility(View.GONE);
               }
            }
        });
         //往返程选择
        backway_radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    MyApp.setRoundFlight(true);
                    flight_back_date.setVisibility(View.VISIBLE);
                    flight_back_week.setVisibility(View.VISIBLE);
                    flight_back_date.setText(TimeUntil.getDayStr(MyApp.getDefaltEndDate()));
                    flight_back_week.setText(TimeUntil.getWeek(MyApp.getDefaltEndDate()));
                }
            }
        });
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.flight_start_airport://出发机场

                break;
            case R.id.flight_to_airport://到达机场

                break;
            case R.id.flight_start_date://出发日期
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putLong(Constants.SELECT_TIME, seleteTime);
                intent.putExtras(bundle);
                intent.setClass(SearchFlightActivity.this, CaldroidActivity.class);
                startActivityForResult(intent,10);
                break;
            case R.id.flight_back_date://返程日期
                Intent intentRound = new Intent();
                Bundle bundleRound = new Bundle();
                bundleRound.putLong(Constants.SELECT_TIME, seleteTime);
                intentRound.putExtras(bundleRound);
                intentRound.setClass(SearchFlightActivity.this, CaldroidActivity.class);
                startActivityForResult(intentRound,20);
                break;
            case R.id.goto_search_flight://去查询
                AndroidUtil.open(this,SearchFlightListActivity.class,true);
                break;
            case  R.id.app_back_icon:
                finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10){//去程航班
            if (resultCode==Constants.CHOSE_T){
                {
                    seleteTime = data.getLongExtra(Constants.SELECT_DATA_TIME, 0);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = new Date(seleteTime);
                    String t1 = format.format(d1);
                    if (seleteTime > 0) {
                        MyApp.setStartDate(t1);
                        flight_start_date.setText(TimeUntil.parseDataToStr(d1));
                        flight_start_week.setText(TimeUntil.getWeek(t1));
                        /**----如果选择出发日期默认返程日期加一天---**/
                        MyApp.setEndDate(TimeUntil.addOrLoer(t1,1));
                        //还需要再设置一下
                        flight_back_date.setText(TimeUntil.getDayStr(MyApp.getDefaltEndDate()));
                        flight_back_week.setText(TimeUntil.getWeek(MyApp.getDefaltEndDate()));
                    } else {
                        return;
                    }
                }
            }else if (requestCode == Constants.NO_SELECT_T){
                return;
            }
        }else if (requestCode==20){//返程日期
            if (resultCode==Constants.CHOSE_T){
                {
                    seleteTime = data.getLongExtra(Constants.SELECT_DATA_TIME, 0);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = new Date(seleteTime);
                    String t1 = format.format(d1);
                    if (seleteTime > 0) {
                        MyApp.setEndDate(t1);
                        flight_back_date.setText(TimeUntil.parseDataToStr(d1));
                        flight_back_week.setText(TimeUntil.getWeek(t1));
                    } else {
                        return;
                    }
                }
            }else if (requestCode == Constants.NO_SELECT_T){
                return;
            }
        }

    }
}