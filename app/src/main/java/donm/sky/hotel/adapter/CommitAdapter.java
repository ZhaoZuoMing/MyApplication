package donm.sky.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.dmonline.R;

import java.util.List;

import f.sky.flight.model.B_TouristDOObj;

/**
 * Created by Administrator on 2017/2/23/023.
 */

public class CommitAdapter extends BaseAdapter {
    private List<B_TouristDOObj> list;
    private Context ctx;
    private LayoutInflater mInflater;
    public CommitAdapter( List<B_TouristDOObj> list,Context ctx) {
        this.ctx = ctx;
        this.list = list;
        this.mInflater = LayoutInflater.from(ctx);
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
          convertView = mInflater.inflate(R.layout.commit_person_item_layout,null);
        TextView p_user_name = (TextView) convertView.findViewById(R.id.p_user_name);
        TextView p_user_phone = (TextView) convertView.findViewById(R.id.p_user_phone);
          p_user_name.setText(list.get(position).getTouristName());
          p_user_phone.setText(list.get(position).getMobileNo());
        return convertView;
    }
}
