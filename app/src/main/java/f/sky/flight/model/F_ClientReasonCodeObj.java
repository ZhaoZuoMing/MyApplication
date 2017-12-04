package f.sky.flight.model;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class F_ClientReasonCodeObj {


    private int ID;
    private int ClientID;
    private String Code;
    private int Type;
    private String DescC;
    private String DescE;
    private String Remark;
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public int getClientID() {
        return ClientID;
    }
    public void setClientID(int clientID) {
        ClientID = clientID;
    }
    public String getCode() {
        return Code;
    }
    public void setCode(String code) {
        Code = code;
    }
    public int getType() {
        return Type;
    }
    public void setType(int type) {
        Type = type;
    }
    public String getDescC() {
        return DescC;
    }
    public void setDescC(String descC) {
        DescC = descC;
    }
    public String getDescE() {
        return DescE;
    }
    public void setDescE(String descE) {
        DescE = descE;
    }
    public String getRemark() {
        return Remark;
    }
    public void setRemark(String remark) {
        Remark = remark;
    }


}
