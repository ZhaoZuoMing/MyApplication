package f.sky.flight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.MyApp;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import f.sky.flight.model.CabinPriceExObj;
import f.sky.flight.model.SegmentObj;

/**
 * Created by Administrator on 2016/11/25/025.
 * 是否违反政策和其他机票价格Item 以及详细几篇下拉Item
 */

public class CabinAdapter extends BaseAdapter {
    private boolean isRule;
    private List<CabinPriceExObj> cabins = new ArrayList<>();

    protected LayoutInflater mInflater;
    private SegmentObj segmentObj;
    private Context c;

    public boolean isRule() {
        return isRule;
    }

    public void setRule(boolean isRule) {
        this.isRule = isRule;
        cabins.clear();
        if(isRule){
            for(CabinPriceExObj c: segmentObj.getCabins()){
                if(c.isIsRule()){
                    cabins.add(c);
                }
            }
        } else {
            cabins.addAll(segmentObj.getCabins());
        }
        this.notifyDataSetChanged();
    }
    public SegmentObj getSegmentObj() {
        return segmentObj;
    }

    public void setSegmentObj(SegmentObj segmentObj) {
        this.segmentObj = segmentObj;
    }

    /**
     * 构造函数
     * @param c
     * @param segmentObj
     */
    public CabinAdapter(Context c, SegmentObj segmentObj) {
        this.mInflater = LayoutInflater.from(c);
        this.segmentObj = segmentObj;
        this.c = c;
    }

    @Override
    public int getCount() {
        if(null == segmentObj){
            return 0;
        }
        return cabins.size();
    }

    @Override
    public Object getItem(int position) {
        if(null == segmentObj){
            return null;
        }
        return cabins.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.cabin_item_layout, null);
            holder.cabin_price = (TextView) convertView.findViewById(R.id.cabin_price);
            holder.cabin_isRule = (TextView) convertView.findViewById(R.id.cabin_isRule_txt);
            holder.cabin_desc = (TextView) convertView.findViewById(R.id.cabin_desc);
            holder.cabin_count = (TextView) convertView.findViewById(R.id.cabin_count);
            holder.cabin_aggrementCode = (TextView) convertView.findViewById(R.id.cabin_aggrementCode_txt);
//            holder.right_line_view = (View) convertView.findViewById(R.id.right_line_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        CabinPriceExObj cabinPriceExObj = (CabinPriceExObj)getItem(position);
        holder.cabin_price.setText(MyApp.showNumber(cabinPriceExObj.getPrice()));
        if(cabinPriceExObj.isIsRule()){
            holder.cabin_isRule.setVisibility(View.GONE);
        } else {
            holder.cabin_isRule.setVisibility(View.VISIBLE);
        }
        if(cabinPriceExObj.isAgreementCabin()){
            holder.cabin_aggrementCode.setVisibility(View.VISIBLE);
        } else {
            holder.cabin_aggrementCode.setVisibility(View.GONE);
        }
//       if(position%3 == 2){
//            holder.right_line_view.setVisibility(View.INVISIBLE);
//       } else {
//           holder.right_line_view.setVisibility(View.VISIBLE);
//       }
        holder.cabin_desc.setText(cabinPriceExObj.getCabinDesc() + cabinPriceExObj.getCabin());
        holder.cabin_count.setText(parseCabinCount(cabinPriceExObj.getCabinCount()));
        return convertView;
    }

    static class ViewHolder {
        public TextView cabin_price;
        public TextView cabin_isRule;
        public TextView cabin_desc;
        public TextView cabin_count;
        public TextView cabin_aggrementCode;
        public View right_line_view;
    }
    private String parseCabinCount(String cabinCount){
        if(StringUtils.equals("A", cabinCount)){
            return c.getString(R.string.cabin_count_A);
        }
        int count = Integer.parseInt(cabinCount);
        if(count < 5){
            return c.getString(R.string.cabin_count_little);
        }
        return cabinCount + c.getString(R.string.cabin_count_number);
    }
}
