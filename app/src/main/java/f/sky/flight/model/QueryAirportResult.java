package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class QueryAirportResult extends ServerResObj {

    private List<AirportObj> airprots = new ArrayList<AirportObj>();

    public List<AirportObj> getAirprots() {
        return airprots;
    }

    public void setAirprots(List<AirportObj> airprots) {
        this.airprots = airprots;
    }

    public void addOne(AirportObj airportObj){
        airprots.add(airportObj);
    }
}
