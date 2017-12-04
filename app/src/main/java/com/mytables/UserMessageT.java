package com.mytables;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import f.sky.flight.core.UserService;
import f.sky.flight.core.WebServUtil;
import f.sky.flight.model.LoginRegResult;
import com.myuntils.AndroidUtil;
import com.myuntils.CheckInputUtil;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2016/12/13/013.
 * 个人信息修改和查看
 */

public class UserMessageT extends AppT {

    private static final int USER_UPDATE_FLAG = 0;
    private EditText mobileTxt, mailTxt, idNumberTxt, nicknameTxt;
    private TextView idTypeTxt,app_title_txt;
    private ImageView app_back_btn;

    private int selectIDType;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.config_info);

        mobileTxt = (EditText) findViewById(R.id.config_info_mobile_txt);
        mailTxt = (EditText) findViewById(R.id.config_info_email_txt);
        idNumberTxt = (EditText) findViewById(R.id.config_info_idNumber_txt);
        nicknameTxt = (EditText) findViewById(R.id.config_info_nickname_txt);
        app_back_btn = (ImageView)findViewById(R.id.app_back_icon);
        idTypeTxt = (TextView) findViewById(R.id.config_info_idType_txt);
        app_title_txt = (TextView)findViewById(R.id.app_title_txt);
        findViewById(R.id.config_info_idType_txt).setOnClickListener(this);
        findViewById(R.id.check_btn).setOnClickListener(this);
        app_back_btn.setOnClickListener(this);
        initPersonInfo();
    }

    private void initPersonInfo() {
        app_title_txt.setText(R.string.config_info_change_txt);
        selectIDType = MyApp.loginRegResult.getUserServerObject().getIDType();
        ((TextView) findViewById(R.id.config_info_id_txt)).setText(this
                .getString(R.string.config_person_id_title)
                + MyApp.loginRegResult.getUserServerObject().getUserName());
        ((TextView) findViewById(R.id.config_info_name_txtview)).setText(this
                .getString(R.string.config_person_name_title)
                + MyApp.loginRegResult.getUserServerObject().getRealName());
        mobileTxt.setText(MyApp.loginRegResult.getUserServerObject().getMobile());
        mailTxt.setText(MyApp.loginRegResult.getUserServerObject().getMail());
        idTypeTxt.setText(WebServUtil.ID_TYPES[selectIDType]);
        idNumberTxt.setText(MyApp.loginRegResult.getUserServerObject().getIDNumber());
        nicknameTxt.setText(MyApp.loginRegResult.getUserServerObject().getNick());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (USER_UPDATE_FLAG == flag) {
            return UserService.UserUpdateInfo(WebServUtil.USER_ID,
                    WebServUtil.PASSWORD, MyApp.loginRegResult
                            .getUserServerObject().getUserName(), nicknameTxt.getText().toString(),
                    mobileTxt.getText().toString(), mailTxt.getText()
                            .toString(), selectIDType, idNumberTxt.getText().toString());//TODO 增加证件类型跟证件号码修改
        }
        return super.doTask(flag, params);
    }

    @Override
    public void taskDone(int flag, Object result) {
        if (null == result) {
            AndroidUtil.alert(this,
                    this.getString(R.string.change_user_info_err));
            return;
        }
        if (flag == USER_UPDATE_FLAG) {
            LoginRegResult loginRegResult = (LoginRegResult) result;
            if (!loginRegResult.isSuccess()) {
                AndroidUtil.alert(this, loginRegResult.getMessage());
                return;
            }
            if (null == loginRegResult.getUserServerObject()
                    || 0 == loginRegResult.getUserServerObject().getId()) {
                AndroidUtil.alert(this,
                        this.getString(R.string.change_user_info_err));
                return;
            }
            MyApp.loginRegResult = loginRegResult;
            AndroidUtil.shortToast(this, this.getString(R.string.change_info_success));
            this.finish();
        }
        super.taskDone(flag, result);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        if(R.id.check_btn == arg0.getId()){
            if (checkInput()) {
                AndroidUtil.confrim(this, null, "你确认要修改个人信息", new Runnable() {
                    @Override
                    public void run() {
                        executeWeb(USER_UPDATE_FLAG,
                                UserMessageT.this.getString(R.string.loading_process));
                    }
                });
            }
        } else if (R.id.config_info_idType_txt == arg0.getId()) {
            new AlertDialog.Builder(this).setTitle("选择证件类型")
                    .setItems(WebServUtil.ID_TYPES, selectPsgIdTypeListener)
                    .setCancelable(true).setNegativeButton("取消", null).create()
                    .show();
        }else if (R.id.app_back_icon==arg0.getId()){
            finish();
        }
    }

    android.content.DialogInterface.OnClickListener selectPsgIdTypeListener = new android.content.DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            selectIDType = which;
            refreshEditIdType();
        }
    };

    private void refreshEditIdType() {
        idTypeTxt.setText(WebServUtil.ID_TYPES[selectIDType]);
    }

    private boolean checkInput() {
        String mobile = mobileTxt.getText().toString();
        String email = mailTxt.getText().toString();
        String idNumber = idNumberTxt.getText().toString();
        String nickname = nicknameTxt.getText().toString();
        if (StringUtils.isBlank(nickname)) {
            AndroidUtil.alert(this, "昵称不能为空!");
            return false;
        }
        if (StringUtils.isEmpty(mobile) && CheckInputUtil.checkMobile(mobile)) {
            AndroidUtil.alert(this, "请输入正确手机号码");
            return false;
        }
        if (StringUtils.isEmpty(email) && !CheckInputUtil.checkEmail(email)) {
            AndroidUtil.alert(this, "请输入正确的邮箱");
            return false;
        }
        if (StringUtils.isBlank(idNumber)) {
            AndroidUtil.alert(this, "证件号码不能为空!");
            return false;
        }
        if (selectIDType == WebServUtil.IDENTITY_TYPE
                && !CheckInputUtil.checkIdCard(idNumber)) {
            AndroidUtil.alert(this, "证件号码输入错误!");
            return false;
        }
        return true;
    }


}
