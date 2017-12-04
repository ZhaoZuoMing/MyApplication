package com.mytables;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.administrator.dmonline.R;

public class MainActivity extends TabActivity implements CompoundButton.OnCheckedChangeListener{

    private TabHost tabHost;
    private Intent intentHome;
    private Intent intentXingC;
    private Intent intentKeF;
    private Intent intentUser;

    private RadioGroup clientGroup;
    private RadioButton rbHome;
    private RadioButton rbXingC;
    private RadioButton rbKeF;
    private RadioButton rbUser;
    public String whichTab = "";// 当前选中Tab
    private long mExitTime;
    Resources res;// 资源
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = this.getResources();
        /**
         * 跳转至对应的是个Tab
         */
        intentHome = new Intent(this,HomeActivity.class);
        intentXingC = new Intent(this,TravelT.class);
        intentKeF = new Intent(this,KefuActivity.class);
        intentUser = new Intent(this,UserActivity.class);

        InitialRadios();
        InitialTab();
        InitialSelectedTab();
    }

    /**
     * 初始化当选按钮
     */
    private void InitialRadios(){
        rbHome =(RadioButton) this.findViewById(R.id.TabHome);
        rbHome.setOnCheckedChangeListener(this);
        rbXingC =(RadioButton) this.findViewById(R.id.TabXingC);
        rbXingC.setOnCheckedChangeListener(this);
        rbKeF =(RadioButton) this.findViewById(R.id.TabKeF);
        rbKeF.setOnCheckedChangeListener(this);
        rbUser =(RadioButton) this.findViewById(R.id.Tabuser);
        rbUser.setOnCheckedChangeListener(this);
        clientGroup = (RadioGroup) this.findViewById(R.id.clientGroup);
        clientGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                 RadioButton rb = (RadioButton)findViewById(radioButtonId);
//                rb.setTextColor(getResources().getColorStateList(R.drawable.color_radiobutton));
            }
        });
    }

    /**
     * 初始化Tab
     */
    private void InitialTab() {
        tabHost = this.getTabHost();
        tabHost.addTab(buildTabSpec("home", R.string.home, R.mipmap.icon, intentHome));
        tabHost.addTab(buildTabSpec("xingcheng", R.string.xingcheng, R.mipmap.icon, intentXingC));
        tabHost.addTab(buildTabSpec("kefu_home", R.string.kefu,R.mipmap.icon, intentKeF));
        tabHost.addTab(buildTabSpec("user_home", R.string.user,R.mipmap.icon, intentUser));
    }

    /**
     * 设置默认选中Tab
     */
    private void InitialSelectedTab() {
        SharedPreferences settings = getSharedPreferences(
                res.getString(R.string.preferences_key), MODE_PRIVATE);
        whichTab = settings.getString(
                res.getString(R.string.preferences_current_tab), "home");
        if (whichTab.equals("home")) {
            rbHome.setChecked(true);
//           rbHome.setTextColor(getResources().getColorStateList(R.drawable.color_radiobutton));
        } else if (whichTab.equals("xingcheng")) {
            rbXingC.setChecked(true);

        } else if (whichTab.equals("kefu_home")) {
            rbKeF.setChecked(true);

        }else if (whichTab.equals("user_home")){
            rbUser.setChecked(true);
        }

    }
    /**
     * 公用初始化Tab
     */
    private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon, final Intent content) {
        return tabHost.newTabSpec(tag).setIndicator(getString(resLabel),getResources().getDrawable(resIcon)).setContent(content);
    }

    /**
     * 重写选择事件
     * @param buttonView
     * @param isChecked
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            return;
        }


        switch (buttonView.getId()) {
            case R.id.TabHome:
                whichTab = "home";
                tabHost.setCurrentTabByTag("home");
                break;
            case R.id.TabXingC:
                whichTab = "xingcheng";
                tabHost.setCurrentTabByTag("xingcheng");
                break;
            case R.id.TabKeF:
                whichTab = "kefu_home";
                tabHost.setCurrentTabByTag("kefu_home");
                break;
            case R.id.Tabuser:
                whichTab = "user_home";
                tabHost.setCurrentTabByTag("user_home");
                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
               exit();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}