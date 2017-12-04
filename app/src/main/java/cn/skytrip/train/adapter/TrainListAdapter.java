package cn.skytrip.train.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.dmonline.R;

import java.util.List;

import cn.skytrip.train.model.TrainObj;

/**
 * Created by Administrator on 2016/12/15/015.
 */

public class TrainListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context ctx;
    private List<TrainObj> list;
    public TrainListAdapter(Context context,List<TrainObj> list) {
        this.ctx = context;
        this.list =list;
        mInflater =LayoutInflater.from(ctx);

    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view==null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.train_item_lay,null);
            holder.train_dept_txt = (TextView) view.findViewById(R.id.train_dept_txt);
            holder.train_dept_time_txt = (TextView) view.findViewById(R.id.train_dept_time_txt);
            holder.train_number_txt = (TextView) view.findViewById(R.id.train_number_txt);
            holder.train_running_time_txt = (TextView) view.findViewById(R.id.train_running_time_txt);
            holder.train_arr_txt = (TextView) view.findViewById(R.id.train_arr_txt);
            holder.train_arr_time_txt = (TextView) view.findViewById(R.id.train_arr_time_txt);

            holder.train_price_txt = (TextView) view.findViewById(R.id.train_price_txt);
            holder.train_second_seat_txt = (TextView) view.findViewById(R.id.train_second_seat_txt);
            holder.train_first_seat_txt = (TextView) view.findViewById(R.id.train_first_seat_txt);
            holder.train_shop_seat_txt = (TextView) view.findViewById(R.id.train_shop_seat_txt);
            holder.train__qita_txt =(TextView)view.findViewById(R.id.train__qita_txt);

            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }
        holder.trainObj = list.get(position);
        holder.train_dept_txt.setText(list.get(position).getFrom_station_name());
        holder.train_arr_txt.setText(list.get(position).getTo_station_name());
        holder.train_dept_time_txt.setText(list.get(position).getStart_time());
        holder.train_number_txt.setText(list.get(position).getTrain_code());
        holder.train_running_time_txt.setText(list.get(position).getRun_time());
        holder.train_arr_time_txt.setText(list.get(position).getArrive_time());
//        String days = list.get(position).getArrive_days();
//        if (days.equals("1")){
//            holder.train_arr_time_txt.setText(list.get(position).getArrive_time()+"+1");
//        }else if (days.equals("2")){
//            holder.train_arr_time_txt.setText(list.get(position).getArrive_time()+"+2");
//        }else if (days.equals("3")){
//            holder.train_arr_time_txt.setText(list.get(position).getArrive_time()+"+3");
//        }else {
//
//        }

        //判断是高铁还是快铁
        String train_type =list.get(position).getTrain_type();
         if (train_type.equals("G")||train_type.equals("D")){//高铁/动车
             holder.train_price_txt.setText("￥"+list.get(position).getEdz_price()+"起");
             holder.train_second_seat_txt.setText("二等座:"+list.get(position).getEdz_num()+"张");
             holder.train_first_seat_txt.setText("一等座:"+list.get(position).getYdz_num()+"张");
             holder.train_shop_seat_txt.setText("商务座:"+list.get(position).getSwz_num()+"张");
         }else if (train_type.equals("T")||train_type.equals("K")||train_type.equals("Z")){//快铁
             holder.train_price_txt.setText("￥"+list.get(position).getWz_price()+"起");
             holder.train_second_seat_txt.setText("硬座:"+list.get(position).getYz_num()+"张");
             holder.train_first_seat_txt.setText("硬卧:"+list.get(position).getYw_num()+"张");
             holder.train_shop_seat_txt.setText("软卧:"+list.get(position).getRw_num()+"张");
             holder.train__qita_txt.setText("无座:"+list.get(position).getWz_num()+"张");
         }


        return view;
    }

    public class  ViewHolder{
        public TextView train_dept_txt,train_dept_time_txt,train_number_txt,train_running_time_txt;
        public TextView train_arr_txt,train_arr_time_txt;
        public TextView train_price_txt;
        public TextView train_second_seat_txt,train_first_seat_txt,train_shop_seat_txt;
        public TextView train__qita_txt;
        public TrainObj trainObj;
    }

}
