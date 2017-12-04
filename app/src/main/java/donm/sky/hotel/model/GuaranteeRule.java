package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/2/8/008.
 * 担保规则
 */

public class GuaranteeRule {
    /***
     *  <Description>担保条件：在17.02.03至17.04.18入住如果在20:00至次日6:00到店，或预订房量超过5间
     需要您提供信用卡担保。客人入住日前2天24:00点前可以变更取消，之后无法变更取消，如未入住，将扣除第一晚房费作为违约金。    </Description>
     <DateType>CheckInDay</DateType>
     <StartDate>2017-02-03T00:00:00+08:00</StartDate>
     <EndDate>2017-04-18T00:00:00+08:00</EndDate>
     <WeekSet>1,2,3,4,5,6,7,</WeekSet>
     <IsTimeGuarantee>true</IsTimeGuarantee>
     <StartTime>20:00</StartTime>
     <EndTime>6:00</EndTime>
     <IsTomorrow>true</IsTomorrow>
     <IsAmountGuarantee>true</IsAmountGuarantee>
     <Amount>5</Amount>
     <GuaranteeType>FirstNightCost</GuaranteeType>
     <ChangeRule>NeedCheckin24hour</ChangeRule>
     <Day>2008-08-18T00:00:00+08:00</Day>
     <Time>18:00</Time>
     <Hour>48</Hour>
     <GuranteeRuleId>185728492</GuranteeRuleId>
     */

    private String Description;
    private String DateType;
    private String StartDate;
    private String EndDate;
    private String WeekSet;
    private boolean IsTimeGuarantee;
    private String StartTime;
    private String EndTime;
    private boolean  IsTomorrow;
    private boolean IsAmountGuarantee;
    private int Amount;
    private  String GuaranteeType;
    private  String ChangeRule;
    private  String Day;
    private String Time;
    private  String Hour;
    private String GuranteeRuleId;

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

    public boolean isTimeGuarantee() {
        return IsTimeGuarantee;
    }

    public void setTimeGuarantee(boolean timeGuarantee) {
        IsTimeGuarantee = timeGuarantee;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public boolean isTomorrow() {
        return IsTomorrow;
    }

    public void setTomorrow(boolean tomorrow) {
        IsTomorrow = tomorrow;
    }

    public boolean isAmountGuarantee() {
        return IsAmountGuarantee;
    }

    public void setAmountGuarantee(boolean amountGuarantee) {
        IsAmountGuarantee = amountGuarantee;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getGuaranteeType() {
        return GuaranteeType;
    }

    public void setGuaranteeType(String guaranteeType) {
        GuaranteeType = guaranteeType;
    }

    public String getChangeRule() {
        return ChangeRule;
    }

    public void setChangeRule(String changeRule) {
        ChangeRule = changeRule;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public String getGuranteeRuleId() {
        return GuranteeRuleId;
    }

    public void setGuranteeRuleId(String guranteeRuleId) {
        GuranteeRuleId = guranteeRuleId;
    }
}
