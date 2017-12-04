package com.changJson;

/**
 * Created by zhaody on 2017/11/1.
 */

public interface API {
     String TOKEN= "D42E7EDE1B7540AAA23E0599E234C406";
    /**http://test.api.sky-trip.com/account/login?&name=T2G00210&password=T2G00210&token=D42E7EDE1B7540AAA23E0599E234C406
    http://test.api.sky-trip.com/account/login?data={"Name":"T2G00210","Password":"T2G00210"}&timestamp=1511236498000&sign=6ovd3eev9g99fju3p2f63l9k97&taken=D42E7EDE1B7540AAA23E0599E234C406
     http://test.flight.api.tmc.sky-trip.com/home/detail?date=2017-11-24&fromCode=SHA&toCode=PEK&ticket=cf677db488a14ec89490ef528f05ddbe
     *
     */
     String LOGIN_URL= "http://test.api.sky-trip.com/account/login?";
     String SEARCH_FLIGHT_LIST = "http://test.flight.api.tmc.sky-trip.com/home/detail?date=";//tmc-ct
     //航班基础数据接口
     String AIRPROT_DETAIL = "http://test.api.sky-trip.com/Resource/Airport?lastUpdateTime=0&token=D42E7EDE1B7540AAA23E0599E234C406";
     //获取登记人信息
     String GETUER_MESSAGE = "http://test.api.tmc.sky-trip.com/Account/Credentials?ticket=";
     String ORDER_FLIGHT = "http://test.flight.api.tmc.sky-trip.com/home/book";
     //获取火车票订单
    String SEARCH_TRAIN_API = "http://test.train.api.tmc.sky-trip.com/home/Search?date=2017-12-30&fromStation=VAP&toStation=AOH&ticket=3b635cb31b354f41b99d49be1979de1b";
     //存User对象
     String TICKET = "TICKET";
     String ID = "ID";
     String NAME = "NAME";
     String TMCID ="TMCID";
     String HRID = "HRID";
     String CMSID = "CMSID";
     String CREDENTIAL = "credential";
     String CHANNEL = "Android";
     int COMMIT_FLIGHT = 1;
}
