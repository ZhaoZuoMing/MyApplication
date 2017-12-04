package f.sky.flight.model;

/**
 * Created by zhaody on 2017/11/27.
 */

public class OrderLinkmanDto {
    private String Name;
    private String Mobile;
    private String Email;

    public OrderLinkmanDto(String name, String mobile, String email) {
        Name = name;
        Mobile = mobile;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
