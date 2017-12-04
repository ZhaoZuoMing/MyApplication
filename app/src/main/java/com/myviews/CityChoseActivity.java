package com.myviews;

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
import android.util.Log;
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
import com.mytables.MyApp;
import com.myuntils.CharacterParser;
import com.myuntils.ClearEditText;
import com.myuntils.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import f.sky.flight.adapter.MyBaseAdapter;
import f.sky.flight.adapter.SortAdapter;
import f.sky.flight.model.AirportObj;
import f.sky.flight.model.SortModel;

/**
 * Created by Administrator on 2016/11/17/017.
 * 城市选择界面
 */

public class CityChoseActivity extends Activity {
//    private List<RegionInfo> provinceList;
//    private List<RegionInfo> citysList;
//    private List<String> provinces;
//    private List<RegionInfo> mReMenCitys;// 热门城市列表

    private List<AirportObj> airportList;//机场信息集合
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

    private void initData()
    {
        airportList = MyApp.queryAirportResult.getAirprots();//所有的机场名称对象集合

        airporNamelist = new ArrayList<>();
        for (AirportObj info: airportList) {
            airporNamelist.add(info.getAirPortName().trim());
        }

        List<AirportObj> hotcity = MyApp.getHotCity();
        hotModel = new ArrayList<>();
        for (int i=0;i<hotcity.size();i++) {
            SortModel s = new SortModel();
            s.setCityCode(hotcity.get(i).getCityCode());
            s.setAirPortCode(hotcity.get(i).getAirPortCode());
            s.setName(hotcity.get(i).getAirPortName());
            hotModel.add(s);
        }

//
//        mReMenCitys.add(new RegionInfo(2, 1, "北京"));
//        mReMenCitys.add(new RegionInfo(25, 1, "上海"));
//        mReMenCitys.add(new RegionInfo(77, 6, "深圳"));
//        mReMenCitys.add(new RegionInfo(76, 6, "广州"));
//        mReMenCitys.add(new RegionInfo(197, 14, "长沙"));
//        mReMenCitys.add(new RegionInfo(343, 1, "天津"));
//        mReMenCitys.add(new RegionInfo(180, 13, "武汉"));
//        mReMenCitys.add(new RegionInfo(194, 13, "成都"));
//        mReMenCitys.add(new RegionInfo(32, 1, "重庆"));
       /*******************   ********************/
//        provinceList = RegionDAO.getProvencesOrCity(1);//数据库中获取省
//        provinceList.addAll(RegionDAO.getProvencesOrCity(2));//数据库中获取城市名，并将所有城市添加到一个集合中
//        citysList = new ArrayList<>();
        //热门城市本地初始化集合
        // mReMenCitys = new ArrayList<>();
        //将所有市名添加到一个集合中
//        provinces = new ArrayList<>();
//        for (RegionInfo info : provinceList)
//        {
//            provinces.add(info.getName().trim());
//        }

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
        SourceDateList = filledData(airportList);

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
                CityChoseActivity.this.finish();
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
    private List<SortModel> filledData(List<AirportObj> date)
    {
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for (int i = 0; i < date.size(); i++)
        {
            SortModel sortModel = new SortModel();
            sortModel.setName(date.get(i).getAirPortName());
            sortModel.setAirPortCode(date.get(i).getAirPortCode());
            sortModel.setCityCode(date.get(i).getCityCode());
            sortModel.setEngName(date.get(i).getEngName());
            sortModel.setField1(date.get(i).getField1());
            sortModel.setField2(date.get(i).getField2());
            sortModel.setField3(date.get(i).getField3());
            sortModel.setRemark(date.get(i).getRemark());
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date.get(i).getAirPortName());
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

//            else{
//
//
//
//                filterDateList.clear();
//
//                for (int i = 0; i < provinceList.size(); i++)
//                {
//                    String name = provinceList.get(i).getName();
//                    if (name.equals(filterStr))
//                    {
//                        filterDateList.addAll(filledData(RegionDAO.getProvencesOrCityOnParent(provinceList.get(i).getId())));
//                    }
//                }
//            }
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
            ViewHolder holder = null;
            if (convertView == null)
            {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_remen_city, null);
                holder.id_tv_cityname = (TextView) convertView.findViewById(R.id.id_tv_cityname);
                convertView.setTag(holder);
            } else
            {
                holder = (ViewHolder) convertView.getTag();
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

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.e("onResume()","-------------");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e("onPause()","-------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("onStop()","-------------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy()","-------------");
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
