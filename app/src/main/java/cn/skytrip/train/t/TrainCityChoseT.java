package cn.skytrip.train.t;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.dmonline.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.skytrip.train.json.JsonTrainCity;
import cn.skytrip.train.model.TrainCity;
import cn.skytrip.train.model.TrainHot;
import cn.skytrip.train.model.TraintStation;
import com.myviews.SideBar;
import f.sky.flight.adapter.MyBaseAdapter;
import f.sky.flight.adapter.SortAdapter;
import f.sky.flight.model.SortModel;
import com.myuntils.CharacterParser;
import com.myuntils.ClearEditText;
import com.myuntils.PinyinComparator;

/**
 * Created by Administrator on 2016/12/13/013.
 */

public class TrainCityChoseT extends Activity {


    private List<TraintStation> stations;
    private List<String> airporNamelist;
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private ClearEditText mClearEditText;
    private MyGridViewAdapter gvAdapter;
    private GridView mGridView;
    private RelativeLayout iv_left;
    private Intent intent;
    private List<SortModel> hotModel;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDateList;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_city_selecter);
        intent =new Intent();
        initData();
        initViews();

    }

    private String readStream(InputStream is){
        String res;
        try
        {
            byte[] buf = new byte[is.available()];
            is.read(buf);
            res = new String(buf,"utf-8");
            is.close();
        } catch (Exception e)
        {
            res="";
        }
        return(res);
    }
    private TrainCity initCityDate() {
        JsonTrainCity jsonTrainCity = new JsonTrainCity();
        String str = readStream(getResources().openRawResource(R.raw.traincity));
        TrainCity city = jsonTrainCity.getCityData(str);
        return city;
    }
    private void initData()
    {

         stations = initCityDate().getStationList();
         airporNamelist = new ArrayList<>();
        for (TraintStation info: stations) {
            airporNamelist.add(info.getName().trim());
        }

        List<TrainHot> hotcity = initCityDate().getHotList();
         hotModel = new ArrayList<>();
        for (int i=0;i<hotcity.size();i++) {
            SortModel s = new SortModel();
            s.setCityCode(hotcity.get(i).getScode());
            s.setAirPortCode(hotcity.get(i).getCode());
            s.setName(hotcity.get(i).getName());
            hotModel.add(s);
        }

    }

    private void initViews()
    {
        iv_left = (RelativeLayout) findViewById(R.id.iv_left);
        View view = View.inflate(this, R.layout.head_city_list, null);

        mGridView = (GridView) view.findViewById(R.id.id_gv_remen);
        gvAdapter = new MyGridViewAdapter(this, hotModel);
        mGridView.setAdapter(gvAdapter);
        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        // 设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener()
        {

            @Override
            public void onTouchingLetterChanged(String s)
            {
                // 该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1)
                {
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView) findViewById(R.id.country_lvcountry);
        sortListView.addHeaderView(view);
        //初始化数据
        SourceDateList = filledData(stations);

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        //设置数据并设置适配器
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);

        mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
        // 根据输入框输入值的改变来过滤搜索
        mClearEditText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
        iv_left.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                hideSoftInput(mClearEditText.getWindowToken());
                TrainCityChoseT.this.finish();
            }
        });

        /**
         * listview点击事件
         */
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 这里要利用adapter.getItem(position)来获取当前position所对应的对象
                hideSoftInput(mClearEditText.getWindowToken());
                //这里要传递对象((SortModel) adapter.getItem(position - 1))
                SortModel model = ((SortAdapter.ViewHolder)view.getTag()).model ;
                Bundle bundle = new Bundle();
                bundle.putSerializable("model", model);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        /**
         * mGridView点击事件 热门城市
         */
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                hideSoftInput(mClearEditText.getWindowToken());
                SortModel model = ((MyGridViewAdapter.ViewHolder)view.getTag()).model ;
                Bundle bundle = new Bundle();
                bundle.putSerializable("model", model);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }

    /**
     * 为ListView填充数据
     *
     * @param date
     * @return
     */
    private List<SortModel> filledData(List<TraintStation> date)
    {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.size(); i++)
        {
            SortModel sortModel = new SortModel();
            sortModel.setName(date.get(i).getName());
            sortModel.setAirPortCode(date.get(i).getCode());
            sortModel.setCityCode(date.get(i).getScode());
            sortModel.setJp(date.get(i).getJp());
            sortModel.setPy(date.get(i).getPy());
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]"))
            {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else
            {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr)
    {
        List<SortModel> filterDateList = new ArrayList<>();

        if (TextUtils.isEmpty(filterStr))
        {
            filterDateList = SourceDateList;
        } else
        {
            if (!airporNamelist.contains(filterStr))
            {
                filterDateList.clear();
                for (SortModel sortModel : SourceDateList)
                {
                    String name = sortModel.getName();
                    if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString()))
                    {
                        filterDateList.add(sortModel);
                    }
                }
            }

        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    /**
     * 热门城市
     */
    private class MyGridViewAdapter extends MyBaseAdapter<SortModel, GridView>
    {
        private LayoutInflater inflater;

        public MyGridViewAdapter(Context ct, List<SortModel> list)
        {
            super(ct, list);
            inflater = LayoutInflater.from(ct);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
             MyGridViewAdapter.ViewHolder holder = null;
            if (convertView == null)
            {
                holder = new MyGridViewAdapter.ViewHolder();
                convertView = inflater.inflate(R.layout.item_remen_city, null);
                holder.id_tv_cityname = (TextView) convertView.findViewById(R.id.id_tv_cityname);
                convertView.setTag(holder);
            } else
            {
                holder = (MyGridViewAdapter.ViewHolder) convertView.getTag();
            }
            holder.model = hotModel.get(position);
            holder.id_tv_cityname.setText(hotModel.get(position).getName());
            return convertView;
        }

        class ViewHolder
        {
            TextView id_tv_cityname;
            SortModel model;
        }
    }


    /**
     * 防止用户点击返回键无法取值的问题
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            Bundle bundle = new Bundle();
            bundle.putSerializable("model", null);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    protected void hideSoftInput(IBinder token)
    {
        if (token != null)
        {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
