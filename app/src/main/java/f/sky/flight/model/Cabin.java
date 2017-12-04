package f.sky.flight.model;

/**
 * Created by zhaody on 2017/11/14.
 */

public class Cabin {
    private Variables Variables;
    private RefundChange RefundChange;
    private String FlightNumber;
//  private String PolicyId;
    private String SupplierType;
    private int FareType;
    private String Name;
    private int Type;
    private String Code;
    private float TicketPrice;
    private float SettlePrice;
    private float SalesPrice;
    private float Tax;
    private float Reward;
    private float Discount;
    private int Count;
//    private String FlightPolicy; //未启用字段
//    private String LowerSegment;//未启用字段
    private  String TypeName;
    private String  FareTypeName;
    private String SupplierTypeName;
    private boolean IsAllowOrder;
    private Rules Rules;

    public Variables getVariables() {
        return Variables;
    }

    public void setVariables(Variables variables) {
        this.Variables = variables;
    }

    public RefundChange getRefundChange() {
        return RefundChange;
    }

    public void setRefundChange(RefundChange refundChange) {
        this.RefundChange = refundChange;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        FlightNumber = flightNumber;
    }

//    public String getPolicyId() {
//        return PolicyId;
//    }
//
//    public void setPolicyId(String policyId) {
//        PolicyId = policyId;
//    }

    public String getSupplierType() {
        return SupplierType;
    }

    public void setSupplierType(String supplierType) {
        SupplierType = supplierType;
    }

    public int getFareType() {
        return FareType;
    }

    public void setFareType(int fareType) {
        FareType = fareType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public float getTicketPrice() {
        return TicketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        TicketPrice = ticketPrice;
    }

    public float getSettlePrice() {
        return SettlePrice;
    }

    public void setSettlePrice(float settlePrice) {
        SettlePrice = settlePrice;
    }

    public float getSalesPrice() {
        return SalesPrice;
    }

    public void setSalesPrice(float salesPrice) {
        SalesPrice = salesPrice;
    }

    public float getTax() {
        return Tax;
    }

    public void setTax(float tax) {
        Tax = tax;
    }

    public float getReward() {
        return Reward;
    }

    public void setReward(float reward) {
        Reward = reward;
    }

    public float getDiscount() {
        return Discount;
    }

    public void setDiscount(float discount) {
        Discount = discount;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

//    public String getFlightPolicy() {
//        return FlightPolicy;
//    }
//    public void setFlightPolicy(String flightPolicy) {
//        FlightPolicy = flightPolicy;
//    }
//
//    public String getLowerSegment() {
//        return LowerSegment;
//    }
//
//    public void setLowerSegment(String lowerSegment) {
//        LowerSegment = lowerSegment;
//    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getFareTypeName() {
        return FareTypeName;
    }

    public void setFareTypeName(String fareTypeName) {
        FareTypeName = fareTypeName;
    }

    public String getSupplierTypeName() {
        return SupplierTypeName;
    }

    public void setSupplierTypeName(String supplierTypeName) {
        SupplierTypeName = supplierTypeName;
    }

    public boolean isAllowOrder() {
        return IsAllowOrder;
    }

    public void setAllowOrder(boolean allowOrder) {
        IsAllowOrder = allowOrder;
    }

    public Rules getRules() {
        return Rules;
    }

    public void setRules(Rules rules) {
        Rules = rules;
    }
}
