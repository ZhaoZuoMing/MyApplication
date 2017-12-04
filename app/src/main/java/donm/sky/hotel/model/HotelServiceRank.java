package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/1/13/013.
 * 本酒店服务排行
 *  <HotelServiceRank>
      <anyType xsi:type="HE_HotelServiceRank">
 <ID>259074</ID>
 <HotelID>269047</HotelID>
 <HotelCode>30201106</HotelCode>

 <SummaryScore>4.50</SummaryScore>
 <SummaryRate>11%</SummaryRate>
 <InstantConfirmScore>75%</InstantConfirmScore>
 <InstantConfirmRate>6%</InstantConfirmRate>
 <BookingSuccessScore>98%</BookingSuccessScore>
 <BookingSuccessRate>5%</BookingSuccessRate>
 <ComplaintScore>0%</ComplaintScore>
 <ComplaintRate>0%</ComplaintRate>
      </anyType>
 </HotelServiceRank>
 */

public class HotelServiceRank {
     private int Id;
     private int HotelID;
     private int HotelCode;
     private double SummaryScore;
     private String SummaryRate;
    private String InstantConfirmScore;
    private String InstantConfirmRate;
    private String BookingSuccessScore;
    private String BookingSuccessRate;
    private String ComplaintScore;
    private String ComplaintRate;

    public HotelServiceRank() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getHotelID() {
        return HotelID;
    }

    public void setHotelID(int hotelID) {
        HotelID = hotelID;
    }

    public int getHotelCode() {
        return HotelCode;
    }

    public void setHotelCode(int hotelCode) {
        HotelCode = hotelCode;
    }

    public double getSummaryScore() {
        return SummaryScore;
    }

    public void setSummaryScore(double summaryScore) {
        SummaryScore = summaryScore;
    }

    public String getSummaryRate() {
        return SummaryRate;
    }

    public void setSummaryRate(String summaryRate) {
        SummaryRate = summaryRate;
    }

    public String getInstantConfirmScore() {
        return InstantConfirmScore;
    }

    public void setInstantConfirmScore(String instantConfirmScore) {
        InstantConfirmScore = instantConfirmScore;
    }

    public String getInstantConfirmRate() {
        return InstantConfirmRate;
    }

    public void setInstantConfirmRate(String instantConfirmRate) {
        InstantConfirmRate = instantConfirmRate;
    }

    public String getBookingSuccessScore() {
        return BookingSuccessScore;
    }

    public void setBookingSuccessScore(String bookingSuccessScore) {
        BookingSuccessScore = bookingSuccessScore;
    }

    public String getBookingSuccessRate() {
        return BookingSuccessRate;
    }

    public void setBookingSuccessRate(String bookingSuccessRate) {
        BookingSuccessRate = bookingSuccessRate;
    }

    public String getComplaintScore() {
        return ComplaintScore;
    }

    public void setComplaintScore(String complaintScore) {
        ComplaintScore = complaintScore;
    }

    public String getComplaintRate() {
        return ComplaintRate;
    }

    public void setComplaintRate(String complaintRate) {
        ComplaintRate = complaintRate;
    }
}
