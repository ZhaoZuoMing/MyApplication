package com.myuntils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/11/25/025.
 */

public class ListViewSetup {


    private static final int GRIDVIEW_VERTICAL_SPACING = 0;

    public static void setListViewHeightBasedOnSameRowHeightChildren(ListView listView){
        setListViewHeightBasedOnChildren(listView, true);
    }

    public static void setGridViewHeightBasedOnSameRowHeightChildren(GridView listView){
        setGridViewHeightBasedOnChildren(listView, true);
    }

    public static void setGridViewHeightBasedOnChildren(GridView listView, boolean sameRowHeight) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int rowHeight = 0;
        int rowCount = (listAdapter.getCount()%3==0)?(listAdapter.getCount()/3):(listAdapter.getCount()/3+1);
        for (int i = 0; i < rowCount; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);

            if(sameRowHeight){
                rowHeight = listItem.getMeasuredHeight();
                break;
            }else{
                totalHeight += listItem.getMeasuredHeight();
            }
        }
        if(sameRowHeight){
            totalHeight = rowCount *  rowHeight;
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (rowCount+1)*AndroidUtil.dip2px(listView.getContext(), GRIDVIEW_VERTICAL_SPACING);

        listView.setLayoutParams(params);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView, boolean sameRowHeight) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int rowHeight = 0;
        int count = listAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);

            if(sameRowHeight){
                rowHeight = listItem.getMeasuredHeight();
                break;
            }else{
                totalHeight += listItem.getMeasuredHeight();
            }
        }
        if(sameRowHeight){
            totalHeight = count *  rowHeight;
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);
    }

}
