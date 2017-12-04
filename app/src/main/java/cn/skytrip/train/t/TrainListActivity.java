package cn.skytrip.train.t;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.AppT;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;
import com.myuntils.MyAllUntils;
import com.myviews.CaldroidActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import cn.skytrip.train.adapter.TrainListAdapter;
import cn.skytrip.train.adapter.TrainListAdapter.ViewHolder;
import cn.skytrip.train.json.TrainQuerJs;
import cn.skytrip.train.model.TrainObj;
import cn.skytrip.train.model.TrainQuerObj;
import cn.skytrip.train.model.TrainUntil;
import f.sky.flight.core.Constants;
import f.sky.flight.core.WebServUtil;

import static com.mytables.MyApp.getTrainStartDate;

/**
 * Created by Administrator on 2016/12/15/015.
 * 火车票查询界面
 */

public class TrainListActivity extends AppT implements CompoundButton.OnCheckedChangeListener{

    private static  int SEARCH_TRAIN_TICKET=0;
    private static int SEARCH_TRAIN_LEFTDAY=1;
    private static int SEARCH_TRAIN_RIGHTDAY=2;
    private List<TrainObj> trainlist ;
    private List<TrainObj> allTrainList;
    private List<TrainObj> newTrainList;
    private List<TrainObj> zuHe1List;
    private List<TrainObj> zuHe2List;
    private List<TrainObj> finalList;
    private TextView dept_arr_txt,train_footer_txt,train_data_left_day,train_data_right_day,train_runtime_txt,train_list_start_data_txt,train_start_sorttime_txt;
    private ListView listview;
    private TrainListAdapter adapter;
    private LinearLayout t_list_bottom;
    private RelativeLayout train_chosereset_data_lay;
    private String changeData;
    private boolean lastPosion = false;
    private LinearLayout train_shaixuan_lay,train_starttime_sort_lay,train_tlong_sort_lay,train_price_sort_lay;
    private LinearLayout shaixuan_lay;
    private CheckBox train_shaixuan_gaotie,train_shaixuan_donche,train_shaixuan_putong,train_shaixuan_qita;
    private CheckBox train_time0006,train_time0012,train_time1800,train_time2400;
    private TextView btn_shaixuan_dismiss,  btn_shaixuan_makesure;
    private boolean isFist=true;
    private boolean GAOTIE=false;
    private boolean DONCHE= false;
    private boolean PUKUAI=false;
    private boolean QITALC=false;
    private String STARTZERO_TO_6="";
     private String STARTSIX_TO_12="";
    private String STARTTWELVE_18="";
    private String STARTEIGHTTEEN_24="";
    private boolean isSort =true;
    private boolean isTimeSort = true;
    private long seleteTime = 0;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.train_tickets_lay);
        initView();
        executeWeb(SEARCH_TRAIN_TICKET,"查询火车票...");
    }
    private void initView() {
        shaixuan_lay = (LinearLayout)findViewById(R.id.shaixuan_lay);
        train_start_sorttime_txt = (TextView)findViewById(R.id.train_start_sorttime_txt);
        train_runtime_txt = (TextView)findViewById(R.id.train_runtime_txt);
        findViewById(R.id.app_back_icon).setOnClickListener(this);
        findViewById(R.id.train_shaixuan_lay).setOnClickListener(this);
        findViewById(R.id.train_chosereset_data_lay).setOnClickListener(this);
        findViewById(R.id.train_starttime_sort_lay).setOnClickListener(this);
        findViewById(R.id.train_tlong_sort_lay).setOnClickListener(this);
        findViewById(R.id.train_price_sort_lay).setOnClickListener(this);
        train_data_left_day = (TextView)findViewById(R.id.train_data_left_day);
        train_data_left_day.setOnClickListener(this);
        train_data_right_day = (TextView)findViewById(R.id.train_data_right_day);
        train_data_right_day.setOnClickListener(this);
        train_list_start_data_txt = (TextView)findViewById(R.id.train_list_start_data_txt);
        t_list_bottom = (LinearLayout) findViewById(R.id.t_list_bottom);
        dept_arr_txt = (TextView) findViewById(R.id.app_title_txt);
        dept_arr_txt.setText(MyApp.getTrainOrgCity().getName() + " -- " + MyApp.getTrainDeptCity().getName());
        listview = (ListView) findViewById(R.id.train_listview);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.train_footer_layout,null);
        train_footer_txt = (TextView) v.findViewById(R.id.train_footer_txt);
        listview.addFooterView(v);
        train_list_start_data_txt.setText(MyApp.getTrainStartDate());
        initShaixuanPopo();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==parent.getCount()-1){
                    return;
                }
                TrainObj trainObj = ((ViewHolder) view.getTag()).trainObj;
                Intent intent = new Intent(TrainListActivity.this, TrainDetailT.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.TRAIN_DETAIN, trainObj);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 是当屏幕停止滚动时
                        Animation anim2 = AnimationUtils.loadAnimation(
                                TrainListActivity.this, R.anim.popupwindow_ente);
                        t_list_bottom.startAnimation(anim2);
                        t_list_bottom.setVisibility(View.VISIBLE);
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 滚动时
                        Animation anim1 = AnimationUtils.loadAnimation(
                                TrainListActivity.this, R.anim.popupwindow_exit);
                        t_list_bottom.startAnimation(anim1);
                        t_list_bottom.setVisibility(View.GONE);
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:// 是当用户由于之前划动屏幕并抬起手指，屏幕产生惯性滑动时
                        t_list_bottom.setVisibility(View.GONE);
                        break;
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });


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
                        train_list_start_data_txt.setText(MyApp.getTrainStartDate());
                        changeData = MyApp.getTrainStartDate();
                        lastPosion=false;
                        if (changeData.equals(MyAllUntils.getNowDateF())){
                            train_data_left_day.setTextColor(getResources().getColorStateList(R.color.flight_gray_txt_color));
                        }else{
                            train_data_left_day.setTextColor(getResources().getColorStateList(R.color.white));
                        }
                        executeWeb(SEARCH_TRAIN_LEFTDAY,"正在为您查询...");
                    } else {
                        return;
                    }
                }
            }else if (requestCode == Constants.NO_SELECT_T){
                return;
            }
        }

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.train_chosereset_data_lay:

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putLong(Constants.SELECT_TIME, seleteTime);
                intent.putExtras(bundle);
                intent.setClass(TrainListActivity.this, CaldroidActivity.class);
                startActivityForResult(intent,10);

                break;
            case R.id.app_back_icon:
                finish();
                break;
            case R.id.train_data_left_day://前一天
                if (lastPosion==true){
                    return;
                }
                String now = MyApp.getTrainStartDate();
                String d1 = TrainUntil.lowDay(now,1);
                changeData = d1;
                Log.e("lowDay----", "减去一天:=========== "+ d1+"\t"+now);
                train_list_start_data_txt.setText(d1);
                MyApp.setTrainStartDate(d1);
                if (d1.equals(MyAllUntils.getNowDateF())){
                    train_data_left_day.setTextColor(getResources().getColorStateList(R.color.flight_gray_txt_color));
                    lastPosion = true;
                    executeWeb(SEARCH_TRAIN_LEFTDAY,"正在为您查询...");
                }
                executeWeb(SEARCH_TRAIN_LEFTDAY,"正在为您查询...");

                break;
            case R.id.train_data_right_day://后一天
                String nowData = MyApp.getTrainStartDate();
                String d = TrainUntil.addDay(nowData,1);
                changeData=d;
                lastPosion = false;
                if (!d.equals(MyAllUntils.getNowDateF())){
                    train_data_left_day.setTextColor(getResources().getColorStateList(R.color.white));
                }
                Log.e("addDay----", "加上一天:=========== "+ d+"\t"+nowData);
                train_list_start_data_txt.setText(d);
                MyApp.setTrainStartDate(d);
                executeWeb(SEARCH_TRAIN_LEFTDAY,"正在为您查询...");
                break;
            case R.id.train_shaixuan_lay://筛选
                if (isFist){
                    popoAnimStart();
                    isFist=false;
                }else{
                    popoAnimEnd();
                    isFist=true;
                }
                break;
            case R.id.train_starttime_sort_lay://出发时间
                if (isSort){
                    train_start_sorttime_txt.setText("出发从早到晚");
                    Collections.sort(allTrainList);
                    adapter = new TrainListAdapter(this,allTrainList);
                    listview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    isSort=false;
                }else{
                    train_start_sorttime_txt.setText("出发从晚到早");
                    Collections.reverse(allTrainList);
                    adapter = new TrainListAdapter(this,allTrainList);
                    listview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    isSort=true;
                }

                break;
            case R.id.train_tlong_sort_lay://运行时间
                 if (isTimeSort){
                     train_runtime_txt.setText("耗时从短到长");
                     Collections.sort(allTrainList,new TimeComparator());
                     adapter = new TrainListAdapter(this,allTrainList);
                     listview.setAdapter(adapter);
                     adapter.notifyDataSetChanged();
                     isTimeSort=false;
                 }else{
                     train_runtime_txt.setText("耗时从长到短");
                     Collections.sort(allTrainList,new TimeCompRevose());
                     adapter = new TrainListAdapter(this,allTrainList);
                     listview.setAdapter(adapter);
                     adapter.notifyDataSetChanged();
                     isTimeSort=true;
                 }
                break;
            case R.id.train_price_sort_lay://价格
                break;
            case R.id.btn_shaixuan_dismiss:
                popoAnimEnd();
                break;
            case R.id.btn_shaixuan_makesure:
                shaiXuanT();
                popoAnimEnd();
                break;
        }

    }

     class  TimeComparator implements Comparator{
        @Override
        public int compare(Object lhs, Object rhs) {
            TrainObj obj1 = (TrainObj) lhs;
            TrainObj obj2 = (TrainObj) rhs;
            return obj1.getRun_time().compareTo(obj2.getRun_time());
        }
    }
     class  TimeCompRevose implements Comparator{
        @Override
        public int compare(Object lhs, Object rhs) {
            TrainObj obj1 = (TrainObj) lhs;
            TrainObj obj2 = (TrainObj) rhs;
            return obj2.getRun_time().compareTo(obj1.getRun_time());
        }
    }

    private void shaiXuanT(){
       int tSize=trainlist.size();
       if (GAOTIE==false&&DONCHE==false&&PUKUAI==false&&QITALC==false&&STARTZERO_TO_6.equals("")&&STARTSIX_TO_12.equals("")&&STARTTWELVE_18.equals("")&&STARTEIGHTTEEN_24.equals("")){
           allTrainList = trainlist;
           adapter = new TrainListAdapter(this,trainlist);
           listview.setAdapter(adapter);
           adapter.notifyDataSetChanged();
           train_footer_txt.setText("共找到"+trainlist.size()+"条符合条件的车次");
           return;
       }else{
            if (GAOTIE==false&&DONCHE==false&&PUKUAI==false&&QITALC==false){//只查询时间
                finalList = new ArrayList<>();
                Log.e("选中的值",STARTZERO_TO_6+STARTSIX_TO_12+STARTTWELVE_18+STARTEIGHTTEEN_24);
                for (int j = 0; j <trainlist.size() ; j++) {
                    TrainObj fobj = trainlist.get(j);
                    String start =trainlist.get(j).getStart_time();
                    String startN = start.substring(0,2);
                    int time = Integer.parseInt(startN);
                    if ((!STARTZERO_TO_6.equals(""))&&(time>=0&&time<6)){
                        finalList.add(fobj);
                    }else if ((!STARTSIX_TO_12.equals(""))&&(time>=6&&time<12)){
                        finalList.add(fobj);
                    }else if ((!STARTTWELVE_18.equals(""))&&(time>=12&&time<18)){
                        finalList.add(fobj);
                    }else if ((!STARTEIGHTTEEN_24.equals(""))&&(time>=18&&time<=24)){
                        finalList.add(fobj);
                    }
                }
                allTrainList =finalList;
                train_footer_txt.setText("共找到"+finalList.size()+"条符合条件的车次");
                adapter = new TrainListAdapter(this,finalList);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }else if(STARTZERO_TO_6.equals("")&&STARTSIX_TO_12.equals("")&&STARTTWELVE_18.equals("")&&STARTEIGHTTEEN_24.equals("")){
                newTrainList = new ArrayList<>();
                for (int i = 0; i <tSize ; i++) {//只查询列车类型
                    String type =trainlist.get(i).getTrain_type();
                    Log.e("---TYPE---", "shaiXuanT:====type: "+type);
                    TrainObj obj = trainlist.get(i);

                    if ((GAOTIE==true)&&(type.equals("G")||type.equals("C"))){
                        Log.e("-----高铁----", "--------" );
                        newTrainList.add(obj);
                    }else if((DONCHE==true)&&(type.equals("D"))){
                        Log.e("-----动车----", "--------" );
                        newTrainList.add(obj);
                    }else if((PUKUAI==true)&&(type.equals("T")||type.equals("Z")||type.equals("K"))){
                        Log.e("-----快铁----", "--------" );
                        newTrainList.add(obj);
                    }
                    else if (QITALC==true&&(!type.equals("Z")&&!type.equals("T")&&!type.equals("K")&&!type.equals("D")&&!type.equals("C")&&!type.equals("G"))){
                        Log.e("-----其他----", "=--------" );
                        newTrainList.add(obj);
                   }
                }
                allTrainList = newTrainList;
                train_footer_txt.setText("共找到"+newTrainList.size()+"条符合条件的车次");
                adapter = new TrainListAdapter(this,newTrainList);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }else {//组合查询
                Log.e("---TYPE---", "shaiXuanT:====组合查询: ");
                zuHe1List = new ArrayList<>();
                for (int i = 0; i <tSize ; i++) {//先查询列车类型
                    String type =trainlist.get(i).getTrain_type();
                    TrainObj obj = trainlist.get(i);
                    //G D T Z
                    if ((GAOTIE==true)&&(type.equals("G")||type.equals("C"))){
                        Log.e("-----高铁02----", "--------" );
                        newTrainList.add(obj);
                    }else if((DONCHE==true)&&(type.equals("D"))){
                        Log.e("-----动车02----", "--------" );
                        newTrainList.add(obj);
                    }else if((PUKUAI==true)&&(type.equals("T")||type.equals("Z")||type.equals("K"))){
                        Log.e("-----快贴02----", "--------" );
                        newTrainList.add(obj);
                    }
                    else if (QITALC==true&&(!type.equals("Z")&&!type.equals("T")&&!type.equals("K")&&!type.equals("D")&&!type.equals("C")&&!type.equals("G"))){
                        Log.e("-----其他02----", "--------" );

                        newTrainList.add(obj);
                    }
                }
               zuHe2List = new ArrayList<>();
                for (int k = 0; k <zuHe1List.size() ; k++) {
                    TrainObj fobj = zuHe1List.get(k);
                    String start =zuHe1List.get(k).getStart_time();
                    String startN = start.substring(0,2);
                    int time = Integer.parseInt(startN);
                    Log.e("---time----", "==0003=time " +"出发时间："+start+"截取的时间："+time);
                    if ((!STARTZERO_TO_6.equals(""))&&(time>=0&&time<6)){
                        zuHe2List.add(fobj);
                    }else if ((!STARTSIX_TO_12.equals(""))&&(time>=6&&time<12)){
                        zuHe2List.add(fobj);
                    }else if ((!STARTTWELVE_18.equals(""))&&(time>=12&&time<18)){
                        zuHe2List.add(fobj);
                    }else if ((!STARTEIGHTTEEN_24.equals(""))&&(time>=18&&time<=24)){
                        zuHe2List.add(fobj);
                    }
                }
                allTrainList = zuHe2List;
                train_footer_txt.setText("共找到"+zuHe2List.size()+"条符合条件的车次");
                adapter = new TrainListAdapter(this,zuHe2List);
                listview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
       }

   }

    @Override
    public Object doTask(int flag, Object... params) throws Exception {
        if (flag==SEARCH_TRAIN_TICKET){
            return TrainQuerJs.trainQuery(WebServUtil.TRAIN_SEARCH_PATH,WebServUtil.DOMEI_UERNAME,WebServUtil.TRAIN_SEARCH_METHED,MyApp.getTrainStartDate(),MyAllUntils.getNowDateSS(),
                    WebServUtil.TRAIN_KEY,MyApp.getTrainOrgCity().getCode(),MyApp.getTrainDeptCity().getCode());
        }else if (flag==SEARCH_TRAIN_LEFTDAY){
            return TrainQuerJs.trainQuery(WebServUtil.TRAIN_SEARCH_PATH,WebServUtil.DOMEI_UERNAME,WebServUtil.TRAIN_SEARCH_METHED,
                     changeData,MyAllUntils.getNowDateSS(),
                     WebServUtil.TRAIN_KEY,MyApp.getTrainOrgCity().getCode(),MyApp.getTrainDeptCity().getCode());
        }
        return super.doTask(flag, params);
    }

    @Override
    public void taskDone(int flag, Object result) {
        TrainQuerObj getTrainObj=null;
         if (flag==SEARCH_TRAIN_TICKET){
              if (result==null){
                  AndroidUtil.shortToast(this,"抱歉,没有查询到符合条件的车次");
                  return;
              }
               getTrainObj = (TrainQuerObj)result;
             allTrainList = getTrainObj.getTrain_list();
             trainlist = getTrainObj.getTrain_list();
             if (!getTrainObj.isSuccess()){
                 AndroidUtil.alert(this,getTrainObj.getMsg());
                 return;
             }
         }else if (flag==SEARCH_TRAIN_LEFTDAY){
             if (result==null){
                 AndroidUtil.shortToast(this,"抱歉,没有查询到符合条件的车次");
                 return;
             }
             getTrainObj = (TrainQuerObj)result;
             allTrainList = getTrainObj.getTrain_list();
             trainlist = getTrainObj.getTrain_list();
             if (!getTrainObj.isSuccess()){
                 AndroidUtil.alert(this,getTrainObj.getMsg());
                 return;
             }
         }

        adapter = new TrainListAdapter(this,getTrainObj.getTrain_list());
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        train_footer_txt.setText("共找到"+getTrainObj.getTrain_list().size()+"条符合条件的车次");
        if (MyApp.getTrainStartDate().equals(MyAllUntils.getNowDateF())){
            train_data_left_day.setTextColor(getResources().getColorStateList(R.color.flight_gray_txt_color));
        }
        super.taskDone(flag, result);
    }
    /***初始化筛选popo**/
    private void initShaixuanPopo(){
        btn_shaixuan_dismiss= (TextView)findViewById(R.id.btn_shaixuan_dismiss);
        btn_shaixuan_dismiss.setOnClickListener(this);
        btn_shaixuan_makesure = (TextView)findViewById(R.id.btn_shaixuan_makesure);
        btn_shaixuan_makesure.setOnClickListener(this);
        train_shaixuan_gaotie =(CheckBox) findViewById(R.id.train_shaixuan_gaotie);
        train_shaixuan_donche =(CheckBox) findViewById(R.id.train_shaixuan_donche);
        train_shaixuan_putong =(CheckBox) findViewById(R.id.train_shaixuan_putong);
        train_shaixuan_qita =(CheckBox) findViewById(R.id.train_shaixuan_qita);
        train_time0006 =(CheckBox) findViewById(R.id.train_time0006);
        train_time0012 =(CheckBox) findViewById(R.id.train_time0012);
        train_time1800 =(CheckBox) findViewById(R.id.train_time1800);
        train_time2400 =(CheckBox) findViewById(R.id.train_time2400);

        train_shaixuan_gaotie.setOnCheckedChangeListener(this);
        train_shaixuan_donche.setOnCheckedChangeListener(this);
        train_shaixuan_putong.setOnCheckedChangeListener(this);
        train_shaixuan_qita.setOnCheckedChangeListener(this);
        train_time0006.setOnCheckedChangeListener(this);
        train_time0012.setOnCheckedChangeListener(this);
        train_time1800.setOnCheckedChangeListener(this);
        train_time2400.setOnCheckedChangeListener(this);


    }
    private void popoAnimStart(){
        Animation anim2 = AnimationUtils.loadAnimation(
                TrainListActivity.this, R.anim.popupwindow_ente);
        shaixuan_lay.startAnimation(anim2);
        shaixuan_lay.setVisibility(View.VISIBLE);
    }
    private void popoAnimEnd(){
        Animation anim1 = AnimationUtils.loadAnimation(
                TrainListActivity.this, R.anim.popupwindow_exit);
        shaixuan_lay.startAnimation(anim1);
        shaixuan_lay.setVisibility(View.GONE);
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          switch (buttonView.getId()){
              case R.id.train_shaixuan_gaotie://选择或者不选择高铁
                  setTextButtonColor(isChecked,buttonView);
                  GAOTIE= isChecked ;
                  AndroidUtil.shortToast(this,GAOTIE+"");
                  break;
              case R.id.train_shaixuan_donche://选择或者不选择动车
                  setTextButtonColor(isChecked,buttonView);
                  DONCHE = isChecked;
                  break;
              case R.id.train_shaixuan_putong://普通列车
                  setTextButtonColor(isChecked,buttonView);
                  PUKUAI = isChecked;
                  break;
              case R.id.train_shaixuan_qita://其他列火车
                  setTextButtonColor(isChecked,buttonView);
                  QITALC = isChecked;
                  break;
              case R.id.train_time0006://00-6
                  setTextButtonColor(isChecked,buttonView);
                   if (isChecked){
                       STARTZERO_TO_6="time6";
                   }else {
                       STARTZERO_TO_6="";
                   }
                  break;
              case R.id.train_time0012://6-12
                  setTextButtonColor(isChecked,buttonView);
                  if (isChecked){
                     STARTSIX_TO_12="time12";
                      AndroidUtil.shortToast(TrainListActivity.this,"time12");
                  }else{
                      STARTSIX_TO_12="";
                  }
                  break;
              case R.id.train_time1800://12-18
                  setTextButtonColor(isChecked,buttonView);
                    if (isChecked){
                        STARTTWELVE_18="time18";
                        AndroidUtil.shortToast(TrainListActivity.this,"time18");
                    }else{
                        STARTTWELVE_18="";
                    }
                  break;
              case R.id.train_time2400://18-24
                  setTextButtonColor(isChecked,buttonView);
                  if (isChecked){
                      STARTEIGHTTEEN_24 = "time24";
                      AndroidUtil.shortToast(TrainListActivity.this,"time24");
                  }else{
                      STARTEIGHTTEEN_24="";
                  }
                  break;

          }

    }

    private void setTextButtonColor(boolean isChecked, CompoundButton buttonView) {
        if (isChecked){
            buttonView.setTextColor(getResources().getColorStateList(R.color.colorPrimary));
        }else{
            buttonView.setTextColor(getResources().getColorStateList(R.color.black));
        }
    }
}
