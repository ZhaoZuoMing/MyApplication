package com.myuntils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.administrator.dmonline.R;

import java.util.Timer;
import java.util.TimerTask;

import f.sky.flight.core.Constants;

/**
 * Created by Administrator on 2016/11/23/023.
 */

public class AndroidUtil {

    /**
     * 判断网络是否可用
     * @param activity
     * @return
     */
    public static boolean netOk(Context activity) {
        Context context = activity.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void open(Activity a, Class<? extends Activity> b,
                            boolean finishSelf) {
        Intent intent = new Intent();
        intent.setClass(a, b);
        a.startActivity(intent);
        if (finishSelf) {
            a.finish();
            a.overridePendingTransition(0, 0);
        }
    }
    public static void hideInput(Activity a) {
        InputMethodManager imm = (InputMethodManager) a
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = a.getCurrentFocus();
        if (view != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);// 隐藏软键盘
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
            }
        }, 500);
    }
    public static void hint(Context context, String message) {
        ShowDialog showDialog = new ShowDialog(context, R.style.showdialog,
                Constants.MSG_TYPE_DIALOG, null, message);
        showDialog.show();
    }
    public static void confrim(Activity a, String title, String message,
                               final Runnable confrimTask) {
        ShowDialog showDialog = new ShowDialog(a, R.style.showdialog,
                Constants.CONFIRM_TYPE_DIALOG, title, message, confrimTask);
        showDialog.show();
    }

    public static void alert(Context context, String message) {
        ShowDialog showDialog = new ShowDialog(context, R.style.showdialog,
                Constants.ALERT_TYPE_DIALOG,
                context.getString(R.string.alert_title_txt), message);
        showDialog.show();
    }
    public static void shortToast(Activity activity, String content) {
        Toast.makeText(activity, content, Toast.LENGTH_SHORT).show();
    }
    public static String paraseDate(String date){
        //2016-11-12
        String str = date.substring(5,10);
        return  str;
    }
    public static void showView(View view, boolean visible) {
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static void showSelectAlertDialog(Context c, String title,
                                             String[] items,
                                             android.content.DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(c).setTitle(title).setItems(items, listener)
                .setCancelable(true).setNegativeButton("取消", null).create()
                .show();
    }

}
