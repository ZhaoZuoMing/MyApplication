package f.sky.flight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import f.sky.flight.model.B_TouristDOObj;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9/009.
 */

public class PersonAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater mInflater;
    private List<B_TouristDOObj> list;
    public  PersonAdapter( Context ctx,List<B_TouristDOObj> list){
        this.ctx =ctx;
        this.list = list;
        mInflater  =LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder  holder = null;
         if (convertView==null){
             convertView =mInflater.inflate(R.layout.person_iten_layout,null);
             holder = new ViewHolder();
             holder.person_item_id =(TextView) convertView.findViewById(R.id.person_item_id);
             holder.person_item_name = (TextView)convertView.findViewById(R.id.person_item_name);
             convertView.setTag(holder);
         }else{
             holder = (ViewHolder) convertView.getTag();
         }
          holder.person_item_name.setText(list.get(position).getTouristName());
          holder.person_item_id.setText(list.get(position).getIDNumber());
        return convertView;
    }

    public class  ViewHolder{
        public TextView person_item_name;
        public TextView person_item_id;
    }
}
