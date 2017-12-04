package donm.sky.hotel.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/13/013.
 * 酒店详细内容
 *
 */

public class HotelDetail implements Serializable{


    private int HotelID;
    private int Code;
    private String Name;
    private String Address;
    private String EAddress;
    private String EName;
    private int StarRate;
    private int Category;
    private String Phone;
    private String Fax;
    private String EstablishmentDate;
    private String RenovationDate;
    private int GroupId;
    private  int BrandId;
    private  int IsEconomic;
    private  int IsApartment;
    private double GoogleLat;
    private double GoogleLon;
    private double BaiduLat;
    private double BaiduLon;
    private int Rank;
    private  String CityId;
    private  String District;
    private  String BusinessZone;
    private  String BusinessZone2;
    private String CreditCards;
    private String ECreditCards;
    private String IntroEdito;//酒店详细介绍
    private String EIntroEditor;
    private String Description;//酒店开业时间及装修时间
    private String Traffic;//交通信息
    private String ETraffic;
    private  String Features;//地处陆家嘴金融贸易区，毗邻浦东新世界商城、第一八佰伴和时代广场
    private  String Facilities;//1,5,11,12,13
    private int HasCoupon;
    private int RoomTotalAmount;//房间最大数
    private String Status;//open
    private  int  ReviewCount;
    private int ReviewGood;
    private int ReviewPoor;
    private String EFeatures;
    private String Themes;

    public String getThemes() {
        return Themes;
    }

    public void setThemes(String themes) {
        Themes = themes;
    }

    public String getEFeatures() {
        return EFeatures;
    }

    public void setEFeatures(String EFeatures) {
        this.EFeatures = EFeatures;
    }

    public HotelDetail() {
    }

    public int getHotelID() {
        return HotelID;
    }

    public void setHotelID(int hotelID) {
        HotelID = hotelID;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEAddress() {
        return EAddress;
    }

    public void setEAddress(String EAddress) {
        this.EAddress = EAddress;
    }

    public String getEName() {
        return EName;
    }

    public void setEName(String EName) {
        this.EName = EName;
    }

    public int getStarRate() {
        return StarRate;
    }

    public void setStarRate(int starRate) {
        StarRate = starRate;
    }

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getEstablishmentDate() {
        return EstablishmentDate;
    }

    public void setEstablishmentDate(String establishmentDate) {
        EstablishmentDate = establishmentDate;
    }

    public String getRenovationDate() {
        return RenovationDate;
    }

    public void setRenovationDate(String renovationDate) {
        RenovationDate = renovationDate;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }

    public int getBrandId() {
        return BrandId;
    }

    public void setBrandId(int brandId) {
        BrandId = brandId;
    }

    public int getIsEconomic() {
        return IsEconomic;
    }

    public void setIsEconomic(int isEconomic) {
        IsEconomic = isEconomic;
    }

    public int getIsApartment() {
        return IsApartment;
    }

    public void setIsApartment(int isApartment) {
        IsApartment = isApartment;
    }

    public double getGoogleLat() {
        return GoogleLat;
    }

    public void setGoogleLat(double googleLat) {
        GoogleLat = googleLat;
    }

    public double getGoogleLon() {
        return GoogleLon;
    }

    public void setGoogleLon(double googleLon) {
        GoogleLon = googleLon;
    }

    public double getBaiduLat() {
        return BaiduLat;
    }

    public void setBaiduLat(double baiduLat) {
        BaiduLat = baiduLat;
    }

    public double getBaiduLon() {
        return BaiduLon;
    }

    public void setBaiduLon(double baiduLon) {
        BaiduLon = baiduLon;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getBusinessZone() {
        return BusinessZone;
    }

    public void setBusinessZone(String businessZone) {
        BusinessZone = businessZone;
    }

    public String getBusinessZone2() {
        return BusinessZone2;
    }

    public void setBusinessZone2(String businessZone2) {
        BusinessZone2 = businessZone2;
    }

    public String getCreditCards() {
        return CreditCards;
    }

    public void setCreditCards(String creditCards) {
        CreditCards = creditCards;
    }

    public String getECreditCards() {
        return ECreditCards;
    }

    public void setECreditCards(String ECreditCards) {
        this.ECreditCards = ECreditCards;
    }

    public String getIntroEdito() {
        return IntroEdito;
    }

    public void setIntroEdito(String introEdito) {
        IntroEdito = introEdito;
    }

    public String getEIntroEditor() {
        return EIntroEditor;
    }

    public void setEIntroEditor(String EIntroEditor) {
        this.EIntroEditor = EIntroEditor;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTraffic() {
        return Traffic;
    }

    public void setTraffic(String traffic) {
        Traffic = traffic;
    }

    public String getETraffic() {
        return ETraffic;
    }

    public void setETraffic(String ETraffic) {
        this.ETraffic = ETraffic;
    }

    public String getFeatures() {
        return Features;
    }

    public void setFeatures(String features) {
        Features = features;
    }

    public String getFacilities() {
        return Facilities;
    }

    public void setFacilities(String facilities) {
        Facilities = facilities;
    }

    public int getHasCoupon() {
        return HasCoupon;
    }

    public void setHasCoupon(int hasCoupon) {
        HasCoupon = hasCoupon;
    }

    public int getRoomTotalAmount() {
        return RoomTotalAmount;
    }

    public void setRoomTotalAmount(int roomTotalAmount) {
        RoomTotalAmount = roomTotalAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getReviewCount() {
        return ReviewCount;
    }

    public void setReviewCount(int reviewCount) {
        ReviewCount = reviewCount;
    }

    public int getReviewGood() {
        return ReviewGood;
    }

    public void setReviewGood(int reviewGood) {
        ReviewGood = reviewGood;
    }

    public int getReviewPoor() {
        return ReviewPoor;
    }

    public void setReviewPoor(int reviewPoor) {
        ReviewPoor = reviewPoor;
    }

}
