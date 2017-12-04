package donm.sky.hotel.adapter;

import android.content.Context;
import android.content.Intent;
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

import donm.sky.hotel.model.BookingRules;
import donm.sky.hotel.model.DHotel;
import donm.sky.hotel.model.GuaranteeRule;
import donm.sky.hotel.model.HotelDetail;
import donm.sky.hotel.model.PrepayRule;
import donm.sky.hotel.model.RatePlans;
import donm.sky.hotel.model.ValueAdds;
import donm.sky.hotel.t.HotelOrderDetailT;

/**
 * Created by ZhaoDy on 2017/1/20/020.
 * 房间价格分类适配器
 */

public class HotelRoomPriceAdapter extends BaseAdapter {

    private List<RatePlans> rooms;
    private final Context ctx;
    private LayoutInflater mInflater;
    public HotelRoomPriceAdapter(List<RatePlans> rooms,Context mCtx) {
        this.rooms = rooms;
        this.ctx = mCtx;
        this.mInflater = LayoutInflater.from(mCtx);
    }

    @Override
    public int getCount() {
        return rooms.size();
    }

    @Override
    public Object getItem(int position) {
        return rooms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        PlansRoomHolder holder = null;
        if (convertView==null){
            holder = new PlansRoomHolder();
            convertView = mInflater.inflate(R.layout.room_price_item_layout,null);
            holder.room_price_title = (TextView)convertView.findViewById(R.id.room_price_title);
            holder.room_cancel_txt = (TextView)convertView.findViewById(R.id.room_cancel_txt);
            holder.room_price_isroute = (TextView)convertView.findViewById(R.id.room_price_isroute);
            holder.room_price_wifi = (TextView)convertView.findViewById(R.id.room_price_wifi);
            holder.room_price_food = (TextView)convertView.findViewById(R.id.room_price_food);
            holder.room_hotel_price = (TextView)convertView.findViewById(R.id.room_hotel_price);
            holder.roon_payment_txt = (TextView)convertView.findViewById(R.id.roon_payment_txt);
            holder.room_order_btn = (RelativeLayout)convertView.findViewById(R.id.room_order_btn) ;
             convertView.setTag(holder);
        }else{
            holder = (PlansRoomHolder) convertView.getTag();
        }
         holder.room_price_title.setText(rooms.get(position).getRatePlanName());
         holder.room_hotel_price.setText("￥"+rooms.get(position).getAverageRate());
         String payType = rooms.get(position).getPaymentType();
         if (payType.equals("Prepay")){
             holder.roon_payment_txt.setText("预付");
         }else {
             holder.roon_payment_txt.setText("现付");
         }
        /*一个房型的三个id*/
         RatePlans r = rooms.get(position);
         String bookingRuleId = r.getBookingRuleIds();//预订规则
         String valueAddId    = r.getValueAddIds();//早餐
         String prepayRuleIds =  r.getPrepayRuleIds();//预付规则
         String guaranteeRuleId = r.getGuaranteeRuleIds();

        /*预定规则list*/
        DHotel hotel = MyApp.hotelDetailSearchResult.getdHotel();
        List<BookingRules> bookingRules = hotel.getBookingRules();
        List<ValueAdds> valueAddses = hotel.getValueAddses();
        List<PrepayRule> prepayRules = hotel.getPrepayRules();
        List<GuaranteeRule> guaranteeRules = hotel.getGuaranteeRules();
        /*酒店担保规则*/
//        Log.e(TAG, "guaranteeRuleId --- "+position+"--:"+guaranteeRuleId);
        if (null==guaranteeRuleId){//如果为空就是不需要担保
            holder.room_price_isroute.setText("免担保");
        }else{
            holder.room_price_isroute.setText("需要担保");
            for (GuaranteeRule guaranteeRule: guaranteeRules){
                if (guaranteeRule.getGuranteeRuleId().equals(guaranteeRuleId)){
                    if (guaranteeRule.isAmountGuarantee()==false&&guaranteeRule.isTimeGuarantee()==false){
                        holder.room_price_isroute.setText("需要担保");
                    }
                }
            }
        }
        Log.e("valueAddId", "Id = ： "+valueAddId);
        HotelDetail hotelDetail = MyApp.hotelDetail;
        Log.e("Facilities", "Facilities===: "+ hotelDetail.getFacilities());

        String  Facilities [] = hotelDetail.getFacilities().split(",");
        for (int f = 0; f < Facilities.length; f++) {
              int f_id = Integer.parseInt(Facilities[f]);
              switch (f_id){
                    case 1:
                     holder.room_price_wifi.setText("免费Wifi");
                  break;
                  case 2:
                      holder.room_price_wifi.setText("收费Wifi");
                      break;
                  case 3:
                      holder.room_price_wifi.setText("免费宽带");
                      break;
                  case 4:
                      holder.room_price_wifi.setText("收费宽带");
                      break;

              }
        }

        String valueArr [] = valueAddId.split(",");
        /*得到房间的附加服务内容*/
        for (int j = 0; j <valueArr.length ; j++) {
            String v_Id = valueArr[j];
            Log.e("----早餐ID：----", v_Id);
            if (v_Id.equals("")){
                holder.room_price_food.setText("无早");

            }else{
                for (int i = 0;i<valueAddses.size();i++){
                    ValueAdds value = valueAddses.get(i);

                    if (value.getValueAddId().equals(v_Id)){

                        if (value.getTypeCode().equals("01")) {//代表有早餐

                            if (value.getIsInclude()){//判断是否包含早餐

//                                Log.e("包含早餐", "包含早餐getView: "+"Typecode\t"+value.getTypeCode()+"\t"+value.getIsInclude()+ value.getAmount()+"份");
                                if (value.getAmount()==0){
                                    holder.room_price_food.setText("无早");
                                }else if (value.getAmount()==1){
                                    holder.room_price_food.setText("单早");
                                }else if (value.getAmount()==2){
                                    holder.room_price_food.setText("双早");
                                }else{
                                    holder.room_price_food.setText("三份以上早餐");
                                }
                            }else{
                                holder.room_price_food.setText("无早");
                            }

                        }
                    }
                }
            }
        }




        /*得到房间预付规则*/
        if(null==prepayRuleIds){
            holder.room_cancel_txt.setText("限时取消");
        }else{
            for (PrepayRule prepayR:prepayRules){
                if (prepayR.getPrepayRuleId().equals(prepayRuleIds)){
                    if (prepayR.getChangeRule().equals("PrepayNoChange")){
                        holder.room_cancel_txt.setText("不可取消");
                    }else{
                        holder.room_cancel_txt.setText("限时取消");
                    }
                }
            }
        }

       /*酒店预订*/
        holder.room_order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApp.setPlan(rooms.get(position));
                Intent  intent  =  new Intent(ctx,HotelOrderDetailT.class);
                ctx.startActivity(intent);

            }
        });
        return convertView;
    }

    public  class PlansRoomHolder{
        public TextView room_price_title;
        public TextView room_cancel_txt;//是否可以取消
        public TextView room_price_isroute;//是否需要担保
        public TextView room_price_wifi;
        public TextView room_price_food;//早餐
        public TextView room_hotel_price;
        public RelativeLayout room_order_btn;//预订按钮
        public TextView roon_payment_txt;//支付方式
    }
}
