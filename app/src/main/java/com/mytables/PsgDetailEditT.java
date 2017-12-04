package com.mytables;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import f.sky.flight.core.Constants;
import f.sky.flight.core.UserService;
import f.sky.flight.core.WebServUtil;
import f.sky.flight.model.B_TouristDOObj;
import f.sky.flight.model.PsgOperaResult;
import com.myuntils.AndroidUtil;
import com.myuntils.CheckInputUtil;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2016/12/12/012.
 *  常旅客信息管理/编辑界面
 */

public class PsgDetailEditT  extends  AppT{

    private LinearLayout editPsgLayout;
    private TextView editShowPsgTitleTxt, editPsgIdTypeTxt;
    private EditText editPsgNameTxt, editPsgNickNameTxt, editPsgMobileTxt,
            editPsgIdNumberTxt;
   private ImageView app_back_icon;
    private boolean isEdit;
    private boolean isAdd;
    private B_TouristDOObj touristDOObj;
    private int selectIDType;

    private final static int PSG_UPDATE_FLAG = 0;

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.psg_detail_msg);

        isEdit = this.getIntent().getBooleanExtra(Constants.PSG_IS_EDIT, false);
        isAdd = this.getIntent().getBooleanExtra(Constants.PSG_IS_ADD, false);
        touristDOObj = (B_TouristDOObj) MyApp.args.get(PsgDetailEditT.class
                .getSimpleName());

        editShowPsgTitleTxt = (TextView) findViewById(R.id.app_title_txt);
        editPsgLayout = (LinearLayout) findViewById(R.id.edit_or_add_psg_layout);

        editPsgIdTypeTxt = (TextView) findViewById(R.id.edit_psg_idType_txt);
        editPsgNameTxt = (EditText) findViewById(R.id.edit_psg_name_txt);
        editPsgNickNameTxt = (EditText) findViewById(R.id.edit_psg_nickname_txt);
        editPsgMobileTxt = (EditText) findViewById(R.id.edit_psg_mobile_txt);
        editPsgIdNumberTxt = (EditText) findViewById(R.id.edit_psg_idNumber_txt);
        findViewById(R.id.app_back_icon).setOnClickListener(this);
        findViewById(R.id.save_psg_btn).setOnClickListener(this);
        findViewById(R.id.edit_psg_idType_txt).setOnClickListener(this);

        if (isEdit) {
            editPsgLayout.setVisibility(View.VISIBLE);
            if (isAdd) {
                editShowPsgTitleTxt.setText(this
                        .getString(R.string.add_psg_txt));
            } else {
                initPsgInfo(touristDOObj);
                editShowPsgTitleTxt.setText(this
                        .getString(R.string.edit_psg_txt));
            }
        } else {
            editPsgLayout.setVisibility(View.GONE);
            editShowPsgTitleTxt
                    .setText(this.getString(R.string.psg_detail_txt));
            initPsgInfo(touristDOObj);
        }
    }

    private void initPsgInfo(B_TouristDOObj touristDOObj) {
        selectIDType = touristDOObj.getIDType();
        if (isEdit) {
            editPsgNameTxt.setText(touristDOObj.getTouristName());
            editPsgNickNameTxt.setText(touristDOObj.getField2());
            editPsgMobileTxt.setText(touristDOObj.getMobileNo());
            editPsgIdTypeTxt.setText(WebServUtil.ID_TYPES[touristDOObj
                    .getIDType()]);
            editPsgIdNumberTxt.setText(touristDOObj.getIDNumber());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag == PSG_UPDATE_FLAG) {
            if (isAdd) {
                return UserService.psgUpdate(WebServUtil.USER_ID,
                        WebServUtil.PASSWORD, MyApp.loginRegResult
                                .getUserServerObject().getUserName(),
                        Constants.ZERO, MyApp.loginRegResult
                                .getUserServerObject().getId(), editPsgNameTxt
                                .getText().toString(), editPsgMobileTxt
                                .getText().toString(), Constants.EMPTY_STRING,
                        selectIDType, editPsgIdNumberTxt.getText().toString(),
                        MyApp.loginRegResult.getUserServerObject()
                                .getUserOrgObj().getClientID(),
                        editPsgNickNameTxt.getText().toString());
            } else {
                return UserService.psgUpdate(WebServUtil.USER_ID,
                        WebServUtil.PASSWORD, MyApp.loginRegResult
                                .getUserServerObject().getUserName(),
                        touristDOObj.getTouristID(), MyApp.loginRegResult
                                .getUserServerObject().getId(), editPsgNameTxt
                                .getText().toString(), editPsgMobileTxt
                                .getText().toString(), touristDOObj
                                .getAddress(), selectIDType, editPsgIdNumberTxt
                                .getText().toString(), MyApp.loginRegResult
                                .getUserServerObject().getUserOrgObj()
                                .getClientID(), editPsgNickNameTxt.getText().toString());
            }
        }
        return super.doTask(flag, params);
    }

    @Override
    public void taskDone(int flag, Object result) {
        if (null == result) {
            AndroidUtil.alert(this,
                    this.getString(R.string.update_psg_info_err));
            return;
        }
        PsgOperaResult psgOperaResult = (PsgOperaResult) result;
        if (!psgOperaResult.isSuccess()) {
            AndroidUtil.alert(this, psgOperaResult.getMessage());
            return;
        }
        if (null == psgOperaResult.getTouristDOObj()
                || psgOperaResult.getTouristDOObj().getTouristID() == 0) {
            AndroidUtil.alert(this,
                    this.getString(R.string.update_psg_info_err));
            return;
        }
        if (!isAdd) {
            for (B_TouristDOObj touristDOObj : MyApp.queryPsgResult
                    .getB_TouristDO()) {
                if (touristDOObj.getTouristID() == psgOperaResult
                        .getTouristDOObj().getTouristID()) {
                    MyApp.queryPsgResult.getB_TouristDO().remove(touristDOObj);
                    break;
                }
            }
        }
        MyApp.queryPsgResult.getB_TouristDO().add(
                psgOperaResult.getTouristDOObj());
        this.finish();
        super.taskDone(flag, result);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
//		if (R.id.edit_btn == arg0.getId()) {
//			editPsgLayout.setVisibility(View.VISIBLE);
//			isEdit = true;
//			initPsgInfo(touristDOObj);
//			editShowPsgTitleTxt.setText(this.getString(R.string.edit_psg_txt));
//		} else
        if (R.id.edit_psg_idType_txt == arg0.getId()) {
            new AlertDialog.Builder(this).setTitle("选择证件类型")
                    .setItems(WebServUtil.ID_TYPES, selectPsgIdTypeListener)
                    .setCancelable(true).setNegativeButton("取消", null).create()
                    .show();
        } else if (R.id.save_psg_btn == arg0.getId()) {
            if (checkInput()) {
                executeWeb(PSG_UPDATE_FLAG,
                        this.getString(R.string.loading_process));
            }
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
        editPsgIdTypeTxt.setText(WebServUtil.ID_TYPES[selectIDType]);
    }

    private boolean checkInput() {
        String realName = editPsgNameTxt.getText().toString();
        String mobile = editPsgMobileTxt.getText().toString();
        String idNumber = editPsgIdNumberTxt.getText().toString();
        String nickname = editPsgNickNameTxt.getText().toString();
        if (StringUtils.isBlank(realName)) {
            AndroidUtil.alert(this, "旅客姓名不能为空!");
            return false;
        }
        if (StringUtils.isBlank(nickname)) {
            AndroidUtil.alert(this, "旅客昵称不能为空!");
            return false;
        }
        if (StringUtils.isBlank(mobile) || !CheckInputUtil.checkMobile(mobile)) {
            AndroidUtil.alert(this, "号码输入错误!");
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
