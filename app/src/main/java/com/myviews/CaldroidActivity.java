package com.myviews;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.wz.caldroid.CalendarCellDecorator;
import com.wz.caldroid.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import f.sky.flight.core.Constants;

/**
 * Created by Administrator on 2017/5/15/015.
 * 日期选择类
 */

public class CaldroidActivity extends Activity {
    private CalendarPickerView calendar;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myall_caldroid_lay);
        textView = (TextView) findViewById(R.id.app_title_txt);
        textView.setText("日期选择");
        //获取传进的值
        Bundle myBundle = getIntent().getExtras();
        long seleteTime  = myBundle.getLong(Constants.SELECT_TIME);

        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.MONTH, 3);

        final Calendar lastYear = Calendar.getInstance();
        lastYear.add(Calendar.MONTH, 0);

        calendar = findViewById(R.id.calendar_view);
        Calendar today = Calendar.getInstance();
        ArrayList<Date> dates = new ArrayList<Date>();

        if (seleteTime>0){
            Date d1=new Date(seleteTime);
            dates.add(d1);
        }else{
            dates.add(today.getTime());
        }

        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendar.init(lastYear.getTime(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
                .withSelectedDate(dates.get(0));
        initButtonListeners();
    }

    private void initButtonListeners() {
        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECT_DATA_TIME, calendar.getSelectedDate().getTime());
                setResult(Constants.CHOSE_T, intent);
                finish();
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

        View titlebar_img_back=findViewById(R.id.app_back_icon);
        titlebar_img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECT_DATA_TIME, 0);
                setResult(Constants.NO_SELECT_T, intent);
                finish();
            }
        });
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
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
            Intent intent = new Intent();
            intent.putExtra(Constants.SELECT_DATA_TIME, 0);
            setResult(Constants.NO_SELECT_T, intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
