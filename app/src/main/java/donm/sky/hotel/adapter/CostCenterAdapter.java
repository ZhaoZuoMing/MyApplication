package donm.sky.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.dmonline.R;

import java.util.List;

import f.sky.flight.model.CostCenterObj;

/**
 * Created by Administrator on 2017/2/15/015.
 */

public class CostCenterAdapter extends BaseAdapter {

    private List<CostCenterObj> list;
    private Context ctx;
    private LayoutInflater minflater;
    public CostCenterAdapter(Context mctx,List<CostCenterObj> list){
        this.list = list;
        this.ctx = mctx;
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
        tv.setText(list.get(position).getCostCenterName());


        return convertView;
    }
}
