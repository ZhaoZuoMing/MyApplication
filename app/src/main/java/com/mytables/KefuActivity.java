package com.mytables;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.administrator.dmonline.R;

import com.mytables.AppT;
import com.myuntils.MyAllUntils;

/**
 * Created by Administrator on 2016/11/10/010.
 * 客服
 */

public class KefuActivity extends AppT {
    private  ScrollView mScrollView;
    private  LinearLayout kefu_linearlayout;
    private  TextView kefu_time_txt;
    private RelativeLayout airline_train_hotel_order_lay,qian_zhen_baoxian_lay,dingzhi_lay,huifu_lay,jiesonvip_lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_layout);
        mScrollView = (ScrollView)findViewById(R.id.kefu_listview);
        kefu_linearlayout = (LinearLayout)findViewById(R.id.kefu_linearlayout);
        headView();

    }
    /*添加头部*/
    private void headView() {
        View headView = this.getLayoutInflater().inflate(R.layout.kefu_headview_layout,null);
        kefu_linearlayout.addView(headView);
        kefu_time_txt = (TextView) headView.findViewById(R.id.kefu_time_txt);
        airline_train_hotel_order_lay = (RelativeLayout) headView.findViewById(R.id.airline_train_hotel_order_lay);
        airline_train_hotel_order_lay.setOnClickListener(this);

        kefu_time_txt.setText(MyAllUntils.getNowDateYMDS());
        qian_zhen_baoxian_lay = (RelativeLayout) headView.findViewById(R.id.qian_zhen_baoxian_lay);
        qian_zhen_baoxian_lay.setOnClickListener(this);
        dingzhi_lay = (RelativeLayout) headView.findViewById(R.id.dingzhi_lay);
        dingzhi_lay.setOnClickListener(this);
        huifu_lay = (RelativeLayout) headView.findViewById(R.id.huifu_lay);
        huifu_lay.setOnClickListener(this);
        jiesonvip_lay = (RelativeLayout) headView.findViewById(R.id.jiesonvip_lay);
        jiesonvip_lay.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        switch (arg0.getId()){
            case R.id.airline_train_hotel_order_lay:
                addOneView(getResources().getString(R.string.one_answer),"40088-95808");
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                break;
            case R.id.qian_zhen_baoxian_lay://签证保险，租车办理
                addOneView(getResources().getString(R.string.one_answer),"40088-95808");
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                break;
            case R.id.dingzhi_lay:
                addOneView(getResources().getString(R.string.one_answer),"40088-95808");
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                break;
            case R.id.huifu_lay://会务会展
                addOneView(getResources().getString(R.string.four_answer),"13817530345");
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                break;
            case R.id.jiesonvip_lay://接送机场Vip服务
                addOneView(getResources().getString(R.string.five_answer),"13816062780");
                mScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                break;


        }

    }

    private void addOneView(String content,final String number) {
          View oneView = this.getLayoutInflater().inflate(R.layout.anwser_layout,null);
          TextView answer_time_txt = (TextView) oneView.findViewById(R.id.answer_time_txt);
          TextView answer_content_txt = (TextView) oneView.findViewById(R.id.answer_content_txt);
          answer_time_txt.setText(MyAllUntils.getNowDateYMDS());
          answer_content_txt.setText(content);
          RelativeLayout kefu_answer_rety = (RelativeLayout) oneView.findViewById(R.id.kefu_answer_rety);
          kefu_answer_rety.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + number));
                  startActivity(phoneIntent);
              }
          });
          kefu_linearlayout.addView(oneView);
    }
}
