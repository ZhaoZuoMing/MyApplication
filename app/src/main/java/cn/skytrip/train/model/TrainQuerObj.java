package cn.skytrip.train.model;

import java.util.List;

/**
 * Created by Administrator on 2016/12/16/016.
 *   "success": true,
 "code": 200,
 "searchkey": "BXPAOH2016-12-15",
 "msg": "正常获得结果",
 */

public class TrainQuerObj {

    private boolean success;
    private int code;
    private String searchKey;
    private String msg;
    private List<TrainObj> train_list;

    public TrainQuerObj() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<TrainObj> getTrain_list() {
        return train_list;
    }

    public void setTrain_list(List<TrainObj> train_list) {
        this.train_list = train_list;
    }
}
