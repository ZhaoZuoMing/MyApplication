package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class QueryPlaneStyleResult extends ServerResObj {
    private List<PlaneStyleObj> planeStyles = new ArrayList<PlaneStyleObj>();

    public List<PlaneStyleObj> getPlaneStyles() {
        return planeStyles;
    }

    public void setPlaneStyles(List<PlaneStyleObj> planeStyles) {
        this.planeStyles = planeStyles;
    }

    public void addOne(PlaneStyleObj planeStyleObj){
        planeStyles.add(planeStyleObj);
    }
}