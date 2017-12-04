package f.sky.flight.core;

import android.util.Log;

import com.changJson.API;
import com.google.gson.Gson;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.ArrayList;
import java.util.List;

import f.sky.flight.model.BookDto;
import f.sky.flight.model.Credential;
import f.sky.flight.model.Credentials;
import f.sky.flight.model.FlightBookSegment;
import f.sky.flight.model.FlightSegment;
import f.sky.flight.model.OrderLinkmanDto;
import f.sky.flight.model.PassengerDto;
import f.sky.flight.model.VariableDto;

/**
 * Created by zhaody on 2017/11/27.
 * 酒店订单提交
 */

public class CommitFlightService {
    public static  String commitFligtMethed(Credential credential, FlightSegment segment,String ticket){
        String url = API.ORDER_FLIGHT+"?Data="+bookData(credential,segment)+"&ticket="+ticket;
        try {
            Log.e("订单接口--", "commitFligtMethed: "+url );
            PostMethod postMethod = new PostMethod(url);
            HttpClient httpClient = new HttpClient();
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode== 200){
                String response = postMethod.getResponseBodyAsString();
                Log.e("===", "----机票预订返回的数据---： "+response);
                return  response ;
            }

        }catch (Exception e){
            Log.e("===", "机票预订：commitFlightOrder: "+e.getMessage() );
        }
      return null;
    }

    /**
     * 提交的Json
     * @param credential
     * @param segment
     * @return
     */
    private static  String bookData(Credential credential, FlightSegment segment){
        List<OrderLinkmanDto> Linkmans = new ArrayList<>();//联系人
        Linkmans.add(new OrderLinkmanDto(credential.getCheckName(),credential.getNumber(),"dongyang.zhao@aliyun.com"));
        List<PassengerDto> Passengers = new ArrayList<>();
        //-------订单信息对象---1；
        PassengerDto passengerDto = new PassengerDto();
        //设置乘客证件信息
        passengerDto.setCredentials( setCredentials(credential));
        //设置提交仓位信息
        passengerDto.setFlightCabin(segment.getCabin());
        //设置选定航段
        passengerDto.setFlightSegment(setNewSeg(segment));
        passengerDto.setCostCenterCode("11100011100");
        passengerDto.setCostCenterName("东美在线技术部");
        passengerDto.setTravelPayType(1);
        passengerDto.setTravelType(1);
        Passengers.add(passengerDto);
        BookDto dto = new BookDto(API.CHANNEL,Linkmans,Passengers);
        Gson gson = new Gson();
        String orderData = gson.toJson(dto);
        System.out.println("预订转化Json=====："+orderData);
        return orderData;
    }

    /**
     * 设置联系人信息
     * @param credential
     * @return
     */
    private static Credentials setCredentials(Credential credential) {
       return new Credentials(1,credential.getNumber(),credential.getFirstName(),credential.getLastName(),
                credential.getGender(),credential.getExpirationDate(),credential.getCountry(),credential.getIssueCountry(), credential.getBirthday());
    }

    /**
     * 航段
     * @param flightSegment
     * @return
     */
    private static  FlightBookSegment setNewSeg(FlightSegment flightSegment) {
        FlightBookSegment bookSeg = new FlightBookSegment();
        bookSeg.setAirline(flightSegment.getAirline());
        bookSeg.setAirlineName(flightSegment.getAirlineName());
        bookSeg.setArrivalTime(flightSegment.getArrivalTime());
        bookSeg.setCarrier(flightSegment.getCarrier());
        bookSeg.setCarrierName(flightSegment.getCarrierName());
        bookSeg.setNumber(flightSegment.getNumber());
        bookSeg.setCodeShareNumber(flightSegment.getCodeShareNumber());
        bookSeg.setFromCity(flightSegment.getFromCity());
        bookSeg.setFromCityName(flightSegment.getFromCityName());
        bookSeg.setToCity(flightSegment.getToCity());
        bookSeg.setToCityName(flightSegment.getToCityName());
        bookSeg.setFromAirport(flightSegment.getFromAirport());
        bookSeg.setFromAirportName(flightSegment.getFromAirportName());
        bookSeg.setFromTerminal(flightSegment.getFromTerminal());
        bookSeg.setToTerminal(flightSegment.getToTerminal());
        bookSeg.setToAirport(flightSegment.getToAirport());
        bookSeg.setToAirportName(flightSegment.getToAirportName());
        bookSeg.setTakeoffTime(flightSegment.getTakeoffTime());
        bookSeg.setArrivalTime(flightSegment.getArrivalTime());
        bookSeg.setPlaneType(flightSegment.getPlaneType());
        bookSeg.setDistance(flightSegment.getDistance());
        bookSeg.setFlyTime(flightSegment.getFlyTime());
        bookSeg.setFlyTimeName(flightSegment.getFlyTimeName());
        bookSeg.setStopCities(flightSegment.getStopCities());
        //BigDecimal b1 = new BigDecimal(Double.toString(0.48));
        //BigDecimal b2 = BigDecimal.valueOf(0.48);
        bookSeg.setYFare(flightSegment.getYFare());
        bookSeg.setCFare(flightSegment.getCFare());
        bookSeg.setFFare(flightSegment.getFFare());
        bookSeg.setTax( flightSegment.getTax());
        bookSeg.setLowestFare(flightSegment.getLowestFare());
        bookSeg.setLowestDiscount( flightSegment.getLowestDiscount());
        bookSeg.setLowestCabinCode(flightSegment.getLowestCabinCode());
        VariableDto variableDto = new VariableDto();
        variableDto.setFlightKey(flightSegment.getVariables().getFlightKey());
        variableDto.setFlightRph(flightSegment.getVariables().getFlightRph());
        bookSeg.setVariables(variableDto);
        return bookSeg;
    }
}
