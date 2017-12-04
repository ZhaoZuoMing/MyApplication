package cn.skytrip.train.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/16/016.
 * "rwx_price": 0,
 "end_station_name": "上海虹桥",
 "swz_price": 1748,
 "swz_num": "4",
 "to_station_name": "上海虹桥",
 "ydz_num": "85",
 "yz_num": "--",
 "rw_num": "--",
 "arrive_days": "0",
 "rz_num": "--",
 "access_byidcard": "1",
 "yz_price": 0,
 "ywz_price": 0,
 "sale_date_time": "1230",
 "from_station_code": "VNP",
 "rz_price": 0,
 "gjrw_num": "--",
 "to_station_code": "AOH",
 "ydz_price": 933,
 "wz_price": 0,
 "tdz_price": 0,
 "run_time": "05:39",
 "yw_num": "--",
 "distance": 0,
 "edz_price": 553,
 "qtxb_price": 0,
 "can_buy_now": "Y",
 "yw_price": 0,
 "train_type": "G",
 "rw_price": 0,
 "train_code": "G21",
 "train_no": "2400000G2107",
 "note": "",
 "from_station_name": "北京南",
 "run_time_minute": "339",
 "ywx_price": 0,
 "gjrws_price": 0,
 "arrive_time": "22:39",
 "start_station_name": "北京南",
 "start_time": "17:00",
 "wz_num": "--",
 "edz_num": "0",
 "qtxb_num": "--",
 "train_start_date": "20161215",
 "gjrw_price": 0,
 "tdz_num": "--"
 */

public class TrainObj implements Serializable ,Comparable<TrainObj>{

    private String train_no;//列车号
    private  String train_type;//列车类型
    private  String train_code;//车次
    private String access_byidcard;//是否可凭二代身份证直接进出站
    private String arrive_days;//列车从出发站到达目的站的运行天数 0： 当日到达， 1.次日到达，2：三日到达，3：四日到达，依此类推
    private String sale_date_time;//车票开售时间
    private String can_buy_now;//当前是否可以接受预定（Y:可以，N:不可以）
    private String train_start_date;//列车从始发站出发的日期
    private String start_time;
    private String arrive_time;
    private String run_time;
    private String from_station_name;
    private String to_station_name;
    private String swz_price;//商务座票价
    private String swz_num;

    private String gjrw_num;//高级软卧
    private String gjrw_price;
    private String qtxb_num;
    private String qtxb_price;
    private String rws_num;//软卧（上）
    private String rws_price;
    private String rw_num;//软卧（下）
    private String rw_price;
    private String rwx_price;//软卧下价格
    private String yws_num;//硬卧（上）
    private String yws_price;
    private String yw_num;//硬卧（中）
    private String yw_price;
    private String ywx_num;//硬卧（下）
    private String ywx_price;


    private String rz_num;//软座
    private String rz_price;
    private String yz_num;//硬座
    private String yz_price;
    private String tdz_num;//特等座
    private String tdz_price;
    private String ydz_num;//一等座的余票数量
    private String ydz_price;
    private String edz_num;//二等座
    private String edz_price;
    private String wz_num;//无座
    private String wz_price;

    private String note;//备注起售时间

    public TrainObj() {
    }


    public String getRwx_price() {
        return rwx_price;
    }

    public void setRwx_price(String rwx_price) {
        this.rwx_price = rwx_price;
    }

    public String getTrain_no() {
        return train_no;
    }

    public void setTrain_no(String train_no) {
        this.train_no = train_no;
    }

    public String getTrain_type() {
        return train_type;
    }

    public void setTrain_type(String train_type) {
        this.train_type = train_type;
    }

    public String getTrain_code() {
        return train_code;
    }

    public void setTrain_code(String train_code) {
        this.train_code = train_code;
    }

    public String getAccess_byidcard() {
        return access_byidcard;
    }

    public void setAccess_byidcard(String access_byidcard) {
        this.access_byidcard = access_byidcard;
    }

    public String getArrive_days() {
        return arrive_days;
    }

    public void setArrive_days(String arrive_days) {
        this.arrive_days = arrive_days;
    }

    public String getSale_date_time() {
        return sale_date_time;
    }

    public void setSale_date_time(String sale_date_time) {
        this.sale_date_time = sale_date_time;
    }

    public String getCan_buy_now() {
        return can_buy_now;
    }

    public void setCan_buy_now(String can_buy_now) {
        this.can_buy_now = can_buy_now;
    }

    public String getTrain_start_date() {
        return train_start_date;
    }

    public void setTrain_start_date(String train_start_date) {
        this.train_start_date = train_start_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getRun_time() {
        return run_time;
    }

    public void setRun_time(String run_time) {
        this.run_time = run_time;
    }

    public String getFrom_station_name() {
        return from_station_name;
    }

    public void setFrom_station_name(String from_station_name) {
        this.from_station_name = from_station_name;
    }

    public String getTo_station_name() {
        return to_station_name;
    }

    public void setTo_station_name(String to_station_name) {
        this.to_station_name = to_station_name;
    }

    public String getSwz_price() {
        return swz_price;
    }

    public void setSwz_price(String swz_price) {
        this.swz_price = swz_price;
    }

    public String getSwz_num() {
        return swz_num;
    }

    public void setSwz_num(String swz_num) {
        this.swz_num = swz_num;
    }

    public String getGjrw_num() {
        return gjrw_num;
    }

    public void setGjrw_num(String gjrw_num) {
        this.gjrw_num = gjrw_num;
    }

    public String getGjrw_price() {
        return gjrw_price;
    }

    public void setGjrw_price(String gjrw_price) {
        this.gjrw_price = gjrw_price;
    }

    public String getQtxb_num() {
        return qtxb_num;
    }

    public void setQtxb_num(String qtxb_num) {
        this.qtxb_num = qtxb_num;
    }

    public String getQtxb_price() {
        return qtxb_price;
    }

    public void setQtxb_price(String qtxb_price) {
        this.qtxb_price = qtxb_price;
    }

    public String getRws_num() {
        return rws_num;
    }

    public void setRws_num(String rws_num) {
        this.rws_num = rws_num;
    }

    public String getRws_price() {
        return rws_price;
    }

    public void setRws_price(String rws_price) {
        this.rws_price = rws_price;
    }

    public String getRw_num() {
        return rw_num;
    }

    public void setRw_num(String rw_num) {
        this.rw_num = rw_num;
    }

    public String getRw_price() {
        return rw_price;
    }

    public void setRw_price(String rw_price) {
        this.rw_price = rw_price;
    }

    public String getYws_num() {
        return yws_num;
    }

    public void setYws_num(String yws_num) {
        this.yws_num = yws_num;
    }

    public String getYws_price() {
        return yws_price;
    }

    public void setYws_price(String yws_price) {
        this.yws_price = yws_price;
    }

    public String getYw_num() {
        return yw_num;
    }

    public void setYw_num(String yw_num) {
        this.yw_num = yw_num;
    }

    public String getYw_price() {
        return yw_price;
    }

    public void setYw_price(String yw_price) {
        this.yw_price = yw_price;
    }

    public String getYwx_num() {
        return ywx_num;
    }

    public void setYwx_num(String ywx_num) {
        this.ywx_num = ywx_num;
    }

    public String getYwx_price() {
        return ywx_price;
    }

    public void setYwx_price(String ywx_price) {
        this.ywx_price = ywx_price;
    }

    public String getRz_num() {
        return rz_num;
    }

    public void setRz_num(String rz_num) {
        this.rz_num = rz_num;
    }

    public String getRz_price() {
        return rz_price;
    }

    public void setRz_price(String rz_price) {
        this.rz_price = rz_price;
    }

    public String getYz_num() {
        return yz_num;
    }

    public void setYz_num(String yz_num) {
        this.yz_num = yz_num;
    }

    public String getYz_price() {
        return yz_price;
    }

    public void setYz_price(String yz_price) {
        this.yz_price = yz_price;
    }

    public String getTdz_num() {
        return tdz_num;
    }

    public void setTdz_num(String tdz_num) {
        this.tdz_num = tdz_num;
    }

    public String getTdz_price() {
        return tdz_price;
    }

    public void setTdz_price(String tdz_price) {
        this.tdz_price = tdz_price;
    }

    public String getYdz_num() {
        return ydz_num;
    }

    public void setYdz_num(String ydz_num) {
        this.ydz_num = ydz_num;
    }

    public String getYdz_price() {
        return ydz_price;
    }

    public void setYdz_price(String ydz_price) {
        this.ydz_price = ydz_price;
    }

    public String getEdz_num() {
        return edz_num;
    }

    public void setEdz_num(String edz_num) {
        this.edz_num = edz_num;
    }

    public String getEdz_price() {
        return edz_price;
    }

    public void setEdz_price(String edz_price) {
        this.edz_price = edz_price;
    }

    public String getWz_num() {
        return wz_num;
    }

    public void setWz_num(String wz_num) {
        this.wz_num = wz_num;
    }

    public String getWz_price() {
        return wz_price;
    }

    public void setWz_price(String wz_price) {
        this.wz_price = wz_price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 按出发时间从早到晚排序
     * @param another
     * @return
     */
    @Override
    public int compareTo(TrainObj another) {
        int thisT = Integer.parseInt(this.start_time.substring(0,2));
        int otherT = Integer.parseInt(another.getStart_time().substring(0,2));
        return thisT-otherT;
    }

    @Override
    public String toString() {
        return "TrainObj{" +
                "train_no='" + train_no + '\'' +
                ", train_type='" + train_type + '\'' +
                ", train_code='" + train_code + '\'' +
                ", access_byidcard='" + access_byidcard + '\'' +
                ", arrive_days='" + arrive_days + '\'' +
                ", sale_date_time='" + sale_date_time + '\'' +
                ", can_buy_now='" + can_buy_now + '\'' +
                ", train_start_date='" + train_start_date + '\'' +
                ", start_time='" + start_time + '\'' +
                ", arrive_time='" + arrive_time + '\'' +
                ", run_time='" + run_time + '\'' +
                ", from_station_name='" + from_station_name + '\'' +
                ", to_station_name='" + to_station_name + '\'' +
                ", swz_price='" + swz_price + '\'' +
                ", swz_num='" + swz_num + '\'' +
                ", gjrw_num='" + gjrw_num + '\'' +
                ", gjrw_price='" + gjrw_price + '\'' +
                ", qtxb_num='" + qtxb_num + '\'' +
                ", qtxb_price='" + qtxb_price + '\'' +
                ", rws_num='" + rws_num + '\'' +
                ", rws_price='" + rws_price + '\'' +
                ", rw_num='" + rw_num + '\'' +
                ", rw_price='" + rw_price + '\'' +
                ", rwx_price='" + rwx_price + '\'' +
                ", yws_num='" + yws_num + '\'' +
                ", yws_price='" + yws_price + '\'' +
                ", yw_num='" + yw_num + '\'' +
                ", yw_price='" + yw_price + '\'' +
                ", ywx_num='" + ywx_num + '\'' +
                ", ywx_price='" + ywx_price + '\'' +
                ", rz_num='" + rz_num + '\'' +
                ", rz_price='" + rz_price + '\'' +
                ", yz_num='" + yz_num + '\'' +
                ", yz_price='" + yz_price + '\'' +
                ", tdz_num='" + tdz_num + '\'' +
                ", tdz_price='" + tdz_price + '\'' +
                ", ydz_num='" + ydz_num + '\'' +
                ", ydz_price='" + ydz_price + '\'' +
                ", edz_num='" + edz_num + '\'' +
                ", edz_price='" + edz_price + '\'' +
                ", wz_num='" + wz_num + '\'' +
                ", wz_price='" + wz_price + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
