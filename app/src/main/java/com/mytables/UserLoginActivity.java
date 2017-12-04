package com.mytables;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.administrator.dmonline.R;
import com.myuntils.AndroidUtil;

import org.apache.commons.lang.StringUtils;

import f.sky.flight.core.Constants;
import f.sky.flight.core.OrderService;
import f.sky.flight.core.QueryService;
import f.sky.flight.core.UserService;
import f.sky.flight.core.WebServUtil;
import f.sky.flight.model.F_VersionObj;
import f.sky.flight.model.LoginRegResult;
import f.sky.flight.model.QueryAirlineResult;
import f.sky.flight.model.QueryAirportResult;
import f.sky.flight.model.QueryAuditMailResult;
import f.sky.flight.model.QueryCostCenterResult;
import f.sky.flight.model.QueryDataVersionResult;
import f.sky.flight.model.QueryFlightRoleResult;
import f.sky.flight.model.QueryPlaneStyleResult;
import f.sky.flight.model.QueryReasonCodeResult;

/**
 * Created by Administrator on 2016/11/23/023.
 */

public class UserLoginActivity extends AppT {


    private final static int LOGIN_FLAG = 0;

    private EditText usernameET, passwordET;
    private boolean autoLogin, savePwd;
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.login);

        usernameET = (EditText) findViewById(R.id.login_sign_txt);
        passwordET = (EditText) findViewById(R.id.login_pwd_txt);

        autoLogin = getSp(Constants.C_B_AUTOLOGIN, false);
        savePwd = getSp(Constants.C_B_SAVEPWD, false);
        String username = getSp(Constants.C_S_USERNAME, null);
        String password = getSp(Constants.C_S_PASSWORD, null);

        if(MyApp.debug){
            usernameET.setText("test90"); //ADmin
            passwordET.setText("123456");  //zhangjun
//              usernameET.setText("ADmin"); //ADmin
//              passwordET.setText("zhangjun");  //zhangjun
        }

        if(savePwd && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
            usernameET.setText(username);
            passwordET.setText(password);
        }

        updateSavePwdPart(savePwd);
        if(autoLogin){
            updateAutoLoginPart(autoLogin);
        }

        findViewById(R.id.login_btn).setOnClickListener(this);
        findViewById(R.id.save_password_btn).setOnClickListener(this);
        findViewById(R.id.auto_login_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        super.onClick(arg0);
        if(arg0.getId() == R.id.login_btn){
            String checkResult = checkLoginInput();
            if(null == checkResult){
                executeWeb(LOGIN_FLAG, this.getString(R.string.login_process));
            } else {
                AndroidUtil.alert(this, checkResult);
            }
        } else if (arg0.getId() == R.id.save_password_btn){
            savePwd = !savePwd;
            updateSavePwdPart(savePwd);
        } else if (arg0.getId() == R.id.auto_login_btn){
            autoLogin = !autoLogin;
            updateAutoLoginPart(autoLogin);
        }
    }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag == LOGIN_FLAG) {
            QueryDataVersionResult queryDataVersionResult = QueryService.QueryDataVersion(WebServUtil.USER_ID, WebServUtil.PASSWORD, WebServUtil.SYSTEM_ANDROID);
            if(null == queryDataVersionResult){
                return null;
            }
            if(!queryDataVersionResult.isSuccess()){
                return null;
            }

            //如果需要跟新就下载数据到本地并在内存建立对象,保存版本信息到手机,如果不是在把本地文件建立对象
            for(F_VersionObj f: queryDataVersionResult.getVersionObjL()){
                //更新机场信息数据
                if(StringUtils.equalsIgnoreCase("Airport", f.getDataType())){
                    initCurrentVersionAirportData(f);
                }
                //更新航线数据
                if(StringUtils.equalsIgnoreCase("AirLine", f.getDataType())){
                    initCurrentVersionAirlineData(f);
                }
                //更新机型的数据
                if(StringUtils.equalsIgnoreCase("Planestyle", f.getDataType())){
                    initCurrentVersionPlaneStyleData(f);
                }
            }


            String username = usernameET.getText().toString();
            String password = passwordET.getText().toString();

            LoginRegResult loginRegResult = UserService.userLogin(WebServUtil.USER_ID, WebServUtil.PASSWORD, username, password);

            if(loginIsSuccess(loginRegResult)){
                //登录成功之后，在这里初始化用户所有的数据
                QueryReasonCodeResult queryReasonCodeResultTemp = QueryService.queryReasonCode(WebServUtil.USER_ID, WebServUtil.PASSWORD, username);
                if(null != queryReasonCodeResultTemp && queryReasonCodeResultTemp.isSuccess()){
                    MyApp.queryReasonCodeResult = queryReasonCodeResultTemp;
                }
                QueryCostCenterResult queryCostCenterResultTemp = OrderService.costCenterQuery(WebServUtil.USER_ID, WebServUtil.PASSWORD, username);
                if(null != queryCostCenterResultTemp && queryCostCenterResultTemp.isSuccess()){
                    MyApp.queryCostCenterResult = queryCostCenterResultTemp;
                }
                QueryAuditMailResult queryAuditMailResultTemp = QueryService.queryAuditMail(WebServUtil.USER_ID, WebServUtil.PASSWORD, username);
                if(null != queryAuditMailResultTemp && queryAuditMailResultTemp.isSuccess()){
                    MyApp.queryAuditMailResult = queryAuditMailResultTemp;
                }
                QueryFlightRoleResult queryFlightRoleResultTemp = QueryService.GetFlightRole(WebServUtil.USER_ID, WebServUtil.PASSWORD, username);
                if(null != queryFlightRoleResultTemp && queryFlightRoleResultTemp.isSuccess()){
                    MyApp.queryFlightRoleResult = queryFlightRoleResultTemp;
                }
            }
            return loginRegResult;
        }
        return super.doTask(flag, params);
    }

    @Override
    public void taskDone(int flag, Object result) {
        if (flag == LOGIN_FLAG) {
            if (null == result) {
                AndroidUtil.alert(this, this.getString(R.string.login_result_fail));
                return;
            }
            MyApp.loginRegResult = (LoginRegResult)result;
            if(!loginIsSuccess(MyApp.loginRegResult)){
                AndroidUtil.alert(this, MyApp.loginRegResult.getMessage());
                return;
            }
            String username = usernameET.getText().toString();
            String password = passwordET.getText().toString();
            saveLoginInfo(savePwd, autoLogin);

            if(savePwd){
                MyApp.getSp().edit().putString(Constants.C_S_USERNAME, username)
                        .putString(Constants.C_S_PASSWORD, password).commit();
            }
            if(MyApp.loginRegResult.getUserServerObject().getPosition() == 0){
                AndroidUtil.shortToast(this, "请确认账号信息进行修改");
                AndroidUtil.open(this, ConfigPasswordT.class, true);
                return;
            }


            //在所有数据初始化完成之后，在最后再打开主界面
            AndroidUtil.open(this, MainActivity.class, true);
        }
        super.taskDone(flag, result);
    }

    private void saveLoginInfo(boolean savePwd, boolean autoLogin){
        MyApp.getSp().edit().putBoolean(Constants.C_B_SAVEPWD, savePwd)
                .putBoolean(Constants.C_B_AUTOLOGIN, autoLogin).commit();
    }

    private void updateAutoLoginPart(boolean autoLogin){
        savePwd = autoLogin;
        if (autoLogin) {
            ((ImageButton)findViewById(R.id.save_password_btn)).setImageResource(R.mipmap.check);
            ((ImageButton)findViewById(R.id.auto_login_btn)).setImageResource(R.mipmap.check);
        } else {
            ((ImageButton)findViewById(R.id.auto_login_btn)).setImageResource(R.mipmap.uncheck);
        }
    }

    private void updateSavePwdPart(boolean savePwd){
        if (savePwd) {
            ((ImageButton)findViewById(R.id.save_password_btn)).setImageResource(R.mipmap.check);
        } else {
            autoLogin = savePwd;
            ((ImageButton)findViewById(R.id.save_password_btn)).setImageResource(R.mipmap.uncheck);
            ((ImageButton)findViewById(R.id.auto_login_btn)).setImageResource(R.mipmap.uncheck);
        }
    }

    private String checkLoginInput(){
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        if (StringUtils.isBlank(username)) {
            return "请输入用户名";
        }
        if (StringUtils.isBlank(password)) {
            return "请输入用户密码";
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        AndroidUtil.confrim(this, null, "你确认要退出?", new Runnable() {
            public void run() {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }

    private void initCurrentVersionAirportData(F_VersionObj f)throws Exception{
//        if(f.getVersion().compareTo(MyApp.getCurrentAirportVersion()) > 0){//需要下载
            QueryAirportResult queryAirportResultTemp =null;
            try {
                queryAirportResultTemp= QueryService.queryAirport(UserLoginActivity.this,WebServUtil.USER_ID, WebServUtil.PASSWORD);
            }catch (Exception e){
                Log.e("-------", " 保存文件失败: ");
                e.printStackTrace();
            }
            if(null != queryAirportResultTemp && queryAirportResultTemp.isSuccess()){
                MyApp.queryAirportResult = queryAirportResultTemp;
                MyApp.getSp().edit().putString(Constants.AIRPORT_DATA_VERSION, f.getVersion()).commit();
            }
//        } else {
////            String response = FileUtils.readFileToString(FileUtil.getAirportFile(), "utf-8");
//              String response = HotelUntils.getAirport(this);
//              Log.e("-----airportSaveData---", "initCurrentVersionAirportData: "+response);
//            if(StringUtils.isBlank(response) || -1 == response.indexOf("<QueryAirportResult>")){
//                //文件不存在或者内容不对
//                MyApp.queryAirportResult = QueryService.queryAirport(UserLoginActivity.this,WebServUtil.USER_ID, WebServUtil.PASSWORD);
//            } else {
//                int index = response.indexOf("<QueryAirportResult>");
//                int endIndex = response.lastIndexOf("</QueryAirportResult>");
//                response = response.substring(index, endIndex + "</QueryAirportResult>".length());
//                MyApp.queryAirportResult = QueryService.getQueryAirportObject(response);
//            }
//        }
    }

    private void initCurrentVersionAirlineData(F_VersionObj f)throws Exception{
//        if(f.getVersion().compareTo(MyApp.getCurrentAirlineVersion()) > 0){
            QueryAirlineResult queryAirlineResultTemp = QueryService.queryAirlines(WebServUtil.USER_ID, WebServUtil.PASSWORD);
            if(null != queryAirlineResultTemp && queryAirlineResultTemp.isSuccess()){
                MyApp.queryAirlineResult = queryAirlineResultTemp;
                MyApp.getSp().edit().putString(Constants.AIRLINE_DATA_VERSION, f.getVersion()).commit();
            }
//        } else {
//            String response = FileUtils.readFileToString(FileUtil.getAirlineFile(), "utf-8");
//            if(StringUtils.isBlank(response) || -1 == response.indexOf("<QueryAirlinesResult>")){
//                MyApp.queryAirlineResult = QueryService.queryAirlines(WebServUtil.USER_ID, WebServUtil.PASSWORD);
//            } else {
//                int index = response.indexOf("<QueryAirlinesResult>");
//                int endIndex = response.lastIndexOf("</QueryAirlinesResult>");
//                response = response.substring(index, endIndex + "</QueryAirlinesResult>".length());
//                MyApp.queryAirlineResult = QueryService.getQueryAirlineObject(response);
//            }
//        }
    }

    private void initCurrentVersionPlaneStyleData(F_VersionObj f)throws Exception{
//        if(null!=f && f.getVersion().compareTo(MyApp.getCurrentPlaneStyleVersion()) > 0){
            QueryPlaneStyleResult queryPlaneStyleResult = QueryService.queryPlaneStyle(WebServUtil.USER_ID, WebServUtil.PASSWORD);
            if(null != queryPlaneStyleResult && queryPlaneStyleResult.isSuccess()){
                MyApp.queryPlaneStyleResult = queryPlaneStyleResult;
                MyApp.getSp().edit().putString(Constants.PLANESTYLE_DATA_VERSION, f.getVersion()).commit();
            }
//        } else {
//            String response = FileUtils.readFileToString(FileUtil.getPlaneStyleFile(), "utf-8");
//            if(StringUtils.isBlank(response) || -1 == response.indexOf("<QueryPlaneStyleResult>")){//文件不存在或者内容不对
//                MyApp.queryPlaneStyleResult = QueryService.queryPlaneStyle(WebServUtil.USER_ID, WebServUtil.PASSWORD);
//            } else {
//                int index = response.indexOf("<QueryPlaneStyleResult>");
//                int endIndex = response.lastIndexOf("</QueryPlaneStyleResult>");
//                response = response.substring(index, endIndex + "</QueryPlaneStyleResult>".length());
//                MyApp.queryPlaneStyleResult = QueryService.getQueryPlaneStyleObject(response);
//            }
//        }
    }

}
