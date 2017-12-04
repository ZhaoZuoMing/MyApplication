package donm.sky.hotel.model;

import java.util.List;

import f.sky.flight.model.ServerResObj;

/**
 * Created by Administrator on 2017/1/12/012.
 */

public class HotelListSearchResult extends ServerResObj {
    private List<Hotels>  hotels;

    public HotelListSearchResult() {
    }

    public List<Hotels> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotels> hotels) {
        this.hotels = hotels;
    }
}
