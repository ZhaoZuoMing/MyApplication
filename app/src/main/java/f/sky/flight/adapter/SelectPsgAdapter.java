package f.sky.flight.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.dmonline.R;
import com.mytables.AppT;

import java.util.ArrayList;
import java.util.List;

import f.sky.flight.model.B_TouristDOObj;

/**
 * Created by Administrator on 2016/12/12/012.
 */

public class SelectPsgAdapter extends BaseAdapter {

    protected LayoutInflater mInflater;
    private List<B_TouristDOObj> touristL = new ArrayList<B_TouristDOObj>() ;
    private AppT appT;
    private boolean isEdit;

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean isEdit) {
        this.isEdit = isEdit;
        this.notifyDataSetChanged();
    }

    public SelectPsgAdapter(AppT context){
        this.mInflater = LayoutInflater.from(context);
        this.appT = context;
    }

    public void setDatas(List<B_TouristDOObj> touristS){
        touristL.clear();
        touristL.addAll(touristS);
        this.notifyDataSetChanged();
    }

    public List<B_TouristDOObj> getTouristL() {
        return touristL;
    }

    @Override
    public int getCount() {
        return touristL.size();
    }

    @Override
    public Object getItem(int position) {
        return touristL.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.psg_manager_item, null);
            holder.psgNameTxt = (TextView) convertView.findViewById(R.id.psg_name_txt);
            holder.psgNicknameTxt = (TextView) convertView.findViewById(R.id.psg_nickname_txt);
            holder.psgIdNumberTxt = (TextView)convertView.findViewById(R.id.psg_idNumber_txt);
            holder.psgMobileTxt = (TextView) convertView.findViewById(R.id.psg_mobile_txt);
            holder.delPsgBtn = (Button)convertView.findViewById(R.id.del_psg_btn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        B_TouristDOObj touristDOObj = (B_TouristDOObj)getItem(position);
        if(isEdit){
            holder.delPsgBtn.setVisibility(View.VISIBLE);
            holder.delPsgBtn.setTag(touristDOObj);
            holder.delPsgBtn.setOnClickListener(appT);
        } else {
            holder.delPsgBtn.setVisibility(View.GONE);
        }
        holder.psgNameTxt.setText(touristDOObj.getTouristName());
        holder.psgNicknameTxt.setText(touristDOObj.getField2());
        holder.psgMobileTxt.setText(touristDOObj.getMobileNo());
        holder.psgIdNumberTxt.setText(touristDOObj.getIDNumber());
        return convertView;
    }

    static class ViewHolder{
        public TextView psgNameTxt, psgIdNumberTxt, psgNicknameTxt, psgMobileTxt;
        public Button delPsgBtn;
    }


}
