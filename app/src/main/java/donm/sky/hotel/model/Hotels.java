package donm.sky.hotel.model;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12/012.
 * 酒店所有数据Javabean
 */

public class Hotels {
    private int hotelId;
    private String LowRate;
    private String CurrencyCode;
    private String Distance;
    private List<BookingRules> bookingRules;
    private List<Room> rooms;

    public Hotels() {
    }

    public Hotels(int hotelId, String lowRate, String currencyCode, String distance, List<BookingRules> bookingRules, List<Room> rooms) {
        this.hotelId = hotelId;
        LowRate = lowRate;
        CurrencyCode = currencyCode;
        Distance = distance;
        this.bookingRules = bookingRules;
        this.rooms = rooms;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
