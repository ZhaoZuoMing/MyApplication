package com.myuntils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2016/11/23/023.
 */

public class SimpleOperation extends AsyncTask<Object, Void, Object> {



    private final TaskDelegate delegate;
    protected final Context mCtx;
    private  boolean showProgress;
    private final int flag;
    public String progressText = "";

    private SimpleOperation(TaskDelegate delegate, Context activity, int flag,
                            String pText) {
        this.delegate = delegate;
        this.mCtx = activity;
        this.flag = flag;
        if (!pText.equals("")){
            Log.e(TAG, "SimpleOperation: "+"--------------->" );
              LoadDialog.show(activity,pText);
            if(null == progressText){
                this.showProgress = false;
            }else{
                this.showProgress = true;
                this.progressText = pText;
            }
         }
    }

    protected void onPreExecute() {

        if (showProgress) {
            LoadDialog.show(mCtx,progressText);

        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
         LoadDialog.dismiss(mCtx);
    }


    public static void execute(TaskDelegate delegate, Context activity,
                               int flag, String progressText, Object... params) {
        SimpleOperation operation = new SimpleOperation(delegate, activity,
                flag, progressText);
        operation.execute(params);
    }

    @Override
    protected Object doInBackground(Object... params) {
        try {
            return delegate.doTask(flag, params);
        } catch (Exception e) {
            Log.e(TAG, "doInBackground: ------网络请求异常-----" );
            e.printStackTrace();
            return null;
        }
    }
    @Override
    protected void onPostExecute(Object result) {
         LoadDialog.dismiss(mCtx);
        delegate.taskDone(flag, result);
    }

}
