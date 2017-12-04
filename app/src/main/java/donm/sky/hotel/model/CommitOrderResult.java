package donm.sky.hotel.model;

import f.sky.flight.model.ServerResObj;

/**
 * Created by Administrator on 2017/5/9/009.
 */

public class CommitOrderResult extends ServerResObj {

    private int ID;
    private int BookId;
    private int OrderId;
    private int DepId;
    private int AuditID;
    private String AuditMail;
    private int Status;
    private String insertDate;
    private String AuditDate;
    private String Reason;
    private  int ApplyStatus;
    private  String QueryDateStart;
    private String QureryDateEnd;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getDepId() {
        return DepId;
    }

    public void setDepId(int depId) {
        DepId = depId;
    }

    public int getAuditID() {
        return AuditID;
    }

    public void setAuditID(int auditID) {
        AuditID = auditID;
    }

    public String getAuditMail() {
        return AuditMail;
    }

    public void setAuditMail(String auditMail) {
        AuditMail = auditMail;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getAuditDate() {
        return AuditDate;
    }

    public void setAuditDate(String auditDate) {
        AuditDate = auditDate;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public int getApplyStatus() {
        return ApplyStatus;
    }

    public void setApplyStatus(int applyStatus) {
        ApplyStatus = applyStatus;
    }

    public String getQueryDateStart() {
        return QueryDateStart;
    }

    public void setQueryDateStart(String queryDateStart) {
        QueryDateStart = queryDateStart;
    }

    public String getQureryDateEnd() {
        return QureryDateEnd;
    }

    public void setQureryDateEnd(String qureryDateEnd) {
        QureryDateEnd = qureryDateEnd;
    }
}
