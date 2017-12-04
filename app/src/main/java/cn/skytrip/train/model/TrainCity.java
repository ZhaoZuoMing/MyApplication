package cn.skytrip.train.model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13/013.
 */

public class TrainCity {

    private List<TrainHot> hotList;
    private List<TraintStation> stationList;

    public TrainCity() {
    }

    public TrainCity(List<TrainHot> hotList, List<TraintStation> stationList) {
        this.hotList = hotList;
        this.stationList = stationList;
    }

    public List<TrainHot> getHotList() {
        return hotList;
    }

    public void setHotList(List<TrainHot> hotList) {
        this.hotList = hotList;
    }

    public List<TraintStation> getStationList() {
        return stationList;
    }

    public void setStationList(List<TraintStation> stationList) {
        this.stationList = stationList;
    }
}
