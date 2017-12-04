package com.appliy.sdk;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.mytables.AppT;

import f.sky.flight.core.WebServUtil;

/**
 * Created by Administrator on 2016/12/12/012.
 */

public class PayDemoActivity extends AppT {


    // 合作商户ID。用签约支付宝账号登录ms.alipay.com后，在账户信息页面获取。相当于pid
    public static final String PARTNER = "2088001109659212";
    // 商户收款的支付宝账号
    public static final String SELLER =  "2088001109659212";
    // 商户（RSA）私钥
    public static final String RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANEOk/pkC551iM9yKISJzuf74KpF70ly1MKxpJI1VDCA9kzR54hOi0KZY3xc+Sv71DFftmjzDjhq1Qla5hz5Qz4MvZoqqjDoWY6zPtkLovAJxG6re3+IjLFQ+ZZl2oc1azNy8z0OlyfSUuGu/Tx7pmIQPyzwUPE2i70hr7NTHZBrAgMBAAECgYAVLLLUU7nRjPoUeO2drw+j86En3fmEqPrf1XpjzL8AQq+vzDr1VW9JiZvIBuq+B73QWm9xZdz03e1CC1DvclfbDAEOOWvG4KVUi/U1oaQYBzCZBlT1oUb4dbEobkfYGxyUcAfyaKfZFNI7eoMRiLQXVGRMs2X2AhlCHoUDBo5N8QJBAP4ltrpOa43GfTIbafWVZjmprbM6EPE//w6vvgyDF1J7ZN0f4c2hJ03ykmR+X5NL3oJ4Z+kt9aRDWi/Jimjs2TcCQQDSlLecF5MzsS5NULne/dD0t8kcH1ddgTuumJeAvsdRIabDAotIlkqxI5h10oyLlCFE+nucNOZvXtLLXeK93YxtAkBc0G0hMQ56YmjKadSSiZMhr+PttmEFmfeN54SikSVtS/8T+la+gAIYp2PrK3PAGU8TmuFn+Q7ihG5lUVco5oaVAkACaO1I/gQTPV3YSKo34RO5GTlV9w3a5hW2w8xIDubdCNONu8O4SvAD4vJltskg98sBrRQpD3nroFwtM5/aqGRpAkAY4caKr3bMj3Cf4ckkO7aOpq7FafHjJMwEyFPqU/V2/7a8TF45q2IVi/NMkzQRWUf7zLzbPQDYGIBe9l0J2GHb";
    // 支付宝（RSA）公钥 用签约支付宝账号登录ms.alipay.com后，在密钥管理页面获取。
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgofKcqXS24Bgkro3Wawy5Ftmh+ys696vY1pdu FmW/yqzmKPpRkcxTEyfBGkG0S4hk2Ml8jyaLyC4JZRnVdImguNhvSiCMFbd+abS458oI/5A0Gq/h 9lHWxQ3fHA3cvmnyBzcfWpySFfDO+oX2eXafKYfxxNswfhLvlcrrF9VbRwIDAQAB";

    protected static final int SDK_PAY_FLAG = 1;

    protected static final int SDK_CHECK_FLAG = 2;

    protected Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);

                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();

                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayDemoActivity.this, "支付成功",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayDemoActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayDemoActivity.this, "支付失败",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(PayDemoActivity.this, "检查结果为：" + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        };
    };

    /**
     * call alipay sdk pay. 调用SDK支付
     *
     */
//	public void pay(View v) {
//		if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
//				|| TextUtils.isEmpty(SELLER)) {
//			new AlertDialog.Builder(this)
//					.setTitle("警告")
//					.setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
//					.setPositiveButton("确定",
//							new DialogInterface.OnClickListener() {
//								public void onClick(
//										DialogInterface dialoginterface, int i) {
//									//
//									finish();
//								}
//							}).show();
//			return;
//		}
//		// 订单
//		String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01", 123456);
//
//		// 对订单做RSA 签名
//		String sign = sign(orderInfo);
//		try {
//			// 仅需对sign 做URL编码
//			sign = URLEncoder.encode(sign, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		// 完整的符合支付宝参数规范的订单信息
//		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
//				+ getSignType();
//
//		Runnable payRunnable = new Runnable() {
//
//			@Override
//			public void run() {
//				// 构造PayTask 对象
//				PayTask alipay = new PayTask(PayDemoActivity.this);
//				// 调用支付接口，获取支付结果
//				String result = alipay.pay(payInfo);
//
//				Message msg = new Message();
//				msg.what = SDK_PAY_FLAG;
//				msg.obj = result;
//				mHandler.sendMessage(msg);
//			}
//		};
//
//		// 必须异步调用
//		Thread payThread = new Thread(payRunnable);
//		payThread.start();
//	}
//
//	/**
//	 * check whether the device has authentication alipay account.
//	 * 查询终端设备是否存在支付宝认证账户
//	 *
//	 */
//	public void check(View v) {
//		Runnable checkRunnable = new Runnable() {
//
//			@Override
//			public void run() {
//				// 构造PayTask 对象
//				PayTask payTask = new PayTask(PayDemoActivity.this);
//				// 调用查询接口，获取查询结果
//				boolean isExist = payTask.checkAccountIfExist();
//
//				Message msg = new Message();
//				msg.what = SDK_CHECK_FLAG;
//				msg.obj = isExist;
//				mHandler.sendMessage(msg);
//			}
//		};
//
//		Thread checkThread = new Thread(checkRunnable);
//		checkThread.start();
//
//	}

    /**
     * get the sdk version. 获取SDK版本号
     *
     */
//	public void getSDKVersion() {
//		PayTask payTask = new PayTask(this);
//		String version = payTask.getVersion();
//		Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
//	}

    /**
     * create the order info. 创建订单信息
     *
     */
    public String getOrderInfo(String subject, String body, String price, int alipayOrderId,int whichpay) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号  如果whichpay = 1机票支付订单   2,酒店    3为火车票
       if (whichpay==1){
           orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo(alipayOrderId) + "\"";
       }else if (whichpay==2){
           orderInfo += "&out_trade_no=" + "\"" + getOutHotelNo(alipayOrderId) + "\"";
       }else{
           orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo(alipayOrderId) + "\"";
        }

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        if (whichpay==1){
            orderInfo += "&notify_url=" + "\"" + WebServUtil.ALIPAY_NOTIFY_URL
                    + "\"";
        }else if (whichpay==2){
            orderInfo += "&notify_url=" + "\"" + WebServUtil.HOTEL_PAY_NOTIFY
                    + "\"";
        }

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * 构建酒店支付订单号
     * @param alipayId
     * @return 26717
     */
    private String getOutHotelNo(int alipayId) {
         String  apliyId = String.valueOf(alipayId);
         if (apliyId.length()<9){
             String zero = "";
             int needLg = 9-apliyId.length();
             for (int i = 0; i <needLg; i++) {
                 zero+="0";
             }
              apliyId = "02"+zero+apliyId;
         }
        return apliyId;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    public String getOutTradeNo(int orderId) {
        String alipayOrderId = String.valueOf(orderId);
        if(alipayOrderId.length() == 5){
            alipayOrderId = "0300" + alipayOrderId;
        } else if (alipayOrderId.length() == 6){
            alipayOrderId = "030" + alipayOrderId;
        } else if (alipayOrderId.length() == 7){
            alipayOrderId = "03" + alipayOrderId;
        }
        return alipayOrderId;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    public String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }


}
