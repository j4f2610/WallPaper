package com.j4f.wallpaper.Fragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.j4f.wallpaper.Adapters.ListAlbumAdapter;
import com.j4f.wallpaper.Helpers.Commons.Common;
import com.j4f.wallpaper.Helpers.Commons.ShareDataHelper;
import com.j4f.wallpaper.Interfaces.AsyncTaskCompleteListener;
import com.j4f.wallpaper.Interfaces.RecyclerItemClickListener;
import com.j4f.wallpaper.Model.Album;
import com.j4f.wallpaper.R;

import java.util.ArrayList;

/**
 * Created by pham on 7/20/2015.
 */
public class FragmentListAlbum extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;
    private ArrayList<Album> wallpaperList = new ArrayList<>();
    FragmentListWallPaperListener mCallback;
    private Button btnRefresh;
    private LinearLayout menuSlider;
    public static FragmentListAlbum newInstance() {
        FragmentListAlbum mFragment = new FragmentListAlbum();
        return mFragment;
    }
    public FragmentListAlbum() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_wallpaper, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_listWallPaper);
        btnRefresh = (Button) view.findViewById(R.id.btnRefreshMenu);
        menuSlider = (LinearLayout) view.findViewById(R.id.menuSlider);
        menuSlider.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);
        loadData();
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mCallback.onSelected(position);
            }
        }));
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        bindData(wallpaperList);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            bindData(wallpaperList);
        }
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            bindData(wallpaperList);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (FragmentListWallPaperListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public interface FragmentListWallPaperListener {
        public void onSelected(int position);
    }

    private void bindData(ArrayList<Album> result) {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        ListAlbumAdapter mAdapter = new ListAlbumAdapter(result);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void loadData() {
        if (Common.isNetworkAvailable(getActivity())) {
            if (ShareDataHelper.getInstance().getListWallPaperProxy().getWallPapers().size() == 0) {
                Common.showDialog(getActivity());
                ShareDataHelper.getInstance().getListWallPaperProxy().getListWallPaper(getActivity(), new AsyncTaskCompleteListener<ArrayList<Album>>() {
                    @Override
                    public void onTaskCompelete(ArrayList<Album> result) {
                        bindData(result);
                        wallpaperList = ShareDataHelper.getInstance().getListWallPaperProxy().getWallPapers();
                        Common.dismissDialog(getActivity());
                        btnRefresh.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(int ErrorCode) {
                        btnRefresh.setVisibility(View.VISIBLE);
                        Common.dismissDialog(getActivity());
                    }
                });
            } else bindData(ShareDataHelper.getInstance().getListWallPaperProxy().getWallPapers());
        } else {
            btnRefresh.setVisibility(View.VISIBLE);
            Common.showToast(getActivity(), getString(R.string.NoNetwork));
        }

    }
}
