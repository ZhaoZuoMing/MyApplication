package donm.sky.hotel.model;

import java.io.Serializable;
import java.util.List;

import f.sky.flight.model.B_TouristDOObj;

/**
 * Created by Administrator on 2017/2/22/022.
 */

public class CommitHotel implements Serializable{
    private static final long serialVersionUID = -7620435178023928252L;
    public CommitHotel() {
    }

    private String payType;
    private String food;
    private int rooms;
    private ArriveTime enterTime;
    private String change_rule,danBaoRule;
    private String costCenter;
    private String projectname;
    private String orderP;
    private String user_name,phone_number,Email;
    private int numNight;

    private BookingRules bookingRule;
    private GuaranteeRule guaranteeRule;
    private List<B_TouristDOObj> b_touristDOObjs;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getNumNight() {
        return numNight;
    }

    public void setNumNight(int numNight) {
        this.numNight = numNight;
    }

    public String getDanBaoRule() {
        return danBaoRule;
    }

    public void setDanBaoRule(String danBaoRule) {
        this.danBaoRule = danBaoRule;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getOrderP() {
        return orderP;
    }

    public void setOrderP(String orderP) {
        this.orderP = orderP;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public ArriveTime getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(ArriveTime enterTime) {
        this.enterTime = enterTime;
    }

    public String getChange_rule() {
        return change_rule;
    }

    public void setChange_rule(String change_rule) {
        this.change_rule = change_rule;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public BookingRules getBookingRule() {
        return bookingRule;
    }

    public void setBookingRule(BookingRules bookingRule) {
        this.bookingRule = bookingRule;
    }

    public GuaranteeRule getGuaranteeRule() {
        return guaranteeRule;
    }

    public void setGuaranteeRule(GuaranteeRule guaranteeRule) {
        this.guaranteeRule = guaranteeRule;
    }

    public List<B_TouristDOObj> getB_touristDOObjs() {
        return b_touristDOObjs;
    }

    public void setB_touristDOObjs(List<B_TouristDOObj> b_touristDOObjs) {
        this.b_touristDOObjs = b_touristDOObjs;
    }
}
