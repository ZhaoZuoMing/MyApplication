package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/1/13/013.
 *  <ID>7143723</ID>
 <HotelID>269047</HotelID>
 <HotelCode>30201106</HotelCode>
 <RoomId>1039</RoomId>
 <Name>大床房</Name>
 <EName>King Room</EName>
 <Area>25</Area>
 <Floor>5-13</Floor>
 <BroadnetAccess>1</BroadnetAccess>
 <BroadnetFee>0</BroadnetFee>
 <BedType>大床1.5米</BedType>
 <EBedType>BigBed1.5M</EBedType>
 <Description>大床1.5米、5-13楼、25平米、免费宽带、可入住2人</Description>
 <EDescription>BigBed1.5M, Located on the 5-13(th) floors, 25 square meters, Freewired, Room sleeps 2 guests</EDescription>
 <Comments>房间窗户朝向天井; 内窗;</Comments>
 <EComments>内窗;</EComments>
 <Capacity>2</Capacity>
 <Facilities>42,63,65,67,110,112,677,86,90</Facilities>
 <Amount>1</Amount>
 */

public class HotelRoom {

    private int id;
    private int HotelID;
    private int HotelCode;
    private String RoomId;
    private String Name;
    private String EName;
    private int Area;
    private String Floor;//所在层数
    private int BroadnetAccess;
    private  int BroadnetFee;
    private String BedType;//床型
    private String EBedType;
    private String Description;
    private String EDescription;
    private String Comments;
    private String EComments;
    private int Capacity;
    private String Facilities;
    private int Amount;

    public HotelRoom() {
    }

    public HotelRoom(int id, int hotelID, int hotelCode, String roomId, String name, String EName, int area, String floor, int broadnetAccess, int broadnetFee, String bedType, String EBedType, String description, String EDescription, String comments, String EComments, int capacity, String facilities, int amount) {
        this.id = id;
        HotelID = hotelID;
        HotelCode = hotelCode;
        RoomId = roomId;
        Name = name;
        this.EName = EName;
        Area = area;
        Floor = floor;
        BroadnetAccess = broadnetAccess;
        BroadnetFee = broadnetFee;
        BedType = bedType;
        this.EBedType = EBedType;
        Description = description;
        this.EDescription = EDescription;
        Comments = comments;
        this.EComments = EComments;
        Capacity = capacity;
        Facilities = facilities;
        Amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelID() {
        return HotelID;
    }

    public void setHotelID(int hotelID) {
        HotelID = hotelID;
    }

    public int getHotelCode() {
        return HotelCode;
    }

    public void setHotelCode(int hotelCode) {
        HotelCode = hotelCode;
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEName() {
        return EName;
    }

    public void setEName(String EName) {
        this.EName = EName;
    }

    public int getArea() {
        return Area;
    }

    public void setArea(int area) {
        Area = area;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public int getBroadnetAccess() {
        return BroadnetAccess;
    }

    public void setBroadnetAccess(int broadnetAccess) {
        BroadnetAccess = broadnetAccess;
    }

    public int getBroadnetFee() {
        return BroadnetFee;
    }

    public void setBroadnetFee(int broadnetFee) {
        BroadnetFee = broadnetFee;
    }

    public String getBedType() {
        return BedType;
    }

    public void setBedType(String bedType) {
        BedType = bedType;
    }

    public String getEBedType() {
        return EBedType;
    }

    public void setEBedType(String EBedType) {
        this.EBedType = EBedType;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEDescription() {
        return EDescription;
    }

    public void setEDescription(String EDescription) {
        this.EDescription = EDescription;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getEComments() {
        return EComments;
    }

    public void setEComments(String EComments) {
        this.EComments = EComments;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public String getFacilities() {
        return Facilities;
    }

    public void setFacilities(String facilities) {
        Facilities = facilities;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }
}
