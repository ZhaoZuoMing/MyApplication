package donm.sky.hotel.model;

import f.sky.flight.model.ServerResObj;

/**
 * Created by Administrator on 2017/2/8/008.
 */

public class HotelDetailSearchResult extends ServerResObj {
   private DHotel dHotel;

    public DHotel getdHotel() {
        return dHotel;
    }

    public void setdHotel(DHotel dHotel) {
        this.dHotel = dHotel;
    }
}
