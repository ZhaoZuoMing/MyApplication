package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/3/17/017.
 * 酒店数据验证时返回的对象
 */

public class ResultObject {
    /**
     *    <ResultCode>OK</ResultCode>
          <GuaranteeRate>0.0</GuaranteeRate>
          <CurrencyCode>RMB</CurrencyCode>
          <CancelTime>2017-03-17T23:59:59+08:00</CancelTime>
     */
    private  String Code;//如果为0表示可以预定


    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }


        private String ResultCode;   //如果为ok则表示完全成功
        private String GuaranteeRate;//需要担保的金额
        private  String CurrencyCode;//货币类型
        private String CancelTime;   //最晚取消时间

        public String getResultCode() {
            return ResultCode;
        }

        public void setResultCode(String resultCode) {
            ResultCode = resultCode;
        }

        public String getGuaranteeRate() {
            return GuaranteeRate;
        }

        public void setGuaranteeRate(String guaranteeRate) {
            GuaranteeRate = guaranteeRate;
        }

        public String getCurrencyCode() {
            return CurrencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            CurrencyCode = currencyCode;
        }

        public String getCancelTime() {
            return CancelTime;
        }

        public void setCancelTime(String cancelTime) {
            CancelTime = cancelTime;
        }


}
