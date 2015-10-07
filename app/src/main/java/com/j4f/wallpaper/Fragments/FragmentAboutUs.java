package com.j4f.wallpaper.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j4f.wallpaper.Activities.MainActivity;
import com.j4f.wallpaper.R;

/**
 * Created by pham on 12/8/2015.
 */
public class FragmentAboutUs extends Fragment {
    public static FragmentAboutUs newInstance() {
        FragmentAboutUs mFragment = new FragmentAboutUs();
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ((MainActivity) getActivity()).toolbar.setTitle(getResources().getString(R.string.AboutUs));
        return view;
    }
}
