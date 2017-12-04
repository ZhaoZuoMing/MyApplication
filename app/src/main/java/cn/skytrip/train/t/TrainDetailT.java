package cn.skytrip.train.t;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mytables.MyApp;
import com.example.administrator.dmonline.R;
import f.sky.flight.core.Constants;
import com.mytables.AppT;

import cn.skytrip.train.model.TrainObj;

/**
 * Created by Administrator on 2016/12/19/019.
 * 车次详情
 */

public class TrainDetailT extends AppT {

    private TrainObj trainObj = null;
    private TextView app_title_txt;
    private TextView dt_dept_txt,dt_dept_time_txt,dt_startdate_txt,dt_train_code_txt,dt_run_time_txt;
    private TextView dt_arr_txt,dt_arr_time_txt,dt_arrdate_txt;
    private TextView dt_edz_price,dt_edz_num,dt_ydz_price,dt_ydz_num,dt_swz_price,dt_swz_num;
    private LinearLayout gd_lay,dt_edz_lay,dt_ydz_lay,dt_swz_lay,dt_rw_lay,dt_gjrw_lay,kt_lay;
    private TextView ydz_v1,swz_v3,rw_01,gjrw_v;
    private LinearLayout kt_yz_lay,kt_ywt_lay,kt_rwt_lay,kt_wzt_lay;
    private TextView kt_yz_price,kt_yz_num,kt_ywt_price,kt_ywt_num,kt_rwt_price,kt_rwt_num,kt_wzt_price,kt_wzt_num;
    private Button btn_dtorder_ktyw,btn_dtorder_ktyz,btn_dtorder_ktrw,btn_dtorder_ktwz;
    private TextView kt_yw_line,kt_rw_line,kt_wz_line;
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        trainObj  = (TrainObj)getIntent().getSerializableExtra(Constants.TRAIN_DETAIN);
        Log.e("======", "onCreate: "+trainObj.toString() );
        MyApp.setTrainObj(trainObj);
        setContentView(R.layout.train_detail_lay);
        app_title_txt = (TextView)findViewById(R.id.app_title_txt);
        gd_lay = (LinearLayout)findViewById(R.id.gd_lay);
        kt_lay = (LinearLayout)findViewById(R.id.kt_lay);
        app_title_txt.setText("车次详情");
        findViewById(R.id.app_back_icon).setOnClickListener(this);
        mIntent= new Intent(TrainDetailT.this,OrderTrainT.class);
        initView();
        if (trainObj.getTrain_type().equals("G")||trainObj.getTrain_type().equals("D")){
            Log.e("----------动车高铁-----", "onCreate: ");
            gd_lay.setVisibility(View.VISIBLE);
            kt_lay.setVisibility(View.GONE);
            initGDView();
        }else{
            Log.e("----------快铁---------", "onCreate: ");
            kt_lay.setVisibility(View.VISIBLE);
            gd_lay.setVisibility(View.GONE);
            initKtView();
        }

    }
     /**初始化快铁组件**/
    private void initKtView() {
        kt_yz_lay = (LinearLayout)findViewById(R.id.kt_yz_lay);
        kt_ywt_lay = (LinearLayout)findViewById(R.id.kt_ywt_lay);
        kt_rwt_lay = (LinearLayout)findViewById(R.id.kt_rwt_lay);
        kt_wzt_lay = (LinearLayout)findViewById(R.id.kt_wzt_lay);

        kt_yw_line = (TextView)findViewById(R.id.kt_yw_line);
        kt_rw_line = (TextView)findViewById(R.id.kt_rw_line);
        kt_wz_line = (TextView)findViewById(R.id.kt_wz_line);

        setVB(trainObj.getYz_num(),kt_yz_lay,kt_yz_lay);
        setVB(trainObj.getYw_num(),kt_ywt_lay,kt_yw_line);
        setVB(trainObj.getRw_num(),kt_rwt_lay,kt_rw_line);
        setVB(trainObj.getWz_num(),kt_wzt_lay,kt_wz_line);

        kt_yz_price = (TextView)findViewById(R.id.kt_yz_price);
        kt_yz_num = (TextView)findViewById(R.id.kt_yz_num);
        kt_ywt_price = (TextView)findViewById(R.id.kt_ywt_price);
        kt_ywt_num = (TextView)findViewById(R.id.kt_ywt_num);
        kt_rwt_price = (TextView)findViewById(R.id.kt_rwt_price);
        kt_rwt_num = (TextView)findViewById(R.id.kt_rwt_num);
        kt_wzt_price = (TextView)findViewById(R.id.kt_wzt_price);
        kt_wzt_num = (TextView)findViewById(R.id.kt_wzt_num);

        kt_yz_price.setText("￥"+trainObj.getYz_price());
        kt_yz_num.setText(trainObj.getYz_num()+"张");
        kt_ywt_price.setText("￥"+trainObj.getYw_price());
        kt_ywt_num.setText(trainObj.getYw_num()+"张");
        kt_rwt_price.setText("￥"+trainObj.getRw_price());
        kt_rwt_num.setText(trainObj.getRw_num()+"张");
        kt_wzt_price.setText("￥"+trainObj.getWz_price());
        kt_wzt_num.setText(trainObj.getWz_num()+"张");

        findViewById(R.id.btn_dtorder_ktyw).setOnClickListener(this);
        findViewById(R.id.btn_dtorder_ktyz).setOnClickListener(this);
        findViewById(R.id.btn_dtorder_ktrw).setOnClickListener(this);
        findViewById(R.id.btn_dtorder_ktwz).setOnClickListener(this);


    }

    private void initView() {
        dt_dept_txt = (TextView)findViewById(R.id.dt_dept_txt);
        dt_dept_time_txt = (TextView)findViewById(R.id.dt_dept_time_txt);
        dt_startdate_txt = (TextView)findViewById(R.id.dt_startdate_txt);
        dt_train_code_txt = (TextView)findViewById(R.id.dt_train_code_txt);
        dt_run_time_txt = (TextView)findViewById(R.id.dt_run_time_txt);
        dt_arr_txt = (TextView)findViewById(R.id.dt_arr_txt);
        dt_arr_time_txt = (TextView)findViewById(R.id.dt_arr_time_txt);
        dt_arrdate_txt = (TextView)findViewById(R.id.dt_arrdate_txt);
        dt_dept_txt.setText(trainObj.getFrom_station_name());
        dt_dept_time_txt.setText(trainObj.getStart_time());
        dt_train_code_txt.setText(trainObj.getTrain_code());
        dt_run_time_txt.setText(trainObj.getRun_time());
        dt_arr_txt.setText(trainObj.getTo_station_name());
        dt_arr_time_txt.setText(trainObj.getArrive_time());
        dt_startdate_txt.setText(MyApp.getTrainEndDate(trainObj.getTrain_start_date(),0));
        String days = trainObj.getArrive_days();
        if (days.equals("0")){
            dt_arrdate_txt.setText(MyApp.getTrainEndDate(trainObj.getTrain_start_date(),0));
        }else if (days.equals("1")){
            dt_arrdate_txt.setText(MyApp.getTrainEndDate(trainObj.getTrain_start_date(),1));
        }else  if (days.equals("2")){
            dt_arrdate_txt.setText(MyApp.getTrainEndDate(trainObj.getTrain_start_date(),2));
        }else if (days.equals("3")){
            dt_arrdate_txt.setText(MyApp.getTrainEndDate(trainObj.getTrain_start_date(),3));
        }

    }


    private void initGDView() {
        dt_edz_lay = (LinearLayout)findViewById(R.id.dt_edz_lay) ;

        dt_ydz_lay = (LinearLayout)findViewById(R.id.dt_ydz_lay) ;
        ydz_v1 = (TextView)findViewById(R.id.ydz_v1);

        dt_swz_lay = (LinearLayout)findViewById(R.id.dt_swz_lay) ;
        swz_v3 = (TextView)findViewById(R.id.swz_v3);

        dt_rw_lay = (LinearLayout)findViewById(R.id.dt_rw_lay) ;
        rw_01 = (TextView)findViewById(R.id.rw_01);

        dt_gjrw_lay = (LinearLayout)findViewById(R.id.dt_gjrw_lay) ;
        gjrw_v = (TextView)findViewById(R.id.gjrw_v);

        setVB(trainObj.getEdz_num(),dt_edz_lay,dt_edz_lay);
        setVB(trainObj.getYdz_num(),dt_ydz_lay,ydz_v1);
        setVB(trainObj.getSwz_num(),dt_swz_lay,swz_v3);
        setVB(trainObj.getRw_num(),dt_rw_lay,rw_01);
        setVB(trainObj.getGjrw_num(),dt_gjrw_lay,gjrw_v);

        dt_edz_price = (TextView)findViewById(R.id.dt_edz_price);
        dt_edz_num = (TextView)findViewById(R.id.dt_edz_num);
        dt_ydz_price = (TextView)findViewById(R.id.dt_ydz_price);
        dt_ydz_num = (TextView)findViewById(R.id.dt_ydz_num);
        dt_swz_price = (TextView)findViewById(R.id.dt_swz_price);
        dt_swz_num = (TextView)findViewById(R.id.dt_swz_num);

        dt_edz_price.setText("￥"+trainObj.getEdz_price());
        dt_edz_num.setText(trainObj.getEdz_num()+"张");
        dt_ydz_price.setText("￥"+trainObj.getYdz_price());
        dt_ydz_num.setText(trainObj.getYdz_num()+"张");
        dt_swz_price.setText("￥"+trainObj.getSwz_price());
        dt_swz_num.setText(trainObj.getSwz_num()+"张");
        findViewById(R.id.btn_dtorder_edz).setOnClickListener(this);
        findViewById(R.id.btn_dtorder_ydz).setOnClickListener(this);
        findViewById(R.id.btn_dtorder_swz).setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.app_back_icon:
                finish();
                break;
            case R.id.btn_dtorder_edz://二等座
                intentOrder(Constants.ZW_TYPE,Constants.EDZ_TYPE);

                break;
            case R.id.btn_dtorder_ydz://一等座
                intentOrder(Constants.ZW_TYPE,Constants.YDZ_TYPE);
                break;
            case R.id.btn_dtorder_swz://商务座
                intentOrder(Constants.ZW_TYPE,Constants.SWZ_TYPE);
                break;
             /******快铁预订按钮********/
            case R.id.btn_dtorder_ktyz://硬座
                intentOrder(Constants.ZW_TYPE,Constants.YZ_TYPE);
                break;
            case R.id.btn_dtorder_ktyw://硬卧
                intentOrder(Constants.ZW_TYPE,Constants.YW_TYPE);
                break;
            case R.id.btn_dtorder_ktrw://软卧
                intentOrder(Constants.ZW_TYPE,Constants.RW_TYPE);
                break;
            case R.id.btn_dtorder_ktwz://无座
                intentOrder(Constants.ZW_TYPE,Constants.WZ_TYPE);
                break;


        }

    }
    private  void  intentOrder(String type,String price){
        mIntent.putExtra(type,price);
        startActivity(mIntent);

    }
    private void setVB(String isStr,LinearLayout lay,View txt){
        Log.e("----", "setVB========: "+isStr );
        if (isStr.equals("--")){
            lay.setVisibility(View.GONE);
            txt.setVisibility(View.GONE);
        }else{
            lay.setVisibility(View.VISIBLE);
            txt.setVisibility(View.VISIBLE);
        }

    }
}
