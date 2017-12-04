package f.sky.flight.t;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.changJson.API;
import com.changJson.QuerUserSyc;
import com.example.administrator.dmonline.R;
import com.google.gson.Gson;
import com.mytables.AppT;
import com.mytables.MyApp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import donm.sky.hotel.until.TimeUntil;
import f.sky.flight.core.CommitFlightService;
import f.sky.flight.core.FtUntils;
import f.sky.flight.model.BookDto;
import f.sky.flight.model.Credential;
import f.sky.flight.model.Credentials;
import f.sky.flight.model.FlightBookSegment;
import f.sky.flight.model.FlightSegment;
import f.sky.flight.model.OrderLinkmanDto;
import f.sky.flight.model.PassengerDto;
import f.sky.flight.model.VariableDto;

/**
 * Created by zhaody on 2017/11/22.
 * 机票预订
 */

public class OrderFlightActivity extends AppT {
    private ImageView from_flight_icon,from_flightline_icon,app_back_icon;
    private TextView from_flight_content,from_Order_title,from_Order_flight_change,
            from_flight_time,from_end_time,from_flight_airport, from_end_airport,app_title_txt;
    private EditText contact_number_edt,contact_emil_edt;
    private TextView person_IDNumber_name,person_IDNumber_Id,flight_Order_price;
    private FlightSegment segment;
    private Credential credential;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.order_flight_layout);
        segment = MyApp.getFlightSegment();
        Log.e("--数据---", "segment数据: "+segment.getAirlineName());
        initViews();
     }

    /**
     * 初始化组建
     */
    private void initViews() {
        app_title_txt = findViewById(R.id.app_title_txt);
        app_title_txt.setText("订单填写");
        contact_number_edt = findViewById(R.id.contact_number_edt);
        contact_emil_edt = findViewById(R.id.contact_emil_edt);
        app_back_icon = findViewById(R.id.app_back_icon);
        app_back_icon.setOnClickListener(this);
        person_IDNumber_name = findViewById(R.id.person_IDNumber_name);
        person_IDNumber_Id = findViewById(R.id.person_IDNumber_Id);
        flight_Order_price =findViewById(R.id.flight_Order_price);

        from_flight_icon = findViewById(R.id.from_flight_icon);//去程显示图标
        from_flightline_icon = findViewById(R.id.from_flightline_icon);//航班图标
        from_flight_content = findViewById(R.id.from_flight_content);//显示航班-机型-舱位
        from_Order_title = findViewById(R.id.from_Order_title);//航班时间- 出发城市
        from_Order_flight_change = findViewById(R.id.from_Order_flight_change);//退改签
        from_flight_time = findViewById(R.id.from_flight_time);//出发时间
        from_end_time = findViewById(R.id.from_end_time);//到达时间
        from_flight_airport = findViewById(R.id.from_flight_airport);//出发机场
        from_end_airport = findViewById(R.id.from_end_airport);//到达机场
        /***设置显示数据***/
        FtUntils.setFlightIcon(segment.getAirline(),from_flightline_icon);
        from_Order_title.setText(TimeUntil.getDayStr(MyApp.getDefaltStartDate())+"\t"+
                TimeUntil.getWeek(MyApp.getDefaltStartDate())+segment.getFromCityName()+"-"+segment.getToCityName());
        from_flight_content.setText(segment.getAirlineName()+" | "+segment.getNumber()+" | "+segment.getPlaneType()+" | "+segment.getCabin().getTypeName());
        from_flight_time.setText(segment.getTakeoffTime().substring(11,16));
        from_end_time.setText(segment.getArrivalTime().substring(11,16));
        from_flight_airport.setText(FtUntils.replaceAirportName(segment.getFromAirportName(),segment.getFromTerminal()));
        from_end_airport.setText(FtUntils.replaceAirportName(segment.getToAirportName(),segment.getToTerminal()));
        String cc = MyApp.getSp().getString(API.CREDENTIAL,null);
        Log.e("---", "initViews: "+cc );
        credential = QuerUserSyc.jsonCredential(cc);
        person_IDNumber_name.setText(credential.getCheckName());
        person_IDNumber_Id.setText(credential.getNumber());
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.app_back_icon:
                finish();
                break;
        }
    }

    /**
     * 预订机票
     * @param view
     */
    public void  orderFlightOnclick(View view){
        executeWeb(API.COMMIT_FLIGHT,"正在提交...");
    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag ==API.COMMIT_FLIGHT){
            return CommitFlightService.commitFligtMethed(credential,segment,MyApp.getSp().getString(API.TICKET,null));
        }
        return super.doTask(flag, params);
    }

    @Override
    public void taskDone(int flag, Object result) {
        if (flag==API.COMMIT_FLIGHT){
            Log.e("机票订单返回结果：", "---： "+result);
        }
        super.taskDone(flag, result);
    }
}
