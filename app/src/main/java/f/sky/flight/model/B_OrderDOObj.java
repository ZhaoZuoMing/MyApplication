package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class B_OrderDOObj {


    private int TotalCount;//<TotalCount>int</TotalCount>
    private int ID;// <ID>int</ID>
    private int ApplyID;// <ApplyID>int</ApplyID>
    private int ApplyStatus;// <ApplyStatus>int</ApplyStatus>
    private int OrderID;// <OrderID>int</OrderID>
    private int OrderStatus;// <OrderStatus>int</OrderStatus>
    private int ClientID;// <ClientID>int</ClientID>
    private String PnrNo;// <PnrNo>string</PnrNo>
    private int EtdzID;// <EtdzID>int</EtdzID>
    private String OfficeCode;// <OfficeCode>string</OfficeCode>
    private int ClientPolicyID;// <ClientPolicyID>int</ClientPolicyID>
    private int AirlinePolicyID;// <AirlinePolicyID>int</AirlinePolicyID>
    private String LinkMan;// <LinkMan>string</LinkMan>
    private String LinkTel;// <LinkTel>string</LinkTel>
    private String CostCenter;// <CostCenter>string</CostCenter>
    private String CostNumber;// <CostNumber>string</CostNumber>
    private int CorpID;// <CorpID>int</CorpID>
    private int DeptID;// <DeptID>int</DeptID>
    private int BookID;// <BookID>int</BookID>
    private String BookDate;// <BookDate>string</BookDate>
    private int OrderType;// <OrderType>int</OrderType>
    private int OrderSource;// <OrderSource>int</OrderSource>
    private int SelfTake;// <SelfTake>int</SelfTake>
    private String DeliverAddr;// <DeliverAddr>string</DeliverAddr>
    private int GroupFlag;// <GroupFlag>int</GroupFlag>
    private int PassengerCount;// <PassengerCount>int</PassengerCount>
    private int PayType;// <PayType>int</PayType>
    private int CashStatus;// <CashStatus>int</CashStatus>
    private double TotalPrice;// <TotalPrice>decimal</TotalPrice>
    private double FuelTax;// <FuelTax>decimal</FuelTax>
    private double OrgTotalPrice;// <OrgTotalPrice>decimal</OrgTotalPrice>
    private double Tax;// <Tax>decimal</Tax>
    private double OtherPrice;// <OtherPrice>decimal</OtherPrice>
    private String TktNos;// <TktNos>string</TktNos>
    private String ExternalNo;// <ExternalNo>string</ExternalNo>
    private String Remark;// <Remark>string</Remark>
    private String Field1;// <Field1>string</Field1>
    private String Field2;// <Field2>string</Field2>
    private String Field3;// <Field3>string</Field3>
    private int ReasonCodeID;// <ReasonCodeID>int</ReasonCodeID>
    private String ReasonCode;// <ReasonCode>string</ReasonCode>
    private String ReasonCodeDesc;// <ReasonCodeDesc>string</ReasonCodeDesc>
    private int CategoryID;// <CategoryID>int</CategoryID>
    private String CostID;// <CostID>string</CostID>
    private String SchemeID;// <SchemeID>string</SchemeID>
    private int SmsStatus;// <SmsStatus>int</SmsStatus>
    private String LinkEmail;// <LinkEmail>string</LinkEmail>
    private String City;// <City>string</City>
    private int PayID;// <PayID>int</PayID>
    private int TripStatus;// <TripStatus>int</TripStatus>
    // <Tourist xsi:nil="true" />
    // <SkyWay xsi:nil="true" />
    // <DeletedSkyWay xsi:nil="true" />
    private int ObeyRole;// <ObeyRole>int</ObeyRole>
    private String LowestHint;// <LowestHint>string</LowestHint>
    private String Field4;// <Field4>string</Field4>
    private String Field5;// <Field5>string</Field5>
    private String Field6;// <Field6>string</Field6>
    private String Field7;// <Field7>string</Field7>
    private String Field8;// <Field8>string</Field8>
    private String Field9;// <Field9>string</Field9>
    private String Field10;// <Field10>string</Field10>
    private String Tourists;// <Tourists>string</Tourists>
    private String FlightNos;// <FlightNos>string</FlightNos>
    private String Skyways;// <Skyways>string</Skyways>
    private String SkywayTakeOffDates;
    private String TakeOffDate;
    private String Auditor;//
    private int ClientPayType;

    public int getClientPayType() {
        return ClientPayType;
    }

    public void setClientPayType(int clientPayType) {
        ClientPayType = clientPayType;
    }

    public String getAuditor() {
        return Auditor;
    }

    public void setAuditor(String auditor) {
        Auditor = auditor;
    }

    private List<B_TouristDOObj> touristDOObjL = new ArrayList<B_TouristDOObj>(); ;
    private List<SkyWayObj> skyWayObjL = new ArrayList<SkyWayObj>();;
    private List<SkyWayObj> deletedSkyWayObjL = new ArrayList<SkyWayObj>();

    private String CostCenterName;

    public String getSkywayTakeOffDates() {
        return SkywayTakeOffDates;
    }

    public void setSkywayTakeOffDates(String skywayTakeOffDates) {
        SkywayTakeOffDates = skywayTakeOffDates;
    }

    public String getCostCenterName() {
        return CostCenterName;
    }

    public void setCostCenterName(String costCenterName) {
        CostCenterName = costCenterName;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public String getTakeOffDate() {
        return TakeOffDate;
    }

    public void setTakeOffDate(String takeOffDate) {
        TakeOffDate = takeOffDate;
    }
    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getApplyID() {
        return ApplyID;
    }

    public void setApplyID(int applyID) {
        ApplyID = applyID;
    }

    public int getApplyStatus() {
        return ApplyStatus;
    }

    public void setApplyStatus(int applyStatus) {
        ApplyStatus = applyStatus;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int orderID) {
        OrderID = orderID;
    }

    public int getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        OrderStatus = orderStatus;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }

    public String getPnrNo() {
        return PnrNo;
    }

    public void setPnrNo(String pnrNo) {
        PnrNo = pnrNo;
    }

    public int getEtdzID() {
        return EtdzID;
    }

    public void setEtdzID(int etdzID) {
        EtdzID = etdzID;
    }

    public String getOfficeCode() {
        return OfficeCode;
    }

    public void setOfficeCode(String officeCode) {
        OfficeCode = officeCode;
    }

    public int getClientPolicyID() {
        return ClientPolicyID;
    }

    public void setClientPolicyID(int clientPolicyID) {
        ClientPolicyID = clientPolicyID;
    }

    public int getAirlinePolicyID() {
        return AirlinePolicyID;
    }

    public void setAirlinePolicyID(int airlinePolicyID) {
        AirlinePolicyID = airlinePolicyID;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getLinkTel() {
        return LinkTel;
    }

    public void setLinkTel(String linkTel) {
        LinkTel = linkTel;
    }

    public String getCostCenter() {
        return CostCenter;
    }

    public void setCostCenter(String costCenter) {
        CostCenter = costCenter;
    }

    public String getCostNumber() {
        return CostNumber;
    }

    public void setCostNumber(String costNumber) {
        CostNumber = costNumber;
    }

    public int getCorpID() {
        return CorpID;
    }

    public void setCorpID(int corpID) {
        CorpID = corpID;
    }

    public int getDeptID() {
        return DeptID;
    }

    public void setDeptID(int deptID) {
        DeptID = deptID;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public String getBookDate() {
        return BookDate;
    }

    public void setBookDate(String bookDate) {
        BookDate = bookDate;
    }

    public int getOrderType() {
        return OrderType;
    }

    public void setOrderType(int orderType) {
        OrderType = orderType;
    }

    public int getOrderSource() {
        return OrderSource;
    }

    public void setOrderSource(int orderSource) {
        OrderSource = orderSource;
    }

    public int getSelfTake() {
        return SelfTake;
    }

    public void setSelfTake(int selfTake) {
        SelfTake = selfTake;
    }

    public String getDeliverAddr() {
        return DeliverAddr;
    }

    public void setDeliverAddr(String deliverAddr) {
        DeliverAddr = deliverAddr;
    }

    public int getGroupFlag() {
        return GroupFlag;
    }

    public void setGroupFlag(int groupFlag) {
        GroupFlag = groupFlag;
    }

    public int getPassengerCount() {
        return PassengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        PassengerCount = passengerCount;
    }

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int payType) {
        PayType = payType;
    }

    public int getCashStatus() {
        return CashStatus;
    }

    public void setCashStatus(int cashStatus) {
        CashStatus = cashStatus;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public double getFuelTax() {
        return FuelTax;
    }

    public void setFuelTax(double fuelTax) {
        FuelTax = fuelTax;
    }

    public double getOrgTotalPrice() {
        return OrgTotalPrice;
    }

    public void setOrgTotalPrice(double orgTotalPrice) {
        OrgTotalPrice = orgTotalPrice;
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

    public String getTktNos() {
        return TktNos;
    }

    public void setTktNos(String tktNos) {
        TktNos = tktNos;
    }

    public String getExternalNo() {
        return ExternalNo;
    }

    public void setExternalNo(String externalNo) {
        ExternalNo = externalNo;
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

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public String getCostID() {
        return CostID;
    }

    public void setCostID(String costID) {
        CostID = costID;
    }

    public String getSchemeID() {
        return SchemeID;
    }

    public void setSchemeID(String schemeID) {
        SchemeID = schemeID;
    }

    public int getSmsStatus() {
        return SmsStatus;
    }

    public void setSmsStatus(int smsStatus) {
        SmsStatus = smsStatus;
    }

    public String getLinkEmail() {
        return LinkEmail;
    }

    public void setLinkEmail(String linkEmail) {
        LinkEmail = linkEmail;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getPayID() {
        return PayID;
    }

    public void setPayID(int payID) {
        PayID = payID;
    }

    public int getTripStatus() {
        return TripStatus;
    }

    public void setTripStatus(int tripStatus) {
        TripStatus = tripStatus;
    }

    public int getObeyRole() {
        return ObeyRole;
    }

    public void setObeyRole(int obeyRole) {
        ObeyRole = obeyRole;
    }

    public String getLowestHint() {
        return LowestHint;
    }

    public void setLowestHint(String lowestHint) {
        LowestHint = lowestHint;
    }

    public String getField4() {
        return Field4;
    }

    public void setField4(String field4) {
        Field4 = field4;
    }

    public String getField5() {
        return Field5;
    }

    public void setField5(String field5) {
        Field5 = field5;
    }

    public String getField6() {
        return Field6;
    }

    public void setField6(String field6) {
        Field6 = field6;
    }

    public String getField7() {
        return Field7;
    }

    public void setField7(String field7) {
        Field7 = field7;
    }

    public String getField8() {
        return Field8;
    }

    public void setField8(String field8) {
        Field8 = field8;
    }

    public String getField9() {
        return Field9;
    }

    public void setField9(String field9) {
        Field9 = field9;
    }

    public String getField10() {
        return Field10;
    }

    public void setField10(String field10) {
        Field10 = field10;
    }

    public String getTourists() {
        return Tourists;
    }

    public void setTourists(String tourists) {
        Tourists = tourists;
    }

    public String getFlightNos() {
        return FlightNos;
    }

    public void setFlightNos(String flightNos) {
        FlightNos = flightNos;
    }

    public String getSkyways() {
        return Skyways;
    }

    public void setSkyways(String skyways) {
        Skyways = skyways;
    }

    public List<B_TouristDOObj> getTouristDOObjL() {
        return touristDOObjL;
    }

    public void setTouristDOObjL(List<B_TouristDOObj> touristDOObjL) {
        this.touristDOObjL = touristDOObjL;
    }

    @Override
    public String toString() {
        return "B_OrderDOObj{" +
                "TotalCount=" + TotalCount +
                ", ID=" + ID +
                ", ApplyID=" + ApplyID +
                ", ApplyStatus=" + ApplyStatus +
                ", OrderID=" + OrderID +
                ", OrderStatus=" + OrderStatus +
                ", ClientID=" + ClientID +
                ", PnrNo='" + PnrNo + '\'' +
                ", EtdzID=" + EtdzID +
                ", OfficeCode='" + OfficeCode + '\'' +
                ", ClientPolicyID=" + ClientPolicyID +
                ", AirlinePolicyID=" + AirlinePolicyID +
                ", LinkMan='" + LinkMan + '\'' +
                ", LinkTel='" + LinkTel + '\'' +
                ", CostCenter='" + CostCenter + '\'' +
                ", CostNumber='" + CostNumber + '\'' +
                ", CorpID=" + CorpID +
                ", DeptID=" + DeptID +
                ", BookID=" + BookID +
                ", BookDate='" + BookDate + '\'' +
                ", OrderType=" + OrderType +
                ", OrderSource=" + OrderSource +
                ", SelfTake=" + SelfTake +
                ", DeliverAddr='" + DeliverAddr + '\'' +
                ", GroupFlag=" + GroupFlag +
                ", PassengerCount=" + PassengerCount +
                ", PayType=" + PayType +
                ", CashStatus=" + CashStatus +
                ", TotalPrice=" + TotalPrice +
                ", FuelTax=" + FuelTax +
                ", OrgTotalPrice=" + OrgTotalPrice +
                ", Tax=" + Tax +
                ", OtherPrice=" + OtherPrice +
                ", TktNos='" + TktNos + '\'' +
                ", ExternalNo='" + ExternalNo + '\'' +
                ", Remark='" + Remark + '\'' +
                ", Field1='" + Field1 + '\'' +
                ", Field2='" + Field2 + '\'' +
                ", Field3='" + Field3 + '\'' +
                ", ReasonCodeID=" + ReasonCodeID +
                ", ReasonCode='" + ReasonCode + '\'' +
                ", ReasonCodeDesc='" + ReasonCodeDesc + '\'' +
                ", CategoryID=" + CategoryID +
                ", CostID='" + CostID + '\'' +
                ", SchemeID='" + SchemeID + '\'' +
                ", SmsStatus=" + SmsStatus +
                ", LinkEmail='" + LinkEmail + '\'' +
                ", City='" + City + '\'' +
                ", PayID=" + PayID +
                ", TripStatus=" + TripStatus +
                ", ObeyRole=" + ObeyRole +
                ", LowestHint='" + LowestHint + '\'' +
                ", Field4='" + Field4 + '\'' +
                ", Field5='" + Field5 + '\'' +
                ", Field6='" + Field6 + '\'' +
                ", Field7='" + Field7 + '\'' +
                ", Field8='" + Field8 + '\'' +
                ", Field9='" + Field9 + '\'' +
                ", Field10='" + Field10 + '\'' +
                ", Tourists='" + Tourists + '\'' +
                ", FlightNos='" + FlightNos + '\'' +
                ", Skyways='" + Skyways + '\'' +
                ", SkywayTakeOffDates='" + SkywayTakeOffDates + '\'' +
                ", TakeOffDate='" + TakeOffDate + '\'' +
                ", Auditor='" + Auditor + '\'' +
                ", ClientPayType=" + ClientPayType +
                ", touristDOObjL=" + touristDOObjL +
                ", skyWayObjL=" + skyWayObjL +
                ", deletedSkyWayObjL=" + deletedSkyWayObjL +
                ", CostCenterName='" + CostCenterName + '\'' +
                '}';
    }

    public List<SkyWayObj> getSkyWayObjL() {
        return skyWayObjL;
    }

    public void setSkyWayObjL(List<SkyWayObj> skyWayObjL) {
        this.skyWayObjL = skyWayObjL;
    }

    public List<SkyWayObj> getDeletedSkyWayObjL() {
        return deletedSkyWayObjL;
    }

    public void setDeletedSkyWayObjL(List<SkyWayObj> deletedSkyWayObjL) {
        this.deletedSkyWayObjL = deletedSkyWayObjL;
    }

//	public B_TouristDOObj getTouristDOObj() {
//		return touristDOObj;
//	}
//
//	public void setTouristDOObj(B_TouristDOObj touristDOObj) {
//		this.touristDOObj = touristDOObj;
//	}
//
//	public SkyWayObj getSkyWayObj() {
//		return skyWayObj;
//	}
//
//	public void setSkyWayObj(SkyWayObj skyWayObj) {
//		this.skyWayObj = skyWayObj;
//	}
//
//	public SkyWayObj getDeletedSkyWayObj() {
//		return deletedSkyWayObj;
//	}
//
//	public void setDeletedSkyWayObj(SkyWayObj deletedSkyWayObj) {
//		this.deletedSkyWayObj = deletedSkyWayObj;
//	}

}
