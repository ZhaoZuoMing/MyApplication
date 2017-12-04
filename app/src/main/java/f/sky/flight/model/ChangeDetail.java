package f.sky.flight.model;

/**
 * Created by zhaody on 2017/11/15.
 */

public class ChangeDetail {
    private  String Befores[];
    private String BeforeEns[];
    private String Afters[];
    private String AfterEns[];

    public String[] getBefores() {
        return Befores;
    }

    public void setBefores(String[] befores) {
        Befores = befores;
    }

    public String[] getBeforeEns() {
        return BeforeEns;
    }

    public void setBeforeEns(String[] beforeEns) {
        BeforeEns = beforeEns;
    }

    public String[] getAfters() {
        return Afters;
    }

    public void setAfters(String[] afters) {
        Afters = afters;
    }

    public String[] getAfterEns() {
        return AfterEns;
    }

    public void setAfterEns(String[] afterEns) {
        AfterEns = afterEns;
    }
}
