package com.mytables;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.myuntils.AndroidUtil;
import com.myuntils.MyAllUntils;

/**
 * Created by Administrator on 2016/11/10/010.
 */

public class UserActivity extends AppT {

    private RelativeLayout my_allorder_lay,my_message_lay,my_company_lay,my_fapiao_lay,my_card_lay,my_traval_lay;
    private TextView psg_name_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);
        initView();
    }

    private void initView() {
        my_allorder_lay = (RelativeLayout)findViewById(R.id.my_allorder_lay);
        my_message_lay = (RelativeLayout)findViewById(R.id.my_message_lay);
        my_company_lay = (RelativeLayout)findViewById(R.id.my_company_lay) ;
        my_fapiao_lay = (RelativeLayout)findViewById(R.id.my_fapiao_lay);
        psg_name_txt = (TextView)findViewById(R.id.psg_name_txt) ;
        my_card_lay = (RelativeLayout)findViewById(R.id.my_card_lay);
        my_traval_lay = (RelativeLayout)findViewById(R.id.my_traval_lay);


        my_allorder_lay.setOnClickListener(this);
        my_message_lay.setOnClickListener(this);
        my_company_lay.setOnClickListener(this);
        my_fapiao_lay.setOnClickListener(this);
        my_card_lay.setOnClickListener(this);
        my_traval_lay.setOnClickListener(this);
        initDate();
    }

    private void initDate() {
//        psg_name_txt.setText("Hi，"+ MyApp.loginRegResult.getUserServerObject().getRealName()+"\t欢迎回来");
    }

    @Override
    public void onClick(View v) {

        if (R.id.my_traval_lay==v.getId()){//常旅客管理
//            MyAllUntils.open(UserActivity.this, PsgManagerT.class);
        }else if(R.id.my_traval_lay==v.getId()){//密码修改
            MyAllUntils.open(UserActivity.this, ConfigPasswordT.class);
        }else if (R.id.my_card_lay==v.getId()){//我的证件
            AndroidUtil.shortToast(this,"此功能暂未开放");
        }else if (R.id.my_company_lay==v.getId()){//我的公司
            AndroidUtil.shortToast(this,"此功能暂未开放");
        }else if (R.id.my_message_lay==v.getId()){//个人信息
           MyAllUntils.open(UserActivity.this, UserMessageT.class);
        }else if (R.id.my_allorder_lay==v.getId()){
//            MyAllUntils.open(UserActivity.this,MyOrderT.class);
        }
        super.onClick(v);

    }
}
