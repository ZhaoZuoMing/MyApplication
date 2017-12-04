package f.sky.flight.model;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class AirportObj {


    private String AirPortCode;
    private String CityCode;
    private String AirPortName;
    private String EngName;
    private String Field1;
    private String Field2;//是否热门
    private String Field3;
    private String Remark;

    private String firstPy;
    public String getAirPortCode() {
        return AirPortCode;
    }
    public void setAirPortCode(String airPortCode) {
        AirPortCode = airPortCode;
    }
    public String getCityCode() {
        return CityCode;
    }
    public void setCityCode(String cityCode) {
        CityCode = cityCode;
        firstPy = cityCode.substring(0, 1);
    }
    public String getAirPortName() {
        return AirPortName;
    }
    public void setAirPortName(String airPortName) {
        AirPortName = airPortName;
    }
    public String getEngName() {
        return EngName;
    }
    public void setEngName(String engName) {
        EngName = engName;
    }
    public String getField1() {
        return Field1;
    }
    public void setField1(String field1) {
        Field1 = field1;
    }
    public String getField2() {
        return Field2;
    }
    public void setField2(String field2) {
        Field2 = field2;
    }
    public String getField3() {
        return Field3;
    }
    public void setField3(String field3) {
        Field3 = field3;
    }
    public String getRemark() {
        return Remark;
    }
    public void setRemark(String remark) {
        Remark = remark;
    }
    public String getFirstPy() {
        return firstPy;
    }
    public void setFirstPy(String firstPy) {
        this.firstPy = firstPy;
    }

    @Override
    public String toString() {
        return "AirportObj{" +
                "AirPortCode='" + AirPortCode + '\'' +
                ", CityCode='" + CityCode + '\'' +
                ", AirPortName='" + AirPortName + '\'' +
                ", EngName='" + EngName + '\'' +
                ", Field1='" + Field1 + '\'' +
                ", Field2='" + Field2 + '\'' +
                ", Field3='" + Field3 + '\'' +
                ", Remark='" + Remark + '\'' +
                ", firstPy='" + firstPy + '\'' +
                '}';
    }
}
