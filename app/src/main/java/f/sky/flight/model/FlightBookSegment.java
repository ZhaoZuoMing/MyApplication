package f.sky.flight.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhaody on 2017/11/27.
 * 预订航段
 */

public class FlightBookSegment {
    private String Airline;
    private String AirlineName;
    private String Number;
    private String Carrier;
    private String CarrierName;
    private String CodeShareNumber;
    private String FromCity;
    private String FromCityName;
    private String FromAirport;
    private String FromAirportName;
    private String FromTerminal;
    private String ToCity;
    private String ToCityName;
    private String ToAirport;
    private String ToAirportName;
    private String ToTerminal;
    private String TakeoffTime;
    private String ArrivalTime;
    private String PlaneType;
    private String PlaneTypeName;
    private int Distance;
    private  int FlyTime;
    private String FlyTimeName;
    private List<StopCities> StopCities;
    private BigDecimal YFare;
    private BigDecimal CFare;
    private BigDecimal FFare;
    private BigDecimal Tax;
    private BigDecimal LowestFare;
    private BigDecimal LowestDiscount;
    private String  LowestCabinCode;//最低仓位代码
    private VariableDto Variables;

    public String getAirline() {
        return Airline;
    }

    public void setAirline(String airline) {
        Airline = airline;
    }

    public String getAirlineName() {
        return AirlineName;
    }

    public void setAirlineName(String airlineName) {
        AirlineName = airlineName;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getCarrier() {
        return Carrier;
    }

    public void setCarrier(String carrier) {
        Carrier = carrier;
    }

    public String getCarrierName() {
        return CarrierName;
    }

    public void setCarrierName(String carrierName) {
        CarrierName = carrierName;
    }

    public String getCodeShareNumber() {
        return CodeShareNumber;
    }

    public void setCodeShareNumber(String codeShareNumber) {
        CodeShareNumber = codeShareNumber;
    }

    public String getFromCity() {
        return FromCity;
    }

    public void setFromCity(String fromCity) {
        FromCity = fromCity;
    }

    public String getFromCityName() {
        return FromCityName;
    }

    public void setFromCityName(String fromCityName) {
        FromCityName = fromCityName;
    }

    public String getFromAirport() {
        return FromAirport;
    }

    public void setFromAirport(String fromAirport) {
        FromAirport = fromAirport;
    }

    public String getFromAirportName() {
        return FromAirportName;
    }

    public void setFromAirportName(String fromAirportName) {
        FromAirportName = fromAirportName;
    }

    public String getFromTerminal() {
        return FromTerminal;
    }

    public void setFromTerminal(String fromTerminal) {
        FromTerminal = fromTerminal;
    }

    public String getToCity() {
        return ToCity;
    }

    public void setToCity(String toCity) {
        ToCity = toCity;
    }

    public String getToCityName() {
        return ToCityName;
    }

    public void setToCityName(String toCityName) {
        ToCityName = toCityName;
    }

    public String getToAirport() {
        return ToAirport;
    }

    public void setToAirport(String toAirport) {
        ToAirport = toAirport;
    }

    public String getToAirportName() {
        return ToAirportName;
    }

    public void setToAirportName(String toAirportName) {
        ToAirportName = toAirportName;
    }

    public String getToTerminal() {
        return ToTerminal;
    }

    public void setToTerminal(String toTerminal) {
        ToTerminal = toTerminal;
    }

    public String getTakeoffTime() {
        return TakeoffTime;
    }

    public void setTakeoffTime(String takeoffTime) {
        TakeoffTime = takeoffTime;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getPlaneType() {
        return PlaneType;
    }

    public void setPlaneType(String planeType) {
        PlaneType = planeType;
    }

    public String getPlaneTypeName() {
        return PlaneTypeName;
    }

    public void setPlaneTypeName(String planeTypeName) {
        PlaneTypeName = planeTypeName;
    }

    public int getDistance() {
        return Distance;
    }

    public void setDistance(int distance) {
        Distance = distance;
    }

    public int getFlyTime() {
        return FlyTime;
    }

    public void setFlyTime(int flyTime) {
        FlyTime = flyTime;
    }

    public String getFlyTimeName() {
        return FlyTimeName;
    }

    public void setFlyTimeName(String flyTimeName) {
        FlyTimeName = flyTimeName;
    }

    public List<f.sky.flight.model.StopCities> getStopCities() {
        return StopCities;
    }

    public void setStopCities(List<f.sky.flight.model.StopCities> stopCities) {
        StopCities = stopCities;
    }

    public BigDecimal getYFare() {
        return YFare;
    }

    public void setYFare(BigDecimal YFare) {
        this.YFare = YFare;
    }

    public BigDecimal getCFare() {
        return CFare;
    }

    public void setCFare(BigDecimal CFare) {
        this.CFare = CFare;
    }

    public BigDecimal getFFare() {
        return FFare;
    }

    public void setFFare(BigDecimal FFare) {
        this.FFare = FFare;
    }

    public BigDecimal getTax() {
        return Tax;
    }

    public void setTax(BigDecimal tax) {
        Tax = tax;
    }

    public BigDecimal getLowestFare() {
        return LowestFare;
    }

    public void setLowestFare(BigDecimal lowestFare) {
        LowestFare = lowestFare;
    }

    public BigDecimal getLowestDiscount() {
        return LowestDiscount;
    }

    public void setLowestDiscount(BigDecimal lowestDiscount) {
        LowestDiscount = lowestDiscount;
    }

    public String getLowestCabinCode() {
        return LowestCabinCode;
    }

    public void setLowestCabinCode(String lowestCabinCode) {
        LowestCabinCode = lowestCabinCode;
    }

    public VariableDto getVariables() {
        return Variables;
    }

    public void setVariables(VariableDto variables) {
        Variables = variables;
    }
}
