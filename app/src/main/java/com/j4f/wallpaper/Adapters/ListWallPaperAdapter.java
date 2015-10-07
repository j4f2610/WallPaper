package com.j4f.wallpaper.Adapters;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.j4f.wallpaper.Model.WallPaper;
import com.j4f.wallpaper.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by pham on 7/23/2015.
 */
public class ListWallPaperAdapter extends RecyclerView.Adapter<ListWallPaperAdapter.GridItemWallPaperHolder> {
    private ArrayList<WallPaper> listUrl;
    public ListWallPaperAdapter(ArrayList<WallPaper> listUrl) {
        this.listUrl = listUrl;
    }
    @Override
    public GridItemWallPaperHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_image,parent,false);
        GridItemWallPaperHolder listWallPaperHolder= new GridItemWallPaperHolder(view);
        return listWallPaperHolder;
    }

    @Override
    public void onBindViewHolder(GridItemWallPaperHolder holder, int position) {
        Uri uri= Uri.parse(listUrl.get(position).getUrlImageMini());
        Picasso.with(holder.wallPaper.getContext()).load(uri).placeholder(R.drawable.image).into(holder.wallPaper);
    }

    @Override
    public int getItemCount() {
        return listUrl.size();
    }

    public static class GridItemWallPaperHolder extends RecyclerView.ViewHolder {
        private ImageView wallPaper;
        private CardView cv;
        GridItemWallPaperHolder(View itemView) {
            super(itemView);
            cv=(CardView)itemView.findViewById(R.id.imgCardView);
            wallPaper=(ImageView)itemView.findViewById(R.id.imgWallPaper);
            wallPaper.setScaleType(ImageView.ScaleType.CENTER);
        }
    }
}
