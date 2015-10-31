package com.github.lcokean.fastguide;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.github.lcokean.fastguide.adapter.ViewPagerAdapter;
import com.github.lcokean.fastguide.listener.ViewLazyInitializeListener;
import com.github.lcokean.fastguide.widget.CircleIndicator;
import com.github.lcokean.fastguide.widget.DirectionalViewPager;

import java.util.List;

/**
 * Created by pengjian on 2015/10/30 0030.
 */
public class GuidePage extends FakeActivity {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    DirectionalViewPager mViewPager;
    ViewPagerAdapter mAdapter;
    CircleIndicator mIndicator;
    OnViewSelectedListener mOnViewSelectedListener;

    private int mOrientation = HORIZONTAL;

    public GuidePage() {
        mAdapter = new ViewPagerAdapter();
    }

    public void show(Context context) {
        this.show(context, null);
    }

    public void onCreate() {
        int resId = R.layout.guide_main;
        if (resId > 0) {
            this.activity.setContentView(resId);
            mViewPager = (DirectionalViewPager) this.activity.findViewById(R.id.viewpage);
            mIndicator = (CircleIndicator) this.activity.findViewById(R.id.indicator);
            initViewAndEvents();
        }
    }


    private void initViewAndEvents() {
        if (mOrientation == VERTICAL) {
            mViewPager.setOrientation(DirectionalViewPager.VERTICAL);
            mIndicator.setVisibility(View.GONE);
        }
        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mOnViewSelectedListener != null) {
                    mOnViewSelectedListener.onViewSelected(mAdapter.getDataViews().get(position), position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void addPage(View v) {
        mAdapter.getDataViews().add(v);
    }

    public void addAllPages(List<View> views) {
        mAdapter.getDataViews().addAll(views);
    }

    public void addPage(Context context, int rid) {
        View v = LayoutInflater.from(context).inflate(rid, null);
        mAdapter.getDataViews().add(v);
    }

    public void setOnViewLazyInitializeListener(ViewLazyInitializeListener onViewLazyInitializeListener) {
        mAdapter.setOnViewLazyInitializeListener(onViewLazyInitializeListener);
    }

    public void setOrientation(int orientation) {
        mOrientation = orientation;
    }

    public void setOnViewSelectedListener(OnViewSelectedListener onViewSelectedListener) {
        this.mOnViewSelectedListener = onViewSelectedListener;
    }

    public interface OnViewSelectedListener {
        void onViewSelected(View view, int position);
    }


}
