package f.sky.flight.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class QueryReasonCodeResult extends ServerResObj {
    private List<F_ClientReasonCodeObj> clientReasonCodes = new ArrayList<F_ClientReasonCodeObj>();

    public List<F_ClientReasonCodeObj> getClientReasonCodes() {
        return clientReasonCodes;
    }

    public void setClientReasonCodes(List<F_ClientReasonCodeObj> clientReasonCodes) {
        this.clientReasonCodes = clientReasonCodes;
    }

    public void addOne(F_ClientReasonCodeObj clientReasonCodeObj){
        clientReasonCodes.add(clientReasonCodeObj);
    }
}
