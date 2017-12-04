package com.myviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;

import java.util.ArrayList;
import java.util.List;

import f.sky.flight.model.SortModel;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2016/11/21/021.
 */

public class MyCalendarActivity extends Activity {


    private String date = null;// 设置默认选中的日期  格式为 “2014-04-05” 标准DATE格式
    private Button btn_cadenerlast_last;//上一个月
    private Button btn_next_calendar;//下一个月
    private KCalendar calendar;//日历类
    private Button cadender_checktrue_btn;//日历选中按钮
    private TextView popupwindow_calendar_month;
    private Intent  mInyent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_cadener_layout);
        mInyent =new Intent();
        initView();
    }

    /**
     *组件初始化
     */
    private void initView() {

        calendar  = new KCalendar(this);
        btn_cadenerlast_last = (Button) this.findViewById(R.id.btn_cadenerlast_last);
        btn_next_calendar = (Button) this.findViewById(R.id.btn_next_calendar);
        cadender_checktrue_btn = (Button) this.findViewById(R.id.cadender_checktrue_btn);
        popupwindow_calendar_month = (TextView) this.findViewById(R.id.popupwindow_calendar_month);
        calendar = (KCalendar) this.findViewById(R.id.kcalendar_for_id);
        popupwindow_calendar_month.setText(calendar.getCalendarYear()+"年"+calendar.getCalendarMonth()+"月");


        if (null !=date){
            int years = Integer.parseInt(date.substring(0,
                    date.indexOf("-")));
            int month = Integer.parseInt(date.substring(
                    date.indexOf("-") + 1, date.lastIndexOf("-")));
            popupwindow_calendar_month.setText(years + "年" + month + "月");

            calendar.showCalendar(years, month);
            calendar.setCalendarDayBgColor(date,  R.mipmap.calendar_date_focused);

        }
        List<String> list = new ArrayList<String>(); //设置标记列表
        list.add("2014-04-01");
        list.add("2014-04-02");
        calendar.addMarks(list, 0);


        //监听所选中的日期
        calendar.setOnCalendarClickListener(new KCalendar.OnCalendarClickListener() {

            public void onCalendarClick(int row, int col, String dateFormat) {
                int month = Integer.parseInt(dateFormat.substring(
                        dateFormat.indexOf("-") + 1,
                        dateFormat.lastIndexOf("-")));

                if (calendar.getCalendarMonth() - month == 1//跨年跳转
                        || calendar.getCalendarMonth() - month == -11) {
                    calendar.lastMonth();

                } else if (month - calendar.getCalendarMonth() == 1 //跨年跳转
                        || month - calendar.getCalendarMonth() == -11) {
                    calendar.nextMonth();

                } else {
                    calendar.removeAllBgColor();
                    calendar.setCalendarDayBgColor(dateFormat, R.mipmap.calendar_date_focused);

                    date = dateFormat;//最后返回给全局 date
                }
            }
        });


        //监听当前月份
        calendar.setOnCalendarDateChangedListener(new KCalendar.OnCalendarDateChangedListener() {
            public void onCalendarDateChanged(int year, int month) {
                popupwindow_calendar_month
                        .setText(year + "年" + month + "月");
            }
        });

        //上月监听按钮

        btn_cadenerlast_last .setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                calendar.lastMonth();
            }

        });
        //下月监听按钮

        btn_next_calendar .setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                calendar.nextMonth();
            }
        });

        /**
         * 确定按钮
         */
        cadender_checktrue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //2016年11-12
                Log.e(TAG, "onClick: "+date );
                if (null ==date){
                    AndroidUtil.shortToast(MyCalendarActivity.this,"请选择时间");
                    return ;
                }else{
                    MyApp.setTrainStartDate(date);
                    String str1 = date.substring(0,4);
                    String str2 = date.substring(5,7);
                    String str3 = date.substring(8,10);
                    String newStr  = str1+"年"+str2+"月"+str3+"日";
                    cadender_checktrue_btn.setText(newStr);
                    SortModel sortModel = new SortModel();
                    sortModel.setName(newStr);
                    sortModel.setAirPortCode(date);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("model", sortModel);
                    mInyent.putExtras(bundle);
                    setResult(RESULT_OK, mInyent);
                    finish();
                }

            }
        });
    }

    /**
     * 防止用户点击返回键无法取值的问题
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            Bundle bundle = new Bundle();
            bundle.putSerializable("model", null);
            mInyent.putExtras(bundle);
            setResult(RESULT_OK, mInyent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


}
