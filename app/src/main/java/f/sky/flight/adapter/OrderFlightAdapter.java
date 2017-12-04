package f.sky.flight.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.dmonline.R;

import java.util.ArrayList;
import java.util.List;

import com.mytables.MyApp;
import f.sky.flight.core.WebServUtil;
import f.sky.flight.model.B_OrderDOObj;

/**
 * Created by Administrator on 2017/3/31/031.
 */

public class OrderFlightAdapter extends BaseAdapter {
    protected LayoutInflater mInflater;
    private List<B_OrderDOObj> orders = new ArrayList<>();
    private boolean hasMore;
    private Context mCtx;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public OrderFlightAdapter(Context context) {
        this.mCtx  =context;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<B_OrderDOObj> orderS) {
        this.orders.clear();
        this.orders.addAll(orderS);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (hasMore) {
            return orders.size() + 1;
        }
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.flight_order_item, null);
            holder.order_info_layout = (LinearLayout) convertView.findViewById(R.id.order_info_layout);
            holder.round_skyway_layout = (LinearLayout) convertView.findViewById(R.id.round_skyway_layout);

            holder.apply_id_txt = (TextView) convertView.findViewById(R.id.apply_id_txt);
            holder.order_id_txt = (TextView) convertView.findViewById(R.id.order_id_txt);
            holder.order_date_txt = (TextView) convertView.findViewById(R.id.order_date_txt);
            holder.order_price_txt = (TextView) convertView.findViewById(R.id.order_price_txt);
            holder.order_status_txt = (TextView) convertView.findViewById(R.id.order_status_txt);

            holder.single_apply_skyway_date_txt = (TextView) convertView.findViewById(R.id.single_apply_skyway_date_txt);
            holder.single_apply_skyway_info_txt = (TextView) convertView.findViewById(R.id.single_apply_skyway_info_txt);
            holder.round_apply_skyway_date_txt = (TextView) convertView.findViewById(R.id.round_apply_skyway_date_txt);
            holder.round_apply_skyway_info_txt = (TextView) convertView.findViewById(R.id.round_apply_skyway_info_txt);
            holder.fetch_more_txt = (TextView) convertView.findViewById(R.id.fetch_more_txt);//获取更多
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position == getCount()-1 && hasMore) {
            holder.order_info_layout.setVisibility(View.GONE);
            holder.fetch_more_txt.setVisibility(View.VISIBLE);
        } else {
            B_OrderDOObj orderObj = (B_OrderDOObj) getItem(position);

            holder.order_info_layout.setVisibility(View.VISIBLE);
            holder.fetch_more_txt.setVisibility(View.GONE);

            holder.apply_id_txt.setText(orderObj.getApplyID()+"");
            holder.order_id_txt.setText(orderObj.getApplyID()+"");
            holder.order_date_txt.setText(orderObj.getBookDate());
            if(MyApp.showCorporatePrice()){
                holder.order_price_txt.setText("￥"+orderObj.getTotalPrice());
            } else {
                holder.order_price_txt.setText("￥"+orderObj.getOrgTotalPrice());
            }
            String[] skywayS = orderObj.getSkyways().split(" ");
            String[] SkywayTakeOffDates = orderObj.getSkywayTakeOffDates().split(" ");
            holder.single_apply_skyway_info_txt.setText(skywayS[0]);
            holder.single_apply_skyway_date_txt.setText(SkywayTakeOffDates[0]);
            if(skywayS.length > 1){
                holder.round_skyway_layout.setVisibility(View.VISIBLE);
                holder.round_apply_skyway_date_txt.setText(SkywayTakeOffDates[1]);
                holder.round_apply_skyway_info_txt.setText(skywayS[1]);
            } else {
                holder.round_skyway_layout.setVisibility(View.GONE);
            }
            String showOrderStatusDesc = MyApp.showStatusDesc(orderObj.getOrderStatus(), orderObj.getApplyStatus(), orderObj.getCashStatus());
            holder.order_status_txt.setText(showOrderStatusDesc);

            if (orderObj.getOrderStatus() == WebServUtil.ORDER_SAVED_STATUS) {
                holder.order_status_txt.setTextColor(mCtx.getResources().getColor(R.color.flight_gray_txt_color));
            } else if (orderObj.getOrderStatus() == WebServUtil.ORDER_DELETED_STATUS) {
                holder.order_status_txt.setTextColor(mCtx.getResources().getColor(R.color.flight_price_color));
            } else if (orderObj.getOrderStatus() == WebServUtil.ORDER_APPLYED_STATUS) {
                holder.order_status_txt.setTextColor(mCtx.getResources().getColor(R.color.flight_gray_txt_color));
            } else if (orderObj.getOrderStatus() == WebServUtil.ORDER_UNPAYED_STATUS) {
                holder.order_status_txt.setTextColor(mCtx.getResources().getColor(R.color.flight_gray_txt_color));
            } else if (orderObj.getOrderStatus() == WebServUtil.ORDER_PAYED_STATUS) {
                holder.order_status_txt.setTextColor(mCtx.getResources().getColor(R.color.flight_blue_txt_color));
            }
        }

        return convertView;
    }

    static class ViewHolder {
        public LinearLayout order_info_layout;
//        public LinearLayout single_skyway_layout;
        public LinearLayout round_skyway_layout;

        public TextView apply_id_txt;
        public TextView order_id_txt;
        public TextView order_date_txt;
        public TextView order_price_txt;
        public TextView order_status_txt;

        public TextView single_apply_skyway_date_txt;
        public TextView single_apply_skyway_info_txt;
        public TextView round_apply_skyway_date_txt;
        public TextView round_apply_skyway_info_txt;
        public TextView fetch_more_txt;
    }

}
