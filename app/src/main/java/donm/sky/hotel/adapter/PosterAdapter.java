package donm.sky.hotel.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.dmonline.R;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Administrator on 2017/1/16/016.
 */

public class PosterAdapter extends PagerAdapter {
    private List<ImageView> list_poster;
    private WeakReference<Context> wr;

    public PosterAdapter(Context context, List<ImageView> list) {
        wr = new WeakReference<Context>(context);
        list_poster = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list_poster != null ? list_poster.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(list_poster.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(list_poster.get(position));
        list_poster.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(String.valueOf(v.getTag(R.string.tag_title)))) {
                    if (String.valueOf(v.getTag(R.string.tag_title)).equals("url")) {
                        if (!TextUtils.isEmpty(String.valueOf(v.getTag(R.string.tag_id)))) {
                            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.valueOf(v.getTag(R.string.tag_id))));
                            wr.get().startActivity(intent);
                        }
                    }
                }
            }
        });
        return list_poster.get(position);
    }

    public View getCurrentView(int pos) {
        if (list_poster != null) {
            return list_poster.get(pos);
        } else {
            return null;
        }

    }
}
