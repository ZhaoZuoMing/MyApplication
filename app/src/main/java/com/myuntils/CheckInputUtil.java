package com.myuntils;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/12/12/012.
 */

public class CheckInputUtil {

    private static Pattern emailPattern = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private static Pattern telphonePattern = Pattern
            .compile("0[0-9]{2,3}-?[0-9]{7,8}");

    private static Pattern mobilePattern = Pattern.compile("1\\d{10}");
    private static IdcardValidator idcardValidator = new IdcardValidator();

    public static boolean checkEmail(String eamil) {
        if (StringUtils.isBlank(eamil)) {
            return false;
        }
        Matcher m = emailPattern.matcher(eamil);
        return m.matches();
    }

    public static boolean checkMobile(String mobile) {
        if (StringUtils.isNotBlank(mobile)
                && mobilePattern.matcher(mobile).matches()) {
            return true;
        }
        return false;
    }

    public static boolean checkTelphone(String telphone) {
        if (StringUtils.isBlank(telphone)) {
            return false;
        }
        Matcher m = telphonePattern.matcher(telphone);
        return m.matches();
    }

    public static boolean checkIdCard(String idCardNo) {
        return idcardValidator.isValidatedAllIdcard(idCardNo);
    }


}
