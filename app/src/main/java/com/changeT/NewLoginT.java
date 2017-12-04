package com.changeT;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.changJson.API;
import com.changJson.QuerUserSyc;
import com.example.administrator.dmonline.R;
import com.mymodels.UserMsg;
import com.mytables.AppT;
import com.mytables.MainActivity;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;

import f.sky.flight.core.Constants;
import f.sky.flight.model.Credential;

/**
 * Created by zhaody on 2017/11/1.
 * 新的用户登录界面
 */

public class NewLoginT extends AppT {
    private static  int USER_LOGIN=0;
    private EditText edit_username;
    private EditText edit_password ;
    private CheckBox save_paw_username;
    private Boolean savePwd;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.newlogin_layout);
        edit_username =  findViewById(R.id.edit_username);
        edit_password =  findViewById(R.id.edit_password);
        save_paw_username = findViewById(R.id.save_paw_username);
        savePwd = getSp(Constants.C_B_SAVEPWD, false);
        String username = getSp(Constants.C_S_USERNAME, null);
        String password = getSp(Constants.C_S_PASSWORD, null);
        if (savePwd){
            edit_username.setText(username);
            edit_password.setText(password);
        }
    }

    /**
     * 去登录
     * @param view
     */
    public void loginButton(View view){
        if (save_paw_username.isChecked()){
            MyApp.getSp().edit().putString(Constants.C_S_USERNAME, edit_username.getText().toString())
                    .putString(Constants.C_S_PASSWORD, edit_password.getText().toString()).commit();
            //保存是否记住密码和登录状态 这里是选中则为记住密码
            saveLoginInfo(true,true);
         }else {
            //不记住密码
            MyApp.getSp().edit().putString(Constants.C_S_USERNAME, null)
                    .putString(Constants.C_S_PASSWORD, null).commit();
            saveLoginInfo(false,false);
        }
        executeWeb(USER_LOGIN,"正在登录...");
    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag==USER_LOGIN){
            //请求登录
              UserMsg userMsg = QuerUserSyc.querLoginUser(edit_username.getText().toString(),edit_password.getText().toString());
              //下载需要的数据
              String credential = QuerUserSyc.querCredential(userMsg.getTicket());
              //如果是第一次登录就获取或者更新数据
               userMsg.setCredential(credential);
              return userMsg;
        }
        return super.doTask(flag, params);
    }

    @Override
    public void taskDone(int flag, Object result) {
        super.taskDone(flag, result);
        UserMsg userMsg = null;
        if (flag==USER_LOGIN){
            userMsg = (UserMsg)result;
            if (userMsg.isSuccess()&&userMsg.getTicket()!=null&&userMsg.getCredential()!=null){
                //存User对象
                saveUserMSG(userMsg);
                AndroidUtil.open(NewLoginT.this, MainActivity.class,true);
            }else {
                AndroidUtil.shortToast(this,userMsg.getMessage());
                return;
            }
        }
    }

    /**
     * 存User对象数据
     * @param userMsg
     */
   private void saveUserMSG(UserMsg userMsg){
        MyApp.getSp().edit().putString(API.TICKET,userMsg.getTicket()).putString(API.NAME,userMsg.getName()).putString(API.CMSID,userMsg.getCmsId()).putString(API.HRID,userMsg.getHrId()).putString(API.ID,userMsg.getId()).putString(API.TMCID,userMsg.getTmcId()).putString(API.CREDENTIAL,userMsg.getCredential()).commit();

   }
    /**
     *
     * @param savePwd
     * @param autoLogin
     */
    private void saveLoginInfo(boolean savePwd, boolean autoLogin){
        MyApp.getSp().edit().putBoolean(Constants.C_B_SAVEPWD, savePwd)
                .putBoolean(Constants.C_B_AUTOLOGIN, autoLogin).commit();
    }



}
