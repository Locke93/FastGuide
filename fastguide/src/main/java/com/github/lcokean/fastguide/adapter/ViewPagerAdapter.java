package com.github.lcokean.fastguide.adapter;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.lcokean.fastguide.listener.ViewLazyInitializeListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<View> views = null;
    private ViewLazyInitializeListener viewLazyInitializeListener = null;

    public ViewPagerAdapter() {
        views = new ArrayList<View>();
    }

    public List<View> getDataViews() {
        return views;
    }

    //销毁position位置的界面
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    @Override
    public void finishUpdate(View arg0) {
    }

    //获取当前窗体界面数
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        }
        return 0;
    }

    // 判断是否由对象生成界面
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }

    //初始化arg1位置的界面
    @Override
    public Object instantiateItem(View view, int position) {
        ((ViewPager) view).addView(views.get(position), 0);
        if (null != viewLazyInitializeListener) {
            viewLazyInitializeListener.onViewLazyInitialize(view, position);
        }
        return views.get(position);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {
    }

    public void setOnViewLazyInitializeListener(ViewLazyInitializeListener lazyInitializeListener) {
        this.viewLazyInitializeListener = lazyInitializeListener;
    }

}

