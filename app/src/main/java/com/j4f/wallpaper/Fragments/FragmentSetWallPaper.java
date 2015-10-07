package com.j4f.wallpaper.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j4f.wallpaper.R;

/**
 * Created by pham on 8/5/2015.
 */
public class FragmentSetWallPaper extends Fragment {
    private View view;

    public static FragmentSetWallPaper newInstance() {
        FragmentSetWallPaper mFragment = new FragmentSetWallPaper();
        return mFragment;
    }
    public FragmentSetWallPaper() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image, container, false);
        ((ActionBarActivity) getActivity()).getSupportActionBar().hide();

        return view;
    }
}
