package com.myuntils;

import com.example.administrator.dmonline.R;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2016/11/25/025.
 * 处理显示航空公司的图片
 */

public class FlightAirlineUtil {


    public static int parseDrawableAirline(String airline){
        if(StringUtils.equals("3U", airline.toUpperCase())){
            return R.mipmap.flight_3u;
        } else if(StringUtils.equals("BK", airline.toUpperCase())){
            return R.mipmap.flight_bk;
        } else if(StringUtils.equals("CA", airline.toUpperCase())){
            return R.mipmap.flight_ca;
        } else if(StringUtils.equals("CJ", airline.toUpperCase())){
            return R.mipmap.flight_cj;
        } else if(StringUtils.equals("CX", airline.toUpperCase())){
            return R.mipmap.flight_cx;
        } else if(StringUtils.equals("CZ", airline.toUpperCase())){
            return R.mipmap.flight_cz;
        } else if(StringUtils.equals("EU", airline.toUpperCase())){
            return R.mipmap.flight_eu;
        } else if(StringUtils.equals("FM", airline.toUpperCase())){
            return R.mipmap.flight_fm;
        } else if(StringUtils.equals("HO", airline.toUpperCase())){
            return R.mipmap.flight_ho;
        } else if(StringUtils.equals("HU", airline.toUpperCase())){
            return R.mipmap.flight_hu;
        } else if(StringUtils.equals("HX", airline.toUpperCase())){
            return R.mipmap.flight_hx;
        } else if(StringUtils.equals("JR", airline.toUpperCase())){
            return R.mipmap.flight_jr;
        } else if(StringUtils.equals("KA", airline.toUpperCase())){
            return R.mipmap.flight_ka;
        } else if(StringUtils.equals("MF", airline.toUpperCase())){
            return R.mipmap.flight_mf;
        } else if(StringUtils.equals("MU", airline.toUpperCase())){
            return R.mipmap.flight_mu;
        } else if(StringUtils.equals("NX", airline.toUpperCase())){
            return R.mipmap.flight_nx;
        } else if(StringUtils.equals("PN", airline.toUpperCase())){
            return R.mipmap.flight_pn;
        } else if(StringUtils.equals("SC", airline.toUpperCase())){
            return R.mipmap.flight_sc;
        } else if(StringUtils.equals("SZ", airline.toUpperCase())){
            return R.mipmap.flight_sz;
        } else if(StringUtils.equals("ZH", airline.toUpperCase())){
            return R.mipmap.flight_zh;
        } else if(StringUtils.equals("GS", airline.toUpperCase())){
            return R.mipmap.flight_gs;
        } else if(StringUtils.equals("JD", airline.toUpperCase())){
            return R.mipmap.flight_jd;
        }
        return 0;
    }

}
