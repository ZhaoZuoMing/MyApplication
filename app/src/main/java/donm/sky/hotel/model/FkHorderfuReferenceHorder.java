package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/3/29/029.
 */

public class FkHorderfuReferenceHorder {

    /**
     *  <Orderid>25571</Orderid>
     <Num>1</Num>
     <NetPrice>328.0000</NetPrice>
     <ClientNetPrice>328.0000</ClientNetPrice>
     <SaleAmount>328.0000</SaleAmount>
     <TakePrice>0.0000</TakePrice>
     <RealPrice>0.0000</RealPrice>
     <Tax>0.0000</Tax>
     <Profit>0.0000</Profit>
     <Award1>0.0000</Award1>
     <Award2>0.0000</Award2>
     <RaisePrice>0.0000</RaisePrice>
     <ServiceFee>0.0000</ServiceFee>
     <FavorablePrice>0.0000</FavorablePrice>
     <OtherCharge>0.0000</OtherCharge>
     <CurrencyCode>CNY</CurrencyCode>
     <Memo />
     <IsDeleted>false</IsDeleted>
     <IsChanged>true</IsChanged>
     */

    private String Orderid;
    private int num;
    private String NetPrice;
    private String ClientNetPrice;
    private String SaleAmount;
    private String TakePrice;
    private String RealPrice;
    private String Tax;
    private String Profit;
    private String Award1;
    private String RaisePrice;
    private String Award2;
    private String ServiceFee;
    private String FavorablePrice;
    private String OtherCharge;
    private String CurrencyCode;

    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNetPrice() {
        return NetPrice;
    }

    public void setNetPrice(String netPrice) {
        NetPrice = netPrice;
    }

    public String getClientNetPrice() {
        return ClientNetPrice;
    }

    public void setClientNetPrice(String clientNetPrice) {
        ClientNetPrice = clientNetPrice;
    }

    public String getSaleAmount() {
        return SaleAmount;
    }

    public void setSaleAmount(String saleAmount) {
        SaleAmount = saleAmount;
    }

    public String getTakePrice() {
        return TakePrice;
    }

    public void setTakePrice(String takePrice) {
        TakePrice = takePrice;
    }

    public String getRealPrice() {
        return RealPrice;
    }

    public void setRealPrice(String realPrice) {
        RealPrice = realPrice;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String tax) {
        Tax = tax;
    }

    public String getProfit() {
        return Profit;
    }

    public void setProfit(String profit) {
        Profit = profit;
    }

    public String getAward1() {
        return Award1;
    }

    public void setAward1(String award1) {
        Award1 = award1;
    }

    public String getRaisePrice() {
        return RaisePrice;
    }

    public void setRaisePrice(String raisePrice) {
        RaisePrice = raisePrice;
    }

    public String getAward2() {
        return Award2;
    }

    public void setAward2(String award2) {
        Award2 = award2;
    }

    public String getServiceFee() {
        return ServiceFee;
    }

    public void setServiceFee(String serviceFee) {
        ServiceFee = serviceFee;
    }

    public String getFavorablePrice() {
        return FavorablePrice;
    }

    public void setFavorablePrice(String favorablePrice) {
        FavorablePrice = favorablePrice;
    }

    public String getOtherCharge() {
        return OtherCharge;
    }

    public void setOtherCharge(String otherCharge) {
        OtherCharge = otherCharge;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }
}
