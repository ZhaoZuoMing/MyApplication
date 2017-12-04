package donm.sky.hotel.model;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12/012.
 * 房间信息 及一个房间的多种价格信息
 *
 * */

public class Room {
    private  String RoomId;
    private  String Name;

    private List<RatePlans> RatePlans;


    public Room() {
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

    public List<donm.sky.hotel.model.RatePlans> getRatePlans() {
        return RatePlans;
    }

    public void setRatePlans(List<donm.sky.hotel.model.RatePlans> ratePlans) {
        RatePlans = ratePlans;
    }
}
