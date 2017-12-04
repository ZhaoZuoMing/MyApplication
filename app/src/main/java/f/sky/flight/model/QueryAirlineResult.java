package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class QueryAirlineResult  extends ServerResObj {
    private List<AirlineObj> airlines = new ArrayList<AirlineObj>();

    public List<AirlineObj> getAirlines() {
        return airlines;
    }

    public void setAirlines(List<AirlineObj> airlines) {
        this.airlines = airlines;
    }

    public void addOne(AirlineObj airlineObj){
        airlines.add(airlineObj);
    }
}