package f.sky.flight.model;

/**
 * Created by Administrator on 2017/4/6/006.
 */

public class OrderQueryByIdResult extends ServerResObj {

    private B_OrderDOObj orderDo;

    public B_OrderDOObj getOrderDo() {
        return orderDo;
    }

    public void setOrderDo(B_OrderDOObj orderDo) {
        this.orderDo = orderDo;
    }
}
