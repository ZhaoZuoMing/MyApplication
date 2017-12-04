package f.sky.flight.model;

/**
 * Created by zhaody on 2017/11/27.
 * 经停机场
 */

public class StopCities {
    private String AirportCode;
    private String AirportName;
    private String CityName;
    private String ArriveTime;
    private String TakeoffTime;
    private String StayTime;

    public String getAirportCode() {
        return AirportCode;
    }

    public void setAirportCode(String airportCode) {
        AirportCode = airportCode;
    }

    public String getAirportName() {
        return AirportName;
    }

    public void setAirportName(String airportName) {
        AirportName = airportName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getArriveTime() {
        return ArriveTime;
    }

    public void setArriveTime(String arriveTime) {
        ArriveTime = arriveTime;
    }

    public String getTakeoffTime() {
        return TakeoffTime;
    }

    public void setTakeoffTime(String takeoffTime) {
        TakeoffTime = takeoffTime;
    }

    public String getStayTime() {
        return StayTime;
    }

    public void setStayTime(String stayTime) {
        StayTime = stayTime;
    }
}
