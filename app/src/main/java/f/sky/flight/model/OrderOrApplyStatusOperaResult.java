package f.sky.flight.model;

/**
 * Created by Administrator on 2017/4/6/006.
 */

public class OrderOrApplyStatusOperaResult extends ServerResObj {
    private boolean ResultObject;
    private B_OrderDOObj orderDOObj;

    public B_OrderDOObj getOrderDOObj() {
        return orderDOObj;
    }

    public void setOrderDOObj(B_OrderDOObj orderDOObj) {
        this.orderDOObj = orderDOObj;
    }

    public boolean isResultObject() {
        return ResultObject;
    }

    public void setResultObject(boolean resultObject) {
        ResultObject = resultObject;
    }
}
