package f.sky.flight.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhaody on 2017/11/14.
 * 航班
 */
public class FlightSegment extends ServerResObj{
    private String FromCity;
    private String ToCity;
    private BigDecimal LowestFare;
    private BigDecimal LowestDiscount;
    private BigDecimal Tax;
    private BigDecimal YFare;
    private BigDecimal CFare;
    private BigDecimal FFare;
    private String LowestCabinCode;
    private String Number;
    private String Airline;
    private String AirlineName;
    private String PlaneType;
    private String PlaneTypeDescribe;
    private String CodeShareNumber;
    private String Carrier;
    private String CarrierName;
    private String FromAirport;
    private String ToAirport;
    private String FromAirportName;
    private String FromCityName;
    private String ToAirportName;
    private String ToCityName;
    private String TakeoffTime;
    private String ArrivalTime;
    private String FromTerminal;
    private String ToTerminal;
    private List<StopCities> StopCities;
    private int Distance;
    private int FlyTime;
    private String FlyTimeName;
    private Cabin cabin;
    private List<Cabin> cabins;
    private Variables variables;

    public void setCabin(Cabin cabin) {
        this.cabin = cabin;
    }

    public Cabin getCabin() {
        return cabin;
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

    public BigDecimal getTax() {
        return Tax;
    }

    public void setTax(BigDecimal tax) {
        Tax = tax;
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

    public String getLowestCabinCode() {
        return LowestCabinCode;
    }

    public void setLowestCabinCode(String lowestCabinCode) {
        LowestCabinCode = lowestCabinCode;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

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

    public String getPlaneType() {
        return PlaneType;
    }

    public void setPlaneType(String planeType) {
        PlaneType = planeType;
    }

    public String getPlaneTypeDescribe() {
        return PlaneTypeDescribe;
    }

    public void setPlaneTypeDescribe(String planeTypeDescribe) {
        PlaneTypeDescribe = planeTypeDescribe;
    }

    public String getCodeShareNumber() {
        return CodeShareNumber;
    }

    public void setCodeShareNumber(String codeShareNumber) {
        CodeShareNumber = codeShareNumber;
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

    public String getFromAirport() {
        return FromAirport;
    }

    public void setFromAirport(String fromAirport) {
        FromAirport = fromAirport;
    }

    public String getToAirport() {
        return ToAirport;
    }

    public void setToAirport(String toAirport) {
        ToAirport = toAirport;
    }

    public String getFromAirportName() {
        return FromAirportName;
    }

    public void setFromAirportName(String fromAirportName) {
        FromAirportName = fromAirportName;
    }

    public String getFromCityName() {
        return FromCityName;
    }

    public void setFromCityName(String fromCityName) {
        FromCityName = fromCityName;
    }

    public String getToAirportName() {
        return ToAirportName;
    }

    public void setToAirportName(String toAirportName) {
        ToAirportName = toAirportName;
    }

    public String getToCityName() {
        return ToCityName;
    }

    public void setToCityName(String toCityName) {
        ToCityName = toCityName;
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

    public String getFromTerminal() {
        return FromTerminal;
    }

    public void setFromTerminal(String fromTerminal) {
        FromTerminal = fromTerminal;
    }

    public String getToTerminal() {
        return ToTerminal;
    }

    public void setToTerminal(String toTerminal) {
        ToTerminal = toTerminal;
    }

    public List<StopCities> getStopCities() {
        return StopCities;
    }

    public void setStopCities(List<StopCities> stopCities) {
        StopCities = stopCities;
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

    public List<Cabin> getCabins() {
        return cabins;
    }

    public void setCabins(List<Cabin> cabins) {
        this.cabins = cabins;
    }

    public Variables getVariables() {
        return variables;
    }

    public void setVariables(Variables variables) {
        this.variables = variables;
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

}
