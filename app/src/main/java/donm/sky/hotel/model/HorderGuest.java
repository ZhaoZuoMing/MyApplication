package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/3/29/029.
 * 入住人信息
 *   <Id>16618</Id>
 <Orderid>25571</Orderid>
 <Name>何磊</Name>
 <IdType>0</IdType>
 <IdNumber />
 <Mobile />
 <IsDeleted>false</IsDeleted>
 <IsChanged>true</IsChanged>
 */

public class HorderGuest {
    private int id;
    private String Orderid;
    private String Name;
    private int IdType;
    private String IdNumber;
    private String Mobile;
    private boolean IsDeleted;
    private boolean IsChanged;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getIdType() {
        return IdType;
    }

    public void setIdType(int idType) {
        IdType = idType;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    public boolean isChanged() {
        return IsChanged;
    }

    public void setChanged(boolean changed) {
        IsChanged = changed;
    }
}
