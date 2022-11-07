package com.example.virtual_idol.act;

import android.util.Log;

import androidx.viewpager2.widget.ViewPager2;

import com.example.virtual_idol.components.LOG;
import com.example.virtual_idol.ui.startAppPager.PagerAdapter;

public class PagerHandler implements LOG {
    private static PagerHandler getPageCount = new PagerHandler();
    private PagerAdapter adapter;
    private ViewPager2 pager;

    public static PagerHandler getInstance() {
        if (getPageCount == null) {
            getPageCount = new PagerHandler();
        }
        return getPageCount;
    }

    public void setAdapter(PagerAdapter adapter) {
        this.adapter = adapter;
    }

    public void setViewpager(ViewPager2 pager) {
        this.pager = pager;
    }

    public void pagerChange(int po) {
        Log.d(TAG, "pagerChange: "+pager.getCurrentItem());
        pager.setCurrentItem(pager.getCurrentItem()+po);
    }

    public void setPagerCount(int cnt) {
        Log.d(TAG, "setPagerCount: "+ cnt);
        adapter.setPagerCount = cnt;
        adapter.getItemCount();
    }

    public void setAdapterPager() {
        pager.setAdapter(adapter);
    }
}
