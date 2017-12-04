package donm.sky.hotel.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.MyApp;
import com.myviews.RoundRectImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import donm.sky.hotel.model.HotelDetail;
import donm.sky.hotel.model.HotelImageLocation;
import donm.sky.hotel.model.HotelInfoSearchResult;
import donm.sky.hotel.model.HotelListSearchResult;
import donm.sky.hotel.model.Hotels;
import donm.sky.hotel.until.StarBar;
import f.sky.flight.core.WebServUtil;

/**
 * Created by Administrator on 2017/1/16/016.
 * 查询酒店 酒店列表适配器
 */

public class HotelListAdapter extends BaseAdapter {
    private List<HotelListSearchResult>  results;
    private Context mCtx;
    private LayoutInflater mInflater;
    private List<HotelInfoSearchResult> listInfos;
    List<HotelDetail> details  = new ArrayList<>();
    List<HotelImageLocation> locations = new ArrayList<>();
    public HotelListAdapter(Context mCtx,List<HotelInfoSearchResult>  infos) {
        this.listInfos = infos;
        this.mCtx = mCtx;
        mInflater = LayoutInflater.from(mCtx);
        for (int i = 0; i <listInfos.size() ; i++) {
             details.addAll(listInfos.get(i).getDetails());
             locations.addAll(listInfos.get(i).getImgLocations());
        }
        results = MyApp.getHotelListSearchResult();
    }

    @Override
    public int getCount() {
        return details.size();
    }

    @Override
    public Object getItem(int position) {
        return details.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DetailHodler hodler = null;
        if (convertView==null){
            convertView = mInflater.inflate(R.layout.hotel_item_layout,null);
            hodler = new DetailHodler();
            hodler.hotel_item_img = (RoundRectImageView) convertView.findViewById(R.id.hotel_item_img);
            hodler.hotel_star_level = (StarBar) convertView.findViewById(R.id.hotel_star_level);
            hodler.hotel_item_title = (TextView) convertView.findViewById(R.id.hotel_item_title);
            hodler.hotel_item_address = (TextView) convertView.findViewById(R.id.hotel_item_address);
            hodler.hotel_item_price = (TextView) convertView.findViewById(R.id.hotel_item_price);
            hodler.h_isHav_canyin = (ImageView)convertView.findViewById(R.id.h_isHav_canyin);
            hodler.h_isHav_kuandai = (ImageView)convertView.findViewById(R.id.h_isHav_kuandai);
            hodler.h_isHav_wifi = (ImageView)convertView.findViewById(R.id.h_isHav_wifi);
            hodler.h_isHav_park = (ImageView)convertView.findViewById(R.id.h_isHav_park);
            convertView.setTag(hodler);
        }else {
            hodler = (DetailHodler) convertView.getTag();
        }
         hodler.detail = details.get(position);
         hodler.hotel_item_title.setText(hodler.detail.getName());
         hodler.hotel_item_address.setText(hodler.detail.getAddress());
//        Log.e(TAG, "推荐星级：---- "+hodler.detail.getCategory());
//        Log.e(TAG, "星级：---- "+hodler.detail.getStarRate());

        hodler.hotel_star_level.setStarMark(hodler.detail.getCategory());
        //---------img----
        int hotelId = hodler.detail.getHotelID();
        List<String>url = new ArrayList<>();
        for (int i = 0; i <locations.size() ; i++) {
            HotelImageLocation location = locations.get(i);
            if (location.getHotelID()==hotelId){
               url.add(location.getURL());
            }
        }
        /*图片地址*/
        for (int i = 0; i <url.size() ; i++) {
            if (!url.equals("")){
                ImageLoader.getInstance().displayImage(url.get(i),hodler.hotel_item_img);
                break;
            }
        }

       //----酒店需要显示的最低价----
        List<Hotels> lowPriceList = new  ArrayList<>();
        for (int i = 0; i <results.size() ; i++) {
            lowPriceList.addAll(results.get(i).getHotels());
        }

         double price = WebServUtil.parseDouble(lowPriceList.get(position).getLowRate());

         hodler.hotel_item_price.setText("￥"+(int)price);
         /**/
         String arr[]  = hodler.detail.getFacilities().split(",");
        Log.e("查看酒店设施", "----------------->"+hodler.detail.getFacilities() );
         for(int i=0;i<arr.length;i++){
             int tag =Integer.parseInt(arr[i]);
              if (tag==1||tag==2){
                  hodler.h_isHav_wifi.setVisibility(View.VISIBLE);
              }else if (tag==3||tag==4){
                  hodler.h_isHav_kuandai.setVisibility(View.VISIBLE);
              }else if (tag==5||tag==6){
                  hodler.h_isHav_park.setVisibility(View.VISIBLE);
              }else if (tag==14){
                  Log.e("------", "come in --- " );
                  hodler.h_isHav_canyin.setVisibility(View.VISIBLE);
              }
           }

        return convertView;
    }

    public static class DetailHodler{
        public RoundRectImageView hotel_item_img;
        public TextView hotel_item_title;
        public TextView hotel_item_address;
        public TextView hotel_item_price;
        public  StarBar hotel_star_level;
        public  HotelDetail detail;
        public ImageView h_isHav_canyin,h_isHav_kuandai,h_isHav_wifi,h_isHav_park;
    }
}
