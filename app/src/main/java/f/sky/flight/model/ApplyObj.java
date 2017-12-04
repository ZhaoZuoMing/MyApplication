package f.sky.flight.model;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/12/012.
 */

public class ApplyObj {

    private int ID;// <ID>int</ID>
    private int BookID;// <BookID>int</BookID>
    private int OrderID;// <OrderID>int</OrderID>
    private int DepID;// <DepID>int</DepID>
    private int AuditID;// <AuditID>int</AuditID>
    private int Status;// <Status>int</Status>
    private String Reason;// <Reason>string</Reason>
    private String AuditMail;// <AuditMail>string</AuditMail>
    private Date insertDate;// <insertDate>dateTime</insertDate>
    private Date AuditDate;// <AuditDate>dateTime</AuditDate>
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public int getBookID() {
        return BookID;
    }
    public void setBookID(int bookID) {
        BookID = bookID;
    }
    public int getOrderID() {
        return OrderID;
    }
    public void setOrderID(int orderID) {
        OrderID = orderID;
    }
    public int getDepID() {
        return DepID;
    }
    public void setDepID(int depID) {
        DepID = depID;
    }
    public int getAuditID() {
        return AuditID;
    }
    public void setAuditID(int auditID) {
        AuditID = auditID;
    }
    public int getStatus() {
        return Status;
    }
    public void setStatus(int status) {
        Status = status;
    }
    public String getReason() {
        return Reason;
    }
    public void setReason(String reason) {
        Reason = reason;
    }
    public String getAuditMail() {
        return AuditMail;
    }
    public void setAuditMail(String auditMail) {
        AuditMail = auditMail;
    }
    public Date getInsertDate() {
        return insertDate;
    }
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
    public Date getAuditDate() {
        return AuditDate;
    }
    public void setAuditDate(Date auditDate) {
        AuditDate = auditDate;
    }

    @Override
    public String toString() {
        return "ApplyObj{" +
                "ID=" + ID +
                ", BookID=" + BookID +
                ", OrderID=" + OrderID +
                ", DepID=" + DepID +
                ", AuditID=" + AuditID +
                ", Status=" + Status +
                ", Reason='" + Reason + '\'' +
                ", AuditMail='" + AuditMail + '\'' +
                ", insertDate=" + insertDate +
                ", AuditDate=" + AuditDate +
                '}';
    }
}
