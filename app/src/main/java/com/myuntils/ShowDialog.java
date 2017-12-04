package com.myuntils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import f.sky.flight.core.Constants;

import org.apache.commons.lang.StringUtils;

public class ShowDialog extends Dialog implements OnClickListener {
	private String title;
	private String mesaage;
	
	private int type;
	private Runnable confrimTask;
	
	public ShowDialog(Context context, int theme, int type, String title, String mesaage) {
		super(context, theme);
		this.type = type;
		this.title = title;
		this.mesaage = mesaage;
		WindowManager.LayoutParams lp=getWindow().getAttributes();
		lp.dimAmount=0.5f;
		getWindow().setAttributes(lp);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	}
	
	public ShowDialog(Context context, int theme, int type, String title, String mesaage, Runnable confrimTask) {
		super(context, theme);
		this.type = type;
		this.title = title;
		this.mesaage = mesaage;
		this.confrimTask = confrimTask;
		WindowManager.LayoutParams lp=getWindow().getAttributes();
		lp.dimAmount=0.5f;
		getWindow().setAttributes(lp);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
	}

	public ShowDialog(Context context) {
		super(context);
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(type == Constants.MSG_TYPE_DIALOG){
			setContentView(R.layout.show_msg_dialog);
		} else if (type == Constants.ALERT_TYPE_DIALOG){
			setContentView(R.layout.show_alert_dialog);
			((TextView)findViewById(R.id.dialog_title_txt)).setText(title);
		} else if (type == Constants.CONFIRM_TYPE_DIALOG){
			setContentView(R.layout.show_confirm_dialog);
			
			Button dialogCancelBtn = (Button)findViewById(R.id.dialog_cancel_btn);
			dialogCancelBtn.setText(this.getContext().getString(R.string.cancel_txt));
			dialogCancelBtn.setOnClickListener(this);
			
			TextView titleTxt = ((TextView)findViewById(R.id.dialog_title_txt));
			if(StringUtils.isNotBlank(title)){
				titleTxt.setVisibility(View.VISIBLE);
				titleTxt.setText(title);
			} else {
				titleTxt.setVisibility(View.GONE);
			}
		}
		
		((TextView)findViewById(R.id.dialog_message_txt)).setText(mesaage);
		
		Button dialogCheckBtn = (Button)findViewById(R.id.dialog_check_btn);
		dialogCheckBtn.setText(this.getContext().getString(R.string.check_btn_txt));
		dialogCheckBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		this.dismiss();
		if(v.getId() == R.id.dialog_check_btn){
			if(type == Constants.CONFIRM_TYPE_DIALOG){
				if(null != confrimTask){
					confrimTask.run();
				}
			}
		}
	}
}
