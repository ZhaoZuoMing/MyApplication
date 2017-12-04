package donm.sky.hotel.model;

/**
 * Created by Administrator on 2017/3/29/029.
 */

public class HorderContact {
    /**
     * <ColFkHordercoReferenceHorder>
     <HorderContact>
     <Id>15572</Id>
     <Orderid>25571</Orderid>
     <ContactType>0</ContactType>
     <ContactPersonName>黄阳</ContactPersonName>
     <PhoneNumber>13301890357</PhoneNumber>
     <PhoneTechType />
     <Email>zhaozm@sky-trip.com</Email>
     <ConfirmType>0</ConfirmType>
     <IsDeleted>false</IsDeleted>
     <IsChanged>true</IsChanged>
     </HorderContact>
     </ColFkHordercoReferenceHorder>
     */

    private String Id;
    private String Orderid;
    private String ContactType;
    private String ContactPersonName;
    private String PhoneNumber;
    private String PhoneTechType;
    private String Email;
    private String ConfirmType;
    private boolean IsDeleted;
    private boolean IsChanged;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
    }

    public String getContactType() {
        return ContactType;
    }

    public void setContactType(String contactType) {
        ContactType = contactType;
    }

    public String getContactPersonName() {
        return ContactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        ContactPersonName = contactPersonName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPhoneTechType() {
        return PhoneTechType;
    }

    public void setPhoneTechType(String phoneTechType) {
        PhoneTechType = phoneTechType;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getConfirmType() {
        return ConfirmType;
    }

    public void setConfirmType(String confirmType) {
        ConfirmType = confirmType;
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
