package com.mytables;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.administrator.dmonline.R;
import com.changeT.NewLoginT;
import com.myuntils.AndroidUtil;

import f.sky.flight.core.Constants;

/**
 * Created by Administrator on 2016/12/27/027.
 * 初始化界面
 */

public class SplashActivity extends AppT{

    private static final int INIT_SERVER_INFO_FLAG = 0;
    private static final int LOGIN_FLAG = 1;
    private String username;
    private String password;
    private boolean autoLogin;
    private  String url = "http://test.api.sky-trip.com/account/login?name=T2G00210&password=T2G00210&token=D42E7EDE1B7540AAA23E0599E234C406";
    String url_1 = "http://test.api.sky-trip.com/account/login?name=&password=&tag=TmcId&token=D42E7EDE1B7540AAA23E0599E234C406";//71284e8aa9a44205a9a93316b2d45de6
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.splash);

        autoLogin = getSp(Constants.C_B_AUTOLOGIN, false);
        username = getSp(Constants.C_S_USERNAME, null);
        password = getSp(Constants.C_S_PASSWORD, null);

       new Handler(new Handler.Callback() {
           @Override
           public boolean handleMessage(Message message) {
               if (autoLogin==false){
                  AndroidUtil.open(SplashActivity.this,MainActivity.class,true);
               }else {
                   AndroidUtil.open(SplashActivity.this,NewLoginT.class,true);
               }
               return false;
           }
       }).sendEmptyMessageDelayed(0,2000);

    }




}
