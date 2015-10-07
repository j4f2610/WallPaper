package com.j4f.wallpaper.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.j4f.wallpaper.R;
import com.j4f.wallpaper.View.GridSpacingItem;

/**
 * Created by pham on 7/20/2015.
 */
public class FragmentListAllWallPaper extends Fragment {
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private SliderLayout sliderLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_wallpaper, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_listWallPaper);
        sliderLayout=(SliderLayout)view.findViewById(R.id.slider);
        sliderLayout.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        int spanCount = 3;
        int spacing = 10;
        boolean includeEdge = true;
        recyclerView.addItemDecoration(new GridSpacingItem());
        recyclerView.setLayoutManager(mLayoutManager);
        return view;
    }
}
