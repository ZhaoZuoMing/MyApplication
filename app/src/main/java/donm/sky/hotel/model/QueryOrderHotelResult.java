package donm.sky.hotel.model;

import java.util.List;

import f.sky.flight.model.ServerResObj;

/**
 * Created by Administrator on 2017/3/29/029.
 * 酒店订单列表对象
 */

public class QueryOrderHotelResult extends ServerResObj {

    private List<HotelDataList> hotelDataLists;

    public List<HotelDataList> getHotelDataLists() {
        return hotelDataLists;
    }

    public void setHotelDataLists(List<HotelDataList> hotelDataLists) {
        this.hotelDataLists = hotelDataLists;
    }
}
