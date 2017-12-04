package cn.skytrip.train.t;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.MyApp;
import com.myuntils.MyAllUntils;
import com.myviews.CaldroidActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.skytrip.train.model.TraintStation;
import donm.sky.hotel.until.TimeUntil;
import f.sky.flight.core.Constants;
import f.sky.flight.model.SortModel;

/**
 * Created by Administrator on 2016/11/18/018.
 */

public class SearchTrainActivity extends Activity {


    private TextView train_start_city;//出发城市
    private TextView train_end_city;//到达城市
    private TextView train_date_select;//出发日期选择
    private View train_arrow_lay;
    private Intent mIntent;
    private int requestCode;
    private StringBuffer buffer;
    private TextView app_title_txt;
    private ImageView app_back_icon;
    private long seleteTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train_layout);
        initView();
        mIntent = new Intent();
        mIntent.setClass(SearchTrainActivity.this, TrainCityChoseT.class);
        buffer = new StringBuffer();
    }

    private void initView() {
        app_title_txt =(TextView) this.findViewById(R.id.app_title_txt);
        app_title_txt.setText("火车票查询");
        app_back_icon = (ImageView) this.findViewById(R.id.app_back_icon);
        train_start_city = (TextView) this.findViewById(R.id.train_start_city);
        train_end_city = (TextView) this.findViewById(R.id.train_end_city);
        train_date_select = (TextView) this.findViewById(R.id.train_date_select);
        train_date_select.setText(MyAllUntils.getNowDate()+"今天");
        MyApp.setTrainStartDate(MyAllUntils.getNowDateF());
        train_arrow_lay = this.findViewById(R.id.train_arrow_lay);
        app_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        train_start_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 requestCode = 3;
                 startActivityForResult(mIntent,requestCode);

            }
        });
        train_end_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCode = 4;
                startActivityForResult(mIntent,requestCode);

            }
        });

        train_arrow_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putLong(Constants.SELECT_TIME, seleteTime);
                intent.putExtras(bundle);
                intent.setClass(SearchTrainActivity.this, CaldroidActivity.class);
                startActivityForResult(intent,10);

            }
        });

    }

    /**
     * 火车票查询
     * @param view
     */
    public  void searchTrainTickets(View view){
        MyAllUntils.open(SearchTrainActivity.this, TrainListActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      if (requestCode==10){
            if (resultCode==Constants.CHOSE_T){
                {
                    seleteTime = data.getLongExtra(Constants.SELECT_DATA_TIME, 0);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = new Date(seleteTime);
                    String t1 = format.format(d1);
                    if (seleteTime > 0) {
                        MyApp.setTrainStartDate(t1);
                        train_date_select.setText(TimeUntil.parseDataToStr(d1));
                    } else {
                        return;
                    }
                }
            }else if (requestCode == Constants.NO_SELECT_T){
                return;
            }
      }
      /**城市选择**/
        SortModel model = (SortModel) data.getSerializableExtra("model");
        if (model==null){
            return;
        }else {
            String cityname = model.getName();
            switch (requestCode){
                case 3://出发城市
                    train_start_city.setText(cityname);
                    TraintStation city = new TraintStation();
                    city.setCode(model.getCityCode());
                    city.setName(model.getName());
                    MyApp.setTrainOrgCity(city);
                    break;
                case 4://到达城市
                    train_end_city.setText(cityname);
                    TraintStation endCity = new TraintStation();
                    endCity.setCode(model.getCityCode());
                    endCity.setName(model.getName());
                    MyApp.setTrainDeptCity(endCity);

                    break;
            }
        }

    }
}
