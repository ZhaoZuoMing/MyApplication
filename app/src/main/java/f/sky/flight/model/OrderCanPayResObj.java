package f.sky.flight.model;

/**
 * Created by Administrator on 2017/4/6/006.
 */

public class OrderCanPayResObj extends ServerResObj {

    private boolean orderCanPayResult;

    public OrderCanPayResObj(){}

    public boolean isOrderCanPayResult() {
        return orderCanPayResult;
    }
    public void setOrderCanPayResult(boolean orderCanPayResult) {
        this.orderCanPayResult = orderCanPayResult;
    }
}
