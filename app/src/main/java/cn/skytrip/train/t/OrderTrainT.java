package cn.skytrip.train.t;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mytables.MainActivity;
import com.mytables.MyApp;
import com.example.administrator.dmonline.R;
import f.sky.flight.adapter.SelectPsgAdapter;
import f.sky.flight.core.Constants;
import f.sky.flight.model.B_TouristDOObj;
import com.mytables.AppT;
import com.myuntils.AndroidUtil;
import com.myuntils.ListViewSetup;
import com.myuntils.MyAllUntils;

import java.util.ArrayList;
import java.util.List;

import cn.skytrip.train.json.OrderTrainJs;
import cn.skytrip.train.model.Person;
import cn.skytrip.train.model.TrainObj;


/**
 * Created by zhaody on 2016/12/20/020.
 * 火车票填写订单
 */

public class OrderTrainT extends AppT {

    private TextView order_dept_txt,order_dept_time_txt,order_startdate_txt,order_train_code_txt,
            order_run_time_txt,order_arr_txt,order_arr_time_txt,order_arrdate_txt,order_zw_price_txt,order_all_price,app_title;
    private TextView order_zw_type_txt;
    public final static int REQUEST_PSG_SELECT = 10;
    private ListView order_listview;
    private SelectPsgAdapter selectPsgAdapter;
    private TrainObj trainObj;
    private String zw_type;
    private List<Person> persons;
    private String zw_code ,zw_name;
    private boolean is_accept_standing = false;
    private int ORDER_TRAIN_TICKETS = 1;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.order_train_lay);
        trainObj =MyApp.getTrainObj();
        zw_type = getIntent().getStringExtra(Constants.ZW_TYPE);
        initView();
        Log.e("---获取到的----",MyApp.getTrainObj().toString());
        flashZWp(zw_type);
    }

    private void flashZWp(String t) {
        switch (t){
            case Constants.EDZ_TYPE:
                order_zw_type_txt.setText("二等座票价￥");
                order_zw_price_txt.setText(trainObj.getEdz_price());
                zw_name = "二等座";
                zw_code = "O";
                break;
            case Constants.YDZ_TYPE:
                order_zw_type_txt.setText("一等座票价￥");
                order_zw_price_txt.setText(trainObj.getYdz_price());
                zw_name = "一等座";
                zw_code = "M";
                break;
            case Constants.SWZ_TYPE:
                order_zw_type_txt.setText("商务座票价￥");
                order_zw_price_txt.setText(trainObj.getSwz_price());
                zw_name = "商务座";
                zw_code = "9";
                break;
            case Constants.YZ_TYPE:
                order_zw_type_txt.setText("硬座票价￥");
                order_zw_price_txt.setText(trainObj.getYz_price());
                zw_name = "硬座";
                zw_code = "1";
                break;
            case Constants.YW_TYPE:
                order_zw_type_txt.setText("硬卧票价￥");
                order_zw_price_txt.setText(trainObj.getYw_price());
                zw_name = "硬卧";
                zw_code = "3";
                break;
            case Constants.RW_TYPE:
                order_zw_type_txt.setText("软卧票价￥");
                order_zw_price_txt.setText(trainObj.getYw_price());
                zw_name = "软卧";
                zw_code = "4";
                break;
            case  Constants.WZ_TYPE:
                order_zw_type_txt.setText("无座票价￥");
                order_zw_price_txt.setText(trainObj.getWz_price());
                zw_name = "无座";
                zw_code = trainObj.getTrain_code();
                is_accept_standing = true;
                break;

        }

    }

    private void initView() {
        order_listview = (ListView)findViewById(R.id.order_listview);
        app_title = (TextView)findViewById(R.id.app_title_txt);
        app_title.setText("订单填写");
        order_dept_txt = (TextView)findViewById(R.id.order_dept_txt);
        order_dept_time_txt = (TextView)findViewById(R.id.order_dept_time_txt);
        order_startdate_txt = (TextView)findViewById(R.id.order_startdate_txt);
        order_train_code_txt = (TextView)findViewById(R.id.order_train_code_txt);
        order_run_time_txt = (TextView)findViewById(R.id.order_run_time_txt);
        order_arr_txt = (TextView)findViewById(R.id.order_arr_txt);
        order_arr_time_txt = (TextView)findViewById(R.id.order_arr_time_txt);
        order_arrdate_txt = (TextView)findViewById(R.id.order_arrdate_txt);

        order_zw_price_txt = (TextView)findViewById(R.id.order_zw_price_txt);
        order_all_price = (TextView)findViewById(R.id.order_all_price);
        order_zw_type_txt = (TextView)findViewById(R.id.order_zw_type_txt);

        order_dept_txt.setText(trainObj.getFrom_station_name());
        order_dept_time_txt.setText(trainObj.getStart_time());
        order_train_code_txt.setText(trainObj.getTrain_code());
        order_run_time_txt.setText(trainObj.getRun_time());
        order_arr_txt.setText(trainObj.getTo_station_name());
        order_arr_time_txt.setText(trainObj.getArrive_time());
        order_startdate_txt.setText(MyApp.getTrainEndDate(trainObj.getTrain_start_date(),0));
        String days = trainObj.getArrive_days();
        if (days.equals("0")){
            order_arrdate_txt.setText(MyApp.getTrainEndDate(trainObj.getTrain_start_date(),0));
        }else if (days.equals("1")){
            order_arrdate_txt.setText(MyApp.getTrainEndDate(trainObj.getTrain_start_date(),1));
        }else  if (days.equals("2")){
            order_arrdate_txt.setText(MyApp.getTrainEndDate(trainObj.getTrain_start_date(),2));
        }else if (days.equals("3")){
            order_arrdate_txt.setText(MyApp.getTrainEndDate(trainObj.getTrain_start_date(),3));
        }


        findViewById(R.id.order_add_person).setOnClickListener(this);
        findViewById(R.id.order_tickets_btn).setOnClickListener(this);
        findViewById(R.id.app_back_icon).setOnClickListener(this);

        selectPsgAdapter = new SelectPsgAdapter(this);
        selectPsgAdapter.setEdit(true);
        order_listview.setAdapter(selectPsgAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.app_back_icon:
                finish();
                break;
            case R.id.order_add_person:
//                Intent intent = new Intent();
//                intent.setClass(this, PsgManagerT.class);
//                intent.putExtra(Constants.PSG_IS_SELECT, true);
//                startActivityForResult(intent, REQUEST_PSG_SELECT);
                break;
            case R.id.order_tickets_btn:
                refreshOrgTotalPriceTxt();
                executeWeb(ORDER_TRAIN_TICKETS,"正在提交订单...");
                break;
            case R.id.del_psg_btn://删除乘车人
                deletePso(v);
                break;

        }
    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag==ORDER_TRAIN_TICKETS){
            Log.e("======", "doTask: 参数---："+trainObj.getTrain_code()+"\t"+MyApp.getTrainOrgCity().getName()+"\t"
                    +MyApp.getTrainOrgCity().getCode()+"\t"+ MyApp.getTrainDeptCity().getName()+"\t"
                    +MyApp.getTrainDeptCity().getCode()+"\t"+persons.size() );

            return OrderTrainJs.trainOrder(trainObj.getTrain_code(),is_accept_standing,
                    MyApp.getTrainOrgCity().getName(),MyApp.getTrainOrgCity().getCode(),
                    MyApp.getTrainDeptCity().getName(),MyApp.getTrainDeptCity().getCode(),persons);


        }

        return super.doTask(flag, params);
    }

    @Override
    public void taskDone(int flag, Object result) {
        if (flag==ORDER_TRAIN_TICKETS){
            if (result==null){
                AndroidUtil.shortToast(this,"提交订单失败");
                return;
            }
            Toast.makeText(getApplicationContext(), "提交成功" ,Toast.LENGTH_LONG).show();
            MyAllUntils.open(this, MainActivity.class);
        }

        super.taskDone(flag, result);

    }

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
                ListViewSetup.setListViewHeightBasedOnSameRowHeightChildren(order_listview);
                 refreshOrgTotalPriceTxt();
            }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQUEST_PSG_SELECT && resultCode == SELECT_PSG_RESULT_CODE){
//            selectPsgAdapter.setDatas(MyApp.tempOrderObj.getTouristDOObjL());
//            ListViewSetup.setListViewHeightBasedOnSameRowHeightChildren(order_listview);
//            refreshOrgTotalPriceTxt();
//        }

    }

    /**
     * 刷新相关乘客等的数据
     */
    private void refreshOrgTotalPriceTxt() {
        int personn_num= selectPsgAdapter.getTouristL().size();
        String zw_price = Float.parseFloat(order_zw_price_txt.getText().toString())*personn_num+"";
        order_all_price.setText(zw_price);
        List<B_TouristDOObj> list = selectPsgAdapter.getTouristL();
        persons = new ArrayList<>();
        for (int i = 0; i < personn_num; i++) {
            Person p = new Person();
            p.setPassengersename(list.get(i).getTouristName());
            p.setPassportseno(list.get(i).getIDNumber());
            p.setPassengerid(list.get(i).getIDType()+"");
            p.setPassengersename("身份证");
            p.setPiaotype("1");
            p.setPiaotypename("成人票");
            p.setZwcode(zw_code);
            p.setZwname(zw_name);
            p.setCxin("");
            p.setPrice(zw_price);
            p.setReason("");
            //关于学生票的参数
            p.setProvince_name("");
            p.setPreference_from_station_code("");
            p.setPreference_from_station_name("");
            p.setPreference_to_station_code("");
            p.setPreference_to_station_name("");
            p.setSchool_code("");
            p.setSchool_name("");
            p.setEnter_year("");
            p.setSchool_system("");
            p.setStudent_no("");
            persons.add(p);

        }


    }
}
