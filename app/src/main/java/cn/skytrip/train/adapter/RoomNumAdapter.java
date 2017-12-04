package cn.skytrip.train.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.dmonline.R;

/**
 * Created by Administrator on 2017/2/14/014.
 */

public class RoomNumAdapter extends BaseAdapter {
    private String[] list;
    private Context ctx;
    private LayoutInflater mInflater;
    public RoomNumAdapter(Context ctx,String[] list ) {
        this.ctx = ctx;
        this.list = list;
        this.mInflater =LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.room_gridview_item,null);
        TextView txt=(TextView) convertView.findViewById(R.id.room_num_txt);
        txt.setText(list[position]);
        return convertView;
    }
}
