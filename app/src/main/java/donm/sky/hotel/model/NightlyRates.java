package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/1/12/012.
 * 会员价格及加床或者不加床价格
 */

public class NightlyRates {

    /**
     *  <NightlyRate>
         <Member>536.55</Member>   如果现付 会员价
         <Cost>536.55</Cost> 结算价---仅用于结算价模式下的预付产品，可能出现值大于Member. 预付cost
         <Basis xsi:nil="true" /> 未经过DRR计算过的原始价格，入参Options包含5的时候返回
         <Status>true</Status>  库存状态
         <AddBed>-1.0</AddBed>  -1表示不能加床
         <Date>2017-02-19T00:00:00+08:00</Date>
     </NightlyRate>
     */
    private String Member;
    private  String Cost;
    private boolean Status;
    private  String AddBed;
    private  String Date;

    public NightlyRates() {
    }

    public String getMember() {
        return Member;
    }

    public void setMember(String member) {
        Member = member;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getAddBed() {
        return AddBed;
    }

    public void setAddBed(String addBed) {
        AddBed = addBed;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
