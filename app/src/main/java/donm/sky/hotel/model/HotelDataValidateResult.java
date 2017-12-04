package donm.sky.hotel.model;

import f.sky.flight.model.ServerResObj;

/**
 * Created by Administrator on 2017/3/17/017.
 */

public class HotelDataValidateResult extends ServerResObj {
    private  ResultObject resultObject;

    public ResultObject getResultObject() {
        return resultObject;
    }

    public void setResultObject(ResultObject resultObject) {
        this.resultObject = resultObject;
    }
}
