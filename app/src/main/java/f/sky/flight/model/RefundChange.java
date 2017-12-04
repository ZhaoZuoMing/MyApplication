package f.sky.flight.model;

/**
 * Created by zhaody on 2017/11/15.
 */

public class RefundChange {
   private  String Airline;
   private  String Cabin;
   private String Remark;
   private String BaggageAllowance;
   private RefundDetail RefundDetail;
   private ChangeDetail ChangeDetail;
   private  EndorsementDetail EndorsementDetail;

    public String getAirline() {
        return Airline;
    }

    public void setAirline(String airline) {
        Airline = airline;
    }

    public String getCabin() {
        return Cabin;
    }

    public void setCabin(String cabin) {
        Cabin = cabin;
    }

    public String getRemark() {
        if (Remark==null){
            return "";
        }
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getBaggageAllowance() {
        return BaggageAllowance;
    }

    public void setBaggageAllowance(String baggageAllowance) {
        BaggageAllowance = baggageAllowance;
    }

    public f.sky.flight.model.RefundDetail getRefundDetail() {
        return RefundDetail;
    }

    public void setRefundDetail(f.sky.flight.model.RefundDetail refundDetail) {
        RefundDetail = refundDetail;
    }

    public f.sky.flight.model.ChangeDetail getChangeDetail() {
        return ChangeDetail;
    }

    public void setChangeDetail(f.sky.flight.model.ChangeDetail changeDetail) {
        ChangeDetail = changeDetail;
    }

    public f.sky.flight.model.EndorsementDetail getEndorsementDetail() {
        return EndorsementDetail;
    }

    public void setEndorsementDetail(f.sky.flight.model.EndorsementDetail endorsementDetail) {
        EndorsementDetail = endorsementDetail;
    }
}
