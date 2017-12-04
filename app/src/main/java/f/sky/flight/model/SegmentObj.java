package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/25/025.
 */

public class SegmentObj {

    private String AirLine;//<AirLine>MU</AirLine>
    private String AirLineName;//<AirLineName>东方航空</AirLineName>
    private String AirLineEName;//<AirLineEName>CHINA EASTERN AIRLINE</AirLineEName>
    private String FlightNo;//<FlightNo>MU5137</FlightNo>
    private String OrgCity;//<OrgCity>SHA</OrgCity>
    private String OrgCityName;//<OrgCityName>上海虹桥</OrgCityName>
    private String OrgCityEName;//<OrgCityEName>Shanghai-Hongqiao</OrgCityEName>
    private String OrgT;//<OrgT>T2</OrgT>
    private String DstCity;//<DstCity>PEK</DstCity>
    private String DstCityName;//<DstCityName>北京首都</DstCityName>
    private String DstCityEName;//<DstCityEName>Beijing</DstCityEName>
    private String DstT;//<DstT>T2</DstT>
    private String DepDate;//<DepDate>2012-05-25</DepDate>
    private String DepTime;//<DepTime>07:00</DepTime>起飞时间
    private boolean IsDepTimeModify;//<IsDepTimeModify>false</IsDepTimeModify>
    private String ArrDate;//<ArrDate>2012-05-25</ArrDate>
    private String ArrTime;//<ArrTime>09:20</ArrTime>
    private boolean IsArrTimeModify;//<IsArrTimeModify>false</IsArrTimeModify>
    private int FlyTime;//<FlyTime>140</FlyTime>
    private boolean Asr;//<Asr>true</Asr>
    private boolean IsEtkt;//<IsEtkt>true</IsEtkt>
    private boolean IsRule;//<IsRule>false</IsRule>
    private String Link;//<Link>DS#</Link>
    private boolean Meal;//<Meal>false</Meal>
    private String MealCode;//<MealCode />
    private String PlaneStyle;//<PlaneStyle>333</PlaneStyle>
    private boolean IsCodeShare;//<IsCodeShare>false</IsCodeShare>
    private String Carrier;//<Carrier />
    private double LowestPrice;//<LowestPrice>680</LowestPrice>
    private double AirportTax;//<AirportTax>50</AirportTax>
    private double FuelTax;//<FuelTax>150</FuelTax>
    private double OtherPrice;//<OtherPrice>0</OtherPrice>
    private String CabinCodeInfo;//<CabinCodeInfo>FA PA A8 YA KA BA EA HA LA MA NA RA SA VA TQ WQ GQ XQ QA I3 </CabinCodeInfo>
    private String Remark;//<Remark />
    private String StopCities;//<StopCities />

    private List<CabinPriceExObj> cabins = new ArrayList<CabinPriceExObj>();

    public List<CabinPriceExObj> getCabins() {
        return cabins;
    }
    public void setCabins(List<CabinPriceExObj> cabins) {
        this.cabins = cabins;
    }
    public String getAirLine() {
        return AirLine;
    }
    public void setAirLine(String airLine) {
        AirLine = airLine;
    }
    public String getAirLineName() {
        return AirLineName;
    }
    public void setAirLineName(String airLineName) {
        AirLineName = airLineName;
    }
    public String getAirLineEName() {
        return AirLineEName;
    }
    public void setAirLineEName(String airLineEName) {
        AirLineEName = airLineEName;
    }
    public String getFlightNo() {
        return FlightNo;
    }
    public void setFlightNo(String flightNo) {
        FlightNo = flightNo;
    }
    public String getOrgCity() {
        return OrgCity;
    }
    public void setOrgCity(String orgCity) {
        OrgCity = orgCity;
    }
    public String getOrgCityName() {
        return OrgCityName;
    }
    public void setOrgCityName(String orgCityName) {
        OrgCityName = orgCityName;
    }
    public String getOrgCityEName() {
        return OrgCityEName;
    }
    public void setOrgCityEName(String orgCityEName) {
        OrgCityEName = orgCityEName;
    }
    public String getOrgT() {
        return OrgT;
    }
    public void setOrgT(String orgT) {
        OrgT = orgT;
    }
    public String getDstCity() {
        return DstCity;
    }
    public void setDstCity(String dstCity) {
        DstCity = dstCity;
    }
    public String getDstCityName() {
        return DstCityName;
    }
    public void setDstCityName(String dstCityName) {
        DstCityName = dstCityName;
    }
    public String getDstCityEName() {
        return DstCityEName;
    }
    public void setDstCityEName(String dstCityEName) {
        DstCityEName = dstCityEName;
    }
    public String getDstT() {
        return DstT;
    }
    public void setDstT(String dstT) {
        DstT = dstT;
    }
    public String getDepDate() {
        return DepDate;
    }
    public void setDepDate(String depDate) {
        DepDate = depDate;
    }
    public String getDepTime() {
        return DepTime;
    }
    public void setDepTime(String depTime) {
        DepTime = depTime;
    }
    public boolean isIsDepTimeModify() {
        return IsDepTimeModify;
    }
    public void setIsDepTimeModify(boolean isDepTimeModify) {
        IsDepTimeModify = isDepTimeModify;
    }
    public String getArrDate() {
        return ArrDate;
    }
    public void setArrDate(String arrDate) {
        ArrDate = arrDate;
    }
    public String getArrTime() {
        return ArrTime;
    }
    public void setArrTime(String arrTime) {
        ArrTime = arrTime;
    }
    public boolean isIsArrTimeModify() {
        return IsArrTimeModify;
    }
    public void setIsArrTimeModify(boolean isArrTimeModify) {
        IsArrTimeModify = isArrTimeModify;
    }
    public int getFlyTime() {
        return FlyTime;
    }
    public void setFlyTime(int flyTime) {
        FlyTime = flyTime;
    }
    public boolean isAsr() {
        return Asr;
    }
    public void setAsr(boolean asr) {
        Asr = asr;
    }
    public boolean isIsEtkt() {
        return IsEtkt;
    }
    public void setIsEtkt(boolean isEtkt) {
        IsEtkt = isEtkt;
    }
    public boolean isIsRule() {
        return IsRule;
    }
    public void setIsRule(boolean isRule) {
        IsRule = isRule;
    }
    public String getLink() {
        return Link;
    }
    public void setLink(String link) {
        Link = link;
    }
    public boolean isMeal() {
        return Meal;
    }
    public void setMeal(boolean meal) {
        Meal = meal;
    }
    public String getMealCode() {
        return MealCode;
    }
    public void setMealCode(String mealCode) {
        MealCode = mealCode;
    }
    public String getPlaneStyle() {
        return PlaneStyle;
    }
    public void setPlaneStyle(String planeStyle) {
        PlaneStyle = planeStyle;
    }
    public boolean isIsCodeShare() {
        return IsCodeShare;
    }
    public void setIsCodeShare(boolean isCodeShare) {
        IsCodeShare = isCodeShare;
    }
    public String getCarrier() {
        return Carrier;
    }
    public void setCarrier(String carrier) {
        Carrier = carrier;
    }
    public double getLowestPrice() {
        return LowestPrice;
    }
    public void setLowestPrice(double lowestPrice) {
        LowestPrice = lowestPrice;
    }
    public double getAirportTax() {
        return AirportTax;
    }
    public void setAirportTax(double airportTax) {
        AirportTax = airportTax;
    }
    public double getFuelTax() {
        return FuelTax;
    }
    public void setFuelTax(double fuelTax) {
        FuelTax = fuelTax;
    }
    public double getOtherPrice() {
        return OtherPrice;
    }
    public void setOtherPrice(double otherPrice) {
        OtherPrice = otherPrice;
    }
    public String getCabinCodeInfo() {
        return CabinCodeInfo;
    }
    public void setCabinCodeInfo(String cabinCodeInfo) {
        CabinCodeInfo = cabinCodeInfo;
    }
    public String getRemark() {
        return Remark;
    }
    public void setRemark(String remark) {
        Remark = remark;
    }
    public String getStopCities() {
        return StopCities;
    }
    public void setStopCities(String stopCities) {
        StopCities = stopCities;
    }

}
