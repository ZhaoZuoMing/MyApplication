package donm.sky.hotel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import donm.sky.hotel.model.HotelImage;
import donm.sky.hotel.model.HotelImageLocation;
import donm.sky.hotel.model.HotelInfoSearchResult;
import donm.sky.hotel.model.HotelListSearchResult;
import donm.sky.hotel.model.HotelRoom;
import donm.sky.hotel.model.RatePlans;
import donm.sky.hotel.model.Room;
import com.mytables.MyApp;

/**
 * Created by Administrator on 2017/1/17/017.
 * 详情页面 酒店房间列表显示
 */

public class HotelRoomAdapter extends BaseAdapter {
    private List<HotelRoom> list;//当前酒店的所有房间
    private List<String> imgs;
    private  Context mCtx;
    private LayoutInflater minflater;
    private List<HotelImage> imgIds;
    private List<HotelImageLocation> locations;
    private List<HotelInfoSearchResult> infos;
    private List<HotelListSearchResult> results;
    private List<Room> rooms;
    private List<Room> roomRate;
    private List<RatePlans> plans;

    RoomViewHolder holder = null;
    public HotelRoomAdapter(Context mCtx,List<HotelRoom> list) {
        this.mCtx = mCtx;
        this.list = list;
        this.minflater = LayoutInflater.from(mCtx);
        imgIds = new ArrayList<>();
        locations = new ArrayList<>();
        infos = MyApp.getHotelInfoSearchResult();
        for (int i = 0; i <infos.size() ; i++) {
             imgIds.addAll(infos.get(i).getImages());
             locations.addAll(infos.get(i).getImgLocations());
        }
        results = MyApp.getHotelListSearchResult();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = minflater.inflate(R.layout.room_item_layout,null);
            holder = new RoomViewHolder();
            holder.room_img = (ImageView)convertView.findViewById(R.id.room_img);
            holder.room_content =(TextView)convertView.findViewById(R.id.room_content);
            holder. room_name =(TextView)convertView.findViewById(R.id.room_name);
            holder.room_ischeck_btn = (ImageView) convertView.findViewById(R.id.room_ischeck_btn);
            holder.room_item_rety = (RelativeLayout)convertView.findViewById(R.id.room_item_rety);

            convertView.setTag(holder);
        }else {
            holder=(RoomViewHolder) convertView.getTag();
        }

        holder.room = list.get(position);
        holder.room_name.setText(holder.room.getName());
        holder.room_content.setText(holder.room.getDescription());
           //----图片的处理---RoomId  === ImageId ----Location ----
           int imgId = 0;
           for (int i = 0; i <imgIds.size() ; i++) {
            if (holder.room.getRoomId().equals(imgIds.get(i).getRoomId())){
                imgId= imgIds.get(i).getImageID();
              }
           }
           imgs = new ArrayList<>();
         for (int i = 0; i <locations.size() ; i++) {
             HotelImageLocation imgLocations = locations.get(i);
             if (imgLocations.getImageID()==imgId&&imgLocations.getSize()==1){
                   imgs.add(imgLocations.getURL());
                     ImageLoader.getInstance().displayImage(imgLocations.getURL(),holder.room_img);
             }
           }

        return convertView;
    }


    public static class RoomViewHolder{
        public TextView room_name;
        public TextView room_content;
        public ImageView room_img;
        public ImageView room_ischeck_btn;
        public RelativeLayout room_item_rety;
        public HotelRoom room;
    }

}
