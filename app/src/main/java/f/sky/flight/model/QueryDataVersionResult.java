package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/23/023.
 */

public class QueryDataVersionResult extends  ServerResObj {
    private List<F_VersionObj> versionObjL = new ArrayList<F_VersionObj>();

    public List<F_VersionObj> getVersionObjL() {
        return versionObjL;
    }

    public void setVersionObjL(List<F_VersionObj> versionObjL) {
        this.versionObjL = versionObjL;
    }
}
