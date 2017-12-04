package f.sky.flight.core;

/**
 * Created by Administrator on 2016/11/23/023.
 */

public interface Constants {

    /*存储文件的字段*/
    String SAVE_AIRPORT = "SAVE_AIRPORT";
    String SAVE_AIRPORT_KEY = "airport";
    String SAVE_AIRLINES = "SAVE_AIRLINES";
    String SAVE_AIRLINES_KEY = "airline";
    String SAVE_PLANSTYLE = "SAVE_PLANSTYLE";
    String SAVE_PLANSTYLE_KEY = "planystyle";


    //关于时间类传递时的固定值
    String SELECT_DATA_TIME = "SELETE_DATA_TIME";
    String SELECT_TIME = "selete_time";
    //当用户没有选择时间的返回的Code
    int NO_SELECT_T = -12;
    int CHOSE_T =12;

    String FLIGHT_TAG = "flight";


    String SHARE_PREFER = "SHARE_PREFER";

    int MSG_TYPE_DIALOG = 0;
    int CONFIRM_TYPE_DIALOG = 1;
    int ALERT_TYPE_DIALOG = 2;

    String SEARCH_FLIGHT_CITY = "SEARCH_FLIGHT_CITY";
    int SEARCH_FLIGHT_ORG_CITY = 0;
    int SEARCH_FLIGHT_DST_CITY = 1;
    String TRAIN_DETAIN = "3";
    //返程的城市
    String EMPTY_STRING = "";
    int FUONE = -1;
    int ZERO = 0;
    int TWO = 2;
    int THREE = 3;
    String T = "T";
    boolean FALSE = false;
    boolean TRUE = true;
    String QUERY_ROLE_NO = "NO";
    String QUERY_ROLE_YES = "YES";
    //是否将返回的航班数据进行排序，以什么方式排序
    String QUERY_SORT = "DepDate";
    int TRIP_TYPE = 0;//航班类型 0 单航段 1 来回程  2 联程
    boolean DIRECT = true;

    String C_B_AUTOLOGIN = "C_B_AUTOLOGIN";
    String C_B_SAVEPWD = "C_B_SAVEPWD";
    String C_S_USERNAME = "C_S_USERNAME";
    String C_S_PASSWORD = "C_S_PASSWORD";
    String EDZ_TYPE = "EDZ";
    String YDZ_TYPE="YDZ";
    String SWZ_TYPE="SWZ";
    String YZ_TYPE="YZ";
    String YW_TYPE ="YW";
    String RW_TYPE ="RW";
    String WZ_TYPE = "WZ";
    String ZW_TYPE="TRAIN_TYPE";

    public static final String PAGE_SIZE_S = "PAGE_SIZE_S";
    public static final int PAGE_SIZE_I = 10;
    public static final int MIN_PAGE_SIZE_I = 5;
    public static final int START_PAGE = 1;

    boolean LOAD_SUCCESS = true;

    int ORDER_ID = 0;

    int APPLY_ID = 0;

    String PSG_IS_EDIT = "psgIsEdit";
    String PSG_IS_ADD = "psgIsAdd";
    String PSG_IS_SELECT = "psgIsSelect";
    String EMAIL_MIDDLE_FIX = "@";
    String FROM_APPLY_OR_ORDER_LIST = "FROM_APPLY_OR_ORDER_LIST";
    int FROM_ORDER_LIST = 0;
    int FROM_APPLY_LIST = 1;

    String AIRPORT_DATA_VERSION = "AIRPORT_DATA_VERSION";
    String AIRLINE_DATA_VERSION = "AIRLINE_DATA_VERSION";
    String PLANESTYLE_DATA_VERSION = "PLANESTYLE_DATA_VERSION";
    String REC_DATA_VERSION = "REC_DATA_VERSION";

    String DEFAULT_AIRPORT_DATA_VERSION = "0.0";
    String DEFAULT_AIRLINE_DATA_VERSION = "0.0";
    String DEFAULT_PLANESTYLE_DATA_VERSION = "0.0";
    String DEFAULT_REC_DATA_VERSION = "0.0";
    String LOAD_HELP_URL = "http://biz.sky-trip.com/apphelp.aspx";
}
