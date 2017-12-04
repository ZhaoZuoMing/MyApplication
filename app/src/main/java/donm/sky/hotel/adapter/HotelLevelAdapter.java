package donm.sky.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.administrator.dmonline.R;

import java.util.HashMap;
import java.util.List;

import donm.sky.hotel.model.Level;

/**
 * Created by Administrator on 2016/12/22/022.
 */

public class HotelLevelAdapter extends BaseAdapter {
    private List<Level> list;
    private Context ctx;
    private LayoutInflater mInflater;
    private static HashMap<Integer,Boolean> isSelected;
    public  HotelLevelAdapter(Context ctx,List<Level> list){
        this.ctx =ctx;
        this.list = list;
        this.mInflater = LayoutInflater.from(ctx);
        isSelected = new HashMap<>();
        initDate();
    }
    private void initDate(){
        for(int i=0; i<list.size();i++) {
            getIsSelected().put(i,false);
        }

    }

    public static HashMap<Integer,Boolean> getIsSelected() {
        return isSelected;
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
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HotelLevelAdapter.ViewHolder holder = null;

        if (convertView ==null){
            holder = new HotelLevelAdapter.ViewHolder();
            convertView = mInflater.inflate(R.layout.check_person_item_layout,null);
            holder.cb = (CheckBox) convertView.findViewById(R.id.check_person_btn);
            holder.name = (TextView) convertView.findViewById(R.id.person_name);
            convertView.setTag(holder);
        }else{
            holder = (HotelLevelAdapter.ViewHolder) convertView.getTag();
        }

        holder.cb.setChecked(getIsSelected().get(position));
        if (getIsSelected().get(position)){
            holder.name.setTextColor(ctx.getResources().getColorStateList(R.color.colorPrimary));
        }
        holder.name.setText(list.get(position).getMsg());

        return convertView;
    }

    public  static class ViewHolder{
        public CheckBox cb;
        public TextView name;

    }

}
