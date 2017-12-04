package com.mymodels;

import f.sky.flight.model.ServerResObj;

/**
 * Created by zhaody on 2017/11/1.
 */

public class UserMsg extends ServerResObj{
    private  String Timestamp;
    private  String Ticket;
    private  String Id;
    private  String Name;
    private  String TmcId;
    private  String HrId;
    private  String CmsId;
    private String credential;

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTmcId() {
        return TmcId;
    }

    public void setTmcId(String tmcId) {
        TmcId = tmcId;
    }

    public String getHrId() {
        return HrId;
    }

    public void setHrId(String hrId) {
        HrId = hrId;
    }

    public String getCmsId() {
        return CmsId;
    }

    public void setCmsId(String cmsId) {
        CmsId = cmsId;
    }
}
