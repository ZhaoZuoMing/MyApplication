package f.sky.flight.model;

import java.util.List;

/**
 * Created by zhaody on 2017/11/27.
 * 机票预订实体
 */

public class BookDto {
    private  String Channel;
    private List<OrderLinkmanDto>Linkmans;
    private List<PassengerDto>Passengers;



    public BookDto(String channel, List<OrderLinkmanDto> linkmans, List<PassengerDto> passengers) {
        Channel = channel;
        Linkmans = linkmans;
        Passengers = passengers;
    }

    public String getChannel() {
        return Channel;
    }

    public void setChannel(String channel) {
        Channel = channel;
    }

    public List<OrderLinkmanDto> getLinkmans() {
        return Linkmans;
    }

    public void setLinkmans(List<OrderLinkmanDto> linkmans) {
        Linkmans = linkmans;
    }

    public List<PassengerDto> getPassengers() {
        return Passengers;
    }

    public void setPassengers(List<PassengerDto> passengers) {
        Passengers = passengers;
    }
}
