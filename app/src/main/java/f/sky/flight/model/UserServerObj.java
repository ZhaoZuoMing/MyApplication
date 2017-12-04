package f.sky.flight.model;

import java.util.Date;

/**
 * Created by Administrator on 2016/11/24/024.
 */

public class UserServerObj {

    private Date EffectDate;//<EffectDate>dateTime</EffectDate>
    private int DMBSUserID;//<DMBSUserID>int</DMBSUserID>
    private String UserCode;//<UserCode>string</UserCode>
    private String IDNumber;//<IDNumber>string</IDNumber>
    private int IDType;//<IDType>int</IDType>
    private String ThirdDiscount;//<ThirdDiscount>string</ThirdDiscount>
    private String UserName;//<UserName>string</UserName>
    private int Id;//<ID>int</ID>
    private int UserMinDiscount;//<UserMinDiscount>int</UserMinDiscount>
    private String Password;//<Password>string</Password>
    private String Project;//<Project>string</Project>
    private String RealName;//<RealName>string</RealName>
    private String Mail;//<Mail>string</Mail>
    private String Mobile;//<Mobile>string</Mobile>
    private String Phone;//<Phone>string</Phone>
    private int AllowMobileMessage;//<AllowMobileMessage>int</AllowMobileMessage>
    private int AllowMailMessage;//<AllowMailMessage>int</AllowMailMessage>
    private String Address;//<Address>string</Address>
    private String ZipCode;//<ZipCode>string</ZipCode>
    private int UserDepID;//这里返回为空 <UserDepID>int</UserDepID>
    private String Intro;// <Intro>string</Intro>
    private int Status;// <Status>int</Status>
    private Date CreateDate;// <CreateDate>dateTime</CreateDate>
    private int LoginTimes;// <LoginTimes>int</LoginTimes>
    private int RegType;// <RegType>int</RegType>
    private String RegEncode;// <RegEncode>string</RegEncode>
    private int AuditUser;// <AuditUser>int</AuditUser>
    private Date AuditDate;// <AuditDate>dateTime</AuditDate>
    private int SortIndex;// <SortIndex>int</SortIndex>
    private int Position;// <Position>int</Position>
    private String Nick;//<Nick>张俊</Nick>

    private UserDeptObj userDeptObj;//部门Obj
    private UserDeptObj userOrgObj;//公司Obj
    private UserRoleObj userRoleObj;

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    /**
     * 跟据权限代码判断是否秘书账号
     * @return
     */
    public boolean isTicketApplyForOther(){
        return userRoleObj.getRightCodes().indexOf("|TICKET_APPLY_FOR_OTHER|") != -1 || userRoleObj.getRightCodes().indexOf("|TICKET_APPLY_FOR_OTHERALL|") != -1;
    }

    public UserServerObj() {
    }

    public Date getEffectDate() {
        return EffectDate;
    }

    public void setEffectDate(Date effectDate) {
        EffectDate = effectDate;
    }

    public int getDMBSUserID() {
        return DMBSUserID;
    }

    public void setDMBSUserID(int dMBSUserID) {
        DMBSUserID = dMBSUserID;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String userCode) {
        UserCode = userCode;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String iDNumber) {
        IDNumber = iDNumber;
    }

    public int getIDType() {
        return IDType;
    }

    public void setIDType(int iDType) {
        IDType = iDType;
    }

    public String getThirdDiscount() {
        return ThirdDiscount;
    }

    public void setThirdDiscount(String thirdDiscount) {
        ThirdDiscount = thirdDiscount;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserMinDiscount() {
        return UserMinDiscount;
    }

    public void setUserMinDiscount(int userMinDiscount) {
        UserMinDiscount = userMinDiscount;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getProject() {
        return Project;
    }

    public void setProject(String project) {
        Project = project;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getAllowMobileMessage() {
        return AllowMobileMessage;
    }

    public void setAllowMobileMessage(int allowMobileMessage) {
        AllowMobileMessage = allowMobileMessage;
    }

    public int getAllowMailMessage() {
        return AllowMailMessage;
    }

    public void setAllowMailMessage(int allowMailMessage) {
        AllowMailMessage = allowMailMessage;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public int getUserDepID() {
        return UserDepID;
    }

    public void setUserDepID(int userDepID) {
        UserDepID = userDepID;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public int getLoginTimes() {
        return LoginTimes;
    }

    public void setLoginTimes(int loginTimes) {
        LoginTimes = loginTimes;
    }

    public int getRegType() {
        return RegType;
    }

    public void setRegType(int regType) {
        RegType = regType;
    }

    public String getRegEncode() {
        return RegEncode;
    }

    public void setRegEncode(String regEncode) {
        RegEncode = regEncode;
    }

    public int getAuditUser() {
        return AuditUser;
    }

    public void setAuditUser(int auditUser) {
        AuditUser = auditUser;
    }

    public Date getAuditDate() {
        return AuditDate;
    }

    public void setAuditDate(Date auditDate) {
        AuditDate = auditDate;
    }

    public int getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(int sortIndex) {
        SortIndex = sortIndex;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int position) {
        Position = position;
    }

    public UserDeptObj getUserDeptObj() {
        return userDeptObj;
    }

    public void setUserDeptObj(UserDeptObj userDeptObj) {
        this.userDeptObj = userDeptObj;
    }

    public UserDeptObj getUserOrgObj() {
        return userOrgObj;
    }

    public void setUserOrgObj(UserDeptObj userOrgObj) {
        this.userOrgObj = userOrgObj;
    }

    public UserRoleObj getUserRoleObj() {
        return userRoleObj;
    }

    public void setUserRoleObj(UserRoleObj userRoleObj) {
        this.userRoleObj = userRoleObj;
    }


}
