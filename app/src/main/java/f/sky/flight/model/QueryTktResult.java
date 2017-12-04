package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/25/025.
 */

public class QueryTktResult  extends ServerResObj {
    private String OrgCity;
    private String DstCity;
    private double LowestPrice;
    private String SysMsg;
    private int Distance;
    private List<SegmentObj> flights = new ArrayList<SegmentObj>();
    private List<SegmentObj> roundFlights = new ArrayList<SegmentObj>();
    public String getOrgCity() {
        return OrgCity;
    }
    public void setOrgCity(String orgCity) {
        OrgCity = orgCity;
    }
    public String getDstCity() {
        return DstCity;
    }
    public void setDstCity(String dstCity) {
        DstCity = dstCity;
    }
    public double getLowestPrice() {
        return LowestPrice;
    }
    public void setLowestPrice(double lowestPrice) {
        LowestPrice = lowestPrice;
    }
    public String getSysMsg() {
        return SysMsg;
    }
    public void setSysMsg(String sysMsg) {
        SysMsg = sysMsg;
    }
    public int getDistance() {
        return Distance;
    }
    public void setDistance(int distance) {
        Distance = distance;
    }
    public List<SegmentObj> getFlights() {
        return flights;
    }
    public void setFlights(List<SegmentObj> flights) {
        this.flights = flights;
    }
    public List<SegmentObj> getRoundFlights() {
        return roundFlights;
    }
    public void setRoundFlights(List<SegmentObj> roundFlights) {
        this.roundFlights = roundFlights;
    }

    public void addOneFlight(SegmentObj f){
        flights.add(f);
    }

    public void addOneRoundFlight(SegmentObj f){
        roundFlights.add(f);
    }
}
