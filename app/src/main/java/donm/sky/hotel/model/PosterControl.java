package donm.sky.hotel.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.dmonline.R;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/1/16/016.
 */

public class PosterControl {

    private WeakReference<Context> wr;
    private ViewPager viewPager;
    private ImageView[] image_pointers;
    private boolean isContinue = true;
    private AtomicInteger which = new AtomicInteger(0);
    private boolean isRun = true;
    private Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if(msg.what!=0){
                viewPager.setCurrentItem(msg.what);
            }else {
                viewPager.setCurrentItem(msg.what, false);
            }

        }
    };

    public PosterControl(Context context,ViewPager viewPager,ImageView[] pointers){
        wr=new WeakReference<Context>(context);
        this.viewPager=viewPager;
        image_pointers=pointers;
        this.viewPager.setOnPageChangeListener(new ViewPagerPageChangeListener());
        this.viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        break;
                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });
        new Thread(new ViewPagerScrollRunnable()).start();
    }

    /**
     * viewpager滑动设置
     */
    private class ViewPagerPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @SuppressWarnings("deprecation")
        @Override
        public void onPageSelected(int arg0) {
            which.getAndSet(arg0);
            for (int i = 0; i < image_pointers.length; i++) {
                if (i == arg0) {
                    image_pointers[i].setBackgroundDrawable(wr.get().getResources()
                            .getDrawable(R.drawable.shape_poster_pointer_selected));
                } else {
                    image_pointers[i].setBackgroundDrawable(wr.get().getResources()
                            .getDrawable(R.drawable.shape_poster_pointer_unselect));
                }
            }

        }

    }

    /**
     * 线程广告自动滑动
     */
    private class ViewPagerScrollRunnable implements Runnable {
        @Override
        public void run() {
            while (isRun) {
                if (isContinue) {
                    viewHandler.sendEmptyMessage(which.get());
                    whatOption();
                }
            }
        }
    }

    public int getCurrentItem(){
        return which.get();
    }

    /**
     * 自增到下一个
     */
    private void whatOption() {
        which.incrementAndGet();
        if (which.get() > image_pointers.length - 1) {
            which.getAndAdd(-(image_pointers.length));
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {

        }
    }

    public void setThreadStop(){
        if(isRun){
            isRun=false;
        }
    }


}
