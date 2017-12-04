package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class QueryCostCenterResult extends ServerResObj {
    private List<CostCenterObj> costCenterObjs = new ArrayList<CostCenterObj>();

    public List<CostCenterObj> getCostCenterObjs() {
        return costCenterObjs;
    }

    public void setCostCenterObjs(List<CostCenterObj> costCenterObjs) {
        this.costCenterObjs = costCenterObjs;
    }
    public void addOneCostCenter(CostCenterObj costCenterObj){
        costCenterObjs.add(costCenterObj);
    }
}
