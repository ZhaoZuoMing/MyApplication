package donm.sky.hotel.t;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.appliy.sdk.PayDemoActivity;
import com.example.administrator.dmonline.R;
import com.mytables.MyApp;
import com.myviews.MyListView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;

import donm.sky.hotel.adapter.HorderGuestAdapter;
import donm.sky.hotel.model.HotelDataList;
import donm.sky.hotel.until.TimeUntil;
import f.sky.flight.core.WebServUtil;

/**
 * Created by Administrator on 2017/4/6/006.
 * 酒店订单详情
 */

public class OrderHotelDetail extends PayDemoActivity {

     private TextView order_hotel_name,order_hotel_checkIn,order_hotel_checkOut,order_hotel_price,order_hotel_address,order_hotel_roomtype,order_hotel_paytype,
             order_hotel_lasttime,order_hotel_needbad,app_title_txt;
     private EditText user_contact_name,user_ponenumber_name,user_emil_address,user_pjname_name;
     private TextView user_chenben_name;
     private MyListView myListView;
     protected HotelDataList orderData;
     private ImageView app_back_icon;


    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        orderData = MyApp.hotelDataList;
        setContentView(R.layout.order_hotel_detail);
        initView();

    }

    private void initView() {
        order_hotel_name = (TextView) findViewById(R.id.order_hotel_name);
        order_hotel_checkIn = (TextView) findViewById(R.id.order_hotel_checkin);
        order_hotel_checkOut = (TextView)findViewById(R.id.order_hotel_checkout);
        order_hotel_price = (TextView) findViewById(R.id.order_hotel_price);
        order_hotel_address = (TextView) findViewById(R.id.order_hotel_address);
        order_hotel_roomtype = (TextView) findViewById(R.id.order_hotel_roomtype);
        order_hotel_paytype = (TextView) findViewById(R.id.order_hotel_paytype);
        order_hotel_lasttime = (TextView) findViewById(R.id.order_hotel_lasttime);
        order_hotel_needbad = (TextView) findViewById(R.id.order_hotel_needbad);
        user_chenben_name = (TextView) findViewById(R.id.user_chenben_name);
        app_title_txt = (TextView) findViewById(R.id.app_title_txt);

        user_contact_name = (EditText)findViewById(R.id.user_contact_name);
        user_ponenumber_name = (EditText)findViewById(R.id.user_ponenumber_name);
        user_emil_address = (EditText)findViewById(R.id.user_emil_address);
        user_pjname_name = (EditText)findViewById(R.id.user_pjname_name);

        findViewById(R.id.img_for_chengben).setVisibility(View.GONE);
        myListView = (MyListView) findViewById(R.id.order_hotel_persons);
        app_back_icon = (ImageView)findViewById(R.id.app_back_icon);
        app_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setMyHotelData();
    }
    /*测试支付功能*/
    public  void payBtn(View view){
        toAlipay();
    }


    /**
     * 支付机票
     */
    private void toAlipay(){
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
                || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(this)
                    .setTitle("警告")
                    .setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
                                    finish();
                                }
                            }).show();
            return;
        }
        double orderPrice = 0.01;
        DecimalFormat df =  new DecimalFormat("#.##");
        String price = df.format(orderPrice);
        // 订单
        String orderInfo = getOrderInfo("机票", "差旅无线机票支付", price, WebServUtil.parseInt(orderData.getOrderid()),2);

        // 对订单做RSA 签名
        String sign = sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
                + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(OrderHotelDetail.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();


//		// check to see if the MobileSecurePay is already installed.
//					// 检测安全支付服务是否被安装
//					MobileSecurePayHelper mspHelper = new MobileSecurePayHelper(this);
//					boolean isMobile_spExist = mspHelper.detectMobile_sp();
//					if (!isMobile_spExist){
//						return;
//					}
//
//					// check some info.
//					// 检测配置信息
//					if (!checkInfo()) {
//						BaseHelper
//								.showDialog(
//										OrderDetailT.this,
//										"提示",
//										"缺少partner或者seller，请在src/com/alipay/android/appDemo4/PartnerConfig.java中增加。",
//										R.drawable.infoicon);
//						return;
//					}
//
//
//					int orderId = orderObj.getID();
//					double orderPrice = orderObj.getTotalPrice();
//					DecimalFormat df =  new DecimalFormat("#.##");
//					String price = df.format(orderPrice);
//					String alipayOrderId = String.valueOf(orderId);
//					if(alipayOrderId.length() == 5){
//						alipayOrderId = "0300" + alipayOrderId;
//					} else if (alipayOrderId.length() == 6){
//						alipayOrderId = "030" + alipayOrderId;
//					} else if (alipayOrderId.length() == 7){
//						alipayOrderId = "03" + alipayOrderId;
//					}
//
//					// start pay for this order.
//					// 根据订单信息开始进行支付
//					try {
//						// prepare the order info.
//						// 准备订单信息
//						String orderInfo = getOrderInfo(alipayOrderId, price, "机票", "差旅无线机票支付");
//						// 这里根据签名方式对订单信息进行签名
//						String signType = getSignType();
//						String strsign = sign(signType, orderInfo);
//						ALog.i(strsign);
//						// 对签名进行编码
//						strsign = URLEncoder.encode(strsign);
//						// 组装好参数
//						String info = orderInfo + "&sign=" + "\"" + strsign + "\"" + "&"
//								+ getSignType();
//						ALog.i("orderInfo:", info);
//						// start the pay.
//						// 调用pay方法进行支付
//						MobileSecurePayer msp = new MobileSecurePayer();
//						boolean bRet = msp.pay(info, mHandler, AlixId.RQF_PAY, this);
//
//						if (bRet) {
//							// show the progress bar to indicate that we have started
//							// paying.
//							// 显示"正在支付"进度条
//							closeProgress();
//							mProgress = BaseHelper.showProgress(this, null, "正在支付", false,
//									true);
//						} else
//							;
//					} catch (Exception ex) {
//						Toast.makeText(OrderDetailT.this, R.string.remote_call_failed,
//								Toast.LENGTH_SHORT).show();
//					}
    }
   /*设置酒店数据*/
    private void setMyHotelData() {
        order_hotel_name.setText(orderData.getHotelName());
        order_hotel_checkIn.setText(orderData.getCheckInDate()+"("+TimeUntil.getWeek(orderData.getCheckInDate())+")");
        order_hotel_checkOut.setText(orderData.getCheckOutDate()+"("+TimeUntil.getWeek(orderData.getCheckOutDate())+")");
        DecimalFormat decimal = new DecimalFormat("#.##");
        String price  = decimal.format(WebServUtil.parseDouble(orderData.getFkHorderfuReferenceHorder().getNetPrice()));
        order_hotel_price.setText("￥"+price+"元");
        order_hotel_address.setText(orderData.getHotelAddress());
        String breakFast = isBreakHasfast(orderData.getBreakfast());
        String net =  IsFreeNet(orderData.getNet());
        String night_num = "(共"+TimeUntil.daysBetween(orderData.getCheckInDate(),orderData.getCheckOutDate())+"晚)";
        order_hotel_roomtype.setText(orderData.getRoomTypeName()+night_num +"\t"+breakFast+"\t"+net);
        order_hotel_paytype.setText(getPayMent(orderData.getPayment()));
        order_hotel_lasttime.setText(orderData.getLaterArrivalTime()+ "("+TimeUntil.getWeek(orderData.getCheckInDate())+")");
        order_hotel_needbad.setText(orderData.getSpecialRequest());
        user_chenben_name.setText(orderData.getCostCenter());
        user_pjname_name.setText(orderData.getCostNumber());
        app_title_txt.setText("酒店订单号:"+orderData.getOrderid());
        user_contact_name.setText(orderData.getHorderContact().getContactPersonName());
        user_ponenumber_name.setText(orderData.getHorderContact().getPhoneNumber());
        user_emil_address.setText(orderData.getHorderContact().getEmail());
        myListView.setAdapter(new HorderGuestAdapter(this,orderData.getHorderGuest()));

    }
    /**
     * 获取支付方式
     * @return
     */
    private String  getPayMent(int payType) {
        if (payType==0){
            return  "预付";
        }else if (payType==1){
            return "前台现付";
        }else {
            return  "其他";
        }
    }
    /**
     * 获取网络状态
     * @param net
     * @return
     */
    private String IsFreeNet(String net) {
       if (net.equals("1")||net.equals("3")){
           return "免费Wifi";
       }else if (net.equals("2")||net.equals("4")){
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
    private String isBreakHasfast(String breakfast) {
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


}
