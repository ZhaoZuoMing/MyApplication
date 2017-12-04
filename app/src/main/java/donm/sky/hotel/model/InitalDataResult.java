package donm.sky.hotel.model;

import java.util.List;

import f.sky.flight.model.ServerResObj;

/**
 * Created by Administrator on 2017/1/6/006.
 * 酒店静态数据初始化
 *
 */

public class InitalDataResult extends ServerResObj {

    private List<Cities> citiesList;
    public InitalDataResult() {
    }

    public List<Cities> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<Cities> citiesList) {
        this.citiesList = citiesList;
    }

}
