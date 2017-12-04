package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24/024.
 * 用户信息
 */

public class QueryPsgResult extends ServerResObj {
    private List<B_TouristDOObj> B_TouristDO = new ArrayList<B_TouristDOObj>();

    public QueryPsgResult(){}

    public List<B_TouristDOObj> getB_TouristDO() {
        return B_TouristDO;
    }

    public void setB_TouristDO(List<B_TouristDOObj> b_TouristDO) {
        B_TouristDO = b_TouristDO;
    }

    public void addOne(B_TouristDOObj b_TouristDO){
        B_TouristDO.add(b_TouristDO);
    }
}

