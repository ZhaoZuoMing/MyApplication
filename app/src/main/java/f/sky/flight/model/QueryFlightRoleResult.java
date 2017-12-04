package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class QueryFlightRoleResult extends ServerResObj{
    private List<FlightRoleObj> flightRoleObjL = new ArrayList<FlightRoleObj>();

    public List<FlightRoleObj> getFlightRoleObjL() {
        return flightRoleObjL;
    }

    public void setFlightRoleObjL(List<FlightRoleObj> flightRoleObjL) {
        this.flightRoleObjL = flightRoleObjL;
    }
}
