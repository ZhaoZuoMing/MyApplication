package donm.sky.hotel.model;

import java.util.List;

import f.sky.flight.model.ServerResObj;

/**
 * Created by Administrator on 2017/1/13/013.
 * 酒店静态数据JavaBean
 */

public class HotelInfoSearchResult extends ServerResObj {

    private List<HotelDetail> details;
    private List<HotelRoom> rooms;
    private List<HotelServiceRank> ranks;
    private List<HotelFacilities> facilities;
    private List<HotelImage> images;
    private List<HotelImageLocation> imgLocations;

    public HotelInfoSearchResult() {
    }

    public List<HotelDetail> getDetails() {
        return details;
    }

    public void setDetails(List<HotelDetail> details) {
        this.details = details;
    }

    public List<HotelRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoom> rooms) {
        this.rooms = rooms;
    }

    public List<HotelServiceRank> getRanks() {
        return ranks;
    }

    public void setRanks(List<HotelServiceRank> ranks) {
        this.ranks = ranks;
    }

    public List<HotelFacilities> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<HotelFacilities> facilities) {
        this.facilities = facilities;
    }

    public List<HotelImage> getImages() {
        return images;
    }

    public void setImages(List<HotelImage> images) {
        this.images = images;
    }

    public List<HotelImageLocation> getImgLocations() {
        return imgLocations;
    }

    public void setImgLocations(List<HotelImageLocation> imgLocations) {
        this.imgLocations = imgLocations;
    }
}
