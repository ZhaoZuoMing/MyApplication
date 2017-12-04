package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/2/24/024.
 */

public class ArriveTime {

    private int id;
    private String time;

    public ArriveTime(int id, String time) {
        this.id = id;
        this.time = time;
    }

    public ArriveTime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
