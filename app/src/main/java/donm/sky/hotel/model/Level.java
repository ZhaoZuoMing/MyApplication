package donm.sky.hotel.model;

/**
 * Created by Administrator on 2016/12/22/022.
 */

public class Level {

    private boolean isCheck;
    private String msg;
    private int position;

    public Level(boolean isCheck, String msg, int position) {
        this.isCheck = isCheck;
        this.msg = msg;
        this.position = position;
    }

    public Level() {
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
