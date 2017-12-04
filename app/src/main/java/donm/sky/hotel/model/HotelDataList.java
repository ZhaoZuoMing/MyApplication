package donm.sky.hotel.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/29/029.
 */

public class HotelDataList implements Serializable{
/**
 * <DeptSerialNo />
 <Orderid>25571</Orderid>
 <OrderType>0</OrderType>
 <RelationOrderID>0</RelationOrderID>
 <RelationApplyID>0</RelationApplyID>
 <ApplyID>230639</ApplyID>
 <Hotelid>254153</Hotelid>
 <HotelCode>90934041</HotelCode>
 <HotelName>上海博漾精品酒店</HotelName>
 <HotelEName>Boyang Boutique Hotel</HotelEName>
 <BrandName />
 <PurchaseID>0</PurchaseID>
 <PurchaseCode />
 <InvoiceMode>Hotel</InvoiceMode>
 <HotelAddress>浦东新区拯安路67-69号</HotelAddress>
 <HotelEAddress>No. 67-69 Zheng'an Road, Pudong New District, Shanghai, China</HotelEAddress>
 <RoomTypeName>商务大床房</RoomTypeName>
 <RoomTypeEName>商务大床房</RoomTypeEName>
 <RatePlanCode>3128577</RatePlanCode>
 <Status>20</Status>
 */


    private HorderContact horderContact;
    private List<HorderGuest> horderGuest;
    private FkHorderfuReferenceHorder fkHorderfuReferenceHorder;
    private String CheckInDate;
    private String CheckOutDate;
    private String ArrivalTime;
    private String LaterArrivalTime;
    private String CreateTime;
    private String Breakfast;
    private String Net;
    private String Sms;
    private String Email;
    private String ConfirmEmail;
    private String HotelPhone;
    private String CityCode;
    private String VendorOrderStatus;
    private String VendorOrderShowStatus;

    private String  Orderid;
    private int  OrderType;
    private String  ApplyID;

    private String  Hotelid;
    private String  HotelCode;
    private String  HotelName;
    private String  HotelEName;
    private String  InvoiceMode;
    private String  HotelAddress;
    private String  HotelEAddress;
    private String  RoomTypeName;
    private String  RatePlanCode;
    private int   Status;
    private int  Rank;
    private int  Cityid;
    private String CityName;
    private String ExternalNo;
    private int Payment;
    private String CostCenter;
    private String CostNumber;
    private String Cancelid;

    private String Vendor;
    private String BizDeptid;
    private String Deptid;
    private String Corpid;
    private String Bookid;
    private String BookName;
    private String BookData;
    private String SpecialRequest;

    private int NumberOfUnits;
    private int NumberOfDate;
    private int IsPerRoom;
    private int GuestCount;

    public String getSpecialRequest() {
        return SpecialRequest;
    }

    public void setSpecialRequest(String specialRequest) {
        SpecialRequest = specialRequest;
    }

    public List<HorderGuest> getHorderGuest() {
        return horderGuest;
    }

    public void setHorderGuest(List<HorderGuest> horderGuest) {
        this.horderGuest = horderGuest;
    }

    public String getLaterArrivalTime() {
        return LaterArrivalTime;
    }

    public void setLaterArrivalTime(String laterArrivalTime) {
        LaterArrivalTime = laterArrivalTime;
    }

    public HorderContact getHorderContact() {
        return horderContact;
    }

    public void setHorderContact(HorderContact horderContact) {
        this.horderContact = horderContact;
    }



    public FkHorderfuReferenceHorder getFkHorderfuReferenceHorder() {
        return fkHorderfuReferenceHorder;
    }

    public void setFkHorderfuReferenceHorder(FkHorderfuReferenceHorder fkHorderfuReferenceHorder) {
        this.fkHorderfuReferenceHorder = fkHorderfuReferenceHorder;
    }

    public String getCancelid() {
        return Cancelid;
    }

    public void setCancelid(String cancelid) {
        Cancelid = cancelid;
    }

    public String getCheckInDate() {
        return CheckInDate;
    }

    public void setCheckInDate(String checkInDate) {
        CheckInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return CheckOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        CheckOutDate = checkOutDate;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getBreakfast() {
        return Breakfast;
    }

    public void setBreakfast(String breakfast) {
        Breakfast = breakfast;
    }

    public String getNet() {
        return Net;
    }

    public void setNet(String net) {
        Net = net;
    }

    public String getSms() {
        return Sms;
    }

    public void setSms(String sms) {
        Sms = sms;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getConfirmEmail() {
        return ConfirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        ConfirmEmail = confirmEmail;
    }

    public String getHotelPhone() {
        return HotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        HotelPhone = hotelPhone;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getVendorOrderStatus() {
        return VendorOrderStatus;
    }

    public void setVendorOrderStatus(String vendorOrderStatus) {
        VendorOrderStatus = vendorOrderStatus;
    }

    public String getVendorOrderShowStatus() {
        return VendorOrderShowStatus;
    }

    public void setVendorOrderShowStatus(String vendorOrderShowStatus) {
        VendorOrderShowStatus = vendorOrderShowStatus;
    }

    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
    }

    public int getOrderType() {
        return OrderType;
    }

    public void setOrderType(int orderType) {
        OrderType = orderType;
    }

    public String getApplyID() {
        return ApplyID;
    }

    public void setApplyID(String applyID) {
        ApplyID = applyID;
    }

    public String getHotelid() {
        return Hotelid;
    }

    public void setHotelid(String hotelid) {
        Hotelid = hotelid;
    }

    public String getHotelCode() {
        return HotelCode;
    }

    public void setHotelCode(String hotelCode) {
        HotelCode = hotelCode;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getHotelEName() {
        return HotelEName;
    }

    public void setHotelEName(String hotelEName) {
        HotelEName = hotelEName;
    }

    public String getInvoiceMode() {
        return InvoiceMode;
    }

    public void setInvoiceMode(String invoiceMode) {
        InvoiceMode = invoiceMode;
    }

    public String getHotelAddress() {
        return HotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        HotelAddress = hotelAddress;
    }

    public String getHotelEAddress() {
        return HotelEAddress;
    }

    public void setHotelEAddress(String hotelEAddress) {
        HotelEAddress = hotelEAddress;
    }

    public String getRoomTypeName() {
        return RoomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        RoomTypeName = roomTypeName;
    }

    public String getRatePlanCode() {
        return RatePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        RatePlanCode = ratePlanCode;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public int getCityid() {
        return Cityid;
    }

    public void setCityid(int cityid) {
        Cityid = cityid;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getExternalNo() {
        return ExternalNo;
    }

    public void setExternalNo(String externalNo) {
        ExternalNo = externalNo;
    }

    public int getPayment() {
        return Payment;
    }

    public void setPayment(int payment) {
        Payment = payment;
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

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public String getBizDeptid() {
        return BizDeptid;
    }

    public void setBizDeptid(String bizDeptid) {
        BizDeptid = bizDeptid;
    }

    public String getDeptid() {
        return Deptid;
    }

    public void setDeptid(String deptid) {
        Deptid = deptid;
    }

    public String getCorpid() {
        return Corpid;
    }

    public void setCorpid(String corpid) {
        Corpid = corpid;
    }

    public String getBookid() {
        return Bookid;
    }

    public void setBookid(String bookid) {
        Bookid = bookid;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookData() {
        return BookData;
    }

    public void setBookData(String bookData) {
        BookData = bookData;
    }

    public int getNumberOfUnits() {
        return NumberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        NumberOfUnits = numberOfUnits;
    }

    public int getNumberOfDate() {
        return NumberOfDate;
    }

    public void setNumberOfDate(int numberOfDate) {
        NumberOfDate = numberOfDate;
    }

    public int getIsPerRoom() {
        return IsPerRoom;
    }

    public void setIsPerRoom(int isPerRoom) {
        IsPerRoom = isPerRoom;
    }

    public int getGuestCount() {
        return GuestCount;
    }

    public void setGuestCount(int guestCount) {
        GuestCount = guestCount;
    }






}
