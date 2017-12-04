package f.sky.flight.model;

/**
 * Created by zhaody on 2017/11/27.
 * 提交预订人信息
 */

public class Credentials {
  private  int Type;
  private  String Number;
  private  String  FirstName;
  private  String  LastName;
  private  String  Gender;
  private  String ExpirationDate;
  private String Country;
  private  String  IssueCountry;
  private String  Birthday;

    public Credentials(int type, String number, String firstName, String lastName, String gender, String expirationDate, String country, String issueCountry, String birthday) {
        Type = type;
        Number = number;
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        ExpirationDate = expirationDate;
        Country = country;
        IssueCountry = issueCountry;
        Birthday = birthday;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        ExpirationDate = expirationDate;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getIssueCountry() {
        return IssueCountry;
    }

    public void setIssueCountry(String issueCountry) {
        IssueCountry = issueCountry;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }
}
