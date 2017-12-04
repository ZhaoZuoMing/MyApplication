package donm.sky.hotel.model;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8/008.
 */

public class DHotel {
    private String HotelId;
    private String LowRate;
    private String CurrencyCode;
    private String Facilities;
    private String Distance;
    private List<BookingRules> bookingRules;/*该酒店下所有的预订规则。应在订单填写页面提示给用户，也可以做到系统规则中约束用户的选择或输入。
                                            包含多个 BookingRule节点*/
    private List<GuaranteeRule> guaranteeRules;/*出现规则即表示需要担保。当 isTimeGuarantee和 isAmountGuarantee都等于false时候表示无条件强制担保*/
    private List<PrepayRule> prepayRules; /*预付规则*/
    private List<ValueAdds> valueAddses;
    private List<Room> rooms;

    public String getHotelId() {
        return HotelId;
    }

    public void setHotelId(String hotelId) {
        HotelId = hotelId;
    }

    public String getLowRate() {
        return LowRate;
    }

    public void setLowRate(String lowRate) {
        LowRate = lowRate;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getFacilities() {
        return Facilities;
    }

    public void setFacilities(String facilities) {
        Facilities = facilities;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public List<BookingRules> getBookingRules() {
        return bookingRules;
    }

    public void setBookingRules(List<BookingRules> bookingRules) {
        this.bookingRules = bookingRules;
    }

    public List<GuaranteeRule> getGuaranteeRules() {
        return guaranteeRules;
    }

    public void setGuaranteeRules(List<GuaranteeRule> guaranteeRules) {
        this.guaranteeRules = guaranteeRules;
    }

    public List<PrepayRule> getPrepayRules() {
        return prepayRules;
    }

    public void setPrepayRules(List<PrepayRule> prepayRules) {
        this.prepayRules = prepayRules;
    }

    public List<ValueAdds> getValueAddses() {
        return valueAddses;
    }

    public void setValueAddses(List<ValueAdds> valueAddses) {
        this.valueAddses = valueAddses;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
