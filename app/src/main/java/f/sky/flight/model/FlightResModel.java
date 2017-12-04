package f.sky.flight.model;

import java.util.List;

/**
 * Created by zhaody on 2017/11/15.
 * "Status": true,
 "Code": "Success",
 "Message": "获取成功",
 "Timestamp": 1510564169,
 "Date": "24NOV17",
 "Week": "FRI",
 "FromCity": "SHA",
 "ToCity": "BJS",
 */

public class FlightResModel {
    private  boolean Status;
    private String code;
    private String message;
    private int Timestamp;
    private String Date;
    private String Week;
    private String FromCity;
    private String ToCity;

    private List<FlightSegment> flightSegmentList;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getWeek() {
        return Week;
    }

    public void setWeek(String week) {
        Week = week;
    }

    public String getFromCity() {
        return FromCity;
    }

    public void setFromCity(String fromCity) {
        FromCity = fromCity;
    }

    public String getToCity() {
        return ToCity;
    }

    public void setToCity(String toCity) {
        ToCity = toCity;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(int timestamp) {
        Timestamp = timestamp;
    }

    public List<FlightSegment> getFlightSegmentList() {
        return flightSegmentList;
    }

    public void setFlightSegmentList(List<FlightSegment> flightSegmentList) {
        this.flightSegmentList = flightSegmentList;
    }
}
