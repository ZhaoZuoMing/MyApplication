package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/1/13/013.
 * <anyType xsi:type="HE_HotelImageLocation">
 <ID>167088646</ID>
 <ImageID>27848175</ImageID>
 <HotelID>269047</HotelID>
 <HotelCode>30201106</HotelCode>
 <Size>1</Size>
 <WaterMark>0</WaterMark>
 <URL>http://pavo.elongstatic.com/i/API350_350/c0c545de941c66359276010963b236e6.jpg</URL>
 </anyType>
 */

public class HotelImageLocation {

    private int ID;
    private int ImageID;
    private int HotelID;
    private int HotelCode;
    private int Size;
    private int WaterMark;
    private  String URL;

    public HotelImageLocation() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public int getWaterMark() {
        return WaterMark;
    }

    public void setWaterMark(int waterMark) {
        WaterMark = waterMark;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
