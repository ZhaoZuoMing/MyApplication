package donm.sky.hotel.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.MyApp;

import java.util.List;

import donm.sky.hotel.model.HotelDataList;
import donm.sky.hotel.until.HotelUntils;
import donm.sky.hotel.until.TimeUntil;

import static cn.skytrip.train.json.TrainQuerJs.TAG;

/**
 * Created by Administrator on 2017/3/29/029.
 */

public class HotelOrderListAdapter extends BaseAdapter {


    private List<HotelDataList> list;
    private Context mCtx;
    private LayoutInflater mInflater;
    public HotelOrderListAdapter( List<HotelDataList> list,Context mCtx) {
        this.list = list;
        this.mCtx = mCtx;
        this.mInflater = LayoutInflater.from(mCtx);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
         OderHolder holder = null;
        if (convertView==null) {
            holder = new OderHolder();
            convertView = mInflater.inflate(R.layout.hotel_order_item, null);
            holder.H_order_adapter_hotelName = (TextView) convertView.findViewById(R.id.H_order_adapter_hotelName);
            holder.H_order_adapter_RoomType = (TextView) convertView.findViewById(R.id.H_order_adapter_RoomType);
            holder.H_order_adapter_enterTime = (TextView) convertView.findViewById(R.id.H_order_adapter_enterTime);
            holder.H_order_adapter_hotelPrice = (TextView) convertView.findViewById(R.id.H_order_adapter_hotelPrice);
            holder.H_order_adapter_status = (TextView) convertView.findViewById(R.id.H_order_adapter_status);
            holder.H_order_adapter_cancelOrder = (RelativeLayout) convertView.findViewById(R.id.H_order_adapter_cancelOrder);
            convertView.setTag(holder);

        }else{
            holder = (OderHolder)convertView.getTag();
        }
           final HotelDataList dataList  = list.get(position);
         //酒店订单状态
           holder.H_order_adapter_status.setText(HotelUntils.getHotelOrderStatus(dataList.getStatus()));
           holder.H_order_adapter_hotelName.setText(dataList.getHotelName());
           String enterTime = TimeUntil.getDayStr(dataList.getCheckInDate())+"\t至\t"+TimeUntil.getDayStr(dataList.getCheckOutDate());
           holder.H_order_adapter_enterTime.setText(enterTime+"\t"+dataList.getNumberOfDate()+"晚"+"/"+dataList.getNumberOfUnits()+"间");
           holder.H_order_adapter_RoomType.setText(dataList.getRoomTypeName());
           holder.H_order_adapter_hotelPrice.setText(HotelUntils.getParseNetPrice(dataList.getFkHorderfuReferenceHorder().getNetPrice()));
           holder.H_order_adapter_cancelOrder.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   MyApp.cancelData = dataList;
                   mItemOnClickListener.itemOnClickListener(v);
               }
           });
        return convertView;
    }
    private ItemOnClickListener mItemOnClickListener;

    public void setmItemOnClickListener(ItemOnClickListener listener){
        Log.d(TAG,"setmItemOnClickListener...");
        this.mItemOnClickListener = listener;
    }

    public interface ItemOnClickListener{
        /**
         * 传递点击的view
         * @param view
         */
        public void itemOnClickListener(View view);
    }
    public class OderHolder{
        public TextView H_order_adapter_RoomType;
        public TextView H_order_adapter_hotelName,H_order_adapter_enterTime;
        public TextView H_order_adapter_hotelPrice;
        public RelativeLayout H_order_adapter_cancelOrder;
        public TextView H_order_adapter_status;
     }
}
