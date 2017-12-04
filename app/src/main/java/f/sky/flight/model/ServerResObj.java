package f.sky.flight.model;

/**
 * Created by Administrator on 2016/11/23/023.
 * 服务资源Javabean
 */

public class ServerResObj {

    private boolean success;
    private String message;
    private long elapsedMilliseconds;

    public ServerResObj(){}

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public long getElapsedMilliseconds() {
        return elapsedMilliseconds;
    }
    public void setElapsedMilliseconds(long elapsedMilliseconds) {
        this.elapsedMilliseconds = elapsedMilliseconds;
    }

}
