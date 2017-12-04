package f.sky.flight.t;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.changJson.API;
import com.example.administrator.dmonline.R;
import com.mytables.AppT;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;

import java.util.ArrayList;
import java.util.List;

import f.sky.flight.adapter.RecyclerAdapter;
import f.sky.flight.adapter.RvDividerItemDecoration;
import f.sky.flight.adapter.SecondaryListAdapter;
import f.sky.flight.core.QuerNewFlightService;
import f.sky.flight.model.Cabin;
import f.sky.flight.model.FlightResModel;
import f.sky.flight.model.FlightSegment;

/**
 * Created by zhaody on 2017/11/13.
 */

public class SearchFlightListActivity extends AppT {
    private  String Url = "http://test.flight.api.tmc.sky-trip.com/home/detail?date=2017-11-15&fromCode=SHA&toCode=PEK&ticket=c5971ce004ff4d30a485cec51518b83b";
   private  RecyclerView rv;
   private ImageView app_back;
   private List<SecondaryListAdapter.DataTree<FlightSegment,List<Cabin>>> myDatas = new ArrayList<>();
   private  RecyclerAdapter adapter;
    private static final int SEARCH_TICKETS = 0;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.search_flight_list_layout);
        rv = this.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        rv.addItemDecoration(new RvDividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        app_back = findViewById(R.id.flight_back_icon);
        app_back.setOnClickListener(this);
        executeWeb(SEARCH_TICKETS,"查询中请稍后...");
        adapter = new RecyclerAdapter(this);

    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag == SEARCH_TICKETS){
            //获取登录时得到的tickets
            String tickets = MyApp.getSp().getString(API.TICKET,null);
            return QuerNewFlightService.querNewFlightList(MyApp.getDefaltStartDate(),"SHA","PEK",tickets);
        }
        return super.doTask(flag, params);
    }

    @Override
    public void taskDone(int flag, Object result) {
        super.taskDone(flag, result);
        if (flag==SEARCH_TICKETS){
            FlightResModel resModel =(FlightResModel) result;
            if (resModel.isStatus()){
                AndroidUtil.shortToast(this,"请求成功！！");
                List<FlightSegment> flightSegments  = resModel.getFlightSegmentList();
                Log.e("---size---", "taskDone: "+flightSegments.size()+"个航班" );
//                Log.e("--cabinSize--:", "taskDone: "+flightSegments.get(0).getCabins().size()+"个" );
                for (int i = 0; i <flightSegments.size() ; i++) {
                    myDatas.add(new SecondaryListAdapter.DataTree<FlightSegment,
                            List<Cabin>>(flightSegments.get(i),flightSegments.get(i).getCabins()));
                }
                adapter.setData(myDatas);
                rv.setAdapter(adapter);
            }else {
                AndroidUtil.shortToast(this,"请求失败"+resModel.getMessage());
                return;
            }

        }
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.flight_back_icon:
                finish();
                break;
        }
    }
}
