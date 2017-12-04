package donm.sky.hotel.until;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;

import java.text.DecimalFormat;

import f.sky.flight.core.Constants;
import f.sky.flight.core.WebServUtil;

/**
 * Created by Administrator on 2017/5/11/011.
 */

public class HotelUntils {

    public  static  void saveAirport(Context ctx,String content){
        SharedPreferences share = ctx.getSharedPreferences(Constants.SAVE_AIRPORT, Context.MODE_PRIVATE);//实例化
        SharedPreferences.Editor editor = share.edit(); //使处于可编辑状态
        editor.putString(Constants.SAVE_AIRPORT_KEY, content);
        editor.commit();    //提交数据保存
        Toast.makeText(ctx,"文件保存成功",Toast.LENGTH_SHORT).show();
    }

    public  static String getAirport(Context context){
        SharedPreferences share = context.getSharedPreferences(Constants.SAVE_AIRPORT,
                Context.MODE_PRIVATE);
        return share.getString(Constants.SAVE_AIRPORT_KEY,"");
    }




    public static String  getPayMent(int payType) {
        if (payType==0){
            return  "在线预付";
        }else if (payType==1){
            return "到店支付";
         }else if (payType==2){
            return  "在线支付";
        }
        return "";
    }

    public static String getHotelOrderStatus(int status){
        switch (status){
            case 0:
                return "请求";
            case 5:
                return "取消";
            case 10:
                return "内审";
            case 15:
                return "提交";
            case 17:
                return "处理中";
            case 20:
                return "确认";
            case 22:
                return "离店";
            case 23:
                return "退单";
            case 25:
                return "核对";
            case 30:
                return "结算";
        }
        return "";
    }
    /*订单预订成功后的显示状态*/
    public static String orderStatus_1(int status){
        switch (status){
            case 0:
                return "请求中...";
            case 5:
                return "已取消";
            case 10:
                return "内审";
            case 15:
                return "提交中...";
            case 17:
                return "处理中...";
            case 20:
                return "已确认...";
            case 22:
                return "离店";
            case 23:
                return "已退单";
            case 25:
                return "核对中...";
            case 30:
                return "已结算";
        }
        return "";
    }

    /*对详细内容的显示*/
    public static String getOrderDetailStatus(int status){
        switch (status){
            case 0:
                return "订单请求中,下拉可刷新订单状态";
            case 5:
                return "订单已取消";
            case 10:
                return "订单内审中,下拉可刷新订单状态";
            case 15:
                return "订单已提交,下拉可刷新订单状态";
            case 17:
                return "订单处理中,下拉可刷新订单状态";
            case 20:
                return "订单确认,下拉可刷新订单状态";
            case 22:
                return "已离店";
            case 23:
                return "已退单";
            case 25:
                return "订单核对中,下拉可刷新订单状态";
            case 30:
                return "订单已结算";
        }
        return "";
    }

    /**
     * 获取网络状态
     * @param net
     * @return
     * 0-无
       1-免费宽带
       2-收费宽带
       3-免费WIFI
       4-收费WIFI
     */
    public static String getNet(String net) {
        if (net.equals("1")){
            return "免费宽带";
        }else if (net.equals("2")){
            return "收费宽带";
        }else if (net.equals("3")){
            return "免费Wifi";
        }else if (net.equals("4")){
            return "收费Wifi";
        }else{
            return "无网络";
        }
    }

    /**
     * 获取早餐
     * @param breakfast
     * @return
     */
    public static String getBreakHasfast(String breakfast) {
        if (breakfast.equals("0")){
            return "无早餐";
        }else if (breakfast.equals("1")){
            return "单早";
        }else if (breakfast.equals("2")){
            return "双早";
        }else{
            return "三分早餐";
        }
    }

    public static  void callPhone(Activity activity,String number){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + number);
        intent.setData(data);
        activity.startActivity(intent);
    }

    /***
     * 订单状态返回
     * @param status
     * @return
     */
    public static String getH_Status(int status){
        if (status==10){
            return "内审";
        }else if (status==20){
            return "已确认";
        }
        return "处理中";
    }

    /**
     * 价格转换器
     * @param price
     * @return
     */
    public  static String getParseNetPrice(String price){
        DecimalFormat decimal = new DecimalFormat("#.##");
        return "￥"+decimal.format(WebServUtil.parseDouble(price));
    }
}
