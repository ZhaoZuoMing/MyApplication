package donm.sky.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.dmonline.R;

import java.util.List;

import donm.sky.hotel.model.ArriveTime;

/**
 * Created by Administrator on 2017/2/24/024.
 */

public class ArrvieTimeAdapter extends BaseAdapter {
    private List<ArriveTime> list;
    private Context ctx;
    private LayoutInflater mInflater;
    public ArrvieTimeAdapter(Context ctx,List<ArriveTime> list ) {
        this.ctx = ctx;
        this.list = list;
        this.mInflater =LayoutInflater.from(ctx);
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.room_gridview_item,null);
        TextView txt=(TextView) convertView.findViewById(R.id.room_num_txt);
        txt.setText(list.get(position).getTime());
        return convertView;
    }
}
