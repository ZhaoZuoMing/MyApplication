package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/3/20/020.
 */

public class CardData {
    private String cardNumber;
    private String cardCVV;
    private String horderName;
    private int year;
    private int month;
    private String IDType;
    private String IDNumber;
    private boolean isNeedRoule;//是否需要担保

    public boolean isNeedRoule() {
        return isNeedRoule;
    }

    public void setNeedRoule(boolean needRoule) {
        isNeedRoule = needRoule;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public String getHorderName() {
        return horderName;
    }

    public void setHorderName(String horderName) {
        this.horderName = horderName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getIDType() {
        return IDType;
    }

    public void setIDType(String IDType) {
        this.IDType = IDType;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }
}
