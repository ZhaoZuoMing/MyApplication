package com.mytables;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.myuntils.AndroidUtil;
import com.example.administrator.dmonline.R;

import org.apache.commons.lang.StringUtils;

import f.sky.flight.core.Constants;
import f.sky.flight.core.UserService;
import f.sky.flight.core.WebServUtil;
import f.sky.flight.model.LoginRegResult;

/**
 * Created by Administrator on 2016/12/13/013.
 * 密码管理
 */

public class ConfigPasswordT extends AppT {

    private static final int USER_CHANGE_PWD_FLAG = 0;
    private EditText pwdTxt, newPwdTxt, pwdAgainTxt;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.config_pwd);

        findViewById(R.id.check_btn).setOnClickListener(this);

        pwdTxt = (EditText)findViewById(R.id.login_pwd_txt);
        newPwdTxt = (EditText)findViewById(R.id.new_pwd_txt);
        pwdAgainTxt = (EditText)findViewById(R.id.pwd_again_txt);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if(flag == USER_CHANGE_PWD_FLAG){
            return UserService.UserChangePwd(WebServUtil.USER_ID, WebServUtil.PASSWORD, MyApp.loginRegResult.getUserServerObject().getUserName(), newPwdTxt.getText().toString());
        }
        return super.doTask(flag, params);
    }

    @Override
    public void taskDone(int flag, Object result) {
        if (null == result) {
            AndroidUtil.alert(this, this.getString(R.string.change_user_info_err));
            return;
        }
        if (flag == USER_CHANGE_PWD_FLAG) {
            LoginRegResult loginRegResult = (LoginRegResult)result;
            if (!loginRegResult.isSuccess()) {
                AndroidUtil.alert(this, loginRegResult.getMessage());
                return;
            }
            if (null == loginRegResult.getUserServerObject() || 0 == loginRegResult.getUserServerObject().getId()) {
                AndroidUtil.alert(this,
                        this.getString(R.string.change_user_info_err));
                return;
            }
            AndroidUtil.shortToast(this, this.getString(R.string.change_info_success));
            MyApp.loginRegResult = loginRegResult;
            if(getSp(Constants.C_B_SAVEPWD, false)){
                MyApp.getSp().edit().putString(Constants.C_S_PASSWORD, loginRegResult.getUserServerObject().getPassword()).commit();
            }
            this.finish();
        }
        super.taskDone(flag, result);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        if(R.id.check_btn == arg0.getId()){
            if(checkPwd()){
                AndroidUtil.confrim(this, null, "你确认要修改密码", new Runnable() {
                    @Override
                    public void run() {
                        executeWeb(USER_CHANGE_PWD_FLAG, ConfigPasswordT.this.getString(R.string.loading_process));
                    }
                });

            }
        }
    }

    private boolean checkPwd(){
        String pwd = pwdTxt.getText().toString();
        String newPwd = newPwdTxt.getText().toString();
        String againPwd = pwdAgainTxt.getText().toString();
        if(!StringUtils.equals(pwd, MyApp.loginRegResult.getUserServerObject().getPassword())){
            AndroidUtil.alert(this, this.getString(R.string.pwd_error));
            return false;
        }
        if(!StringUtils.equals(newPwd, againPwd)){
            AndroidUtil.alert(this, this.getString(R.string.new_pwd_error));
            return false;
        }
        return true;
    }


}
