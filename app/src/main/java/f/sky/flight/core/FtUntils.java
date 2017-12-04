package f.sky.flight.core;

import android.widget.ImageView;

import com.example.administrator.dmonline.R;

/**
 * Created by zhaody on 2017/11/21.
 */

public class FtUntils {
    /**
     * 替换一些机场数据
     * @param AirportName
     * @param Terminal
     * @return
     */
    public static String replaceAirportName(String AirportName,String Terminal) {
        if (AirportName.contains("国际机场")){
            if (!Terminal.equals("")){
                return  AirportName.replaceAll("国际机场","")+"("+Terminal+")";
            }else {
                return AirportName.replaceAll("国际机场","");
            }
        } else if (AirportName.contains("机场")){
            if (!Terminal.equals("")){
                return AirportName.replaceAll("机场","")+"("+Terminal+")";
            }else {
                return AirportName.replaceAll("机场","");
            }
        }
        return AirportName;
    }
    /**
     * 计算折扣
     * @param discount
     * @return
     */
    public static String  mathDisCount(float discount) {
        int dis = (int)(discount*100);
        if (dis==100){
            return "全价";
        }
        return String.valueOf(dis)+"折";
    }
    public static void setFlightIcon(String airline, ImageView flight_icon) {
        if (airline.equals("MU")){
            flight_icon.setImageResource(R.mipmap.flight_mu);
        }else  if (airline.equals("CA")){
            flight_icon.setImageResource(R.mipmap.flight_ca);
        }else  if (airline.equals("HU")){
            flight_icon.setImageResource(R.mipmap.flight_hu);
        }else  if (airline.equals("CZ")){
            flight_icon.setImageResource(R.mipmap.flight_cz);
        }else  if (airline.equals("HO")){
            flight_icon.setImageResource(R.mipmap.flight_ho);
        }else  if (airline.equals("MF")){
            flight_icon.setImageResource(R.mipmap.flight_mf);
        }else  if (airline.equals("3U")){
            flight_icon.setImageResource(R.mipmap.flight_3u);
        }else  if (airline.equals("BK")){
            flight_icon.setImageResource(R.mipmap.flight_bk);
        }else  if (airline.equals("CJ")){
            flight_icon.setImageResource(R.mipmap.flight_cj);
        }else  if (airline.equals("NX")){
            flight_icon.setImageResource(R.mipmap.flight_nx);
        }else  if (airline.equals("HX")){
            flight_icon.setImageResource(R.mipmap.flight_hx);
        }else  if (airline.equals("PN")){
            flight_icon.setImageResource(R.mipmap.flight_pn);
        }else  if (airline.equals("JD")){
            flight_icon.setImageResource(R.mipmap.flight_jd);
        }else  if (airline.equals("EU")){
            flight_icon.setImageResource(R.mipmap.flight_eu);
        }else  if (airline.equals("JR")){
            flight_icon.setImageResource(R.mipmap.flight_jr);
        }else  if (airline.equals("SC")){
            flight_icon.setImageResource(R.mipmap.flight_sc);
        }else  if (airline.equals("GS")){
            flight_icon.setImageResource(R.mipmap.flight_gs);
        }else  if (airline.equals("KA")){
            flight_icon.setImageResource(R.mipmap.flight_ka);
        }else  if (airline.equals("SZ")){
            flight_icon.setImageResource(R.mipmap.flight_sz);
        }else  if (airline.equals("ZH")){
            flight_icon.setImageResource(R.mipmap.flight_zh);
        }
    }
}
