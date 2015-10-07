package com.j4f.wallpaper.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.j4f.wallpaper.Adapters.SwipeTabAdapter;
import com.j4f.wallpaper.Helpers.Commons.Common;
import com.j4f.wallpaper.Helpers.View.SlidingTabLayout;
import com.j4f.wallpaper.R;

/**
 * Created by pham on 7/20/2015.
 */
public class FragmentMain extends Fragment implements ActionBar.TabListener {
    public ViewPager viewPager;
    private SwipeTabAdapter swipeTabAdapter;
    private SlidingTabLayout mSlidingTabLayout;
    private CoordinatorLayout coordinatorLayout;
    public static FragmentMain newInstance() {
        FragmentMain mFragment = new FragmentMain();
        return mFragment;
    }
    public FragmentMain() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id
                .coordinatorLayout);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        swipeTabAdapter = new SwipeTabAdapter(getChildFragmentManager(), getActivity());
        viewPager.setAdapter(swipeTabAdapter);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(viewPager);
        mSlidingTabLayout.setSelectedIndicatorColors(Color.WHITE);
        if (!Common.isNetworkAvailable(getContext())) {
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            snackbar.setActionTextColor(Color.RED);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }
        return view;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
