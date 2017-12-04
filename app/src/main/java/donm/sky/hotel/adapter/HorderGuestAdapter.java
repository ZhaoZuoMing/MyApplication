package donm.sky.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.dmonline.R;

import java.util.List;

import donm.sky.hotel.model.HorderGuest;

/**
 * Created by Administrator on 2017/4/7/007.
 */

public class HorderGuestAdapter extends BaseAdapter {


    private Context mCtx;
    private List<HorderGuest> guests;
    private LayoutInflater mInflater;
    public HorderGuestAdapter(Context mCtx,List<HorderGuest> guests) {
        this.mCtx = mCtx;
        this.guests =guests;
        this.mInflater = LayoutInflater.from(mCtx);
    }

    @Override
    public int getCount() {
        return guests.size();
    }

    @Override
    public Object getItem(int position) {
        return guests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.horder_gust_item,null);
        TextView horder_gust_name = (TextView)convertView.findViewById(R.id.horder_gust_name);
        TextView horder_gust_phone = (TextView)convertView.findViewById(R.id.horder_gust_phone);
        horder_gust_name.setText(guests.get(position).getName());
        horder_gust_phone.setText(guests.get(position).getMobile());
        return convertView;
    }



}
