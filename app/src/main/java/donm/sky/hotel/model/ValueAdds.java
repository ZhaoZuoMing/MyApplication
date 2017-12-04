package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/2/8/008.
 * 附加服务
 */

public class ValueAdds {
    /**
     *  <Description>附加服务：单加1份早餐 50 元</Description>
     <TypeCode>01</TypeCode>
     <IsInclude>false</IsInclude>
     <Amount>0</Amount>
     <CurrencyCode>RMB</CurrencyCode>
     <PriceOption>Money</PriceOption>
     <Price>0.0</Price>
     <IsExtAdd>true</IsExtAdd>
     <ExtOption>Money</ExtOption>
     <ExtPrice>50.0</ExtPrice>
     <StartDate>0001-01-01T00:00:00</StartDate>
     <EndDate>0001-01-01T00:00:00</EndDate>
     <ValueAddId>0_01_324416</ValueAddId>
     */
    private String Description;
    private String TypeCode;
    private boolean IsInclude;
    private int Amount;
    private String CurrencyCode;
    private String PriceOption;
    private String Price;
    private boolean IsExtAdd;
    private String ExtOption;
    private String ExtPrice;
    private String StartDate;
    private String EndDate;
    private String ValueAddId;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(String typeCode) {
        TypeCode = typeCode;
    }

    public boolean getIsInclude() {
        return IsInclude;
    }

    public void setIsInclude(boolean isInclude) {
        IsInclude = isInclude;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
    }

    public String getPriceOption() {
        return PriceOption;
    }

    public void setPriceOption(String priceOption) {
        PriceOption = priceOption;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String  price) {
        Price = price;
    }

    public boolean getIsExtAdd() {
        return IsExtAdd;
    }

    public void setIsExtAdd(boolean isExtAdd) {
        IsExtAdd = isExtAdd;
    }

    public String getExtOption() {
        return ExtOption;
    }

    public void setExtOption(String extOption) {
        ExtOption = extOption;
    }

    public String getExtPrice() {
        return ExtPrice;
    }

    public void setExtPrice(String extPrice) {
        ExtPrice = extPrice;
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

    public String getValueAddId() {
        return ValueAddId;
    }

    public void setValueAddId(String valueAddId) {
        ValueAddId = valueAddId;
    }
}
