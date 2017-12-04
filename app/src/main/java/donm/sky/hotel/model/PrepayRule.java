package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/2/8/008.
 * 预订规则
 */

public class PrepayRule {
    /**
     *  <Description>预付规则：在16.07.19 到20.12.31期间入住，离到店日不足60小时取消将收取罚金全额房
     费的百分之100.0，到店日不足0小时之后无法取消。</Description>
     <DateType>CheckInDay</DateType>
     <StartDate>2016-07-19T00:00:00+08:00</StartDate>
     <EndDate>2020-12-31T00:00:00+08:00</EndDate>
     <WeekSet>1,2,3,4,5,6,7,</WeekSet>
     <ChangeRule>PrepayNeedSomeDay</ChangeRule>
     <CashScaleFirstAfter>Percent</CashScaleFirstAfter>
     <CashScaleFirstBefore>FristNight</CashScaleFirstBefore>
     <DateNum>2017-02-08T00:00:00+08:00</DateNum>
     <Time>18:00</Time>
     <DeductFeesAfter>1</DeductFeesAfter>
     <DeductFeesBefore>0</DeductFeesBefore>
     <DeductNumAfter>100.0</DeductNumAfter>
     <DeductNumBefore>0.0</DeductNumBefore>
     <Hour>60</Hour>
     <Hour2>0</Hour2>
     <PrepayRuleId>1</PrepayRuleId>
     */

    private String Description;
    private String DateType;
    private String StartDate;
    private String EndDate;
    private String WeekSet;
    private String ChangeRule;
    private String CashScaleFirstAfter;
    private String CashScaleFirstBefore;
    private String DateNum;
    private String Time;
    private int DeductFeesAfter;
    private int DeductFeesBefore;
    private String DeductNumAfter;
    private String DeductNumBefore;
    private String Hour;
    private String Hour2;
    private String PrepayRuleId;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDateType() {
        return DateType;
    }

    public void setDateType(String dateType) {
        DateType = dateType;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getWeekSet() {
        return WeekSet;
    }

    public void setWeekSet(String weekSet) {
        WeekSet = weekSet;
    }

    public String getChangeRule() {
        return ChangeRule;
    }

    public void setChangeRule(String changeRule) {
        ChangeRule = changeRule;
    }

    public String getCashScaleFirstAfter() {
        return CashScaleFirstAfter;
    }

    public void setCashScaleFirstAfter(String cashScaleFirstAfter) {
        CashScaleFirstAfter = cashScaleFirstAfter;
    }

    public String getCashScaleFirstBefore() {
        return CashScaleFirstBefore;
    }

    public void setCashScaleFirstBefore(String cashScaleFirstBefore) {
        CashScaleFirstBefore = cashScaleFirstBefore;
    }

    public String getDateNum() {
        return DateNum;
    }

    public void setDateNum(String dateNum) {
        DateNum = dateNum;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getDeductFeesAfter() {
        return DeductFeesAfter;
    }

    public void setDeductFeesAfter(int deductFeesAfter) {
        DeductFeesAfter = deductFeesAfter;
    }

    public int getDeductFeesBefore() {
        return DeductFeesBefore;
    }

    public void setDeductFeesBefore(int deductFeesBefore) {
        DeductFeesBefore = deductFeesBefore;
    }

    public String getDeductNumAfter() {
        return DeductNumAfter;
    }

    public void setDeductNumAfter(String deductNumAfter) {
        DeductNumAfter = deductNumAfter;
    }

    public String getDeductNumBefore() {
        return DeductNumBefore;
    }

    public void setDeductNumBefore(String deductNumBefore) {
        DeductNumBefore = deductNumBefore;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getHour2() {
        return Hour2;
    }

    public void setHour2(String hour2) {
        Hour2 = hour2;
    }

    public String getPrepayRuleId() {
        return PrepayRuleId;
    }

    public void setPrepayRuleId(String prepayRuleId) {
        PrepayRuleId = prepayRuleId;
    }
}
