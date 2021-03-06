package f.sky.flight.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27/027.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {


    private List<Fragment> list;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        // TODO Auto-generated constructor stub
        this.list = list;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
     */
    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.support.v4.view.PagerAdapter#getCount()
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.isEmpty() ? 0 : list.size();
    }


}
