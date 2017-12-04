package com.myviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mymodels.TravelData;
import com.myuntils.MyAllUntils;

import java.util.List;

import donm.sky.hotel.until.TimeUntil;

/**
 * Created by Administrator on 2017/6/14/014.
 * 行程适配器
 */

public class TravelAdapter extends BaseAdapter {
   private List<TravelData> list;
    private Context mContext;
    public TravelAdapter( List<TravelData> list,Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
     class  ViewHolder{
        protected LinearLayout travel_title_lay;
        protected TextView d_item_weday,d_item_travel_date;
        protected TextView flight_and_train_NO,travel_item_endTime;
        protected ImageView travel_item_img;
        protected TextView travel_name;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder holder = new ViewHolder();
          if (convertView !=null){
              holder = (ViewHolder) convertView.getTag();
          }else{
              convertView = LayoutInflater.from(mContext).inflate(R.layout.travel_item,null);
              holder.d_item_weday = (TextView) convertView.findViewById(R.id.d_item_weday);
              holder.d_item_travel_date = (TextView) convertView.findViewById(R.id.d_item_travel_date);
              holder.flight_and_train_NO = (TextView) convertView.findViewById(R.id.flight_and_train_NO);
              holder.travel_item_endTime = (TextView) convertView.findViewById(R.id.travel_item_endTime);
              holder.travel_item_img = (ImageView) convertView.findViewById(R.id.travel_item_img);
              holder.travel_title_lay = (LinearLayout)convertView.findViewById(R.id.travel_title_lay);
              holder.travel_name = (TextView)convertView.findViewById(R.id.travel_name);

              convertView.setTag(holder);
           }
                 TravelData travelData = list.get(position);
               if (travelData.getType()==1) {//机票
                   holder.travel_item_endTime.setVisibility(View.GONE);
                   holder.travel_item_img.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.travel_flight_icon));
                   holder.travel_name.setText(travelData.getStartStation()+"\t\t\t至\t\t\t"+travelData.getEndStation());
                   holder.flight_and_train_NO.setText("航班："+travelData.getName());//航班号
                   holder.flight_and_train_NO.setTextColor(mContext.getResources().getColor(R.color.flight_blue_txt_color));
               }else if (travelData.getType()==2){//酒店
                   holder.travel_item_img.setBackgroundDrawable(mContext.getResources().getDrawable(R.mipmap.travel_hotel_icon));
                   holder.flight_and_train_NO.setTextColor(mContext.getResources().getColor(R.color.gray_color));
                   holder.flight_and_train_NO.setText(travelData.getStartTime());
                   holder.travel_item_endTime.setText("入住"+travelData.getEnter_days()+"天");
                   holder.travel_name.setText(travelData.getName());
               }else{//火车票
                }
               String cur = list.get(position).getStartDate();
               String pre = position-1>=0?list.get(position-1).getStartDate():"";
               if (!(pre.equals(cur))){
                   holder.travel_title_lay.setVisibility(View.VISIBLE);
                   holder.d_item_travel_date.setText(TimeUntil.getDayStr(cur));
//                   Log.e("日期：", "getView: "+travelData.getStartDate()+MyAllUntils.getBejiT()+"相差天数："+ TimeUntil.daysBetween(MyAllUntils.getBejiT(),travelData.getStartDate()));
                   if (TimeUntil.daysBetween(MyAllUntils.getBejiT(),travelData.getStartDate())==0){
                         holder.d_item_weday.setBackgroundColor(mContext.getResources().getColor(R.color.chengse));
                         holder.d_item_weday.setText("今天");
//                       Log.e("今天----", "getView: ");
                    } else if (TimeUntil.daysBetween(MyAllUntils.getBejiT(),travelData.getStartDate())==1){
                         holder.d_item_weday.setText("明天");
                    }else if (TimeUntil.daysBetween(MyAllUntils.getBejiT(),travelData.getStartDate())>1){
                        holder.d_item_weday.setText(TimeUntil.getWeek(travelData.getStartDate()));
                   }

               }else{
                   holder.travel_title_lay.setVisibility(View.GONE);
               }



        return convertView;
    }

    private int getPos(List<TravelData> mList){
        for (int i = 0; i < mList.size()-1; i++) {
            for (int j = mList.size()-1; j > i; j--) {
                if (mList.get(j).getStartDate().equals(mList.get(i).getStartDate())) {
                    mList.remove(j);
                }
            }
        }
        for (int i = 0; i <mList.size() ; i++) {
            return i+1;

        }
        return 0;
    }

}
