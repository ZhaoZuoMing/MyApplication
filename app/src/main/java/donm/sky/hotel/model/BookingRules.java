package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/1/12/012.
 * 预订协议
 */

public class BookingRules {
    /**
     *  <Description>酒店要求客人务必提供手机号</Description>
     <TypeCode>NeedPhoneNo</TypeCode>
     <DateType>BookDay</DateType>
     <StartDate>2013-11-20T00:00:00+08:00</StartDate>
     <EndDate>2063-11-20T00:00:00+08:00</EndDate>
     <StartHour>00:00:00</StartHour>
     <EndHour>23:59:00</EndHour>
     <BookingRuleId>69755</BookingRuleId>
     */
    private String Description;
    private String TypeCode;
    private String DateType;
    private String StartDate;
    private String EndDate;
    private String StartHour;
    private String EndHour;
    private String BookingRuleId;

    public BookingRules() {
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(String typeCode) {
        TypeCode = typeCode;
    }

    public String getDateType() {
        return DateType;
    }

    public void setDateType(String dateType) {
        DateType = dateType;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getStartHour() {
        return StartHour;
    }

    public void setStartHour(String startHour) {
        StartHour = startHour;
    }

    public String getEndHour() {
        return EndHour;
    }

    public void setEndHour(String endHour) {
        EndHour = endHour;
    }

    public String getBookingRuleId() {
        return BookingRuleId;
    }

    public void setBookingRuleId(String bookingRuleId) {
        BookingRuleId = bookingRuleId;
    }
}
