package f.sky.flight.model;

/**
 * Created by Administrator on 2016/12/9/009.
 */

public class PsgOperaResult extends ServerResObj {
    private boolean ResultObject;

    private B_TouristDOObj touristDOObj;

    public B_TouristDOObj getTouristDOObj() {
        return touristDOObj;
    }

    public void setTouristDOObj(B_TouristDOObj touristDOObj) {
        this.touristDOObj = touristDOObj;
    }

    public PsgOperaResult(){}

    public boolean isResultObject() {
        return ResultObject;
    }

    public void setResultObject(boolean resultObject) {
        ResultObject = resultObject;
    }
}

