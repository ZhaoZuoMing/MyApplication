package com.mymodels;

/**
 * Created by Administrator on 2017/6/14/014.
 * 行程数据
 */

public class TravelData {

    private String startDate;
    private String endDate;
    private int type;//1飞机票 2 酒店 3 火车
    private String startTime;
    private String endTime;
    private String startStation;
    private String endStation;
    private String name;
    private String OrderId;
    private String enter_days;

    public String getEnter_days() {
        return enter_days;
    }

    public void setEnter_days(String enter_days) {
        this.enter_days = enter_days;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }
}
