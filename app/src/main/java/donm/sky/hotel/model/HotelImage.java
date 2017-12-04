package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/1/13/013.
 *    <ImageID>27848175</ImageID>
     <HotelID>269047</HotelID>
    <HotelCode>30201106</HotelCode>
    <RoomId />
    <Type>5</Type>
    <IsCoverImage>1</IsCoverImage>
   <AuthorType>Hotel</AuthorType>
 */

public class HotelImage {

    private int ImageID;
    private int HotelID;
    private int HotelCode;
    private int Type;
    private int IsCoverImage;
    private String AuthorType;
    private String RoomId;

    public HotelImage() {
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
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

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public int getIsCoverImage() {
        return IsCoverImage;
    }

    public void setIsCoverImage(int isCoverImage) {
        IsCoverImage = isCoverImage;
    }

    public String getAuthorType() {
        return AuthorType;
    }

    public void setAuthorType(String authorType) {
        AuthorType = authorType;
    }
}
