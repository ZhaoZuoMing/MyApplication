package com.mytables;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.administrator.dmonline.R;
import com.myuntils.AndroidUtil;
import com.myuntils.SimpleOperation;
import com.myuntils.TaskDelegate;

import f.sky.flight.model.LoginRegResult;

/**
 * Created by Administrator on 2016/11/23/023.
 */

public class AppT extends BaseActivity implements View.OnClickListener,TaskDelegate{

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void onClick(View arg0) {
    }

    @Override
    public void taskDone(int flag, Object result) {

    }
    public void goBack() {
        AndroidUtil.hideInput(this);
        this.finish();
    }
    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        return null;
    }
    /** 网络检查 */
    public final boolean isNetOk() {
        return AndroidUtil.netOk(this);
    }

    /** asyn task */
    public void executeWeb(int flag, String progressText, Object... arg) {
        if (isNetOk()) {
            SimpleOperation.execute(AppT.this, AppT.this, flag, progressText, arg);
        } else {
            AndroidUtil.alert(this, this.getString(R.string.alert_no_net));
        }
    }
    public void executeLocal(int flag, String progressText, Object... arg) {
        SimpleOperation.execute(this, this, flag, progressText, arg);
    }

    /**
     * 判断是否有保存密码
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean getSp(String key, boolean defaultValue) {
        return MyApp.getSp().getBoolean(key, defaultValue);
    }

    public String getSp(String key, String defaultValue) {
        return MyApp.getSp().getString(key, defaultValue);
    }

    public int getSp(String key, int defaultValue) {
        return MyApp.getSp().getInt(key, defaultValue);
    }

    public boolean loginIsSuccess(LoginRegResult loginRegResult){
        if(null == loginRegResult){
            return false;
        }
        if(!loginRegResult.isSuccess()){
            return false;
        }
        if(null == loginRegResult.getUserServerObject()){
            return false;
        }
        if(loginRegResult.getUserServerObject().getId() == 0){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
