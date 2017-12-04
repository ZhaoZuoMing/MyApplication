package f.sky.flight.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.MyApp;
import com.myuntils.AndroidUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import f.sky.flight.core.FtUntils;
import f.sky.flight.model.Cabin;
import f.sky.flight.model.FlightSegment;
import f.sky.flight.t.OrderFlightActivity;

import static com.example.administrator.dmonline.R.*;

/**
 * Created by zhaody on 2017/11/14.
 */

public class RecyclerAdapter  extends  SecondaryListAdapter<RecyclerAdapter.GroupItemViewHolder, RecyclerAdapter.SubItemViewHolder>{
    private Context context;

    private List<DataTree<FlightSegment, List<Cabin>>> dts = new ArrayList<>();

    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setData(List datas) {
        dts = datas;
        notifyNewData(dts);
    }

    @Override
    public RecyclerView.ViewHolder groupItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout.group_flight_item, parent, false);
        return new GroupItemViewHolder(v);
    }

    @Override
    public RecyclerView.ViewHolder subItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout.sub_flight_item, parent, false);
        return new SubItemViewHolder(v);
    }

    @Override
    public void onGroupItemBindViewHolder(RecyclerView.ViewHolder holder, int groupItemIndex) {
           FlightSegment segment =  dts.get(groupItemIndex).getGroupItem();
           ((GroupItemViewHolder) holder).OrgCityName_txt.setText(FtUntils.replaceAirportName(segment.getFromAirportName(),segment.getFromTerminal()));
           ((GroupItemViewHolder) holder).DstCityName_txt.setText(FtUntils.replaceAirportName(segment.getToAirportName(),segment.getToTerminal()));
            String content = segment.getAirlineName()+segment.getNumber()+" | "+segment.getPlaneType();
            ((GroupItemViewHolder) holder).flight_content_txt.setText(content);
             ((GroupItemViewHolder) holder).DepTime_txt.setText(segment.getTakeoffTime().substring(11,16));
            ((GroupItemViewHolder) holder).ArvTime_txt.setText(segment.getArrivalTime().substring(11,16));
            ((GroupItemViewHolder) holder).lowestPrice_txt.setText("¥"+segment.getLowestFare().intValue());
             FtUntils.setFlightIcon(segment.getAirline(),((GroupItemViewHolder) holder).flight_icon);
    }
    @Override
    public void onSubItemBindViewHolder(RecyclerView.ViewHolder holder, final int groupItemIndex, final  int subItemIndex) {

        Cabin cabin =(Cabin) dts.get(groupItemIndex).getSubItems().get(subItemIndex);
        ((SubItemViewHolder) holder).TypeName_txt.setText(cabin.getTypeName()+" "+ FtUntils.mathDisCount(cabin.getDiscount()));
        ((SubItemViewHolder) holder).BaggageAllowance_txt.setText("免费行李限额"+cabin.getRefundChange().getBaggageAllowance());
        ((SubItemViewHolder) holder).FareTypeName_txt.setText(cabin.getFareTypeName());
        ((SubItemViewHolder) holder).Cabin_price_txt.setText("¥"+(int) cabin.getTicketPrice());
//                    ((SubItemViewHolder) holder).sub_item_lay.setBackgroundResource(drawable.violate_canot_order);
//        ((SubItemViewHolder) holder).sub_order_flight.setBackgroundResource(color.light_gray);

//        /**判断是否符合差标**/
        if (cabin.isAllowOrder()&&cabin.getRules()==null){//不违规可以预订---设置背景为绿色
            ((SubItemViewHolder) holder).sub_order_flight.setOnClickListener(new OrderButtonClick(groupItemIndex,subItemIndex));
            ((SubItemViewHolder) holder).sub_item_lay.setBackgroundResource(drawable.violate_can_order);
        }else if (cabin.isAllowOrder()&&cabin.getRules()!=null){//违规可以预订---设置背景为黄色
            ((SubItemViewHolder) holder).sub_order_flight.setOnClickListener(new OrderButtonClick(groupItemIndex,subItemIndex));
            ((SubItemViewHolder) holder).sub_item_lay.setBackgroundResource(drawable.violate_can_order_yellow);
        }else if (cabin.isAllowOrder()==false){//违规不可预订---设置背景为红色
            ((SubItemViewHolder) holder).sub_item_lay.setBackgroundResource(drawable.violate_canot_order);
            ((SubItemViewHolder) holder).sub_order_flight.setBackgroundResource(color.light_gray);
        }
    }

    /**
     * 控制按钮点击事件
     */
    class  OrderButtonClick implements View.OnClickListener{
        private int groupItemIndex;
        private int subItemIndex;
       public OrderButtonClick(int groupItemIndex,int subItemIndex){
           this.groupItemIndex = groupItemIndex;
           this.subItemIndex = subItemIndex;
        }
        @Override
        public void onClick(View view) {
            FlightSegment segment =  dts.get(groupItemIndex).getGroupItem();
            Cabin cabin = segment.getCabins().get(subItemIndex);
            segment.setCabin(cabin);
            MyApp.setFlightSegment(segment);
            Intent intent = new Intent();
            intent.setClass(context,OrderFlightActivity.class);
            context.startActivity(intent);
        }
    }
    public static class SubItemViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout sub_item_lay;
        TextView TypeName_txt;//仓位名称+折扣
        TextView RefundChange_btn_txt;//退改签政策
        TextView BaggageAllowance_txt;//行李限额
        TextView FareTypeName_txt;//协议内容
        TextView Cabin_price_txt;
        Button sub_order_flight;//预订按钮
        public SubItemViewHolder(View itemView) {
            super(itemView);
            TypeName_txt = itemView.findViewById(R.id.TypeName_txt);
            RefundChange_btn_txt = itemView.findViewById(R.id.RefundChange_btn_txt);
            BaggageAllowance_txt  = itemView.findViewById(R.id.BaggageAllowance_txt);
            FareTypeName_txt  = itemView.findViewById(R.id.FareTypeName_txt);
            Cabin_price_txt  = itemView.findViewById(R.id.Cabin_price_txt);
            sub_order_flight = itemView.findViewById(R.id.sub_order_flight);
            sub_item_lay = itemView.findViewById(R.id.sub_item_lay);
        }
    }
    @Override
    public void onGroupItemClick(Boolean isExpand, GroupItemViewHolder holder, int groupItemIndex) {
           //点击后设置箭头的方向
        if (isExpand){
            (holder).item_up_down.setImageResource(mipmap.item_down);
        }else{
            (holder).item_up_down.setImageResource(mipmap.item_up);
        }
    }

    @Override
    public void onSubItemClick(SubItemViewHolder holder, int groupItemIndex, int subItemIndex) {

//        Toast.makeText(context, "sub item " + String.valueOf(subItemIndex) + " in group item " +
//                String.valueOf(groupItemIndex), Toast.LENGTH_SHORT).show();

    }

    public static class GroupItemViewHolder extends RecyclerView.ViewHolder {

        TextView DepTime_txt;
        TextView ArvTime_txt;
        TextView OrgCityName_txt;//出发站
        TextView DstCityName_txt;//到达站
        TextView lowestPrice_txt;//最低价格
        TextView flight_content_txt;
        ImageView item_up_down;
        ImageView flight_icon;
        public GroupItemViewHolder(View itemView) {
            super(itemView);
            DepTime_txt = itemView.findViewById(id.DepTime_txt);
            ArvTime_txt = itemView.findViewById(id.ArvTime_txt);
            OrgCityName_txt = itemView.findViewById(id.OrgCityName_txt);
            DstCityName_txt = itemView.findViewById(id.DstCityName_txt);
            lowestPrice_txt = itemView.findViewById(id.lowestPrice_txt);
            flight_content_txt = itemView.findViewById(id.flight_content_txt);
            item_up_down = itemView.findViewById(id.item_up_down);
            flight_icon = itemView.findViewById(id.flight_icon);
        }
    }


}
