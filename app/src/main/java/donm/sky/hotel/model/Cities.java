package donm.sky.hotel.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/10/010.
 *  "CityCode": "3410",
    "CityName": "彰化（台湾）",
    "CityEName": "Changhua（Taiwan）",
    "ProvinceId": "3400",
    "ProvinceName": "台湾",
    "IsHot": "0",
   "SortIndex": "99",
   "Status": "1",
   "Country": "中国"
 */

public class Cities implements Serializable{

    private int id;
    private String code;
    private String name;
    private int isHot;
    private String eName;
    private int country;
    private int province;
    private String airport;
    private int status;
    private int sortIndex;
    private String ProvinceId;
    private String ProvinceName;
    private String countryName;



    public Cities() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(String provinceId) {
        ProvinceId = provinceId;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }
}
