package f.sky.flight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.dmonline.R;

import java.util.List;

/**
 * Created by Administrator on 2016/11/14/014.
 */

public class PopoAdapter extends BaseAdapter {

    private List<String> list;
    private Context ctx;
    private LayoutInflater minflater;
    private int witch;
    public PopoAdapter(Context mctx,List<String> list,int witchadapter){
        this.list = list;
        this.ctx = mctx;
        this.witch = witchadapter;
        minflater = LayoutInflater.from(mctx);
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
        convertView = minflater.inflate(R.layout.item_pop_layout,null);
        TextView tv  = (TextView) convertView.findViewById(R.id.pop_item_txt);
        if (witch==2){
            if (list.get(position).equals("-1")){
                tv.setText("全部时间");
            }else{
                tv.setText(list.get(position).toString()+":00");
            }
        }else{
            tv.setText(list.get(position).toString());
        }

        return convertView;
    }

}
