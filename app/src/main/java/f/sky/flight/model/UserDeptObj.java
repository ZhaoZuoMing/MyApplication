package f.sky.flight.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class UserDeptObj {

    private int ID;// <ID>int</ID>
    private int AllowTicketDirect;// <AllowTicketDirect>int</AllowTicketDirect>
    private int ForeDay;// <ForeDay>int</ForeDay>
    private int AllowPayOnline;// <AllowPayOnline>int</AllowPayOnline>
    private String CostCenter;// <CostCenter>string</CostCenter>
    private String MailAddress;// <MailAddress>string</MailAddress>
    private int ClientID;// <ClientID>int</ClientID>
    private int LowestPriceDuration;// <LowestPriceDuration>int</LowestPriceDuration>
    private String ClientCode;// <ClientCode>string</ClientCode>
    private String DepEName;// <DepEName>string</DepEName>
    private String ServiceInfo;// <ServiceInfo>string</ServiceInfo>
    private String ServiceEInfo;// <ServiceEInfo>string</ServiceEInfo>
    private String DepName;// <DepName>string</DepName>
    // <ReasonID xsi:nil="true" />
    // <ReasonCode xsi:nil="true" />
    private String DepFullName;// <DepFullName>string</DepFullName>
    private String AirLineDiscount;// <AirLineDiscount>string</AirLineDiscount>
    private int Status;// <Status>int</Status>
    private int Parent;// <Parent>int</Parent>
    private int SortIndex;// <SortIndex>int</SortIndex>
    private int CorpID;// <CorpID>int</CorpID>
    private String intro;// <intro>string</intro>
    private String LinkAddress;// <LinkAddress>string</LinkAddress>
    private String LinkPhone;// <LinkPhone>string</LinkPhone>
    private String LinkMan;// <LinkMan>string</LinkMan>
    private String DepType;// <DepType>string</DepType>
    private int tmpUserID;// <tmpUserID>int</tmpUserID>
    private List<String> DeliverAddress = new ArrayList<String>();//送票地址
    private List<String> DeliverAddressE = new ArrayList<String>();//送票地址

    public List<String> getDeliverAddressE() {
        return DeliverAddressE;
    }

    public void setDeliverAddressE(List<String> deliverAddressE) {
        DeliverAddressE = deliverAddressE;
    }

    private Map<String, String> configsMap = new HashMap<String, String>();

    public List<String> getDeliverAddress() {
        return DeliverAddress;
    }

    public void setDeliverAddress(List<String> deliverAddress) {
        DeliverAddress = deliverAddress;
    }

    public Map<String, String> getConfigsMap() {
        return configsMap;
    }

    public void setConfigsMap(Map<String, String> configsMap) {
        this.configsMap = configsMap;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getAllowTicketDirect() {
        return AllowTicketDirect;
    }

    public void setAllowTicketDirect(int allowTicketDirect) {
        AllowTicketDirect = allowTicketDirect;
    }

    public int getForeDay() {
        return ForeDay;
    }

    public void setForeDay(int foreDay) {
        ForeDay = foreDay;
    }

    public int getAllowPayOnline() {
        return AllowPayOnline;
    }

    public void setAllowPayOnline(int allowPayOnline) {
        AllowPayOnline = allowPayOnline;
    }

    public String getCostCenter() {
        return CostCenter;
    }

    public void setCostCenter(String costCenter) {
        CostCenter = costCenter;
    }

    public String getMailAddress() {
        return MailAddress;
    }

    public void setMailAddress(String mailAddress) {
        MailAddress = mailAddress;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }

    public int getLowestPriceDuration() {
        return LowestPriceDuration;
    }

    public void setLowestPriceDuration(int lowestPriceDuration) {
        LowestPriceDuration = lowestPriceDuration;
    }

    public String getClientCode() {
        return ClientCode;
    }

    public void setClientCode(String clientCode) {
        ClientCode = clientCode;
    }

    public String getDepEName() {
        return DepEName;
    }

    public void setDepEName(String depEName) {
        DepEName = depEName;
    }

    public String getServiceInfo() {
        return ServiceInfo;
    }

    public void setServiceInfo(String serviceInfo) {
        ServiceInfo = serviceInfo;
    }

    public String getServiceEInfo() {
        return ServiceEInfo;
    }

    public void setServiceEInfo(String serviceEInfo) {
        ServiceEInfo = serviceEInfo;
    }

    public String getDepName() {
        return DepName;
    }

    public void setDepName(String depName) {
        DepName = depName;
    }

    public String getDepFullName() {
        return DepFullName;
    }

    public void setDepFullName(String depFullName) {
        DepFullName = depFullName;
    }

    public String getAirLineDiscount() {
        return AirLineDiscount;
    }

    public void setAirLineDiscount(String airLineDiscount) {
        AirLineDiscount = airLineDiscount;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getParent() {
        return Parent;
    }

    public void setParent(int parent) {
        Parent = parent;
    }

    public int getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(int sortIndex) {
        SortIndex = sortIndex;
    }

    public int getCorpID() {
        return CorpID;
    }

    public void setCorpID(int corpID) {
        CorpID = corpID;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getLinkAddress() {
        return LinkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        LinkAddress = linkAddress;
    }

    public String getLinkPhone() {
        return LinkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        LinkPhone = linkPhone;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getDepType() {
        return DepType;
    }

    public void setDepType(String depType) {
        DepType = depType;
    }

    public int getTmpUserID() {
        return tmpUserID;
    }

    public void setTmpUserID(int tmpUserID) {
        this.tmpUserID = tmpUserID;
    }


}
