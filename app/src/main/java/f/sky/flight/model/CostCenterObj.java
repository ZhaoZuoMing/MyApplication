package f.sky.flight.model;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class CostCenterObj {

    private int ID;//<ID>int</ID>
    private int DepID;//<DepID>int</DepID>
    private int SortIndex;//<SortIndex>int</SortIndex>
    private int Status;//<Status>int</Status>
    private String CostCenterName;//<CostCenterName>string</CostCenterName>
    private String CostCenterCode;//<CostCenterCode>string</CostCenterCode>
    private String Remark;//<Remark>string</Remark>
    private int ProjectNumNeeded;//<ProjectNumNeeded>int</ProjectNumNeeded>

    public String costNumPrjNum;//客户端使用
    public int getProjectNumNeeded() {
        return ProjectNumNeeded;
    }
    public void setProjectNumNeeded(int projectNumNeeded) {
        ProjectNumNeeded = projectNumNeeded;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public int getDepID() {
        return DepID;
    }
    public void setDepID(int depID) {
        DepID = depID;
    }
    public int getSortIndex() {
        return SortIndex;
    }
    public void setSortIndex(int sortIndex) {
        SortIndex = sortIndex;
    }
    public int getStatus() {
        return Status;
    }
    public void setStatus(int status) {
        Status = status;
    }
    public String getCostCenterName() {
        return CostCenterName;
    }
    public void setCostCenterName(String costCenterName) {
        CostCenterName = costCenterName;
    }
    public String getCostCenterCode() {
        return CostCenterCode;
    }
    public void setCostCenterCode(String costCenterCode) {
        CostCenterCode = costCenterCode;
    }
    public String getRemark() {
        return Remark;
    }
    public void setRemark(String remark) {
        Remark = remark;
    }


}
