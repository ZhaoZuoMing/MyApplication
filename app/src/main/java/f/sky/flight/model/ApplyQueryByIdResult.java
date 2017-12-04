package f.sky.flight.model;

/**
 * Created by Administrator on 2016/12/13/013.
 */

public class ApplyQueryByIdResult extends ServerResObj {
    private B_OrderDOObj orderDo;
    private ApplyObj applyObj;
    public B_OrderDOObj getOrderDo() {
        return orderDo;
    }

    public void setOrderDo(B_OrderDOObj orderDo) {
        this.orderDo = orderDo;
    }

    public ApplyObj getApplyObj() {
        return applyObj;
    }

    public void setApplyObj(ApplyObj applyObj) {
        this.applyObj = applyObj;
    }

}