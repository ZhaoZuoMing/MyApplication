package f.sky.flight.model;

/**
 * Created by Administrator on 2016/11/24/024.
 * 乘机人信息类
 */

public class B_TouristDOObj {


    private int TouristID;
    private int OrderID;
    private int Hits;
    private int UserID;
    private String TouristName;
    private int TouristType;
    private String MobileNo;
    private String Address;
    private String NationalCode;
    private int PassengerID;
    private int IDType;
    private String IDNumber;
    private int FreeFlag;
    private double Price;
    private double FuelTax;
    private double Tax;
    private double OtherPrice;
    private String TktNos;
    private String Remark;
    private String Field1;
    private String Field2;//昵称,如果跟TouristName相同就不显示
    private String Field3;
    private String Birthday;
    private int gender;
    private String NationalityName;
    private String CardValid;
    private double InsuranceAmount;
    private int Status;

    public B_TouristDOObj(){}

    public int getTouristID() {
        return TouristID;
    }
    public void setTouristID(int touristID) {
        TouristID = touristID;
    }
    public int getOrderID() {
        return OrderID;
    }
    public void setOrderID(int orderID) {
        OrderID = orderID;
    }
    public int getHits() {
        return Hits;
    }
    public void setHits(int hits) {
        Hits = hits;
    }
    public int getUserID() {
        return UserID;
    }
    public void setUserID(int userID) {
        UserID = userID;
    }
    public String getTouristName() {
        return TouristName;
    }
    public void setTouristName(String touristName) {
        TouristName = touristName;
    }
    public int getTouristType() {
        return TouristType;
    }
    public void setTouristType(int touristType) {
        TouristType = touristType;
    }
    public String getMobileNo() {
        return MobileNo;
    }
    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public String getNationalCode() {
        return NationalCode;
    }
    public void setNationalCode(String nationalCode) {
        NationalCode = nationalCode;
    }
    public int getPassengerID() {
        return PassengerID;
    }
    public void setPassengerID(int passengerID) {
        PassengerID = passengerID;
    }
    public int getIDType() {
        return IDType;
    }
    public void setIDType(int iDType) {
        IDType = iDType;
    }
    public String getIDNumber() {
        return IDNumber;
    }
    public void setIDNumber(String iDNumber) {
        IDNumber = iDNumber;
    }
    public int getFreeFlag() {
        return FreeFlag;
    }
    public void setFreeFlag(int freeFlag) {
        FreeFlag = freeFlag;
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
    public String getTktNos() {
        return TktNos;
    }
    public void setTktNos(String tktNos) {
        TktNos = tktNos;
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
    public String getBirthday() {
        return Birthday;
    }
    public void setBirthday(String birthday) {
        Birthday = birthday;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getNationalityName() {
        return NationalityName;
    }
    public void setNationalityName(String nationalityName) {
        NationalityName = nationalityName;
    }
    public String getCardValid() {
        return CardValid;
    }
    public void setCardValid(String cardValid) {
        CardValid = cardValid;
    }
    public double getInsuranceAmount() {
        return InsuranceAmount;
    }
    public void setInsuranceAmount(double insuranceAmount) {
        InsuranceAmount = insuranceAmount;
    }
    public int getStatus() {
        return Status;
    }
    public void setStatus(int status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "B_TouristDOObj{" +
                "TouristID=" + TouristID +
                ", OrderID=" + OrderID +
                ", Hits=" + Hits +
                ", UserID=" + UserID +
                ", TouristName='" + TouristName + '\'' +
                ", TouristType=" + TouristType +
                ", MobileNo='" + MobileNo + '\'' +
                ", Address='" + Address + '\'' +
                ", NationalCode='" + NationalCode + '\'' +
                ", PassengerID=" + PassengerID +
                ", IDType=" + IDType +
                ", IDNumber='" + IDNumber + '\'' +
                ", FreeFlag=" + FreeFlag +
                ", Price=" + Price +
                ", FuelTax=" + FuelTax +
                ", Tax=" + Tax +
                ", OtherPrice=" + OtherPrice +
                ", TktNos='" + TktNos + '\'' +
                ", Remark='" + Remark + '\'' +
                ", Field1='" + Field1 + '\'' +
                ", Field2='" + Field2 + '\'' +
                ", Field3='" + Field3 + '\'' +
                ", Birthday='" + Birthday + '\'' +
                ", gender=" + gender +
                ", NationalityName='" + NationalityName + '\'' +
                ", CardValid='" + CardValid + '\'' +
                ", InsuranceAmount=" + InsuranceAmount +
                ", Status=" + Status +
                '}';
    }
}
