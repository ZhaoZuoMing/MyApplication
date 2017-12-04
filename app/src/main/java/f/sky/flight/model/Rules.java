package f.sky.flight.model;

/**
 * Created by zhaody on 2017/12/1.
 */

public class Rules {
    private String FlightIsMustBookLowestPrice;//违反必须预定经济舱最低价格政策"
    private  String FlightLowerFare;//违反出发时间前后120分钟内最低价航班的政策"
    private String FlightDiscountLimit;//违反10折及以下航班的政策

    public String getFlightDiscountLimit() {
        return FlightDiscountLimit;
    }

    public void setFlightDiscountLimit(String flightDiscountLimit) {
        FlightDiscountLimit = flightDiscountLimit;
    }

    public String getFlightIsMustBookLowestPrice() {
        return FlightIsMustBookLowestPrice;
    }

    public void setFlightIsMustBookLowestPrice(String flightIsMustBookLowestPrice) {
        FlightIsMustBookLowestPrice = flightIsMustBookLowestPrice;
    }

    public String getFlightLowerFare() {
        return FlightLowerFare;
    }

    public void setFlightLowerFare(String flightLowerFare) {
        FlightLowerFare = flightLowerFare;
    }
}
