package donm.sky.hotel.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/12/012.
 *  对应房型的不同价格数据
 */

public class RatePlans implements Serializable {
    /**
     *
     *<RatePlanId>357068</RatePlanId>
     <RatePlanName>不含早（艺龙直采预付售后保障）</RatePlanName>
     <MinAmount>1</MinAmount>
     <MinDays>1</MinDays>
     <MaxDays>365</MaxDays>
     <PaymentType>Prepay</PaymentType>
     <Status>true</Status>
     <CustomerType>All</CustomerType>
     <CurrentAlloment>0</CurrentAlloment>
     <InstantConfirmation>true</InstantConfirmation>
     <IsLastMinuteSale>false</IsLastMinuteSale>
     <StartTime>00:00:00</StartTime>
     <EndTime>23:59:00</EndTime>
     <TotalRate>930.75</TotalRate>
     <AverageRate>310.25</AverageRate>
     <AverageBaseRate xsi:nil="true" />
     <CurrencyCode>RMB</CurrencyCode>
     <Coupon>0.0</Coupon>
     BookingRuleIds>69755</BookingRuleIds>
     <PrepayRuleIds>1</PrepayRuleIds>
     <ValueAddIds />
     <RoomTypeId>1105</RoomTypeId>
     <HotelCode>30201106</HotelCode>
     <InvoiceMode>Elong</InvoiceMode>
     */
    private  String RatePlanId;
    private String RatePlanName;
    private String MinAmount;
    private int MinDays;
    private int MaxDays;
    private String PaymentType;
    private String CustomerType;
    private  boolean Status;
    private  int CurrentAlloment;
    private  boolean InstantConfirmation;
    private  boolean IsLastMinuteSale;
    private  String StartTime;
    private  String EndTime;
    private  String TotalRate;
    private  String AverageRate;
    private  String CurrencyCode;
    private  String Coupon;
    private  String BookingRuleIds;
    private  String PrepayRuleIds;
    private  String ValueAddIds;
    private  String GuaranteeRuleIds;//担保规则Id
    private  String RoomTypeId;
    private  int HotelCode;
    private  String InvoiceMode;


    private List<NightlyRates> NightlyRates;

    public String getGuaranteeRuleIds() {
        return GuaranteeRuleIds;
    }

    public void setGuaranteeRuleIds(String guaranteeRuleIds) {
        GuaranteeRuleIds = guaranteeRuleIds;
    }

    public String getValueAddIds() {
        return ValueAddIds;
    }

    public void setValueAddIds(String valueAddIds) {
        ValueAddIds = valueAddIds;
    }

    public String getPrepayRuleIds() {
        return PrepayRuleIds;
    }

    public void setPrepayRuleIds(String prepayRuleIds) {
        PrepayRuleIds = prepayRuleIds;
    }

    public RatePlans(String totalRate, String ratePlanName) {
        TotalRate = totalRate;
        RatePlanName = ratePlanName;
    }

    public RatePlans() {
    }

    public List<donm.sky.hotel.model.NightlyRates> getNightlyRates() {
        return NightlyRates;
    }

    public void setNightlyRates(List<donm.sky.hotel.model.NightlyRates> nightlyRates) {
        NightlyRates = nightlyRates;
    }

    public String getRatePlanId() {
        return RatePlanId;
    }

    public void setRatePlanId(String ratePlanId) {
        RatePlanId = ratePlanId;
    }

    public String getRatePlanName() {
        return RatePlanName;
    }

    public void setRatePlanName(String ratePlanName) {
        RatePlanName = ratePlanName;
    }

    public String getMinAmount() {
        return MinAmount;
    }

    public void setMinAmount(String minAmount) {
        MinAmount = minAmount;
    }

    public int getMinDays() {
        return MinDays;
    }

    public void setMinDays(int minDays) {
        MinDays = minDays;
    }

    public int getMaxDays() {
        return MaxDays;
    }

    public void setMaxDays(int maxDays) {
        MaxDays = maxDays;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String customerType) {
        CustomerType = customerType;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public int getCurrentAlloment() {
        return CurrentAlloment;
    }

    public void setCurrentAlloment(int currentAlloment) {
        CurrentAlloment = currentAlloment;
    }

    public boolean isInstantConfirmation() {
        return InstantConfirmation;
    }

    public void setInstantConfirmation(boolean instantConfirmation) {
        InstantConfirmation = instantConfirmation;
    }

    public boolean isLastMinuteSale() {
        return IsLastMinuteSale;
    }

    public void setLastMinuteSale(boolean lastMinuteSale) {
        IsLastMinuteSale = lastMinuteSale;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getTotalRate() {
        return TotalRate;
    }

    public void setTotalRate(String totalRate) {
        TotalRate = totalRate;
    }

    public String getAverageRate() {
        return AverageRate;
    }

    public void setAverageRate(String averageRate) {
        AverageRate = averageRate;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getCoupon() {
        return Coupon;
    }

    public void setCoupon(String coupon) {
        Coupon = coupon;
    }

    public String getBookingRuleIds() {
        return BookingRuleIds;
    }

    public void setBookingRuleIds(String bookingRuleIds) {
        BookingRuleIds = bookingRuleIds;
    }

    public String getRoomTypeId() {
        return RoomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        RoomTypeId = roomTypeId;
    }

    public int getHotelCode() {
        return HotelCode;
    }

    public void setHotelCode(int hotelCode) {
        HotelCode = hotelCode;
    }

    public String getInvoiceMode() {
        return InvoiceMode;
    }

    public void setInvoiceMode(String invoiceMode) {
        InvoiceMode = invoiceMode;
    }
}

