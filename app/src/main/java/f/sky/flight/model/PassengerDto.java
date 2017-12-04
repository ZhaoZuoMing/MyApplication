package f.sky.flight.model;

import java.util.List;

/**
 * Created by zhaody on 2017/11/27.
 * 订单旅客
 */

public class PassengerDto {

    private Credentials Credentials;
    private Cabin FlightCabin;
    private FlightBookSegment FlightSegment;
    private String CostCenterCode;
    private String CostCenterName;
    private String OrganizationName;
    private  String OrganizationCode;
    private String IllegalPolicy;//违反差旅政策内容
    private String  IllegalReason;
    private int TravelType;
    private int TravelPayType;
    private String Mobile;
    private String Email;
    private String OutNumbers;
    private String OutNumberNames;
    private long ApprovalId;

    public Credentials getCredentials() {
        return Credentials;
    }

    public void setCredentials(Credentials credentials) {
        Credentials = credentials;
    }

    public Cabin getFlightCabin() {
        return FlightCabin;
    }

    public void setFlightCabin(Cabin flightCabin) {
        FlightCabin = flightCabin;
    }

    public FlightBookSegment getFlightSegment() {
        return FlightSegment;
    }

    public void setFlightSegment(FlightBookSegment flightSegment) {
        FlightSegment = flightSegment;
    }

    public String getCostCenterCode() {
        return CostCenterCode;
    }

    public void setCostCenterCode(String costCenterCode) {
        CostCenterCode = costCenterCode;
    }

    public String getCostCenterName() {
        return CostCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        CostCenterName = costCenterName;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getOrganizationCode() {
        return OrganizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        OrganizationCode = organizationCode;
    }

    public String getIllegalPolicy() {
        return IllegalPolicy;
    }

    public void setIllegalPolicy(String illegalPolicy) {
        IllegalPolicy = illegalPolicy;
    }

    public String getIllegalReason() {
        return IllegalReason;
    }

    public void setIllegalReason(String illegalReason) {
        IllegalReason = illegalReason;
    }

    public int getTravelType() {
        return TravelType;
    }

    public void setTravelType(int travelType) {
        TravelType = travelType;
    }

    public int getTravelPayType() {
        return TravelPayType;
    }

    public void setTravelPayType(int travelPayType) {
        TravelPayType = travelPayType;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOutNumbers() {
        return OutNumbers;
    }

    public void setOutNumbers(String outNumbers) {
        OutNumbers = outNumbers;
    }

    public String getOutNumberNames() {
        return OutNumberNames;
    }

    public void setOutNumberNames(String outNumberNames) {
        OutNumberNames = outNumberNames;
    }

    public long getApprovalId() {
        return ApprovalId;
    }

    public void setApprovalId(long approvalId) {
        ApprovalId = approvalId;
    }
}
