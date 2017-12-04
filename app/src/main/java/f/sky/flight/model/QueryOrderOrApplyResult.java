package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30/030.
 */

public class QueryOrderOrApplyResult extends ServerResObj {
    private List<B_OrderDOObj> orderDoS = new ArrayList<>();

    public List<B_OrderDOObj> getOrderDoS() {
        return orderDoS;
    }

    public void setOrderDoS(List<B_OrderDOObj> orderDoS) {
        this.orderDoS = orderDoS;
    }

    public void addOneOrder(B_OrderDOObj order){
        orderDoS.add(order);
    }

}
