package com.mymodels;

import f.sky.flight.model.ServerResObj;

/**
 * Created by Administrator on 2017/4/12/012.
 * {
 "status": 0,
 "result": {
 "location": {
 "lng": 121.45308599999991,
 "lat": 31.204286863096026
 },
 "formatted_address": "上海市徐汇区肇嘉浜路839号",
 "business": "肇嘉浜路,徐家汇,交通大学",
 "addressComponent": {
 "country": "中国",
 "country_code": 0,
 "province": "上海市",
 "city": "上海市",
 "district": "徐汇区",
 "adcode": "310104",
 "street": "肇嘉浜路",
 "street_number": "839号",
 "direction": "附近",
 "distance": "19"
 },
 "pois": [],
 "poiRegions": [],
 "sematic_description": "尚秀商务楼附近46米",
 "cityCode": 289
 }
 */

public class MyLocation extends ServerResObj{

    private int status;
    private double lng;//精度
    private double lat;//纬度
    private String formatted_address;
    private String business;
    private String country;
    private String country_code;
    private String province;
    private String city;
    private String district;
    private String street;
    private String street_number;
    private String direction;
    private String distance;
    private String sematic_description;
    private String cityCode;
    private String adcode;


    public MyLocation() {
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSematic_description() {
        return sematic_description;
    }

    public void setSematic_description(String sematic_description) {
        this.sematic_description = sematic_description;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
