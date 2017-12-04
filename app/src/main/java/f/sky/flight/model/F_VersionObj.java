package f.sky.flight.model;

/**
 * Created by Administrator on 2016/11/23/023.
 */

public class F_VersionObj {

    private int ID;// <ID >int</ID>
    private String OS;// <OS>string</OS>
    private String DataType;// <DataType>string</DataType>
    private String Version;// <Version>string</Version>
    private int status;// <status>int</status>
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getDataType() {
        return DataType;
    }
    public void setDataType(String dataType) {
        DataType = dataType;
    }
    public String getVersion() {
        return Version;
    }
    public void setVersion(String version) {
        Version = version;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getOS() {
        return OS;
    }
    public void setOS(String oS) {
        OS = oS;
    }

}
