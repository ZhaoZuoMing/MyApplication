package f.sky.flight.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/25/025.
 * 航班查询时需要传输的数据
 */

public class AirpotData  implements Serializable {

    private static final long serialVersionUID = 1L;
    private String startDate;
    private String backDate;
    private String startCity;
    private String endCity;
    private String flightName;//航空公司
    private boolean isBack;//是否为往返 0 为往返   1为单程
    private int userStarttime; //出发具体时间
    private String companyRole; //是否以符合公司政策查询
    private String startCityCode;
    private String endCityCode;

    public AirpotData(){

    }

    public AirpotData(String startDate, String backDate, String startCity, String endCity, String flightName,  boolean isBack, int userStarttime, String companyRole, String startCityCode, String endCityCode) {
        this.startDate = startDate;
        this.backDate = backDate;
        this.startCity = startCity;
        this.endCity = endCity;
        this.flightName = flightName;
        this.isBack = isBack;
        this.userStarttime = userStarttime;
        this.companyRole = companyRole;
        this.startCityCode = startCityCode;
        this.endCityCode = endCityCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStartCityCode() {
        return startCityCode;
    }

    public void setStartCityCode(String startCityCode) {
        this.startCityCode = startCityCode;
    }

    public String getEndCityCode() {
        return endCityCode;
    }

    public void setEndCityCode(String endCityCode) {
        this.endCityCode = endCityCode;
    }

    public String getCompanyRole() {
        return companyRole;
    }

    public void setCompanyRole(String companyRole) {
        this.companyRole = companyRole;
    }

    public int getUserStarttime() {
        return userStarttime;
    }

    public void setUserStarttime(int userStarttime) {
        this.userStarttime = userStarttime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }


    public boolean isBack() {
        return isBack;
    }

    public void setBack(boolean back) {
        isBack = back;
    }

    @Override
    public String toString() {
        return "AirpotData{" +
                "startDate='" + startDate + '\'' +
                ", backDate='" + backDate + '\'' +
                ", startCity='" + startCity + '\'' +
                ", endCity='" + endCity + '\'' +
                ", flightName='" + flightName + '\'' +
                ", flagIsBack=" + isBack +
                ", userStarttime=" + userStarttime +
                ", companyRole='" + companyRole + '\'' +
                ", startCityCode='" + startCityCode + '\'' +
                ", endCityCode='" + endCityCode + '\'' +
                '}';
    }
}
