package com.j4f.wallpaper.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.j4f.wallpaper.Fragments.FragmentListAllWallPaper;
import com.j4f.wallpaper.Fragments.FragmentListAlbum;
import com.j4f.wallpaper.Helpers.Commons.Constants;

/**
 * Created by pham on 7/20/2015.
 */
public class SwipeTabAdapter extends FragmentPagerAdapter {
    private Context context;
    public SwipeTabAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context= context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentListAlbum.newInstance();
            case 1:
                return new FragmentListAllWallPaper();
            default:
                return FragmentListAlbum.newInstance();
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.tabs[position].toString();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
