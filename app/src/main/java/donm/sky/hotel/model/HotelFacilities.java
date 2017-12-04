package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/1/13/013.
 *   <ID>259074</ID>
    <HotelID>269047</HotelID>
    <HotelCode>30201106</HotelCode>
    <GeneralAmenities>206,207,208,215,217,220,223,224,225,236,198,201,205</GeneralAmenities>
    <RecreationAmenities>158,160,161</RecreationAmenities>
    <ServiceAmenities>10,11,12,13,15,17,19</ServiceAmenities>
 */

public class HotelFacilities {
    private int ID;
    private int HotelID;
    private int HotelCode;
    private String GeneralAmenities;
    private String RecreationAmenities;
    private String ServiceAmenities;

    public HotelFacilities() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getGeneralAmenities() {
        return GeneralAmenities;
    }

    public void setGeneralAmenities(String generalAmenities) {
        GeneralAmenities = generalAmenities;
    }

    public String getRecreationAmenities() {
        return RecreationAmenities;
    }

    public void setRecreationAmenities(String recreationAmenities) {
        RecreationAmenities = recreationAmenities;
    }

    public String getServiceAmenities() {
        return ServiceAmenities;
    }

    public void setServiceAmenities(String serviceAmenities) {
        ServiceAmenities = serviceAmenities;
    }
}
