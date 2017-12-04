package f.sky.flight.model;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class LoginRegResult extends ServerResObj {

    private UserServerObj userServerObject;

    public LoginRegResult(){}

    public UserServerObj getUserServerObject() {
        return userServerObject;
    }

    public void setUserServerObject(UserServerObj userServerObject) {
        this.userServerObject = userServerObject;
    }


}
