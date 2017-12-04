package f.sky.flight.model;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class SkyWayObj{


        private int SkyWayID;
        private int Status;
        private String SameDayHint;//
        private String LowestHint;
        private String BrokeRoles;
        private String BrokeRolesReason;
        private double StandardPrice;
        private String StandardCabin;
        private int SkyWayGroupID;
        private int OrderID;
        private int GroupSerial;
        private int SkyWayType;
        private int SkyWayStatus;
        private String MainPnrNo;//
        private String OrinPnrNo;
        private String FlightNo;
        private String Carrier;
        private String OrgCity;
        private String DstCity;
        private String Cabin;
        private String CabinDesc;
        private String TakeOffDate;
        private String TakeOffTime;
        private String LandDate;
        private String LandTime;
        private int PricePolicyID;
        private double YPrice;
        private double Price;
        private double FuelTax;
        private double Tax;
        private double OtherPrice;
        private String Remark;
        private String Field1;
        private String Field2;
        private String Field3;
        private String OrgTeminal;
        private String DstTeminal;
        private int ReasonCodeID;
        private String ReasonCode;
        private String ReasonCodeDesc;
        private double LowestPrice;
        private String LowestCabin;
        private String LowestCabinDesc;
        private String NonRer;
        private String RerNotes;
        private String EnRerNotes;
        private String NonRef;
        private String AgreementCode;
        private String AgreementID;
        private String DisCountCode;
        private String RefNotes;
        private String EnRefNotes;
        private String NonEnd;
        private String EndNotes;
        private String CarrierFlight;
        private boolean IsCodeShare;
        private String PlaneStyle;
        private boolean AgreementCabin;

        private String AirLineName;
        private String orgCityname;
        private String dstCityname;
        private String maxCabinCount;
        private int distance;

        public int getDistance() {
            return distance;
        }
        public void setDistance(int distance) {
            this.distance = distance;
        }
        public String getAgreementID() {
            return AgreementID;
        }
        public void setAgreementID(String agreementID) {
            AgreementID = agreementID;
        }
        public String getMaxCabinCount() {
            return maxCabinCount;
        }
        public void setMaxCabinCount(String maxCabinCount) {
            this.maxCabinCount = maxCabinCount;
        }
        public boolean isAgreementCabin() {
            return AgreementCabin;
        }
        public void setAgreementCabin(boolean agreementCabin) {
            AgreementCabin = agreementCabin;
        }
        public String getOrgCityname() {
            return orgCityname;
        }
        public void setOrgCityname(String orgCityname) {
            this.orgCityname = orgCityname;
        }
        public String getDstCityname() {
            return dstCityname;
        }
        public void setDstCityname(String dstCityname) {
            this.dstCityname = dstCityname;
        }
        public String getAirLineName() {
            return AirLineName;
        }
        public void setAirLineName(String airLineName) {
            AirLineName = airLineName;
        }
        //根据舱位的是否违反政策填写违反政策原因
        private boolean isRule;

        public boolean isRule() {
            return isRule;
        }
        public void setRule(boolean isRule) {
            this.isRule = isRule;
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
        public String getCarrierFlight() {
            return CarrierFlight;
        }
        public void setCarrierFlight(String carrierFlight) {
            CarrierFlight = carrierFlight;
        }
        public String getAgreementCode() {
            return AgreementCode;
        }
        public void setAgreementCode(String agreementCode) {
            AgreementCode = agreementCode;
        }
        public String getDisCountCode() {
            return DisCountCode;
        }
        public void setDisCountCode(String disCountCode) {
            DisCountCode = disCountCode;
        }
        public int getSkyWayID() {
            return SkyWayID;
        }
        public void setSkyWayID(int skyWayID) {
            SkyWayID = skyWayID;
        }
        public int getStatus() {
            return Status;
        }
        public void setStatus(int status) {
            Status = status;
        }
        public String getSameDayHint() {
            return SameDayHint;
        }
        public void setSameDayHint(String sameDayHint) {
            SameDayHint = sameDayHint;
        }
        public String getLowestHint() {
            return LowestHint;
        }
        public void setLowestHint(String lowestHint) {
            LowestHint = lowestHint;
        }
        public String getBrokeRoles() {
            return BrokeRoles;
        }
        public void setBrokeRoles(String brokeRoles) {
            BrokeRoles = brokeRoles;
        }
        public String getBrokeRolesReason() {
            return BrokeRolesReason;
        }
        public void setBrokeRolesReason(String brokeRolesReason) {
            BrokeRolesReason = brokeRolesReason;
        }
        public double getStandardPrice() {
            return StandardPrice;
        }
        public void setStandardPrice(double standardPrice) {
            StandardPrice = standardPrice;
        }
        public String getStandardCabin() {
            return StandardCabin;
        }
        public void setStandardCabin(String standardCabin) {
            StandardCabin = standardCabin;
        }
        public int getSkyWayGroupID() {
            return SkyWayGroupID;
        }
        public void setSkyWayGroupID(int skyWayGroupID) {
            SkyWayGroupID = skyWayGroupID;
        }
        public int getOrderID() {
            return OrderID;
        }
        public void setOrderID(int orderID) {
            OrderID = orderID;
        }
        public int getGroupSerial() {
            return GroupSerial;
        }
        public void setGroupSerial(int groupSerial) {
            GroupSerial = groupSerial;
        }
        public int getSkyWayType() {
            return SkyWayType;
        }
        public void setSkyWayType(int skyWayType) {
            SkyWayType = skyWayType;
        }
        public int getSkyWayStatus() {
            return SkyWayStatus;
        }
        public void setSkyWayStatus(int skyWayStatus) {
            SkyWayStatus = skyWayStatus;
        }
        public String getMainPnrNo() {
            return MainPnrNo;
        }
        public void setMainPnrNo(String mainPnrNo) {
            MainPnrNo = mainPnrNo;
        }
        public String getOrinPnrNo() {
            return OrinPnrNo;
        }
        public void setOrinPnrNo(String orinPnrNo) {
            OrinPnrNo = orinPnrNo;
        }
        public String getFlightNo() {
            return FlightNo;
        }
        public void setFlightNo(String flightNo) {
            FlightNo = flightNo;
        }
        public String getCarrier() {
            return Carrier;
        }
        public void setCarrier(String carrier) {
            Carrier = carrier;
        }
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
        public String getCabin() {
            return Cabin;
        }
        public void setCabin(String cabin) {
            Cabin = cabin;
        }
        public String getCabinDesc() {
            return CabinDesc;
        }
        public void setCabinDesc(String cabinDesc) {
            CabinDesc = cabinDesc;
        }
        public String getTakeOffDate() {
            return TakeOffDate;
        }
        public void setTakeOffDate(String takeOffDate) {
            TakeOffDate = takeOffDate;
        }
        public String getTakeOffTime() {
            return TakeOffTime;
        }
        public void setTakeOffTime(String takeOffTime) {
            TakeOffTime = takeOffTime;
        }
        public String getLandDate() {
            return LandDate;
        }
        public void setLandDate(String landDate) {
            LandDate = landDate;
        }
        public String getLandTime() {
            return LandTime;
        }
        public void setLandTime(String landTime) {
            LandTime = landTime;
        }
        public int getPricePolicyID() {
            return PricePolicyID;
        }
        public void setPricePolicyID(int pricePolicyID) {
            PricePolicyID = pricePolicyID;
        }
        public double getYPrice() {
            return YPrice;
        }
        public void setYPrice(double yPrice) {
            YPrice = yPrice;
        }
        public double getPrice() {
            return Price;
        }
        public void setPrice(double price) {
            Price = price;
        }
        public double getFuelTax() {
            return FuelTax;
        }
        public void setFuelTax(double fuelTax) {
            FuelTax = fuelTax;
        }
        public double getTax() {
            return Tax;
        }
        public void setTax(double tax) {
            Tax = tax;
        }
        public double getOtherPrice() {
            return OtherPrice;
        }
        public void setOtherPrice(double otherPrice) {
            OtherPrice = otherPrice;
        }
        public String getRemark() {
            return Remark;
        }
        public void setRemark(String remark) {
            Remark = remark;
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
        public String getOrgTeminal() {
            return OrgTeminal;
        }
        public void setOrgTeminal(String orgTeminal) {
            OrgTeminal = orgTeminal;
        }
        public String getDstTeminal() {
            return DstTeminal;
        }
        public void setDstTeminal(String dstTeminal) {
            DstTeminal = dstTeminal;
        }
        public int getReasonCodeID() {
            return ReasonCodeID;
        }
        public void setReasonCodeID(int reasonCodeID) {
            ReasonCodeID = reasonCodeID;
        }
        public String getReasonCode() {
            return ReasonCode;
        }
        public void setReasonCode(String reasonCode) {
            ReasonCode = reasonCode;
        }
        public String getReasonCodeDesc() {
            return ReasonCodeDesc;
        }
        public void setReasonCodeDesc(String reasonCodeDesc) {
            ReasonCodeDesc = reasonCodeDesc;
        }
        public double getLowestPrice() {
            return LowestPrice;
        }
        public void setLowestPrice(double lowestPrice) {
            LowestPrice = lowestPrice;
        }
        public String getLowestCabin() {
            return LowestCabin;
        }
        public void setLowestCabin(String lowestCabin) {
            LowestCabin = lowestCabin;
        }
        public String getLowestCabinDesc() {
            return LowestCabinDesc;
        }
        public void setLowestCabinDesc(String lowestCabinDesc) {
            LowestCabinDesc = lowestCabinDesc;
        }
        public String getNonRer() {
            return NonRer;
        }
        public void setNonRer(String nonRer) {
            NonRer = nonRer;
        }
        public String getRerNotes() {
            return RerNotes;
        }
        public void setRerNotes(String rerNotes) {
            RerNotes = rerNotes;
        }
        public String getNonRef() {
            return NonRef;
        }
        public void setNonRef(String nonRef) {
            NonRef = nonRef;
        }
        public String getRefNotes() {
            return RefNotes;
        }
        public void setRefNotes(String refNotes) {
            RefNotes = refNotes;
        }
        public String getNonEnd() {
            return NonEnd;
        }
        public void setNonEnd(String nonEnd) {
            NonEnd = nonEnd;
        }
        public String getEndNotes() {
            return EndNotes;
        }
        public void setEndNotes(String endNotes) {
            EndNotes = endNotes;
        }
        public String getEnRerNotes() {
            return EnRerNotes;
        }
        public void setEnRerNotes(String enRerNotes) {
            EnRerNotes = enRerNotes;
        }
        public String getEnRefNotes() {
            return EnRefNotes;
        }
        public void setEnRefNotes(String enRefNotes) {
            EnRefNotes = enRefNotes;
        }

    @Override
    public String toString() {
        return "SkyWayObj{" +
                "SkyWayID=" + SkyWayID +
                ", Status=" + Status +
                ", SameDayHint='" + SameDayHint + '\'' +
                ", LowestHint='" + LowestHint + '\'' +
                ", BrokeRoles='" + BrokeRoles + '\'' +
                ", BrokeRolesReason='" + BrokeRolesReason + '\'' +
                ", StandardPrice=" + StandardPrice +
                ", StandardCabin='" + StandardCabin + '\'' +
                ", SkyWayGroupID=" + SkyWayGroupID +
                ", OrderID=" + OrderID +
                ", GroupSerial=" + GroupSerial +
                ", SkyWayType=" + SkyWayType +
                ", SkyWayStatus=" + SkyWayStatus +
                ", MainPnrNo='" + MainPnrNo + '\'' +
                ", OrinPnrNo='" + OrinPnrNo + '\'' +
                ", FlightNo='" + FlightNo + '\'' +
                ", Carrier='" + Carrier + '\'' +
                ", OrgCity='" + OrgCity + '\'' +
                ", DstCity='" + DstCity + '\'' +
                ", Cabin='" + Cabin + '\'' +
                ", CabinDesc='" + CabinDesc + '\'' +
                ", TakeOffDate='" + TakeOffDate + '\'' +
                ", TakeOffTime='" + TakeOffTime + '\'' +
                ", LandDate='" + LandDate + '\'' +
                ", LandTime='" + LandTime + '\'' +
                ", PricePolicyID=" + PricePolicyID +
                ", YPrice=" + YPrice +
                ", Price=" + Price +
                ", FuelTax=" + FuelTax +
                ", Tax=" + Tax +
                ", OtherPrice=" + OtherPrice +
                ", Remark='" + Remark + '\'' +
                ", Field1='" + Field1 + '\'' +
                ", Field2='" + Field2 + '\'' +
                ", Field3='" + Field3 + '\'' +
                ", OrgTeminal='" + OrgTeminal + '\'' +
                ", DstTeminal='" + DstTeminal + '\'' +
                ", ReasonCodeID=" + ReasonCodeID +
                ", ReasonCode='" + ReasonCode + '\'' +
                ", ReasonCodeDesc='" + ReasonCodeDesc + '\'' +
                ", LowestPrice=" + LowestPrice +
                ", LowestCabin='" + LowestCabin + '\'' +
                ", LowestCabinDesc='" + LowestCabinDesc + '\'' +
                ", NonRer='" + NonRer + '\'' +
                ", RerNotes='" + RerNotes + '\'' +
                ", EnRerNotes='" + EnRerNotes + '\'' +
                ", NonRef='" + NonRef + '\'' +
                ", AgreementCode='" + AgreementCode + '\'' +
                ", AgreementID='" + AgreementID + '\'' +
                ", DisCountCode='" + DisCountCode + '\'' +
                ", RefNotes='" + RefNotes + '\'' +
                ", EnRefNotes='" + EnRefNotes + '\'' +
                ", NonEnd='" + NonEnd + '\'' +
                ", EndNotes='" + EndNotes + '\'' +
                ", CarrierFlight='" + CarrierFlight + '\'' +
                ", IsCodeShare=" + IsCodeShare +
                ", PlaneStyle='" + PlaneStyle + '\'' +
                ", AgreementCabin=" + AgreementCabin +
                ", AirLineName='" + AirLineName + '\'' +
                ", orgCityname='" + orgCityname + '\'' +
                ", dstCityname='" + dstCityname + '\'' +
                ", maxCabinCount='" + maxCabinCount + '\'' +
                ", distance=" + distance +
                ", isRule=" + isRule +
                '}';
    }
}
