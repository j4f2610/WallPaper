package com.j4f.wallpaper.Fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.github.pedrovgs.DraggableView;
import com.j4f.wallpaper.Activities.MainActivity;
import com.j4f.wallpaper.Adapters.ListWallPaperAdapter;
import com.j4f.wallpaper.Helpers.Commons.Common;
import com.j4f.wallpaper.Helpers.Commons.ShareDataHelper;
import com.j4f.wallpaper.Helpers.Commons.Utils;
import com.j4f.wallpaper.Interfaces.AsyncTaskCompleteListener;
import com.j4f.wallpaper.Interfaces.RecyclerItemClickListener;
import com.j4f.wallpaper.Model.Album;
import com.j4f.wallpaper.Model.WallPaper;
import com.j4f.wallpaper.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pham on 7/29/2015.
 */
public class FragmentWallPaper extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private View view;
    private static Album albumWallPaper;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private SliderLayout sliderLayout;
    private TextView txtTitleAlbum;
    private Activity mContext;
    private ImageView imgWallPaper;
    private ImageButton btnSetWallPaper;
    private Utils utils;

    public static FragmentWallPaper newInstance(Album album) {
        FragmentWallPaper mFragment = new FragmentWallPaper();
        albumWallPaper = album;
        return mFragment;
    }

    public FragmentWallPaper() {

    }

    private static final int DELAY_MILLIS = 10;
    private DraggableView draggableView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list_wallpaper, container, false);
        getViewItem();
        bindData();
        return view;
    }

    private void getViewItem() {
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_listWallPaper);
        sliderLayout = (SliderLayout) view.findViewById(R.id.slider);
        txtTitleAlbum = (TextView) view.findViewById(R.id.txtTitleAlbum);
        draggableView = (DraggableView) view.findViewById(R.id.draggable_view);
        imgWallPaper = (ImageView) view.findViewById(R.id.image);
        btnSetWallPaper = (ImageButton) view.findViewById(R.id.btnWallPaper);
    }

    private void bindData() {
        draggableView.setClickToMaximizeEnabled(true);
        draggableView.setClickToMinimizeEnabled(false);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        ShareDataHelper.getInstance().getListItemWallPaperProxy().getItemWallPaper(getActivity(), albumWallPaper.getType(), new AsyncTaskCompleteListener<ArrayList<WallPaper>>() {
            @Override
            public void onTaskCompelete(final ArrayList<WallPaper> result) {
                showMenuSlider(result);
                txtTitleAlbum.setText(albumWallPaper.getName());
                txtTitleAlbum.setVisibility(View.VISIBLE);
                ListWallPaperAdapter listItemWallPaperAdapter = new ListWallPaperAdapter(result);
                recyclerView.setAdapter(listItemWallPaperAdapter);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Uri uri = Uri.parse(result.get(position).getUrlImage());
                        loadImage(uri);
                    }
                }));
            }

            @Override
            public void onError(int ErrorCode) {

            }
        });
        btnSetWallPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable) imgWallPaper.getDrawable())
                        .getBitmap();
                utils = new Utils(getActivity());
                utils.setAsWallpaper(bitmap);
            }
        });
    }

    private void loadImage(final Uri uri) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Picasso.with(getActivity()).load(uri).into(imgWallPaper);
                draggableView.setVisibility(View.VISIBLE);
                draggableView.maximize();
            }
        }, DELAY_MILLIS);
    }

    private void showMenuSlider(final ArrayList<WallPaper> wallPapers) {
        final HashMap<String, String> url_maps = new HashMap<String, String>();
        for (int i = 0; i < 4; i++) {
            int ran = Common.randInt(wallPapers.size());
            String url = wallPapers.get(ran).getUrlImage();
            url_maps.put(Integer.toString(ran), url);
        }
        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterInside)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);
        sliderLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onSliderClick(BaseSliderView baseSliderView) {
        Uri myUri = Uri.parse(baseSliderView.getUrl());
        loadImage(myUri);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = (MainActivity) activity;
    }


}
